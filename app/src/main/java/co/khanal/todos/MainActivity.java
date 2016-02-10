package co.khanal.todos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ThingsToDoDataSource ds = new ThingsToDoDataSource(this);
        try {
            ds.open();
            ds.addThingToDo(new ThingToDo(0, "Hello world!", false));
            ds.addThingToDo(new ThingToDo(1, "Hola world!", false));
            ds.addThingToDo(new ThingToDo(2, "Hello amigo!", false));
            Log.v("Main", String.valueOf(ds.thingsToDoCount()));
            Log.v("Main", ds.getAllThingsToDo().toString());
            ThingToDo td = ds.getAllThingsToDo().get(0);
            Log.v("Main", td.toString());
            ds.deleteAllTodos();
            Log.v("Main", String.valueOf(ds.thingsToDoCount()));


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ds.close();
        }
        //ds.addThingToDo(new ThingToDo(75, "Hello world!", false));
        //System.out.write(ds.thingsToDoCount());

    }
}
