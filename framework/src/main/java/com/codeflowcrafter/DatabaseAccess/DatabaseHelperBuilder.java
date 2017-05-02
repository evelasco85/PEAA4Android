package com.codeflowcrafter.DatabaseAccess;

import android.content.Context;

import com.codeflowcrafter.DatabaseAccess.Interfaces.IDatabaseHelperBuilder;
import com.codeflowcrafter.DatabaseAccess.Interfaces.IDatabaseHelperBuilder_Setup;

import java.util.HashMap;

public class DatabaseHelperBuilder implements IDatabaseHelperBuilder
        , IDatabaseHelperBuilder_Setup
{
    String _tag;
    String _databaseFilename;
    HashMap<String, String> _tableScripts = new HashMap<String, String>();
    DatabaseHelper _dbHelper;

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

    public DatabaseHelper Create(Context context)
    {
        if(_dbHelper == null)
            _dbHelper = new DatabaseHelper(_tag, _databaseFilename, context, _tableScripts);

        return _dbHelper;
    }
}