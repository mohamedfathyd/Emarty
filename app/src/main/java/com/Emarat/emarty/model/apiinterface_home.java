package com.Emarat.emarty.model;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface apiinterface_home {


    @FormUrlEncoded
    @POST("montag/WordPad/Emara_login.php")
    Call<List<user_content>>getcontacts_login(@Field("phonee") String phonee, @Field("password") String password);
    @GET("montag/WordPad/Emara_annonce.php")
    Call<List<contact_annonce>> getcontacts_annonce();
    @FormUrlEncoded
    @POST("montag/WordPad/Emara_add_manager.php")
    Call<ResponseBody> getcontactsadd(@Field("name") String name, @Field("image") String image,
                                      @Field("phone") String phone,
                                      @Field("emara_num") int Emara_num,
                                      @Field("password") String password,
                                      @Field("start_date") String start_date,
                                      @Field("end_date") String end_date,
                                      @Field("details") String details );
    @FormUrlEncoded
    @POST("montag/WordPad/Emara_add_DataEntry.php")
    Call<ResponseBody> getcontactsadddataentry(@Field("name") String name, @Field("image") String image,
                                      @Field("phone") String phone,
                                      @Field("emara_num") int Emara_num,
                                      @Field("password") String password,
                                      @Field("start_date") String start_date,
                                      @Field("end_date") String end_date,
                                      @Field("details") String details );
    @GET("montag/WordPad/Emara_all_manager.php")
    Call<List<user_content>> getcontacts_manger();
    @FormUrlEncoded
    @POST("montag/WordPad/Emara_all_manager_search.php")
    Call<List<user_content>> getcontacts_manger_search(@Field("name") String name);
    @GET("montag/WordPad/Emara_all_DataEntries.php")
    Call<List<user_content>> getcontacts_users();
    @FormUrlEncoded
    @POST("montag/WordPad/Emara_all_DataEntries_forManager.php")
    Call<List<user_content>> all_dataentryformanager(@Field("emara_num") int emara_num);
    @FormUrlEncoded
    @POST("montag/WordPad/Emara_delete_manager.php")
    Call<ResponseBody> delete_manager(@Field("id") int id);
    @FormUrlEncoded
    @POST("montag/WordPad/Emara_add_annonce.php") Call<ResponseBody> getcontacts_add_annonce(@Field("image") String image);
    @FormUrlEncoded
    @POST("montag/WordPad/Emara_delete_annonce.php")
    Call<ResponseBody> delete_annonce(@Field("id") int id);
    @FormUrlEncoded
    @POST("montag/WordPad/Emara_make_recervation.php")
    Call<ResponseBody> getcontactsmakerecervation(@Field("name") String name, @Field("phone") String phone,
                                      @Field("id") String id,
                                      @Field("Duration") int Duration,
                                      @Field("datefrom") String datefrom,
                                      @Field("dateto") String dateto,
                                                  @Field("month") int month,
                                                  @Field("price") int price,
                                      @Field("emara_num") int emara_num,
                                                  @Field("dataentry_id") int dataentry_id
                                                  );
    @FormUrlEncoded
    @POST("montag/WordPad/Emara_all_recervation.php")
    Call<List<Reservation_content>> get_all_recervations(@Field("id") int id);
    @FormUrlEncoded
    @POST("montag/WordPad/Emara_all_recervation_today.php")
    Call<List<Reservation_content>> get_all_recervations_today(@Field("Emara_num") int Emara_num,@Field("Date") String Date);

    @FormUrlEncoded
    @POST("montag/WordPad/Emara_all_recervation_month.php")
    Call<List<Reservation_content>> get_all_recervations_month(@Field("Emara_num") int Emara_num,@Field("month") int month,@Field("Date")String Date);
    @FormUrlEncoded
    @POST("montag/WordPad/Emara_new_expenses.php")
    Call<ResponseBody> getcontactsnewexpenses(@Field("name") String name, @Field("price") int price, @Field("month") int month,
                                              @Field("emara_num") int emara_num,@Field("date")String date );

    @FormUrlEncoded
    @POST("montag/WordPad/Emara_all_Exp_month.php")
    Call<List<Exp_content>> get_all_exp_month(@Field("Emara_num") int Emara_num,@Field("month") int month,@Field("Date")String Date);
    @FormUrlEncoded
    @POST("montag/WordPad/Emara_update_datelogin.php")
    Call<ResponseBody> update_manager(@Field("id") int id,@Field("Date")String Date);
}

