package com.codeflowcrafter.Sample.Project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codeflowcrafter.Sample.Project.Implementation.IProjectRequests;
import com.codeflowcrafter.Sample.Project.Implementation.IView_Project;
import com.codeflowcrafter.Sample.Project.Implementation.Presenter_Project;
import com.codeflowcrafter.Sample.R;

/**
 * Created by aiko on 5/1/17.
 */

public class MainActivity
        extends Activity
    implements IView_Project
{
    Presenter_Project _presenter;
    IProjectRequests _viewRequest;
    Button _btnAddProject;
    Fragment_Project_List _listImplementation;

    public static final int REQUEST_BY_PROJECT_ADD = 1;
    public static final int REQUEST_BY_PROJECT_EDIT = 2;

    public IProjectRequests GetViewRequest(){return _viewRequest;}
    public void SetViewRequest(IProjectRequests viewRequest){_viewRequest = viewRequest;}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_project_layout);

        _presenter = new Presenter_Project(this);

        AssociateViewToLocalVar();
        SetViewHandlers();
    }

    void AssociateViewToLocalVar()
    {
        _btnAddProject = (Button)findViewById(R.id.btnAddProject);
        _listImplementation = (Fragment_Project_List) getFragmentManager()
                .findFragmentById(R.id.projectList);
    }

    void SetViewHandlers()
    {
        _btnAddProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _viewRequest.OpenProjectEntry();
            }
        });
    }

    public void OnOpenProjectEntryCompletion()
    {
        Intent projectEntryIntent = new Intent(this, ProjectActivity.class);

        projectEntryIntent.setAction(ProjectActivity.ACTION_ADD);

        this.startActivityForResult(projectEntryIntent, MainActivity.REQUEST_BY_PROJECT_ADD);
    }
}
