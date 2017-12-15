package com.example.passcrypt;

/**
 * Created by breck on 11/20/2017.
 */

public class Password {
    String company,password,key;
    int id;
    public Password(){

    }
    public Password( String company,String password){
        //this.id = id;
        this.company = company;
        this.password = password;
        //this.key = key;
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
    public void setKey(String key){
        this.key =key;
    }
    public String getKey(){
        return key;
    }
    public String toCompanyString(){
        return getCompany();
    }
    public String toPasswordString(){
        return  getPassword();
    }

}