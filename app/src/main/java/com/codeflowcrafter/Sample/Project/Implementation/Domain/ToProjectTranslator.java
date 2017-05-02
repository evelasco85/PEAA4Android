package com.codeflowcrafter.Sample.Project.Implementation.Domain;

import android.database.Cursor;

import com.codeflowcrafter.Sample.Project.Implementation.ContentProvider.ProjectModel;
import com.codeflowcrafter.Sample.Project.Implementation.ContentProvider.ProjectTable;

/**
 * Created by aiko on 5/1/17.
 */

public class ToProjectTranslator {
    int _idIndex = 0;
    int _nameIndex = 0;
    int _descriptionIndex = 0;
    int _startedDate = 0;
    int _endedDate = 0;

    //Base class of Query Object and Data Mapper
    public void UpdateColumnOrdinals(Cursor cursor)
    {
        _idIndex = cursor.getColumnIndexOrThrow(ProjectTable.COLUMN_ID);
        _nameIndex = cursor.getColumnIndexOrThrow(ProjectTable.COLUMN_NAME);
        _descriptionIndex = cursor.getColumnIndexOrThrow(ProjectTable.COLUMN_DESCRIPTION);
        _startedDate = cursor.getColumnIndexOrThrow(ProjectTable.COLUMN_STARTED_AT);
        _endedDate = cursor.getColumnIndexOrThrow(ProjectTable.COLUMN_ENDED_AT);
    }

    //Query Object
    public ProjectModel CursorToEntity(Cursor cursor)
    {
        return new ProjectModel(
                cursor.getInt(_idIndex),
                cursor.getString(_nameIndex),
                cursor.getString(_descriptionIndex),
                cursor.getString(_startedDate),
                cursor.getString(_endedDate)
        );
    }
}