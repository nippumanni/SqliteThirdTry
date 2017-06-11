package com.example.daleshprashar.sqlitethirdtry;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    DatabaseHelper myDb;
    EditText editName , editSurName , editMarks , editTextId;
    Button btnAddData;
    Button btnviewAll;
    Button btnupdate;
    Button btndelete;
    // just for github practise change

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        editName = (EditText) findViewById(R.id.editText);
        editSurName = (EditText) findViewById(R.id.editText2);
        editMarks = (EditText) findViewById(R.id.editText3);
        editTextId = (EditText) findViewById(R.id.editText4);
        btnAddData = (Button) findViewById(R.id.button);
        btnviewAll = (Button) findViewById(R.id.button2);
        btnupdate = (Button) findViewById(R.id.button3);
        btndelete = (Button) findViewById(R.id.button4);
        AddData();
        viewAll();
        updateData();
        DeleteData();

    }

    public  void AddData()
    {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDb.insertData(editName.getText().toString(),
                        editSurName.getText().toString(),
                        editMarks.getText().toString());
                boolean isInserted = myDb.insertData(editName.getText().toString(),
                        editSurName.getText().toString(),
                        editMarks.getText().toString());
                if(isInserted = true)
                    Toast.makeText(MainActivity.this , "DATA INSERTED",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this , "DATA NOT INSERTED",Toast.LENGTH_LONG).show();

            }
        });

    }

    public  void  viewAll()
    {
        btnviewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Cursor res =   myDb.getAllData();
                if (res.getCount() == 0)
                {
                    // show message
                    showMessage("Error","Nothing Found");

                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("ID :" + res.getString(0)+"\n");
                    buffer.append("Name :" + res.getString(1)+"\n");
                    buffer.append("SurName :" + res.getString(2)+"\n");
                    buffer.append("Marks :" + res.getString(3)+"\n\n");
                }

                // show all data
                showMessage("Data",buffer.toString());
            }
        });
    }

    public  void  showMessage(String title , String Message)
    {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }

    public  void updateData()
    {
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDb.updateData(

                        editTextId.getText().toString() ,editName.getText().toString(),
                        editSurName.getText().toString(),
                        editMarks.getText().toString());

                if (isUpdate == true)
                    Toast.makeText(MainActivity.this , "DATA UPDATE",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this , "DATA NOT UPDATED",Toast.LENGTH_LONG).show();

            }
        });
    }
public  void  DeleteData()
{
    btndelete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            myDb.deleteData(editTextId.getText().toString());
            Integer deletedRows =   myDb.deleteData(editTextId.getText().toString());
            if (deletedRows > 0)
                Toast.makeText(MainActivity.this , "DATA DELETED",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this , "DATA NOT DELETED",Toast.LENGTH_LONG).show();

        }
    });
}


}
