package com.passcrypt.passcrypt;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SetPin extends AppCompatActivity {

    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnDel,btnGo;
    TextView ed_pin;
    String pin[] = {"-","-","-","-"};

    String pinStr="";
    String pinStrAstr="*";
    String pinStrFinal[] = new String[4];
    String finalPinStr;
    boolean last = false;
    int counter =0;
    final int MAX_LEN =4;
    Context context = this;
    String userPin1;
    AESHelper aesHelper = new AESHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pin);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            userPin1 = bundle.getString("pin");
        }
        final DBPinHelper dbPin = new DBPinHelper(this);



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
                            //Toast.makeText(getApplicationContext(),"Pin: " + finalPin, Toast.LENGTH_LONG).show();
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
                           // Toast.makeText(getApplicationContext(),"Pin: " + finalPin, Toast.LENGTH_LONG).show();
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
                            //Toast.makeText(getApplicationContext(),"Pin: " + finalPin, Toast.LENGTH_LONG).show();
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
                            //Toast.makeText(getApplicationContext(),"Pin: " + finalPin, Toast.LENGTH_LONG).show();
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
                           // Toast.makeText(getApplicationContext(),"Pin: " + finalPin, Toast.LENGTH_LONG).show();
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
                            //Toast.makeText(getApplicationContext(),"Pin: " + finalPin, Toast.LENGTH_LONG).show();
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
                           // Toast.makeText(getApplicationContext(),"Pin: " + finalPin, Toast.LENGTH_LONG).show();
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
                           // Toast.makeText(getApplicationContext(),"Pin: " + finalPin, Toast.LENGTH_LONG).show();
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
                           // Toast.makeText(getApplicationContext(),"Pin: " + finalPin, Toast.LENGTH_LONG).show();
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
                           // Toast.makeText(getApplicationContext(),"Pin: " + finalPin, Toast.LENGTH_LONG).show();
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
                String userPin2 = getFinalPin();
                    if(userPin2.equals(userPin1)){
                       //Toast.makeText(getApplicationContext(),"Pins Match!",Toast.LENGTH_LONG).show();
                        /*String seed="5476641322446326";*/
                        try {
                            //int pinEnc = userPin2.hashCode();//AESHelper.encryption(userPin2);//encryption(userPin2);
                            dbPin.insertPin(userPin2);
                            Intent intent = new Intent(getApplicationContext(), FingerprintActivity.class);
                            startActivity(intent);
                        }catch (Exception e){
                            Log.d("Error: ", e.toString());
                        }
                    } else{
                        //Case is depricated: the pin is now only used for the seed to encryption
                        Toast.makeText(getApplicationContext(),"Pins DO NOT Match!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),LockScreen.class);
                        startActivity(intent);
                    }
                /*Intent intent = new Intent(getApplicationContext(), PassList.class);
                startActivity(intent);*/
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
        }

   /* @Override
    protected void onRestart(){
        super.onRestart();
        setContentView(R.layout.activity_lock_screen);

    }*/

