package com.example.project_7_contactbook.Mainactivity_2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_7_contactbook.MainActivity_Update_Image_Page;
import com.example.project_7_contactbook.R;

public class MainActivity_2_Image_Page extends AppCompatActivity
{

    ImageView closeButton , saveContactButton ;
    TextView contactName , contactNumber;
    int idImagePage;
    String nameImagePage , numberImagePage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_image_page);

        closeButton = findViewById(R.id.CloseButtonImagePage);
        saveContactButton = findViewById(R.id.SaveContactButtonImagePage);
        contactName = findViewById(R.id.ContactsNameImagePage);
        contactNumber = findViewById(R.id.ContactNumberImagePage);


        idImagePage = getIntent().getIntExtra("ContactId" , 0);
        nameImagePage = getIntent().getStringExtra("ContactName" );
        numberImagePage = getIntent().getStringExtra("ContactNumber");

        contactName.setText(nameImagePage);
        contactNumber.setText(numberImagePage);

        closeButton.setOnClickListener(view -> {

            Intent Iback;
            Iback = new Intent(MainActivity_2_Image_Page.this , MainActivity_2.class);
            startActivity(Iback);

        });

        saveContactButton.setOnClickListener(view -> {

            Intent Inext;
            Inext = new Intent(MainActivity_2_Image_Page.this , MainActivity_Update_Image_Page.class);

            Inext.putExtra("NameImagePage" , nameImagePage);
            Inext.putExtra("NumberImagePage" , numberImagePage);
            Inext.putExtra("IdImagePage" , idImagePage);

            startActivity(Inext);

        });


    }
}