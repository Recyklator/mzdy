package cz.uhk.mzdy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import cz.uhk.mzdy.model.MzdaVystup;

public class MainActivity extends AppCompatActivity {

    private EditText edHruba;
    private EditText edDeti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edHruba = (EditText)findViewById(R.id.edHruba);
        edDeti = (EditText)findViewById(R.id.edDeti);

        Button bt = (Button)findViewById(R.id.btPocitej);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Mzda", edHruba.getText().toString());
                Log.d("Deti", edDeti.getText().toString());
                Vstup vstup = new Vstup();
                vstup.hruba = Integer.valueOf(edHruba.getText().toString());
                vstup.deti = Integer.valueOf(edDeti.getText().toString());
                new MzdyServiceTask().execute(vstup);
            }
        });
    }

    class Vstup {
        int hruba;
        int deti;
    }

    class MzdyServiceTask extends AsyncTask<Vstup, Integer, MzdaVystup> {

        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(MainActivity.this,"Upozorneni", "Stahuji data...");
        }

        @Override
        protected MzdaVystup doInBackground(Vstup... params) {
            String adresa = String.format("http://iris.uhk.cz/SalaryService/salary?hruba=%d&deti=%d", params[0].hruba, params[0].deti);
            try {
                URL url = new URL(adresa);
                JsonReader reader = new JsonReader(new InputStreamReader(url.openStream()));

                reader.beginObject();
                reader.nextName(); // salary result
                reader.beginObject();
                reader.nextName();
                int cista = reader.nextInt();
                reader.nextName();
                int dan = reader.nextInt();
                reader.nextName();
                int soc = reader.nextInt();
                reader.nextName();
                int zdr = reader.nextInt();
                reader.endObject();

                reader.close();

                return new MzdaVystup(cista, dan, soc, zdr);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(MzdaVystup mzdaVystup) {
            progress.dismiss();
            if(mzdaVystup != null) {
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("data", mzdaVystup);
                startActivity(i);
            }
        }
    }
}
