package lab2apprun.gr8.compumovil.udea.edu.co.lab2apprun;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import co.edu.udea.lab2apprun.gr8.database.UserDbManager;

public class LoginActivity extends AppCompatActivity {

    private static final int REQUEST_CODE=10;
    UserDbManager userDbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userDbManager = new UserDbManager(LoginActivity.this);
    }

    public void onClickRegister(View view){
        clearFields();
        Intent i = new Intent(this,RegisterActivity.class);
        startActivityForResult(i, REQUEST_CODE);
    }

    public void onClickLogin(View view){
        TextView account = (TextView) findViewById(R.id.userLoginField);
        TextView password = (TextView) findViewById(R.id.passwordLoginField);

        if (account.getText().toString().equals("")||password.getText().toString().equals("")){
            Toast.makeText(LoginActivity.this, "Por favor ingrese todos los campos", Toast.LENGTH_SHORT).show();
        }
        else if (checkFields(account.getText().toString(), password.getText().toString())){
            clearFields();
            Intent i = new Intent(this,MainActivity.class);
            startActivityForResult(i, REQUEST_CODE);
            finish();
        }
    }

    private void clearFields() {
        TextView account = (TextView) findViewById(R.id.userLoginField);
        TextView password = (TextView) findViewById(R.id.passwordLoginField);

        account.setText("");
        password.setText("");
    }

    private boolean checkFields(String account, String pass) {
        boolean findAccount = userDbManager.userExists(account);
        if(!findAccount){
            Toast.makeText(LoginActivity.this, "Usuario no existente", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (userDbManager.getUserAccount(account).equals(account) && userDbManager.getUserPassword(account).equals(pass)) {
            return true;
        } else {
            Toast.makeText(LoginActivity.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
