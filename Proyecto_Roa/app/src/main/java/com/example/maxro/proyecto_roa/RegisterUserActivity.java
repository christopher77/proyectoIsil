package com.example.maxro.proyecto_roa;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterUserActivity extends AppCompatActivity {

    EditText User, Password ;
    Button Registrar;
    String UserHolder, PasswordHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder ;
    DatabaseHelper sqLiteHelper;
    Cursor cursor;
    String F_Result = "Not_Found";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        Registrar = (Button)findViewById(R.id.btnRegistrar);
        User = (EditText)findViewById(R.id.editUser);
        Password = (EditText)findViewById(R.id.editPassword);
        sqLiteHelper = new DatabaseHelper(this);

        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDataBaseBuild();

                SQLiteTableBuild();

                CheckingData();

                CheckingUser();

                LimpiarData();
            }
        });
    }

    public void SQLiteDataBaseBuild(){
        sqLiteDatabaseObj = openOrCreateDatabase(DatabaseHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);
    }

    public void SQLiteTableBuild() {
        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " + DatabaseHelper.TABLE_NAME
                + "(" + DatabaseHelper.Table_Column_1_User + " VARCHAR, "
                + DatabaseHelper.Table_Column_2_Password + " VARCHAR);");

    }

    public void CheckingData(){
        UserHolder = User.getText().toString();
        PasswordHolder = Password.getText().toString();

        if( TextUtils.isEmpty(UserHolder) || TextUtils.isEmpty(PasswordHolder)){
            EditTextEmptyHolder = false ;
        }
        else {
            EditTextEmptyHolder = true ;
        }
    }

    public void CheckingUser(){
        sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();
        cursor = sqLiteDatabaseObj.query(DatabaseHelper.TABLE_NAME, null, " " + DatabaseHelper.Table_Column_1_User + "=?",
                new String[]{UserHolder}, null, null, null);

        while (cursor.moveToNext()) {
            if (cursor.isFirst()) {
                cursor.moveToFirst();
                F_Result = "User Found";
                cursor.close();
            }
        }
        CheckFinalResult();
    }

    public void CheckFinalResult(){
        if(F_Result.equalsIgnoreCase("User Found"))
        {
            Toast.makeText(RegisterUserActivity.this,"Ya existe ese usuario",Toast.LENGTH_LONG).show();
        }
        else {
            InsertarData();
        }
        F_Result = "Not_Found" ;
    }

    public void LimpiarData(){
        User.getText().clear();
        Password.getText().clear();
    }

    public void InsertarData(){
        if(EditTextEmptyHolder == true)
        {
            SQLiteDataBaseQueryHolder = "INSERT INTO "+DatabaseHelper.TABLE_NAME+" " +
                    "(user,password) VALUES('"+UserHolder+"', '"+PasswordHolder+"');";
            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);
            sqLiteDatabaseObj.close();
            Toast.makeText(RegisterUserActivity.this,"Usuario Registrado", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(RegisterUserActivity.this,"Debe llenar todos los campos.", Toast.LENGTH_LONG).show();
        }
    }
}
