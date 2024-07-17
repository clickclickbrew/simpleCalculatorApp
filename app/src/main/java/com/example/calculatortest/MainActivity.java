package com.example.calculatortest;
//Created during Android Java Masterclass - Become an App Developer by Tim Buchalka
//Initially created Feb 2022 by David Smith
//Last update July 16 2024

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
boolean cMem; //Tracks if we have a value in memory
String opMem;
EditText res;
EditText inp;
TextView op;
boolean anm;
ArrayList<Button> nums; //Array of the number buttons
ArrayList<Button> ops; //Array of the operational buttons

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declare display fields
        String opMem = "";
        res = (EditText) findViewById(R.id.result);
        inp = (EditText) findViewById(R.id.inNum);
        op = (TextView) findViewById(R.id.oper);
        inp.setText("");
        res.setText("");
        op.setText("");
        anm = false;

        //Declare ArrayLists of numbers and operations
        nums = new ArrayList<Button>();
        ops = new ArrayList<Button>();

        //OnClickListeners that call calculatron with arguments gleamed from button ID
        View.OnClickListener opOCL = new View.OnClickListener(){
            //This will pass the last three letters of button ID as a String
            public void onClick(View view){
                String bName = getResources().getResourceName(view.getId());
                calcutron5000(bName.substring(bName.length() - 3));
            }
        };
        View.OnClickListener numOCL = new View.OnClickListener(){
            //This will pass the 1 digit number at the end of button ID as an int
            public void onClick(View view){
                String bName = getResources().getResourceName(view.getId());
                calcutron5000(Integer.parseInt(bName.substring(bName.length() - 1)));
            }
        };

        //Adding the buttons to the array
        nums.add((Button)  findViewById(R.id.num1));
        nums.add((Button)  findViewById(R.id.num2));
        nums.add((Button)  findViewById(R.id.num3));
        nums.add((Button)  findViewById(R.id.num4));
        nums.add((Button)  findViewById(R.id.num5));
        nums.add((Button)  findViewById(R.id.num6));
        nums.add((Button)  findViewById(R.id.num7));
        nums.add((Button)  findViewById(R.id.num8));
        nums.add((Button)  findViewById(R.id.num9));
        nums.add((Button)  findViewById(R.id.num0));
        ops.add((Button) findViewById(R.id.opAdd));
        ops.add((Button) findViewById(R.id.opSub));
        ops.add((Button) findViewById(R.id.opMul));
        ops.add((Button) findViewById(R.id.opDiv));
        ops.add((Button) findViewById(R.id.opClr));
        ops.add((Button) findViewById(R.id.opFin));

        //Setting the onClickListener for all Buttons in ArrayList
        nums.forEach((n) -> n.setOnClickListener(numOCL));
        ops.forEach((n) -> n.setOnClickListener(opOCL));
        }

    //Called when a number button is pressed.
    //Parameter is number of button pressed
    //Updates the input display with the number.
    public void calcutron5000(int num){
        ((Button) findViewById(R.id.opClr)).setText("C");
        Log.d("VAL","num" + String.valueOf(num));
        anm = false;
        String t = inp.getText().toString();
        if(t.equals("")){
            inp.setText(String.valueOf(num));
            return;
        }else{
            inp.setText(t + String.valueOf(num));
        }
    }
    //Called when operation button is pressed
    //Parameter is name of operation button pressed
    //Takes action related to button
    public void calcutron5000(String opin){
        Log.d("VAL", opin);
        if(opin.equals("Fin")){
            resolver(); return;
        }
        if(opin.equals("Clr")){
            if(((Button) findViewById(R.id.opClr)).getText().toString().equals("C")){
                inp.setText("");
                ((Button) findViewById(R.id.opClr)).setText("A"); return;
            }
            else{
                op.setText(""); res.setText("");
                ((Button) findViewById(R.id.opClr)).setText("C"); return;
            }
        }
        if(!anm){ swip(); }
        if (opin.equals("Div")) {
            op.setText("/"); return;
        }
        if (opin.equals("Mul")) {
            op.setText("*"); return;
        }
        if (opin.equals("Add")) {
            op.setText("+"); return;
        }
        if (opin.equals("Sub")) {
            op.setText("-"); return;
        }
    }
    //What happens when equals button is pressed
    public void resolver(){
        if(op.getText().toString().equals("")){ return; }
        Log.d("VAL", "op is \"" + op.getText().toString() + "\"");
        anm = true;
        //If one of the numbers hasn't been entered, clear the operator and return
        if(res.getText().toString().equals("") || inp.getText().toString().equals("")){ op.setText(""); return;}
        Double result;
        Double numA = Double.parseDouble(res.getText().toString()); Double numB = Double.parseDouble(inp.getText().toString());
        if (op.getText().toString().equals("/")) {
            result = numA / numB;
        }
        else if (op.getText().toString().equals("*")) {
            result = numA * numB;
        }
        else if (op.getText().toString().equals("+")) {
            result = numA + numB;
        }
        else if (op.getText().toString().equals("-")) {
            result = numA - numB;
        }
        else{ //If an operation wasn't found, return
            return;
        }
        if(result == 0 || (result%1) == 0){
            res.setText(String.valueOf(result.intValue()));
        }else{
            res.setText(String.valueOf(result));
        }
        inp.setText(""); op.setText("");
    }

    public void swip(){
        res.setText(inp.getText());
        inp.setText("");
    }
    }