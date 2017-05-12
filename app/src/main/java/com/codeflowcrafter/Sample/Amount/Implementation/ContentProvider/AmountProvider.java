package com.codeflowcrafter.Sample.Amount.Implementation.ContentProvider;

import com.codeflowcrafter.DatabaseAccess.ContentProviderTemplate;
import com.codeflowcrafter.Sample.SampleApplicationContentProviders;

/**
 * Created by aiko on 5/12/17.
 */

public class AmountProvider  extends ContentProviderTemplate {
    public AmountProvider() {
        super(
                SampleApplicationContentProviders.APPLICATION_NAME,
                SampleApplicationContentProviders.GetInstance(),
                "AmountProvider", "amounts",
                new AmountTable());
    }
}
