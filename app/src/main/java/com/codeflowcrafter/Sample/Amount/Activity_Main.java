package com.codeflowcrafter.Sample.Amount;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.codeflowcrafter.Sample.Amount.Implementation.Domain.Amount;
import com.codeflowcrafter.Sample.Amount.Implementation.MVP.IRequests_Amount;
import com.codeflowcrafter.Sample.Amount.Implementation.MVP.IView_Amount;
import com.codeflowcrafter.Sample.Amount.Implementation.MVP.Presenter_Amount;
import com.codeflowcrafter.Sample.R;
import com.codeflowcrafter.Sample.SampleApplicationContentProviders;

import java.util.ArrayList;
import java.util.List;

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
        _viewRequest.LoadAmountsViaLoader((CursorLoader)loader);
    }

    public void onLoaderReset(Loader<Cursor> loader){}

    @Override
    public void onResume()
    {
        super.onResume();
        getLoaderManager().restartLoader(0, null, this);
    }

    public void OnLoadAmountsViaLoaderCompletion(List<Amount> amounts)
    {
        _activityList.clear();
        _activityList.addAll(amounts);
        _activityAdapter.notifyDataSetChanged();
    }

    public void OnPromptExecution_AddAmountEntry()
    {
        Activity_Amount_Dialog_AddEdit dialog = Activity_Amount_Dialog_AddEdit
                .newInstance(Activity_Amount_Dialog_AddEdit.ACTION_ADD, _projectId, null);

        dialog.SetViewRequest(_viewRequest);
        dialog.show(getFragmentManager(), Activity_Amount_Dialog_AddEdit.FRAGMENT_NAME);
    }

    @Override
    public void OnPromptExecution_EditAmountEntry(Amount amount) {
        Activity_Amount_Dialog_AddEdit dialog = Activity_Amount_Dialog_AddEdit
                .newInstance(Activity_Amount_Dialog_AddEdit.ACTION_EDIT, _projectId, amount);

        dialog.SetViewRequest(_viewRequest);
        dialog.show(getFragmentManager(), Activity_Amount_Dialog_AddEdit.FRAGMENT_NAME);

    }

    @Override
    public void OnPromptExecution_DeleteAmountEntry(final Amount amount) {
        AlertDialog.Builder verify = new AlertDialog.Builder(this);

        verify.setTitle("Are you sure you want to delete?");
        verify.setMessage("You are about to delete this item");

        verify.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg) {
                        _viewRequest.DeleteAmount(amount);
                        String message = "Amount of " + amount.GetAmount() + " is deleted";
                        Toast
                                .makeText(getApplicationContext(), message, Toast.LENGTH_SHORT)
                                .show();
                    }
                }
        );

        verify.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg) {
                        //No action takes
                    }
                }
        );

        verify.show();
    }

    @Override
    public void OnPromptExecution_AmountDetail(Amount amount) {
        Activity_Amount_Dialog_Show_Detail dialog = Activity_Amount_Dialog_Show_Detail
                .newInstance(amount);

        dialog.show(getFragmentManager(), Activity_Amount_Dialog_Show_Detail.FRAGMENT_NAME);

    }
}
