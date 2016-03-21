package lab2apprun.gr8.compumovil.udea.edu.co.lab2apprun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
}
