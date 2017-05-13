package com.codeflowcrafter.Sample.Project;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.codeflowcrafter.PEAA.DataSynchronizationManager;
import com.codeflowcrafter.Sample.Project.Implementation.Domain.Project;
import com.codeflowcrafter.Sample.Project.Implementation.MVP.IRequests_Project;
import com.codeflowcrafter.Sample.R;
import com.codeflowcrafter.UI.Date.Fragment_DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by aiko on 5/3/17.
 */

public class Activity_Project_Dialog_AddEdit extends DialogFragment{
    private Button _btnSave, _btnCancel;
    private EditText _txtName, _txtDescription;
    private TextView _txtStartDate, _txtEndDate;

    public static final String FRAGMENT_NAME = "Add/Edit Project";

    public static final String ACTION_ADD = "ADD";
    public static final String ACTION_EDIT = "EDIT";

    private static final String KEY_ACTION = "action";

    private IRequests_Project _viewRequest;
    private Project _projectToEdit;
    private int _projectId = 0;

    public void SetViewRequest(IRequests_Project viewRequest)
    {
        _viewRequest = viewRequest;
    }
    public void SetProjectToEdit(Project project){_projectToEdit = project;}

    public static Activity_Project_Dialog_AddEdit newInstance(String action, Project project)
    {
        Bundle args = new Bundle();

        args.putString(KEY_ACTION, action);

        Activity_Project_Dialog_AddEdit fragment = new Activity_Project_Dialog_AddEdit();

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
        View view = inflater.inflate(R.layout.activity_project_fragment_add_edit, container, false);

        AssociateViewToLocalVar(view);

        String selectedAction = getArguments().getString(KEY_ACTION);

        SetViewHandlers(selectedAction);

        if(selectedAction == ACTION_EDIT) SetModelToViewData(_projectToEdit);

        return view;
    }

    private void AssociateViewToLocalVar(View view) {
        _btnSave = (Button)view.findViewById(R.id.btnSave);
        _btnCancel = (Button)view.findViewById(R.id.btnCancel);
        _txtName = (EditText)view.findViewById(R.id.txtName);
        _txtDescription = (EditText) view.findViewById(R.id.txtDescription);
        _txtStartDate = (TextView) view.findViewById(R.id.txtStartDate);
        _txtEndDate = (TextView) view.findViewById(R.id.txtEndDate);
    }

    private void SetViewHandlers(final String selectedAction)
    {
        _btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InvokeActionBasedPersistency(selectedAction);
                dismiss();
            }
        });

        _btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _viewRequest.CancelProjectEntry();
                dismiss();
            }
        });

        _txtStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_DatePicker dateFragment = new Fragment_DatePicker();

                dateFragment.SetDefaultDate(_txtStartDate.getText().toString());
                dateFragment.SetOnDateSetListener(new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day)
                    {
                        Calendar cal = Calendar.getInstance();

                        cal.set(Calendar.YEAR, year);
                        cal.set(Calendar.MONTH, month);
                        cal.set(Calendar.DAY_OF_MONTH, day);

                        _txtStartDate.setText((new SimpleDateFormat("yyyy-MM-dd")).format(cal.getTime()));
                    }
                });
                dateFragment.show(getFragmentManager(), "datePicker");
            }
        });

        _txtEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_DatePicker dateFragment = new Fragment_DatePicker();

                dateFragment.SetDefaultDate(_txtEndDate.getText().toString());
                dateFragment.SetOnDateSetListener(new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day)
                    {
                        Calendar cal = Calendar.getInstance();

                        cal.set(Calendar.YEAR, year);
                        cal.set(Calendar.MONTH, month);
                        cal.set(Calendar.DAY_OF_MONTH, day);

                        _txtEndDate.setText((new SimpleDateFormat("yyyy-MM-dd")).format(cal.getTime()));
                    }
                });
                dateFragment.show(getFragmentManager(), "datePicker");
            }
        });
    }

    private void InvokeActionBasedPersistency(String selectedAction)
    {
        switch (selectedAction)
        {
            case ACTION_ADD:
                _viewRequest.AddProject(ViewDataToModel());
                break;
            case ACTION_EDIT:
                _viewRequest.UpdateProject(ViewDataToModel());
                break;
            default:
                break;
        }
    }

    private Project ViewDataToModel()
    {
        return new Project(
                DataSynchronizationManager.GetInstance().GetMapper(Project.class),
                _projectId,
                _txtName.getText().toString(),
                _txtDescription.getText().toString(),
                _txtStartDate.getText().toString(),
                _txtEndDate.getText().toString()
        );
    }

    private void SetModelToViewData(Project project)
    {
        if(project == null)
            return;

        this._projectId = project.GetId();
        this._txtName.setText(project.GetName());
        this._txtDescription.setText(project.GetDescription());

        if(!TextUtils.isEmpty(project.GetCreatedDate()))
            this._txtStartDate.setText(project.GetCreatedDate());

        if(!TextUtils.isEmpty(project.GetEndedDate()))
            this._txtEndDate.setText(project.GetEndedDate());
    }

    @Override
    public void onDestroyView()
    {
        try{
            Fragment childFragment = getFragmentManager().findFragmentById(R.id.saveCancelFragmentPlaceholder);

            FragmentTransaction transaction = getFragmentManager()
                    .beginTransaction();

            transaction.remove(childFragment);

            transaction.commit();
        }catch(Exception e){
        }

        super.onDestroyView();
    }
}
