package com.codeflowcrafter.Sample.Project.Implementation.Domain;

import com.codeflowcrafter.PEAA.DataManipulation.BaseMapper;
import com.codeflowcrafter.PEAA.DataManipulation.BaseMapperInterfaces.InvocationDelegates;

/**
 * Created by aiko on 5/1/17.
 */

public class ProjectMapper extends BaseMapper<Project> {
    public ProjectMapper()
    {
        super(Project.class);
    }

    @Override
    public boolean ConcreteUpdate(Project project, InvocationDelegates invocationDelegates) {
        return false;
    }

    @Override
    public boolean ConcreteInsert(Project project, InvocationDelegates invocationDelegates) {
        return false;
    }

    @Override
    public boolean ConcreteDelete(Project project, InvocationDelegates invocationDelegates) {
        return false;
    }


}
