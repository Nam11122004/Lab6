package com.example.lab5_6.Model;
import com.google.gson.annotations.SerializedName;
public class Students {
    @SerializedName("_id")
    private String id;
    private String masv,hoten,diem, createAt, updateAt;

    public Students() {
    }

    public Students(String id, String masv, String hoten, String diem, String createAt, String updateAt) {
        this.id = id;
        this.masv = masv;
        this.hoten = hoten;
        this.diem = diem;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getDiem() {
        return diem;
    }

    public void setDiem(String diem) {
        this.diem = diem;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
}
