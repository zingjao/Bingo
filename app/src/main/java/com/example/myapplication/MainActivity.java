package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.*;

public class MainActivity extends AppCompatActivity {
    //存放數字按鈕陣列
    private ArrayList<Button> bingoBtnList = new ArrayList<>();
    private int[][] matrix;
    private int lines=0,layers=3;
    private Button RandomNumBtn;
    private TableLayout bingoNumLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpOnBind();
        setUpOnClick();
        generateBingoTable(layers);
//        if(checkBingoLine()) showWinDialog();
    }

    private void setUpOnBind(){
        bingoNumLayout = findViewById(R.id.bingoNumLayout);
        RandomNumBtn = findViewById(R.id.RandomNumBtn);
    }

    private void setUpOnClick(){
        for(int i=0 ; i<layers*layers ; i++){
            Button bingoPositionBtn = bingoBtnList.get(i);
            bingoPositionBtn.setOnClickListener(view->{
                //設定顏色
                if(bingoPositionBtn.isSelected()) bingoPositionBtn.setBackgroundResource(R.color.black);
                else bingoPositionBtn.setBackgroundResource(com.google.android.material.R.color.material_dynamic_neutral70);
                if(checkBingoLine()) showWinDialog();
            });
        }
        RandomNumBtn.setOnClickListener(view->{
            setNumOnBtn();
        });
    }

    private void setNumOnBtn(){
        for(int i=0 ; i<layers ; i++){
            for(int j=0 ; j<layers ;j++){
                Toast.makeText(getApplicationContext(),"遊戲開始！",Toast.LENGTH_SHORT);
                //0~8 產生1~25亂數
                bingoBtnList.get(j+(i*3)).setText(""+(int)(Math.random()*24)+1);
            }
        }
    }

    private void generateBingoTable(int layer){
        for(int i=0 ; i<layer ; i++){
            TableRow bingoTableRow = new TableRow(this);
            bingoTableRow.setGravity(Gravity.CENTER);
            //一行產生幾個按鈕
            for(int j=0 ; j<layer ; j++){
                Button btn = new Button(this);
                btn.setGravity(Gravity.CENTER);
                bingoBtnList.add(btn);
                bingoTableRow.addView(btn);
            }
            //一行的樣式&幾層
            bingoNumLayout.addView(bingoTableRow,layer);
        }
    }

    private Boolean checkBingoLine(){
        //橫排、直排、斜排對照
        int columnCheck=0,rowCheck=0,bevelCheck=0;
        //橫排(i對照直的index j對照橫的index)
        for(int i=0;i<layers;i++){
            for(int j=0;j<layers;j++){
                //(3*3=0 1 2 , 3 4 5 , 6 7 8)
                if(!bingoBtnList.get(j+(i*layers)).isSelected()) break;
                else columnCheck+=1;
            }
            if(columnCheck==layers) lines++;
            columnCheck=0;
        }
        //直排
        for(int i=0;i<layers;i++){
            //(3*3=0,3,6 1,4,7 2,5,8)
            for(int j=0;j<layers;j++){
                if(!bingoBtnList.get(i+(j*layers)).isSelected()) break;
                else rowCheck+=1;
            }
            if(rowCheck==layers) lines++;
            rowCheck=0;
        }
        //斜排
        for(int i=0;i<layers;i++){
            //(3*3 = 0,4,8 2,4,7)
            //座標( {0,0} , {1,1} , {2,2} & {0,2} , {1,1} , {2,0} )
            for(int j=0;j<layers;j++){
               if((i+j) == (layers-1) || i==j) bevelCheck+=1;
               else break;
            }
            if(bevelCheck==layers) lines++;
            bevelCheck=0;
        }
        if(lines >=2) return true;
        else return false;
    }

    private void showWinDialog(){
        AlertDialog.Builder winAlertDialog = new AlertDialog.Builder(MainActivity.this);
        winAlertDialog.setTitle("你贏了！");
        winAlertDialog.setMessage("是否繼續？");
        winAlertDialog.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                setNumOnBtn();
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