package m.lou.ihmmoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import m.lou.ihmmoney.modele.Convert;

public class doMyMaths extends AppCompatActivity {

    public final static int RESULT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_my_maths);

        //receive packet from intent
        Intent intent = getIntent();
        String strIn = intent.getExtras().getString(MainActivity.DEVISE_DEPART);
        String strOut = intent.getExtras().getString(MainActivity.DEVISE_ARRIVEE);
        Double dblAmount = intent.getExtras().getDouble(MainActivity.MONTANT);

        //do the maths
        double dblRes = Convert.convertir(strIn, strOut, dblAmount);
        String strRes = String.format("%.2f", dblRes);
        strRes = "Result : "+ strRes;

        //Return packet in intent, into setResult (MANDATORY)
        Intent intentRes = new Intent();
        intentRes.putExtra("MathsDone", strRes);
        setResult(RESULT_CODE, intentRes);

        //finish activity
        finish();
    }
}