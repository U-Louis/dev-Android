package m.lou.ihmmoney;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import m.lou.ihmmoney.modele.Convert;


public class MainActivity extends AppCompatActivity {

    //INIT
        public final static String TAG = "MainActivity";
        private ArrayList<String> listeDevises;
        public final static String DEVISE_DEPART = "in";
        public final static String DEVISE_ARRIVEE = "out";
        public final static String MONTANT = "amount";
        public final static int REQUEST_CODE = 1;
        public final static String PREFS = "devisesSet";

        private String strIn = null;
        private String strOut = null;
        private Double dblAmount = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init data from persistence layer
            SharedPreferences CurPrefs = getSharedPreferences(PREFS, MODE_PRIVATE);
            this.strIn = CurPrefs.getString("in", "");
            this.strOut = CurPrefs.getString("out", "");
            this.dblAmount = new Double(CurPrefs.getFloat("amount", 0.0f));
Log.i(TAG, String.valueOf(dblAmount));

        // MAJ le montant
        EditText edtAmount = (EditText) findViewById(R.id.inputAmount);
        edtAmount.setText(this.dblAmount.toString());
Log.i(TAG, String.valueOf(edtAmount.getText()));

        //Init spinners
            chargeDevise(Convert.getConversionTable(this));
            chargeSpinner(R.id.sprIn, this.strIn );
            chargeSpinner(R.id.sprOut, this.strOut);
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        Log.i("Mainactivity", "oncreateOptionmenu");
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("Mainactivity", "onOption");

/*        switch (item.getItemId()) {
            case R.id.item1:
                // action 1
                return true;
            case R.id.item2:
                // action 2
                return true;
            case R.id.item3:
                // action 3
                return true;
        }*/
        return false;
    }

    /*----------------------- METHODS ---------------------------------*/

    public void goToNextActivity(View view){
        Intent intent = new Intent(this, devisesManager.class);
        intent.putExtra("hello","hello nextActivity");
        startActivity(intent);
    }

    private void chargeSpinner(int idSpinner, String devise){
        //Init
            Spinner spinner = (Spinner) findViewById(idSpinner);
            final ArrayAdapter adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item , this.listeDevises);

        //Set
            spinner.setAdapter(adapter);

        //get init pos
            int pos = adapter.getPosition(devise);
            if (pos<0 || pos > spinner.getCount()) pos =0;
            spinner.setSelection(pos);
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
                    this.dblAmount = Double.parseDouble(strAmount); //or Double.valueOf(strAmount);
                    //or double t = Double.parseDouble(((EditText) findViewById(R.id.inputAmount)).getText().toString());
                    double t = (Convert.convertir(strIn, strOut, dblAmount));
                    String res = Double.toString(t);
                    Toast.makeText(getBaseContext(), (String) res, Toast.LENGTH_LONG).show();

                    //output to prefs
                        SharedPreferences CurPrefs = getSharedPreferences(PREFS, MODE_PRIVATE);
                        SharedPreferences.Editor temp = CurPrefs.edit();
                        temp.putString("in", strIn);
                        temp.putString("out", strOut);
                        temp.putFloat("amount", new Float(this.dblAmount));
                        temp.apply();
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
