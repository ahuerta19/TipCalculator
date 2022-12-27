package com.huertaalexis.tipsplitcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText billTot;
    private TextView taxTot;
    private TextView grandTot;
    private EditText splitBill;
    private TextView payPerson;
    private RadioGroup tipSelections;
    public RadioButton twelvePer;
    public RadioButton fifteenPer;
    public RadioButton eighteenPer;
    public RadioButton twentyPer;
    public double ttlTip = 0.0;
    public double gTotal = 0.0;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billTot = findViewById(R.id.billTotal);
        taxTot = findViewById(R.id.tipAmt);
        grandTot = findViewById(R.id.grandTtl);
        splitBill = findViewById(R.id.partySize);
        payPerson =  findViewById(R.id.displaySplit);
        tipSelections = findViewById(R.id.radioGroup);
        twelvePer = findViewById(R.id.twelvePercent);
        fifteenPer = findViewById(R.id.fifteenPercent);
        eighteenPer = findViewById(R.id.eighteenPercent);
        twentyPer = findViewById(R.id.twentyPercent);

    }

    public void doMath(View v){

        String billTStr = billTot.getText().toString();
        if(billTStr.isEmpty()) {
            tipSelections.clearCheck();
            return;
        }
        double billTVal = Double.parseDouble(billTStr);
        //int billTVal = Integer.parseInt(billTStr);

        if (v.getId() == R.id.twelvePercent){
            ttlTip = billTVal * 0.12;
            gTotal = billTVal + ttlTip;
        } else if (v.getId() == R.id.fifteenPercent){
            ttlTip = billTVal * 0.15;
            gTotal = billTVal + ttlTip;
        } else if (v.getId() == R.id.eighteenPercent){
            ttlTip = billTVal * 0.18;
            gTotal = billTVal + ttlTip;
        } else if (v.getId() == R.id.twentyPercent){
            ttlTip = billTVal * 0.20;
            gTotal = billTVal + ttlTip;
        }

        //double tipDisplay = Math.round((ttlTip * 100.0) / 100.0);
        //double grandTotDisplay = Math.round((gTotal * 100.0) / 100.0);
        taxTot.setText(String.format("$" + "%.2f", ttlTip));
        grandTot.setText(String.format("$" + "%.2f", gTotal));
    }

    public void billSplit(View v){
        String billTStr = billTot.getText().toString();
        String sBill = splitBill.getText().toString();
        if(sBill.isEmpty() || sBill.equals("0") || billTStr.isEmpty() || tipSelections.getCheckedRadioButtonId() == -1)
            return;
        double spltBill = Double.parseDouble(sBill);

        double perPerson = gTotal/spltBill;
        perPerson = Math.ceil(perPerson*100.0)/100;

        payPerson.setText(String.format("$" + "%.2f", perPerson));

    }
    public void clearData(View v){
        billTot.setText("");
        taxTot.setText("");
        grandTot.setText("");
        splitBill.setText("");
        payPerson.setText("");
        tipSelections.clearCheck();
        ttlTip = 0.0;
        gTotal = 0.0;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        outState.putString("BILLTOTAL", taxTot.getText().toString());
        outState.putString("GRANDTOTAL", grandTot.getText().toString());
        outState.putString("splitAmnt", payPerson.getText().toString());

        outState.putString("billTot", billTot.getText().toString());
        outState.putString("SPLIT", splitBill.getText().toString());

        outState.putBoolean("12PER", twelvePer.isChecked());
        outState.putBoolean("15PER", fifteenPer.isChecked());
        outState.putBoolean("18PER", eighteenPer.isChecked());
        outState.putBoolean("20PER", twentyPer.isChecked());

        outState.putDouble("TTLTIP", ttlTip);
        outState.putDouble("GTOTAL", gTotal);

        super.onSaveInstanceState(outState);

    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        taxTot.setText(savedInstanceState.getString("BILLTOTAL"));
        grandTot.setText(savedInstanceState.getString("GRANDTOTAL"));
        payPerson.setText(savedInstanceState.getString("splitAmnt"));

        billTot.setText(savedInstanceState.getString("billTot"));
        splitBill.setText(savedInstanceState.getString("SPLIT"));

        twelvePer.setChecked(savedInstanceState.getBoolean("12PER"));
        fifteenPer.setChecked(savedInstanceState.getBoolean("15PER"));
        eighteenPer.setChecked(savedInstanceState.getBoolean("18PER"));
        twentyPer.setChecked(savedInstanceState.getBoolean("20PER"));

        ttlTip = savedInstanceState.getDouble("TTLTIP");
        gTotal = savedInstanceState.getDouble("GTOTAL");
    }



}