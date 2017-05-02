package com.codeflowcrafter.Sample;

import android.content.Context;

import com.codeflowcrafter.DatabaseAccess.BaseContentProviders;
import com.codeflowcrafter.DatabaseAccess.DatabaseHelper;
import com.codeflowcrafter.DatabaseAccess.DatabaseHelperBuilder;
import com.codeflowcrafter.DatabaseAccess.Interfaces.IDatabaseHelperBuilder_Setup;
import com.codeflowcrafter.Sample.Project.Implementation.ContentProvider.ProjectProvider;

/**
 * Created by aiko on 5/1/17.
 */


public class SampleApplicationContentProviders extends BaseContentProviders {

    public static final String APPLICATION_NAME = "SampleApplication";
    public static final String DATABASE_TAG_NAME = "SampleDatabase";
    static final String DATABASE_FILENAME = "sample.db";

    ProjectProvider _projectProvider;

    IDatabaseHelperBuilder_Setup _dbHelperSetup;

    static SampleApplicationContentProviders instance = new SampleApplicationContentProviders();
    public static SampleApplicationContentProviders GetInstance(){ return instance;}

    private SampleApplicationContentProviders()
    {
        _projectProvider = new ProjectProvider();

        _dbHelperSetup = DatabaseHelperBuilder.GetInstance()
                .SetDatabase(DATABASE_TAG_NAME, DATABASE_FILENAME)
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
