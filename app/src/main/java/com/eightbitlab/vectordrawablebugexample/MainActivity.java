package com.eightbitlab.vectordrawablebugexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recycler = (RecyclerView) findViewById(R.id.list);
        recycler.setAdapter(new ExampleListAdapter(this));

        View root = findViewById(R.id.root);

        LolView lolView = (LolView) findViewById(R.id.lol);
        lolView.setRoot(root);
    }
}
