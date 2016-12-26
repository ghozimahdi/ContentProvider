package com.blankdev.contentprovider;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Blank Dev on 25/12/16.
 */
public class ContactsCursorAdapter extends CursorAdapter {

    public ContactsCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_list_item,viewGroup,false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String contactName = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CONTACT_NAME));
        String contactPhone = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CONTACT_PHONE));

        TextView textView = (TextView) view.findViewById(R.id.txt1);
        TextView textView2 = (TextView) view.findViewById(R.id.txt2);
        textView.setText(contactName);
        textView2.setText(contactPhone);
    }
}
