package com.example.mainframe_gt.seccion_01;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class TercerActivity extends AppCompatActivity {

    EditText editTextPhone,editTextWeb;
    ImageButton Web,Phone,Camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercer);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextWeb = (EditText) findViewById(R.id.editTextWeb);
        Web = (ImageButton) findViewById(R.id.imageButtonWeb);
        Phone = (ImageButton) findViewById(R.id.imageButtonPhone);
        Camera = (ImageButton) findViewById(R.id.imageButtonCameta);

        editTextPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = editTextPhone.getText().toString();
                if (number !=null)
                {
                    Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel: "+number));
                }
            }
        });

    }

    //aqui comprueba un permiso
    private boolean CheckPermission(String permission){
        int  result =this.checkCallingOrSelfPermission(permission);
        return  result == PackageManager.PERMISSION_GRANTED;
    }


}
