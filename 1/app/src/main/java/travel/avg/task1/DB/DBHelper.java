package travel.avg.task1.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME  = "MyDB";
    public static final String tableName1 = "List";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + tableName1
                + "(id_lsit INTEGER primary key,"
                + "date TEXT,"
                + "name TEXT,"
                + "count INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addList(String date, String name, Integer count){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("date", date);
        cv.put("name", name);
        cv.put("count", count);

        Long rowID = db.insert(tableName1, null, cv);

        if (rowID == -1) {
            Log.d("Input Database: ","Sorry, such ID already exists");
        } else {
            Log.d("Input Database: " , "row student student inserted, ID = " + rowID);
        }
        cv.clear();
    }
}
