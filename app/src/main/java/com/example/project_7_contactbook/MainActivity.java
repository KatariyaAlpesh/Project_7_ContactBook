package com.example.project_7_contactbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.project_7_contactbook.Mainactivity_2.MainActivity_2;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    LinearLayout callList , contactList;

    ArrayList<ContactModel> contactModelArrayList1 = new ArrayList<>();

    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.RecyclerView);
        floatingActionButton = findViewById(R.id.FlotingActionButton);
//        callList = findViewById(R.id.CallList);
        contactList = findViewById(R.id.ContactList);


        dbHelper = new DBHelper(MainActivity.this);

        displaydata1();

        contactList.setOnClickListener(view -> {

            Intent Inext;
            Inext = new Intent(MainActivity.this , MainActivity_2.class);
            startActivity(Inext);

        });
        
    }

    private void displaydata1()
    {
        Cursor cursor = dbHelper.getData();

        if (cursor.getCount() == 0)
        {
            Toast.makeText(this, "No Data Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while (cursor.moveToNext())
            {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String number = cursor.getString(2);

                ContactModel model = new ContactModel(id , name , number);

                contactModelArrayList1.add(model);

            }

            Recycler_Adapter_1 recycler_adapter_1 = new Recycler_Adapter_1(this , contactModelArrayList1);
            recyclerView.setAdapter(recycler_adapter_1);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.popup_optionmenu_main , menu);

        if (menu instanceof MenuBuilder)
        {
            MenuBuilder M = (MenuBuilder) menu;

            M.setOptionalIconsVisible(true);
        }
        return super.onCreateOptionsMenu(menu);
    }


}