package com.blankdev.contentprovider;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private Button btn;
    private ListView listview;
    private ContactsCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ContactsCursorAdapter(this,null,0);
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);

        getLoaderManager().initLoader(0,null,this);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout l = new LinearLayout(getApplicationContext());
                final EditText e1 = new EditText(getApplicationContext());
                final EditText e2 = new EditText(getApplicationContext());
                l.addView(e1);
                l.addView(e2);

                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext())
                        .setView(l)
                        .setCancelable(false)
                        .setTitle("Create Contact")
                        .setPositiveButton("save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                insertContact(e1.getText().toString(),e2.getText().toString());
                                restartLoader();
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    private void insertContact(String contactName,String contactPhone){
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.CONTACT_NAME,contactName);
        values.put(DBOpenHelper.CONTACT_PHONE,contactPhone);
        Uri uri = getContentResolver().insert(ContactsProvider.CONTENT_URI, values);
        Toast.makeText(MainActivity.this, "Create Contact" + contactName, Toast.LENGTH_SHORT).show();
    }

    private void restartLoader(){
        getLoaderManager().initLoader(0,null,this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,ContactsProvider.CONTENT_URI,null,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        adapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
