package com.example.mainframe_gt.seccion_01;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class TercerActivity extends AppCompatActivity {

    EditText editTextPhone, editTextWeb;
    ImageButton Web, Phone, Camera;
    private final int PHONE_CALL_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercer);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextWeb = (EditText) findViewById(R.id.editTextWeb);
        Web = (ImageButton) findViewById(R.id.imageButtonWeb);
        Phone = (ImageButton) findViewById(R.id.imageButtonPhone);
        Camera = (ImageButton) findViewById(R.id.imageButtonCameta);

        Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = editTextPhone.getText().toString();
                if (number != null) {
                    //Comprobar la version actual de nuestro Android que estamos ejecutando
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                    } else {
                        OldVersion(number);
                    }

                }
            }


            @SuppressLint("MissingPermission")
            private void OldVersion(String phoneNumer) {
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + phoneNumer));
                if (CheckPermission(Manifest.permission.CALL_PHONE)) {
                    startActivity(intentCall);
                } else {
                    Toast.makeText(TercerActivity.this, "Acceso Denegado", Toast.LENGTH_SHORT).show();
                }
            }


        });

    }

    //obriescribimos uste metedo ya que tenemos una respues asincrona
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //estamos en el caso del Telefono
        switch (requestCode) {
            case PHONE_CALL_CODE:
                String permission = permissions[0];
                int result = grantResults[0];

                if (permission.equals(Manifest.permission.CALL_PHONE)) {
                    //Comprobar si acepto el permiso o no
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        //se concede el permiso
                        String phoneNumer = editTextPhone.getText().toString();
                        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + phoneNumer));
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        startActivity(intentCall);
                  }else
                      //se cancelo
                      {
                          Toast.makeText(TercerActivity.this, "Acceso Denegado", Toast.LENGTH_SHORT).show();
                      }
              }

            break;

            default:
              super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            break;

        }

    }

    //aqui comprueba un permiso
    private boolean CheckPermission(String permission){
        int  result =this.checkCallingOrSelfPermission(permission);
        return  result == PackageManager.PERMISSION_GRANTED;
    }


}
