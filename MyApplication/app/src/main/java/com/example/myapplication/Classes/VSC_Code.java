package com.example.myapplication.Classes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class VSC_Code {


    /* Parameter: String
      return : String
      Purpose: Attempting connection to the JSON Object
    */
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

    /* Parameter: String
       return : List<Item>
       Purpose: Parsing through the JSON object
       Info : TypeToken aids Gson to deserialize a generic class
     */
    public static List<Item> parseJSON(String jsonData) {
        Gson gson = new Gson();
        return gson.fromJson(jsonData, new TypeToken<List<Item>>() {}.getType());
    }

    /* Parameter: List<Item>
       return : String
       Purpose: Sort, group and filter
     */
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
