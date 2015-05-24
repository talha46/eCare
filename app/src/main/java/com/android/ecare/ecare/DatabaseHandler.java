package com.android.ecare.ecare;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.List;
import java.util.ArrayList;
/**
 * Created by TALHA on 22.5.15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ecareDB";

    private static final String TABLE_PATIENTS = "patients";

    private static final String TABLE_DOCTORS = "doctors";

    //Patient Table column names
    private static final String KEY_PID = "pid";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_AGE = "age";
    private static final String KEY_PH_NO = "phone_number";
    private static final String KEY_SEX = "sex";
    private static final String KEY_PIC = "pic";

    //Doctors Table Column names which are not in patients table
    private static final String KEY_DID = "did";
    private static final String KEY_SPECIALIZATION = "specialization";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PATIENTS_TABLE = "CREATE TABLE " + TABLE_PATIENTS + "("
                + KEY_PID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT," + KEY_PASSWORD + " TEXT," + KEY_AGE + " INTEGER," + KEY_PH_NO + " TEXT," + KEY_SEX + " TEXT" + ")";
                //KEY_PIC + " BLOB" + ")";
        db.execSQL(CREATE_PATIENTS_TABLE);

        String CREATE_DOCTORS_TABLE = "CREATE TABLE " + TABLE_DOCTORS + "("
                + KEY_DID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT," + KEY_PASSWORD + " TEXT," + KEY_SPECIALIZATION + " TEXT" + ")";
        db.execSQL(CREATE_DOCTORS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENTS);
        db.execSQL("DROP TBALE IF EXISTS" + TABLE_DOCTORS);
        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    //Adding a new doctor
    void addDoctor(Doctor dr)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, dr.getName()); // Patient Name
        values.put(KEY_EMAIL, dr.getEmail());
        values.put(KEY_PASSWORD, dr.getPassword());
        values.put(KEY_SPECIALIZATION, dr.getSpecallization());
        // Inserting doctor Row
        db.insert(TABLE_DOCTORS, null, values);
        db.close(); // Closing database connection

    }


    Doctor getDoctor(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DOCTORS, new String[] { KEY_PID,
                        KEY_NAME, KEY_EMAIL, KEY_PASSWORD, KEY_SPECIALIZATION }, KEY_PID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Doctor dr = new Doctor(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4));
        // return doctor
        return dr;
    }



    // Adding new patient
    void addPatient(Patient patient) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, patient.getName()); // Patient Name
        values.put(KEY_EMAIL, patient.getEmail());
        values.put(KEY_PASSWORD, patient.getPassword());
        values.put(KEY_AGE, patient.getAge());
        values.put(KEY_PH_NO, patient.getPhoneNo()); // PAtientt Phone
        values.put(KEY_SEX, patient.getSex());
        // Inserting Patient Row
        db.insert(TABLE_PATIENTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single patient
    Patient getPatient(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PATIENTS, new String[] { KEY_PID,
                        KEY_NAME, KEY_EMAIL, KEY_PASSWORD, KEY_AGE, KEY_PH_NO, KEY_SEX }, KEY_PID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Patient patient = new Patient(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), Integer.parseInt(cursor.getString(4)), cursor.getString(5),
                cursor.getString(6));
        // return patient
        return patient;
    }

    // Getting All Patients
    public List<Patient> getAllPatients() {
        List<Patient> patientList = new ArrayList<Patient>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PATIENTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Patient patient = new Patient(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), Integer.parseInt(cursor.getString(4)), cursor.getString(5), cursor.getString(6));

                // Adding patient to list
                patientList.add(patient);
            } while (cursor.moveToNext());
        }

        // return patient list
        return patientList;
    }


    // Getting All Doctors
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctorList = new ArrayList<Doctor>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DOCTORS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Doctor dr = new Doctor(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4));

                // Adding doctor to list
                doctorList.add(dr);
            } while (cursor.moveToNext());
        }

        // return doctor list
        return doctorList;
    }

}
