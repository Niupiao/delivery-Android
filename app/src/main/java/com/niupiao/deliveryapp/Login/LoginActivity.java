package com.niupiao.deliveryapp.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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


public class LoginActivity extends ActionBarActivity {
    private Button mLoginButton;
    private CheckBox mRememberCheckBox;
    private EditText mIdField;
    private LinearLayout ll;
    private View logo;
    private ProgressBar loader;
    private final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        ll = (LinearLayout) findViewById(R.id.sliding_ll);
        logo = findViewById(R.id.image);
        loader = (ProgressBar) findViewById(R.id.loading_circle);

        mIdField = (EditText) findViewById(R.id.username_et);
        mLoginButton = (Button) findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoginAnimation();

                // Delayed start
                android.os.Handler handler = new android.os.Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendLoginRequest();
                    }
                }, 500);
            }
        });

        mRememberCheckBox = (CheckBox) findViewById(R.id.remember_checkbox);

        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        if (settings.getBoolean("rememberLogin", false)) {
            mIdField.setText(settings.getString("login", ""));
            mRememberCheckBox.setChecked(settings.getBoolean("rememberLogin", false));
            mLoginButton.callOnClick();
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("rememberLogin", mRememberCheckBox.isChecked());
        if (mRememberCheckBox.isChecked()) {
            editor.putString("login", mIdField.getText().toString());
        }
        // Commit the edits!
        editor.commit();
    }

    // Move views
    private void startLoginAnimation() {
        // Transition to loading animation
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(500);
        fadeOut.setFillAfter(true);
        ll.startAnimation(fadeOut);
        ll.setVisibility(View.GONE);

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new AccelerateInterpolator());
        fadeIn.setDuration(100);
        fadeIn.setStartOffset(900);
        fadeIn.setFillAfter(true);
        loader.startAnimation(fadeIn);
        loader.setVisibility(View.VISIBLE);

        // Move logo down
        Animation moveDown = new TranslateAnimation(0, 0, 0, 200);
        moveDown.setDuration(600);
        moveDown.setStartOffset(800);
        moveDown.setInterpolator(new DecelerateInterpolator());
        moveDown.setFillAfter(true);
        logo.startAnimation(moveDown);
    }

    // Create and send login request to server
    private void sendLoginRequest() {
        String url = "https://niupiaomarket.herokuapp.com/delivery/login?format=json&key=";
        DataSource.USER_KEY = mIdField.getText().toString();
        url += mIdField.getText();
        // Formulate the request and handle the response.
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Intent intent = new Intent(getApplicationContext(), MainTabActivity.class);
                        //startActivity(intent);
                        //end();
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
}
