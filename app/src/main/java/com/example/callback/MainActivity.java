package com.example.callback;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CustomListener{
    private TextView tv_test;
    private Button bt_test;
    private int change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
        initTest();

    }

    private void initLayout(){
        tv_test = findViewById(R.id.tv_test);
        bt_test = findViewById(R.id.bt_test);
        bt_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startA();
            }
        });
    }

    public void startA(){
        new A(this).start(); //여기서 동작할 클래스 A를 호출하고 생성자로 콜백
    }

    private void initTest(){
        change = 0;
    }

    @Override
    public void callBack(Object object) {
        String st = (String) object;
        //헤당 함수가 A클래스의 new 생성 > start > result()를 통해서 콜백 호출됨
        //결과 호출
        if(change == 0){
            tv_test.setText(st);
            change = 1;
        }else if(change ==1){
            tv_test.setText("Hello World");
            change = 0;
        }
    }

    public class A{
        private CustomListener listener;

        public A(CustomListener customListener){
            super();
            this.listener = customListener;
        }
        public void start(){
            //..필요한 작업 실행 후 결과 호출
            result();
        }
        public void result(){
            if (this.listener != null){
                //callback 호출
                listener.callBack("HELLO");
            }
        }
    }
}