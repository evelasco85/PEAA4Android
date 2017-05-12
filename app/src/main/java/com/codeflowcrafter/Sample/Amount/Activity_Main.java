package com.codeflowcrafter.Sample.Amount;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import com.codeflowcrafter.Sample.Amount.Implementation.Domain.Amount;
import com.codeflowcrafter.Sample.R;
import com.codeflowcrafter.Sample.SampleApplicationContentProviders;

import java.util.ArrayList;

/**
 * Created by aiko on 5/1/17.
 */

public class Activity_Main extends Activity implements LoaderManager.LoaderCallbacks<Cursor>{
    public static final String FILTER_BY_PROJECTID = "ProjectId";

    private ArrayList<Amount> _activityList;
    private Activity_Amount_List_Item _activityAdapter;
    private Activity_Amount_Fragment_List _listImplementation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_amount);

        getLoaderManager().restartLoader(0, null, this);

        _activityList = new ArrayList<Amount>();
        _activityAdapter = new Activity_Amount_List_Item(this, _activityList);

        AssociateViewToLocalVar();
        SetViewHandlers();
        SetDefaultMainViewData();
    }

    public void AssociateViewToLocalVar()
    {
        _listImplementation = (Activity_Amount_Fragment_List) getFragmentManager()
                .findFragmentById(R.id.fragment_amountList);
    }

    public void SetViewHandlers()
    {
    }

    public void SetDefaultMainViewData()
    {
        _listImplementation.setListAdapter(_activityAdapter);
        getLoaderManager().initLoader(0, null, this);
    }

    public Loader<Cursor> onCreateLoader(int id, Bundle args)
    {
        CursorLoader loader = new CursorLoader(this, SampleApplicationContentProviders.GetInstance().GetAmountProvider().GetContentUri(),
                null, null, null, null
        );

        return  loader;
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor)
    {
    }

    public void onLoaderReset(Loader<Cursor> loader){}

    @Override
    public void onResume()
    {
        super.onResume();
        getLoaderManager().restartLoader(0, null, this);
    }
}
