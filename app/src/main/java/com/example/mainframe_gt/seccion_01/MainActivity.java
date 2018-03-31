package com.example.mainframe_gt.seccion_01;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button button;
    private final String GREETER="HOLA DESDE OTRA VENTANA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button= (Button) findViewById(R.id.button);


        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       //esto es un Intent explicito
        Context context = this;
        Intent intent = new Intent(context,Segundo.class);
        intent.putExtra("greeter",GREETER);
        startActivity(intent);

    }
}
