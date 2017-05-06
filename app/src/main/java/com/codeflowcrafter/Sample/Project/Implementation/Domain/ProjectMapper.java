package com.codeflowcrafter.Sample.Project.Implementation.Domain;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

import com.codeflowcrafter.PEAA.DataManipulation.BaseMapper;
import com.codeflowcrafter.PEAA.DataManipulation.BaseMapperInterfaces.IInvocationDelegates;
import com.codeflowcrafter.Sample.Project.Implementation.ContentProvider.ProjectTable;

import java.util.Hashtable;

/**
 * Created by aiko on 5/1/17.
 */

public class ProjectMapper extends BaseMapper<Project> {
    public final static String MAPPER_NAME = "Mapper Name";
    public final static String OPERATION = "Operation";
    public final static String COUNT = "Count";

    private ContentResolver _resolver;
    private Uri _uri;

    public ProjectMapper(ContentResolver resolver, Uri uri)
    {
        super(Project.class);

        _resolver = resolver;
        _uri = uri;
    }

    @Override
    public boolean ConcreteUpdate(Project project, IInvocationDelegates invocationDelegates) {
        int updatedRecords = 0;

        if(project == null)
            return false;

        String where = ProjectTable.COLUMN_ID + "=" +  project.GetId();

        updatedRecords = _resolver
                .update(
                        _uri,
                        EntityToContentValues(project),
                        where, null);

        Hashtable results = new Hashtable();

        results.put(MAPPER_NAME, this.getClass().getName());
        results.put(OPERATION, "Update");
        results.put(COUNT, String.valueOf(updatedRecords));

        invocationDelegates.SetResults(results);
        invocationDelegates.SuccessfulInvocation(project);

        return true;
    }

    @Override
    public boolean ConcreteInsert(Project project, IInvocationDelegates invocationDelegates) {
        _resolver.insert(_uri, EntityToContentValues(project));

        Hashtable results = new Hashtable();

        results.put(MAPPER_NAME, this.getClass().getName());
        results.put(OPERATION, "Insertion");
        results.put(COUNT, "1");

        invocationDelegates.SetResults(results);
        invocationDelegates.SuccessfulInvocation(project);

        return true;
    }

    @Override
    public boolean ConcreteDelete(Project project, IInvocationDelegates invocationDelegates) {
        String where = ProjectTable.COLUMN_ID + "=" +  project.GetId();

        int deletedRecords = _resolver
                .delete(_uri, where, null );

        Hashtable results = new Hashtable();

        results.put(MAPPER_NAME, this.getClass().getName());
        results.put(OPERATION, "Deletion");
        results.put(COUNT, String.valueOf(deletedRecords));

        invocationDelegates.SetResults(results);
        invocationDelegates.SuccessfulInvocation(project);

        return true;
    }

    private ContentValues EntityToContentValues(Project project)
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
