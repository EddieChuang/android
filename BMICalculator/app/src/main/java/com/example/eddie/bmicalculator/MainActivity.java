package com.example.eddie.bmicalculator;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected EditText tall, weight;
    protected RadioButton male, female;
    protected RadioGroup radioGroup;
    protected Button calculate;
    protected TextView standard, bmi;
    int gender = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tall = (EditText) findViewById(R.id.tall);
        weight = (EditText) findViewById(R.id.weight);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        calculate = (Button) findViewById(R.id.calculate);
        standard = (TextView) findViewById(R.id.standard);
        bmi = (TextView) findViewById(R.id.bmi);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i){
                switch(radioGroup.getCheckedRadioButtonId()){
                    case R.id.male:
                        gender = 1;
                        break;
                    case R.id.female:
                        gender = 2;
                        break;
                }
            }
        });
        calculate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                runAsyncTask();
            }
        });
    }

    private void runAsyncTask(){
        new AsyncTask<Void, Integer, Boolean>(){
          private ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                progressDialog.setMessage("計算中......");
                progressDialog.setCancelable(false);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();
            }

            @Override
            protected Boolean doInBackground(Void... voids){
                int progress = 0;
                while(progress <= 100){
                    try{
                        Thread.sleep(50);
                        publishProgress(Integer.valueOf(progress));
                        progress++;
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                return true;
            }

            @Override
            protected  void onProgressUpdate(Integer... values){
                super.onProgressUpdate(values);
                progressDialog.setProgress(values[0]);
            }

            @Override
            protected void onPostExecute(Boolean status){
                progressDialog.dismiss();
                double cal_tall = Double.parseDouble(tall.getText().toString());
                double cal_weight = Double.parseDouble(weight.getText().toString());
                double cal_standard = 22 * cal_tall / 100 * cal_tall / 100;
                double cal_bmi = 0;
                if(gender == 1){
                    cal_bmi = (cal_weight - (0.88 * cal_standard)) / cal_weight * 100;
                }
                else if(gender == 2){
                    cal_bmi = (cal_weight - (0.82 * cal_standard)) / cal_weight * 100;
                }
                standard.setText(String.format("%.2f", cal_standard));
                bmi.setText(String.format("%.2f", cal_bmi));
            }
        }.execute();
    }

}
