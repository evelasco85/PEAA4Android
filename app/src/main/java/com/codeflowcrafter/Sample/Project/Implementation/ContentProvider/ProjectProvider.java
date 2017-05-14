package com.codeflowcrafter.Sample.Project.Implementation.ContentProvider;

import com.codeflowcrafter.DatabaseAccess.ContentProviderTemplate;
import com.codeflowcrafter.Sample.SampleApplicationContentProviders;

/**
 * Created by aiko on 5/1/17.
 */

public class ProjectProvider extends ContentProviderTemplate {
    private static final String PROVIDER_NAME = "ProjectProvider";

    public ProjectProvider() {
        super(
                SampleApplicationContentProviders.APPLICATION_NAME,
                SampleApplicationContentProviders.GetInstance(),
                PROVIDER_NAME, ProjectTable.TABLE_NAME,
                new ProjectTable());
    }
}
