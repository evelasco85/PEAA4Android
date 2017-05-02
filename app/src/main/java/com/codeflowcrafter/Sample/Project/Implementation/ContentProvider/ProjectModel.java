package com.codeflowcrafter.Sample.Project.Implementation.ContentProvider;

//Domain Object
public class ProjectModel{
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

    public ProjectModel(int id, String name, String description, String createdDate)
    {
        _id = id;
        _name = name;
        _description = description;
        _createdDate = createdDate;
    }
    public ProjectModel(int id, String name, String description, String createdDate, String endedDate)
    {
        this(id, name, description, createdDate);

        _endedDate = endedDate;
    }


}
