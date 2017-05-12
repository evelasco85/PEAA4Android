package com.codeflowcrafter.Sample.Project;

import android.app.DialogFragment;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.codeflowcrafter.Sample.Project.Implementation.Domain.Project;
import com.codeflowcrafter.Sample.R;

/**
 * Created by aiko on 5/6/17.
 */

public class Activity_Project_Fragment_Show_Detail extends DialogFragment{
    private EditText _txtName, _txtDescription;
    private TextView _txtProjectId, _txtStartDate, _txtEndDate;

    private Project _projectToView;

    public void SetProjectToEdit(Project project){
        _projectToView = project;}

    public static final String FRAGMENT_NAME = "Show Project Details";

    public static Activity_Project_Fragment_Show_Detail newInstance(Project project)
    {
        Bundle args = new Bundle();

        Activity_Project_Fragment_Show_Detail fragment = new Activity_Project_Fragment_Show_Detail();

        fragment.setArguments(args);
        fragment.SetProjectToEdit(project);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_project_fragment_show_detail, container, false);

        AssociateViewToLocalVar(view);
        SetModelToViewData(_projectToView);

        return view;
    }

    private void AssociateViewToLocalVar(View view) {
        _txtProjectId = (TextView)view.findViewById(R.id.txtProjectId);
        _txtName = (EditText)view.findViewById(R.id.txtName);
        _txtDescription = (EditText) view.findViewById(R.id.txtDescription);
        _txtStartDate = (TextView) view.findViewById(R.id.txtStartDate);
        _txtEndDate = (TextView) view.findViewById(R.id.txtEndDate);

        _txtName.setEnabled(false);
        _txtName.setInputType(InputType.TYPE_NULL);
        _txtDescription.setEnabled(false);
        _txtDescription.setInputType(InputType.TYPE_NULL);
    }

    private void SetModelToViewData(Project project)
    {
        if(project == null)
            return;

        this._txtProjectId.setText(String.valueOf(project.GetId()));
        this._txtName.setText(project.GetName());
        this._txtDescription.setText(project.GetDescription());
        this._txtStartDate.setHint("");
        this._txtEndDate.setHint("");

        if(!TextUtils.isEmpty(project.GetCreatedDate()))
            this._txtStartDate.setText(project.GetCreatedDate());

        if(!TextUtils.isEmpty(project.GetEndedDate()))
            this._txtEndDate.setText(project.GetEndedDate());
    }
}