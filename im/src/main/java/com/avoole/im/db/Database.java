package com.avoole.im.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.avoole.im.Applog;

public class Database extends SQLiteOpenHelper {
    public static final String DB_NAME = "avooleim.db";
    public static final int DB_VERSION = 1;

    /*
     *  database singleton
     */
    private static Database database;

    public static Database getInstance() {
        return database;
    }

    public static void init(Context context) {
        if (database == null) {
            database = new Database(context);
        }
    }

    private Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //createAllTables(db);
        final int currentVersion = db.getVersion();
        onUpgrade(db, currentVersion, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int currentVersion, int newVersion) {
        Applog.d("Upgrading database from version " + currentVersion + " to version " + newVersion);
        switch (currentVersion) {
            case 1:
                createTable(db);
            default:
                break;
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // IMPORTANT: do NOT call super() here - doing so throws a SQLiteException
        Applog.d("Downgrading database from version " + oldVersion + " to version " + newVersion);
    }

    public static void createTable(SQLiteDatabase database) {
        String user = "create table if not exists user ("
                + "id text,"
                + "nickname text,"
                + "phone text,"
                + "icon text"
                + ")";

        String group = "create table if not exists group ("
                + "id text,"
                + "name text"
                + ")";

        String group_member = "create table if not exists group_member ("
                + "id integer primary key autoincrement,"
                + "group text,"
                + "user text"
                + ")";

        String msg = "create table if not exists msg ("
                + "id text,"
                + "content text,"
                + "from text,"
                + "to text,"
                + "status integer,"
                + "lifetime text,"
                + "time integer,"
                + "type integer"
                + ")";

        String option = "create table if not exists msg ("
                + "id integer primary key autoincrement,"
                + "key text,"
                + "value text,"
                + "extend text"
                + ")";

        database.execSQL(user);
        database.execSQL(group);
        database.execSQL(group_member);
        database.execSQL(msg);
        database.execSQL(option);
    }
}
