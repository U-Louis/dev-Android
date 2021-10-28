package m.lou.ihmmoney;

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
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //INIT
        public final static String TAG = "MainActivity";
        private ArrayList<String> listeDevises;
        public final static String DEVISE_DEPART = "in";
        public final static String DEVISE_ARRIVEE = "out";
        public final static String MONTANT = "amount";
        public final static int REQUEST_CODE = 1;

        private String strIn = null;
        private String strOut = null;
        private Double dblAmount = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init spinners
            chargeDevise(Convert.getConversionTable());
            chargeSpinner(R.id.sprIn);
            chargeSpinner(R.id.sprOut);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case MainActivity.REQUEST_CODE:
                if (resultCode == doMyMaths.RESULT_CODE) {
                    String msg = data.getStringExtra("MathsDone");

                    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this,"intent return error",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                Toast.makeText(this,"intent return error",Toast.LENGTH_SHORT).show();
        }
    }

    /*----------------------- METHODS ---------------------------------*/

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

    /**
     * Set and Check inputs for feeding intent towards calculations
     */
    private boolean checkInputs(){
        //GET inputs
        Spinner sprIn = (Spinner) findViewById(R.id.sprIn);
        this.strIn = (String) sprIn.getSelectedItem();

        Spinner sprOut = (Spinner) findViewById(R.id.sprOut);
        this.strOut = (String) sprOut.getSelectedItem();

        EditText etAmount = (EditText) findViewById(R.id.inputAmount);
        String strAmount = etAmount.getText().toString();

        //TEST inputs
        if(this.strIn.equals("") ||
                (this.strOut.equals("") ||
                        (strAmount.equals("")))){
            Log.d(TAG, "@string/missingInput");
            Toast.makeText( getBaseContext(), R.string.missingInput, Toast.LENGTH_SHORT).show();
        }
        else{
            try{
                this.dblAmount = Double.parseDouble(strAmount); //or Double.valueOf(strAmount);
                //or double t = Double.parseDouble(((EditText) findViewById(R.id.inputAmount)).getText().toString());
                return true;
            }
            catch (Exception e){
                Toast.makeText( getBaseContext(), R.string.missingInput, Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }

    /**
     * launch doMyMaths activity
     */
    public void goToDoMyMaths(View v){
        if(checkInputs()){
            Intent intent = new Intent(this, doMyMaths.class);
            intent.putExtra(MainActivity.DEVISE_DEPART, this.strIn);
            intent.putExtra(MainActivity.DEVISE_ARRIVEE, this.strOut);
            intent.putExtra(MainActivity.MONTANT, this.dblAmount);
            startActivityForResult(intent, MainActivity.REQUEST_CODE);
        }
    }
}
