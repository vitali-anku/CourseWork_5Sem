package travel.avg.task1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import travel.avg.task1.ArList;
import travel.avg.task1.R;

public class Adapter extends ArrayAdapter<String> {

    Context context;
    ArrayList<String> values ;
    Adapter adapter = this;


    public Adapter(Context context, ArrayList<String> states) {
        super(context, R.layout.layout_adapter1, states);

        this.context = context;
        this.values = states;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View view, @NonNull final ViewGroup parent) {

        if(view == null){
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_adapter1, parent, false);
        }

        final TextView text = view.findViewById(R.id.edtext);
        final ImageButton b1 = view.findViewById(R.id.clr);
        final ImageButton b2 = view.findViewById(R.id.edt);
        text.setText(values.get(position));
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                values.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompt, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Введите слово!")
                        .setView(promptsView);
                final EditText word = promptsView.findViewById(R.id.word);
                builder.setCancelable(false)
                        .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                values.set(position, word.getText().toString());
                                text.setText(values.get(position));
                            }
                        })
                        .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        return view;
    }
}
