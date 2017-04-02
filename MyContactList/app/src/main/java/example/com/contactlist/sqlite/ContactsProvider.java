package example.com.contactlist.sqlite;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class ContactsProvider extends ContentProvider {
	private static final String TAG = ContactsProvider.class.getSimpleName();
	private static final int CONTACTS = 1;

	private static final UriMatcher uriMatcher;
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(ContactsProviderHelper.PROVIDER_NAME, "contacts", CONTACTS);
	}
	private SQLiteDatabase contactDb;
	DatabaseHandler dbHelper;


	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// arg0 = uri
		// arg1 = selection
		// arg2 = selectionArgs
		int count = 0;
		String id;
		switch (uriMatcher.match(arg0)) {
			case CONTACTS:
				count = contactDb.delete(DbSchema.TABLE_CONTACT, arg1, arg2);
				break;

			default:
				throw new IllegalArgumentException("Unknown URI " + arg0);
		}
		getContext().getContentResolver().notifyChange(arg0, null);
		return count;
	}

	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
		// ---get all contact---
			case CONTACTS:
				return "vnd.android.cursor.dir/example.com.contactlist.provider.contact";

		default:
			throw new IllegalArgumentException("Unsupported URI: " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// ---add a new customer---
		long rowID;
		if (uriMatcher.match(uri) == CONTACTS)
		{
			rowID = contactDb.insert(DbSchema.TABLE_CONTACT, "", values);

			// ---if added successfully---
			if (rowID > 0) {
				Uri _uri = ContentUris.withAppendedId(ContactsProviderHelper.CONTACT_URI, rowID);
				getContext().getContentResolver().notifyChange(_uri, null);
				return _uri;
			}
		}

		throw new SQLException("Failed to insert row into " + uri);
	}

	@Override
	public boolean onCreate() {
		Context context = getContext();
		dbHelper = new DatabaseHandler(context);
		contactDb = dbHelper.getWritableDatabase();
		return (contactDb == null) ? false : true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder sqlBuilder = new SQLiteQueryBuilder();
			sqlBuilder.setTables(DbSchema.TABLE_CONTACT);

		Cursor c = sqlBuilder.query(contactDb, projection, selection, selectionArgs, null, null, sortOrder);

		// ---register to watch a content URI for changes---
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		int count = 0;
		switch (uriMatcher.match(uri)) {
			case CONTACTS:
				count = contactDb.update(DbSchema.TABLE_CONTACT, values, selection, selectionArgs);
				break;
			default:
				throw new IllegalArgumentException("Unknown URI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
}
