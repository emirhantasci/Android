package com.emirhantasci.catchrobber;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView scoreText;
    TextView timeText;
    TextView bestScoreText;
    int score;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    int best_Score;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = this.getSharedPreferences( "com.emirhantasci.CatchRobber", Context.MODE_PRIVATE);
        timeText=findViewById(R.id.timeText);
        scoreText=findViewById(R.id.scoreText);
        bestScoreText=findViewById(R.id.bestScoreText);

        imageView1 = findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);

        imageArray= new ImageView[] {imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9};
        hideImages();
        score=0;

        int best_Scorer = sharedPreferences.getInt("best_Scorer" , 0);
        bestScoreText.setText("Best Score: "+best_Scorer);
        new CountDownTimer(10000, 1000){
            public void onTick(long millisUntilFinished){
                timeText.setText("Time: " + millisUntilFinished/1000);
            }
            public void onFinish(){
                timeText.setText("Time off");
                handler.removeCallbacks(runnable);
                for (ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Yeniden oyna");
                alert.setMessage("Tekrardan başlatmak ister misiniz?");
                alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //restart
                        finish();
                        Intent intent = new Intent(getApplicationContext(), MainCatchRobber2.class);
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Oyun bitti", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
            }
        }.start();
    }

    public void increaseScore(View view){
        score=score+1;
        scoreText.setText("Score: "+score);
        int best_Score = sharedPreferences.getInt("best_Scorer" , 0);
        System.out.println(best_Score);
        if (score>best_Score){
            best_Score=score;
            sharedPreferences.edit().putInt("best_Scorer", best_Score).apply();
        }

    }

    public void hideImages() {
        handler = new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for (ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE); //Tüm image'ler görünmez oldu.
                }
                Random random = new Random();
                int i = random.nextInt(9); //0 ile 8 arasında sayı üretecek
                imageArray[i].setVisibility(View.VISIBLE);
                Intent intent =getIntent();
                int level = intent.getIntExtra("seviyes", 500);
                handler.postDelayed(this,level);
            }


        };
        handler.post(runnable);
    }
}