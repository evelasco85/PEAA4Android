package com.codeflowcrafter.Sample.Project.Implementation.DB;

import com.codeflowcrafter.DatabaseAccess.ContentProviderTemplate;
import com.codeflowcrafter.Sample.SampleApplicationDataAccess;

/**
 * Created by aiko on 5/1/17.
 */

public class ProjectProvider extends ContentProviderTemplate {
    public ProjectProvider() {
        super(
                SampleApplicationDataAccess.APPLICATION_NAME,
                SampleApplicationDataAccess.GetInstance(),
                "ProjectProvider", "projects",
                new ProjectTable());
    }
}
