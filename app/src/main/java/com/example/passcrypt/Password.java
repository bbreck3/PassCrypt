package com.example.passcrypt;

/**
 * Created by breck on 11/20/2017.
 */

public class Password {
    String company,password;
    int id;
    public Password(){

    }
    public Password( String company,String password){
        //this.id = id;
        this.company = company;
        this.password = password;
    }
    /*public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }*/
    public void setCompany(String company){
        this.company = company;
    }
    public String getCompany(){
        return company;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    public String toCompanyString(){
        return getCompany();
    }
    public String toPasswordString(){
        return  getPassword();
    }

}