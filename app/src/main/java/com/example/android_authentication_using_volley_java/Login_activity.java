package com.example.android_authentication_using_volley_java;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;



public class Login_activity extends AppCompatActivity {

    TextView lt;
    Button sginn,sgupy;
    ProgressDialog pd;

    EditText  moby,passy;
    String    mobile,password,user_idd;

    //use your own hosting url "http://*"
    public static final String LOGIN_URL = "http://abc/login.php";


    public static final String KEY_MOBILE="mobile";
    public static final String KEY_PASSWORD="password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        sgupy=(Button)findViewById(R.id.regy);
        sgupy.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                Intent it = new Intent(Login_activity.this,Register_activity.class);
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(it);
            }
        });






        moby = (EditText)findViewById(R.id.mob);
        passy = (EditText)findViewById(R.id.pass);

        sginn = (Button)findViewById(R.id.sgin);
        sginn.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                mobile = moby.getText().toString().trim();
                password = passy.getText().toString().trim();

                if(mobile.equals("")||password.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter the values",Toast.LENGTH_SHORT).show();
                }


                else if(mobile.equals("ADMIN")&&password.equals("admin"))
                {
                    Intent it = new Intent(Login_activity.this,Register_activity.class);

                    it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(it);
                }
                else
                {
                    // Toast.makeText(getApplicationContext(),"Succed",2000).show();
                    try
                    {
                        userLogin();

                    }
                    catch(Exception e)
                    {

                    }
                }

            }

            private void userLogin()
            {
                // TODO Auto-generated method stub
                pd=new ProgressDialog(Login_activity.this);
                pd.setTitle("Loading...");
                pd.setIndeterminate(true);
                pd.setCancelable(false);
                pd.show();

                StringRequest stringRequest;
                stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response)
                            {
                                if(response.trim().equals("success"))
                                {


                                    Intent intHome = new Intent(Login_activity.this,Register_activity.class);

                                    startActivity(intHome);


                                }
                                else
                                {
                                    Toast.makeText(Login_activity.this,response,Toast.LENGTH_LONG).show();
                                }

                                pd.dismiss();
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                Toast.makeText(Login_activity.this,error.toString(),Toast.LENGTH_LONG ).show();
                                pd.dismiss();
                            }
                        })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> map = new HashMap<String,String>();
                        map.put(KEY_MOBILE,mobile);
                        map.put(KEY_PASSWORD,password);
                        return map;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(Login_activity.this);
                requestQueue.add(stringRequest);
            }
        });

    }
}
