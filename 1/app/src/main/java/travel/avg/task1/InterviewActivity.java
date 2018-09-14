package travel.avg.task1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    List<Integer[][]> lst = new ArrayList<>();
    List<Integer> lda = new ArrayList<>();

    Button btn1, btn2;
    int count = 0;
    int current = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);

        for (String asd : ArList.list) {
            lda.add(ArList.list.indexOf(asd));
        }

        show = findViewById(R.id.number);
        btn1 = findViewById(R.id.test1);
        btn2 = findViewById(R.id.test2);

        Victory();
        Collections.shuffle(lst);
        final int nn = Count();

        for(Integer[]anArr : lst.get(0)){
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

//                if (ArList.nam.size() != 0) {
//
//                    for (String a : ArList.nam) {
//                        if (a.equals(btn1.getText().toString())) {
//                            int index = ArList.nam.indexOf(a);
//                            int value = ArList.count.indexOf(index);
//                            value++;
//                            ArList.count.set(index, value);
//                        } else {
//                            ArList.nam.add(btn1.getText().toString());
//                            ArList.nam.add(btn2.getText().toString());
//                            ArList.count.add(ArList.list.indexOf(btn1.getText().toString()), 1);
//                            ArList.count.add(ArList.list.indexOf(btn2.getText().toString()), 0);
//                        }
//                    }
//                } else {
//                    ArList.nam.add(btn1.getText().toString());
//                    ArList.nam.add(btn2.getText().toString());
//                    ArList.count.add(ArList.nam.indexOf(btn1.getText().toString()), 1);
//                    ArList.count.add(ArList.nam.indexOf(btn2.getText().toString()), 0);
//                }
                if(current<nn-1){
                    current++;
                    for(Integer[]anArr : lst.get(current)){
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

//                if (ArList.nam.size() != 0) {
//                    for (String a : ArList.nam) {
//                        if (a.equals(btn2.getText().toString())) {
//                            int index = ArList.nam.indexOf(a);
//                            int value = ArList.count.indexOf(index);
//                            value++;
//                            ArList.count.set(index, value);
//                        } else {
//                            ArList.nam.add(btn1.getText().toString());
//                            ArList.nam.add(btn2.getText().toString());
//                            ArList.count.add(ArList.nam.indexOf(btn1.getText().toString()), 1);
//                            ArList.count.add(ArList.nam.indexOf(btn2.getText().toString()), 0);
//                        }
//                    }
//                }
//                else {
//                    ArList.nam.add(btn1.getText().toString());
//                    ArList.nam.add(btn2.getText().toString());
//                    ArList.count.add(ArList.nam.indexOf(btn1.getText().toString()), 0);
//                    ArList.count.add(ArList.nam.indexOf(btn2.getText().toString()), 1);
//                }
                if(current<nn-1) {
                    current++;
                    for (Integer[] anArr : lst.get(current)) {
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

    public void SetButton(String str1, String str2, int count){
        btn1.setText(str1);
        btn2.setText(str2);
        show.setText("Вопрос " + (current+1) + " из " + count);
    }

    public void Victory() {

        for (int i = 0; i < lda.size() - 1; i++) {
            for (int j = i + 1; j <= lda.size() - 1; j++) {

                Integer[][] asd = new Integer[1][2];
                asd[0][0] = i;
                asd[0][1] = j;

                lst.add(asd);
                count++;
            }
        }
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


}
