package com.example.practicaandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText porcentajepractico,porcentajeteorico,notaminima,primer,segundo,tercero,pasarnota,practica;
    private Button iniciarCalculos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        porcentajepractico=findViewById(R.id.practico);
        porcentajeteorico=findViewById(R.id.teorico);
        notaminima=findViewById(R.id.minimo);
        primer=findViewById(R.id.nota1);
        segundo=findViewById(R.id.nota2);
        tercero=findViewById(R.id.mejoramiento);
        pasarnota=findViewById(R.id.promedio);
        iniciarCalculos=findViewById(R.id.calcular);
        practica=findViewById(R.id.practico);


        iniciarCalculos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Espacio para verificaciones
                String pt=porcentajeteorico.getText().toString();
                String pp=porcentajepractico.getText().toString();
                String nm=notaminima.getText().toString();
                String pa1=primer.getText().toString();
                String pa2=segundo.getText().toString();
                String np=practica.getText().toString();
                String pa3=tercero.getText().toString();
                if(TextUtils.isEmpty(pt)){
                    Toast.makeText(MainActivity.this,"No ha ingresado Porcentaje Teorico",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(pp)){
                    Toast.makeText(MainActivity.this,"No ha ingresado Porcentaje Practico",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(nm)){
                    Toast.makeText(MainActivity.this,"No ha ingresado nota minima",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(pa1)){
                    Toast.makeText(MainActivity.this,"No ha ingresado primera nota",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(pa2)){
                    Toast.makeText(MainActivity.this,"No ha ingresado segunda nota",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(np)){
                    Toast.makeText(MainActivity.this,"No ha ingresado nota practica",Toast.LENGTH_SHORT).show();
                }else{
                    //Operacones
                    double nota1=Double.parseDouble(porcentajepractico.getText().toString());
                    double nota2=Double.parseDouble(porcentajeteorico.getText().toString());
                    if(nota1+nota2==100){
                        double promedio;
                        if(TextUtils.isEmpty(pa3)){
                            promedio=(((Double.parseDouble(pa1)+Double.parseDouble(pa2))/2)*(nota1/100))+(Double.parseDouble(np)*(nota2/100));
                        }else{
                            promedio=(((Double.parseDouble(pa1)+Double.parseDouble(pa2)+Double.parseDouble(pa3))/3)*(nota1/100))+((Double.parseDouble(np)*(nota2/100)));
                        }
                        pasarnota.setText(Double.toString(promedio));
                        if(promedio>=Double.parseDouble(nm)) {
                            Toast.makeText(MainActivity.this, "Aprobo la materia", Toast.LENGTH_SHORT).show();
                        }else
                            Toast.makeText(MainActivity.this,"No aprobo la materia",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this,"No esta bien ingresado los porcentajes",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        Button segundoboton=findViewById(R.id.boton2);
        segundoboton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),EstudiantesDestacados.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
