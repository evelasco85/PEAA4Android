package com.codeflowcrafter.Sample.Amount.Implementation.MVP;

import android.content.CursorLoader;

/**
 * Created by aiko on 5/14/17.
 */

public interface IRequests_Amount {
    void Prompt_AddAmountEntry();

    void LoadAmountsViaLoader(CursorLoader loader);
    void CancelAmountEntry();
}
