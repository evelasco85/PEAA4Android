package com.codeflowcrafter.Sample.Project;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codeflowcrafter.Sample.Project.Implementation.ContentProvider.ProjectModel;
import com.codeflowcrafter.Sample.Project.Implementation.MVP.IProjectRequests;
import com.codeflowcrafter.Sample.Project.Implementation.MVP.IView_Project;
import com.codeflowcrafter.Sample.Project.Implementation.MVP.Presenter_Project;
import com.codeflowcrafter.Sample.R;
import com.codeflowcrafter.Sample.SampleApplicationContentProviders;

import java.util.ArrayList;

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
    Activity_Main_Fragment_Project_List _listImplementation;

    public static final int REQUEST_BY_PROJECT_ADD = 1;
    public static final int REQUEST_BY_PROJECT_EDIT = 2;

    public IProjectRequests GetViewRequest(){return _viewRequest;}
    public void SetViewRequest(IProjectRequests viewRequest){_viewRequest = viewRequest;}

    ArrayList<ProjectModel> _activityList;
    Activity_Main_ProjectAdapter _activityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_project_layout);

        getLoaderManager().restartLoader(0, null, this);

        _activityList = new ArrayList<ProjectModel>();
        _activityAdapter = new Activity_Main_ProjectAdapter(this,
                R.layout.activity_project_layout_fragment_listitem,
                _activityList);


        _presenter = new Presenter_Project(this);

        AssociateViewToLocalVar();
        SetViewHandlers();
        SetDefaultMainViewData();
    }

    public void AssociateViewToLocalVar()
    {
        _btnAddProject = (Button)findViewById(R.id.btnAddProject);
        _listImplementation = (Activity_Main_Fragment_Project_List) getFragmentManager()
                .findFragmentById(R.id.projectList);
    }

    public void SetViewHandlers()
    {
        _btnAddProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _viewRequest.OpenProjectEntry();
            }
        });
    }

    public void SetDefaultMainViewData()
    {
        _listImplementation.setListAdapter(_activityAdapter);
        getLoaderManager().initLoader(0, null, this);
    }

    public void OnOpenProjectEntryCompletion()
    {
        Intent projectEntryIntent = new Intent(this, Activity_Project.class);

        projectEntryIntent.setAction(Activity_Project.ACTION_ADD);

        this.startActivityForResult(projectEntryIntent, Activity_Main.REQUEST_BY_PROJECT_ADD);
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
        _activityList.clear();
//        _activityList.addAll(SampleApplicationContentProviders.GetInstance().GetProjectMapper().LoadAll(cursor));
        _activityAdapter.notifyDataSetChanged();
    }

    public void onLoaderReset(Loader<Cursor> loader){}

    @Override
    public void onResume()
    {
        super.onResume();
        getLoaderManager().restartLoader(0, null, this);
    }
}
