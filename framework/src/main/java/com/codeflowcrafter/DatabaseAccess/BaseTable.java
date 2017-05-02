package com.codeflowcrafter.DatabaseAccess;

import android.content.UriMatcher;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import java.util.HashMap;

/**
 * Created by aiko on 4/29/17.
 */
interface IBaseTable
{
    Uri GetContentUri();
    String GetRecordKeyColumnName();
    String GetTableName();
    String GetTableCreationScript();
    HashMap<String, String> GetSearchProjectionMap();
    SQLiteQueryBuilder GetQueryBuilder(Uri uri);
}

public abstract class BaseTable implements IBaseTable {
    public abstract String GetRecordKeyColumnName();
    public abstract String GetTableName();
    public abstract String GetTableCreationScript();
    public abstract SQLiteQueryBuilder GetQueryBuilder(Uri uri);

    ContentProviderTemplate _contentProvider;
    protected HashMap<String, String> _search_projection_map;

    public HashMap<String, String> GetSearchProjectionMap()
    {
        return _search_projection_map;
    }

    public Uri GetContentUri()
    {

        return _contentProvider.GetContentUri();
    }

    public UriMatcher GetUriMatcher()
    {
        return _contentProvider.GetUriMatcher();
    }

    public void SetContentProviderTemplate(ContentProviderTemplate contentProviderTemplate)
    {
        _contentProvider = contentProviderTemplate;
    }
}
