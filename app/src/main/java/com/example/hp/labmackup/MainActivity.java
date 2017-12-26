package com.example.hp.labmackup;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "HTAG";
    // A "projection" defines the columns that will be returned for each row
    String[] mProjection =
            {
                    UserDictionary.Words._ID,    // Contract class constant for the _ID column name
                    UserDictionary.Words.WORD,   // Contract class constant for the word column name
                    UserDictionary.Words.LOCALE  // Contract class constant for the locale column name
            };

    // Defines a string to contain the selection clause
    String mSelectionClause = null;

    // Initializes an array to contain selection arguments
    String[] mSelectionArgs = {""};

    private EditText mSearchWord;
    private String mSearchString;

    private Cursor mCursor;
    private Button btnCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchWord = (EditText) findViewById(R.id.edit_word_dictionary);
        //////////////////////////////////////////////////////////////////

        // enter data

        // Defines a new Uri object that receives the result of the insertion
        Uri mNewUri;



// Defines an object to contain the new values to insert
        ContentValues mNewValues = new ContentValues();

/*
 * Sets the values of each column and inserts the word. The arguments to the "put"
 * method are "column name" and "value"
 */
        mNewValues.put(UserDictionary.Words.APP_ID,"example.user" );
        mNewValues.put(UserDictionary.Words.LOCALE, "en_US");
        mNewValues.put(UserDictionary.Words.WORD, "myinsert");
        mNewValues.put(UserDictionary.Words.FREQUENCY, "100");

        mNewUri = getContentResolver().insert(
                UserDictionary.Words.CONTENT_URI,  // the user dictionary content URI
                mNewValues                          // the values to insert
        );

        Log.d(TAG, "mNewUri " + mNewUri);






        ////////////////////////////////////////




        mSearchString = "myinsert";

        // If the word is the empty string, gets everything
        if (TextUtils.isEmpty(mSearchString)) {
            // Setting the selection clause to null will return all words
            mSelectionClause = null;
            mSelectionArgs[0] = "";
            Log.d(TAG, "is Empty call");


        } else {
            // Constructs a selection clause that matches the word that the user entered.
            mSelectionClause = UserDictionary.Words.WORD + " = ?";

            //    mSelectionClause = UserDictionary.Words.WORD +"="+ mSearchString;

            // Moves the user's input string to the selection arguments.
            mSelectionArgs[0] = mSearchString;
            Log.d(TAG, " mSelection claus or args else part");

        }

        // Does a query against the table and returns a Cursor object
        mCursor = getContentResolver().query(
                UserDictionary.Words.CONTENT_URI,  // The content URI of the words table
                mProjection,                       // The columns to return for each row
                mSelectionClause,                   // Either null, or the word the user entered
                mSelectionArgs, // Either empty, or the string the user entered
                null);


        if (null == mCursor) {
    /*
     * Insert code here to handle the error. Be sure not to use the cursor! You may want to
     * call android.util.Log.e() to log this error.
     *
     */
            Log.d(TAG, "mCursor == Null  ");

// If the Cursor is empty, the provider found no matches
        } else if (mCursor.getCount() < 1) {

    /*
     * Insert code here to notify the user that the search was unsuccessful. This isn't necessarily
     * an error. You may want to offer the user the option to insert a new row, or re-type the
     * search term.
     */

            Log.d(TAG, "getCount < 1  ");
            Log.d(TAG, "onClick: " + mCursor.getCount());

        } else {
            // Insert code here to do something with the results

            Log.d(TAG, "onCreate: Result Found");

        }




        ////////////////////////////////////////////////////////

        btnCheck = (Button) findViewById(R.id.btn_word_dictionary);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mSearchString = mSearchWord.getText().toString();

                // If the word is the empty string, gets everything
                if (TextUtils.isEmpty(mSearchString)) {
                    // Setting the selection clause to null will return all words
                    mSelectionClause = null;
                    mSelectionArgs[0] = "";
                    Log.d(TAG, "is Empty call");
                    

                } else {
                    // Constructs a selection clause that matches the word that the user entered.
                    mSelectionClause = UserDictionary.Words.WORD + " = ?";

                //    mSelectionClause = UserDictionary.Words.WORD +"="+ mSearchString;

                    // Moves the user's input string to the selection arguments.
                    mSelectionArgs[0] = mSearchString;
                    Log.d(TAG, " mSelection claus or args else part");

                }

                // Does a query against the table and returns a Cursor object
                mCursor = getContentResolver().query(
                        UserDictionary.Words.CONTENT_URI,  // The content URI of the words table
                        mProjection,                       // The columns to return for each row
                        mSelectionClause,                   // Either null, or the word the user entered
                        mSelectionArgs, // Either empty, or the string the user entered
                        null);


                if (null == mCursor) {
    /*
     * Insert code here to handle the error. Be sure not to use the cursor! You may want to
     * call android.util.Log.e() to log this error.
     *
     */
                    Log.d(TAG, "mCursor == Null  ");

// If the Cursor is empty, the provider found no matches
                } else if (mCursor.getCount() < 1) {

    /*
     * Insert code here to notify the user that the search was unsuccessful. This isn't necessarily
     * an error. You may want to offer the user the option to insert a new row, or re-type the
     * search term.
     */

                    Log.d(TAG, "getCount < 1  ");
                    Log.d(TAG, "onClick: " + mCursor.getCount());

                } else {
                    // Insert code here to do something with the results

                    Log.d(TAG, "onCreate: Result Found");


                }


            }
        });


    }
}
