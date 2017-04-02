package example.com.contactlist.sqlite;

/**
 * Created by ivan on 05-Jan-16.
 */
/* package private */
interface DbSchema {
    // Contacts table name
    String TABLE_CONTACT = "contacts";


    // Contacts Table Columns names
    static final String KEY_ID = "_id";
    static final String KEY_CONTACT_NO = "cNo";
    static final String KEY_CONTACT_NAME = "cName";


}
