package travel.avg.task1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> list = new ArrayList<>();
    ListView listView;
//    Button btn1, btn2;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
        listView = findViewById(R.id.listView1);

        adapter = new Adapter(this, ArList.list);
        if (ArList.list.size() != 0) {
            listView.setAdapter(adapter);
        }

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(MainActivity.this);
                View promptsView = li.inflate(R.layout.prompt, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Введите слово!")
                        .setView(promptsView);
                final EditText word = promptsView.findViewById(R.id.word);
                builder.setCancelable(false)
                        .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String str = word.getText().toString();
                                if(!str.toString().equals("")) {
                                    list.add(str);
                                    ArList.list.add(str);
                                    listView.setAdapter(adapter);
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Неверный ввод", Toast.LENGTH_SHORT).show();
                                }
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
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ArList.list.size()<3||list.size()>25){
                    Toast.makeText(getApplicationContext(), "Нужно от 3 до 25 элементов в списке!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(MainActivity.this, InterviewActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            return false;
        }
        else{
            return true;
        }
    }

    //    public void OnClick(View view){
//        //adapter = new Adapter(MainActivity.this, ArList.list);
//        switch (view.getId()){
//            case R.id.btn1:
//                LayoutInflater li = LayoutInflater.from(MainActivity.this);
//                View promptsView = li.inflate(R.layout.prompt, null);
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setTitle("Введите слово!")
//                        .setView(promptsView);
//                final EditText word = promptsView.findViewById(R.id.word);
//                builder.setCancelable(false)
//                        .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                String str = word.getText().toString();
////                                list.add(str);
//                                ArList.list.add(str);
//                            }
//                        })
//                        .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.cancel();
//                            }
//                        });
//                AlertDialog alert = builder.create();
//                alert.show();
//                listView.setAdapter(adapter);
//
//                break;
//            case R.id.btn2:
//                if(ArList.list.size()<3||list.size()>25){
//                    Toast.makeText(getApplicationContext(), "Нужно от 3 до 25 элементов в списке!", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Intent intent = new Intent(MainActivity.this, InterviewActivity.class);
//                    startActivity(intent);
//                }
//                break;
//        }
//    }
}
