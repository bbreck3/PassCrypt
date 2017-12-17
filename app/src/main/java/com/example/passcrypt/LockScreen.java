package com.example.passcrypt;

import android.content.Context;
import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LockScreen extends AppCompatActivity {

    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnDel,btnGo;
    TextView ed_pin, setPin;
    String pin[] = {"-","-","-","-"};

    String pinStr="";
    String pinStrAstr="*";
    String pinStrFinal[] = new String[4];
    String finalPinStr;
    boolean last = false;
    int counter =0;
    final int MAX_LEN =4;
    Context context = this;
    String userPin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);
        setPin = (TextView)findViewById(R.id.setPin);
        final DBPinHelper dbPin = new DBPinHelper(this);



        if(exists("pins_db.db",dbPin)) {

            userPin= dbPin.getPin();//getAllRecords(db)
            if(userPin.equals("")){
                setPin.setText(" Set Pin");
            } else {
                setPin.setText("");
            }
        } else{
            dbPin.createDatabase();
            setPin.setText("Set Pin");
            //userPin = dbPin.getPin();
        }

       /* Button button = (Button) findViewById(R.id.btn_show_pass);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LockScreen.this, PassList.class);
                startActivity(intent);
            }
        });*/


        btn0 = (Button)findViewById(R.id.btn0);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button)findViewById(R.id.btn5);
        btn6 = (Button)findViewById(R.id.btn6);
        btn7 = (Button)findViewById(R.id.btn7);
        btn8 = (Button)findViewById(R.id.btn8);
        btn9 = (Button)findViewById(R.id.btn9);
        btnGo = (Button)findViewById(R.id.btn_show_pass);
        btnDel = (Button)findViewById(R.id.btnDel);
        ed_pin = (TextView)findViewById(R.id.tvPin);


        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("Counter: ", Integer.toString(counter));
                if(counter==3){
                    last=true;
                }
                if (counter <3) {
                    pin[counter]=pinStrAstr;
                    pinStrFinal[counter]="0";
                    counter++;
                    String temp ="";
                    for(int i=0; i<pin.length;i++){
                        temp+=pin[i];
                    }
                    ed_pin.setText(temp);
                } else if(counter==3 && last){
                    pin[counter]=pinStrAstr;
                    pinStrFinal[counter]="0";
                    String temp ="";
                    for(int i=0; i<pin.length;i++){
                        temp+=pin[i];
                    }
                    ed_pin.setText(temp);
                    String finalPin ="";
                    for(int i=0;i<pinStrFinal.length;i++){
                        finalPin+=pinStrFinal[i];
                    }
                    setFinalPin(finalPin);
                    Toast.makeText(getApplicationContext(),"Pin: " + finalPin, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Pin limit exceeded", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Counter: ", Integer.toString(counter));
                if(counter==3){
                    last=true;
                }
                if (counter <3) {
                    pin[counter]=pinStrAstr;
                    pinStrFinal[counter]="1";
                    counter++;
                    String temp ="";
                    for(int i=0; i<pin.length;i++){
                        temp+=pin[i];
                    }
                    ed_pin.setText(temp);
                } else if(counter==3 && last){
                    last=false;
                    pin[counter]=pinStrAstr;
                    pinStrFinal[counter]="1";
                    String temp ="";
                    for(int i=0; i<pin.length;i++){
                        temp+=pin[i];
                    }
                    ed_pin.setText(temp);
                    String finalPin ="";
                    for(int i=0;i<pinStrFinal.length;i++){
                        finalPin+=pinStrFinal[i];
                    }
                    setFinalPin(finalPin);
                    Toast.makeText(getApplicationContext(),"Pin: " + finalPin, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Pin limit exceeded", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Counter: ", Integer.toString(counter));
                if(counter==3){
                    last=true;
                }
                if (counter <3) {
                    pin[counter]=pinStrAstr;
                    pinStrFinal[counter]="2";
                    counter++;
                    String temp ="";
                    for(int i=0; i<pin.length;i++){
                        temp+=pin[i];
                    }
                    ed_pin.setText(temp);
                } else if(counter==3 && last){
                    pin[counter]=pinStrAstr;
                    pinStrFinal[counter]="2";
                    String temp ="";
                    for(int i=0; i<pin.length;i++){
                        temp+=pin[i];
                    }
                    ed_pin.setText(temp);
                    String finalPin ="";
                    for(int i=0;i<pinStrFinal.length;i++){
                        finalPin+=pinStrFinal[i];
                    }
                    setFinalPin(finalPin);
                    Toast.makeText(getApplicationContext(),"Pin: " + finalPin, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Pin limit exceeded", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Counter: ", Integer.toString(counter));
                if(counter==3){
                    last=true;
                }
                if (counter <3) {
                    pin[counter]=pinStrAstr;
                    pinStrFinal[counter]="3";
                    counter++;
                    String temp ="";
                    for(int i=0; i<pin.length;i++){
                        temp+=pin[i];
                    }
                    ed_pin.setText(temp);
                } else if(counter==3 && last){
                    pin[counter]=pinStrAstr;
                    pinStrFinal[counter]="3";
                    String temp ="";
                    for(int i=0; i<pin.length;i++){
                        temp+=pin[i];
                    }
                    ed_pin.setText(temp);
                    String finalPin ="";
                    for(int i=0;i<pinStrFinal.length;i++){
                        finalPin+=pinStrFinal[i];
                    }
                    setFinalPin(finalPin);
                    Toast.makeText(getApplicationContext(),"Pin: " + finalPin, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Pin limit exceeded", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Counter: ", Integer.toString(counter));
                if(counter==3){
                    last=true;
                }
                if (counter <3) {
                    pin[counter]=pinStrAstr;
                    pinStrFinal[counter]="4";
                    counter++;
                    String temp ="";
                    for(int i=0; i<pin.length;i++){
                        temp+=pin[i];
                    }
                    ed_pin.setText(temp);
                } else if(counter==3 && last){
                    pin[counter]=pinStrAstr;
                    pinStrFinal[counter]="4";
                    String temp ="";
                    for(int i=0; i<pin.length;i++){
                        temp+=pin[i];
                    }
                    ed_pin.setText(temp);
                    String finalPin ="";
                    for(int i=0;i<pinStrFinal.length;i++){
                        finalPin+=pinStrFinal[i];
                    }
                    setFinalPin(finalPin);
                    Toast.makeText(getApplicationContext(),"Pin: " + finalPin, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Pin limit exceeded", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Counter: ", Integer.toString(counter));
                if(counter==3){
                    last=true;
                }
                if (counter <3) {
                    pin[counter]=pinStrAstr;
                    pinStrFinal[counter]="5";
                    counter++;
                    String temp ="";
                    for(int i=0; i<pin.length;i++){
                        temp+=pin[i];
                    }
                    ed_pin.setText(temp);
                } else if(counter==3 && last){
                    pin[counter]=pinStrAstr;
                    pinStrFinal[counter]="5";
                    String temp ="";
                    for(int i=0; i<pin.length;i++){
                        temp+=pin[i];
                    }
                    ed_pin.setText(temp);
                    String finalPin ="";
                    for(int i=0;i<pinStrFinal.length;i++){
                        finalPin+=pinStrFinal[i];
                    }
                    setFinalPin(finalPin);
                    Toast.makeText(getApplicationContext(),"Pin: " + finalPin, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Pin limit exceeded", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Counter: ", Integer.toString(counter));
                if(counter==3){
                    last=true;
                }
                if (counter <3) {
                    pin[counter]=pinStrAstr;
                    pinStrFinal[counter]="6";
                    counter++;
                    String temp ="";
                    for(int i=0; i<pin.length;i++){
                        temp+=pin[i];
                    }
                    ed_pin.setText(temp);
                } else if(counter==3 && last){
                    pin[counter]=pinStrAstr;
                    pinStrFinal[counter]="6";
                    String temp ="";
                    for(int i=0; i<pin.length;i++){
                        temp+=pin[i];
                    }
                    ed_pin.setText(temp);
                    String finalPin ="";
                    for(int i=0;i<pinStrFinal.length;i++){
                        finalPin+=pinStrFinal[i];
                    }
                    setFinalPin(finalPin);
                    Toast.makeText(getApplicationContext(),"Pin: " + finalPin, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Pin limit exceeded", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Counter: ", Integer.toString(counter));
                if(counter==3){
                    last=true;
                }
                if (counter <3) {
                    pin[counter]=pinStrAstr;
                    pinStrFinal[counter]="7";
                    counter++;
                    String temp ="";
                    for(int i=0; i<pin.length;i++){
                        temp+=pin[i];
                    }
                    ed_pin.setText(temp);
                } else if(counter==3 && last){
                    pin[counter]=pinStrAstr;
                    pinStrFinal[counter]="7";
                    String temp ="";
                    for(int i=0; i<pin.length;i++){
                        temp+=pin[i];
                    }
                    ed_pin.setText(temp);
                    String finalPin ="";
                    for(int i=0;i<pinStrFinal.length;i++){
                        finalPin+=pinStrFinal[i];
                    }
                    setFinalPin(finalPin);
                    Toast.makeText(getApplicationContext(),"Pin: " + finalPin, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Pin limit exceeded", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Counter: ", Integer.toString(counter));
                if(counter==3){
                    last=true;
                }
                if (counter <3) {
                    pin[counter]=pinStrAstr;
                    pinStrFinal[counter]="8";
                    counter++;
                    String temp ="";
                    for(int i=0; i<pin.length;i++){
                        temp+=pin[i];
                    }
                    ed_pin.setText(temp);
                } else if(counter==3 && last){
                    pin[counter]=pinStrAstr;
                    pinStrFinal[counter]="8";
                    String temp ="";
                    for(int i=0; i<pin.length;i++){
                        temp+=pin[i];
                    }
                    ed_pin.setText(temp);
                    String finalPin ="";
                    for(int i=0;i<pinStrFinal.length;i++){
                        finalPin+=pinStrFinal[i];
                    }
                    setFinalPin(finalPin);
                    Toast.makeText(getApplicationContext(),"Pin: " + finalPin, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Pin limit exceeded", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Counter: ", Integer.toString(counter));
                if(counter==3){
                    Log.d("Debug: Counter is 3: ", Integer.toString(counter));
                    last=true;
                }
                if (counter <3) {
                    pin[counter]=pinStrAstr;
                    pinStrFinal[counter]="9";
                    counter++;
                    String temp ="";
                    for(int i=0; i<pin.length;i++){
                        temp+=pin[i];
                    }
                    ed_pin.setText(temp);
                } else if(counter==3 && last){
                    pin[counter]=pinStrAstr;
                    pinStrFinal[counter]="9";
                    String temp ="";
                    for(int i=0; i<pin.length;i++){
                        temp+=pin[i];
                    }
                    ed_pin.setText(temp);
                    String finalPin ="";
                    for(int i=0;i<pinStrFinal.length;i++){
                        finalPin+=pinStrFinal[i];
                    }
                    setFinalPin(finalPin);
                    Toast.makeText(getApplicationContext(),"Pin: " + finalPin, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Pin limit exceeded", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Counter: ", Integer.toString(counter));
                if (counter== 0) {
                    Log.d("Counter: ", Integer.toString(counter));
                    pin[counter] ="-";
                    pinStrFinal[counter]=null;
                    String temp="";
                    for (int i = 0; i < pin.length; i++) {
                        // Log.d("Debgu: PIN: ", pin[i]);
                        temp+=pin[i];
                    }
                    // String delPrevChar = pinStr.substring(0,pinStr.indexOf(pin[counter]));
                    ed_pin.setText(temp);
                    //pinStr);
                    pinStr = "";
                }else if (counter <=3 && counter>0) {
                    Log.d("Counter: ", Integer.toString(counter));
                    if(counter==3){
                        last=false;
                    }
                    pin[counter] ="-";
                    pinStrFinal[counter]=null;
                    counter--;
                    String temp="";
                    for (int i = 0; i < pin.length; i++) {
                        // Log.d("Debgu: PIN: ", pin[i]);
                        temp+=pin[i];
                    }
                    // String delPrevChar = pinStr.substring(0,pinStr.indexOf(pin[counter]));
                    ed_pin.setText(temp);
                    //pinStr);
                    pinStr = "";
                }else {
                    Toast.makeText(getApplicationContext(), "Pin limit exceeded", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //implement if else logic later
                //Toast.makeText(getApplicationContext(),pin,Toast.LENGTH_LONG).show();
                String setPin1 = getFinalPin();
                String pinTmp = dbPin.getPin();
                if(pinTmp.equals("")){
                    Intent intent = new Intent(getApplicationContext(), SetPin.class);
                    intent.putExtra("pin",setPin1);
                    startActivity(intent);
                }else if(!setPin1.equals(pinTmp)){
                    Toast.makeText(getApplicationContext(),"Incorrect Pin",Toast.LENGTH_LONG).show();
                } else{
                    Intent intent = new Intent(getApplicationContext(), PassList.class);
                    startActivity(intent);
                }
            }
        });
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        /** FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
         fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null).show();
        }
        });*/
    }

    public void refreshPin(){
        ed_pin.setText("----");
    }
    public void setFinalPin(String pin){
        this.finalPinStr=pin;
    }
    public String getFinalPin(){
        return finalPinStr;
    }
    public boolean exists(String table, DBPinHelper db){
        String dbName = db.getDatabaseName();

        if(dbName.equals(table)){
           /* Log.d("DEBUG",dbName);
            Log.d("DEBUG","DEBUG");*/

            return true;
        } else{
            return false;
        }
        /*try{
            db.("Select * FROM " +table,null);
            return true;
        }catch (SQLException e){
            return false;
        }*/
    }
}
   /* @Override
    protected void onRestart(){
        super.onRestart();
        setContentView(R.layout.activity_lock_screen);

    }*/
