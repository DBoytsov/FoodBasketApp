package com.example.boytsov.foodbasketapp;

/**
 * Created by Boytsov on 09.08.2015.
 */
public class Product {
    String name_product;
    int id;
    Boolean isstrikeout=false;

    public Product(){

    }
    public Product(String name_product){
        this.name_product=name_product;
        this.isstrikeout=false;
    }
    public Product(int id,String name_product){
        this.name_product=name_product;
        this.id=id;
        this.isstrikeout=false;
    }
    public Product(int id,String name_product,Boolean isstrikeout){
        this.name_product=name_product;
        this.id=id;
        this.isstrikeout=isstrikeout;
    }

    public String getName_product() {
        return name_product;
    }
    public int getID_product() {
        return id;
    }

    public void setID_product(int id) {
        this.id=id;
    }
    public void setName_product(String name_product) {
        this.name_product = name_product;
    }
    public void setIsstrikeout(boolean isstrikeout){
        this.isstrikeout=isstrikeout;
    }
    public boolean IsStrikeout(){
        return isstrikeout;
    }
    public boolean IsStrikeoutByName(String name_product){

        return isstrikeout;
    }
}
