package com.example.mainframe_gt.seccion_01;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Segundo extends AppCompatActivity {

    TextView textView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);
        textView = (TextView)findViewById(R.id.txt2);

        button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
              Intent intent = new Intent(Segundo.this,TercerActivity.class);
              startActivity(intent);
            }
        });

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
