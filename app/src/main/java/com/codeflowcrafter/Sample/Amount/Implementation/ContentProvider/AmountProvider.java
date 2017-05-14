package com.codeflowcrafter.Sample.Amount.Implementation.ContentProvider;

import com.codeflowcrafter.DatabaseAccess.ContentProviderTemplate;
import com.codeflowcrafter.Sample.SampleApplicationContentProviders;

/**
 * Created by aiko on 5/12/17.
 */

public class AmountProvider  extends ContentProviderTemplate {
    private static final String PROVIDER_NAME = "AmountProvider";

    public AmountProvider() {
        super(
                SampleApplicationContentProviders.APPLICATION_NAME,
                SampleApplicationContentProviders.GetInstance(),
                PROVIDER_NAME, AmountTable.TABLE_NAME,
                new AmountTable());
    }
}
