package com.Emarat.emarty.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.Emarat.emarty.R;
import com.Emarat.emarty.model.Apiclient_home;
import com.Emarat.emarty.model.apiinterface_home;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_new_DataEntry extends AppCompatActivity {

    EditText input_date,input_name,input_phone,input_Emaranum,input_password,input_info;
    ImageView image;
    AppCompatButton btn_signup;
    String current_date;
    int Emara_num;
    private SharedPreferences sharedpref;
    private SharedPreferences.Editor edt;
    Call<ResponseBody> call = null;
    private apiinterface_home apiinterface;
    ProgressDialog progressDialog;
    final Calendar myCalendar = Calendar.getInstance();
    Bitmap bitmap;
    Toolbar toolbar;
    private  static final int IMAGE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new__data_entry);
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
        sharedpref = getSharedPreferences("ManoAd", Context.MODE_PRIVATE);
        edt = sharedpref.edit();
        Emara_num=sharedpref.getInt("emara_num",0);
        Intializer();
        String dateStr = "04/05/2010";
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(c.getTime());
        current_date =formattedDate;
     //   Toast.makeText(Add_New_Manager.this,current_date,Toast.LENGTH_LONG).show();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        input_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(
                        Add_new_DataEntry.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchInfo();
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }
    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        input_date.setText(sdf.format(myCalendar.getTime()));
    }
    public void Intializer(){
        input_date=findViewById(R.id.input_date);
        input_name=findViewById(R.id.input_name);
        input_Emaranum=findViewById(R.id.input_Emaranum);
        input_info=findViewById(R.id.input_info);
        input_phone=findViewById(R.id.input_phone);
        input_password=findViewById(R.id.input_password);
        image=findViewById(R.id.image);
        btn_signup=findViewById(R.id.btn_signup);
    }
    public void fetchInfo() {
        progressDialog = ProgressDialog.show(Add_new_DataEntry.this, "جاري أضافة مدخل بيانات", "Please wait...", false, false);
        progressDialog.show();
        String image;
        try {
            image = convertToString();
        }catch (Exception e){
            image="d";
        }



        apiinterface = Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<ResponseBody> call = apiinterface.getcontactsadddataentry(input_name.getText().toString(),image,
                input_phone.getText().toString(),
                Emara_num, input_password.getText().toString(),current_date,input_date.getText().toString(),
                input_info.getText().toString());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();

                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(Add_new_DataEntry.this);
                dlgAlert.setMessage("تم أضافة مدخل البيانات بنجاح ");
                dlgAlert.setTitle("الدفتر الألكترونى");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Add_new_DataEntry.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE);
    }
    private String convertToString()
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,70,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== IMAGE && resultCode==RESULT_OK && data!=null)
        {
            Uri path = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
