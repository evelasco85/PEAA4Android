package com.codeflowcrafter.Sample.Project.Implementation.Domain;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

import com.codeflowcrafter.PEAA.DataManipulation.BaseMapper;
import com.codeflowcrafter.PEAA.DataManipulation.BaseMapperInterfaces.InvocationDelegates;
import com.codeflowcrafter.Sample.Project.Implementation.ContentProvider.ProjectTable;

/**
 * Created by aiko on 5/1/17.
 */

public class ProjectMapper extends BaseMapper<Project> {
    private ContentResolver _resolver;
    private Uri _uri;

    public ProjectMapper(ContentResolver resolver, Uri uri)
    {
        super(Project.class);

        _resolver = resolver;
        _uri = uri;
    }

    @Override
    public boolean ConcreteUpdate(Project project, InvocationDelegates invocationDelegates) {
        int updatedRecords = 0;

        if(project == null)
            return false;

        String where = ProjectTable.COLUMN_ID + "=" +  project.GetId();

        updatedRecords = _resolver
                .update(
                        _uri,
                        EntityToContentValues(project),
                        where, null);

        return true;
    }

    @Override
    public boolean ConcreteInsert(Project project, InvocationDelegates invocationDelegates) {
        _resolver.insert(_uri, EntityToContentValues(project));

        return true;
    }

    @Override
    public boolean ConcreteDelete(Project project, InvocationDelegates invocationDelegates) {
        String where = ProjectTable.COLUMN_ID + "=" +  project.GetId();

        int deletedRecords = _resolver
                .delete(_uri, where, null );

        return true;
    }

    ContentValues EntityToContentValues(Project project)
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
}
