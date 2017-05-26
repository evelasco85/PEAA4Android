package com.codeflowcrafter.Sample.Amount.Implementation.MVP;

import com.codeflowcrafter.MVP.IView;
import com.codeflowcrafter.Sample.Amount.Implementation.Domain.Amount;

import java.util.List;

/**
 * Created by aiko on 5/14/17.
 */

interface IView_Events
{
    void OnPromptExecution_AddAmountEntry();
    void OnPromptExecution_EditAmountEntry(Amount amount);
    void OnPromptExecution_DeleteAmountEntry(Amount amount);
    void OnPromptExecution_AmountDetail(Amount amount);

    void OnLoadAmountsViaLoaderCompletion(List<Amount> amounts);
    void OnPerformProjectUpdate();
}

public interface IView_Amount extends IView_Events, IView<IRequests_Amount> {
}
