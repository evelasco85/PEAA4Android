package com.codeflowcrafter.DatabaseAccess;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;

import java.util.List;

/**
 * Created by aiko on 4/29/17.
 */

interface IMapperTemplate<TEntity>
{
    void SetContentResolver(ContentResolver resolver);
    boolean AddItem(TEntity newItem);
    int DeleteItem(int itemId);
    int UpdateItem(TEntity existingItem);
    void SetAddItemCallback(Runnable addItemCallback);
    void SetDeleteItemCallback(Runnable deleteItemCallback);
    void SetUpdateItemCallback(Runnable updateItemCallback);
    List LoadAll(Cursor cursor);
    void UpdateColumnOrdinals(Cursor cursor);
    TEntity CursorToEntity(Cursor cursor);
}

public abstract class MapperTemplate<TEntity> implements IMapperTemplate<TEntity> {
    ContentResolver _resolver;
    ContentProviderTemplate _providerTemplate;
    Runnable _addItemCallback, _deleteItemCallback, _updateItemCallback;

    protected ContentResolver GetContentResolver()
    {
        return  _resolver;
    }
    protected  ContentProviderTemplate GetProviderTemplate()
    {
        return _providerTemplate;
    }
    protected Runnable GetAddItemCallback()
    {
        return _addItemCallback;
    }
    protected Runnable GetDeleteItemCallback()
    {
        return _deleteItemCallback;
    }
    protected Runnable GetUpdateItemCallback()
    {
        return _updateItemCallback;
    }

    public MapperTemplate(ContentProviderTemplate providerTemplate)
    {
        _providerTemplate = providerTemplate;
    }

    public void SetContentResolver(ContentResolver resolver)
    {
        _resolver = resolver;
    }

    public Cursor GetCursor(Context context)
    {
        CursorLoader loader = new CursorLoader(context, this.GetProviderTemplate().GetContentUri(),
                null, null, null, null
        );
        Cursor cursor = loader.loadInBackground();

        return  cursor;
    }

    public List LoadAll(Context context)
    {
        Cursor cursor = this.GetCursor(context);
        List entityList = LoadAll(cursor);

        return  entityList;
    }

    public abstract List LoadAll(Cursor cursor);
    public abstract boolean AddItem(TEntity newItem);
    public abstract int UpdateItem(TEntity existingItem);
    public abstract int DeleteItem(int itemId);
    public abstract TEntity SelectFirst(Context context, int itemId);
    public abstract ContentValues EntityToContentValues(TEntity item);
    public abstract void UpdateColumnOrdinals(Cursor cursor);
    public abstract TEntity CursorToEntity(Cursor cursor);

    public void SetAddItemCallback(Runnable addItemCallback)
    {
        _addItemCallback = addItemCallback;
    }

    public void SetDeleteItemCallback(Runnable deleteItemCallback)
    {
        _deleteItemCallback = deleteItemCallback;
    }

    public void SetUpdateItemCallback(Runnable updateItemCallback)
    {
        _updateItemCallback = updateItemCallback;
    }
}
