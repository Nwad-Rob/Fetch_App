package com.example.myapplication.Classes;
import android.util.Log;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.stream.Collectors;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
public class VSC_Code {


    // Creating a connection to the url Link and returning it as a String
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

    // parsing through the Json Object using Gson ( google Library)
    public static List<Item> parseJSON(String jsonData) {
        Gson gson = new Gson();
        return gson.fromJson(jsonData, new TypeToken<List<Item>>() {}.getType());
    }

    // Filtering the Item using streams to filter and group
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

}
