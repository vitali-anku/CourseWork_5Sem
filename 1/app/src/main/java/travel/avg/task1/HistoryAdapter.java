package travel.avg.task1;

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

public class HistoryAdapter extends ArrayAdapter<String> {
    Context context;
    ArrayList<String> values;


    public HistoryAdapter(Context context, ArrayList<String> values) {
        super(context, R.layout.layout_adapter3, values);

        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View view, @NonNull final ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_adapter3, parent, false);
        }

        final String str = values.get(position);

        TextView date = view.findViewById(R.id.dateHistory);
        TextView time = view.findViewById(R.id.timeHistory);

        date.setText(str.subSequence(0, 5));
        time.setText(str.substring(6, str.length()-3));

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
