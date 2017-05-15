package com.codeflowcrafter.Sample.Amount;

import android.app.DialogFragment;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.codeflowcrafter.Sample.Amount.Implementation.Domain.Amount;
import com.codeflowcrafter.Sample.R;

/**
 * Created by aiko on 5/15/17.
 */

public class Activity_Amount_Dialog_Show_Detail extends DialogFragment {
    TextView _txtDate, _txtTime;
    EditText _txtAmount, _txtDescription;
    CheckBox _chkExpense;

    private Amount _amountToView;
    public void SetAmountToView(Amount amount){
        _amountToView = amount;}

    public static final String FRAGMENT_NAME = "Show Amount Details";

    public static Activity_Amount_Dialog_Show_Detail newInstance(Amount amount)
    {
        Bundle args = new Bundle();

        Activity_Amount_Dialog_Show_Detail dialog = new Activity_Amount_Dialog_Show_Detail();

        dialog.setArguments(args);
        dialog.SetAmountToView(amount);

        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_amount_fragment_show_detail, container, false);

        AssociateViewToLocalVar(view);
        SetModelToViewData(_amountToView);

        return view;
    }

    private void AssociateViewToLocalVar(View view) {
        _txtDate = (TextView)view.findViewById(R.id.txtDate);
        _txtTime = (TextView)view.findViewById(R.id.txtTime);
        _txtAmount = (EditText)view.findViewById(R.id.txtAmount);
        _txtDescription = (EditText)view.findViewById(R.id.txtDescription);
        _chkExpense = (CheckBox)view.findViewById(R.id.chkExpense);

        _txtAmount.setEnabled(false);
        _txtAmount.setInputType(InputType.TYPE_NULL);
        _txtDescription.setEnabled(false);
        _txtDescription.setInputType(InputType.TYPE_NULL);
        _chkExpense.setEnabled(false);
        _chkExpense.setInputType(InputType.TYPE_NULL);
    }

    private void SetModelToViewData(Amount amount)
    {
        if(amount == null)
            return;

        this._txtDate.setText(amount.GetCreatedDate());
        this._txtTime.setText(amount.GetCreatedTime());
        this._txtAmount.setText(String.valueOf(amount.GetAmount()));
        this._txtDescription.setText(amount.GetDescription());
        this._chkExpense.setChecked(amount.GetIsExpense());
    }
}
