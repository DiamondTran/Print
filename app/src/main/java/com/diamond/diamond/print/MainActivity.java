package com.diamond.diamond.print;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ListBill(View view) {
        startActivity(new Intent(MainActivity.this,ListBillActivity.class));
    }

    public void Thongke(View view) {
    }
}
