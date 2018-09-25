package com.example.padcc.listtest;

/**
 * Created by padcc on 20/09/2018.
 */

public class jsondata {
    String username,userid,useremail,useraddress,usergender,userphonemobile,userphonehome,userphoneoffice;

    public jsondata(String username, String userid, String useremail, String useraddress, String usergender, String userphonemobile, String userphonehome, String userphoneoffice) {
        this.username = username;
        this.userid = userid;
        this.useremail = useremail;
        this.useraddress = useraddress;
        this.usergender = usergender;
        this.userphonemobile = userphonemobile;
        this.userphonehome = userphonehome;
        this.userphoneoffice = userphoneoffice;
    }

    public jsondata() {

    }

    public String getUsername() {
        return username;
    }

    public String getUserid() {
        return userid;
    }

    public String getUseremail() {
        return useremail;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public String getUsergender() {
        return usergender;
    }

    public String getUserphonemobile() {
        return userphonemobile;
    }

    public String getUserphonehome() {
        return userphonehome;
    }

    public String getUserphoneoffice() {
        return userphoneoffice;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    public void setUsergender(String usergender) {
        this.usergender = usergender;
    }

    public void setUserphonemobile(String userphonemobile) {
        this.userphonemobile = userphonemobile;
    }

    public void setUserphonehome(String userphonehome) {
        this.userphonehome = userphonehome;
    }

    public void setUserphoneoffice(String userphoneoffice) {
        this.userphoneoffice = userphoneoffice;
    }
}
