package com.codeflowcrafter.Sample.Amount.Implementation.MVP;

import com.codeflowcrafter.MVP.IView;

/**
 * Created by aiko on 5/14/17.
 */

interface IView_Amount_Events
{

}

public interface IView_Amount extends IView_Amount_Events, IView<IAmountRequests> {
}
