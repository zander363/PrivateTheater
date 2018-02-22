package com.mrb.test.MOVIE;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Mr.B on 2018/1/15.
 */

public class OrderItem implements Serializable {
    private Integer ID;
    private String moviename;
    private String orderTime;

    public OrderItem(){
        this.ID=-1;
        this.moviename="";
        this.orderTime="";
    }
    public OrderItem(Integer ID, String moviename,String orderTime){
        this.ID=ID;
        this.moviename=moviename;
        this.orderTime=orderTime;
    }
    public Integer getID() {
        return ID;
    }
    public void setID(Integer ID){
        this.ID=ID;
    }
    public void setMoviename(String moviename){
        this.moviename=moviename;
    }
    public void setOrderTime(String orderTime){
        this.orderTime=orderTime;
    }
    public String getMoviename(){
        return moviename;
    }
    public String getOrderTime(){
        return orderTime;
    }


}
