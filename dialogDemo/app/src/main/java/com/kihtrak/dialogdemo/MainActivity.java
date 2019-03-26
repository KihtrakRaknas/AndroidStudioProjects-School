package com.kihtrak.dialogdemo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ebutt = findViewById(R.id.button);
        ebutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "DONE",Toast.LENGTH_LONG).show();
                    }
                });
                alert.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "canceled",Toast.LENGTH_LONG).show();
                    }
                });
                alert.setMessage("MESS");
                alert.setTitle("HI");
                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        });

        Button fbutt = findViewById(R.id.button4);
        fbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getApplicationContext());
                dialog.setContentView(R.layout.dialay);
                dialog.setTitle("Custom Title");
                Button button = dialog.findViewById(R.id.button2);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Custom Clicked", Toast.LENGTH_SHORT).show();
                        dialog.hide();
                    }
                });
                dialog.show();
            }
        });
    }
}
