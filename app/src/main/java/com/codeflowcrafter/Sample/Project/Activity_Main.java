package com.codeflowcrafter.Sample.Project;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.codeflowcrafter.Sample.Project.Implementation.Domain.Project;
import com.codeflowcrafter.Sample.Project.Implementation.MVP.IProjectRequests;
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
    Presenter_Project _presenter;
    IProjectRequests _viewRequest;
    Button _btnAddProject;
    Activity_Main_Project_List _listImplementation;

    public IProjectRequests GetViewRequest(){return _viewRequest;}
    public void SetViewRequest(IProjectRequests viewRequest){
        _viewRequest = viewRequest;}

    ArrayList<Project> _activityList;
    Activity_Main_ProjectAdapter _activityAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_project);

        getLoaderManager().restartLoader(0, null, this);

        _activityList = new ArrayList<Project>();
        _activityAdapter = new Activity_Main_ProjectAdapter(this, _activityList);

        _presenter = new Presenter_Project(this);

        AssociateViewToLocalVar();
        SetViewHandlers();
        SetDefaultMainViewData();
    }

    public void AssociateViewToLocalVar()
    {
        _btnAddProject = (Button)findViewById(R.id.btnAddProject);
        _listImplementation = (Activity_Main_Project_List) getFragmentManager()
                .findFragmentById(R.id.projectList);
    }

    public void SetViewHandlers()
    {
        _btnAddProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _viewRequest.OpenAddProjectEntry();
            }
        });
    }

    public void SetDefaultMainViewData()
    {
        _listImplementation.setListAdapter(_activityAdapter);
        getLoaderManager().initLoader(0, null, this);
    }

    public void OnOpenAddProjectEntryCompletion()
    {
        Activity_Main_Fragment_Project_AddEdit fragment = Activity_Main_Fragment_Project_AddEdit
                .newInstance(Activity_Main_Fragment_Project_AddEdit.ACTION_ADD);

        fragment.SetViewRequest(_viewRequest);
        fragment.show(getFragmentManager(), Activity_Main_Fragment_Project_AddEdit.FRAGMENT_NAME);
    }

    public void OnOpenEditProjectEntryCompletion(Project project)
    {
        Activity_Main_Fragment_Project_AddEdit fragment = Activity_Main_Fragment_Project_AddEdit
                .newInstance(Activity_Main_Fragment_Project_AddEdit.ACTION_EDIT);

        fragment.SetViewRequest(_viewRequest);
        fragment.SetProjectToEdit(project);

        fragment.show(getFragmentManager(), Activity_Main_Fragment_Project_AddEdit.FRAGMENT_NAME);
    }

    public void OnPerformDeleteProjectEntryCompletion(final Project project)
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
}
