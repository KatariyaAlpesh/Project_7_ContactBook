package com.example.project_7_contactbook;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recycler_Adapter_1 extends RecyclerView.Adapter<Recycler_Adapter_1.MyClass>
{

    MainActivity mainActivity;
    ArrayList<ContactModel> contactModelArrayList1 = new ArrayList<>();

    public Recycler_Adapter_1(MainActivity mainActivity, ArrayList<ContactModel> contactModelArrayList1)
    {

        this.mainActivity = mainActivity;
        this.contactModelArrayList1 = contactModelArrayList1;

    }

    public class MyClass extends RecyclerView.ViewHolder
    {
        LinearLayout linearLayout;
        ImageView upArrow , popupMenuinfoImage;
        TextView contactName , contactNumber , timeUpdate;

        public MyClass(@NonNull View itemView)
        {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.LinearLayout);
            upArrow = itemView.findViewById(R.id.OutgoingCalls);
            popupMenuinfoImage = itemView.findViewById(R.id.PopupMenuinfoImage);
            contactName = itemView.findViewById(R.id.ContactName1);
            contactNumber = itemView.findViewById(R.id.ContactNumber1);
            timeUpdate = itemView.findViewById(R.id.TimeUpdate1);

        }
    }

    @NonNull
    @Override
    public MyClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(mainActivity).inflate(R.layout.calllist_image_page_main , parent , false);

        MyClass M = new MyClass(view);

        return M;
    }

    @Override
    public void onBindViewHolder(@NonNull Recycler_Adapter_1.MyClass holder, int position)
    {

        ContactModel contactModel = contactModelArrayList1.get(position);
        holder.contactName.setText(contactModel.getName() + "   " + contactModel.getId());
        holder.contactNumber.setText(contactModel.getNumber());

        holder.popupMenuinfoImage.setOnLongClickListener(view -> {

            PopupMenu popupMenu = new PopupMenu(mainActivity , holder.contactName);
            popupMenu.getMenuInflater().inflate(R.menu.popup_optionmenu_main , popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
            {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem)
                {
                    DBHelper dbHelper = new DBHelper(mainActivity);

                    if (menuItem.getTitle().equals("Update"))
                    {
                        Intent Inext;
                        Inext = new Intent(mainActivity , MainActivity_Update_Image_Page.class);
                        mainActivity.startActivity(Inext);

                    }

                    if (menuItem.getTitle().equals("Delete"))
                    {
                        int id = contactModelArrayList1.get(holder.getAdapterPosition()).getId();
                        dbHelper.deleteRecord(id);
                        contactModelArrayList1.remove(holder.getAdapterPosition());
                        notifyItemRemoved(holder.getAdapterPosition());
                    }

                    return true;
                }
            });

            popupMenu.show();
            return true;
        });
    }

    @Override
    public int getItemCount()
    {
        return 0;
    }


}
