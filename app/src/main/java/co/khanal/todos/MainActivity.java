package co.khanal.todos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    Button ToListOfTodos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToListOfTodos = (Button) findViewById(R.id.ToListOfTodos);
        ToListOfTodos.setOnClickListener(toListOfTodosListener);
    }

    View.OnClickListener toListOfTodosListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Intent intent = new Intent(getBaseContext(),);
        }
    };
}
