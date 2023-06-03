package com.example.project_7_contactbook.Mainactivity_2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_7_contactbook.ContactModel;
import com.example.project_7_contactbook.DBHelper;
import com.example.project_7_contactbook.R;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class Recycler_Adapter_2 extends RecyclerView.Adapter<Recycler_Adapter_2.MyClass>
{
    MainActivity_2 mainActivity_2;
    ArrayList<ContactModel> contactModelArrayList = new ArrayList<>();

    public Recycler_Adapter_2(MainActivity_2 mainActivity_2, ArrayList<ContactModel> contactModelArrayList)
    {

        this.mainActivity_2 = mainActivity_2;
        this.contactModelArrayList = contactModelArrayList;

    }

    public class MyClass extends RecyclerView.ViewHolder
    {
        LinearLayout linearLayout2;
        TextView textName , textNumber;
        ImageView imageView;

        public MyClass(@NonNull View itemView)
        {
            super(itemView);

            linearLayout2 = itemView.findViewById(R.id.LinearLayout2);
            textName = itemView.findViewById(R.id.ContactName2);
            textNumber = itemView.findViewById(R.id.ContactNumber2);
            imageView = itemView.findViewById(R.id.PopupMenuImage);

        }
    }


    @NonNull
    @Override
    public MyClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        View view = LayoutInflater.from(mainActivity_2).inflate(R.layout.contactlist_image_page_main2, parent , false);

        MyClass M = new MyClass(view);

        return M;
    }


    @Override
    public void onBindViewHolder(@NonNull MyClass holder, @SuppressLint("RecyclerView") int position)
    {
        ContactModel ContactModel = contactModelArrayList.get(position);
        holder.textName.setText(ContactModel.getName());   /// +"   "+ ContactModel.getId());
        holder.textNumber.setText(ContactModel.getNumber());

        DBHelper dbHelper = new DBHelper(mainActivity_2);

        holder.linearLayout2.setOnClickListener(view -> {

            Intent Inext;
            Inext = new Intent(mainActivity_2 , MainActivity_2_Image_Page.class);
            Inext.putExtra("ContactName" , contactModelArrayList.get(holder.getAdapterPosition()).getName());
            Inext.putExtra("ContactNumber" , contactModelArrayList.get(holder.getAdapterPosition()).getNumber());
            Inext.putExtra("ContactId" , contactModelArrayList.get(holder.getAdapterPosition()).getId());
            mainActivity_2.startActivity(Inext);

        });

        holder.imageView.setOnClickListener(view ->
        {
            PopupMenu popup = new PopupMenu(mainActivity_2 , holder.textName);
            popup.getMenuInflater().inflate(R.menu.popup_optionmenu_main_2, popup.getMenu());

            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
            {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem)
                {

                    if (menuItem.getTitle().equals("Update"))
                    {

                        Dialog dialog = new Dialog(mainActivity_2);
                        dialog.setContentView(R.layout.update_page_main2);

                        EditText editname = dialog.findViewById(R.id.editname2);
                        EditText editnumber = dialog.findViewById(R.id.editnumber2);
                        TextView titleText = dialog.findViewById(R.id.textTitle2);
                        Button buttonAdd = dialog.findViewById(R.id.ButtonAdd);

                        titleText.setText("UpDate Contact's");
                        buttonAdd.setText("Update");

                        editname.setText(contactModelArrayList.get(position).getName());
                        editnumber.setText(contactModelArrayList.get(position).getNumber());

                        buttonAdd.setOnClickListener(view1 ->
                        {
                            String Name = " ";
                            String Number = " ";

                            if (!editname.getText().toString().equals(" "))
                            {
                                Name = editname.getText().toString();
                            } else { Toast.makeText(mainActivity_2, "Pliz Enter Name", Toast.LENGTH_SHORT).show(); }


                            if (!editnumber.getText().toString().equals(" "))
                            {
                                Number = editnumber.getText().toString();
                            } else { Toast.makeText(mainActivity_2, "Pliz Enter Number", Toast.LENGTH_SHORT).show(); }


                            int id = contactModelArrayList.get(holder.getAdapterPosition()).getId();
                            contactModelArrayList.set(position, new ContactModel(id , Name, Number));

                            dbHelper.UpdateData2(id , Name, Number);

                            notifyItemChanged(position);

                            dialog.dismiss();

                        });

                        dialog.show();

                    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    if (menuItem.getTitle().equals("Delete"))
                    {

                        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity_2)
                                .setTitle("Delete Contact")
                                .setMessage("Are you sure you Want Delete")
                                .setIcon(R.drawable.d1)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i)
                                    {
                                        int id = contactModelArrayList.get(holder.getAdapterPosition()).getId();
                                        dbHelper.deleteRecord(id);
                                        contactModelArrayList.remove(holder.getAdapterPosition());
                                        notifyItemRemoved(holder.getAdapterPosition());

                                    }
                                })

                                .setNegativeButton("No", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i)
                                    {

                                    }
                                });

                        builder.show();

                    }
                    return true;
                }

            });
            popup.show();

        });

//        holder.linearLayout2.setOnClickListener(view -> {
//
//            Dialog dialog = new Dialog(mainActivity_2);
//            dialog.setContentView(R.layout.update_page);
//
//            EditText editname = dialog.findViewById(R.id.editname2);
//            EditText editnumber = dialog.findViewById(R.id.editnumber2);
//            TextView titleText = dialog.findViewById(R.id.textTitle2);
//            Button buttonAdd = dialog.findViewById(R.id.ButtonAdd);
//
//            titleText.setText("UpDate Contact's");
//            buttonAdd.setText("Update");
//
//            editname.setText(contactModelArrayList.get(position).name);
//            editnumber.setText(contactModelArrayList.get(position).number);
//
//            buttonAdd.setOnClickListener(view1 -> {
//
//                String Name = " ";
//                String Number = " ";
//
//                if (!editname.getText().toString().equals(" "))
//                {
//                    Name = editname.getText().toString();
//                }
//                else
//                {
//                    Toast.makeText(mainActivity_2, "Pliz Enter Name", Toast.LENGTH_SHORT).show();
//                }
//
//                if (!editnumber.getText().toString().equals(" "))
//                {
//                    Number = editname.getText().toString();
//                }
//                else
//                {
//                    Toast.makeText(mainActivity_2, "Pliz Enter Number", Toast.LENGTH_SHORT).show();
//                }
//
//                contactModelArrayList.set(position , new ContactModel(Name , Number));
//
//                notifyItemChanged(position);
//
//            });
//
//            dialog.show();
//
//        });
//
//
//        holder.linearLayout2.setOnLongClickListener(view -> {
//
//            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity_2)
//                    .setTitle("Delete Contact")
//                    .setMessage("Are you sure you Want Delete")
//                    .setIcon(R.drawable.d1)
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
//                    {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i)
//                        {
//                            contactModelArrayList.remove(position);
//                            notifyItemRemoved(position);
//                        }
//                    })
//
//                    .setNegativeButton("No", new DialogInterface.OnClickListener()
//                    {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i)
//                        {
//
//                        }
//                    });
//
//            builder.show();
//
//            return true;
//
//        });
    }

    @Override
    public int getItemCount()
    {
        return contactModelArrayList.size();
    }

}
