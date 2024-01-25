package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.*;

public class MainActivity extends AppCompatActivity {
    private List<Integer> RandomNumList = new ArrayList<Integer>();
    private Boolean[] b = {false,false,false,false,false,
                            false,false,false,false};
    private Button BingoBtn1,BingoBtn2,BingoBtn3,BingoBtn4,BingoBtn5,
                    BingoBtn6,BingoBtn7,BingoBtn8,BingoBtn9,RandomNumBtn;
    private int lines=0
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpOnBind();
        setUpOnClick();
        if(checkBingoLine()) showWinDialog();
    }

    public void setUpOnBind(){
        BingoBtn1 = findViewById(R.id.BingoBtn1);
        BingoBtn2 = findViewById(R.id.BingoBtn2);
        BingoBtn3 = findViewById(R.id.BingoBtn3);
        BingoBtn4 = findViewById(R.id.BingoBtn4);
        BingoBtn5 = findViewById(R.id.BingoBtn5);
        BingoBtn6 = findViewById(R.id.BingoBtn6);
        BingoBtn7 = findViewById(R.id.BingoBtn7);
        BingoBtn8 = findViewById(R.id.BingoBtn8);
        BingoBtn9 = findViewById(R.id.BingoBtn9);
        RandomNumBtn = findViewById(R.id.RandomNumBtn);
    }

    public void setUpOnClick(){
        BingoBtn1.setOnClickListener((View.OnClickListener) this);
        BingoBtn2.setOnClickListener((View.OnClickListener) this);
        BingoBtn3.setOnClickListener((View.OnClickListener) this);
        BingoBtn4.setOnClickListener((View.OnClickListener) this);
        BingoBtn5.setOnClickListener((View.OnClickListener) this);
        BingoBtn6.setOnClickListener((View.OnClickListener) this);
        BingoBtn7.setOnClickListener((View.OnClickListener) this);
        BingoBtn8.setOnClickListener((View.OnClickListener) this);
        BingoBtn9.setOnClickListener((View.OnClickListener) this);
        RandomNumBtn.setOnClickListener((View.OnClickListener) this);
//        if(RandomNumBtn.on)
    }
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.RandomNumBtn: setNumOnBtn(); break;
        }
    }

    public void setNumOnBtn(){
        for(int i=0 ; i<9 ;i++) RandomNumList.add((int)Math.random()*100/4);
    }

    public Boolean checkBingoLine(){
        //if() lines+1
        if(lines ==2) return true;
    }

    public void showWinDialog(){
        AlertDialog.Builder winAlertDialog = new AlertDialog.Builder(MainActivity.this);
        winAlertDialog.setTitle("你贏了！");
        winAlertDialog.setMessage("是否繼續？");
        winAlertDialog.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setNumOnBtn();
            }
        });
        winAlertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        winAlertDialog.show();
    }
}