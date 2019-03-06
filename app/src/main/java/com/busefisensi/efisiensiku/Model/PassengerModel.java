package com.busefisensi.efisiensiku.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;

public class PassengerModel {
    private int id;
    private String nama;
    private String handhphone;

    public PassengerModel(int id, String nama, String handhphone){
        this.id = id;
        this.nama = nama;
        this.handhphone = handhphone;
    }

    public PassengerModel(){}

    public PassengerModel(String nama, String handhphone){
        this.nama = nama;
        this.handhphone = handhphone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHandhphone() {
        return handhphone;
    }

    public void setHandhphone(String handhphone) {
        this.handhphone = handhphone;
    }
}
