package com.example.finalgameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
Button btnPlayFlag;
Button btnPlayNumber;
Button btnPlayPuzzle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPlayFlag=findViewById(R.id.button);
        btnPlayNumber=findViewById(R.id.button2);
        btnPlayPuzzle=findViewById(R.id.button10);

        btnPlayFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar snackbar=Snackbar.make(v,"You can play it 10 times and you'll win with score:8",Snackbar.LENGTH_LONG);
                snackbar.setAction("Play The Game", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent =new Intent(MainActivity.this,FlagActivity.class);
                        startActivity(intent);
                    }
                });

snackbar.show();
            }
        });
        btnPlayNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar=Snackbar.make(v,"You can play it 15 times and you'll win with score:10",Snackbar.LENGTH_LONG);
                snackbar.setAction("Play The Game", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this, NumberActivity.class);
                                startActivity(intent);
                            }
                        });
                snackbar.show();
            }
        });

btnPlayPuzzle.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Snackbar snackbar=Snackbar.make(v,"choose the written color.choosing wrong means losing!",Snackbar.LENGTH_LONG);
        snackbar.setAction("Play The Game", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, MatchColorActivity.class);
                        startActivity(intent);
                    }
                });
        snackbar.show();
    }
});
    }
}