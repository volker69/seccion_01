package com.example.mainframe_gt.seccion_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Segundo extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);
        textView = (TextView)findViewById(R.id.txt2);


        //Tomar los datos del intent
        Bundle bundle =getIntent().getExtras();
        if (bundle != null && bundle.getString("greeter")!=null)
        {
            String greeter =  bundle.getString("greeter");
            textView.setText(greeter);
            Toast.makeText(this, "Logrado ", Toast.LENGTH_SHORT).show();
        }else
         {
             Toast.makeText(this, "No se pudo :c", Toast.LENGTH_SHORT).show();
         }
    }
}
