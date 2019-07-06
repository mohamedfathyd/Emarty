package com.Emarat.emarty.model;

import com.google.gson.annotations.SerializedName;

public class Exp_content {
    @SerializedName("id")
    int id;
    @SerializedName("detials")
    String name;
    @SerializedName("amount")
    int price;


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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
