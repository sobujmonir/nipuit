package example.com.contactlist.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DatabaseHandler extends SQLiteOpenHelper {
    private static final String TAG = DatabaseHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "mycontactlist";

    private Context context;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + DbSchema.TABLE_CONTACT + "("
                + DbSchema.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DbSchema.KEY_CONTACT_NO + " TEXT,"
                + DbSchema.KEY_CONTACT_NAME + " TEXT" + ")";


        db.execSQL(CREATE_CONTACT_TABLE);

    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DbSchema.TABLE_CONTACT);

        // Create tables again
        onCreate(db);
    }

}
