package cz.uhk.mzdy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cz.uhk.mzdy.model.MzdaVystup;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tvCistaMzda = (TextView) findViewById(R.id.cistaMzda);

        Intent i = getIntent();
        MzdaVystup vystup = (MzdaVystup) i.getSerializableExtra("data");

        tvCistaMzda.setText(String.valueOf(vystup.getCista()));
    }
}
