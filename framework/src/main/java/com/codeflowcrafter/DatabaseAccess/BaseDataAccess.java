package com.codeflowcrafter.DatabaseAccess;

import android.content.Context;

import com.codeflowcrafter.DatabaseAccess.Interfaces.IBaseDataAccess;

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

    public abstract DatabaseHelper GetDatabaseHelper(Context context);
}