package com.codeflowcrafter.Sample.Amount.Implementation.Domain;

import android.content.ContentResolver;
import android.net.Uri;

import com.codeflowcrafter.PEAA.DataManipulation.BaseMapper;
import com.codeflowcrafter.PEAA.DataManipulation.BaseMapperInterfaces.IInvocationDelegates;
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
}
