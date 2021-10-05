package com.example.activitylifecycledemo;

public class Contact {
    private int _ID;
    private String _Name;
    private String _PhoneNo;
    public Contact()
    {

    }
    public Contact(int ID)
    {
        this._ID = ID;
    }
    public Contact(String Name, String PhoneNo)
    {
        this._Name = Name;
        this._PhoneNo = PhoneNo;
    }

    public Contact(int ID, String Name, String PhoneNo)
    {
        this._ID = ID;
        this._Name = Name;
        this._PhoneNo = PhoneNo;
    }
    public int get_ID() {
        return _ID;
    }

    public String get_Name() {
        return _Name;
    }

    public String get_PhoneNo() {
        return _PhoneNo;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }

    public void set_Name(String _Name) {
        this._Name = _Name;
    }

    public void set_PhoneNo(String _PhoneNo) {
        this._PhoneNo = _PhoneNo;
    }
}
