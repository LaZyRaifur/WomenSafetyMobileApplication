package com.example.programmer.womensafety;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Programmer on 12/17/2017.
 */

public class ContactList  extends AppCompatActivity {


    private final int PICK_CONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactlist);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

    public void BackButton(View v)
    {
        onBackPressed();
         }
    public void callContacts(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, PICK_CONTACT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_CONTACT) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                Uri contactData = data.getData();
                Cursor cursor = getContentResolver().query(contactData, null, null, null, null);

                if (cursor.moveToFirst()) {
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                    Intent intent = new Intent(this,MainActivity.class);
                    intent.putExtra("call","name");
                    Toast.makeText(this, "You've picked : " + name, Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

}