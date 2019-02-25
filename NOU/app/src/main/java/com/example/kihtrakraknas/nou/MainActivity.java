package com.example.kihtrakraknas.nou;

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
    final int delay = 0;

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

                        if((message.toLowerCase().contains("nuh uh"))) {
                            sendMessage("YUH UH", address);
                        }
                    //((TextView)findViewById(R.id.output)).setText(state);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //= new BroadcastReceiver();



    }


    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
}
