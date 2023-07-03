package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.stream.Collectors;


import com.example.myapplication.Classes.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonE;
    TextView txt;

    Item item = new Item();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonE = (Button) findViewById(R.id.btn1);
        txt = (TextView) findViewById(R.id.txt1);
        buttonE.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // Obtain the JSON url
        String url = "https://fetch-hiring.s3.amazonaws.com/hiring.json";
        try {
            String jsonData = getHTML(url);
            List<Item> items = parseJSON(jsonData);
            displayItems(items);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(getHTML(url));

        System.out.println(parseJSON(url));
    }

    //Creating connection, obtain the Json Object and transform it into a string
    public static String getHTML(String urlToRead) throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        return result.toString();
    }

    //parsing through a Json Object
    private static List<Item> parseJSON(String jsonData) {
        Gson gson = new Gson();
        return gson.fromJson(jsonData, new TypeToken<List<Item>>() {}.getType());
    }

    private static void displayItems(List<Item> items) {
        Map<Integer, List<Item>> itemsByListId = items.stream()
                .filter(item -> item.getName() != null && !item.getName().isEmpty())
                .collect(Collectors.groupingBy(Item::getListId));

        itemsByListId.forEach((listId, itemList) -> {
            itemList.sort(Comparator.comparing(Item::getName));
            System.out.println("List ID: " + listId);
            itemList.forEach(item -> System.out.println("  Item Name: " + item.getName()));
            System.out.println();
        });
    }
}