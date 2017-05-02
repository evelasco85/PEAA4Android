package com.codeflowcrafter.Sample;

import android.content.Context;

import com.codeflowcrafter.DatabaseAccess.BaseDataAccess;
import com.codeflowcrafter.DatabaseAccess.DatabaseHelper;
import com.codeflowcrafter.DatabaseAccess.DatabaseHelperBuilder;
import com.codeflowcrafter.DatabaseAccess.Interfaces.IDatabaseHelperBuilder_Setup;
import com.codeflowcrafter.Sample.ContentProviders.Project.ProjectProvider;

/**
 * Created by aiko on 5/1/17.
 */


public class SampleApplicationDataAccess extends BaseDataAccess {

    public static final String APPLICATION_NAME = "sample";
    public static final String DATABASE_TAG_NAME = "SampleProvider";
    static final String DATABASE_FILENAME = "sample.db";

    ProjectProvider _projectProvider;

    IDatabaseHelperBuilder_Setup _dbHelperSetup;

    static SampleApplicationDataAccess instance = new SampleApplicationDataAccess();
    public static SampleApplicationDataAccess GetInstance(){ return instance;}

    private SampleApplicationDataAccess()
    {
        super(DATABASE_TAG_NAME, DATABASE_FILENAME);

        _projectProvider = new ProjectProvider();

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

    public DatabaseHelper GetDatabaseHelper(Context context)
    {
        DatabaseHelper dbHelper = _dbHelperSetup.Create(context);

        return dbHelper;
    }
}
