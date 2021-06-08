package com.example.demoHW;


public class RestaurantDTO
{
    protected int m_id;
    protected String m_name;
    protected String m_address;
    protected String m_foodtype;
    protected float m_mealprice;

    public RestaurantDTO()
    {
        super();
    }

    public RestaurantDTO(int m_id, String m_name, String m_address, String m_foodtype, float m_mealprice)
    {
        this.m_id = m_id;
        this.m_name = m_name;
        this.m_address = m_address;
        this.m_foodtype = m_foodtype;
        this.m_mealprice = m_mealprice;
    }

    public int getM_id()
    {
        return m_id;
    }

    public String getM_name()
    {
        return m_name;
    }

    public String getM_address()
    {
        return m_address;
    }

    public String getM_foodtype()
    {
        return m_foodtype;
    }

    public float getM_mealprice()
    {
        return m_mealprice;
    }

    public void setM_id(int m_id)
    {
        this.m_id = m_id;
    }

    public void setM_name(String m_name)
    {
        this.m_name = m_name;
    }

    public void setM_address(String m_address)
    {
        this.m_address = m_address;
    }

    public void setM_foodtype(String m_foodtype)
    {
        this.m_foodtype = m_foodtype;
    }

    public void setM_mealprice(float m_mealprice)
    {
        this.m_mealprice = m_mealprice;
    }

    @Override
    public String toString()
    {
        return "RestaurantDTO{" +
                "m_id=" + m_id +
                ", m_name='" + m_name + '\'' +
                ", m_address='" + m_address + '\'' +
                ", m_foodtype='" + m_foodtype + '\'' +
                ", m_mealprice=" + m_mealprice +
                '}';
    }



}
