package com.learning.applozic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.applozic.mobicomkit.Applozic;
import com.applozic.mobicomkit.api.account.register.RegistrationResponse;
import com.applozic.mobicomkit.api.account.user.User;
import com.applozic.mobicomkit.listners.AlLoginHandler;

public class LoginActivity extends AppCompatActivity {

    EditText name,pass;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = findViewById(R.id.enterName);
        pass = findViewById(R.id.enterPassword);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().equalsIgnoreCase("") && pass.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(LoginActivity.this, "Please fill all detail", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    logIn(name.getText().toString(),pass.getText().toString());
                }
            }
        });
    }

    private void logIn(String userId, String pass) {
        User user = new User();
        user.setUserId(userId); //userId it can be any unique user identifier NOTE : +,*,? are not allowed chars in userId.
        user.setDisplayName(userId); //displayName is the name of the user which will be shown in chat messages
        user.setPassword(pass); //If password is set, you need to pass it always when authenticating this user.
        user.setImageLink("");//optional, set your image link if you have

        Applozic.connectUser(this, user, new AlLoginHandler() {
            @Override
            public void onSuccess(RegistrationResponse registrationResponse, Context context) {
                // After successful registration with Applozic server the callback will come here
                Intent mainIntent = new Intent(context, MainActivity.class);
                mainIntent.putExtra("name",userId);
                context.startActivity(mainIntent);
                LoginActivity.this.finish();
            }
            @Override
            public void onFailure(RegistrationResponse registrationResponse, Exception exception) {
                // If any failure in registration the callback  will come here
                Toast.makeText(LoginActivity.this, "Error : " + registrationResponse, Toast.LENGTH_SHORT).show();
            }
        });

    }
}