package com.codeflowcrafter.Sample.Project.Implementation.DB;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.codeflowcrafter.DatabaseAccess.ContentProviderTemplate;
import com.codeflowcrafter.DatabaseAccess.Deprecated.MapperTemplate;
import com.codeflowcrafter.Sample.ContentProviders.Project.ProjectModel;
import com.codeflowcrafter.Sample.ContentProviders.Project.ProjectTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aiko on 5/1/17.
 */

public class ProjectMapper extends MapperTemplate<ProjectModel> {
    public ProjectMapper(ContentProviderTemplate provider)
    {
        super(provider);
    }

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
    public ProjectModel SelectFirst(Context context, int projectId)
    {
        ContentResolver resolver = GetContentResolver();
        String where = ProjectTable.COLUMN_ID + "=" +  projectId;
        Cursor cursor = resolver.query(GetProviderTemplate().GetContentUri(), null, where, null, null);
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
    public boolean AddItem(ProjectModel project)
    {
        ContentResolver resolver = GetContentResolver();

        resolver.insert(
                GetProviderTemplate().GetContentUri(),
                EntityToContentValues(project));

        if(GetAddItemCallback() != null)
            GetAddItemCallback().run();

        return true;
    }

    //Data mapper
    public int UpdateItem(ProjectModel existingProject)
    {
        int updatedRecords = 0;

        if(existingProject == null)
            return updatedRecords;

        ContentResolver resolver = GetContentResolver();
        String where = ProjectTable.COLUMN_ID + "=" +  existingProject.GetId();

        updatedRecords = resolver
                .update(
                        GetProviderTemplate().GetContentUri(),
                        EntityToContentValues(existingProject),
                        where, null);

        if(GetUpdateItemCallback() != null)
            GetUpdateItemCallback().run();

        return updatedRecords;
    }

    //Data mapper
    public int DeleteItem(int projectId)
    {
        ContentResolver resolver = GetContentResolver();
        String where = ProjectTable.COLUMN_ID + "=" +  projectId;

        int deletedRecords = resolver
                .delete(GetProviderTemplate().GetContentUri(), where, null );

        if(GetDeleteItemCallback() != null)
            GetDeleteItemCallback().run();

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
}