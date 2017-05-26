package com.codeflowcrafter.Sample.Project.Implementation.Domain;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.codeflowcrafter.PEAA.DataManipulation.BaseQueryObject;
import com.codeflowcrafter.Sample.Project.Implementation.ContentProvider.ProjectTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aiko on 5/1/17.
 */

public class QueryProjectById extends BaseQueryObject<Project, QueryProjectById.Criteria> {
    private Context _context;
    private Uri _uri;
    private ToProjectTranslator _translator = new ToProjectTranslator();

    public static class Criteria {
        public int ProjectId;

        public Criteria(int projectId) {
            ProjectId = projectId;
        }
    }

    public QueryProjectById(Context context, Uri uri) {
        super( QueryProjectById.Criteria.class);

        _context = context;
        _uri = uri;
    }

    @Override
    public List<Project> PerformSearchOperation(Criteria criteria) {
        String where = ProjectTable.COLUMN_ID + "=" +  criteria.ProjectId;
        Cursor cursor = _context.getContentResolver().query(_uri, null, where, null, null);
        List entityList = new ArrayList();

        if(cursor == null) {
            return entityList;
        }

        _translator.UpdateColumnOrdinals(cursor);

        if(cursor.moveToFirst()) {
            entityList.add(_translator.CursorToEntity(cursor));
            cursor.close();
        }

        return entityList;
    }
}
