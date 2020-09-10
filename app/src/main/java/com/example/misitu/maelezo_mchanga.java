package com.example.misitu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class maelezo_mchanga extends AppCompatActivity {

    com.omarshehe.forminputkotlin.FormInputText tani, ainaYaUjenzi, houseNo;
    com.omarshehe.forminputkotlin.FormInputSpinner wilaya, shehia;
    String maelezoBinafsi;
    Button viambatanishi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maelezo_mchanga);

        tani = findViewById(R.id.tani);
        ainaYaUjenzi = findViewById(R.id.ainaYaUjenzi);
        houseNo = findViewById(R.id.houseNumber);
        wilaya = findViewById(R.id.wilaya);
        shehia = findViewById(R.id.shehia);

        Intent maelezo = getIntent();
        maelezoBinafsi = maelezo.getStringExtra("maelezoBinafsi");
        Toast.makeText(getApplicationContext(),maelezoBinafsi,Toast.LENGTH_LONG).show();

        viambatanishi = (Button)findViewById(R.id.btnViambatanishi);
        viambatanishi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!tani.isError(null) && !ainaYaUjenzi.isError(null) && !houseNo.isError(null) &&
                        !wilaya.isError(null)  && !shehia.isError(null)) {
                    event();
                }
            }
        });

    }

    public void event() {
        Intent intent = new Intent(maelezo_mchanga.this, viambatanishi.class);

        JSONObject maelezoYaMchanga = new JSONObject();
        try {
            maelezoYaMchanga.put("tani", tani.getValue());
            maelezoYaMchanga.put("aina", ainaYaUjenzi.getValue());
            maelezoYaMchanga.put("houseNo", houseNo.getValue());
            maelezoYaMchanga.put("wilaya", wilaya.getValue());
            maelezoYaMchanga.put("shehia", shehia.getValue());

        } catch (JSONException e) {

            e.printStackTrace();
        }

        JSONObject maelezoYote = new JSONObject();
        try {
            maelezoYote.put("maelezoBinafsi",new JSONObject(maelezoBinafsi));
            maelezoYote.put("maelezoYaMchanga",maelezoYaMchanga);
        } catch (JSONException e) {

            e.printStackTrace();
        }

        intent.putExtra("maelezoYote", maelezoYote.toString());
        startActivity(intent);
        finish();
    }
}