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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BroadcastReceiver receiver;
    String state = "Awaiting greeting";
    final int delay = 3000;
    int failedRes = 0;

    ArrayList<String> memez = new ArrayList<String>();

    @Override
    protected void onResume() {
        super.onResume();
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("TESTS","res");
                Object[] objs = (Object[])intent.getExtras().get("pdus");
                SmsMessage smsArr[] = new SmsMessage[objs.length];
                final SmsManager man = SmsManager.getDefault();
                for(int i =0; i!=objs.length;i++){
                    smsArr[i] = SmsMessage.createFromPdu((byte[])objs[i],intent.getStringExtra("format"));
                    final String address = smsArr[i].getOriginatingAddress();
                    String message = smsArr[i].getMessageBody();
                    Log.d("TESTS","message: "+message);

                    if(state == "Awaiting greeting") {
                        if((message.toLowerCase().contains("hi")||message.toLowerCase().contains("hello"))) {
                            sendMessage("Hello! Wanna see something kewl?",address);
                            failedRes = 0;
                            state = "Awaiting Response to \"wanna se something kewl\"";
                        }else{
                            if(failedRes<1) {
                                sendMessage("That's kinda random...",address);
                                failedRes++;
                            }else if(failedRes<2) {
                                sendMessage("I was expecting some kind of greeting (hi/hello)...",address);
                                failedRes++;
                            }else{
                                sendMessage("I'm not sure what you are trying to say but... Would you like to see something cool?",address);
                                failedRes = 0;
                                state = "Awaiting Response to \"wanna se something kewl\"";
                            }
                        }
                    }else if(state == "Awaiting Response to \"wanna se something kewl\"") {
                        if((message.toLowerCase().contains("yes")||message.toLowerCase().contains("sure")||message.toLowerCase().contains("ok"))) {
                            String meme = memez.remove( (int)(Math.random()*memez.size()) );
                            sendMessage("Click this link: "+meme+" \nText me back once you do",address);
                            state = "Received Postitive Responce";
                            failedRes = 0;
                        }else if(message.toLowerCase().contains("no")&&failedRes<2){
                            sendMessage("I think Ur making the wrong choice... I'll give you one last chance to say yes.",address);
                            failedRes=2;
                        }else{
                            if(failedRes<2) {
                                sendMessage("It's a yes/no question... How are you bad at this?",address);
                                failedRes++;
                            }else{
                                sendMessage("Okay, fine, BYE!",address);
                                failedRes = 0;
                                state = "Awaiting greeting";
                            }
                        }
                    }else if(state == "Received Postitive Responce") {
                            sendMessage("So, did you enjoy it?",address);
                            state = "Awaiting Response to \"So, did you enjoy it?\"";
                    }else if(state == "Awaiting Response to \"So, did you enjoy it?\"") {
                        if(memez.size()>0) {
                            if ((message.toLowerCase().contains("yes") || message.toLowerCase().contains("sure"))) {
                                sendMessage("Great! " + "Wanna see something else cool?", address);
                                failedRes = 0;
                                state = "Awaiting Response to \"wanna se something kewl\"";
                            } else if (message.toLowerCase().contains("no")) {
                                sendMessage("Aw, that sucks. " + "Wanna see something else cool?", address);
                                state = "Awaiting Response to \"wanna se something kewl\"";
                                failedRes = 0;
                            } else {
                                if(failedRes<2) {
                                    sendMessage("I was expecting something like \"yes\" or \"no\". I'm not sure what \""+message+"\" means", address);
                                    failedRes++;
                                }else{
                                    sendMessage("I'm assuming you are too amazed to properly respond.",address);
                                    failedRes = 0;

                                }
                            }
                        }else{
                            sendMessage("Okay... Sorry, I'm all out of memes.\nFeel try to greet me later to view them again!", address);
                            state = "Awaiting greeting";
                            populateMemez();
                        }

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

    public void sendMessage(final String sendMessage,final String address){
        final SmsManager man = SmsManager.getDefault();
        Handler hand = new Handler();
        hand.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("TESTS", "test");
                man.sendTextMessage(address, null, sendMessage, null, null);
            }
        }, delay);
    }

    public void populateMemez(){
        while(memez.size()>0) {
            memez.remove(0);
        }

        memez.add("https://red.kihtrak.com/r");
        memez.add("https://red.kihtrak.com/meme1");
        memez.add("https://red.kihtrak.com/meme2");
        memez.add("https://red.kihtrak.com/meme3");
        memez.add("https://red.kihtrak.com/meme4");
        memez.add("https://red.kihtrak.com/meme5");
        memez.add("https://red.kihtrak.com/meme6");
        memez.add("https://red.kihtrak.com/meme7");
        memez.add("https://red.kihtrak.com/meme8");
        memez.add("https://red.kihtrak.com/meme9");
        memez.add("https://red.kihtrak.com/meme10");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //= new BroadcastReceiver();

        populateMemez();



    }

    public class res extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {


        }
    }

    public class hand extends Handler{

    }
}
