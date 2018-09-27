package travel.avg.task1.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import travel.avg.task1.R;

public class WordAdapter extends ArrayAdapter<String> {
    Context context;
    ArrayList<String> values;
    ArrayList<Integer> count;

    public WordAdapter(Context context, ArrayList<String> values, ArrayList<Integer> count) {
        super(context, R.layout.word_layout_adapter, values);

        this.context = context;
        this.values = values;
        this.count = count;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View view, @NonNull final ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.word_layout_adapter, parent, false);
        }

        TextView nameword = view.findViewById(R.id.nameword);
        TextView countword = view.findViewById(R.id.countword);

        nameword.setText(values.get(position).toString());
        countword.setText(count.get(position).toString());

        return view;
    }
}
