package com.example.user_img;

import android.graphics.Bitmap;

public class NhanVien {
    private Bitmap img;
    private String maso;
    private String hoten;
    private String gioitinh;
    private String donvi;

    public NhanVien(Bitmap img, String maso, String hoten, String gioitinh, String donvi) {
        this.img = img;
        this.maso = maso;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.donvi = donvi;
    }



    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getMaso() {
        return maso;
    }

    public void setMaso(String maso) {
        this.maso = maso;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getDonvi() {
        return donvi;
    }

    public void setDonvi(String donvi) {
        this.donvi = donvi;
    }
}
