package co.khanal.todos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by abhi on 2/10/16.
 */
public class KhanalSqliteHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;

    public static final String TABLE_NAME = "ThingsToDo";
    public static final String TABLE_COLUMN_ID = "_id";
    public static final String TABLE_COLUMN_TITLE = "title";
    public static final String TABLE_COLUMN_COMPLETED = "completed";

    private static final String DATABASE_NAME = "Todos.db";
    private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " +
            TABLE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            TABLE_COLUMN_TITLE + " TEXT NOT NULL," +
            TABLE_COLUMN_COMPLETED + " INTEGER NOT NULL" +
            " );";

    public KhanalSqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS;" + TABLE_NAME;
        db.execSQL(drop);
        onCreate(db);
    }
}
