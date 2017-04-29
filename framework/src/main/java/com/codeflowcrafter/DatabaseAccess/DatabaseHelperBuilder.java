package com.codeflowcrafter.DatabaseAccess;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by aiko on 4/29/17.
 */

interface IDatabaseHelperBuilder_Setup {
    public IDatabaseHelperBuilder_Setup AddTable(String tableName, String tableScript);
    public BaseDatabaseHelper Create(Context context);
}

interface IDatabaseHelperBuilder {
    public IDatabaseHelperBuilder_Setup SetDatabase(String databaseTagName, String databaseFilename);

}

public class DatabaseHelperBuilder implements  IDatabaseHelperBuilder
        , IDatabaseHelperBuilder_Setup
{
    String _tag;
    String _databaseFilename;
    HashMap<String, String> _tableScripts = new HashMap<String, String>();
    BaseDatabaseHelper _dbHelper;

    static  IDatabaseHelperBuilder instance = new DatabaseHelperBuilder();

    private DatabaseHelperBuilder(){}

    public static IDatabaseHelperBuilder GetInstance()
    {
        return instance;
    }

    public IDatabaseHelperBuilder_Setup SetDatabase(String databaseTagName, String databaseFilename)
    {
        _tag = databaseTagName;
        _databaseFilename = databaseFilename;

        return this;
    }

    public IDatabaseHelperBuilder_Setup AddTable(String tableName, String tableScript)
    {
        _tableScripts.put(tableName, tableScript);

        return this;
    }

    public BaseDatabaseHelper Create(Context context)
    {
        if(_dbHelper == null)
            _dbHelper = new BaseDatabaseHelper(_tag, _databaseFilename, context, _tableScripts);

        return _dbHelper;
    }
}