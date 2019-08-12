package com.example.practicaandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class EstudiantesDestacados extends AppCompatActivity {
    String url="https://www.fiec.espol.edu.ec/es/lista-excelencia";
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiantes_destacados);

        Button mejoresEstudiantes = findViewById(R.id.mejores);

        mejoresEstudiantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Title().execute();
            }
        });

    }

    private class Title extends AsyncTask<Void,Void,Void>{
        String title;
        ArrayList<String> downServers = new ArrayList<>();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(EstudiantesDestacados.this);
            mProgressDialog.setTitle("Mejores estudiantes de Fiec");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Document document = Jsoup.connect(url).get();
                //title = document.title();

                Element table = document.select("table").get(0);
                Elements rows = table.select("tr");

                for(int i=1;i< rows.size();i++){
                    Element row = rows.get(i);
                    Elements cols = row.select("td");
                    downServers.add(cols.text());
                }


            }catch(IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            TextView ver=findViewById(R.id.ver);
            for(int i=1;i<downServers.size();i++){
                if(i!=downServers.size()){
                    title=title+downServers.get(i)+'\n';
                }else{
                    title=title+downServers.get(i);
                }
            }
            ver.setText(title);
            mProgressDialog.dismiss();
        }
    }
}
