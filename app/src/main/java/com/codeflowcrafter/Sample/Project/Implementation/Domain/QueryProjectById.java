package com.codeflowcrafter.Sample.Project.Implementation.Domain;

import com.codeflowcrafter.PEAA.DataManipulation.BaseQueryObject;

import java.util.List;

/**
 * Created by aiko on 5/1/17.
 */

public class QueryProjectById  extends BaseQueryObject<Project, QueryProjectById.Criteria> {

    public static class Criteria {
        public int ProjectId;

        public Criteria(int projectId) {
            ProjectId = projectId;
        }
    }

    public QueryProjectById() {
        super( QueryProjectById.Criteria.class);
    }

    @Override
    public List<Project> PerformSearchOperation(Criteria criteria) {
        return null;
    }
}
