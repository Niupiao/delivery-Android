package com.niupiao.deliveryapp.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.niupiao.deliveryapp.Deliveries.DataSource;
import com.niupiao.deliveryapp.R;
import com.niupiao.deliveryapp.SlidingTab.MainTabActivity;
import com.niupiao.deliveryapp.VolleySingleton;

import org.json.JSONObject;

import java.util.prefs.Preferences;


public class LoginActivity extends ActionBarActivity {
    public static final String LOGIN_PREFS = "LOGIN_PREFS";
    private Button mLoginButton;
    private CheckBox mRememberCheckBox;
    private EditText mIdField;
    private EditText mPasswordField;
    private final Context context= this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        mIdField = (EditText) findViewById(R.id.username_et);
        mLoginButton = (Button) findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://niupiaomarket.herokuapp.com/delivery/login?format=json&key=";
                DataSource.USER_KEY = mIdField.getText().toString();
                url += mIdField.getText();
                // Formulate the request and handle the response.
                JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Intent intent = new Intent(getApplicationContext(), MainTabActivity.class);
                                startActivity(intent);
                                end();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
                VolleySingleton.getInstance(context).addToRequestQueue(jsonRequest);
            }
        });

        mRememberCheckBox = (CheckBox)findViewById(R.id.remember_checkbox);

        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        if(settings.getBoolean("rememberLogin", false)) {
            mIdField.setText(settings.getString("login", ""));
            mRememberCheckBox.setChecked(settings.getBoolean("rememberLogin", false));
        }
    }

    private void end() {
        finish();
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

    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("rememberLogin", mRememberCheckBox.isChecked());
        if(mRememberCheckBox.isChecked()) {
            editor.putString("login", mIdField.getText().toString());
        }
        // Commit the edits!
        editor.commit();
    }
}
