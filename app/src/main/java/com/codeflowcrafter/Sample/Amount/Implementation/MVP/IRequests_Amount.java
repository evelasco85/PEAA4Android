package com.codeflowcrafter.Sample.Amount.Implementation.MVP;

import android.content.CursorLoader;

import com.codeflowcrafter.Sample.Amount.Implementation.Domain.Amount;

/**
 * Created by aiko on 5/14/17.
 */

public interface IRequests_Amount {
    void Prompt_AddAmountEntry();
    void Prompt_EditAmountEntry(Amount amount);
    void Prompt_DeleteAmountEntry(Amount amount);
    void Prompt_AmountDetail(Amount amount);

    void AddAmount(Amount amount);
    void UpdateAmount(Amount amount);
    void DeleteAmount(Amount amount);

    void LoadAmountsViaLoader(int projectId);
    void CancelAmountEntry();
}
