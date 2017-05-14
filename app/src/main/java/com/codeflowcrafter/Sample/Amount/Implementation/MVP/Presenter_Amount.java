package com.codeflowcrafter.Sample.Amount.Implementation.MVP;

import android.content.CursorLoader;
import android.database.Cursor;

import com.codeflowcrafter.LogManagement.Interfaces.IStaticLogEntryWrapper;
import com.codeflowcrafter.LogManagement.Priority;
import com.codeflowcrafter.LogManagement.Status;
import com.codeflowcrafter.PEAA.DataManipulation.BaseMapperInterfaces.IInvocationDelegates;
import com.codeflowcrafter.Sample.Amount.Implementation.Domain.Amount;
import com.codeflowcrafter.Sample.Amount.Implementation.Domain.ToAmountTranslator;
import com.codeflowcrafter.Sample.InvocationDelegate;
import com.codeflowcrafter.Sample.SampleApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aiko on 5/14/17.
 */

public class Presenter_Amount implements IRequests_Amount {
    private IView_Amount _view;
    private IStaticLogEntryWrapper _slc = SampleApplication.GetInstance().CreateSLC();
    private ToAmountTranslator _translator = new ToAmountTranslator();
    private IInvocationDelegates _invocationDelegate = new InvocationDelegate(_slc);

    public Presenter_Amount(IView_Amount view)
    {
        view.SetViewRequest(this);

        _view = view;

        _slc.SetComponent("Amount");
    }

    public void Prompt_AddAmountEntry()
    {
        _view.OnPromptExecution_AddAmountEntry();
        _slc.SetEvent("Open Amount Entry").EmitLog(Priority.Info, Status.Success);
    }

    public void CancelAmountEntry()
    {
        _slc
                .SetEvent("Cancel amount entry window")
                .EmitLog(Priority.Info, Status.Success);
    }

    public void LoadAmountsViaLoader(CursorLoader loader)
    {
        Cursor cursor = loader.loadInBackground();
        List<Amount> entityList = new ArrayList();

        if(cursor == null) {
            _view.OnLoadAmountsViaLoaderCompletion(entityList);

            return;
        }

        _translator.UpdateColumnOrdinals(cursor);

        while (cursor.moveToNext())
        {
            entityList.add(_translator.CursorToEntity(cursor));
        }

        _view.OnLoadAmountsViaLoaderCompletion(entityList);
        _slc
                .SetEvent(String.format("Loaded amount count %d", entityList.size()))
                .EmitLog(Priority.Info, Status.Success);
    }
}
