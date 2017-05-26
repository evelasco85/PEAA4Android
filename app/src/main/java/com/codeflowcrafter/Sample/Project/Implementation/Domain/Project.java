package com.codeflowcrafter.Sample.Project.Implementation.Domain;

import com.codeflowcrafter.PEAA.DataManipulation.BaseMapperInterfaces.IBaseMapper;
import com.codeflowcrafter.PEAA.Domain.DomainObject;

/**
 * Created by aiko on 5/1/17.
 */

public class Project extends DomainObject {
    private int _id;
    private String _name;
    private String _description;
    private String _createdDate;
    private String _endedDate;
    private double _total;

    public int GetId(){return  _id;}

    public String GetName() {return _name;}

    public String GetDescription() {return _description;}

    public String GetCreatedDate() {return _createdDate;}

    public void SetCreatedDate(String createdDate)
    {
        _createdDate = createdDate;
    }

    public String GetEndedDate() {return _endedDate;}

    public void SetEndedDate(String endedDate)
    {
        _endedDate = endedDate;
    }

    public double GetTotal(){return _total;}
    public void SetTotal(double total){_total = total;}

    public Project(IBaseMapper mapper, int id, String name, String description, String createdDate, double total)
    {
        super(mapper);

        _id = id;
        _name = name;
        _description = description;
        _createdDate = createdDate;
        _total = total;
    }
    public Project(IBaseMapper mapper, int id, String name, String description, String createdDate, String endedDate, double total)
    {
        this(mapper, id, name, description, createdDate, total);

        _endedDate = endedDate;
    }
}
