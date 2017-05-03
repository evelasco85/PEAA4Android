package com.codeflowcrafter.Sample;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.codeflowcrafter.DatabaseAccess.ContentProviderTemplate;
import com.codeflowcrafter.LogManagement.Interfaces.ILogEmitter;
import com.codeflowcrafter.LogManagement.Interfaces.ILogEntry;
import com.codeflowcrafter.LogManagement.Interfaces.IStaticLogEntryWrapper;
import com.codeflowcrafter.LogManagement.LogManager;
import com.codeflowcrafter.LogManagement.StaticLogEntryWrapper;
import com.codeflowcrafter.PEAA.DataManipulation.BaseQueryObjectInterfaces.IBaseQueryObjectConcrete;
import com.codeflowcrafter.PEAA.DataSynchronizationManager;
import com.codeflowcrafter.PEAA.Interfaces.IDataSynchronizationManager;
import com.codeflowcrafter.Sample.Project.Implementation.Domain.Project;
import com.codeflowcrafter.Sample.Project.Implementation.Domain.ProjectMapper;
import com.codeflowcrafter.Sample.Project.Implementation.Domain.QueryAllProjects;
import com.codeflowcrafter.Sample.Project.Implementation.Domain.QueryProjectById;
import com.codeflowcrafter.Utilities.DateHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by aiko on 5/1/17.
 */

public class SampleApplication
        extends Application
    implements ILogEmitter
{
    private static SampleApplication s_instance;
    private IDataSynchronizationManager _dsManager = DataSynchronizationManager.GetInstance();
    private IStaticLogEntryWrapper _slc = new StaticLogEntryWrapper(
            LogManager.GetInstance(),
            "Mobile",
            "com.codeflowcrafter.Sample"
    );

    public static SampleApplication GetInstance()
    {
        return s_instance;
    }

    public IStaticLogEntryWrapper GetSLC()
    {
        return _slc;
    }

    @Override
    public void OnLogEmit(ILogEntry log) {
        if (log == null) return;

        switch (log.GetPriority()) {
            case Debug:
            case Info:
            case Notice:
                Log.i(log.GetComponent(), GetInfoLogDetail(log));
                break;

            case Warning:
                Log.w(log.GetComponent(), GetInfoLogDetail(log));
                break;

            case Error:
            case Critical:
            case Alert:
            case Emergency:
                Log.e(log.GetComponent(), GetCompleteLogDetail(log));
                break;
        }

        HashMap<String, String> params = log.GetParameters();

        if ((params != null) && (!params.isEmpty())) EmitLogParams(log.GetEvent(), params);
    }

    String GetInfoLogDetail(ILogEntry log)
    {
        JSONObject jsonLog = new JSONObject();
        String logString = "";

        try {
            TimeZone tz = log.GetTimeZone();

            jsonLog.put("Occurence", DateHelper.DateToString(log.GetOccurence()));

            jsonLog.put("Component", log.GetComponent());
            jsonLog.put("Event", log.GetEvent());

            jsonLog.put("Description", log.GetDescription());
            jsonLog.put("Status", log.GetStatus());

            logString = jsonLog.toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logString = e.toString();
        }

        return logString;
    }

    String GetCompleteLogDetail(ILogEntry log)
    {
        JSONObject jsonLog = new JSONObject();
        String logString = "";

        try {
            TimeZone tz = log.GetTimeZone();
            String timezone = "TimeZone   "+tz.getDisplayName(false, TimeZone.SHORT)+" Timezon id :: " +tz.getID();

            jsonLog.put("Occurence", DateHelper.DateToString(log.GetOccurence()));
            jsonLog.put("Timezone", timezone);

            jsonLog.put("System", log.GetSystem());
            jsonLog.put("Application", log.GetApplication());
            jsonLog.put("Component", log.GetComponent());

            jsonLog.put("Event", log.GetEvent());
            jsonLog.put("Priority", log.GetPriority());
            jsonLog.put("Description", log.GetDescription());
            jsonLog.put("Status", log.GetStatus());

            logString = jsonLog.toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logString = e.toString();
        }

        return logString;
    }

    void EmitLogParams(String event, HashMap<String, String> params)
    {
        for(Map.Entry<String, String> entry : params.entrySet()) {

            Log.i(String.format("Event[%s] Param", event), String.format("%s = %s", entry.getKey(), entry.getValue()));
        }
    }
    @Override
    public final void onCreate()
    {
        LogManager.GetInstance().SetEmitter(this);
        super.onCreate();

        RegisterProjectDomain(_dsManager);

        s_instance = this;
    }

    void RegisterProjectDomain(IDataSynchronizationManager dsManager)
    {
        ContentResolver resolver = getContentResolver();

        List<IBaseQueryObjectConcrete<Project>> projectQueryObjects = new ArrayList<IBaseQueryObjectConcrete<Project>>();
        Uri uri = SampleApplicationContentProviders.GetInstance().GetProjectProvider().GetContentUri();
        Context context = this.getApplicationContext();

        projectQueryObjects.add(new QueryAllProjects(context, uri));
        projectQueryObjects.add(new QueryProjectById(context, uri));
        dsManager.RegisterEntity(
                Project.class,
                new ProjectMapper(resolver, uri),
                projectQueryObjects);
    }
}