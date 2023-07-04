package com.example.myapplication;
/*

import org.junit.jupiter.api.Test;

import com.example.Classes.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;


class AppTest {

    String jsonStr;
    String url;
    String filteredItem;

    @BeforeEach
    void setUp(){
        url = "https://fetch-hiring.s3.amazonaws.com/hiring.json";
        try{
            jsonStr = App.getHTML(url);
        }catch (IOException e){
            assertNotNull(jsonStr);
        }
    }

    @Test
    void testApp() {
        assertEquals(1, 1);
    }

    @Test
    void testGetJson(){
        assertNotNull(jsonStr);
    }

    @Test
    void testParseJson(){
        List<Item> items = App.parseJSON(jsonStr);
        assertNotEquals(0, items.size());
    }

    @Test
    void testFilter(){
        List<Item> items = App.parseJSON(jsonStr);
        filteredItem = App.testDisplayItems(items);
        assertNotNull(filteredItem);
    }

    @Test
    void testResult(){
        List<Item> items = App.parseJSON(jsonStr);
        filteredItem = App.testDisplayItems(items);
        assertTrue(filteredItem.contains("Item Name: Item 749"));
    }



}
*/