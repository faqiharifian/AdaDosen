/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.digitcreativestudio.safian.adadosen.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.digitcreativestudio.safian.adadosen.Data.DBContract.*;

/**
 * Manages a local database for weather data.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "adadosen.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_LECTURERS_TABLE = "CREATE TABLE " + LecturerEntry.TABLE_NAME + " (" +

                LecturerEntry._ID + " INTEGER PRIMARY KEY," +

                LecturerEntry.COLUMN_NIP +" TEXT, "+
                LecturerEntry.COLUMN_NAME +" TEXT, "+
                LecturerEntry.COLUMN_STATUS +" NUMERING, "+
                LecturerEntry.COLUMN_COMMENT +" TEXT, "+
                LecturerEntry.COLUMN_MODIFIED_BY +" TEXT, "+
                LecturerEntry.COLUMN_LAST_MODIFY +" NUMERING, "+
                " UNIQUE (" + LecturerEntry._ID + ") ON CONFLICT REPLACE);";
        sqLiteDatabase.execSQL(SQL_CREATE_LECTURERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LecturerEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
