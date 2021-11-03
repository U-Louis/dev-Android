package m.lou.ihmmoney;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Toast;

import m.lou.ihmmoney.modele.DevisesMgr;

public class devisesManager extends AppCompatActivity {

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

/*
        chargeSpinner(R.id.sprIn, this.strIn );
*/


        DevisesMgr bddMgrInstance = new DevisesMgr();
        bddMgrInstance.addDevise("cacahuete", 0.45, this);

        // Test
        bddMgrInstance.getAll(this);
    }
}