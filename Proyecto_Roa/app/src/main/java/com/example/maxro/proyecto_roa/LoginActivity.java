package com.example.maxro.proyecto_roa;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button LogInButton,RegistroUserButton ;
    EditText User, Password ;
    String UserHolder, PasswordHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    DatabaseHelper  sqLiteHelper;
    Cursor cursor;
    String TempPassword = "NOT_FOUND" ;
    public static final String UsuarioName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LogInButton = (Button) findViewById(R.id.buttonLogin);
        RegistroUserButton= (Button) findViewById(R.id.buttonRegister);
        User = (EditText) findViewById(R.id.etUser);
        Password = (EditText) findViewById(R.id.etPassword);
        sqLiteHelper = new DatabaseHelper(this);

        LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckEditTextStatus();
                LoginFunction();
            }
        });

        RegistroUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterUserActivity.class);
                startActivity(intent);
            }
        });
    }

    public void LoginFunction(){
        if(EditTextEmptyHolder) {
            sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();
            cursor = sqLiteDatabaseObj.query(DatabaseHelper.TABLE_NAME, null, " " + DatabaseHelper.Table_Column_1_User +
                    "=?", new String[]{UserHolder}, null, null, null);

            while (cursor.moveToNext()) {
                if (cursor.isFirst()) {
                    cursor.moveToFirst();
                    TempPassword = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Table_Column_2_Password));
                    cursor.close();
                }
            }
            CheckFinalResult();
        }
        else {
            Toast.makeText(LoginActivity.this,"Complete los campos Usuario y Password.",Toast.LENGTH_LONG).show();
        }
    }

    public void CheckEditTextStatus(){
        UserHolder = User.getText().toString();
        PasswordHolder = Password.getText().toString();
        if( TextUtils.isEmpty(UserHolder) || TextUtils.isEmpty(PasswordHolder)){
            EditTextEmptyHolder = false ;
        }
        else {
            EditTextEmptyHolder = true ;
        }
    }

    public void CheckFinalResult(){
        if(TempPassword.equalsIgnoreCase(PasswordHolder))
        {
            Toast.makeText(LoginActivity.this,"Login exitoso",Toast.LENGTH_LONG).show();
            savePreferences();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(LoginActivity.this,"Usuario o Password incorrectos, intente nuevamente.",Toast.LENGTH_LONG).show();
        }
        TempPassword = "NOT_FOUND" ;
    }

    private void savePreferences() {
        DatabaseHelper.saveSession(this,UserHolder,PasswordHolder);
    }

}
