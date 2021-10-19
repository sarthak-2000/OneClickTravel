package com.example.oneclicktravel.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oneclicktravel.Database.DbHelper;
import com.example.oneclicktravel.Model.UserData;
import com.example.oneclicktravel.R;

public class SignUpActivity extends AppCompatActivity{

    SessionManager sessionManager;

    EditText edt_name,edt_email,edt_password,edt_cnfpassword,edt_mobno;

    TextView txt_loginlink;

    Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();

        txt_loginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login_intent=new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(login_intent);
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    public void init()
    {
        edt_name=(EditText)findViewById(R.id.edt_name);
        edt_email=(EditText)findViewById(R.id.edt_email);
        edt_password=(EditText)findViewById(R.id.edt_password);
        edt_cnfpassword=(EditText)findViewById(R.id.edt_confirm_password);
        edt_mobno=(EditText)findViewById(R.id.edt_mobno);

        btn_signup=(Button)findViewById(R.id.btn_signup);

        txt_loginlink=(TextView)findViewById(R.id.txt_loginlink);

    }


    public void register(){

        if (!validate())
        {
            Toast.makeText(getBaseContext(), "Enter the details as required", Toast.LENGTH_LONG).show();
            btn_signup.setEnabled(true);
            return;
        }

        btn_signup.setEnabled(false);

        String name=edt_name.getText().toString();
        String email=edt_email.getText().toString();
        String password=edt_password.getText().toString();
        String cpassword=edt_cnfpassword.getText().toString();
        String mobile=edt_mobno.getText().toString();

        UserData udata=new UserData();

        udata.setName(name);
        udata.setEmail(email);
        udata.setPassword(password);
        udata.setContact(mobile);

        DbHelper db=new DbHelper(this);
        db.insertUserDetail(udata);

        db = DbHelper.getInstance(getApplicationContext());

        Intent show=new Intent(SignUpActivity.this,LoginActivity.class);
        startActivity(show);




    }


    private boolean validate()
    {
        boolean valid = true;

        //fetch the details of EditText in String Variable for Validating
        String name=edt_name.getText().toString();
        String email=edt_email.getText().toString();
        String password=edt_password.getText().toString();
        String cpassword=edt_cnfpassword.getText().toString();
        String mobile=edt_mobno.getText().toString();

        //First Name must not be left Empty
        if(name.isEmpty())
        {
            edt_name.setError("Can't be left Empty");
            valid = false;
        }
        else
        {
            edt_name.setError(null);
        }

        //Validate if the email Address is according to pre-defined Format
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edt_email.setError("enter a valid email address");
            valid = false;
        } else {
            edt_email.setError(null);
        }


        //Validate the length of Mobile number is 10 and the field is not left empty
        if (mobile.isEmpty() || mobile.length()!=10) {
            edt_mobno.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            edt_mobno.setError(null);
        }


        //Validate the password is entered inalphanumeric characters and is between length 4-10
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            edt_password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            edt_password.setError(null);
        }

        //Validate the confirm password is same as the password
        if (cpassword.isEmpty() || cpassword.length() < 4 || cpassword.length() > 10 || !(cpassword.equals(password))) {
            edt_cnfpassword.setError("Password Do not match");
            valid = false;
        } else {
            edt_cnfpassword.setError(null);
        }


        return valid;
    }
}
