package com.codeflowcrafter.Sample.Amount;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codeflowcrafter.Sample.Amount.Implementation.Domain.Amount;
import com.codeflowcrafter.Sample.Amount.Implementation.MVP.IRequests_Amount;
import com.codeflowcrafter.Sample.Amount.Implementation.MVP.IView_Amount;
import com.codeflowcrafter.Sample.Amount.Implementation.MVP.Presenter_Amount;
import com.codeflowcrafter.Sample.R;
import com.codeflowcrafter.Sample.SampleApplicationContentProviders;

import java.util.ArrayList;

/**
 * Created by aiko on 5/1/17.
 */

public class Activity_Main extends Activity implements IView_Amount, LoaderManager.LoaderCallbacks<Cursor>{
    private TextView _txtProjectId;
    private TextView _txtProjectName;
    private Button _btnAddAmount;

    private Presenter_Amount _presenter;
    private IRequests_Amount _viewRequest;
    public IRequests_Amount GetViewRequest(){return _viewRequest;}
    public void SetViewRequest(IRequests_Amount viewRequest){
        _viewRequest = viewRequest;}

    public static final String KEY_PROJECTID = "Project Id";
    public static final String KEY_PROJECTNAME = "Project Name";
    public static final String ACTION_NONE = "NONE";
    public static final String ACTION_ADD = "ADD";

    private ArrayList<Amount> _activityList;
    private Activity_Amount_List_Item _activityAdapter;
    private Activity_Amount_Fragment_List _listImplementation;

    private String _action = ACTION_NONE;
    private int _projectId = 0;
    private String _projectName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_amount);

        getLoaderManager().restartLoader(0, null, this);

        _activityList = new ArrayList<Amount>();
        _activityAdapter = new Activity_Amount_List_Item(this, _activityList);

        _presenter = new Presenter_Amount(this);
        Intent invoker = getIntent();

        if(invoker != null) {
            _projectId = invoker.getIntExtra(KEY_PROJECTID, 0);
            _projectName = invoker.getStringExtra(KEY_PROJECTNAME);
            _action = invoker.getAction();
        }

        AssociateViewToLocalVar();
        SetViewHandlers();
        SetDefaultMainViewData();
        PerformAction();
    }

    private void AssociateViewToLocalVar()
    {
        _listImplementation = (Activity_Amount_Fragment_List) getFragmentManager()
                .findFragmentById(R.id.fragment_amountList);
        _txtProjectId = (TextView) findViewById(R.id.txtProjectId);
        _txtProjectName = (TextView) findViewById(R.id.txtProjectName);
        _btnAddAmount = (Button)findViewById(R.id.btnAddAmount);
    }

    private void SetViewHandlers()
    {
        _btnAddAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _viewRequest.Prompt_AddAmountEntry();
            }
        });
    }

    private void SetDefaultMainViewData()
    {
        _txtProjectId.setText(String.valueOf(_projectId));
        _txtProjectName.setText(String.valueOf(_projectName));

        _listImplementation.setListAdapter(_activityAdapter);
        getLoaderManager().initLoader(0, null, this);
    }

    private void PerformAction()
    {
        if(_action == ACTION_ADD) _viewRequest.Prompt_AddAmountEntry();
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

    public void OnPromptExecution_AddAmountEntry()
    {
        Activity_Amount_Dialog_AddEdit fragment = Activity_Amount_Dialog_AddEdit
                .newInstance(Activity_Amount_Dialog_AddEdit.ACTION_ADD, null);

        fragment.SetViewRequest(_viewRequest);
        fragment.show(getFragmentManager(), Activity_Amount_Dialog_AddEdit.FRAGMENT_NAME);
    }
}
