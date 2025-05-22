package com.example.miniproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView budgetText;
    private CheckBox betCar1, betCar2, betCar3;
    private SeekBar car1, car2, car3;
    private EditText betAmountCar1, betAmountCar2, betAmountCar3;
//    String totalBet ;
    private Button startButton, resetButton;
    private boolean isRaceRunning = false;
    private boolean Winner = false;
    private int budget = 1000;
    private Handler handler = new Handler();
    private Random random = new Random();
    private MediaPlayer mediaPlayer;
     Button btnLogOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        budgetText = findViewById(R.id.budget_text);
        betCar1 = findViewById(R.id.bet_car_1);
        betCar2 = findViewById(R.id.bet_car_2);
        betCar3 = findViewById(R.id.bet_car_3);
        car1 = findViewById(R.id.car_1);
        car2 = findViewById(R.id.car_2);
        car3 = findViewById(R.id.car_3);
        betAmountCar1 = findViewById(R.id.bet_amount_car_1);
        betAmountCar2 = findViewById(R.id.bet_amount_car_2);
        betAmountCar3 = findViewById(R.id.bet_amount_car_3);
        startButton = findViewById(R.id.start_button);
        resetButton = findViewById(R.id.reset_button);

//        btnLogOut= (Button) findViewById(R.id.logOut);

        updateBudgetText();
        setupBetAmountWatcher(betAmountCar1);
        setupBetAmountWatcher(betAmountCar2);
        setupBetAmountWatcher(betAmountCar3);
        startButton.setOnClickListener(v -> startRace());
        resetButton.setOnClickListener(v -> cancelRace());

    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    private void setupBetAmountWatcher(EditText betAmount) {
        betAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateBetAmounts();
                validateBetAmountsChange();

            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
    private void validateBetAmounts() {
        //thay đổi giá trị mới có thay đổi total.. nếu không hàm sẽ tự động trừ dựa vào hàm bên dưới !

        int bet1 = parseBetAmount(betAmountCar1);
        int bet2 = parseBetAmount(betAmountCar2);
        int bet3 = parseBetAmount(betAmountCar3);

        int totalBet = bet1 + bet2 + bet3;

        if (totalBet > budget) {
            startButton.setEnabled(false);
            Toast.makeText(this, "Đang Bị Lỗi Số Tiền Đặt Cược 💵", Toast.LENGTH_SHORT).show();
        } else {
            startButton.setEnabled(true);
        }
    }

    private void validateBetAmountsChange() {
        //thay đổi giá trị mới có thay đổi total.. nếu không hàm sẽ tự động trừ dựa vào hàm bên dưới !

        int bet1 = parseBetAmount(betAmountCar1);
        int bet2 = parseBetAmount(betAmountCar2);
        int bet3 = parseBetAmount(betAmountCar3);

        int totalBet = bet1 + bet2 + bet3;

        if (totalBet >= budget) {
            startButton.setEnabled(false);
            Toast.makeText(this, "Đang Bị Lỗi Số Tiền Đặt Cược 💵", Toast.LENGTH_SHORT).show();
        } else {
            startButton.setEnabled(true);
        }
    }

    private void updateBudgetText() {
//        int totalBet = bet1 + bet2 + bet3;
        budgetText.setText("Budget: $" + budget);

    }

    private void cancelRace() {
        isRaceRunning = false;
        resetRace();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        Toast.makeText(this, "Race cancelled", Toast.LENGTH_SHORT).show();
    }

    private void startRace() {
        int Processing1= 0;

        car1.setProgress(Processing1);
        car2.setProgress(Processing1);
        car3.setProgress(Processing1);



        if (isRaceRunning) return;

        isRaceRunning = true;

        // Initialize MediaPlayer and start playing music
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.mysong);
        }
        mediaPlayer.start();

        new Thread(() -> {
            while (isRaceRunning &&
                    car1.getProgress() < car1.getMax() &&
                    car2.getProgress() < car2.getMax() &&
                    car3.getProgress() < car3.getMax()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!isRaceRunning) break;

                int progressCar1 = car1.getProgress() + random.nextInt(5);
                int progressCar2 = car2.getProgress() + random.nextInt(5);
                int progressCar3 = car3.getProgress() + random.nextInt(5);

                runOnUiThread(() -> {
                    car1.setProgress(progressCar1);
                    car2.setProgress(progressCar2);
                    car3.setProgress(progressCar3);
                });
            }

            if (isRaceRunning) {
                runOnUiThread(this::determineWinner);
            }
        }).start();
    }

    private int parseBetAmount(EditText betAmount) {
        String betStr = betAmount.getText().toString();
        return betStr.isEmpty() ? 0 : Integer.parseInt(betStr);
    }

    private void resetRace() {
        car1.setProgress(0);
        car2.setProgress(0);
        car3.setProgress(0);
    }

    private void determineWinner() {
        int winner = -1;

        // lấy cái đang chạy so với cái lớn nhất rồi set cho con số winner
        if (car1.getProgress() >= car1.getMax()) winner = 1;
        if (car2.getProgress() >= car2.getMax()) winner = 2;
        if (car3.getProgress() >= car3.getMax()) winner = 3;

        //lấy giá trị của số tiền đã đặt cược thông qua hàm parseBetAmount
        int bet1 = parseBetAmount(betAmountCar1);
        int bet2 = parseBetAmount(betAmountCar2);
        int bet3 = parseBetAmount(betAmountCar3);

        validateBetAmountsChange();

        // sau khi check xong hàm xét giá trị theo 1,2,3 đã gán
        // xét từng trừng hợp của checkbox công thức "budget + or - con số vừa đặt cược vào budget"

        if (winner == 1) {
            if (betCar1.isChecked()) budget += bet1;
            if (betCar2.isChecked()) budget -= bet2;
            if (betCar3.isChecked()) budget -= bet3;
        } else if (winner == 2) {
            if (betCar1.isChecked()) budget -= bet1;
            if (betCar2.isChecked()) budget += bet2;
            if (betCar3.isChecked()) budget -= bet3;
        } else if (winner == 3) {
            if (betCar1.isChecked()) budget -= bet1;
            if (betCar2.isChecked()) budget -= bet2;
            if (betCar3.isChecked()) budget += bet3;
        }

//        if (winner == 1) {
//            if (betCar1.isChecked()) {
//                budget += bet1;
//                Winner=true;
//            }else if (betCar2.isChecked()) {
//                budget -= bet2;
//                Winner=false;
//            }else if (betCar3.isChecked()) {
//                budget -= bet3;
//                Winner=false;
//            }
//        } else if (winner == 2) {
//            if (betCar1.isChecked()){
//                budget -= bet1;
//                Winner= false;
//            }else if (betCar2.isChecked()){
//                budget += bet2;
//                Winner=true;
//            }else if (betCar3.isChecked()){
//                budget -= bet3;
//                Winner=false;
//            }
//        } else if (winner == 3) {
//            if (betCar1.isChecked()) {
//                budget -= bet1;
//                Winner=false;
//            }
//            else if (betCar2.isChecked()) {
//                budget -= bet2;
//                Winner=false;
//            }
//            else if (betCar3.isChecked()) {
//                budget += bet3;
//                Winner=true;
//            }
//        }

        Toast.makeText(this, "Car " + winner + " wins!", Toast.LENGTH_SHORT).show();
        updateBudgetText();


        //Set âm thanh
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }

        isRaceRunning = false;
        if (budget==1){
            Toast.makeText(this, " 😈 Tiếp Tục Chơi, Nếu Dưới 0 Sẽ Ghi Nợ ☠️ ", Toast.LENGTH_SHORT).show();
        }
    }

//    private void logOut(){
//        Intent intent= new Intent(this, HomeActivity.class);
//        startActivity(intent);
//        finish();
//    }



//    private void winner(){
//        Intent intent= new Intent(this, WinnerActivity.class);
//        startActivity(intent);
//        finish();
//    }

    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.logOut) {
//        logOut();
//        }
    }
}

