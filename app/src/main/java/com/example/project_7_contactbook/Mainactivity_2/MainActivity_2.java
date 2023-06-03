package com.example.project_7_contactbook.Mainactivity_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.project_7_contactbook.ContactModel;
import com.example.project_7_contactbook.DBHelper;
import com.example.project_7_contactbook.MainActivity;
import com.example.project_7_contactbook.MainActivity_3;
import com.example.project_7_contactbook.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity_2 extends AppCompatActivity
{

    RecyclerView recyclerView2;
    FloatingActionButton floatingActionButton2;
    LinearLayout callList2 , contactList2;

    ArrayList<ContactModel> contactModelArrayList = new ArrayList<>();

    DBHelper dbHelper;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView2 = findViewById(R.id.RecyclerView2);
        floatingActionButton2 = findViewById(R.id.FlotingActionButton2);
        callList2 = findViewById(R.id.CallList2);
//        contactList2 = findViewById(R.id.ContactList2);

        dbHelper = new DBHelper(MainActivity_2.this);

        displaydata();

        floatingActionButton2.setOnClickListener(view -> {

            Intent Inext;
            Inext = new Intent(MainActivity_2.this , MainActivity_3.class);
            startActivity(Inext);

        });

        callList2.setOnClickListener(view -> {

            Intent Iback;
            Iback = new Intent(MainActivity_2.this , MainActivity.class);
            startActivity(Iback);

        });

    }

    private void displaydata()
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

                ContactModel ContactModel = new ContactModel(id , name , number);

                contactModelArrayList.add(ContactModel);

            }

            Recycler_Adapter_2 recycler_adapter_2 = new Recycler_Adapter_2(this , contactModelArrayList);
            recyclerView2.setAdapter(recycler_adapter_2);

            recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        }
    }

    /////////////////    Create OptionMenu       //////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.mainactivity_optionmenu , menu);


        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        Fragment F = null;

        if (item.getItemId() == R.id.Edit)
        {
            F = new F_Edit();
        }
        if (item.getItemId() == R.id.BlockAndFilter)
        {
            F = new F_Block_Filter();
        }
        if (item.getItemId() == R.id.ManageContacts)
        {
            F = new F_Manage_Contacts();
        }
        if (item.getItemId() == R.id.Settings)
        {
            F = new F_Settings();
        }


        getSupportFragmentManager().beginTransaction().replace(R.id.TextViewContacts , F).commit();
        return super.onOptionsItemSelected(item);
    }
}