package fr.afpa.convertisseurdwwm04;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.afpa.convertisseurdwwm04.metier.Convert;

public class MainActivity extends AppCompatActivity {

    // Constantes
    private final static String TAG = "MainActivity";
    public final static String DEVISE_DEPART = "devDep";
    public final static String DEVISE_ARRIVEE = "devArr";
    public final static String MONTANT = "montant";
    public final static int REQUEST_CODE = 10;

    // Données membres
    private ArrayList<String> listeDevises = null;
    private String strDevDep = null;
    private String strDevArr = null;
    private Double dblMontantDep = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chargeDevise();
        chargeSpinner(R.id.spDevDep);
        chargeSpinner(R.id.spDevArr);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case MainActivity.REQUEST_CODE:
                if (resultCode == ConvertARActivity.RESULT_CODE) {
                    String msg = data.getStringExtra(ConvertARActivity.RETOUR);

                    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this,R.string.err_result_code,Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                Toast.makeText(this,R.string.err_request_code,Toast.LENGTH_SHORT).show();
        }
    }

    // ---------------------------
    // Méthodes APPLICATIVES
    // ---------------------------

    /**
     * Charge le spinner passé en paramètre avec un adapter sur le tableau
     * listeDevise
     * @param idSpinner
     */
    private void chargeSpinner(int idSpinner) {
        Log.i(MainActivity.TAG, "chargeSpinner");

        // Récupère le spinner
        Spinner spinner = (Spinner) findViewById(idSpinner);

        // Crée un adapter
        final ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, this.listeDevises);
        // Assigne l'adapter au spinner
        spinner.setAdapter(adapter);
    }

    /**
     * Alimente le tableau listeDevises avec les clés de la Map Convert.conversionTable
     */
    private void chargeDevise() {
        Log.i(MainActivity.TAG, "chargeDevise");

        Map<String, Double> conversionTable =  Convert.getConversionTable();
        this.listeDevises = new ArrayList<String>(conversionTable.keySet());

        // Ajouter une clé vide
        this.listeDevises.add("");

        // Trier le tableau
        Collections.sort(this.listeDevises);
    }

    /**
     * Contrôle des champs du formulaire
     * @return boolean
     */
    private boolean doConvertir() {
        Log.i(MainActivity.TAG, "doConvertir");

        // Récupérer les Widgets de la vue
        Spinner spDevDep = (Spinner) findViewById(R.id.spDevDep);
        this.strDevDep = (String) spDevDep.getSelectedItem();
        Spinner spDevArr = (Spinner) findViewById(R.id.spDevArr);
        this.strDevArr = (String) spDevArr.getSelectedItem();
        EditText etMontant = (EditText) findViewById(R.id.edtMontant);
        String strMontantDep = etMontant.getText().toString();

        // Contrôler les valeurs
        // Test si la devise de départ est VIDE
        if (this.strDevDep.equals("")) {
            Toast.makeText(getBaseContext(),R.string.err_dev_dep,Toast.LENGTH_SHORT).show();
        } else if (this.strDevArr.equals("")) {
            // Test si la devise d'arrivée est VIDE
            Toast.makeText(getBaseContext(),R.string.err_dev_arr,Toast.LENGTH_SHORT).show();
        } else if (this.strDevDep.equals(this.strDevArr)) {
            // Test si la devise d'arrivée est égale à la devise de départ
            Toast.makeText(getBaseContext(),R.string.err_identique,Toast.LENGTH_SHORT).show();
        } else if (strMontantDep.equals("") || strMontantDep.matches("[^0-9]")) {
            // Test si le montant ne contient aucun chiffre
            Toast.makeText(getBaseContext(),R.string.err_montant,Toast.LENGTH_SHORT).show();
        } else {
            // TOUT VA BIEN
            try {

                this.dblMontantDep = Double.valueOf(strMontantDep);
                // Exemple en 1 seule ligne
                //this.dblMontantDep = Double.valueOf(((EditText) findViewById(R.id.edtMontant)).getText().toString());

                return true;
            } catch (NumberFormatException e) {
                // Si la conversion du montant en Double plante
                Toast.makeText(getBaseContext(),R.string.err_montant,Toast.LENGTH_SHORT).show();
            }
        }

        return false;
    }

    /**
     * Contrôle les champs, puis appelle l'activité ConvertActivity pour calculer et afficher la conversion
     * @param v
     */
    public void clicConvertir(View v) {
        Log.i(MainActivity.TAG, "clicConvertir");

        // 1 - Contrôle des champs
        if (doConvertir()) {
/*            // Lancer une conversion
            double dblMontantArr = Convert.convertir(strDevDep, strDevArr, dblMontantDep);

            // Afficher le résultat de la conversion
            String msg = dblMontantDep + " " + strDevDep + " = " + dblMontantArr
                    + " " + strDevArr;
            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
/**/
            // 2 - Envoi vers la 2ème activité
            Intent intent = new Intent(this, ConvertActivity.class);

            // Ajout des données à transférer
            intent.putExtra(MainActivity.DEVISE_DEPART, this.strDevDep);
            intent.putExtra(MainActivity.DEVISE_ARRIVEE, this.strDevArr);
            intent.putExtra(MainActivity.MONTANT, this.dblMontantDep);

            startActivity(intent);
            //finish(); // Ferme l'activité
        }
    }

    /**
     * Contrôle les champs, puis appelle l'activité ConvertARActivity pour convertir
     * et retourner le résultat de la conversion
     * @param v
     */
    public void clicConvertirAR(View v) {
        Log.i(TAG, "clicConvertirAR");

        // 1 - Contrôle des champs
        if (doConvertir()) {

            // 2 - Envoi vers la 2ème activité en ALLER-RETOUR
            Intent intent = new Intent(this, ConvertARActivity.class);

            // Ajout des données à transférer
            intent.putExtra(MainActivity.DEVISE_DEPART, this.strDevDep);
            intent.putExtra(MainActivity.DEVISE_ARRIVEE, this.strDevArr);
            intent.putExtra(MainActivity.MONTANT, this.dblMontantDep);

            startActivityForResult(intent, MainActivity.REQUEST_CODE);
        }
    }

    /**
     * Ferme l'application
     * @param v
     */
    public void clicQuitter(View v) {
        Log.i(MainActivity.TAG, "clicQuitter");
        Toast.makeText(getBaseContext(), "clicQuitter", Toast.LENGTH_SHORT).show();
        System.exit(0);
    }

}