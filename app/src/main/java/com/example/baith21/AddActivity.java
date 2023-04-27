package com.example.baith21;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.baith21.dal.SQLiteHeper;
import com.example.baith21.model.Item;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText title,nd,date;
    private Spinner sp;
    private CheckBox ct;
    private Button add,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        inintView();
    }

    private void inintView() {
        title = findViewById(R.id.title);
        nd = findViewById(R.id.nd);
        date = findViewById(R.id.date);
        sp = findViewById(R.id.spinerCategory);
        ct = findViewById(R.id.ct);
        add = findViewById(R.id.Add);
        cancel = findViewById(R.id.Cancel);
        sp.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.category)));
        date.setOnClickListener(this);
        add.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == date){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    String date1="";
                    if(m > 8){
                        date1= d+"/"+(m+1)+"/"+y;
                    }else {
                        date1= d+"/0"+(m+1)+"/"+y;
                    }
                    date.setText(date1);
                }
            },year,month,day);
            dialog.show();
        }
        if(view == cancel){
            finish();
        }
        if (view == add){
            String t = title.getText().toString();
            String nd1 = nd.getText().toString();
            String cate = sp.getSelectedItem().toString();
            String d = date.getText().toString();
            boolean ct1 = ct.isChecked();
            if(!t.isEmpty() && !nd1.isEmpty()){
                Item i = new Item(t,cate,nd1,d,ct1);
                System.out.println("thanh cong");
                SQLiteHeper db = new SQLiteHeper(this);
                db.addItem(i);
                finish();
            }

        }
    }
}