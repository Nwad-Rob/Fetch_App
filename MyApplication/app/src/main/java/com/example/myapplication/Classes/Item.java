package com.example.myapplication.Classes;

// An item class that displays elements in the Json Object. Create getters and setters
public class Item {

    private int id;
    private int listId;
    private String name;



    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getListId() {
        return this.listId;
    }



    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
