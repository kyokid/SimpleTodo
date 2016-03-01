package com.codepath.simpletodo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import com.codepath.simpletodo.adapter.ItemAdapter;
import com.codepath.simpletodo.constant.Const;
import com.codepath.simpletodo.database.DBController;
import com.codepath.simpletodo.model.Item;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private Button btnAddItem;
    private DBController dbCon;
    private List<Item> mItemList = new ArrayList<>();
    private EditText etAddItem;
    final Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbCon = new DBController(MainActivity.this);
        dbCon.open();
        mListView = (ListView) findViewById(R.id.lvItems);
        mItemList = dbCon.loadAll();
        final ItemAdapter itemAdapter = new ItemAdapter(this, mItemList);
        mListView.setAdapter(itemAdapter);

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dbCon.open();
                dbCon.deleteItem(mItemList.get(position).getItemId());
                mItemList.remove(position);
                itemAdapter.notifyDataSetChanged();
                return true;
            }
        });

        btnAddItem = (Button) findViewById(R.id.btnAddItem);
        etAddItem = (EditText) findViewById(R.id.etNewItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = etAddItem.getText().toString().trim();
                if(!text.isEmpty()) {
                    Item item = new Item(text);
                    dbCon.open();
                    long id = dbCon.addItem(item);
                    item.setItemId(id);
                    etAddItem.setText(Const.BLANK);
                    mItemList.add(item);
                    itemAdapter.notifyDataSetChanged();
                }
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle(getString(R.string.editItemTitle));
                alert.setMessage(getString(R.string.editItemMessage));
                final EditText input = new EditText(mContext);
                input.setText(mItemList.get(position).getItemName());
                input.setSelection(input.getText().length());
                alert.setView(input);
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String edited = input.getEditableText().toString();
                        if (!edited.isEmpty()) {
                            Item item = mItemList.get(position);
                            item.setItemName(edited);
                            dbCon.open();
                            dbCon.updateItem(item.getItemId(), edited);
                            itemAdapter.notifyDataSetChanged();

                        }
                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        });

    }



}
