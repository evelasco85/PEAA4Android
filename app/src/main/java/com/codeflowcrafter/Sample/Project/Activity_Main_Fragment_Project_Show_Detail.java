package com.codeflowcrafter.Sample.Project;

import android.app.DialogFragment;
import android.os.Bundle;

import com.codeflowcrafter.Sample.Project.Implementation.Domain.Project;

/**
 * Created by aiko on 5/6/17.
 */

public class Activity_Main_Fragment_Project_Show_Detail extends DialogFragment{
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_CREATED = "created date";
    private static final String KEY_ENDED = "ended date";

    public static Activity_Main_Fragment_Project_AddEdit newInstance(Project project)
    {
        Bundle args = new Bundle();

        args.putInt(KEY_ID, project.GetId());
        args.putString(KEY_NAME, project.GetName());
        args.putString(KEY_DESCRIPTION, project.GetDescription());
        args.putString(KEY_CREATED, project.GetCreatedDate());
        args.putString(KEY_ENDED, project.GetEndedDate());

        Activity_Main_Fragment_Project_AddEdit fragment = new Activity_Main_Fragment_Project_AddEdit();

        fragment.setArguments(args);

        return fragment;
    }
}