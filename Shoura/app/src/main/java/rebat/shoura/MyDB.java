package rebat.shoura;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.ByteArrayOutputStream;

/**
 * Created by ACM on 08/02/2017.
 */

public class MyDB  extends SQLiteOpenHelper {
    Bitmap icon;

    public MyDB (Context context) {
        super(context, "ee", null, 2);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ad);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Consultant (username text primary key,email text,password text, name text primary key,field text,bio text, photo BLOB);");
        ContentValues cv = new  ContentValues();
        cv.put("username", "غادة");
        cv.put("email", "shoura.wameedh@gmail.com");
        cv.put("password", "ShouraRebat5");
        cv.put("name", "غادة");
        cv.put("field", "نفسي");
        cv.put("bio", "bio bio bio bio bio bio bio bio bio bio bio bio bio bio bio bio bio bio bio bio");
        cv.put("photo", getBytes(icon));
        db.insert( "Expert", null, cv );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Consultant;");
        onCreate(db);
    }

    public String getUserEmail(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String email = null;
        Cursor res = db.rawQuery("select email from Consultant where username = ?", new String[] {username});
        if (res.moveToFirst()) {
            email = res.getString(0);
        }
        return email;
    }

    public Entities.Consultant getProfile(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Consultant where username = ?", new String[] {name});
        Entities.Consultant consultant = new Entities().new Consultant();

        if (res.moveToFirst()) {
            consultant.Name = res.getString(0);
            consultant.Field = res.getString(1);
            consultant.Bio = res.getString(2);
            consultant.Photo = res.getBlob(3);
        }
        return consultant;
    }
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }
}
