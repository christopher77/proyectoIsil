package com.example.maxro.proyecto_roa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tviLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tviLogout= (TextView)findViewById(R.id.tviLogout);
        String username = DatabaseHelper.getUserSession(this);

        tviLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    private void logout() {
        DatabaseHelper.signOut(this);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

}
