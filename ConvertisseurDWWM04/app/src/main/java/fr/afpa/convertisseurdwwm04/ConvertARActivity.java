package fr.afpa.convertisseurdwwm04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import fr.afpa.convertisseurdwwm04.metier.Convert;

public class ConvertARActivity extends AppCompatActivity {

    public final static String RETOUR = "msgConversion";
    public final static int RESULT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_convert_a_r);

        // Récupère les données de l'Intent
        Intent intent = getIntent();

        String strDevDep = intent.getExtras().getString(MainActivity.DEVISE_DEPART);
        String strDevArr = intent.getExtras().getString(MainActivity.DEVISE_ARRIVEE);
        Double dblMontant = intent.getExtras().getDouble(MainActivity.MONTANT);

        // Calcul de conversion
        double dblMontantConvert = Convert.convertir(strDevDep, strDevArr, dblMontant);

        // Formatter le montant - Version 3
        String strMontantConvert = String.format("%.2f", dblMontantConvert);
        String strMontant = String.format("%.2f", dblMontant);

        // Afficher le résultat
        String msg = strMontant + " " + strDevDep + " = " + strMontantConvert + " " + strDevArr;

        // Retourner le résultat
        Intent intentResult = new Intent();
        intentResult.putExtra(ConvertARActivity.RETOUR, msg);

        setResult(RESULT_CODE, intentResult);
        finish();
    }
}