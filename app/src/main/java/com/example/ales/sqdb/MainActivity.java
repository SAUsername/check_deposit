package com.example.ales.sqdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mDbHelper;
    private Button btnAdd, btnViewData;
    private EditText itemName, priceNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.addBtn);
        btnViewData = (Button) findViewById(R.id.viewDataBtn);
        itemName = (EditText) findViewById(R.id.itemName);
        priceNumber = (EditText) findViewById(R.id.priceNumber);
        mDbHelper = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newE = itemName.getText().toString();
                Float newP = Float.valueOf(priceNumber.getText().toString());
                System.out.println(newE);
                System.out.println(newP);

                if (itemName.length() != 0) {
                    addDat(newE, newP);
                    itemName.setText("");
                    priceNumber.setText("");
                }
            }
        });

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addDat(String newEntry, Float newPrice) {
        boolean insertData = mDbHelper.addData(newEntry, newPrice);

        if (insertData) {
            ToastMessage("Successfully returned data.");
        } else {
            ToastMessage("Something went wrong!");
        }


    }

    private void ToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
