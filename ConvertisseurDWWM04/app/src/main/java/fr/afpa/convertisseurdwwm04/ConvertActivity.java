package fr.afpa.convertisseurdwwm04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import fr.afpa.convertisseurdwwm04.metier.Convert;

public class ConvertActivity extends AppCompatActivity {

    // Constante
    private final static String TAG = "ConvertActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(ConvertActivity.TAG,"onCreate");
        setContentView(R.layout.activity_convert);

        // Récupère les données de l'Intent
        Intent intent = getIntent();

        String strDevDep = intent.getExtras().getString(MainActivity.DEVISE_DEPART);
        String strDevArr = intent.getExtras().getString(MainActivity.DEVISE_ARRIVEE);
        Double dblMontant = intent.getExtras().getDouble(MainActivity.MONTANT);

        // Calcul de conversion
        double dblMontantConvert = Convert.convertir(strDevDep, strDevArr, dblMontant);

        // Formatter le montant - Version 1
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(2);
        String strMontantConvert = nf.format(dblMontantConvert);

        // Formatter le montant - Version 2
        DecimalFormat df = new DecimalFormat("0.00");
        strMontantConvert = df.format(dblMontantConvert);

        // Formatter le montant - Version 3
        strMontantConvert = String.format("%.2f", dblMontantConvert);
        String strMontant = String.format("%.2f", dblMontant);

        // Afficher le résultat
        String msg = strMontant + " " + strDevDep + " = " + strMontantConvert + " " + strDevArr;

        TextView tvResultat = (TextView) findViewById(R.id.tvValueResultat);
        tvResultat.setText(msg);
    }

    // ---------------------------
    // Méthodes APPLICATIVES
    // ---------------------------
    public void clicRetour(View v) {
        Log.i(ConvertActivity.TAG, "clicRetour");
        finish(); // On demande à fermer l'activité
    }
}