package com.example.rok.a0607;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase database = null;
    EditText e1,e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = (EditText)findViewById(R.id.etdbname);
        e2 = (EditText)findViewById(R.id.etData);
    }

    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btncreatedb:
                if(e1.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"dsa",Toast.LENGTH_SHORT).show();
                    return;
                }
                database = openOrCreateDatabase(e1.getText().toString(),MODE_PRIVATE,null);
                if(database == null){
                    Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btndeletedb:
                boolean delete= deleteDatabase(e1.getText().toString());
                if(delete){
                    showToast("delete success");
                    database = null;
                }
                else {
                    showToast("delete fail");
                }
                break;
            case R.id.btncreateTable:
                String sql  = "create table if not exists students ("+
                        "id Integer primary key autoincrement,"+
                        "name text  not null,"+
                        "hakno text)";
                try{
                    database.execSQL(sql);
                    showToast("success");
                }catch (SQLiteException e){
                    showToast("fail"+e.getMessage());

                }
                break;
            case R.id.btninsertdata:
                String sql1 = "insert into students if not exists values(null,'gkdl','2013')";
                String sql2 = "insert into students values(null,'gkdl123','201233')";
                String sql3 = "insert into students values(null,'gk12312dl','201123124323')";
                showToast("success");
                try{
                    database.execSQL(sql1);
                    database.execSQL(sql2);
                    database.execSQL(sql3);
                }catch(SQLiteException e){
                    showToast("fail");
                }
                break;
            case R.id.btnselectdata:
                String sql4 = "select * from students";
                try{
                    Cursor recordset  = database.rawQuery(sql4,null);
                    recordset.moveToFirst();
                    String str = "";
                    do{
                        str +=recordset.getInt(0)+"/"+recordset.getString(1)+"/"+recordset.getString(2);
                    }while(recordset.moveToNext());
                    recordset.close();
                    e2.setText(str);
                    showToast("success");
                }catch (SQLiteException e){
                    showToast("[Error]"+e.getMessage());
                }
                break;


        }
    }
    public void showToast(String name){
        Toast.makeText(getApplicationContext(),name,Toast.LENGTH_SHORT).show();
    }
}
