package com.kondie.pm_admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    public static Activity activity;
    Toolbar mainToolbar;
    public static RecyclerView mechanicList;
    public static List<MechanicItem> mechanicItems;
    public static List<MechanicItem> markedItems;
    public static MechanicListAdapter mechanicListAdapter;
    private LinearLayoutManager linearLayMan;
    public static ProgressBar loadingMechanics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;

        loadingMechanics = findViewById(R.id.loading_mechanics);
        mainToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);
        mechanicList = findViewById(R.id.mechanic_list);
        mechanicItems = new ArrayList<>();
        linearLayMan = new LinearLayoutManager(activity);
        mechanicList.setLayoutManager(linearLayMan);
        mechanicListAdapter = new MechanicListAdapter(activity, mechanicItems, mechanicList);
        mechanicList.setAdapter(mechanicListAdapter);

        new GetMechanics().execute("5050-00-00 00:00:00");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.add_mechanic_button:
                Intent toSignUpIntent = new Intent(activity, SignUp.class);
                startActivity(toSignUpIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
