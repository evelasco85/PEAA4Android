package com.codeflowcrafter.Sample.Amount.Implementation.Domain;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

import com.codeflowcrafter.PEAA.DataManipulation.BaseMapper;
import com.codeflowcrafter.PEAA.DataManipulation.BaseMapperInterfaces.IInvocationDelegates;
import com.codeflowcrafter.Sample.Amount.Implementation.ContentProvider.AmountTable;

import java.util.Hashtable;

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
        int updatedRecords = 0;

        if(amount == null)
            return false;

        String where = AmountTable.COLUMN_ID + "=" +  amount.GetId();

        updatedRecords = _resolver
                .update(
                        _uri,
                        EntityToContentValues(amount),
                        where, null);

        Hashtable results = new Hashtable();

        results.put(KEY_MAPPER_NAME, this.getClass().getName());
        results.put(KEY_OPERATION, "Update");
        results.put(KEY_COUNT, String.valueOf(updatedRecords));

        invocationDelegates.SetResults(results);
        invocationDelegates.SuccessfulInvocation(amount);

        return true;
    }

    @Override
    public boolean ConcreteInsert(Amount amount, IInvocationDelegates invocationDelegates) {
        _resolver.insert(_uri, EntityToContentValues(amount));

        Hashtable results = new Hashtable();

        results.put(KEY_MAPPER_NAME, this.getClass().getName());
        results.put(KEY_OPERATION, "Insertion");
        results.put(KEY_COUNT, "1");

        invocationDelegates.SetResults(results);
        invocationDelegates.SuccessfulInvocation(amount);

        return true;
    }

    @Override
    public boolean ConcreteDelete(Amount amount, IInvocationDelegates invocationDelegates) {
        String where = AmountTable.COLUMN_ID + "=" +  amount.GetId();

        int deletedRecords = _resolver
                .delete(_uri, where, null );

        Hashtable results = new Hashtable();

        results.put(KEY_MAPPER_NAME, this.getClass().getName());
        results.put(KEY_OPERATION, "Deletion");
        results.put(KEY_COUNT, String.valueOf(deletedRecords));

        invocationDelegates.SetResults(results);
        invocationDelegates.SuccessfulInvocation(amount);

        return true;
    }

    private ContentValues EntityToContentValues(Amount amount)
    {
        ContentValues values = new ContentValues();

        if(amount.GetId() > 0)
            values.put(AmountTable.COLUMN_ID, amount.GetId());

        values.put(AmountTable.COLUMN_PROJECT_ID, amount.GetProjectId());
        values.put(AmountTable.COLUMN_CREATED_DATE, amount.GetCreatedDate());
        values.put(AmountTable.COLUMN_CREATED_TIME, amount.GetCreatedTime());
        values.put(AmountTable.COLUMN_AMOUNT, amount.GetAmount());
        values.put(AmountTable.COLUMN_EXPENSE_ENTRY, amount.GetIsExpense());
        values.put(AmountTable.COLUMN_DESCRIPTION, amount.GetDescription());

        return values;
    }

}
