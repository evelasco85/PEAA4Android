package com.codeflowcrafter.Sample.Amount.Implementation.Domain;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.codeflowcrafter.PEAA.DataManipulation.BaseQueryObject;
import com.codeflowcrafter.Sample.Amount.Implementation.ContentProvider.AmountTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aiko on 5/14/17.
 */

public class QueryAmountByProjectId extends BaseQueryObject<Amount, QueryAmountByProjectId.Criteria> {
    private Context _context;
    private Uri _uri;
    private ToAmountTranslator _translator = new ToAmountTranslator();

    public static class Criteria {
        public int ProjectId;

        public Criteria(int projectId) {
            ProjectId = projectId;
        }
    }

    public QueryAmountByProjectId(Context context, Uri uri) {
        super( QueryAmountByProjectId.Criteria.class);

        _context = context;
        _uri = uri;
    }

    @Override
    public List<Amount> PerformSearchOperation(Criteria criteria) {
        String where = AmountTable.COLUMN_PROJECT_ID + "=" +  criteria.ProjectId;
        Cursor cursor = _context.getContentResolver().query(_uri, null, where, null, null);
        List entityList = new ArrayList();

        if(cursor == null) {
            return entityList;
        }

        _translator.UpdateColumnOrdinals(cursor);

        if(cursor.moveToFirst()) {
            entityList.add(_translator.CursorToEntity(cursor));
        }

        return entityList;
    }
}
