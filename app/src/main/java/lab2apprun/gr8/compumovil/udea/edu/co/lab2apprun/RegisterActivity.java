package lab2apprun.gr8.compumovil.udea.edu.co.lab2apprun;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import co.edu.udea.lab2apprun.gr8.database.UserDbManager;

public class RegisterActivity extends AppCompatActivity {

    private UserDbManager userDbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userDbManager = new UserDbManager(RegisterActivity.this);
    }

    public void onClickGoBack(View view){
        finish();
    }

    @Override
    public void finish(){
        Intent data = new Intent();
        setResult(RESULT_OK, data);
        super.finish();
    }
    public void onClickRegisterUser(View view){
        TextView account = (TextView) findViewById(R.id.usernameRegisterField);
        TextView pass = (TextView) findViewById(R.id.userpassRegisterField);
        TextView passConfirm = (TextView) findViewById(R.id.confirmpassRegisterField);
        TextView email = (TextView) findViewById(R.id.emailRegisterField);


        if(account.getText().toString().equals("")||pass.getText().toString().equals("")||passConfirm.getText().toString().equals("")||email.getText().toString().equals("")){
            Toast.makeText(RegisterActivity.this, "Por favor ingrese todos los datos", Toast.LENGTH_SHORT).show();
        }
        else if (!pass.getText().toString().equals(passConfirm.getText().toString())){
            Toast.makeText(RegisterActivity.this, "La contraseña no coincide con la confirmación", Toast.LENGTH_SHORT).show();
        }
        else if (userDbManager.userExists(account.getText().toString())){
            Toast.makeText(RegisterActivity.this, "Usuario ya registrado", Toast.LENGTH_SHORT).show();
        }
        else{
            userDbManager.insertUser(account.getText().toString(), pass.getText().toString(), email.getText().toString());
            Toast.makeText(RegisterActivity.this, "Usuario ingresado", Toast.LENGTH_SHORT).show();
            account.setText("");
            pass.setText("");
            passConfirm.setText("");
            email.setText("");
        }
    }
}
