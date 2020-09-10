package com.example.misitu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class viambatanishi extends AppCompatActivity {
    public static final int PICKFILE_RESULT_CODE = 1;
    public static final int PICKFILE_RESULT_CODE2 = 2;

    Button sheha, kibali, sendData;
    TextView shehaName, kibaliName;
    private Uri fileUri;
    private String shehaFilePath;
    private String kibaliFilePath;
    private Bitmap shehaBitmap;
    private Bitmap kibaliBitmap;
    String maelezoYote;
    private ProgressDialog prg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viambatanishi);
        //getSupportActionBar().hide();

        sheha = (Button)findViewById(R.id.btnSheha);
        shehaName = (TextView)findViewById(R.id.shehaName);
        kibali = (Button)findViewById(R.id.btnKibali);
        sendData = (Button)findViewById(R.id.btnTuma);
        kibaliName = (TextView)findViewById(R.id.kibaliName);
        Intent tester = getIntent();
        maelezoYote = tester.getStringExtra("maelezoYote");

        prg = new ProgressDialog(this);
//        Toast.makeText(this, maelezoYote, Toast.LENGTH_SHORT).show();

        sheha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                chooseFile.setType("*/*");
                chooseFile = Intent.createChooser(chooseFile, "Choose a file");
                startActivityForResult(chooseFile, PICKFILE_RESULT_CODE);
            }
        });

        kibali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                chooseFile.setType("*/*");
                chooseFile = Intent.createChooser(chooseFile, "Choose a file");
                startActivityForResult(chooseFile, PICKFILE_RESULT_CODE2);
            }
        });

        sendData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                event();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICKFILE_RESULT_CODE:
                fileUri = data.getData();
                shehaFilePath = fileUri.getPath();
                if (shehaFilePath != null) {
                    try {
                        shehaBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), fileUri);
                        shehaName.setText(shehaFilePath);
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }
                break;
            case PICKFILE_RESULT_CODE2:
                fileUri = data.getData();
                kibaliFilePath = fileUri.getPath();
                if (kibaliFilePath != null) {
                    try {
                        kibaliBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), fileUri);
                        kibaliName.setText(kibaliFilePath);
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }

                break;
            default:
                Toast.makeText(getApplicationContext(), "No File Selected", Toast.LENGTH_SHORT).show();

        }
    }


    public void event() {
        registerUser();
    }




    private void registerUser() {
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, "http://192.168.43.212/ClearenceApp/api/apply.php",
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        Toast.makeText(getApplicationContext(), "Result : \n"+response, Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject obj = new JSONObject(new String(response.data));
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
//                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "An Error Occur !!!\n"+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("GotError",""+error.getMessage());
                    }
                }) {


            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("data",maelezoYote);
                return params;
            }
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("sheha", new DataPart(imagename + ".png", getFileDataFromDrawable(shehaBitmap)));
                params.put("kibali", new DataPart(imagename + ".png", getFileDataFromDrawable(kibaliBitmap)));
                return params;
            }
        };

        //adding the request to volley
        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

}