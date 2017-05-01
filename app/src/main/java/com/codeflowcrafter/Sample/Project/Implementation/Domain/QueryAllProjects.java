package com.codeflowcrafter.Sample.Project.Implementation.Domain;

import com.codeflowcrafter.PEAA.DataManipulation.BaseQueryObject;

import java.util.List;

/**
 * Created by aiko on 5/1/17.
 */

public class QueryAllProjects extends BaseQueryObject<Project, QueryAllProjects.Criteria> {

    public static class Criteria {
        public Criteria() {
        }
    }

    public QueryAllProjects() {
        super( QueryAllProjects.Criteria.class);
    }

    @Override
    public List<Project> PerformSearchOperation(QueryAllProjects.Criteria criteria) {
        return null;
    }
}