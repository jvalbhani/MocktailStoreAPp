package com.mocktails.mocktailstore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mocktails.mocktailstore.ApiCalls.fetchResultApi;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText email, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SharedPreferences shared = getSharedPreferences(getString(R.string.login), MODE_PRIVATE);
        String user = shared.getString(getString(R.string.login_id), null);
        if(user != null)
        {
            finish();
            startActivity(new Intent(this, HomeActivity.class));
        }

        setContentView(R.layout.activity_login);

        email = (EditText)findViewById(R.id.loginEmail);
        pass = (EditText)findViewById(R.id.loginPassword);
        Button signIn = (Button) findViewById(R.id.loginBtn);
        Button createAc = (Button) findViewById(R.id.createAccount);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate())
                {
                    final JSONObject resp = new JSONObject();
                    try {
                        resp.put("email", email.getText().toString().trim());
                        resp.put("password", pass.getText().toString().trim());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    final fetchResultApi result = new fetchResultApi();
                    Log.d("----------------3", "making api call");
                    result.callApi("POST", getString(R.string.mocktailstore) + "account/login", resp.toString());
                    Log.d("----------------3", "made api call");
                    Toast.makeText(getApplicationContext(), "Singing in.... Please wait ", Toast.LENGTH_SHORT).show();
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
                            Log.d("----------------3", "received call : " + result.isFinished() + " : " + result.getResult());
                            if(!result.getResult().equalsIgnoreCase("error"))
                            {
                                shared.edit().putString(getString(R.string.login_id), result.getResult()).commit();
                                finish();
                                startActivity(new Intent(LoginActivity.this, HomeActivity.class).putExtra("userId",result.getResult()));
                            }
                    Snackbar.make(view, "Error Occoured please try again later", Snackbar.LENGTH_LONG).show();
//                          }
//                    }, 3000);
                }
            }
        });
        createAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
    }
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public boolean validate()
    {
        if(!email.getText().toString().trim().matches(emailPattern))
        {
            email.setError("Enter valid email address");
            return false;
        }
        if(pass.getText().toString().length() < 8)
        {
            pass.setError("minimum password length is 8");
            return false;
        }

        return true;
    }

}
