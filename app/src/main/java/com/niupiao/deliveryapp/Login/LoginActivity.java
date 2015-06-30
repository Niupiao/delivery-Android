package com.niupiao.deliveryapp.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
        //ll.setVisibility(View.INVISIBLE);
/*
        Animation up = new TranslateAnimation(0, 0, 200, 0);
        up.setDuration(10);
        up.setFillAfter(true);
        */

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new AccelerateInterpolator());
        fadeIn.setDuration(150);
        fadeIn.setStartOffset(600);
        fadeIn.setFillAfter(true);

        //AnimationSet moveLoader = new AnimationSet(false);
        //moveLoader.addAnimation(up);
        //moveLoader.addAnimation(fadeIn);
        //loader.clearAnimation();
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
        // Fade textviews in
        Animation fadeIn = new AlphaAnimation(1, 0);
        fadeIn.setInterpolator(new AccelerateInterpolator());
        fadeIn.setStartOffset(300);
        fadeIn.setDuration(500);
        fadeIn.setFillAfter(true);
        ll.startAnimation(fadeIn);
/*
        Animation down = new TranslateAnimation(0, 0, 0, 200);
        down.setDuration(10);
        down.setFillAfter(true);
*/
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(150);
        fadeOut.setStartOffset(600);
        fadeOut.setFillAfter(true);

        //AnimationSet moveLoader = new AnimationSet(false);
        //moveLoader.addAnimation(down);
        //moveLoader.addAnimation(fadeOut);
        loader.clearAnimation();
        loader.startAnimation(fadeOut);

        // Move logo up
        Animation moveUp = new TranslateAnimation(0, 0, 300, 0);
        moveUp.setDuration(600);
        moveUp.setStartOffset(100);
        moveUp.setInterpolator(new DecelerateInterpolator());
        //moveUp.setFillAfter(true);
        //logo.clearAnimation();
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
                        Intent intent = new Intent(getApplicationContext(), MainTabActivity.class);
                        startActivity(intent);
                        end();
                        /* TODO: FIX THIS
                        try {
                            // Returns to login screen
                            String check = response.getString("error");
                            if (check.contains("Invalid key")) {
                                // Wrong login information
                                Log.e("error", "asl;df");
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
                        } */
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
