package com.codeflowcrafter.Sample.Project.Implementation.DB;

import android.content.Context;

import com.codeflowcrafter.DatabaseAccess.BaseDataAccess;
import com.codeflowcrafter.DatabaseAccess.BaseDatabaseHelper;
import com.codeflowcrafter.DatabaseAccess.DatabaseHelperBuilder;
import com.codeflowcrafter.DatabaseAccess.Interfaces.IDatabaseHelperBuilder_Setup;

/**
 * Created by aiko on 5/1/17.
 */


public class HabitAtDataAccess extends BaseDataAccess {

    public static final String APPLICATION_NAME = "habitAt";
    public static final String DATABASE_TAG_NAME = "HabitAtProvider";
    static final String DATABASE_FILENAME = "habitAt.db";

    ProjectProvider _projectProvider;
    ProjectMapper _projectMapper;

    IDatabaseHelperBuilder_Setup _dbHelperSetup;

    static HabitAtDataAccess instance = new HabitAtDataAccess();
    public static HabitAtDataAccess GetInstance(){ return instance;}

    private HabitAtDataAccess()
    {
        super(DATABASE_TAG_NAME, DATABASE_FILENAME);

        _projectProvider = new ProjectProvider();
        _projectMapper = new ProjectMapper(_projectProvider);

        _dbHelperSetup = DatabaseHelperBuilder.GetInstance()
                .SetDatabase(GetDatabaseTagName(), GetDatabaseFilename())
                .AddTable(
                        _projectProvider.GetUnderlyingTable().GetTableName(),
                        _projectProvider.GetUnderlyingTable().GetTableCreationScript()
                )
        ;
    }

    public ProjectProvider GetProjectProvider()
    {
        return _projectProvider;
    }

    public ProjectMapper GetProjectMapper()
    {
        return _projectMapper;
    }


    public BaseDatabaseHelper GetDatabaseHelper(Context context)
    {
        BaseDatabaseHelper dbHelper = _dbHelperSetup.Create(context);

        return dbHelper;
    }
}
