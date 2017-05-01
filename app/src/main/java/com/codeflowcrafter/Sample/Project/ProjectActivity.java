package com.codeflowcrafter.Sample.Project;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.codeflowcrafter.Sample.R;
import com.codeflowcrafter.UI.Date.Fragment_DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by aiko on 5/1/17.
 */

public class ProjectActivity extends Activity {
    Button _btnSave, _btnCancel;
    EditText _txtName, _txtDescription;
    TextView _txtStartDate, _txtEndDate;

    public static final String ACTION_NONE = "NONE";
    public static final String ACTION_ADD = "ADD";
    public static final String ACTION_EDIT = "EDIT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_layout_add_edit);

        AssociateViewToLocalVar();
        SetViewHandlers();
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
//                InvokeActionBasedPersistency();
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
}