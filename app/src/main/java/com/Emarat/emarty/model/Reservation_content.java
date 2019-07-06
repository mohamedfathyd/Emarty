package com.Emarat.emarty.model;

import com.google.gson.annotations.SerializedName;

public class Reservation_content {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("phone")
    String phone;
    @SerializedName("national_id")
    String nationalid;
    @SerializedName("month")
    String months;
    @SerializedName("Date_from")
    String Date_from;
    @SerializedName("Date_to")
    String Date_to;
    @SerializedName("price")
    int price;
    @SerializedName("Duration")
    String duration;
    @SerializedName("emara_num")
    int emara_num;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNationalid() {
        return nationalid;
    }

    public void setNationalid(String nationalid) {
        this.nationalid = nationalid;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public String getDate_from() {
        return Date_from;
    }

    public void setDate_from(String date_from) {
        Date_from = date_from;
    }

    public String getDate_to() {
        return Date_to;
    }

    public void setDate_to(String date_to) {
        Date_to = date_to;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getEmara_num() {
        return emara_num;
    }

    public void setEmara_num(int emara_num) {
        this.emara_num = emara_num;
    }
}
