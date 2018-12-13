package com.example.user.myapplication;

import android.database.Cursor;
import android.view.View;
import android.widget.Toast;

public class MainActivity {
     btn_query.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick (View v){
            Cursor c;
            if (ed_book.length() < 1)
                c = dbrw.rawQuery("SELECT * FROM myTable", null);
            else
                c = dbrw.rawQuery("SELECT * FROM myTable WHERE book LIKE '" + ed_book.getText().toString() + "'", null);
            c.moveToFirst();
            items.clear();
            Toast.makeText(MainActivity.this, "共有" + c.getString() + "筆資料", Toast.LENGTH_SHORT).show();
            for (int i = 0; i < c.getCount(); i++) {
                items.add("書名:" + c.getString(0) +
                        "\t\t\t\t 價格:" + c.getString(1));
                c.moveToNext();
            }
            adapter.notifyDataSetChanged();
            c.close();
        }
    })
            btn_insert.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
            if (ed_book.length() < 1 || ed_price.length() < 1)
                Toast.makeText(MainActivity.this, "欄位請勿留空",
                        Toast.LENGTH_SHORT).show();
            else {
                try {
                    dbrw.execSQL("INSERT INTO myTable(book price)VALUES(?,?)", new Object[]{ed_book.getText().toString(), ed_price.getText().toString()});
                    Toast.makeText(MainActivity.this, "新增書名" + ed_book.getText().toString() + "價格" + ed_price.getText().toString(), Toast.LENGTH_SHORT).show();
                    ed_book.setText("");
                    ed_price.setText("");
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "新增失敗:" + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
    });
}
