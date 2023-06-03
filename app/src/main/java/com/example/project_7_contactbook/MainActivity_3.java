package com.example.project_7_contactbook;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.project_7_contactbook.Mainactivity_2.MainActivity_2;

public class MainActivity_3 extends AppCompatActivity
{

    ImageView closeButton3 , saveButton3 ;
    EditText editName3 , editNumber3 , editEmail3 , editGroup3;

    DBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        closeButton3 = findViewById(R.id.CloseImage3);
        saveButton3 = findViewById(R.id.SaveContactButton3);
        editName3 = findViewById(R.id.AddName3);
        editNumber3 = findViewById(R.id.AddNumber3);
        editEmail3 = findViewById(R.id.AddEmail3);
        editGroup3 = findViewById(R.id.AddGroup3);

        dbHelper = new DBHelper(MainActivity_3.this);

        saveButton3.setOnClickListener(view -> {

            String nameTEXT = editName3.getText().toString();
            String numberTEXT = editNumber3.getText().toString();

            Boolean CheckInsertData = dbHelper.saveButton( nameTEXT , numberTEXT);

            if (CheckInsertData == true)
            {
                Toast.makeText(MainActivity_3.this, "New Data Insert", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(MainActivity_3.this, "New Data Insert", Toast.LENGTH_SHORT).show();
            }
            finish();

        });

        closeButton3.setOnClickListener(view -> {

            Intent Iback;
            Iback = new Intent(MainActivity_3.this , MainActivity_2.class);
            startActivity(Iback);

        });
    }


}