package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //khai bao cac bien
    TextView tv1,tv2;
    int idButton[] = {R.id.btn_0,R.id.btn_1,R.id.btn_2,R.id.btn_3,R.id.btn_4,R.id.btn_5,R.id.btn_6,
            R.id.btn_7,R.id.btn_8,R.id.btn_9,R.id.btn_ce,R.id.btn_c,R.id.btn_bs,R.id.btn_addition,
            R.id.btn_subtraction,R.id.btn_multiphication,R.id.btn_division,R.id.btn_dau,R.id.btn_cham,R.id.btn_bang};
    double result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ket noi toi view
        tv1 = findViewById(R.id.text_view1);
        tv2 = findViewById(R.id.text_view2);
        for(int i = 0 ; i < idButton.length ; i++) {
            findViewById(idButton[i]).setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        //fomat ket qua tinh duoc theo dinh dang
        DecimalFormat df = new DecimalFormat("###.######");

        //an vao c
        if(id == R.id.btn_c) {
            tv1.setText("");
            tv2.setText("0");
        }
        //an vao ce
        if(id == R.id.btn_ce) {
            tv2.setText("0");
        }
        //an vao bs
        if(id == R.id.btn_bs) {
            int a = Integer.parseInt(tv2.getText().toString());
            tv2.setText(String.valueOf(a/10));
        }
        //an vao so
        for(int i = 0 ; i < 10 ; i++ ) {
            if(id == idButton[i]) {
                String s = tv1.getText().toString();
                //thuc hien 1 phep tinh moi neu truoc do da thuc hien
                if( !s.isEmpty() && s.charAt(s.length()-1) == '=' ) {
                    tv1.setText("");
                    tv2.setText("0");
                }
                int a = Integer.parseInt(tv2.getText().toString());
                if(a == 0)
                    tv2.setText(String.valueOf(i));
                else tv2.append(String.valueOf(i)); //viet lien tuc toan hang hien tai
            }
        }
        //an vao dau +/-
        if ( id == R.id.btn_dau) {
            int a = Integer.parseInt(tv2.getText().toString());
             a = -a;
             tv2.setText(String.valueOf(a));
        }
        //an vao phep tinh
        if(id == R.id.btn_addition) {
            String s = tv1.getText().toString();
            if( !s.isEmpty() ) {
                if( s.charAt(s.length()-1) == '=' ) { //tiep tuc tinh toan voi gia tri cua bien thuc truoc do
                    tv1.setText(tv2.getText().toString() + "+");
                }else {
                    s = s.substring(0, s.length() - 1); //thay doi phep tinh can thuc hien
                    tv1.setText(s + "+");
                }
            }
            else {
                tv1.setText(tv2.getText().toString() + "+"); //thuc hien nhap 1 phep tinh binh thuong
            }
            tv2.setText("0");
        }
        if(id == R.id.btn_subtraction) {
            String s = tv1.getText().toString();
            if( !s.isEmpty() ) {
                if( s.charAt(s.length()-1) == '=' ) {
                    tv1.setText(tv2.getText().toString() + "-");
                }else {
                    s = s.substring(0, s.length() - 1);
                    tv1.setText(s + "-");
                }
            }
            else {
                tv1.setText(tv2.getText().toString() + "-");
            }
            tv2.setText("0");
        }
        if(id == R.id.btn_multiphication) {
            String s = tv1.getText().toString();
            if( !s.isEmpty() ) {
                if( s.charAt(s.length()-1) == '=' ) {
                    tv1.setText(tv2.getText().toString() + "x");
                }else {
                    s = s.substring(0, s.length() - 1);
                    tv1.setText(s + "x");
                }
            }
            else {
                tv1.setText(tv2.getText().toString() + "x");
            }
            tv2.setText("0");
        }
        if(id == R.id.btn_division) {
            String s = tv1.getText().toString();
            if( !s.isEmpty() ) {
                if( s.charAt(s.length()-1) == '=' ) {
                    tv1.setText(tv2.getText().toString() + "/");
                }else {
                    s = s.substring(0, s.length() - 1);
                    tv1.setText(s + "/");
                }
            }
            else {
                tv1.setText(tv2.getText().toString() + "/");
            }
            tv2.setText("0");
        }
        //an dau bang de lay ket qua phep tinh
        if( id == R.id.btn_bang ) {
            String s = tv1.getText().toString();
            if( s.isEmpty() ) {
                tv1.setText(tv2.getText().toString() + "=");
            }else if( s.charAt(s.length()-1) == '=' ){
                tv1.setText(tv2.getText().toString() + "=");
            }else {
                int a = Integer.parseInt(s.substring(0, s.length()-1));
                int b = Integer.parseInt(tv2.getText().toString());
                tv1.setText(s + tv2.getText().toString() + "=");
                switch ( s.charAt(s.length()-1) ){
                    case '+' :
                        result = (a+b); break;
                    case '-' :
                        result = (a-b); break;
                    case 'x' :
                        result = (a*b); break;
                    case '/' :
                        if( b == 0 ) {
                            Toast.makeText(MainActivity.this, "Cannot divide by zero", Toast.LENGTH_LONG).show();
                            //tv2.setText("Cannot divide by zero");
                            return;
                        }
                        else {
                            result = (double)a/b;
                        }
                        break;
                    default:
                        result=a; break;
                }
                tv2.setText(df.format(result));
            }

        }



    }
}
