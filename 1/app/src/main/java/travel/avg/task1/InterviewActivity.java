package travel.avg.task1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class InterviewActivity extends AppCompatActivity {

    int n = ArList.list.size();

    TextView show;

    Button btn1, btn2;
    int current = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);
        if(savedInstanceState!=null){
            current=savedInstanceState.getInt("current");
        }else {
            current=0;
            ArList.lst.clear();
            ArList.lda.clear();
            ArList.NewMethod();
            ArList.Victory();
            ArList.Sort();
        }

        show = findViewById(R.id.number);
        btn1 = findViewById(R.id.test1);
        btn2 = findViewById(R.id.test2);

        final int nn = Count();

        for(Integer[]anArr : ArList.lst.get(current)){
            SetButton(ArList.list.get(anArr[0]), ArList.list.get(anArr[1]), nn);
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = btn1.getText().toString();
                String str2 = btn2.getText().toString();
                if(ArList.l.size() != 0){
                    if(!ArList.l.containsKey(str1)){
                        ArList.l.put(str1, 1);
                    }
                    else {
                        int value = ArList.l.get(str1);
                        value++;
                        ArList.l.put(str1, value);
                    }
                    if(!ArList.l.containsKey(str2)){
                        ArList.l.put(str2, 0);
                    }
                }
                else {
                    ArList.l.put(str1, 1);
                    ArList.l.put(str2, 0);
                }
                if(current<nn-1){
                    current++;
                    for(Integer[]anArr : ArList.lst.get(current)){
                        SetButton(ArList.list.get(anArr[0]), ArList.list.get(anArr[1]), nn);
                    }
                }
                else if(current==nn-1){
                    Intent intent1 = new Intent(InterviewActivity.this, LastActivity.class);
                    startActivity(intent1);
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = btn1.getText().toString();
                String str2 = btn2.getText().toString();
                if(ArList.l.size() != 0){
                    if(!ArList.l.containsKey(str2)){
                        ArList.l.put(str2, 1);
                    }
                    else {
                        int value = ArList.l.get(str2);
                        value++;
                        ArList.l.put(str2, value);
                    }
                    if(!ArList.l.containsKey(str1)){
                        ArList.l.put(str1, 0);
                    }
                }
                else {
                    ArList.l.put(str1, 0);
                    ArList.l.put(str2, 1);
                }

                if(current<nn-1) {
                    current++;
                    for (Integer[] anArr : ArList.lst.get(current)) {
                        SetButton(ArList.list.get(anArr[0]), ArList.list.get(anArr[1]), nn);
                    }
                }
                else if(current == nn-1){
                    Intent intent2 = new Intent(InterviewActivity.this, LastActivity.class);
                    startActivity(intent2);
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("current", current);
    }

    public void SetButton(String str1, String str2, int count){
        btn1.setText(str1);
        btn2.setText(str2);
        show.setText("Вопрос " + (current+1) + " из " + count);
    }

    public int Count(){

        int count = 0;
        int das = ArList.list.size();

        for(int i=0; i<das-1; i++){
            for (int j=i+1; j<=das-1; j++){
                count++;
            }
        }

        return count;
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
}
