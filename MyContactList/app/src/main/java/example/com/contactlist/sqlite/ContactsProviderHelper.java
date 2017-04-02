package example.com.contactlist.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;


import java.util.ArrayList;

import example.com.contactlist.model.Contact;

/**
 * Created by ivan on 05-Jan-16.
 */
public class ContactsProviderHelper {
    private static final String TAG = ContactsProviderHelper.class.getSimpleName();
    protected static String PROVIDER_NAME = "example.com.contactlist.provider.contact";

    protected static Uri CONTACT_URI = Uri.parse("content://" + PROVIDER_NAME + "/contacts");

    Context context;

    public ContactsProviderHelper(Context c){
        context = c;
    }
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
    // Adding new customer
    public void addContact(ContentValues values) {
        context.getContentResolver().insert(CONTACT_URI, values);
    }

    public ArrayList<Contact> getTenContacts(){
        ArrayList<Contact> contactList = new ArrayList<Contact>();
        String[] projection = new String[] { DbSchema.KEY_CONTACT_NO,DbSchema.KEY_CONTACT_NAME };
        Cursor cursor = context.getContentResolver().query(CONTACT_URI, projection, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.cNo = cursor.getString(0);
                contact.cName = cursor.getString(1);

                Log.i(TAG, " Contact Name = " + cursor.getString(1));
                Log.i(TAG, " Contact No = " + cursor.getString(0));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        return contactList;

    }

}
