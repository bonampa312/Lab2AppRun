package lab2apprun.gr8.compumovil.udea.edu.co.lab2apprun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    private static final int REQUEST_CODE=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClickRegister(View view){
        Intent i = new Intent(this,RegisterActivity.class);
        startActivityForResult(i, REQUEST_CODE);
    }

    public void onClickLogin(View view){
        Intent i = new Intent(this,MainActivity.class);
        startActivityForResult(i, REQUEST_CODE);
        finish();
    }

}
