package m.lou.ihmmoney;

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
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //INIT
        public final static String TAG = "MainActivity";
        private ArrayList<String> listeDevises;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init spinners
            chargeDevise(Convert.getConversionTable());
            chargeSpinner(R.id.sprIn);
            chargeSpinner(R.id.sprOut);

    }

    public void goToNextActivity(View view){
        Intent intent = new Intent(this, nextActivity.class);
        intent.putExtra("hello","hello nextActivity");
        startActivity(intent);
    }

    private void chargeSpinner(int idSpinner){
        //Init
            Spinner spinner = (Spinner) findViewById(idSpinner);
            final ArrayAdapter adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item , this.listeDevises);

        //Set
        spinner.setAdapter(adapter);
    }

    private void chargeDevise(Map<String, Double> arr){
        //transform into arrayList
            this.listeDevises = new ArrayList<String>(arr.keySet());

        //add empty input
            this.listeDevises.add("");
        //sort
            Collections.sort(this.listeDevises);
    }


    /**
     * Launch conversion
     * @param v
     */
    public void convert(View v){

        //LOGS
//            Log.d(TAG, "btn convert clicked");
//            Toast.makeText(getBaseContext(), "convert clicked", Toast.LENGTH_SHORT).show();

        //GET inputs
            Spinner sprIn = (Spinner) findViewById(R.id.sprIn);
            String strIn = (String) sprIn.getSelectedItem();

            Spinner sprOut = (Spinner) findViewById(R.id.sprOut);
            String strOut = (String) sprOut.getSelectedItem();

            EditText etAmount = (EditText) findViewById(R.id.inputAmount);
            String strAmount = etAmount.getText().toString();

        //TEST inputs
            if(strIn.equals("") ||
                    (strOut.equals("") ||
                            (strAmount.equals("")))){
                    Log.d(TAG, "@string/missingInput");
                    Toast.makeText( getBaseContext(), R.string.missingInput, Toast.LENGTH_SHORT).show();
                }
            else{
                try{
                    double dblAmount = Double.parseDouble(strAmount); //or Double.valueOf(strAmount);
                    //or double t = Double.parseDouble(((EditText) findViewById(R.id.inputAmount)).getText().toString());
                    double t = (Convert.convertir(strIn, strOut, dblAmount));
                    String res = Double.toString(t);
                    Toast.makeText(getBaseContext(), (String) res, Toast.LENGTH_LONG).show();

                }
                catch (Exception e){
                    Toast.makeText( getBaseContext(), R.string.missingInput, Toast.LENGTH_SHORT).show();
                }
            }



    }

    /**
     * leave app
     * @param v
     */
    public void quit(View v){
//        Log.d(TAG, "btn quit clicked");
//        Toast.makeText(getBaseContext(), "quit clicked", Toast.LENGTH_SHORT).show();
        System.exit(0);
    }

}