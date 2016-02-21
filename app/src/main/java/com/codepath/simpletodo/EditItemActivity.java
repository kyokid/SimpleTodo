package com.codepath.simpletodo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EditItemActivity extends AppCompatActivity {
    EditText edItem;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        String item = getIntent().getStringExtra("ITEM");
        final int position = getIntent().getIntExtra("POSITION", 0);

        edItem = (EditText) findViewById(R.id.edItem);

        btnSave = (Button) findViewById(R.id.btnSave);

        edItem.setText(item);
        int textLength = edItem.getText().length();
        edItem.setSelection(textLength);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditItemActivity.this, MainActivity.class);
                String result = ((EditText) findViewById(R.id.edItem)).getText().toString();
                intent.putExtra("result", result);
                intent.putExtra("position", position);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
