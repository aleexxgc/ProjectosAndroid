package com.example.tema3layouts;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView pantallaPrincipal;
    private TextView pantallaHistorial;
    private final StringBuilder expresion = new StringBuilder();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);

        pantallaPrincipal = findViewById(R.id.texto);
        pantallaHistorial = findViewById(R.id.tvHistorial);

        GridLayout contenedorBotones = findViewById(R.id.grid_layout);
        if (contenedorBotones != null) {
            for (int i = 0; i < contenedorBotones.getChildCount(); i++) {
                View componente = contenedorBotones.getChildAt(i);
                if (componente instanceof Button) {
                    componente.setOnClickListener(this);
                }
            }
        }
    }


    public void onClick(View vista) {
        Button boton = (Button) vista;
        String entrada = boton.getText().toString();

        switch (entrada) {
            case "AC":
                expresion.setLength(0);
                pantallaPrincipal.setText("0");
                pantallaHistorial.setText("");
                break;

            case "C":
                if (expresion.length() > 0) {
                    expresion.deleteCharAt(expresion.length() - 1);
                    pantallaPrincipal.setText(expresion.length() == 0 ? "0" : expresion.toString());
                }
                break;

            case "=":
                if (expresion.length() > 0) {
                    pantallaHistorial.setText(expresion.toString() + " =");
                    evaluarYMostrarResultado();
                }
                break;

            default:
                if (pantallaPrincipal.getText().toString().equals("0") && !esOperador(entrada)) {
                    expresion.setLength(0);
                }
                expresion.append(entrada);
                pantallaPrincipal.setText(expresion.toString());
                break;
        }
    }

    private boolean esOperador(String c) {
        return c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/");
    }

    private void evaluarYMostrarResultado() {
        try {
            String exp = expresion.toString();
            for (char op : new char[]{'+', '-', '*', '/'}) {
                int pos = exp.indexOf(op);
                if (pos > 0) {
                    double num1 = Double.parseDouble(exp.substring(0, pos));
                    double num2 = Double.parseDouble(exp.substring(pos + 1));
                    double res = 0;

                    switch (op) {
                        case '+': res = num1 + num2; break;
                        case '-': res = num1 - num2; break;
                        case '*': res = num1 * num2; break;
                        case '/': res = num2 != 0 ? num1 / num2 : 0; break;
                    }

                    String resultadoFinal = (res % 1 == 0) ? String.valueOf((long) res) : String.valueOf(res);

                    pantallaPrincipal.setText(resultadoFinal);

                    expresion.setLength(0);
                    expresion.append(resultadoFinal);
                    return;
                }
            }
        } catch (Exception e) {
            pantallaPrincipal.setText("Error");
            pantallaHistorial.setText("");
            expresion.setLength(0);
        }
    }
}