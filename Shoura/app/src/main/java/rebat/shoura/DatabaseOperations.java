package rebat.shoura;

/**
 * Created by Maram on 12/02/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOperations extends SQLiteOpenHelper {

    public static final int database_version = 1;
    public String CREATE_QUERY = "CREATE TABLE " + Entities.User.TABLE_NAME + " ("
            + Entities.User.USER_NAME + " TEXT, "
            + Entities.User.USER_EMAIL + " TEXT, "
            + Entities.User.USER_AGE + " TEXT, "
            + Entities.User.USER_GENDER + " TEXT, "
            + Entities.User.USER_STATUS + " TEXT, "
            + Entities.User.USER_JOB + " TEXT )";

    public DatabaseOperations(Context context) {
        super(context, Entities.User.DATABASE_NAME, null, database_version);
        Log.d("Database operations", "Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_QUERY);
        Log.d("Database operations", "Database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS User");
        onCreate(db);

    }

    public void InsertInfo(DatabaseOperations dop, String name, String email, String age, String gender, String status, String job) {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Entities.User.USER_NAME, name);
        cv.put(Entities.User.USER_EMAIL, name);
        cv.put(Entities.User.USER_AGE, age);
        cv.put(Entities.User.USER_GENDER, gender);
        cv.put(Entities.User.USER_STATUS, status);
        cv.put(Entities.User.USER_JOB, job);
        long k = SQ.insert(Entities.User.TABLE_NAME, null, cv);
        Log.d("Database operations", "One raw inserted");
    }

    public Cursor getInfo() {

        SQLiteDatabase dp = this.getWritableDatabase();
        Cursor res = dp.rawQuery("Select * from " + Entities.User.TABLE_NAME, null);
        return res;
    }
}