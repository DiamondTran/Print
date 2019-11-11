package com.diamond.diamond.print;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.mazenrashed.printooth.Printooth;
import com.mazenrashed.printooth.data.printable.Printable;
import com.mazenrashed.printooth.data.printable.RawPrintable;
import com.mazenrashed.printooth.data.printable.TextPrintable;
import com.mazenrashed.printooth.data.printer.DefaultPrinter;
import com.mazenrashed.printooth.ui.ScanningActivity;
import com.mazenrashed.printooth.utilities.Printing;
import com.mazenrashed.printooth.utilities.PrintingCallback;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class InforBillActivity extends AppCompatActivity implements PrintingCallback {
private TextView txtname,txtPair;
Printing printing;
Button btnPrint,btnTt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_bill);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtname= findViewById(R.id.txtname);
        txtPair= findViewById(R.id.pair);
         btnPrint= findViewById(R.id.btnPrint);
        txtname.setText(getIntent().getStringExtra("name"));


        if (printing !=null){
            printing.setPrintingCallback(this);
            //event
            txtPair.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Printooth.INSTANCE.hasPairedPrinter()){
                        Printooth.INSTANCE.removeCurrentPrinter();

                    }else {
                        startActivityForResult(new Intent(InforBillActivity.this, ScanningActivity.class),ScanningActivity.SCANNING_FOR_PRINTER);
                     changePairAndUnPair();
                    }
                }
            });

            btnPrint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!Printooth.INSTANCE.hasPairedPrinter())
                        startActivityForResult(new Intent(InforBillActivity.this, ScanningActivity.class),ScanningActivity.SCANNING_FOR_PRINTER);
                     else
                         printbill();
                }
            });
        }
    }

    private void printbill() {
        ArrayList<Printable> printables = new ArrayList<>();
        printables.add(new RawPrintable.Builder(new byte[]{27,100,4}).build());

        //setText to print
        printables.add(new TextPrintable.Builder().setText(String.valueOf(txtname))
        .setCharacterCode(DefaultPrinter.Companion.getCHARCODE_PC1252())
        .setNewLinesAfter(1)
        .build());

        //custom text
        printables.add(new TextPrintable.Builder()
        .setText("Hello")
        .setLineSpacing(DefaultPrinter.Companion.getLINE_SPACING_60())
        .setAlignment(DefaultPrinter.Companion.getALIGNMENT_CENTER())
        .setEmphasizedMode(DefaultPrinter.Companion.getEMPHASIZED_MODE_BOLD())
        .setUnderlined(DefaultPrinter.Companion.getUNDERLINED_MODE_ON())
        .setNewLinesAfter(1)
        .build());


        printing.print(printables);
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

    }

    private void changePairAndUnPair() {
        if (Printooth.INSTANCE.hasPairedPrinter())
          txtPair.setText(new StringBuilder("UnPair ").append(Printooth.INSTANCE.getPairedPrinter()
          .getName()).toString());
        else
            txtPair.setText("Pair with Printer");
    }


    @Override
    public void connectingWithPrinter() {
        Toast.makeText(this, "Connecting to printer", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void connectionFailed(String s) {
        Toast.makeText(this, "Failed: "+s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String s) {
        Toast.makeText(this, "Lỗi: "+s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void printingOrderSentSuccessfully() {
        Toast.makeText(this, "Order sent to printer", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ScanningActivity.SCANNING_FOR_PRINTER &&
        resultCode == Activity.RESULT_OK)
            intPrinting();
        changePairAndUnPair();
    }

    private void intPrinting() {
        if (!Printooth.INSTANCE.hasPairedPrinter())
         printing = Printooth.INSTANCE.printer();
        if (printing != null)
            printing.setPrintingCallback(this);
    }
}
