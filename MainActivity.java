package com.wecodebloggers.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button [][] buttons= new Button[3][3];
    private boolean player1trun=true;
    private int player_1score;
    private int player_2score;
    private TextView txtviewplayer1,txtviewplayer2;
    private int lapCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtviewplayer1=findViewById(R.id.txtview_player1);
        txtviewplayer2=findViewById(R.id.txtview_player2);

        //for get tile id as 2D array format
        for(int i=0;i<3;i++){
            for (int j =0;j<3;j++){
                String buttonId ="tile_"+i+j; //carefull while giving name for tile
                int resourceId=getResources().getIdentifier(buttonId,"id",getPackageName());
                buttons[i][j]=findViewById(resourceId);
                View.OnClickListener onClickListener =this;
                buttons[i][j].setOnClickListener(onClickListener);

            }
        }
        Button buttonrestart =findViewById(R.id.btn_restart);
        buttonrestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();// lets code later
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (!((Button)v).getText().toString().equals("")){
            return;
        }
        if (player1trun) {
            ((Button) v).setText("X");
        } else{
                ((Button)v).setText("O");
            }
        lapCount++;

        if (WinPossibility()){
            if (player1trun){
                palyer1Wins();
            }else{
                player2Wins();
            }
        }else if (lapCount==9){ //lapcount is tile count ie =9 tiles
            draw();

        }else{
            player1trun=!player1trun;
        }
    }

    private boolean WinPossibility() {
        String[][]field = new  String[3][3];
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                field[i][j]=buttons[i][j].getText().toString();
            }
        }
        //for horizantal chechking (1,2,3 & 4,5,6 &7,8,9)
        for (int i=0;i<3;i++){
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")){
                    return true;
            }
        }
        //for vertical checking (1,4,7 &2,5,8&3,6,9)
        for (int i= 0;i<3;i++){
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")){
                return true;
            }
        }
        //diognal tile(1,5,9)
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")){
            return true;
        }
        //diagonal tile(3.5.7)
        if (field[0][2].equals(field[1][1])
                &&field[0][2].equals(field[2][0])
                && !field[0][2].equals("")){
            return true;
        }
        return false;
    }
    private void palyer1Wins(){
        player_1score++;
        ShowMessage("Congrats","Player 1 Won");
        updatePoints();
        resetAll();
    }
    private  void player2Wins(){
        player_2score++;
        ShowMessage("Congrats","Player 2 Won");
        updatePoints();
        resetAll();

    }
    private void draw(){
        ShowMessage("OOP's","Match Draw");
        resetAll();
    }
    private void updatePoints(){
        txtviewplayer1.setText("Player 1 : "+player_1score);
        txtviewplayer2.setText("Player 2 : "+player_2score);
    }
    private void resetAll(){
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                buttons[i][j].setText("");
            }
        }
        lapCount =0;
        player1trun=true;

    }
    private void restartGame(){
      player_1score=0;
      player_2score=0;
      updatePoints();
      resetAll();
    }
    private void ShowMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
