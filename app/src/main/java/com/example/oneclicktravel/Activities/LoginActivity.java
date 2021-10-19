package com.example.oneclicktravel.Activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oneclicktravel.Database.DbHelper;
import com.example.oneclicktravel.R;

public class LoginActivity extends RuntimePermissionsActivity {

    Button btn_login;
    EditText edt_email, edt_password;
    SessionManager sessionManager;
    private static final int REQUEST_PERMISSIONS = 20;
    TextView txt_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = (Button) findViewById(R.id.btn_login);

        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_password = (EditText) findViewById(R.id.edt_password);

        txt_signup=(TextView)findViewById(R.id.tv_new_user);

        sessionManager = new SessionManager();



        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(signup);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    @Override
    public void onPermissionsGranted(int requestCode) {

    }

    public void login() {

        if (!validate()) {
            Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
            btn_login.setEnabled(true);
            return;
        }

        String email=edt_email.getText().toString();
        String password=edt_password.getText().toString();

        DbHelper db=new DbHelper(this);

        String stored_pwd= db.getSingleUserData(email);
        db = DbHelper.getInstance(getApplicationContext());

        if(stored_pwd.equals("NULL"))
        {
            Toast.makeText(getApplicationContext(),"User does not exist",Toast.LENGTH_SHORT).show();

        }
        else {

            if (password.equals(stored_pwd)) {
                sessionManager.setPreferences(LoginActivity.this, "status", "1");
                String STATUS = sessionManager.getPreferences(LoginActivity.this, "status");
                Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    LoginActivity.super.requestAppPermissions(new
                                    String[]{Manifest.permission.INTERNET,
                                    Manifest.permission.ACCESS_FINE_LOCATION}, R.string
                                    .runtime_permissions_txt
                            , REQUEST_PERMISSIONS);

                }
                /* LoginActivity.super.requestAppPermissions(new
                                String[]{Manifest.permission.READ_CONTACTS,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE}, R.string
                                .runtime_permissions_txt
                        , REQUEST_PERMISSIONS);*/
                Intent login_intent = new Intent(this, BaseActivity.class);
                startActivity(login_intent);
            } else {

                Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
            }

        }


        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        new Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        btn_login.setEnabled(true);
                        finish();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);

    }

    public boolean validate() {
        boolean valid = true;

        String email = edt_email.getText().toString();
        String pass = edt_password.getText().toString();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edt_email.setError("Enter a valid email address");
            valid = false;

        } else {
            edt_email.setError(null);
        }
        if (pass.isEmpty() || pass.length() < 4 || pass.length() > 10) {
            edt_password.setError("between 4 to 10 alphanumeric characters");
            valid = false;
        } else {
            edt_password.setError(null);

        }
        return valid;
    }
}

