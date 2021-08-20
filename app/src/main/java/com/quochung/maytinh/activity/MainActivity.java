package com.quochung.maytinh.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.quochung.maytinh.R;


public class MainActivity extends AppCompatActivity {

    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonAdd, buttonSub, buttonDivision,
            buttonMul, buttonC, buttondot, buttonEqual;
    EditText hienthi;
    EditText hienthitop;
    float mValueOne, mValueTwo;
    BottomNavigationView lightdarkmode;
    SharedPreferences lightdarksave;
    boolean Maddition, mSubtract, MMul, MDiv;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lightdarkmode();
        calculatorinit();


    }




    @SuppressLint("DefaultLocale")
    private static String fmt(double d) {
        if (d == (long) d)
            return String.format("%d", (long) d);
        else
            return String.format("%s", d);
    }



    private void lightdarkmode(){
        lightdarksave = getSharedPreferences("dbmodedata", MODE_PRIVATE);
        lightdarkmode = findViewById(R.id.lightdark_menu);
        //Check if user select data mode yet, if not, use system default
        if (!lightdarksave.contains("datadb")) {
            int nightModeFlags =
                    this.getResources().getConfiguration().uiMode &
                            Configuration.UI_MODE_NIGHT_MASK;
            SharedPreferences.Editor editor;
            switch (nightModeFlags) {
                case Configuration.UI_MODE_NIGHT_YES:
                    //System default dark
                    lightdarkmode.setSelectedItemId(R.id.darkmode);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = lightdarksave.edit();
                    editor.putString("datadb", "dark");
                    editor.apply();
                    break;
                case Configuration.UI_MODE_NIGHT_NO:
                case Configuration.UI_MODE_NIGHT_UNDEFINED:
                    //System default light
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = lightdarksave.edit();
                    editor.putString("datadb", "light");
                    editor.apply();
                    lightdarkmode.setSelectedItemId(R.id.lightmode);
                    break;
            }

        } else {
            if (lightdarksave.getString("datadb", "Undefined").equals("light")) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                lightdarkmode.setSelectedItemId(R.id.lightmode);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                lightdarkmode.setSelectedItemId(R.id.darkmode);
            }
        }

        lightdarkmode.setOnItemSelectedListener(item -> {
            SharedPreferences.Editor editor = lightdarksave.edit();
            switch(item.getItemId()){
                case R.id.lightmode:

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor.putString("datadb", "light");
                    editor.apply();
                    break;
                case R.id.darkmode:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = lightdarksave.edit();
                    editor.putString("datadb", "dark");

                    editor.apply();
                    break;
            }
            return true;
        });



        }






    @SuppressLint("SetTextI18n")
    private void calculatorinit () {
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonAdd = findViewById(R.id.buttonadd);
        buttonSub = findViewById(R.id.buttonsub);
        buttonMul = findViewById(R.id.buttonmul);
        buttonDivision = findViewById(R.id.buttondiv);
        buttonC = findViewById(R.id.buttonac);
        buttonEqual = findViewById(R.id.buttonequal);
        buttondot = findViewById(R.id.buttondot);
        hienthi = findViewById(R.id.edt1);
        hienthitop = findViewById(R.id.edttop);


        button1.setOnClickListener(v -> hienthi.setText(hienthi.getText() + "1"));

        button2.setOnClickListener(v -> hienthi.setText(hienthi.getText() + "2"));

        button3.setOnClickListener(v -> hienthi.setText(hienthi.getText() + "3"));

        button4.setOnClickListener(v -> hienthi.setText(hienthi.getText() + "4"));

        button5.setOnClickListener(v -> hienthi.setText(hienthi.getText() + "5"));

        button6.setOnClickListener(v -> hienthi.setText(hienthi.getText() + "6"));

        button7.setOnClickListener(v -> hienthi.setText(hienthi.getText() + "7"));

        button8.setOnClickListener(v -> hienthi.setText(hienthi.getText() + "8"));

        button9.setOnClickListener(v -> hienthi.setText(hienthi.getText() + "9"));

        button0.setOnClickListener(v -> hienthi.setText(hienthi.getText() + "0"));

        buttondot.setOnClickListener(v -> hienthi.setText(hienthi.getText() + "."));

        buttonAdd.setOnClickListener(v -> {

            if (hienthi == null) {
                assert false;
                hienthi.setText("0");
            } else {
                mValueOne = Float.parseFloat(hienthi.getText() + "");
                Maddition = true;
                hienthitop.setText(hienthi.getText() + " +");
                hienthi.setText(null);
            }
        });

        buttonSub.setOnClickListener(v -> {
            mValueOne = Float.parseFloat(hienthi.getText() + "");
            mSubtract = true;
            hienthitop.setText(hienthi.getText() + " -");
            hienthi.setText(null);
        });

        buttonMul.setOnClickListener(v -> {
            mValueOne = Float.parseFloat(hienthi.getText() + "");
            MMul = true;
            hienthitop.setText(hienthi.getText() + " x");
            hienthi.setText(null);
        });

        buttonDivision.setOnClickListener(v -> {
            mValueOne = Float.parseFloat(hienthi.getText() + "");
            MDiv = true;
            hienthitop.setText(hienthi.getText() + " /");
            hienthi.setText(null);
        });

        buttonEqual.setOnClickListener(v -> {
            if (!String.valueOf(hienthi.getText()).isEmpty()) {
                mValueTwo = Float.parseFloat(hienthi.getText() + "");
            }



            if (Maddition) {
                hienthi.setText(fmt(mValueOne + mValueTwo) + "");
                hienthitop.setText(fmt(mValueOne) + " " + getResources().getString(R.string.Maytinh_add) + " " + fmt(mValueTwo) + "");
                Maddition = false;
            }

            if (mSubtract) {
                hienthi.setText(fmt(mValueOne - mValueTwo) + "");
                hienthitop.setText(fmt(mValueOne) + " " + getResources().getString(R.string.Maytinh_sub) + " " + fmt(mValueTwo) + "");
                mSubtract = false;
            }

            if (MMul) {
                hienthi.setText(fmt(mValueOne * mValueTwo) + "");
                hienthitop.setText(fmt(mValueOne) + " " + getResources().getString(R.string.Maytinh_mul) + " " + fmt(mValueTwo) + "");
                MMul = false;
            }

            if (MDiv) {
                hienthi.setText(fmt(mValueOne / mValueTwo) + "");
                hienthitop.setText(fmt(mValueOne) + " " + getResources().getString(R.string.Maytinh_div) + " " + fmt(mValueTwo) + "");
                MDiv = false;
            }
        });

        buttonC.setOnClickListener(v -> {
            hienthi.setText("");
            hienthitop.setText("");
        });


    }





}