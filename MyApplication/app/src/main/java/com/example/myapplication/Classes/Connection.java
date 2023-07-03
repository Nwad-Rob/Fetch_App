package com.example.myapplication.Classes;
import android.os.AsyncTask;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.stream.Collectors;


import com.example.myapplication.Classes.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
public class Connection  {
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
}
