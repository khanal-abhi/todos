package co.khanal.todos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.zip.Inflater;

/**
 * Created by abhi on 2/10/16.
 */
public class TodoAdapter extends ArrayAdapter<ThingToDo> {
    private Context mContext;
    private int layoutResource;
    private ThingToDo[] thingsToDo;

    public TodoAdapter(Context context, int resource, ThingToDo[] objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.layoutResource = resource;
        this.thingsToDo = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        PlaceHolder holder;

        if(row == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(layoutResource,parent,false);

            holder = new PlaceHolder();

            holder.title = (TextView) row.findViewById(R.id.Title);
            holder.completed = (ImageView) row.findViewById(R.id.Completed);

            row.setTag(holder);

        } else {
            holder = (PlaceHolder)row.getTag();
        }

        Long Id = thingsToDo[position].getId();

        holder.title.setText(thingsToDo[position].getTitle());
        holder.completed.setVisibility(thingsToDo[position].isCompleted()? View.VISIBLE: View.INVISIBLE);

        return row;

    }

    private class PlaceHolder{
        TextView title;
        ImageView completed;
    }
}
