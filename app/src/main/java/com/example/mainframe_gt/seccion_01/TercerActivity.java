package com.example.mainframe_gt.seccion_01;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class TercerActivity extends AppCompatActivity {

    EditText editTextPhone, editTextWeb;
    ImageButton Web, Phone, Camera;
    TextView textView ;
    private final int PHONE_CALL_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercer);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextWeb = (EditText) findViewById(R.id.editTextWebs);
        Web = (ImageButton) findViewById(R.id.imageButtonWeb);
        Phone = (ImageButton) findViewById(R.id.imageButtonPhone);
        Camera = (ImageButton) findViewById(R.id.imageButtonCameta);
        textView = (TextView) findViewById(R.id.textView);

        Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // se recoge el numero de telefono
                String number = editTextPhone.getText().toString();
                //pergunta si es nulo o esta vacio
                if (number != null && !number.isEmpty()) {
                    //Comprobar la version actual de nuestro Android que estamos ejecutando
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        //Comprobar si aceptado
                        if (CheckPermission(Manifest.permission.CALL_PHONE)) {
                            //acepto
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + number));
                            if (ActivityCompat.checkSelfPermission(TercerActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                return;}
                            startActivity(intent);
                        }else
                        {
                            //no acepto o es la primera vez que se pregunta
                            if(!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE))
                            {
                                // no se pregunto aun
                                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                            }else {
                                //a denegado
                                Toast.makeText(TercerActivity.this, "DEnegado su acceso", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                i.addCategory(Intent.CATEGORY_DEFAULT);
                                i.setData(Uri.parse("package:"+getPackageName()));//vemps el nombre de nuestra app
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                startActivity(i);

                            }
                        }
                        //
                    } else {
                        OldVersion(number);
                    }

                }else {
                    Toast.makeText(TercerActivity.this, "Vaalor nulo", Toast.LENGTH_SHORT).show();
                }
            }


            @SuppressLint("MissingPermission")
            private void OldVersion(String phoneNumer) {
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + phoneNumer));
                //comprovamo se el permiso esta en el manifest
                if (CheckPermission(Manifest.permission.CALL_PHONE)) {
                    startActivity(intentCall);
                } else {
                    Toast.makeText(TercerActivity.this, "Acceso Denegado", Toast.LENGTH_SHORT).show();
                }
            }


        });

        Web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = editTextWeb.getText().toString();
                textView.setText(url);

                if (url!=null&&!url.isEmpty())
                {
                    Intent Iweb = new Intent(Intent.ACTION_VIEW,Uri.parse("http://"+url));
                    //Iweb.setAction(Intent.ACTION_VIEW);
                    //Iweb.setData(Uri.parse("https://"+url));
                    startActivity(Iweb);
                }else {
                    Toast.makeText(TercerActivity.this, "Error en la URL", Toast.LENGTH_SHORT).show();
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
                //ve si el permiso es igual al manifest
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

    //aqui comprueba un permiso(es un metodo generico)
    private boolean CheckPermission(String permission){
        int  result =this.checkCallingOrSelfPermission(permission);
        return  result == PackageManager.PERMISSION_GRANTED;
    }


}
