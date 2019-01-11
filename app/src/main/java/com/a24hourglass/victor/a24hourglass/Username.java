package com.a24hourglass.victor.a24hourglass;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Username extends AppCompatActivity {
    public static final String USER_NAME = "PreferenceFile";

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private EditText userName;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);

        userName = (EditText) findViewById(R.id.userName);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();

        checkSharedPreferences();

        button = findViewById(R.id.okButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();
                mEditor.putString(getString(R.string.username), name);
                mEditor.commit();
                startMainActivity();

            }
        });
    }

    private void checkSharedPreferences() {
        String name = mPreferences.getString(getString(R.string.username), "");

        userName.setText(name);
        if(name.equals("")) {

        } else {
            startMainActivity();
        }
    }

    public void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
