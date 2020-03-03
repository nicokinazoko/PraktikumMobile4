package com.example.praktikummobile4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnCall;
    private EditText editText;
    private String numberTelp;
    private static final int PHONE_REQUEST_CODE =   986;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCall     =   findViewById(R.id.btn_call);
        editText    =   findViewById(R.id.et_main);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calling();
            }
        });
    }

    private void calling()
    {
        numberTelp              =   editText.getText().toString();

        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
        {
            Intent intent       =   new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + numberTelp));
            startActivity(intent);
        }
        else
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},PHONE_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PHONE_REQUEST_CODE) {

            if (grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                calling();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Aplikasi Tidak Diijinkan", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
