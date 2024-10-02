package com.firstapp.messagesms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextPhone,editTextMessage;
    Button btnSent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMessage = findViewById(R.id.editTextMessage);
        editTextPhone = findViewById(R.id.editTextPhone);
        btnSent = findViewById(R.id.btnSent);

        btnSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED){

                    sendSMS();

                }else {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS},
                            100);
                }

            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 & grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
            sendSMS();
        }else {
            Toast.makeText(this, "PERMISSION DENIED !!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendSMS() {
        String phone = editTextPhone.getText().toString();
        String message = editTextMessage.getText().toString();

        if (!phone.isEmpty() && !message.isEmpty()){
            SmsManager smsManager = SmsManager.getDefault();

            smsManager.sendTextMessage(phone, null,message,null,null);

            Toast.makeText(this, "SMS SENT SUCCESSFULLY", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please Enter message and phone Number Properly", Toast.LENGTH_SHORT).show();
        }
    }
}