package com.changxiying.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.changxiying.myapplication.R;

public class MainActivity extends FragmentActivity {
    private static final String TAG = "MainActivity";
    private final String NORMAL_ACTION = "com.changxiying.myapplication.receiver";

    CheckBox _shanghai=null;
    Button _nextbutton;
    EditText _editText;
    Button _toastButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        _nextbutton = (Button)findViewById(R.id.button_next);
        _nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,NextActivity.class);
                startActivity(intent);

            }
        });
        _editText = (EditText) findViewById(R.id.editText1);
        _shanghai = (CheckBox)findViewById(R.id.shanghai);
        _shanghai.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    _editText.setText(buttonView.getText()+"选中");
                }else{
                    _editText.setText(buttonView.getText()+"取消选中");
                }
            }
        });
        _toastButton = (Button)findViewById(R.id.button_simpletoast);
        _toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast=Toast.makeText(MainActivity.this,"简单的信息提示",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
    public void sendBroadcast(View view){
        Intent intent = new Intent(NORMAL_ACTION);
        intent.putExtra("Msg","CXY");
        sendBroadcast(intent);
    }

}
