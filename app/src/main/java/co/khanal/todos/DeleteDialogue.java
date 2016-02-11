package co.khanal.todos;

import android.app.DialogFragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.sql.SQLException;

/**
 * Created by abhi on 2/10/16.
 */
public class DeleteDialogue extends DialogFragment {

    Button YesButton;
    Button NoButton;
    ThingsToDoDataSource thingsToDoDataSource;
    ThingToDo thingToDo;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete_dialog, null);
        setCancelable(false);
        setStyle(STYLE_NO_TITLE, android.R.style.Theme);
        //getDialog().setTitle(thingToDo.getTitle());
        YesButton = (Button) view.findViewById(R.id.YesButton);
        NoButton = ( Button) view.findViewById(R.id.NoButton);

        NoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragmentCompletedListener activity  = (DialogFragmentCompletedListener) getActivity();
                activity.onFinishedDialogFragment(false, null);
                dismiss();
            }
        });

        YesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragmentCompletedListener activity  = (DialogFragmentCompletedListener) getActivity();
                activity.onFinishedDialogFragment(true, thingToDo);
                dismiss();
            }
        });

        return view;
    }

    public void setThingsToDoDataSource(ThingsToDoDataSource thingsToDoDataSource){
        this.thingsToDoDataSource = thingsToDoDataSource;
    }

    public void setThingToDo(ThingToDo thingToDo){
        this.thingToDo = thingToDo;
    }






}
