package com.learning.applozic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.applozic.mobicomkit.Applozic;
import com.applozic.mobicomkit.uiwidgets.conversation.ConversationUIService;
import com.applozic.mobicomkit.uiwidgets.conversation.activity.ConversationActivity;

public class MainActivity extends AppCompatActivity {

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = getIntent().getStringExtra("name");

        if (Applozic.isConnected(this)) {
// This is the code to launch chat with a particular userId
            Intent intent = new Intent(MainActivity.this, ConversationActivity.class);
            intent.putExtra("userId", "amit"); //change the userId here for other users
            intent.putExtra(ConversationUIService.DISPLAY_NAME, name); //This name will be displayed on the toolbar of the chat screen
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }


    }
}