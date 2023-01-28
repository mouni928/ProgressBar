package com.example.progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button b;
    SeekBar s;
    ProgressBar p,p1;
    TextView t1,t2;
    int status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=(Button) findViewById(R.id.button);
        s=(SeekBar) findViewById(R.id.seekBar);
        p=(ProgressBar) findViewById(R.id.progressBar);
        p1=(ProgressBar) findViewById(R.id.progressBar2);
        t1=(TextView) findViewById(R.id.textView1);
        t2=(TextView)findViewById(R.id.textView2);

        p.setVisibility(View.INVISIBLE);
        p1.setVisibility(View.INVISIBLE);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status=0;
                p.setVisibility(View.VISIBLE);
                p1.setVisibility(View.VISIBLE);


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (status<100){
                            try {
                                Thread.sleep(100);
                            }
                            catch (InterruptedException e){
                                e.printStackTrace();
                            }
                            status++;
                            if(status>=100)
                                p.setVisibility(View.INVISIBLE);

                            else
                                p.setProgress(status);

                            t1.setText(String.valueOf(status)+"/"+p.getMax());

                        }
                    }
                }).start();
            }
        });
        s.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int seek=0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                seek=i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                t2.setText(seek+"/"+seekBar.getMax());
            }
        });
    }
}