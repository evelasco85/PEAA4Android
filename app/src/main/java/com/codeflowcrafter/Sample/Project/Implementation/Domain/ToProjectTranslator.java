package com.codeflowcrafter.Sample.Project.Implementation.Domain;

import android.database.Cursor;

import com.codeflowcrafter.PEAA.DataSynchronizationManager;
import com.codeflowcrafter.Sample.Project.Implementation.ContentProvider.ProjectTable;

/**
 * Created by aiko on 5/1/17.
 */

public class ToProjectTranslator {
    int _idIndex = 0;
    int _nameIndex = 0;
    int _descriptionIndex = 0;
    int _startedDateIndex = 0;
    int _endedDateIndex = 0;
    int _totalIndex = 0;

    //Base class of Query Object and Data Mapper
    public void UpdateColumnOrdinals(Cursor cursor)
    {
        _idIndex = cursor.getColumnIndexOrThrow(ProjectTable.COLUMN_ID);
        _nameIndex = cursor.getColumnIndexOrThrow(ProjectTable.COLUMN_NAME);
        _descriptionIndex = cursor.getColumnIndexOrThrow(ProjectTable.COLUMN_DESCRIPTION);
        _startedDateIndex = cursor.getColumnIndexOrThrow(ProjectTable.COLUMN_STARTED_AT);
        _endedDateIndex = cursor.getColumnIndexOrThrow(ProjectTable.COLUMN_ENDED_AT);
        _totalIndex = cursor.getColumnIndexOrThrow(ProjectTable.COLUMN_TOTAL);
    }

    //non-static to avoid race condition
    public Project CursorToEntity(Cursor cursor)
    {
        return new Project(DataSynchronizationManager.GetInstance().GetMapper(Project.class),
                cursor.getInt(_idIndex),
                cursor.getString(_nameIndex),
                cursor.getString(_descriptionIndex),
                cursor.getString(_startedDateIndex),
                cursor.getString(_endedDateIndex),
                cursor.getDouble(_totalIndex)
        );
    }
}