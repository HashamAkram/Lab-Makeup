package com.example.hp.labmackup.contentprovider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.hp.labmackup.R;

public class MYContacts extends AppCompatActivity {

    private static final String TAG = "HTAG";
    private String[] mColumnProjection = new String[]{
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.CONTACT_STATUS,
            ContactsContract.Contacts.HAS_PHONE_NUMBER,
    };
    private String mOrderBy = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycontacts);

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,mColumnProjection,
                null,null,null);
        if(cursor !=null && cursor.getCount()>0){
            StringBuilder stringBuilder = new StringBuilder("");
            while (cursor.moveToNext()){
                stringBuilder.append(cursor.getString(0) + " , " + cursor.getString(1)+ " , "+ cursor.getString(2) + "\n");
                Log.d(TAG, "Cursor moveToNext : " + stringBuilder.toString());
            }

        }
        else {
            Log.d(TAG, "onCreate:  No Record Found");
        }

    }
}
