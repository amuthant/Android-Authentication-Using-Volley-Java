package com.example.android_authentication_using_volley_java;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Register_activity extends AppCompatActivity {

    ProgressDialog pd;

    private static final String REGISTER_URL = "http://abc/register.php";



    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_MOBILE = "mobile";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_PASSWORD = "password";



    Button submit;
    EditText usr;
    EditText eml;
    EditText mobr;
    EditText adr;
    EditText passr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);

        usr=(EditText) findViewById(R.id.usr);
        eml=(EditText) findViewById(R.id.eml);
        mobr=(EditText) findViewById(R.id.mobr);
        adr=(EditText) findViewById(R.id.adr);
        passr = (EditText) findViewById(R.id.passr);



        submit = (Button)findViewById(R.id.subbut);

        submit.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                registerUser();
            }

        });


    }
    protected void registerUser()
    {



        final String username = usr.getText().toString().trim();
        final String email = eml.getText().toString().trim();
        final String mobile = mobr.getText().toString().trim();
        final String address = adr.getText().toString().trim();
        final String password = passr.getText().toString().trim();



        pd=new ProgressDialog(Register_activity.this);
        pd.setTitle("Loading...");
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>()
                {
                    @Override

                    public void onResponse(String response)
                    {
                        Toast.makeText(Register_activity.this,response,Toast.LENGTH_LONG).show();
                        if(response.trim().equals("Successfully Registered"))
                        {
                            Intent it= new Intent(Register_activity.this,Login_activity.class);

                            startActivity(it);
                        }
                        pd.dismiss();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {

                        Toast.makeText(Register_activity.this,error.toString(),Toast.LENGTH_LONG).show();
                        pd.dismiss();
                    }
                })

        {

            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();

                params.put(KEY_USERNAME,username);
                params.put(KEY_EMAIL,email);
                params.put(KEY_MOBILE,mobile);
                params.put(KEY_ADDRESS,address);
                params.put(KEY_PASSWORD,password);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(Register_activity.this);
        requestQueue.add(stringRequest);




    }
}
