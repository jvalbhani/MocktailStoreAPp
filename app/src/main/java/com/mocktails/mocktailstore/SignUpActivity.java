package com.mocktails.mocktailstore;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.mocktails.mocktailstore.ApiCalls.fetchResultApi;

/**
 * Skeleton of an Android Things activity.
 * <p>
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 *
 * <pre>{@code
 * PeripheralManagerService service = new PeripheralManagerService();
 * mLedGpio = service.openGpio("BCM6");
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
 * mLedGpio.setValue(true);
 * }</pre>
 * <p>
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 */
public class SignUpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.mocktails.mocktailstore.R.layout.activity_sign_up);
        final EditText email = (EditText)findViewById(com.mocktails.mocktailstore.R.id.signupEmail);
        final EditText fname = (EditText)findViewById(com.mocktails.mocktailstore.R.id.signupFName);
        final EditText lname = (EditText)findViewById(com.mocktails.mocktailstore.R.id.signupLName);
        final EditText pass = (EditText)findViewById(com.mocktails.mocktailstore.R.id.signupPass);
        final EditText contact = (EditText)findViewById(com.mocktails.mocktailstore.R.id.signupContact);
        final EditText cpass = (EditText)findViewById(com.mocktails.mocktailstore.R.id.signupCPass);
        final Button signUp = (Button) findViewById(com.mocktails.mocktailstore.R.id.signupBtn);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate())
                {
                    JsonObject obj = new JsonObject();
                    obj.addProperty("firstName", fname.getText().toString().trim());
                    obj.addProperty("lastName", lname.getText().toString().trim());
                    obj.addProperty("password", pass.getText().toString().trim());
                    obj.addProperty("email", email.getText().toString().trim());
                    obj.addProperty("contactNo", contact.getText().toString().trim());

                    final fetchResultApi result = new fetchResultApi();
                    result.callApi("POST", getString(R.string.mocktailstore) + "account/signup", obj.toString());
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run()
                        {
                    Log.d("------------result", result.isFinished() + " : " + result.getResult());
                            if(result.isFinished())
                            {
                                if(result.getResult().equals("error"))
                                    Toast.makeText(getApplicationContext(),"Check your internet and try again later", Toast.LENGTH_SHORT).show();
                                else
                                {
                                    SharedPreferences prefs = getApplicationContext().getSharedPreferences(getString(R.string.login), MODE_PRIVATE);
                                    prefs.edit().putString(getString(R.string.login_id), result.getResult());
                                    finish();
                                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                }
                            }
                        }
                    }, 5000);
                }

            }
            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            public boolean validate()
            {
                if(!email.getText().toString().matches(emailPattern))
                {
                    email.setError("enter valid email");
                    return false;
                }

                if(fname.getText().toString().trim().length() < 3)
                {
                    fname.setError("fname too short");
                    return false;
                }
                if(pass.getText().toString().trim().length() < 8)
                {
                    pass.setError("minimum password length is 8");
                    return false;
                }
                if(!cpass.getText().toString().equals(pass.getText().toString()))
                {
                    cpass.setError("password does not match");
                    return false;
                }
                if(contact.getText().toString().trim().length() < 10)
                {
                    contact.setError("enter valid mobile");
                    return false;
                }
                return true;
            }
        });

    }

}
