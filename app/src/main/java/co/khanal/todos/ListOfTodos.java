package co.khanal.todos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.List;

public class ListOfTodos extends AppCompatActivity {

    Button ToAddATodo;
    ListView ToDoListView;
    ThingsToDoDataSource thingsToDoDataSource;
    ThingToDo[] thingsToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_todos);

        ToAddATodo = (Button) findViewById(R.id.ToAddAToDo);
        ToDoListView = (ListView) findViewById(R.id.ToDoListView);
        thingsToDoDataSource = new ThingsToDoDataSource(this);
        firstSetup();
        setThingsToDo();
        TodoAdapter todoAdapter = new TodoAdapter(this, R.layout.row, thingsToDo);

        if(ToDoListView != null){
            ToDoListView.setAdapter(todoAdapter);
        }

        ToDoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ThingToDo tdd = thingsToDo[position];
                Toast.makeText(getApplicationContext(),tdd.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void addThings(ThingToDo thingToDo, ThingsToDoDataSource ds){
        ds.addThingToDo(thingToDo);
    }

    public void firstSetup(){
        try{
            thingsToDoDataSource.open();
            thingsToDoDataSource.deleteAllTodos();
            addThings(new ThingToDo(1, "Wake up", true), thingsToDoDataSource);
            addThings(new ThingToDo("Go to bathroom"), thingsToDoDataSource);
            addThings(new ThingToDo("Brush the teeth"), thingsToDoDataSource);
            addThings(new ThingToDo("Go to work"), thingsToDoDataSource);
            thingsToDoDataSource.close();
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            thingsToDoDataSource.close();
        }
    }

    public void setThingsToDo(){
        try {

            thingsToDoDataSource.open();
            List<ThingToDo> tdds = thingsToDoDataSource.getAllThingsToDo();
            thingsToDoDataSource.close();
            if( tdds.size() > 0 ){
                thingsToDo = tdds.toArray(new ThingToDo[tdds.size()]);
            } else {
                ThingToDo[] thingsToDo1 = {new ThingToDo("nothing to do ...")};
                thingsToDo = thingsToDo1;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            thingsToDoDataSource.close();
        }
    }
}
