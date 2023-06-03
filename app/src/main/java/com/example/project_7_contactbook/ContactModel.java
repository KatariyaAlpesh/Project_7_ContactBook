package com.example.project_7_contactbook;

public class ContactModel
{

    int id;
    String name;
    String number;

//    public ContactModel( String name, String number)
//    {
//        this.id = id;
//        this.name = name;
//        this.number = number;
//    }

    public ContactModel(int id, String name, String number)
    {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

}
