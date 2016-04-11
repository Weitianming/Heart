package com.example.main.heart;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.main.heart.view.SineWave;

public class MainActivity extends AppCompatActivity {
    private Button ceshi; // 波形
    private SineWave sw;
    private boolean isCS = true;
//	private Axes as;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 不自动弹出软键盘
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        initView();
//		as = new Axes(this);
    }

    private void initView() {
        sw = (SineWave) findViewById(R.id.sw);
        ceshi = (Button) findViewById(R.id.ceshi);
        ceshi.setOnClickListener((View.OnClickListener) new WaveOnClickListener());
    }

    // 测试按钮
    private class WaveOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            if (isCS) {
                MyRandom.isRandom = true;
                new MyRandom(handler).start();
                ceshi.setText("测试关闭");
                isCS = false;
            } else {
                MyRandom.isRandom = false;
                ceshi.setText("测试开启");
                isCS = true;
            }

        }
    }

    // 刷新折线图
    public Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            sw.Set(msg.what-100);
            sw.reFresh();
        }
    };

}
