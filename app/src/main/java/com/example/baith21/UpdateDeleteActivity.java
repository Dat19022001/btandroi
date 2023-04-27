package com.example.baith21;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText title,nd,date;
    private Spinner sp;
    private CheckBox ct;
    private Button update,cancel,remove;

    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
    }

    private void initView() {
        title = findViewById(R.id.title);
        nd = findViewById(R.id.nd);
        date = findViewById(R.id.date);
        sp = findViewById(R.id.spinerCategory);
        ct = findViewById(R.id.ct);
        update = findViewById(R.id.Update);
        remove = findViewById(R.id.Remove);
        cancel = findViewById(R.id.Cancel);
        sp.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.category)));
        date.setOnClickListener(this);
        update.setOnClickListener(this);
        cancel.setOnClickListener(this);
        remove.setOnClickListener(this);
        Intent intent = getIntent();
        item = (Item)intent.getSerializableExtra("item");
        title.setText(item.getTitle());
        nd.setText(item.getNd());
        date.setText(item.getDate());
        int p =0;
        for(int i = 0;i<sp.getCount();i++){
            if(sp.getItemAtPosition(i).toString().equalsIgnoreCase(item.getCategory())){
                p=i;
                break;
            }
        }
        sp.setSelection(p);
        ct.setChecked(item.isCt());
    }

    @Override
    public void onClick(View view) {
        SQLiteHeper db = new SQLiteHeper(this);
        if(view == date){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(UpdateDeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        if(view == update){
            String t = title.getText().toString();
            String nd1 = nd.getText().toString();
            String c = sp.getSelectedItem().toString();
            String d = date.getText().toString();
            boolean ct1 = ct.isChecked();
            if(!t.isEmpty() && !nd1.isEmpty()){
                int id = item.getId();
                Item i = new Item(id,t,c,nd1,d,ct1);
                System.out.println("thanh cong");
                db.update(i);
                finish();
            }
        }
        if (view == remove){
            int id = item.getId();
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thong bao xoa");
            builder.setMessage("ban co muon xoa"+item.getTitle()+"Khong?");
            builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SQLiteHeper bb = new SQLiteHeper(getApplicationContext());
                    bb.delete(id);
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }
}