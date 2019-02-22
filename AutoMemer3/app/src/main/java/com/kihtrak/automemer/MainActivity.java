package com.kihtrak.automemer;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    BroadcastReceiver receiver;
    String state = "Awaiting greeting";
    @Override
    protected void onResume() {
        super.onResume();
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("TESTS","res");
                Object[] objs = (Object[])intent.getExtras().get("pdus");
                SmsMessage smsArr[] = new SmsMessage[objs.length];
                for(int i =0; i!=objs.length;i++){
                    smsArr[i] = SmsMessage.createFromPdu((byte[])objs[i],intent.getStringExtra("format"));
                    final String address = smsArr[i].getOriginatingAddress();
                    String message = smsArr[i].getMessageBody();
                    Log.d("TESTS","message: "+message);

                    final SmsManager man = SmsManager.getDefault();

                    if(state == "Awaiting greeting") {
                        if((message.toLowerCase().contains("hi")||message.toLowerCase().contains("hello"))) {
                            Handler hand = new Handler();
                            final String sendMessage = "Hello! Wanna see something kewl?";
                            hand.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d("TESTS", "test");
                                    man.sendTextMessage(address, null, sendMessage, null, null);
                                    state = "Awaiting Response to \"wanna se something kewl\"";
                                }
                            }, 1000);
                        }else{
                            Handler hand = new Handler();
                            final String sendMessage = "Thats kinda random. Thats not how normal people start a conversation";
                            hand.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d("TESTS", "test");
                                    man.sendTextMessage(address, null, sendMessage, null, null);
                                }
                            }, 1000);
                        }
                    }else if(state == "Awaiting Response to \"wanna se something kewl\"") {
                        if((message.toLowerCase().contains("y")||message.toLowerCase().contains("sure"))) {
                            Handler hand = new Handler();
                            final String sendMessage = "Click this link: http://red.kihtrak.com/rr";
                            hand.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d("TESTS", "test");
                                    man.sendTextMessage(address, null, sendMessage, null, null);
                                    state = "Received Postitive Responce";
                                }
                            }, 1000);
                        }else if(message.toLowerCase().contains("n")){
                            Handler hand = new Handler();
                            final String sendMessage = "I think Ur making the wrong choice... R U sure?";
                            hand.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d("TESTS", "test");
                                    man.sendTextMessage(address, null, sendMessage, null, null);
                                }
                            }, 1000);
                        }else{
                            Handler hand = new Handler();
                            final String sendMessage = "It's a yes/no question... How are you bad at this?";
                            hand.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d("TESTS", "test");
                                    man.sendTextMessage(address, null, sendMessage, null, null);
                                }
                            }, 1000);
                        }
                    }else if(state == "Received Postitive Responce") {
                        if((message.toLowerCase().contains("hi")||message.toLowerCase().contains("hello"))) {
                            Handler hand = new Handler();
                            final String sendMessage = "So, did you enjoy it?";
                            hand.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d("TESTS", "test");
                                    man.sendTextMessage(address, null, sendMessage, null, null);
                                    state = "Awaiting Response to \"So, did you enjoy it?\"";
                                }
                            }, 1000);
                        }
                    }else if(state == "Awaiting Response to \"So, did you enjoy it?\"") {
                        if((message.toLowerCase().contains("y")||message.toLowerCase().contains("sure"))) {
                            String sendMessageApend = "Great! ";

                            Handler hand = new Handler();
                            final String sendMessage = sendMessageApend+"Wanna see something else cool?";
                            hand.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d("TESTS", "test");
                                    man.sendTextMessage(address, null, sendMessage, null, null);
                                    state = "Awaiting Response to \"wanna se something kewl\"";
                                }
                            }, 1000);
                        }else if(message.toLowerCase().contains("n")){
                            String sendMessageApend = "Aw, that sucks. ";

                            Handler hand = new Handler();
                            final String sendMessage = sendMessageApend+"Wanna see something else cool?";
                            hand.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d("TESTS", "test");
                                    man.sendTextMessage(address, null, sendMessage, null, null);
                                    state = "Awaiting Response to \"wanna se something kewl\"";
                                }
                            }, 1000);
                        }else{
                            Handler hand = new Handler();
                            final String sendMessage = "It's a yes/no question... How are you bad at this?";
                            hand.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d("TESTS", "test");
                                    man.sendTextMessage(address, null, sendMessage, null, null);
                                }
                            }, 1000);
                        }

                    }else {

                    }
                    ((TextView)findViewById(R.id.output)).setText(state);
                }
            }
        };

        String permission = Manifest.permission.RECEIVE_SMS;
        String permission2 = Manifest.permission.SEND_SMS;
        if ( ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, permission2) != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[2];
            permission_list[0] = permission;
            permission_list[1] = permission2;
            ActivityCompat.requestPermissions(this, permission_list, 1);


        }else{
            IntentFilter inF = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
            registerReceiver(receiver,inF);
            Log.d("TESTS","reg");
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //= new BroadcastReceiver();



    }

    public class res extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {


        }
    }

    public class hand extends Handler{

    }
}
