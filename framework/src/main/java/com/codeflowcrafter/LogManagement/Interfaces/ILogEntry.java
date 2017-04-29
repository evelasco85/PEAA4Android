package com.codeflowcrafter.LogManagement.Interfaces;

import com.codeflowcrafter.LogManagement.Priority;
import com.codeflowcrafter.LogManagement.Status;

import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

/**
 * Created by aiko on 4/29/17.
 */

public interface ILogEntry {
    TimeZone GetTimeZone();

    Date GetOccurence();

    String GetUser();

    String GetSessionId();

    String GetTransactionId();

    String GetSystem();

    void SetSystem(String system);

    String GetApplication();

    void SetApplication(String application);

    String GetComponent();

    void SetComponent(String component);

    String GetEvent();

    void SetEvent(String event);

    Priority GetPriority();

    Status GetStatus();

    void SetStatus(Status value);

    String GetDescription();

    void SetDescription(String value);

    HashMap<String, String> GetParameters();

    void AddParameter(String paramKey, String paramValue);
}
