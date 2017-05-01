package com.codeflowcrafter.Sample.Project;

import android.app.Activity;
import android.os.Bundle;

import com.codeflowcrafter.Sample.Project.Implementation.IProjectRequests;
import com.codeflowcrafter.Sample.Project.Implementation.IView_Project;
import com.codeflowcrafter.Sample.R;

/**
 * Created by aiko on 5/1/17.
 */

public class MainActivity
        extends Activity
    implements IView_Project
{
    IProjectRequests _viewRequest;

    public IProjectRequests GetViewRequest(){return _viewRequest;}
    public void SetViewRequest(IProjectRequests viewRequest){_viewRequest = viewRequest;}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_project_layout);
    }
}
