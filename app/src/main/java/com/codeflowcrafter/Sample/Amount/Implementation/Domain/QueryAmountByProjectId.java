package com.codeflowcrafter.Sample.Amount.Implementation.Domain;

import android.content.Context;
import android.net.Uri;

import com.codeflowcrafter.PEAA.DataManipulation.BaseQueryObject;

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
        return null;
    }
}
