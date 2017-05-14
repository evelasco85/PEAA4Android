package com.codeflowcrafter.Sample.Project;

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
import android.widget.Toast;

import com.codeflowcrafter.Sample.Project.Implementation.Domain.Project;
import com.codeflowcrafter.Sample.Project.Implementation.MVP.IRequests_Project;
import com.codeflowcrafter.Sample.Project.Implementation.MVP.IView_Project;
import com.codeflowcrafter.Sample.Project.Implementation.MVP.Presenter_Project;
import com.codeflowcrafter.Sample.R;
import com.codeflowcrafter.Sample.SampleApplicationContentProviders;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aiko on 5/1/17.
 */

public class Activity_Main
        extends Activity
    implements IView_Project, LoaderManager.LoaderCallbacks<Cursor>
{
    private Button _btnAddProject;

    private Presenter_Project _presenter;
    private IRequests_Project _viewRequest;
    public IRequests_Project GetViewRequest(){return _viewRequest;}
    public void SetViewRequest(IRequests_Project viewRequest){
        _viewRequest = viewRequest;}

    private ArrayList<Project> _activityList;
    private Activity_Project_List_Item _activityAdapter;
    private Activity_Project_Fragment_List _listImplementation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_project);

        getLoaderManager().restartLoader(0, null, this);

        _activityList = new ArrayList<Project>();
        _activityAdapter = new Activity_Project_List_Item(this, _activityList);

        _presenter = new Presenter_Project(this);

        AssociateViewToLocalVar();
        SetViewHandlers();
        SetDefaultMainViewData();
    }

    private void AssociateViewToLocalVar()
    {
        _btnAddProject = (Button)findViewById(R.id.btnAddProject);
        _listImplementation = (Activity_Project_Fragment_List) getFragmentManager()
                .findFragmentById(R.id.fragment_projectList);
    }

    private void SetViewHandlers()
    {
        _btnAddProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _viewRequest.Prompt_AddProjectEntry();
            }
        });
    }

    private void SetDefaultMainViewData()
    {
        _listImplementation.setListAdapter(_activityAdapter);

        getLoaderManager().initLoader(0, null, this);
    }

    public void OnPromptExecution_AddProjectEntry()
    {
        Activity_Project_Dialog_AddEdit dialog = Activity_Project_Dialog_AddEdit
                .newInstance(Activity_Project_Dialog_AddEdit.ACTION_ADD, null);

        dialog.SetViewRequest(_viewRequest);
        dialog.show(getFragmentManager(), Activity_Project_Dialog_AddEdit.FRAGMENT_NAME);
    }

    public void OnPromptExecution_EditProjectEntry(Project project)
    {
        Activity_Project_Dialog_AddEdit dialog = Activity_Project_Dialog_AddEdit
                .newInstance(Activity_Project_Dialog_AddEdit.ACTION_EDIT, project);

        dialog.SetViewRequest(_viewRequest);
        dialog.show(getFragmentManager(), Activity_Project_Dialog_AddEdit.FRAGMENT_NAME);
    }

    public void OnPromptExecution_DeleteProjectEntry(final Project project)
    {
        AlertDialog.Builder verify = new AlertDialog.Builder(this);

        verify.setTitle("Are you sure you want to delete?");
        verify.setMessage("You are about to delete this item");

        verify.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg) {
                        _viewRequest.DeleteProject(project);
                        String message = project.GetName() + " item deleted";
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

    public Loader<Cursor> onCreateLoader(int id, Bundle args)
    {
        CursorLoader loader = new CursorLoader(this, SampleApplicationContentProviders.GetInstance().GetProjectProvider().GetContentUri(),
                null, null, null, null
        );

        return  loader;
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor)
    {
       _viewRequest.LoadProjectsViaLoader((CursorLoader)loader);
    }

    public void onLoaderReset(Loader<Cursor> loader){}

    @Override
    public void onResume()
    {
        super.onResume();
        getLoaderManager().restartLoader(0, null, this);
    }

    public void OnLoadProjectsViaLoaderCompletion(List<Project> projects)
    {
        _activityList.clear();
        _activityList.addAll(projects);
        _activityAdapter.notifyDataSetChanged();
    }

    public void OnPromptExecution_ProjectDetail(Project project)
    {
        Activity_Project_Dialog_Show_Detail dialog = Activity_Project_Dialog_Show_Detail
                .newInstance(project);

        dialog.show(getFragmentManager(), Activity_Project_Dialog_Show_Detail.FRAGMENT_NAME);
    }

    public void OnPromptExecution_AddAmountEntry(Project project)
    {
        Intent amountListIntent = new Intent(this, com.codeflowcrafter.Sample.Amount.Activity_Main.class);

        amountListIntent.setAction(com.codeflowcrafter.Sample.Amount.Activity_Main.ACTION_ADD);
        amountListIntent.putExtra(com.codeflowcrafter.Sample.Amount.Activity_Main.KEY_PROJECTID, project.GetId());
        amountListIntent.putExtra(com.codeflowcrafter.Sample.Amount.Activity_Main.KEY_PROJECTNAME, project.GetName());

        this.startActivity(amountListIntent);
    }

    public void OnPromptExecution_AmountList(Project project)
    {
        Intent amountListIntent = new Intent(this, com.codeflowcrafter.Sample.Amount.Activity_Main.class);

        amountListIntent.putExtra(com.codeflowcrafter.Sample.Amount.Activity_Main.KEY_PROJECTID, project.GetId());
        amountListIntent.putExtra(com.codeflowcrafter.Sample.Amount.Activity_Main.KEY_PROJECTNAME, project.GetName());

        this.startActivity(amountListIntent);
    }
}
