package com.example.emoney;
/*
    Danny Jay M. Flores (BSIT-3D)
    E-Money Activity - Users
 */
public class User {

    private String mUsername;
    private String mPassword;
    private String mName;
    private String mContact;
    private String mBalance;
    private String mBill;

    public User(String mUsername, String mPassword, String mName, String mContact, String mBalance, String mBill) {
        this.mUsername = mUsername;
        this.mPassword = mPassword;
        this.mName = mName;
        this.mContact = mContact;
        this.mBalance = mBalance;
        this.mBill = mBill;
    }

    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmContact() {
        return mContact;
    }

    public void setmContact(String mContact) {
        this.mContact = mContact;
    }

    public String getmBalance() {
        return mBalance;
    }

    public void setmBalance(String mBalance) {
        this.mBalance = mBalance;
    }

    public String getmBill() {
        return mBill;
    }

    public void setmBill(String mBill) {
        this.mBill = mBill;
    }
}
