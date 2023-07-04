package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Classes.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Connection;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class test_main extends AppCompatActivity {
    private TextView mTextViewResult;
    private String myResponse;
    private String outputStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main);

        mTextViewResult = findViewById(R.id.txt_test);

        OkHttpClient client = new OkHttpClient();
        String url = "https://fetch-hiring.s3.amazonaws.com/hiring.json";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                     myResponse = response.body().string();
                    StringBuilder output = new StringBuilder();
                    Gson gson = new Gson();
                    List<Item> items = gson.fromJson(myResponse, new TypeToken<List<Item>>() {}.getType());
                    Map<Integer, List<Item>> itemsByListId = items.stream()
                            .filter(item -> item.getName() != null && !item.getName().isEmpty())
                            .collect(Collectors.groupingBy(Item::getListId));

                    itemsByListId.forEach((listId, itemList) -> {
                        itemList.sort(Comparator.comparing(Item::getName));
                        output.append("List ID: ").append(listId).append("\n");
                        itemList.forEach(item -> output.append("  Item Name: ").append(item.getName()).append("\n"));
                        output.append("\n");
                    });
                    outputStr = output.toString();
                    //ans

                    test_main.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextViewResult.setText(outputStr);
                        }
                    });
                }
            }
        });




    }
}
