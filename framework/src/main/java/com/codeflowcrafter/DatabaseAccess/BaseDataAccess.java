package com.codeflowcrafter.DatabaseAccess;

import android.content.Context;

/**
 * Created by aiko on 4/29/17.
 */

interface IBaseDataAccess
{
    String GetDatabaseTagName();
    BaseDatabaseHelper GetDatabaseHelper(Context context);
}

public abstract class BaseDataAccess implements IBaseDataAccess {
    String _databaseTagName;
    String _databaseFilename;

    public String GetDatabaseTagName()
    {
        return _databaseTagName;
    }

    public String GetDatabaseFilename()
    {
        return _databaseFilename;
    }

    public BaseDataAccess(String databaseTagName, String databaseFilename)
    {
        _databaseTagName = databaseTagName;
        _databaseFilename = databaseFilename;
    }

    public abstract BaseDatabaseHelper GetDatabaseHelper(Context context);
}