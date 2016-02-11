package co.khanal.todos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhi on 2/10/16.
 */
public class ThingsToDoDataSource {

    private SQLiteDatabase thingsToDoDatabase;
    private KhanalSqliteHelper dbHelper;
    private String[] thingsToDoTableColumns = {
            dbHelper.TABLE_COLUMN_ID,
            dbHelper.TABLE_COLUMN_TITLE,
            dbHelper.TABLE_COLUMN_COMPLETED
    };

    public ThingsToDoDataSource(Context context){
        dbHelper = new KhanalSqliteHelper(context);
    }

    public void open() throws SQLException{
        thingsToDoDatabase = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public ThingToDo addThingToDo(ThingToDo thingToDo){

        ContentValues values = new ContentValues();
        values.put(dbHelper.TABLE_COLUMN_TITLE, thingToDo.getTitle());
        values.put(dbHelper.TABLE_COLUMN_COMPLETED, thingToDo.isCompleted() ? 1 : 0);
        long insertId = thingsToDoDatabase.insert(dbHelper.TABLE_NAME, null, values);
        Cursor cursor = thingsToDoDatabase.query(
                dbHelper.TABLE_NAME,
                thingsToDoTableColumns,
                dbHelper.TABLE_COLUMN_ID+"=" + insertId,
                null, null, null, null
        );

        cursor.close();

        return getThingToDo(insertId);
    }

    public void deleteThingToDo(ThingToDo thingToDo){
        thingsToDoDatabase.delete(dbHelper.TABLE_NAME, dbHelper.TABLE_COLUMN_ID + "=" + thingToDo.getId(), null);
    }

    public int thingsToDoCount(){
        Cursor cursor = thingsToDoDatabase.rawQuery("SELECT * FROM " + dbHelper.TABLE_NAME, null);
        if( cursor != null){
            return cursor.getCount();
        } else {
            return 0;
        }
    }

    public ThingToDo getThingToDo(long id){
        Cursor cursor = thingsToDoDatabase.query(dbHelper.TABLE_NAME, thingsToDoTableColumns, dbHelper.TABLE_COLUMN_ID + "=" + id, null, null, null, null);
        if(cursor != null){
            if(cursor.getCount() == 0)
                Log.v("Source", String.valueOf(id));
            else {
                cursor.moveToFirst();
                ThingToDo todo = new ThingToDo(cursor.getLong(0), cursor.getString(1), cursor.getInt(2) != 0);
                cursor.close();
                return todo;
            }
        }
        return null;
    }

    public List<ThingToDo> getAllThingsToDo(){
        List<ThingToDo> thingsToDo = new ArrayList<ThingToDo>();

        Cursor cursor = thingsToDoDatabase.rawQuery("SELECT * FROM " + dbHelper.TABLE_NAME, null);
        if(cursor != null){
            if(cursor.getCount() == 0){
                thingsToDo.add(new ThingToDo("nothing to do ..."));
                cursor.close();
                return thingsToDo;
            }

            cursor.moveToFirst();
            do {
                thingsToDo.add(new ThingToDo(cursor.getLong(0), cursor.getString(1), cursor.getInt(2) != 0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return thingsToDo;
    }

    public void deleteAllTodos(){
        thingsToDoDatabase.delete(dbHelper.TABLE_NAME, "1", null);
    }

    public ThingToDo updateThingToDo(ThingToDo thingToDo){
        ContentValues values = new ContentValues();
        values.put(dbHelper.TABLE_COLUMN_TITLE, thingToDo.getTitle());
        values.put(dbHelper.TABLE_COLUMN_COMPLETED, thingToDo.isCompleted() ? 1 : 0);
        thingsToDoDatabase.update(dbHelper.TABLE_NAME, values, dbHelper.TABLE_COLUMN_ID + "=" + thingToDo.getId(), null);
        return getThingToDo(thingToDo.getId());
    }
}
