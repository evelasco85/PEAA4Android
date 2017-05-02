package com.codeflowcrafter.Sample.Project;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.codeflowcrafter.PEAA.DataSynchronizationManager;
import com.codeflowcrafter.Sample.Amount.MainActivity;
import com.codeflowcrafter.Sample.Project.Implementation.Domain.Project;
import com.codeflowcrafter.Sample.Project.Implementation.MVP.IProjectRequests;
import com.codeflowcrafter.Sample.R;
import com.codeflowcrafter.UI.Date.Fragment_DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by aiko on 5/1/17.
 */

public class Activity_Project extends Activity {
    Button _btnSave, _btnCancel;
    EditText _txtName, _txtDescription;
    TextView _txtStartDate, _txtEndDate;

    public static final String ACTION_NONE = "NONE";
    public static final String ACTION_ADD = "ADD";
    public static final String ACTION_EDIT = "EDIT";
    public static final String FILTER_BY_PROJECTID = "ProjectId";

    String _action = ACTION_NONE;
    int _projectId = 0;

    IProjectRequests _viewRequest = Activity_Main.GetStaticViewRequest();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_layout_add_edit);

        if(IntentValidated())
            _action = getIntent().getAction();

        _projectId  = getIntent().getIntExtra(FILTER_BY_PROJECTID, 0);


        AssociateViewToLocalVar();
        SetViewHandlers();
        this.SetViewData();
    }

    public boolean IntentValidated()
    {
        return (getIntent() != null) && (getIntent().getAction() != null);
    }

    void AssociateViewToLocalVar() {
        _btnSave = (Button)findViewById(R.id.btnSave);
        _btnCancel = (Button)findViewById(R.id.btnCancel);
        _txtName = (EditText)findViewById(R.id.txtName);
        _txtDescription = (EditText) findViewById(R.id.txtDescription);
        _txtStartDate = (TextView) findViewById(R.id.txtStartDate);
        _txtEndDate = (TextView) findViewById(R.id.txtEndDate);
    }

    void SetViewHandlers()
    {
        _btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InvokeActionBasedPersistency();
                setResult(RESULT_OK);
                finish();
            }
        });

        _btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
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

    public void SetViewData()
    {
        switch (_action)
        {
            case ACTION_ADD:
                ModelToViewData(_viewRequest.ConstructEmptyProject());
                break;
            case ACTION_EDIT:
                ModelToViewData(_viewRequest.GetProjectById(_projectId));
                break;
            default:
                break;
        }
    }

    public void InvokeActionBasedPersistency()
    {
        switch (_action)
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
}