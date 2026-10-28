package com.example.tema4;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Button boton1 = findViewById(R.id.miToggleButton);
        TextView texto = findViewById(R.id.texto);
        boton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                texto.setText("Se ha pulsado el boton");
            }
        });
    }
    public void onClick(View view){
        TextView texto = findViewById(R.id.texto);

        int id = view.getId();
        if (id == R.id.button2) {
            texto.setText("He presionado el boton 2");
        } else if (id == R.id.button3) {
            texto.setText("He presionado el boton 3");
        }
    }
}