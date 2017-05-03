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
import com.codeflowcrafter.Sample.Project.Implementation.MVP.IProjectRequests;
import com.codeflowcrafter.Sample.R;
import com.codeflowcrafter.UI.Date.Fragment_DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by aiko on 5/3/17.
 */

public class Activity_Main_Fragment_Project_AddEdit extends DialogFragment{
    Button _btnSave, _btnCancel;
    EditText _txtName, _txtDescription;
    TextView _txtStartDate, _txtEndDate;

    public static final String FRAGMENT_NAME = "Add/Edit Project";

    public static final String ACTION_ADD = "ADD";
    public static final String ACTION_EDIT = "EDIT";

    private static String KEY_ACTION = "action";
    private static String KEY_PROJECT_ID = "project id";
    private String _selectedAction;
    private int _projectId;

    private IProjectRequests _viewRequest;

    public void SetViewRequest(IProjectRequests viewRequest)
    {
        _viewRequest = viewRequest;
    }

    public static Activity_Main_Fragment_Project_AddEdit newInstance(String action, int projectId)
    {
        Bundle args = new Bundle();

        args.putString(KEY_ACTION, action);
        args.putInt(KEY_PROJECT_ID, projectId);

        Activity_Main_Fragment_Project_AddEdit fragment = new Activity_Main_Fragment_Project_AddEdit();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        _selectedAction = getArguments().getString(KEY_ACTION);
        _projectId = getArguments().getInt(KEY_PROJECT_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_main_fragment_project_add_edit, container, false);

        AssociateViewToLocalVar(view);
        SetViewHandlers();

        return view;
    }

    void AssociateViewToLocalVar(View view) {
        _btnSave = (Button)view.findViewById(R.id.btnSave);
        _btnCancel = (Button)view.findViewById(R.id.btnCancel);
        _txtName = (EditText)view.findViewById(R.id.txtName);
        _txtDescription = (EditText) view.findViewById(R.id.txtDescription);
        _txtStartDate = (TextView) view.findViewById(R.id.txtStartDate);
        _txtEndDate = (TextView) view.findViewById(R.id.txtEndDate);
    }

    void SetViewHandlers()
    {
        _btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InvokeActionBasedPersistency();
                dismiss();
            }
        });

        _btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    public void InvokeActionBasedPersistency()
    {
        switch (_selectedAction)
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

    public Project ViewDataToModel()
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

    public void ModelToViewData(Project project)
    {
        if(project == null)
            return;

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
