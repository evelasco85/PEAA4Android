package com.codeflowcrafter.Sample.Project.Implementation.DB;

import com.codeflowcrafter.DatabaseAccess.ContentProviderTemplate;

/**
 * Created by aiko on 5/1/17.
 */

public class ProjectProvider extends ContentProviderTemplate {
    public ProjectProvider() {
        super(
                HabitAtDataAccess.APPLICATION_NAME,
                HabitAtDataAccess.GetInstance(),
                "ProjectProvider", "projects",
                new ProjectTable());
    }
}
