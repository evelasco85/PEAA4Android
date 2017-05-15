package com.codeflowcrafter.Sample.Amount;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeflowcrafter.Sample.Amount.Implementation.Domain.Amount;
import com.codeflowcrafter.Sample.R;

/**
 * Created by aiko on 5/15/17.
 */

public class Activity_Amount_Dialog_Show_Detail extends DialogFragment {
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
    }

    private void SetModelToViewData(Amount amount)
    {
        if(amount == null)
            return;


    }
}
