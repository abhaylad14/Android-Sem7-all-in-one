package com.example.activitylifecycledemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class TelephonyActivity extends AppCompatActivity {
    TextView txtPhoneDetails;
    TelephonyManager tm;
    PhoneStateListener listenr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephony);
        ControlInitialization();
    }

    private void ControlInitialization() {
        txtPhoneDetails = findViewById(R.id.txtPhoneDetails);
        tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},
                    1);
        }
        else
        {
            PhoneDetails();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            PhoneDetails();
        }
    }

    private void PhoneDetails() {
        String IMEINO = tm.getDeviceId();
        String SimSerialNo = tm.getSimSerialNumber();
        String NetworkCountryISO = tm.getNetworkCountryIso();
        String SimCountryISO = tm.getSimCountryIso();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        String SoftwareVersion = tm.getDeviceSoftwareVersion();
        String VoiceMail = tm.getVoiceMailNumber();
        boolean IsROming = tm.isNetworkRoaming();

        String strPhoneType = "";
        int PhoneType = tm.getPhoneType();
        switch (PhoneType)
        {
            case TelephonyManager.PHONE_TYPE_NONE:
                strPhoneType = "None";
                break;
            case TelephonyManager.PHONE_TYPE_CDMA:
                strPhoneType = "CDMA";
                break;
            case TelephonyManager.PHONE_TYPE_GSM:
                strPhoneType = "GSM";
                break;
        }
        String PhoneDetails = " Phone Details : \n" +
        "IMEI No : " + IMEINO + "\n" +
        "Sim Serial No : " + SimSerialNo + "\n" +
        "Network Country ISO : " + NetworkCountryISO + "\n" +
        "Sim Country ISO : " + SimCountryISO + "\n" +
        "Software Version : " + SoftwareVersion + "\n" +
        "Voice Mail : " + VoiceMail + "\n" +
        "Is Roming ? " + IsROming + "\n" +
        "Phone Type : " + strPhoneType + "\n";

        listenr = new PhoneStateListener()
        {
            @Override
            public void onCallStateChanged(int state, String phoneNumber) {
                String State = "";
               switch (state)
               {
                   case TelephonyManager.CALL_STATE_IDLE:
                       State = "Idle";
                       break;
                   case TelephonyManager.CALL_STATE_RINGING:
                       State = "Ringing";
                       break;
                   case TelephonyManager.CALL_STATE_OFFHOOK:
                       State = "Received";
                       break;
               }
               txtPhoneDetails.append(String.format("\n on call state changed : %s",State));
            }
        };
        tm.listen(listenr,PhoneStateListener.LISTEN_CALL_STATE);
        txtPhoneDetails.setText(PhoneDetails);
    }
}