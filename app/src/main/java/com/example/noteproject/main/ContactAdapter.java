package com.example.noteproject.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteproject.R;
import com.example.noteproject.model.ContactModel;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactsViewHolder> {

    private LayoutInflater layoutInflater;
    private List<ContactModel> list;
    //Activity activity;
    Context context;

    public ContactAdapter (Context context, List<ContactModel> list){
        this.list = list;
       // this.activity = activity;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_contacts, parent, false);
        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        holder.txtContactName.setText(list.get(position).getName());
        holder.txtContactNumber.setText(list.get(position).getPhone());

        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = holder.txtContactNumber.getText().toString().trim();
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                intentCall.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intentCall);
                //context.getApplicationContext().startActivity(i ntentCall);
                //((MainActivity)context).startActivity(intentCall);
            }
        });


        holder.btnWA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent webIntent = new Intent(Intent.ACTION_WEB_SEARCH);
//                webIntent.putExtra(SearchManager.QUERY, holder.txtContactNumber.getText().toString().trim());
//                context.startActivity(webIntent);
                String phone = holder.txtContactNumber.getText().toString().trim();
                String url = "https://api.whatsapp.com/send?phone=" + phone;
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse(url));
                context.startActivity(intent);
                //v.getContext().startActivity(webIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder {

        TextView txtContactName, txtContactNumber;
        Button btnCall, btnWA;

        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);

            txtContactName = itemView.findViewById(R.id.txt_item_contact_name);
            txtContactNumber = itemView.findViewById(R.id.txt_item_contact_phone);

            btnCall = itemView.findViewById(R.id.btn_item_call);
            btnWA = itemView.findViewById(R.id.btn_item_whatsapp);
        }
    }
}
