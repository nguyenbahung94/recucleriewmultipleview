package com.example.nbhung.demorecyclerviewmultipleviewtypes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nbhung.demorecyclerviewmultipleviewtypes.Adapter.MyAdapter;
import com.example.nbhung.demorecyclerviewmultipleviewtypes.model.item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView list;
    private RecyclerView.LayoutManager layoutManager;
    private List<item> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (RecyclerView) findViewById(R.id.recycleview);
        list.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        setData();
    }

    private void setData() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                item itemss = new item("this is item" + (i + 1), "this is child item" + (i + 1), true);
                itemList.add(itemss);
            } else {
                item itemss = new item("this is item" + (i + 1), "this is child item" + (i + 1), false);
                itemList.add(itemss);
            }
        }
        MyAdapter adapter=new MyAdapter(itemList);
        list.setAdapter(adapter);
    }
}
