package com.codeflowcrafter.Sample.Amount;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.codeflowcrafter.Sample.Amount.Implementation.Domain.Amount;
import com.codeflowcrafter.Sample.Amount.Implementation.MVP.IRequests_Amount;
import com.codeflowcrafter.Sample.R;

/**
 * Created by aiko on 5/13/17.
 */

public class Activity_Amount_Dialog_AddEdit extends DialogFragment {
    private Button _btnSave, _btnCancel;

    public static final String FRAGMENT_NAME = "Add/Edit Amount";

    public static final String ACTION_ADD = "ADD";
    public static final String ACTION_EDIT = "EDIT";

    private static final String KEY_ACTION = "action";


    private IRequests_Amount _viewRequest;
    private Amount _amountToEdit;
    private int _projectId = 0;

    public void SetViewRequest(IRequests_Amount viewRequest)
    {
        _viewRequest = viewRequest;
    }
    public void SetAmountToEdit(Amount amount){        _amountToEdit = amount;}

    public static Activity_Amount_Dialog_AddEdit newInstance(String action, Amount amount)
    {
        Bundle args = new Bundle();

        args.putString(KEY_ACTION, action);

        Activity_Amount_Dialog_AddEdit fragment = new Activity_Amount_Dialog_AddEdit();

        fragment.setArguments(args);
        fragment.SetAmountToEdit(amount);

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
        View view = inflater.inflate(R.layout.activity_amount_fragment_add_edit, container, false);

        AssociateViewToLocalVar(view);

        String selectedAction = getArguments().getString(KEY_ACTION);

        SetViewHandlers(selectedAction);

        if(selectedAction == ACTION_EDIT) SetModelToViewData(_amountToEdit);

        return view;
    }

    private void AssociateViewToLocalVar(View view) {
        _btnSave = (Button)view.findViewById(R.id.btnSave);
        _btnCancel = (Button)view.findViewById(R.id.btnCancel);
    }

    private void SetViewHandlers(final String selectedAction)
    {
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
    }

    private void SetModelToViewData(Amount amount)
    {
        if(amount == null)
            return;
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
