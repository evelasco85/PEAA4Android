package com.codeflowcrafter.Sample.Project.Implementation.ContentProvider;

import android.app.SearchManager;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import com.codeflowcrafter.DatabaseAccess.ContentProviderTemplate;
import com.codeflowcrafter.DatabaseAccess.BaseTable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aiko on 5/1/17.
 */

public class ProjectTable extends BaseTable {
    public static final String TABLE_NAME = "project";

    /*COLUMNS SECTION HERE*/
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_STARTED_AT = "started_at";
    public static final String COLUMN_ENDED_AT = "ended_at";
    /**********************/

    public ProjectTable()
    {
        _search_projection_map = new HashMap<String, String>();

        _search_projection_map.put(SearchManager.SUGGEST_COLUMN_TEXT_1, COLUMN_DESCRIPTION + " AS " + SearchManager.SUGGEST_COLUMN_TEXT_1);
        _search_projection_map.put("_id", COLUMN_ID + " AS " + "_id");
    }

    public String GetRecordKeyColumnName()
    {
        return COLUMN_ID;
    }

    public String GetTableName()
    {
        return TABLE_NAME;
    }

    public String GetTableCreationScript()
    {
        HashMap<String, String> tableColumns = new HashMap<String, String>();

        tableColumns.put(COLUMN_ID, "integer primary key autoincrement");
        tableColumns.put(COLUMN_NAME, "TEXT");
        tableColumns.put(COLUMN_DESCRIPTION, "TEXT");
        tableColumns.put(COLUMN_STARTED_AT, "DATETIME default CURRENT_DATE");
        tableColumns.put(COLUMN_ENDED_AT, "DATETIME");

        String fields = "";
        String terminator = ", ";

        for(Map.Entry<String, String> entry : tableColumns.entrySet()) {
            fields += entry.getKey() + " " + entry.getValue() + terminator;
        }

        return "create table " + GetTableName() + " ( " +
                ((fields.length() > 0) ? fields.substring(0, fields.length() - terminator.length()) : fields) +
                " );";
    }

    public SQLiteQueryBuilder GetQueryBuilder(Uri uri)
    {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        queryBuilder.setTables(GetTableName());

        switch (GetUriMatcher().match(uri))
        {
            case ContentProviderTemplate.URI_SEARCH_SPECIFIC:
                queryBuilder.appendWhere(GetRecordKeyColumnName() + "=" + uri.getPathSegments().get(1));
                break;
            case ContentProviderTemplate.URI_SEARCH_GLOBAL:
                queryBuilder.appendWhere(COLUMN_DESCRIPTION + " LIKE \"% " + uri.getPathSegments().get(1) + "%\"");
                queryBuilder.setProjectionMap(GetSearchProjectionMap());
                break;
            default:
                break;
        }

        return queryBuilder;
    }
}
