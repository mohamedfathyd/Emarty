package com.Emarat.emarty.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Emarat.emarty.R;
import com.Emarat.emarty.model.Apiclient_home;
import com.Emarat.emarty.model.apiinterface_home;
import com.Emarat.emarty.model.user_content;

import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    Button takhaty;
    AppCompatButton Naccount;
    AppCompatButton login;

    TextView newaccount;
    EditText textInputEditTextphone,textInputEditTextpassword;

    private List<user_content> contactList;
    private apiinterface_home apiinterface;
    private SharedPreferences sharedpref;
    private SharedPreferences.Editor edt;
    ProgressDialog progressDialog;
    String codee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Droid.ttf", true);


        login=findViewById(R.id.btn_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchInfo();
            }
        });



        textInputEditTextphone=findViewById(R.id.input_email);
        textInputEditTextpassword=findViewById(R.id.input_password);





        sharedpref = getSharedPreferences("ManoAd", Context.MODE_PRIVATE);
        edt = sharedpref.edit();





    }
    public void fetchInfo(){
        progressDialog = ProgressDialog.show(Login.this,"جاري تسجيل الدخول","Please wait...",false,false);
        progressDialog.show();
        String phone=textInputEditTextphone.getText().toString();
        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<user_content>> call= apiinterface.getcontacts_login(phone,
                textInputEditTextpassword.getText().toString());
        call.enqueue(new Callback<List<user_content>>() {
            @Override
            public void onResponse(Call<List<user_content>> call, Response<List<user_content>> response) {
                try{
                    if(response.isSuccessful()){

                        contactList = response.body();

                        progressDialog.dismiss();
                        edt.putInt("id",contactList.get(0).getId());
                        edt.putString("name",contactList.get(0).getName());
                        edt.putInt("type_user",contactList.get(0).getType_user());
                        edt.putString("phone",contactList.get(0).getPhone());
                        edt.putString("last_login",contactList.get(0).getLast_login());
                        edt.putString("password",contactList.get(0).getPassword());
                        edt.putInt("emara_num",contactList.get(0).getEmara_num());
                        edt.putString("start_date",contactList.get(0).getStart_date());
                        edt.putString("image",contactList.get(0).getImage());
                        edt.putString("details",contactList.get(0).getDetails());
                        edt.putString("end_date", String.valueOf(contactList.get(0).getEnd_date()));

                        edt.putString("remember","yes");
                        edt.apply();
                        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(Login.this);
                        dlgAlert.setMessage("تم تسجيل الدخول بنجاح");
                        dlgAlert.setTitle("الدفتر الألكترونى");
                        dlgAlert.setPositiveButton("OK", null);
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();
                        if(contactList.get(0).getType_user()==1){
                        startActivity(new Intent(Login.this,MainActivity.class));}
                        if(contactList.get(0).getType_user()==3){
                            startActivity(new Intent(Login.this,Main_DataEntrie.class));}
                        if(contactList.get(0).getType_user()==2){
                            startActivity(new Intent(Login.this,Main_Manager.class));}
                        progressDialog.dismiss();
                    }}
                catch (Exception e){
                    Toast.makeText(Login.this,"هناك خطأ فى الهاتف او الرقم السري  ",Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<List<user_content>> call, Throwable t) {
                Toast.makeText(Login.this,"هناك خطأ فى الهاتف او الرقم السري   ",Toast.LENGTH_LONG).show();

                progressDialog.dismiss();
            }
        });
    }}