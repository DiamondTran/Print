package com.diamond.diamond.print;

import android.os.Bundle;

import com.diamond.diamond.print.adapter.AdapterBill;
import com.diamond.diamond.print.model.Bill;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

public class ListBillActivity extends AppCompatActivity {
private RecyclerView recyclerView;
private AdapterBill adapterBill;
private LinearLayoutManager linearLayoutManager;
private List<Bill> bills = new ArrayList<>();
Bill bill = new Bill();
String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bill);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            recyclerView = findViewById(R.id.recyclerview);
            for(int i = 0; i < 10; i++){
                name= ""+i;
            }
        bill.setID(name);
        bills.add(bill);

           adapterBill= new AdapterBill(bills,this);
            linearLayoutManager= new LinearLayoutManager(this);
             recyclerView.setLayoutManager(linearLayoutManager);
             recyclerView.setAdapter(adapterBill);
    }

}
