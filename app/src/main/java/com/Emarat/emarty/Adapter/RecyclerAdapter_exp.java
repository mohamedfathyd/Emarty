package com.Emarat.emarty.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Emarat.emarty.R;
import com.Emarat.emarty.model.Exp_content;
import com.Emarat.emarty.model.Reservation_content;
import com.Emarat.emarty.model.apiinterface_home;

import java.util.List;

public class RecyclerAdapter_exp extends RecyclerView.Adapter<RecyclerAdapter_exp.MyViewHolder> {
    Typeface myTypeface;
    private Context context;
    List<Exp_content> contactslist;
     apiinterface_home apiinterface;
    public RecyclerAdapter_exp(Context context, List<Exp_content> contactslist){
        this.contactslist=contactslist;
        this.context=context;


    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_exp,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {


    holder.Name.setText("صرف فى :"+contactslist.get(position).getName());
   holder.finish_date.setText("المبلغ :" +contactslist.get(position).getPrice());


       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });
    }
    @Override
    public int getItemCount() {
        return contactslist.size();
    }

public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Name,finish_date;

    public MyViewHolder(View itemView) {
        super(itemView);

        Name=(TextView)itemView.findViewById(R.id.txt_fish_title);
        finish_date=(TextView)itemView.findViewById(R.id.txt_title);

    }
}

}