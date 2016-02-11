package co.khanal.todos;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by abhi on 2/10/16.
 */
public class AddTodo extends DialogFragment {
    Button YesButton;
    Button NoButton;
    EditText Title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setCancelable(false);
        getDialog().setTitle("Add new Todo:");
        View view = inflater.inflate(R.layout.fragment_add_todo,null);
        YesButton = (Button) view.findViewById(R.id.YesButton);
        NoButton = (Button) view.findViewById(R.id.NoButton);
        Title = (EditText) view.findViewById(R.id.Title);

        YesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = Title.getText().toString();
                title = title.trim();
                if(title.length() > 0) {
                    AddTodoListener activity = (AddTodoListener) getActivity();
                    activity.onTodoAdded(title);
                }
                dismiss();
            }
        });

        NoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog =  super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    public interface AddTodoListener{
        public void onTodoAdded(String title);
    }
}
