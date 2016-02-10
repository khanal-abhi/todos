package co.khanal.todos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListOfTodos extends AppCompatActivity {

    Button ToAddATodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_todos);

        ToAddATodo = (Button) findViewById(R.id.ToAddAToDo);
    }

    public View.OnClickListener ToAddAToDoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
        }
    };
}
