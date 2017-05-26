package com.codeflowcrafter.Sample.Amount.Implementation.MVP;

import android.content.CursorLoader;

import com.codeflowcrafter.Sample.Amount.Implementation.Domain.Amount;
import com.codeflowcrafter.Sample.Project.Implementation.Domain.Project;

/**
 * Created by aiko on 5/14/17.
 */

public interface IRequests_Amount {
    void Prompt_AddAmountEntry();
    void Prompt_EditAmountEntry(Amount amount);
    void Prompt_DeleteAmountEntry(Amount amount);
    void Prompt_AmountDetail(Amount amount);

    void AddAmount(Project project, Amount amount);
    void UpdateAmount(Project project, Amount amount);
    void DeleteAmount(Project project, Amount amount);

    void LoadAmountsViaLoader(int projectId);
    void CancelAmountEntry();
}
