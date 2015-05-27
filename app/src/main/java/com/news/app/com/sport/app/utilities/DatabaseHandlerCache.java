package com.news.app.com.sport.app.utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.news.app.com.sport.app.model.Pojo;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandlerCache extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "News";
	private static final String TABLE_NAME_BOXING = "boxing";
    private static final String TABLE_NAME_PREMIERE_LEAGUE = "premiere_league";
    private static final String TABLE_NAME_LALIGA = "laliga";
    private static final String TABLE_NAME_HORSE_RACE = "horse_race";
    private static final String TABLE_NAME_CAR_RACE = "car_race";
	private static final String KEY_ID = "id";
	private static final String KEY_CATID = "catid";
	private static final String KEY_CID = "cid";
	private static final String KEY_CATEGORYNAME = "categoryname";
 	private static final String KEY_NEWSHEADING = "newsheading";
	private static final String KEY_NEWSIMAGE = "newsimage";
	private static final String KEY_NEWSDESC = "newsdesc";
	private static final String KEY_NEWSDATE = "newsdate";


	public DatabaseHandlerCache(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
			@Override
			public void onCreate(SQLiteDatabase db) 
			{
				String BOXING_TABLE = "CREATE TABLE " + TABLE_NAME_BOXING + "("
						+ KEY_ID + " INTEGER PRIMARY KEY," 
						+ KEY_CATID + " TEXT,"
						+ KEY_CID + " TEXT," 
						+ KEY_CATEGORYNAME + " TEXT,"
 						+ KEY_NEWSHEADING + " TEXT,"
						+ KEY_NEWSIMAGE + " TEXT,"
						+ KEY_NEWSDESC + " TEXT,"
						+ KEY_NEWSDATE + " TEXT"
					 	+ ")";
                db.execSQL(BOXING_TABLE);


                String EPL_TABLE = "CREATE TABLE " + TABLE_NAME_PREMIERE_LEAGUE + "("
                        + KEY_ID + " INTEGER PRIMARY KEY,"
                        + KEY_CATID + " TEXT,"
                        + KEY_CID + " TEXT,"
                        + KEY_CATEGORYNAME + " TEXT,"
                        + KEY_NEWSHEADING + " TEXT,"
                        + KEY_NEWSIMAGE + " TEXT,"
                        + KEY_NEWSDESC + " TEXT,"
                        + KEY_NEWSDATE + " TEXT"
                        + ")";
				db.execSQL(EPL_TABLE);

                String LALIGA_TABLE = "CREATE TABLE " + TABLE_NAME_LALIGA + "("
                        + KEY_ID + " INTEGER PRIMARY KEY,"
                        + KEY_CATID + " TEXT,"
                        + KEY_CID + " TEXT,"
                        + KEY_CATEGORYNAME + " TEXT,"
                        + KEY_NEWSHEADING + " TEXT,"
                        + KEY_NEWSIMAGE + " TEXT,"
                        + KEY_NEWSDESC + " TEXT,"
                        + KEY_NEWSDATE + " TEXT"
                        + ")";
                db.execSQL(LALIGA_TABLE);

                String HORSE_RACE_TABLE = "CREATE TABLE " + TABLE_NAME_HORSE_RACE + "("
                        + KEY_ID + " INTEGER PRIMARY KEY,"
                        + KEY_CATID + " TEXT,"
                        + KEY_CID + " TEXT,"
                        + KEY_CATEGORYNAME + " TEXT,"
                        + KEY_NEWSHEADING + " TEXT,"
                        + KEY_NEWSIMAGE + " TEXT,"
                        + KEY_NEWSDESC + " TEXT,"
                        + KEY_NEWSDATE + " TEXT"
                        + ")";
                db.execSQL(HORSE_RACE_TABLE);

                String CAR_RACE_TABLE = "CREATE TABLE " + TABLE_NAME_CAR_RACE + "("
                        + KEY_ID + " INTEGER PRIMARY KEY,"
                        + KEY_CATID + " TEXT,"
                        + KEY_CID + " TEXT,"
                        + KEY_CATEGORYNAME + " TEXT,"
                        + KEY_NEWSHEADING + " TEXT,"
                        + KEY_NEWSIMAGE + " TEXT,"
                        + KEY_NEWSDESC + " TEXT,"
                        + KEY_NEWSDATE + " TEXT"
                        + ")";
                db.execSQL(CAR_RACE_TABLE);
				
			}

			// Upgrading database
			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				// TODO Auto-generated method stub
				// Drop older table if existed
						db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BOXING);
                        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PREMIERE_LEAGUE);
                        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_LALIGA);
                        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_HORSE_RACE);
                        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CAR_RACE);
						// Create tables again
						onCreate(db);
			}
			
			//Adding Record in Database
			
			public	void AddtoBoxing(Pojo pj)
			{
				SQLiteDatabase db = this.getWritableDatabase();
				ContentValues values = new ContentValues();
				values.put(KEY_CATID, pj.getCatId());
				values.put(KEY_CID, pj.getCId());
				values.put(KEY_CATEGORYNAME, pj.getCategoryName());
 				values.put(KEY_NEWSHEADING, pj.getNewsHeading());
				values.put(KEY_NEWSIMAGE, pj.getNewsImage());
				values.put(KEY_NEWSDESC, pj.getNewsDesc());
				values.put(KEY_NEWSDATE, pj.getNewsDate());
			 
				// Inserting Row
				db.insert(TABLE_NAME_BOXING, null, values);
				db.close(); // Closing database connection
				
			}

    public	void AddtoPremiereLeague(Pojo pj)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CATID, pj.getCatId());
        values.put(KEY_CID, pj.getCId());
        values.put(KEY_CATEGORYNAME, pj.getCategoryName());
        values.put(KEY_NEWSHEADING, pj.getNewsHeading());
        values.put(KEY_NEWSIMAGE, pj.getNewsImage());
        values.put(KEY_NEWSDESC, pj.getNewsDesc());
        values.put(KEY_NEWSDATE, pj.getNewsDate());

        // Inserting Row
        db.insert(TABLE_NAME_PREMIERE_LEAGUE, null, values);
        db.close(); // Closing database connection

    }

    public	void AddtoLaliga(Pojo pj)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CATID, pj.getCatId());
        values.put(KEY_CID, pj.getCId());
        values.put(KEY_CATEGORYNAME, pj.getCategoryName());
        values.put(KEY_NEWSHEADING, pj.getNewsHeading());
        values.put(KEY_NEWSIMAGE, pj.getNewsImage());
        values.put(KEY_NEWSDESC, pj.getNewsDesc());
        values.put(KEY_NEWSDATE, pj.getNewsDate());

        // Inserting Row
        db.insert(TABLE_NAME_LALIGA, null, values);
        db.close(); // Closing database connection

    }

    public	void AddtoHorseRace(Pojo pj)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CATID, pj.getCatId());
        values.put(KEY_CID, pj.getCId());
        values.put(KEY_CATEGORYNAME, pj.getCategoryName());
        values.put(KEY_NEWSHEADING, pj.getNewsHeading());
        values.put(KEY_NEWSIMAGE, pj.getNewsImage());
        values.put(KEY_NEWSDESC, pj.getNewsDesc());
        values.put(KEY_NEWSDATE, pj.getNewsDate());

        // Inserting Row
        db.insert(TABLE_NAME_HORSE_RACE, null, values);
        db.close(); // Closing database connection

    }

    public	void AddtoCarRace(Pojo pj)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CATID, pj.getCatId());
        values.put(KEY_CID, pj.getCId());
        values.put(KEY_CATEGORYNAME, pj.getCategoryName());
        values.put(KEY_NEWSHEADING, pj.getNewsHeading());
        values.put(KEY_NEWSIMAGE, pj.getNewsImage());
        values.put(KEY_NEWSDESC, pj.getNewsDesc());
        values.put(KEY_NEWSDATE, pj.getNewsDate());

        // Inserting Row
        db.insert(TABLE_NAME_CAR_RACE, null, values);
        db.close(); // Closing database connection

    }
			
			// Getting All Data From Boxing
			public List<Pojo> getAllDataFromBoxing()
			{
				List<Pojo> dataList = new ArrayList<Pojo>();
				// Select All Query
				String selectQuery = "SELECT  * FROM " + TABLE_NAME_BOXING;

				SQLiteDatabase db = this.getWritableDatabase();
				Cursor cursor = db.rawQuery(selectQuery, null);

				// looping through all rows and adding to list
				if (cursor.moveToFirst()) 
				{
					do {
						Pojo contact = new Pojo();
						contact.setId(Integer.parseInt(cursor.getString(0)));
						contact.setCatId(cursor.getString(1));
						contact.setCId(cursor.getString(2));
						contact.setCategoryName(cursor.getString(3));
 						contact.setNewsHeading(cursor.getString(4));
						contact.setNewsImage(cursor.getString(5));
						contact.setNewsDesc(cursor.getString(6));
						contact.setNewsDate(cursor.getString(7));
						 
						// Adding contact to list
						dataList.add(contact);
					} while (cursor.moveToNext());
				}

				// return contact list
				return dataList;
			}

    // Getting All Data From PremierLeague
    public List<Pojo> getAllDataPremieeLeague()
    {
        List<Pojo> dataList = new ArrayList<Pojo>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME_PREMIERE_LEAGUE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst())
        {
            do {
                Pojo contact = new Pojo();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setCatId(cursor.getString(1));
                contact.setCId(cursor.getString(2));
                contact.setCategoryName(cursor.getString(3));
                contact.setNewsHeading(cursor.getString(4));
                contact.setNewsImage(cursor.getString(5));
                contact.setNewsDesc(cursor.getString(6));
                contact.setNewsDate(cursor.getString(7));

                // Adding contact to list
                dataList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return dataList;
    }

    // Getting All Data From Laliga
    public List<Pojo> getAllDataFromLaliga()
    {
        List<Pojo> dataList = new ArrayList<Pojo>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME_LALIGA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst())
        {
            do {
                Pojo contact = new Pojo();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setCatId(cursor.getString(1));
                contact.setCId(cursor.getString(2));
                contact.setCategoryName(cursor.getString(3));
                contact.setNewsHeading(cursor.getString(4));
                contact.setNewsImage(cursor.getString(5));
                contact.setNewsDesc(cursor.getString(6));
                contact.setNewsDate(cursor.getString(7));

                // Adding contact to list
                dataList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return dataList;
    }

    // Getting All Data From Car Race
    public List<Pojo> getAllDataFromCarRace()
    {
        List<Pojo> dataList = new ArrayList<Pojo>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME_CAR_RACE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst())
        {
            do {
                Pojo contact = new Pojo();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setCatId(cursor.getString(1));
                contact.setCId(cursor.getString(2));
                contact.setCategoryName(cursor.getString(3));
                contact.setNewsHeading(cursor.getString(4));
                contact.setNewsImage(cursor.getString(5));
                contact.setNewsDesc(cursor.getString(6));
                contact.setNewsDate(cursor.getString(7));

                // Adding contact to list
                dataList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return dataList;
    }

    // Getting All Data From Horse Race
    public List<Pojo> getAllDataFromHorseRace()
    {
        List<Pojo> dataList = new ArrayList<Pojo>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME_HORSE_RACE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst())
        {
            do {
                Pojo contact = new Pojo();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setCatId(cursor.getString(1));
                contact.setCId(cursor.getString(2));
                contact.setCategoryName(cursor.getString(3));
                contact.setNewsHeading(cursor.getString(4));
                contact.setNewsImage(cursor.getString(5));
                contact.setNewsDesc(cursor.getString(6));
                contact.setNewsDate(cursor.getString(7));

                // Adding contact to list
                dataList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return dataList;
    }
			
		//getting single row
			
			public List<Pojo> getSingleBoxingRow(String id)
			{
				List<Pojo> dataList = new ArrayList<Pojo>();
				// Select All Query
				String selectQuery = "SELECT  * FROM " + TABLE_NAME_BOXING +" WHERE catid="+id;

				SQLiteDatabase db = this.getWritableDatabase();
				Cursor cursor = db.rawQuery(selectQuery, null);

				// looping through all rows and adding to list
				if (cursor.moveToFirst()) 
				{
					do {
						Pojo contact = new Pojo();
						contact.setId(Integer.parseInt(cursor.getString(0)));
						contact.setCatId(cursor.getString(1));
						contact.setCId(cursor.getString(2));
						contact.setCategoryName(cursor.getString(3));
 						contact.setNewsHeading(cursor.getString(4));
						contact.setNewsImage(cursor.getString(5));
						contact.setNewsDesc(cursor.getString(6));
						contact.setNewsDate(cursor.getString(7));
						
						// Adding contact to list
						dataList.add(contact);
					} while (cursor.moveToNext());
				}

				// return contact list
				return dataList;
			}

    public List<Pojo> getSinglePremiereLeagueRow(String id)
    {
        List<Pojo> dataList = new ArrayList<Pojo>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME_PREMIERE_LEAGUE +" WHERE catid="+id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst())
        {
            do {
                Pojo contact = new Pojo();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setCatId(cursor.getString(1));
                contact.setCId(cursor.getString(2));
                contact.setCategoryName(cursor.getString(3));
                contact.setNewsHeading(cursor.getString(4));
                contact.setNewsImage(cursor.getString(5));
                contact.setNewsDesc(cursor.getString(6));
                contact.setNewsDate(cursor.getString(7));

                // Adding contact to list
                dataList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return dataList;
    }

    public List<Pojo> getSingleLaligaRow(String id)
    {
        List<Pojo> dataList = new ArrayList<Pojo>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME_LALIGA +" WHERE catid="+id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst())
        {
            do {
                Pojo contact = new Pojo();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setCatId(cursor.getString(1));
                contact.setCId(cursor.getString(2));
                contact.setCategoryName(cursor.getString(3));
                contact.setNewsHeading(cursor.getString(4));
                contact.setNewsImage(cursor.getString(5));
                contact.setNewsDesc(cursor.getString(6));
                contact.setNewsDate(cursor.getString(7));

                // Adding contact to list
                dataList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return dataList;
    }

    public List<Pojo> getSingleCarRaceRow(String id)
    {
        List<Pojo> dataList = new ArrayList<Pojo>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME_CAR_RACE +" WHERE catid="+id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst())
        {
            do {
                Pojo contact = new Pojo();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setCatId(cursor.getString(1));
                contact.setCId(cursor.getString(2));
                contact.setCategoryName(cursor.getString(3));
                contact.setNewsHeading(cursor.getString(4));
                contact.setNewsImage(cursor.getString(5));
                contact.setNewsDesc(cursor.getString(6));
                contact.setNewsDate(cursor.getString(7));

                // Adding contact to list
                dataList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return dataList;
    }

    public List<Pojo> getSingleHorseRaceRow(String id)
    {
        List<Pojo> dataList = new ArrayList<Pojo>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME_HORSE_RACE +" WHERE catid="+id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst())
        {
            do {
                Pojo contact = new Pojo();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setCatId(cursor.getString(1));
                contact.setCId(cursor.getString(2));
                contact.setCategoryName(cursor.getString(3));
                contact.setNewsHeading(cursor.getString(4));
                contact.setNewsImage(cursor.getString(5));
                contact.setNewsDesc(cursor.getString(6));
                contact.setNewsDate(cursor.getString(7));

                // Adding contact to list
                dataList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return dataList;
    }

			
			public enum DatabaseManager {
				INSTANCE;
				private SQLiteDatabase db;
				private boolean isDbClosed =true;
				DatabaseHandlerCache dbHelper;
				public void init(Context context) {
					dbHelper = new DatabaseHandlerCache(context);
				if(isDbClosed){
				isDbClosed =false;
				this.db = dbHelper.getWritableDatabase();
				}

				}


				public boolean isDatabaseClosed(){
				return isDbClosed;
				}

				public void closeDatabase(){
				if(!isDbClosed && db != null){
				isDbClosed =true;
				db.close();
				dbHelper.close();
				}
				}
			}
}
