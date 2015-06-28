package com.niupiao.deliveryapp.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.niupiao.deliveryapp.R;
import com.niupiao.deliveryapp.SlidingTab.MainTabActivity;
import com.niupiao.deliveryapp.VolleySingleton;

import org.json.JSONObject;


public class LoginActivity extends ActionBarActivity {
    private Button mLoginButton;
    private EditText mIdField;
    private EditText mPasswordField;
    private final Context context= this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Intent intent = new Intent(this, MainTabActivity.class);

        mIdField = (EditText) findViewById(R.id.username_et);
        mPasswordField = (EditText) findViewById(R.id.password_et);

        mLoginButton = (Button) findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://niupiaomarket.herokuapp.com/delivery/login?format=JSON";
                url = url + "&key=" + mIdField.getText();
                Log.d("URL", url);
                // Formulate the request and handle the response.
                JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                        intent.putExtra("KEY", mIdField.getText());
                        startActivity(intent);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://niupiaomarket.herokuapp.com",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(),error.getCause().toString(), Toast.LENGTH_LONG).show();
                            }
                        });
                VolleySingleton.getInstance(context).addToRequestQueue(jsonRequest);
                //VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
