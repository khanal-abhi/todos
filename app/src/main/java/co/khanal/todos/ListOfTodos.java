package co.khanal.todos;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.List;

public class ListOfTodos extends AppCompatActivity implements DeleteDialogue.DialogFragmentCompletedListener, AddTodo.AddTodoListener {

    Button ToAddATodo;
    ListView ToDoListView;
    ThingsToDoDataSource thingsToDoDataSource;
    ThingToDo[] thingsToDo;

    final static boolean DEBUG = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_todos);

        ToAddATodo = (Button) findViewById(R.id.ToAddAToDo);
        ToDoListView = (ListView) findViewById(R.id.ToDoListView);
        thingsToDoDataSource = new ThingsToDoDataSource(this);
        if(DEBUG)
            firstSetup();
        setThingsToDo();
        TodoAdapter todoAdapter = new TodoAdapter(this, R.layout.row, thingsToDo);

        if(ToDoListView != null){
            ToDoListView.setAdapter(todoAdapter);
            ToDoListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        }

        ToDoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ThingToDo tdd = thingsToDo[position];
                if(tdd.getId() != 0) {
                    if (!tdd.isCompleted()) {
                        tdd.setCompleted(true);
                        updateTodo(tdd);
                    }
                }

            }
        });

        ToDoListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if(thingsToDo[position].getId() != 0 )
                    showDeleteDialog(view, thingsToDo[position]);

                return true;
            }
        });

        ToAddATodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                AddTodo addTodo = new AddTodo();
                addTodo.show(fragmentManager, "ADDTODO");
            }
        });
    }

    public void showDeleteDialog(View view, ThingToDo thingToDo){
        FragmentManager fragmentManager = getFragmentManager();
        DeleteDialogue deleteDialogue = new DeleteDialogue();
        deleteDialogue.setThingsToDoDataSource(thingsToDoDataSource);
        deleteDialogue.setThingToDo(thingToDo);
        deleteDialogue.show(fragmentManager, "DELETEDIALOG");

    }


    public void addThing(ThingToDo thingToDo){
        try{
            thingsToDoDataSource.open();
            thingsToDoDataSource.addThingToDo(thingToDo);
            thingsToDoDataSource.close();
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            thingsToDoDataSource.close();
        }
    }

    public void firstSetup(){
        try{
            thingsToDoDataSource.open();
            thingsToDoDataSource.deleteAllTodos();
            thingsToDoDataSource.close();
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            thingsToDoDataSource.close();
        }
        addThing(new ThingToDo(1, "Wake up", true));
        addThing(new ThingToDo("Go to bathroom"));
        addThing(new ThingToDo("Brush the teeth"));
        addThing(new ThingToDo("Go to work"));
    }

    public void setThingsToDo(){
        try {

            thingsToDoDataSource.open();
            List<ThingToDo> tdds = thingsToDoDataSource.getAllThingsToDo();
            thingsToDoDataSource.close();
            if( tdds.size() > 0 ){
                thingsToDo = tdds.toArray(new ThingToDo[tdds.size()]);
            } else {
                ThingToDo[] thingsToDo1 = {new ThingToDo(-1, "nothing to do ...", true)};
                thingsToDo = thingsToDo1;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            thingsToDoDataSource.close();
        }
    }

    public void updateTodo(ThingToDo thingToDo){
        try{
            thingsToDoDataSource.open();
            thingsToDoDataSource.updateThingToDo(thingToDo);
            thingsToDoDataSource.close();
            refreshList();
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            thingsToDoDataSource.close();
        }
    }

    public void deleteTodo(ThingToDo thingToDo){
        try{
            thingsToDoDataSource.open();
            thingsToDoDataSource.deleteThingToDo(thingToDo);
            thingsToDoDataSource.close();
            refreshList();
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            thingsToDoDataSource.close();
        }
    }

    public void refreshList(){
        setThingsToDo();
        TodoAdapter todoAdapter = new TodoAdapter(this, R.layout.row, thingsToDo);
        if(ToDoListView != null){
            ToDoListView.setAdapter(todoAdapter);
        }
    }

    @Override
    public void onTodoDeleted(boolean deleted, ThingToDo thingToDo) {
        if(deleted){
            deleteTodo(thingToDo);
        }
    }

    @Override
    public void onTodoAdded(String title) {
        addThing(new ThingToDo(title));
        refreshList();
    }
}
