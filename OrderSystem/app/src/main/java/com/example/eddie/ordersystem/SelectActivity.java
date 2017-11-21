package com.example.eddie.ordersystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

/**
 * Created by eddie on 2017/11/22.
 */

public class SelectActivity extends Activity {

    Button sent_btn;
    String suger = "無糖";
    EditText set_drink;
    String ice_opt = "微冰";

    public void onCreate(Bundle savedInstancsState){
        super.onCreate(savedInstancsState);
        setContentView(R.layout.select);

        RadioGroup rg1 = (RadioGroup) findViewById(R.id.radioGroup);
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                switch(checkedId){
                    case R.id.radioButton:
                        suger = "無糖";
                        break;
                    case R.id.radioButton2:
                        suger = "少糖";
                        break;
                    case R.id.radioButton3:
                        suger = "半糖";
                        break;
                    case R.id.radioButton4:
                        suger = "全糖";
                        break;
                }
            }
        });

        RadioGroup rg2 = (RadioGroup) findViewById(R.id.radioGroup2);
        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                switch(checkedId){
                    case R.id.radioButton5:
                        ice_opt = "微冰";
                        break;
                    case R.id.radioButton6:
                        ice_opt = "少冰";
                        break;
                    case R.id.radioButton7:
                        ice_opt = "正常冰";
                        break;
                }
            }
        });

        sent_btn = (Button) findViewById(R.id.send);
        sent_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                set_drink = (EditText) findViewById(R.id.editText);
                String temp = set_drink.getText().toString();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("suger_level", suger);
                bundle.putString("drink_level", temp);
                bundle.putString("ice_level", ice_opt);
                intent.putExtras(bundle);
                setResult(101, intent);
                finish();
            }
        });

    }


}
