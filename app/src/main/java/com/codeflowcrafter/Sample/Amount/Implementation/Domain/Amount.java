package com.codeflowcrafter.Sample.Amount.Implementation.Domain;

import android.text.TextUtils;

import com.codeflowcrafter.PEAA.DataManipulation.BaseMapperInterfaces.IBaseMapper;
import com.codeflowcrafter.PEAA.Domain.DomainObject;
import com.codeflowcrafter.Utilities.DateHelper;

import java.util.Date;

/**
 * Created by aiko on 5/12/17.
 */

public class Amount  extends DomainObject {
    private int _id;
    private int _projectId;
    private String _createdDate;
    private String _createdTime;
    private double _amount;
    private boolean _expenseEntry;
    private String _description;

    public Amount(IBaseMapper mapper)
    {
        this(mapper, 0, 0, "", "", 0, 1, "");
    }

    public Amount(IBaseMapper mapper, int id, int projectId, String createdDate, String createdTime, double amount, int expenseEntry, String description)
    {
        super(mapper);

        _createdDate = createdDate;
        _createdTime = createdTime;

        if(TextUtils.isEmpty(createdDate))
            _createdDate = DateHelper.DateToString(new Date(), "yyyy-MM-dd");

        if(TextUtils.isEmpty(createdTime))
            _createdTime = DateHelper.DateToString(new Date(), "HH:mm");

        _id = id;
        _projectId = projectId;
        _amount = amount;
        _expenseEntry = (expenseEntry > 0) ? true : false;
        _description = description;
    }

    public int GetId() {
        return _id;
    }

    public void SetId(int _id) {
        this._id = _id;
    }

    public int GetProjectId() {
        return _projectId;
    }

    public void SetProjectId(int _projectId) {
        this._projectId = _projectId;
    }

    public String GetCreatedDate() {
        return _createdDate;
    }

    public void SetCreatedDate(String _createdDate) {
        this._createdDate = _createdDate;
    }

    public String GetCreatedTime() {
        return _createdTime;
    }

    public void SetCreatedTime(String _createdTime) {
        this._createdTime = _createdTime;
    }

    public double GetAmount() {
        return _amount;
    }

    public void SetAmount(double _amount) {
        this._amount = _amount;
    }

    public boolean GetIsExpense() {
        return _expenseEntry;
    }

    public void SetIsExpense(boolean _expenseEntry) {
        this._expenseEntry = _expenseEntry;
    }

    public String GetDescription() {
        return _description;
    }

    public void SetDescription(String _description) {
        this._description = _description;
    }
}
