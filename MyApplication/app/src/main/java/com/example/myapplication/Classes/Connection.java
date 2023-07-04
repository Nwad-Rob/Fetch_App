package com.example.myapplication.Classes;
import android.os.AsyncTask;
import android.util.Log;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.stream.Collectors;


import com.example.myapplication.Classes.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
public class Connection  {
    String result;
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
        conn.disconnect();
        return result.toString();
    }
    public static List<Item> parseJSON(String jsonData) {
        Gson gson = new Gson();
        return gson.fromJson(jsonData, new TypeToken<List<Item>>() {}.getType());
    }

    public  static String displayItems(List<Item> items) {
        StringBuilder output = new StringBuilder();
        Map<Integer, List<Item>> itemsByListId = items.stream()
                .filter(item -> item.getName() != null && !item.getName().isEmpty())
                .collect(Collectors.groupingBy(Item::getListId));

        itemsByListId.forEach((listId, itemList) -> {
            itemList.sort(Comparator.comparing(Item::getName));
            output.append("List ID: ").append(listId).append("\n");
            itemList.forEach(item -> output.append("  Item Name: ").append(item.getName()).append("\n"));
            output.append("\n");
        });

        return output.toString();
    }

    public void execute (){
        String url = "https://fetch-hiring.s3.amazonaws.com/hiring.json";
        try {
            String jsonData = getHTML(url);
            Log.d("backend",  jsonData);
            List<Item> items = parseJSON(jsonData);
            this.result = displayItems(items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResult(){
        return this.result;
    }
}
