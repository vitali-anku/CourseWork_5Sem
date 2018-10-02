package travel.avg.task1.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import travel.avg.task1.DateTimeListActivity;
import travel.avg.task1.R;

public class HistoryAdapter extends ArrayAdapter<String> {
    Context context;
    ArrayList<String> values;


    public HistoryAdapter(Context context, ArrayList<String> values) {
        super(context, R.layout.layout_history_adapter, values);

        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View view, @NonNull final ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_history_adapter, parent, false);
        }

        final String str = values.get(position);

        TextView date = view.findViewById(R.id.dateHistory);

        date.setText(str);

        view.findViewById(R.id.qwerty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DateTimeListActivity.class);
                intent.putExtra("key", str);
                context.startActivity(intent);
            }
        });

        return view;
    }
}
