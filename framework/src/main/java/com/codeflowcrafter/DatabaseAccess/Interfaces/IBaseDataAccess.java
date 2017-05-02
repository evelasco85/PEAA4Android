package com.codeflowcrafter.DatabaseAccess.Interfaces;

import android.content.Context;

import com.codeflowcrafter.DatabaseAccess.DatabaseHelper;

/**
 * Created by aiko on 4/29/17.
 */

public interface IBaseDataAccess
{
    String GetDatabaseTagName();
    DatabaseHelper GetDatabaseHelper(Context context);
}
