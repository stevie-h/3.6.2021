package com.example.demoHW;

import java.sql.*;
import java.util.ArrayList;


public class RestaurantREPO
{
    private String m_conn;

    public RestaurantREPO(String m_conn)
    {
        this.m_conn = m_conn;
    }

    public ArrayList<RestaurantDTO> getAllRestaurants()
    {
        ArrayList<RestaurantDTO> m_restaurants = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(m_conn))
        {
            if(conn != null)
            {
                String sql = "SELECT * FROM Restaurants";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next())
                {
                    RestaurantDTO rest = new RestaurantDTO(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("foodtype"),
                            rs.getFloat("mealprice"));
                    m_restaurants.add(rest);
                }
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return m_restaurants;
    }

    public RestaurantDTO getRestaurantById(int id)
    {
        try (Connection conn = DriverManager.getConnection(m_conn))
        {
            if(conn != null)
            {
                String sql = "SELECT * FROM Restaurants WHERE id = " + id;
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next())
                {
                    RestaurantDTO rest = new RestaurantDTO(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("foodtype"),
                            rs.getFloat("mealprice"));
                    return rest;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void insertRestaurant(RestaurantDTO rest)
    {
        try (Connection conn = DriverManager.getConnection(m_conn))
        {
            if(conn != null)
            {
                String sql = String.format("INSERT INTO Restaurants (id, name, address, foodtype, mealprice)" +
                                " VALUES (%d, '%s', '%s', '%s', %f)", rest.getM_id(), rest.getM_name(),
                        rest.getM_address(), rest.getM_foodtype(), rest.getM_mealprice());
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateRestaurant(RestaurantDTO rest, int id)
    {
        try (Connection conn = DriverManager.getConnection(m_conn))
        {
            if(conn != null)
            {
                String sql = String.format("UPDATE Restaurants SET " +
                                "name = '%s', " +
                                "address = '%s', " +
                                "foodtype = '%s', " +
                                "mealprice = %f" +
                                " WHERE id = %d", rest.getM_name(), rest.getM_address(), rest.getM_foodtype(),
                        rest.getM_mealprice(), rest.getM_id());
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteRestaurant(int id)
    {
        try (Connection conn = DriverManager.getConnection(m_conn))
        {
            if(conn != null)
            {
                String sql = "DELETE FROM Restaurants WHERE id = " + id;
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }




}
