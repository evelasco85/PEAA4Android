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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.codeflowcrafter.PEAA.DataSynchronizationManager;
import com.codeflowcrafter.Sample.Amount.Implementation.Domain.Amount;
import com.codeflowcrafter.Sample.Amount.Implementation.MVP.IRequests_Amount;
import com.codeflowcrafter.Sample.R;
import com.codeflowcrafter.UI.Date.Dialog_DatePicker;
import com.codeflowcrafter.UI.Date.Dialog_TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by aiko on 5/13/17.
 */

public class Activity_Amount_Dialog_AddEdit extends DialogFragment {
    Button _btnSave, _btnCancel;
    TextView _txtDate, _txtTime;
    EditText _txtAmount, _txtDescription;
    CheckBox _chkExpense;

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

        Activity_Amount_Dialog_AddEdit dialog = new Activity_Amount_Dialog_AddEdit();

        dialog.SetProjectId(projectId);
        dialog.setArguments(args);
        dialog.SetAmountToEdit(amount);

        return dialog;
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
        _btnSave = (Button)view.findViewById(R.id.btnSave);
        _btnCancel = (Button)view.findViewById(R.id.btnCancel);
        _txtDate = (TextView)view.findViewById(R.id.txtDate);
        _txtTime = (TextView)view.findViewById(R.id.txtTime);
        _txtAmount = (EditText)view.findViewById(R.id.txtAmount);
        _txtDescription = (EditText)view.findViewById(R.id.txtDescription);
        _chkExpense = (CheckBox)view.findViewById(R.id.chkExpense);

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
                InvokeActionBasedPersistency(selectedAction);
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
                Dialog_DatePicker dialog = new Dialog_DatePicker();

                dialog.SetDefaultDate(_txtDate.getText().toString());
                dialog.SetOnDateSetListener(new DatePickerDialog.OnDateSetListener()
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
                dialog.show(getFragmentManager(), "datePicker");
            }
        });

        _txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_TimePicker dialog = new Dialog_TimePicker();

                dialog.SetDefaultTime(_txtTime.getText().toString());
                dialog.SetOnDateSetListener(new TimePickerDialog.OnTimeSetListener()
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
                dialog.show(getFragmentManager(), "timePicker");
            }
        });
    }

    private void InvokeActionBasedPersistency(String selectedAction)
    {
        switch (selectedAction)
        {
            case ACTION_ADD:
                _viewRequest.AddAmount(ViewDataToModel());
                break;
            case ACTION_EDIT:
                _viewRequest.UpdateAmount(ViewDataToModel());
                break;
            default:
                break;
        }
    }

    private void SetModelToViewData(Amount amount) {
        if (amount == null)
            return;

        this._amountId = amount.GetId();
        this._txtDate.setText(amount.GetCreatedDate());
        this._txtTime.setText(amount.GetCreatedTime());
        this._txtAmount.setText(String.valueOf(amount.GetAmount()));
        this._txtDescription.setText(amount.GetDescription());
        this._chkExpense.setChecked(amount.GetIsExpense());
    }

    public Amount ViewDataToModel()
    {
        return new Amount(
                DataSynchronizationManager.GetInstance().GetMapper(Amount.class),
                _amountId,
                _projectId,
                _txtDate.getText().toString(),
                _txtTime.getText().toString(),
                Double.parseDouble(_txtAmount.getText().toString()),
                (_chkExpense.isChecked()) ? 1 : 0,
                _txtDescription.getText().toString()
        );
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
