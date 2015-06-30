package com.niupiao.deliveryapp.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
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
    public static final String LOGIN_PREFS = "LOGIN_PREFS";
    private Button mLoginButton;
    private CheckBox mRememberCheckBox;
    private EditText mIdField;
    private LinearLayout ll;
    private View logo;
    private LinearLayout loader;
    private EditText mPasswordField;
    private final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        ll = (LinearLayout) findViewById(R.id.sliding_ll);
        logo = findViewById(R.id.image);
        loader = (LinearLayout) findViewById(R.id.loading_circle);

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
                }, 1700);
            }
        });

        mRememberCheckBox = (CheckBox) findViewById(R.id.remember_checkbox);

        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        if (settings.getBoolean("rememberLogin", false)) {
            mIdField.setText(settings.getString("login", ""));
            mRememberCheckBox.setChecked(settings.getBoolean("rememberLogin", true));
            mLoginButton.callOnClick();
        }

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
        ll.clearAnimation();
        ll.startAnimation(fadeOut);
        ll.setVisibility(View.GONE);

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new AccelerateInterpolator());
        fadeIn.setDuration(150);
        fadeIn.setStartOffset(700);
        fadeIn.setFillAfter(true);
        loader.clearAnimation();
        loader.startAnimation(fadeIn);
        loader.setVisibility(View.VISIBLE);

        // Move logo down
        Animation moveDown = new TranslateAnimation(0, 0, 0, 300);
        moveDown.setDuration(600);
        moveDown.setStartOffset(600);
        moveDown.setInterpolator(new DecelerateInterpolator());
        moveDown.setFillAfter(true);
        logo.clearAnimation();
        logo.startAnimation(moveDown);
    }

    // Reverse prior animation
    private void returnToLogin() {
        // Transition to loading animation
        Animation fadeIn = new AlphaAnimation(1, 0);
        fadeIn.setInterpolator(new AccelerateInterpolator());
        fadeIn.setDuration(100);
        fadeIn.setFillAfter(true);
        ll.clearAnimation();
        ll.startAnimation(fadeIn);
        ll.setVisibility(View.VISIBLE);

        Animation fadeOut = new AlphaAnimation(0, 1);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(100);
        fadeOut.setStartOffset(100);
        fadeOut.setFillAfter(true);
        loader.clearAnimation();
        loader.startAnimation(fadeOut);
        loader.setVisibility(View.GONE);

        // Move logo down
        Animation moveUp = new TranslateAnimation(0, 0, 300, 0);
        moveUp.setDuration(600);
        moveUp.setStartOffset(100);
        moveUp.setInterpolator(new DecelerateInterpolator());
        moveUp.setFillAfter(true);
        logo.clearAnimation();
        logo.startAnimation(moveUp);
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
                        try {
                            String check = response.getString("error");
                            if (check.contains("Invalid key")) {
                                // Wrong login information
                                returnToLogin();
                                Log.e("false login", "true");
                            } else {
                                // Start application
                                Intent intent = new Intent(getApplicationContext(), MainTabActivity.class);
                                startActivity(intent);
                                end();
                            }
                        } catch (Exception e) {
                            Log.e("JSON login error: ", e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        VolleySingleton.getInstance(context).addToRequestQueue(jsonRequest);
    }

    private void end() {
        finish();
    }
}
