package com.codeflowcrafter.Sample.Amount;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.codeflowcrafter.Sample.Amount.Implementation.Domain.Amount;
import com.codeflowcrafter.Sample.Amount.Implementation.MVP.IRequests_Amount;
import com.codeflowcrafter.Sample.R;
import com.codeflowcrafter.UI.Date.Fragment_DatePicker;
import com.codeflowcrafter.UI.Date.Fragment_TimePicker;
import com.codeflowcrafter.Utilities.DateHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by aiko on 5/13/17.
 */

public class Activity_Amount_Dialog_AddEdit extends DialogFragment {
    private Button _btnSave, _btnCancel;
    private TextView _txtDate, _txtTime;

    public static final String FRAGMENT_NAME = "Add/Edit Amount";

    public static final String ACTION_ADD = "ADD";
    public static final String ACTION_EDIT = "EDIT";

    private static final String KEY_ACTION = "action";


    private IRequests_Amount _viewRequest;
    private Amount _amountToEdit;
    private int _projectId = 0;
    private int _amountId = 0;

    public void SetViewRequest(IRequests_Amount viewRequest) {
        _viewRequest = viewRequest;
    }

    public void SetProjectId(int projectId) {
        _projectId = projectId;
    }

    public void SetAmountToEdit(Amount amount) {
        _amountToEdit = amount;
    }

    public static Activity_Amount_Dialog_AddEdit newInstance(String action, int projectId, Amount amount) {
        Bundle args = new Bundle();

        args.putString(KEY_ACTION, action);

        Activity_Amount_Dialog_AddEdit fragment = new Activity_Amount_Dialog_AddEdit();

        fragment.SetProjectId(projectId);
        fragment.setArguments(args);
        fragment.SetAmountToEdit(amount);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_amount_fragment_add_edit, container, false);
        String selectedAction = getArguments().getString(KEY_ACTION);

        AssociateViewToLocalVar(view, selectedAction);
        SetViewHandlers(selectedAction);

        if (selectedAction == ACTION_EDIT) SetModelToViewData(_amountToEdit);

        return view;
    }

    private void AssociateViewToLocalVar(View view, final String selectedAction) {
        _btnSave = (Button) view.findViewById(R.id.btnSave);
        _btnCancel = (Button) view.findViewById(R.id.btnCancel);
        _txtDate = (TextView) view.findViewById(R.id.txtDate);
        _txtTime = (TextView) view.findViewById(R.id.txtTime);

        if (selectedAction == ACTION_ADD)
        {
            Date datetime = Calendar.getInstance().getTime();

            _txtDate.setText(
                    (new SimpleDateFormat("yyyy-MM-dd"))
                            .format(datetime.getTime()));
            _txtTime.setText(
                    (new SimpleDateFormat("HH:mm"))
                            .format(datetime.getTime()));
        }
    }

    private void SetViewHandlers(final String selectedAction) {
        _btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        _btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _viewRequest.CancelAmountEntry();
                dismiss();
            }
        });

        _txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_DatePicker dateFragment = new Fragment_DatePicker();

                dateFragment.SetDefaultDate(_txtDate.getText().toString());
                dateFragment.SetOnDateSetListener(new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day)
                    {
                        Calendar cal = Calendar.getInstance();

                        cal.set(Calendar.YEAR, year);
                        cal.set(Calendar.MONTH, month);
                        cal.set(Calendar.DAY_OF_MONTH, day);

                        _txtDate.setText((new SimpleDateFormat("yyyy-MM-dd")).format(cal.getTime()));
                    }
                });
                dateFragment.show(getFragmentManager(), "datePicker");
            }
        });

        _txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_TimePicker dateFragment = new Fragment_TimePicker();

                dateFragment.SetDefaultTime(_txtTime.getText().toString());
                dateFragment.SetOnDateSetListener(new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
                    {
                        Calendar cal = Calendar.getInstance();

                        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        cal.set(Calendar.MINUTE, minute);

                        _txtTime.setText((new SimpleDateFormat("HH:mm")).format(cal.getTime()));
                    }
                });
                dateFragment.show(getFragmentManager(), "timePicker");
            }
        });
    }

    private void SetModelToViewData(Amount amount) {
        if (amount == null)
            return;
    }

    @Override
    public void onDestroyView() {
        try {
            Fragment childFragment = getFragmentManager().findFragmentById(R.id.saveCancelFragmentPlaceholder);

            FragmentTransaction transaction = getFragmentManager()
                    .beginTransaction();

            transaction.remove(childFragment);

            transaction.commit();
        } catch (Exception e) {
        }

        super.onDestroyView();
    }
}
