package com.codeflowcrafter.Sample.Amount.Implementation.Domain;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

import com.codeflowcrafter.PEAA.DataManipulation.BaseMapper;
import com.codeflowcrafter.PEAA.DataManipulation.BaseMapperInterfaces.IInvocationDelegates;
import com.codeflowcrafter.Sample.Amount.Implementation.ContentProvider.AmountTable;
import com.codeflowcrafter.Sample.Project.Implementation.Domain.Project;

/**
 * Created by aiko on 5/12/17.
 */

public class AmountMapper extends BaseMapper<Amount> {
    public final static String KEY_MAPPER_NAME = "[Mapper Name]";
    public final static String KEY_OPERATION = "[Operation]";
    public final static String KEY_COUNT = "[Count]";

    private ContentResolver _resolver;
    private Uri _uri;

    public AmountMapper(ContentResolver resolver, Uri uri)
    {
        super(Amount.class);

        _resolver = resolver;
        _uri = uri;
    }

    @Override
    public boolean ConcreteUpdate(Amount amount, IInvocationDelegates invocationDelegates) {
        return false;
    }

    @Override
    public boolean ConcreteInsert(Amount amount, IInvocationDelegates invocationDelegates) {
        return false;
    }

    @Override
    public boolean ConcreteDelete(Amount amount, IInvocationDelegates invocationDelegates) {
        return false;
    }

    private ContentValues EntityToContentValues(Amount amount)
    {
        ContentValues values = new ContentValues();

        if(amount.get_id() > 0)
            values.put(AmountTable.COLUMN_ID, amount.get_id());

        values.put(AmountTable.COLUMN_PROJECT_ID, amount.get_projectId());
        values.put(AmountTable.COLUMN_CREATED_DATE, amount.get_createdDate());
        values.put(AmountTable.COLUMN_CREATED_TIME, amount.get_createdTime());
        values.put(AmountTable.COLUMN_AMOUNT, amount.get_amount());
        values.put(AmountTable.COLUMN_EXPENSE_ENTRY, amount.is_expenseEntry());
        values.put(AmountTable.COLUMN_DESCRIPTION, amount.get_description());

        return values;
    }

}
