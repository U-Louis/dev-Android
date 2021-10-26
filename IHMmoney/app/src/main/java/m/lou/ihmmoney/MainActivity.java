package m.lou.ihmmoney;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Launch conversion
     * @param v
     */
    public void convert(View v){
        //LOGS
            Log.d(TAG, "btn convert clicked");
            Toast.makeText(getBaseContext(), "convert clicked", Toast.LENGTH_SHORT).show();

        //GET inputs
            Spinner sprIn = (Spinner) findViewById(R.id.sprIn);
            String strIn = (String) sprIn.getSelectedItem();

            Spinner sprOut = (Spinner) findViewById(R.id.sprOut);
            String strOut = (String) sprOut.getSelectedItem();

            EditText inputAmount = (EditText) findViewById(R.id.inputAmount);
            String strAmount = inputAmount.getText().toString();
            double dblAmount = Double.parseDouble(strAmount); //or Double.valueOf(strAmount);
            //or double t = Double.parseDouble(((EditText) findViewById(R.id.inputAmount)).getText().toString());


        //TEST inputs
            if(strIn.equals("") || (strOut.equals(""))){
                Log.d(TAG, "@string/missingInput");
                Toast.makeText( getBaseContext(), "@string/missingInput", Toast.LENGTH_SHORT).show();
            }

    }

    public void quit(View v){
        Log.d(TAG, "btn quit clicked");
        Toast.makeText(getBaseContext(), "quit clicked", Toast.LENGTH_SHORT).show();
    }

}