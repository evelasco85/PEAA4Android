package com.codeflowcrafter.Sample.Amount.Implementation.ContentProvider;

import android.app.SearchManager;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import com.codeflowcrafter.DatabaseAccess.BaseTable;
import com.codeflowcrafter.DatabaseAccess.ContentProviderTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aiko on 5/12/17.
 */

public class AmountTable extends BaseTable {
    public static final String TABLE_NAME = "amount";

    /*COLUMNS SECTION HERE*/
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PROJECT_ID = "project_id";
    public static final String COLUMN_CREATED_DATE = "created_date";
    public static final String COLUMN_CREATED_TIME = "created_time";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_EXPENSE_ENTRY = "expense_entry";
    public static final String COLUMN_DESCRIPTION = "description";
    /**********************/

    public AmountTable()
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
        tableColumns.put(COLUMN_PROJECT_ID, "integer");
        tableColumns.put(COLUMN_CREATED_DATE, "DATETIME");
        tableColumns.put(COLUMN_CREATED_TIME, "DATETIME default CURRENT_DATE");
        tableColumns.put(COLUMN_AMOUNT, "REAL");
        tableColumns.put(COLUMN_EXPENSE_ENTRY, "integer");
        tableColumns.put(COLUMN_DESCRIPTION, "TEXT");

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
