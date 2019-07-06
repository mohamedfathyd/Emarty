package com.Emarat.emarty.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Emarat.emarty.Activity.Show_manager_details;
import com.Emarat.emarty.R;
import com.Emarat.emarty.model.apiinterface_home;
import com.Emarat.emarty.model.user_content;
import com.bumptech.glide.Glide;


import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerAdapter_manager extends RecyclerView.Adapter<RecyclerAdapter_manager.MyViewHolder> {
    Typeface myTypeface;
    private Context context;
    List<user_content> contactslist;
     apiinterface_home apiinterface;
    public RecyclerAdapter_manager(Context context, List<user_content> contactslist){
        this.contactslist=contactslist;
        this.context=context;


    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_show,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {


    holder.Name.setText(contactslist.get(position).getName());
   holder.last_login.setText("آخر تسجيل دخول :" +contactslist.get(position).getLast_login());
        Glide.with(context).load(contactslist.get(position).getImage()).error(R.drawable.login_back).into(holder.image);
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(context, Show_manager_details.class);
               intent.putExtra("name",contactslist.get(position).getName());
               intent.putExtra("image",contactslist.get(position).getImage());
               intent.putExtra("last_login",contactslist.get(position).getLast_login());
               intent.putExtra("emara_num",contactslist.get(position).getEmara_num());
               intent.putExtra("start_date",contactslist.get(position).getStart_date());
               intent.putExtra("end_date",contactslist.get(position).getEnd_date());
               intent.putExtra("phone",contactslist.get(position).getPhone());
               intent.putExtra("password",contactslist.get(position).getPassword());
               intent.putExtra("details",contactslist.get(position).getDetails());
               context.startActivity(intent);
           }
       });
    }
    @Override
    public int getItemCount() {
        return contactslist.size();
    }

public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Name,last_login;
        ImageView image;

        ImageView delete;
    public MyViewHolder(View itemView) {
        super(itemView);

        Name=(TextView)itemView.findViewById(R.id.txt_fish_title);
        last_login=(TextView)itemView.findViewById(R.id.txt_title);
        image=(ImageView)itemView.findViewById(R.id.imageView3);
        delete=(ImageView)itemView.findViewById(R.id.delete);

    }
}

}