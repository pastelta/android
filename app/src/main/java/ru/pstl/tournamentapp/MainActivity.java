package ru.pstl.tournamentapp;

import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout layoutList;
    private EditText buying_in;
    int counter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutList = findViewById(R.id.layout_list);
        Button buttonAdd = findViewById(R.id.button_add);
        buying_in = findViewById(R.id.buying_in);
        Button buttonUpdate = findViewById(R.id.button_submit_list);
        buttonAdd.setOnClickListener(this);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "getChildCount " + layoutList.getChildCount(), Toast.LENGTH_SHORT).show();
                if (buying_in.getText().length() == 0) buying_in.setText(String.valueOf(0));
                int amount = 0;
                for (int i = 0; i < layoutList.getChildCount(); i++) {
                    TextView e = layoutList.getChildAt(i).findViewById(R.id.entry);
                    EditText b = findViewById(R.id.buying_in);
                    Integer pay_in_res = Integer.parseInt(e.getText().toString()) * Integer.parseInt(b.getText().toString());
                    TextView p_in = layoutList.getChildAt(i).findViewById(R.id.pay_in);
                    p_in.setText(String.valueOf(pay_in_res));
                    amount += pay_in_res;
                }
                Toast toast = Toast.makeText(getApplicationContext(), "Amount: " + amount, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.END, 0, 0);
                toast.show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(buying_in.getText().length() == 0) buying_in.setText(String.valueOf(0));

        addView();
    }
    private void addView(){
            final View PLAYER_VIEW = getLayoutInflater().inflate(R.layout.row_add_player, null, false);

            ImageView imageClose = PLAYER_VIEW.findViewById(R.id.image_remove);

            ImageView imageEntryMin = PLAYER_VIEW.findViewById(R.id.image_entry_min);
            TextView entry = PLAYER_VIEW.findViewById(R.id.entry);
            ImageView imageEntryMax = PLAYER_VIEW.findViewById(R.id.image_entry_max);
            TextView pay_in = PLAYER_VIEW.findViewById(R.id.pay_in);
            pay_in.setText(String.valueOf(Integer.parseInt(buying_in.getText().toString())));

            imageEntryMin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    counter = Integer.parseInt(entry.getText().toString());
                    counter--;
                    entry.setText(String.valueOf(counter));
                    pay_in.setText(String.valueOf(counter * Integer.parseInt(buying_in.getText().toString())));
                }
            });

            imageEntryMax.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    counter = Integer.parseInt(entry.getText().toString());
                    counter++;
                    entry.setText(String.valueOf(counter));
                    pay_in.setText(String.valueOf(counter * Integer.parseInt(buying_in.getText().toString())));
                }
            });

            imageClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeView(PLAYER_VIEW);
                }
            });

            layoutList.addView(PLAYER_VIEW);
        }
    private void removeView(View v){
        layoutList.removeView(v);
    }
}