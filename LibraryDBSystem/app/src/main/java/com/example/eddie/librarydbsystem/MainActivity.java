package com.example.eddie.librarydbsystem;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editName, editPrice;
    TextView textName, textPrice, textNo;
    Button add, append, delete, query;
    SQLiteDatabase dbrw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = (EditText) findViewById(R.id.editName);
        editPrice = (EditText) findViewById(R.id.editPrice);
        textName = (TextView) findViewById(R.id.textName);
        textNo = (TextView) findViewById(R.id.textPrice);
        textPrice = (TextView) findViewById(R.id.textPrice);
        add = (Button) findViewById(R.id.add);
        append = (Button) findViewById(R.id.append);
        delete = (Button) findViewById(R.id.delete);
        query = (Button) findViewById(R.id.query);

        MyDBHelper dbHelper = new MyDBHelper(this);
        dbrw = dbHelper.getWritableDatabase();

        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                newBook();
            }
        });

        append.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                renewBook();
            }
        });

        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                deleteBook();
            }
        });

        query.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                queryBook();
            }
        });
    }

    public void newBook(){

        if(editName.getText().toString().equals("") || editPrice.getText().toString().equals(""))
            Toast.makeText(this, "輸入資料不完全", Toast.LENGTH_SHORT).show();
        else
        {
            double price = Double.parseDouble(editPrice.getText().toString());
            ContentValues cv = new ContentValues();
            cv.put("title", editName.getText().toString());
            cv.put("price", price);
            dbrw.insert("myTable", null, cv);
            Toast.makeText(this, "新增書名: " + editName.getText().toString() + "\n價格: " + price, Toast.LENGTH_SHORT).show();
            editPrice.setText("");
            editName.setText("");
        }
    }

    public void renewBook(){
        if(editName.getText().toString().equals("") || editPrice.getText().toString().equals(""))
            Toast.makeText(this, "輸入資料不完全", Toast.LENGTH_SHORT).show();
        else
        {
            double price = Double.parseDouble(editPrice.getText().toString());
            ContentValues cv = new ContentValues();
            cv.put("price", price);
            dbrw.update("myTable", cv, "title='" + editName.getText().toString() + "'", null);
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
            editPrice.setText("");
            editName.setText("");
        }
    }

    public void deleteBook(){
        if(editName.getText().toString().equals(""))
            Toast.makeText(this, "請輸入要刪除的書名", Toast.LENGTH_SHORT).show();
        else
        {
            dbrw.delete("myTable", "title='" + editName.getText().toString() + "'", null);
            Toast.makeText(this, "刪除成功", Toast.LENGTH_SHORT).show();
            editName.setText("");
        }
    }

    public void queryBook(){

        String index = "順序\n", title = "書名\n", price = "價格\n";
        String [] column = {"title", "price"};
        Cursor cursor;
        if(editName.getText().toString().equals(""))
            cursor = dbrw.query("myTable", column, null, null, null, null, null);
        else
            cursor = dbrw.query("myTable", column, "title='" + editName.getText().toString() + "'", null, null, null, null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            for(int i = 0; i < cursor.getCount(); ++i){
                index += (i+1) + "\n";
                title += cursor.getString(0) + "\n";
                price += cursor.getString(1) + "\n";
                cursor.moveToNext();
            }
            textNo.setText(index);
            textName.setText(title);
            textPrice.setText(price);
            Toast.makeText(this, "共有" + cursor.getCount() + "筆紀錄", Toast.LENGTH_SHORT).show();
        }
        editName.setText("");
    }

}
