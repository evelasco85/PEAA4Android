package com.codeflowcrafter.Sample.Project.Implementation.Domain;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;

import com.codeflowcrafter.PEAA.DataManipulation.BaseQueryObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aiko on 5/1/17.
 */

public class QueryAllProjects extends BaseQueryObject<Project, QueryAllProjects.Criteria> {
    Context _context;
    Uri _uri;
    ToProjectTranslator _translator = new ToProjectTranslator();

    public static class Criteria {
        public Criteria() {
        }
    }

    public QueryAllProjects(Context context, Uri uri) {
        super( QueryAllProjects.Criteria.class);

        _context = context;
        _uri = uri;
    }

    @Override
    public List<Project> PerformSearchOperation(QueryAllProjects.Criteria criteria) {

        CursorLoader loader = new CursorLoader(_context, _uri,
                null, null, null, null
        );

        Cursor cursor = loader.loadInBackground();
        List entityList = new ArrayList();

        if(cursor == null)
            return entityList;

        _translator.UpdateColumnOrdinals(cursor);

        while (cursor.moveToNext())
        {
            entityList.add(_translator.CursorToEntity(cursor));
        }

        return  entityList;
    }
}