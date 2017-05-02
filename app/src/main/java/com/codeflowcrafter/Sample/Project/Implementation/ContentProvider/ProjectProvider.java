package com.codeflowcrafter.Sample.Project.Implementation.ContentProvider;

import com.codeflowcrafter.DatabaseAccess.ContentProviderTemplate;
import com.codeflowcrafter.Sample.SampleApplicationContentProviders;

/**
 * Created by aiko on 5/1/17.
 */

public class ProjectProvider extends ContentProviderTemplate {
    public ProjectProvider() {
        super(
                SampleApplicationContentProviders.APPLICATION_NAME,
                SampleApplicationContentProviders.GetInstance(),
                "ProjectProvider", "projects",
                new ProjectTable());
    }
}
