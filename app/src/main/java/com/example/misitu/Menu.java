package com.example.misitu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    //ImageViewDeclearation
    Button mawe, mchanga, kokoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getSupportActionBar().hide();
        mawe = (Button) findViewById(R.id.mawe);
        mchanga = (Button) findViewById(R.id.mchanga);
        kokoto = (Button) findViewById(R.id.kokoto);


        //Mchanga
        mchanga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mchanga();
            }
        });

    }


    public void mawe() {
    }

    public void kokoto() {

    }

    public void mchanga() {
        Intent intent = new Intent(Menu.this, malezo_binafsi.class);
        startActivity(intent);
    }
}