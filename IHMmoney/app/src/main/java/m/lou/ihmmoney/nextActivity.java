package m.lou.ihmmoney;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

public class nextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        Intent thisIntent = getIntent();
        try{
            String hello = thisIntent.getExtras().getString("hello");
            Toast.makeText(getBaseContext(), hello, Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(getBaseContext(), "intent param not received : " + e, Toast.LENGTH_SHORT).show();
        }


    }
}