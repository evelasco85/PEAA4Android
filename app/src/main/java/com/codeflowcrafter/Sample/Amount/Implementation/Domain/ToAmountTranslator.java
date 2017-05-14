package com.codeflowcrafter.Sample.Amount.Implementation.Domain;

import android.database.Cursor;

import com.codeflowcrafter.PEAA.DataSynchronizationManager;
import com.codeflowcrafter.Sample.Amount.Implementation.ContentProvider.AmountTable;

/**
 * Created by aiko on 5/14/17.
 */

public class ToAmountTranslator {
    int _idIndex = 0;
    int _projectIdIndex = 0;
    int _createdDateIndex = 0;
    int _createdTimeIndex = 0;
    int _amountIndex = 0;
    int _expenseEntryIndex = 0;
    int _descriptionIndex = 0;

    public void UpdateColumnOrdinals(Cursor cursor)
    {
        _idIndex = cursor.getColumnIndexOrThrow(AmountTable.COLUMN_ID);
        _projectIdIndex = cursor.getColumnIndexOrThrow(AmountTable.COLUMN_PROJECT_ID);
        _createdDateIndex = cursor.getColumnIndexOrThrow(AmountTable.COLUMN_CREATED_DATE);
        _createdTimeIndex = cursor.getColumnIndexOrThrow(AmountTable.COLUMN_CREATED_TIME);
        _amountIndex = cursor.getColumnIndexOrThrow(AmountTable.COLUMN_AMOUNT);
        _expenseEntryIndex = cursor.getColumnIndexOrThrow(AmountTable.COLUMN_EXPENSE_ENTRY);
        _descriptionIndex = cursor.getColumnIndexOrThrow(AmountTable.COLUMN_DESCRIPTION);
    }

    public Amount CursorToEntity(Cursor cursor)
    {
        return new Amount(DataSynchronizationManager.GetInstance().GetMapper(Amount.class),
                cursor.getInt(_idIndex),
                cursor.getInt(_projectIdIndex),
                cursor.getString(_createdDateIndex),
                cursor.getString(_createdTimeIndex),
                cursor.getDouble(_amountIndex),
                cursor.getInt(_expenseEntryIndex),
                cursor.getString(_descriptionIndex)
        );
    }
}
