package com.codeflowcrafter.DatabaseAccess;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by aiko on 5/1/17.
 */
interface IAdapterHelper<TEntity>
{
    Activity GetActivity();
    int GetResource();
    MapperTemplate<TEntity> GetMapper();
    LinearLayout GetLayout(View view, Context context, int resource);
    void SetItemViewHandler(LinearLayout itemLayout, TEntity item);
    void SetViewToLocalVarAssociation(LinearLayout itemLayout);
}

public abstract class AdapterHelper<TEntity> extends ArrayAdapter<TEntity> implements IAdapterHelper<TEntity> {
    int _resource;
    Activity _activity;
    MapperTemplate<TEntity> _itemMapper;

    public Activity GetActivity(){return _activity;}
    public int GetResource() {return _resource;}
    public MapperTemplate<TEntity> GetMapper(){return _itemMapper;}

    public AdapterHelper(Activity activity, int resource, List<TEntity> items, MapperTemplate<TEntity> itemMapper)
    {
        super( activity.getApplicationContext(), resource, items);

        _resource = resource;
        _activity = activity;
        _itemMapper = itemMapper;
    }

    public LinearLayout GetLayout(View view, Context context, int resource)
    {
        LinearLayout layout;

        if(view == null)
        {
            LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            layout = new LinearLayout(context);

            li.inflate(resource, layout, true);
        }
        else
            layout = (LinearLayout) view;

        return layout;
    }

    public abstract void SetItemViewHandler(LinearLayout itemLayout, TEntity item);
    public abstract void SetViewToLocalVarAssociation(LinearLayout itemLayout);
}