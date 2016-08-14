package com.abhiruchimaurya.heritage;

import java.io.Serializable;

/**
 * Created by Abhiruchi Maurya on 22-07-2016.
 */
public class Monument implements Serializable {

    public Monument()
    {

    }

    public Monument(String description, String monument_name, String creator_name, String city_name, byte[] monu_img) {
        this.description = description;
        this.monument_name = monument_name;
        this.creator_name = creator_name;
        this.city_name = city_name;
        this.monu_img = monu_img;
    }

    String description,monument_name,creator_name;
    String city_name;
    byte[] monu_img;

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMonument_name() {
        return monument_name;
    }

    public void setMonument_name(String monument_name) {
        this.monument_name = monument_name;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }

    public byte[] getMonu_img() {
        return monu_img;
    }

    public void setMonu_img(byte[] monu_img) {
        this.monu_img = monu_img;
    }
    public String toString()
    {
        return monument_name;
    }
}
