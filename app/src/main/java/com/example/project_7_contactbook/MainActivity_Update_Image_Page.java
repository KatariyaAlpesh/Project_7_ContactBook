package com.example.project_7_contactbook;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity_Update_Image_Page extends AppCompatActivity
{

    ImageView closeButton , saveButton ;
    EditText editName , editNumber , editEmail , editGroup;
    TextView contactProfileName;

    DBHelper dbHelper;

    int idUpdatePage;
    String nameUpdatePage , numberUpdatePage;


    ArrayList<ContactModel> contactModelArrayListUpdatePage = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_update_image_page);

        closeButton = findViewById(R.id.CloseImage1);
        saveButton = findViewById(R.id.SaveContactButton1);
        editName = findViewById(R.id.AddName1);
        editNumber = findViewById(R.id.AddNumber1);
        editEmail = findViewById(R.id.AddEmail1);
        editGroup = findViewById(R.id.AddGroup1);
        contactProfileName = findViewById(R.id.ContactsProfileName);

        dbHelper = new DBHelper(MainActivity_Update_Image_Page.this);


//        idUpdatePage = getIntent().getIntExtra("IdImagePage" 0);
        nameUpdatePage = getIntent().getStringExtra("NameImagePage");
        numberUpdatePage = getIntent().getStringExtra("NumberImagePage");

        editName.setText(nameUpdatePage);
        editNumber.setText(numberUpdatePage);
        contactProfileName.setText(nameUpdatePage);


//        displayDataUpdateImagePage();

//        saveButton.setOnClickListener(view -> {
//
//            ContactModel ContactModel = contactModelArrayListUpdatePage.get();
//
//            String nameTEXT = nameUpdatePage;
//            String numberTEXT = numberUpdatePage;
//
//            Boolean CheckInsertData = dbHelper.saveButton( nameTEXT , numberTEXT);
//
//            if (CheckInsertData == true)
//            {
//                nameTEXT = editName.getText().toString();
//                numberTEXT = editNumber.getText().toString();
//            }
//            else
//            {
//                Toast.makeText(MainActivity_Update_Image_Page.this, "New UpDate Insert", Toast.LENGTH_SHORT).show();
//            }
//
//            int id = contactModelArrayListUpdatePage.get()
//
//            finish();
//
//        });

        closeButton.setOnClickListener(view -> {

            Intent Iback;
            Iback = new Intent(MainActivity_Update_Image_Page.this , MainActivity.class);
            startActivity(Iback);

        });

    }
}