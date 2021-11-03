package m.lou.ihmmoney.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.HashMap;
import java.util.Map;

import m.lou.ihmmoney.BddDevises;

public class DevisesMgr {

    public static final String NOM_BDD = "devises";
    public static final Integer VERSION = 1;
    private static SQLiteDatabase bdd;
    private static SQLiteOpenHelper bddDevises;
/*    private SQLiteDatabase bdd;
    private BddDevises bddDevises;*/

    //constructor
/*    public DevisesMgr(){
Log.i("nullfound", "constructor launched  "+String.valueOf(bddDevises));
        bddDevises = new BddDevises(null, NOM_BDD , null, VERSION );
Log.i("nullfound", "constructor OK "+String.valueOf(bddDevises));
    }

    private static void open(){
Log.i("nullfound", "open launched "+String.valueOf(bddDevises));
        bdd = bddDevises.getWritableDatabase();
Log.i("nullfound", "open OK "+String.valueOf(bddDevises));

    }
*/
    public static Map<String, Double> getAll (Context context){
        //init map
        Map<String, Double> devises = new HashMap<String, Double>();

        //init connexion
        bddDevises = new BddDevises(context, NOM_BDD , null, VERSION);
        bdd = bddDevises.getWritableDatabase(); // open();

        //init sql querry
        String sql = "SELECT " + BddDevises.COL_NAME + " as _id,"
                + BddDevises.COL_RATE + " FROM " + BddDevises.TABLE_DEVISES;

        //launch sql querry with cursor
        Cursor cursor = bdd.rawQuery(sql, null);

        //fill map
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String devise = cursor.getString(0);
                    Double rate = cursor.getDouble(cursor.getColumnIndex(BddDevises.COL_RATE));
                    devises.put(devise, rate);

                    Log.e("DEBUG", "mon : " + devise + " / rate " + rate);
                } while (cursor.moveToNext());
            }
        }

        bdd.close();

        return devises;

    }



    public void addDevise(String name, Double rate, Context context){
        //init connexion
        bddDevises = new BddDevises(context, NOM_BDD , null, VERSION);
        bdd = bddDevises.getWritableDatabase(); // open();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("rate", rate);
        bdd.insert("table_devises", null, values);
    }




}

