package com.codeflowcrafter.Sample.Project.Implementation.Domain;

import com.codeflowcrafter.PEAA.DataManipulation.BaseMapperInterfaces.IBaseMapper;
import com.codeflowcrafter.PEAA.Domain.DomainObject;

/**
 * Created by aiko on 5/1/17.
 */

public class Project extends DomainObject {
    int _id;
    String _name;
    String _description;
    String _createdDate;
    String _endedDate;

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

    public Project(IBaseMapper mapper, int id, String name, String description, String createdDate)
    {
        super(mapper);

        _id = id;
        _name = name;
        _description = description;
        _createdDate = createdDate;
    }
    public Project(IBaseMapper mapper, int id, String name, String description, String createdDate, String endedDate)
    {
        this(mapper, id, name, description, createdDate);

        _endedDate = endedDate;
    }
}
