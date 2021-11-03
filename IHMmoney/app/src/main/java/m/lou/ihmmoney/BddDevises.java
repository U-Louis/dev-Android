package m.lou.ihmmoney;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BddDevises extends SQLiteOpenHelper {

    public static final String TABLE_DEVISES = "table_devises";
    public static final String COL_ID = "ID";
    public static final String COL_NAME = "name";
    public static final String COL_RATE = "rate";

    private static final String CREATE_BDD =
            "CREATE TABLE " + TABLE_DEVISES + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NAME + " TEXT NOT NULL,"
            + COL_RATE + " DOUBLE NOT NULL);";


    public BddDevises(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase bddDevise) {
        bddDevise.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bddDevise, int oldVersion, int newVersion) {
        bddDevise.execSQL("DROP TABLE " + TABLE_DEVISES + ";");
        this.onCreate(bddDevise);
    }
}
