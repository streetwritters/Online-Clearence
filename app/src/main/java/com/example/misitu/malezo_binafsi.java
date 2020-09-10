package com.example.misitu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.omarshehe.forminputkotlin.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

public class malezo_binafsi extends AppCompatActivity {

    com.omarshehe.forminputkotlin.FormInputText fname, mname, lname, zid, phone, street;
    Button endelea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_malezo_binafsi);

        //getSupportActionBar().hide();
        endelea = (Button)findViewById(R.id.btnMchanga);
        fname = findViewById(R.id.fname);
        mname = findViewById(R.id.mname);
        lname = findViewById(R.id.lname);
        zid = findViewById(R.id.zanID);
        phone = findViewById(R.id.phoneNumber);
        street = findViewById(R.id.mtaa);


        endelea.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!fname.isError(null) && !mname.isError(null) && !lname.isError(null) &&
                        !zid.isError(null)  && !phone.isError(null) && !street.isError(null))
                {
                    event();
                }

            }
        });


    }

    public void event() {
        Intent intent = new Intent(malezo_binafsi.this, maelezo_mchanga.class);
        JSONObject maelezoBinafsi = new JSONObject();
        try {
            maelezoBinafsi.put("fname", fname.getValue());
            maelezoBinafsi.put("mname", mname.getValue());
            maelezoBinafsi.put("lname", lname.getValue());
            maelezoBinafsi.put("zid", zid.getValue());
            maelezoBinafsi.put("phone", phone.getValue());
            maelezoBinafsi.put("street", street.getValue());

        } catch (JSONException e) {
            // to do auto-generated catch block
            e.printStackTrace();
        }


        intent.putExtra("maelezoBinafsi", maelezoBinafsi.toString());
        startActivity(intent);
        finish();
    }

    public void inputValidation() {

        }

    }
