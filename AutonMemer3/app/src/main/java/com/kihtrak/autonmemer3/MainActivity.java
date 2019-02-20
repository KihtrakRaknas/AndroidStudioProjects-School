package com.kihtrak.auto_memer;

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

public class MainActivity extends AppCompatActivity {
    BroadcastReceiver receiver;

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
                    final String sendMessage = "test";
                    Handler hand = new Handler();

                    hand.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("TESTS","test");
                            man.sendTextMessage(address, null,sendMessage,null,null);
                        }
                    },1000);

                }
            }
        };

        String permission = Manifest.permission.RECEIVE_SMS;
        int grant = ContextCompat.checkSelfPermission(this, permission);
        if ( grant != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[1];
            permission_list[0] = permission;
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

