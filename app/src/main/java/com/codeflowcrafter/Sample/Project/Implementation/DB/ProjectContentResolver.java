package com.codeflowcrafter.Sample.Project.Implementation.DB;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;

import com.codeflowcrafter.Sample.ContentProviders.Project.ProjectModel;
import com.codeflowcrafter.Sample.ContentProviders.Project.ProjectTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aiko on 5/1/17.
 */

public class ProjectContentResolver {
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

    //Query Object
    public ProjectModel SelectFirst(ContentResolver resolver, Uri uri, int projectId)
    {
        String where = ProjectTable.COLUMN_ID + "=" +  projectId;
        Cursor cursor = resolver.query(uri, null, where, null, null);
        ProjectModel project = new ProjectModel(0, "", "", "");

        if(cursor == null)
            return project;

        UpdateColumnOrdinals(cursor);

        if(cursor.moveToFirst())
            project = CursorToEntity(cursor);

        return project;
    }

    //Data Mapper
    public ContentValues EntityToContentValues(ProjectModel project)
    {
        ContentValues values = new ContentValues();

        if(project.GetId() > 0)
            values.put(ProjectTable.COLUMN_ID, project.GetId());

        values.put(ProjectTable.COLUMN_NAME, project.GetName());
        values.put(ProjectTable.COLUMN_DESCRIPTION, project.GetDescription());
        values.put(ProjectTable.COLUMN_STARTED_AT, project.GetCreatedDate());
        values.put(ProjectTable.COLUMN_ENDED_AT, project.GetEndedDate());

        return values;
    }

    //Data mapper
    public boolean AddItem(ContentResolver resolver, Uri uri, ProjectModel project)
    {
        resolver.insert(uri, EntityToContentValues(project));

        return true;
    }

    //Data mapper
    public int UpdateItem(ContentResolver resolver, Uri uri, ProjectModel existingProject)
    {
        int updatedRecords = 0;

        if(existingProject == null)
            return updatedRecords;

        String where = ProjectTable.COLUMN_ID + "=" +  existingProject.GetId();

        updatedRecords = resolver
                .update(
                        uri,
                        EntityToContentValues(existingProject),
                        where, null);

        return updatedRecords;
    }

    //Data mapper
    public int DeleteItem(ContentResolver resolver, Uri uri, int projectId)
    {
        String where = ProjectTable.COLUMN_ID + "=" +  projectId;

        int deletedRecords = resolver
                .delete(uri, where, null );

        return  deletedRecords;
    }

    //Query Object
    public List LoadAll(Cursor cursor)
    {
        List entityList = new ArrayList();

        if(cursor == null)
            return entityList;

        UpdateColumnOrdinals(cursor);

        while (cursor.moveToNext())
        {
            entityList.add(CursorToEntity(cursor));
        }

        return  entityList;
    }

    public List LoadAll(Context context, Uri uri)
    {
        CursorLoader loader = new CursorLoader(context, uri,
                null, null, null, null
        );

        Cursor cursor = loader.loadInBackground();
        List entityList = LoadAll(cursor);

        return  entityList;
    }
}