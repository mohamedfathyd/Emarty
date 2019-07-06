package com.Emarat.emarty.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.Emarat.emarty.R;
import com.bumptech.glide.Glide;

import me.anwarshahriar.calligrapher.Calligrapher;

public class Show_manager_details extends AppCompatActivity {
TextView name,last_login,phone,password,details,end_date,emara_num;
ImageView imageView;
Intent intent;
String img;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_manager_details);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Droid.ttf", true);

        this.setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );
        name=findViewById(R.id.name);
        last_login=findViewById(R.id.last_login);
        phone=findViewById(R.id.phone);
        password=findViewById(R.id.password);
        details=findViewById(R.id.details);

        emara_num=findViewById(R.id.emaranum);
        imageView=findViewById(R.id.img);
        intent=getIntent();
        name.setText(intent.getStringExtra("name"));
        last_login.setText(intent.getStringExtra("last_login"));
        phone.setText(intent.getStringExtra("phone"));
        password.setText(intent.getStringExtra("password"));
        details.setText(intent.getStringExtra("details"));
        emara_num.setText(intent.getIntExtra("emara_num",0)+"");
        img=intent.getStringExtra("image");
        Glide.with(this).load(img).error(R.drawable.login_back).into(imageView);
    }
}
