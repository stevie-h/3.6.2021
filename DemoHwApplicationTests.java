package com.example.demoHW;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class DemoHwApplicationTests
{
	private static final RestaurantREPO restaurantREPO = new RestaurantREPO
			("jdbc:sqlite:E:/SQLite/rest03062021.db");

	@Test
	void contextLoads()
	{
	}

	
	@Test
	public void test_get_restaurant_id1()
	{
		ClientConfig clientConfig = new DefaultClientConfig();
		Client client = Client.create(clientConfig);
		WebResource webResource = client.resource(UriBuilder.fromUri("http://localhost:8080/restaurant/1").build());

		String result = webResource.path("").path("").accept(String.valueOf(MediaType.APPLICATION_JSON)).get(String.class);
		System.out.println(result);

		Gson gson = new Gson();
		RestaurantDTO rest_id1 = gson.fromJson(JsonParser.parseString(result).getAsJsonObject(), RestaurantDTO.class);

		RestaurantDTO expectedResult_id1 = restaurantREPO.getRestaurantById(1);
		System.out.println(expectedResult_id1);

		assertEquals(expectedResult_id1.m_id, rest_id1.m_id);
		assert rest_id1.m_name.equals(expectedResult_id1.m_name) == true;

	}

	@Test
	public void test_post_restaurant_id4()
	{
		Gson gson = new Gson();

		ClientConfig clientConfig = new DefaultClientConfig();
		Client client = Client.create(clientConfig);
		WebResource webResource =
				client.resource(UriBuilder.fromUri("http://localhost:8080/restaurant").build());

		RestaurantDTO rest = new RestaurantDTO(4, "Rest 4", "4 St", "Type 4",44.44f);

		ClientResponse resp =
				webResource.path("").path("").accept("application/json").type("application/json").post(ClientResponse.class,
						gson.toJson(rest));

		if(resp.getStatus() != 201)
		{
			assert false;
		}

		webResource = client.resource(UriBuilder.fromUri("http://localhost:8080/restaurant/4").build());
		String result =  webResource.path("").accept(String.valueOf(MediaType.APPLICATION_JSON)).get(String.class);
		System.out.println(result);

		RestaurantDTO rest_id4 = gson.fromJson(JsonParser.parseString(result).getAsJsonObject(), RestaurantDTO.class);

		RestaurantDTO expectedResult_id4 = restaurantREPO.getRestaurantById(4);
		System.out.println(expectedResult_id4);

		assert rest_id4.m_id == expectedResult_id4.m_id;
		assert rest_id4.m_name.equals(expectedResult_id4.m_name) == true;
	}

	@Test
	public void test_put_restaurant_id2()
	{
		Gson gson = new Gson();

		ClientConfig clientConfig = new DefaultClientConfig();
		Client client = Client.create(clientConfig);
		WebResource webResource =  client.resource(UriBuilder.fromUri("http://localhost:8080/restaurant/2").build());

		RestaurantDTO restaurant = new RestaurantDTO(2, "Rest 2", "2 St", "Type 2",22.22f);

		ClientResponse resp = webResource.accept("application/json").type("application/json").put(ClientResponse.class,
				gson.toJson(restaurant));

		if(resp.getStatus() != 200)
		{
			assert false;
		}

		webResource = client.resource(UriBuilder.fromUri("http://localhost:8080/restaurant/2").build());
		String result =  webResource.path("").accept(String.valueOf(MediaType.APPLICATION_JSON)).get(String.class);
		System.out.println(result);

		RestaurantDTO rest_id2 = gson.fromJson(JsonParser.parseString(result).getAsJsonObject(), RestaurantDTO.class);

		restaurantREPO.updateRestaurant(rest_id2, 2);

		RestaurantDTO expectedResult_id2 = restaurantREPO.getRestaurantById(2);
		System.out.println(expectedResult_id2);

		assert rest_id2.m_name.equals(expectedResult_id2.m_name) == true;
		assert rest_id2.m_foodtype.equals(expectedResult_id2.m_foodtype) == true;
	}


	@Test
	public void test_delete_restaurant_id2()
	{
		Gson gson = new Gson();

		ClientConfig clientConfig = new DefaultClientConfig();

		Client client = Client.create(clientConfig);
		WebResource webResource =  client.resource(UriBuilder.fromUri("http://localhost:8080/restaurant/2").build());
		webResource.accept("application/json").type("application/json").delete();

		webResource = client.resource(UriBuilder.fromUri("http://localhost:8080/restaurant/2").build());
		String result =  webResource.path("").accept(String.valueOf(MediaType.APPLICATION_JSON)).get(String.class);
		System.out.println(result);

		restaurantREPO.deleteRestaurant(2);
		RestaurantDTO expectedResult_id2 = restaurantREPO.getRestaurantById(2);
		System.out.println(expectedResult_id2);

		assert result.equals("") == true;
	}



}
