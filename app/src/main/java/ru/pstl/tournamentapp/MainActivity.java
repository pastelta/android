package ru.pstl.tournamentapp;

import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout layoutList;
    private Button buttonAdd;
    private Button buttonUpdate;
    private Integer counter = 1;
    private EditText buying_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutList = findViewById(R.id.layout_list);
        buttonAdd = findViewById(R.id.button_add);
        buying_in = (EditText) findViewById(R.id.buying_in);
        buttonUpdate = findViewById(R.id.button_submit_list);
        buttonAdd.setOnClickListener(this);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "getChildCount " + layoutList.getChildCount(), Toast.LENGTH_SHORT).show();
                if(buying_in.getText().length() == 0) buying_in.setText(String.valueOf(0));

                for(int i = 0; i<layoutList.getChildCount(); i++){
                    TextView e = layoutList.getChildAt(i).findViewById(R.id.entry);
                    EditText b = ((EditText) findViewById(R.id.buying_in));
                    Integer pay_in_res = Integer.parseInt(e.getText().toString()) * Integer.parseInt(b.getText().toString());
                    TextView p_in = layoutList.getChildAt(i).findViewById(R.id.pay_in);
                    p_in.setText(String.valueOf(pay_in_res));
                }
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
            EditText editText = (EditText) PLAYER_VIEW.findViewById(R.id.edit_player_name);
            ImageView imageClose = (ImageView) PLAYER_VIEW.findViewById(R.id.image_remove);

            ImageView imageEntryMin = (ImageView) PLAYER_VIEW.findViewById(R.id.image_entry_min);
            TextView entry = PLAYER_VIEW.findViewById(R.id.entry);
            ImageView imageEntryMax = (ImageView) PLAYER_VIEW.findViewById(R.id.image_entry_max);
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