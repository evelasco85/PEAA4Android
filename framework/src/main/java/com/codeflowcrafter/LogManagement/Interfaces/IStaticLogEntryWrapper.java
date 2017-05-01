package com.codeflowcrafter.LogManagement.Interfaces;

import com.codeflowcrafter.LogManagement.Priority;
import com.codeflowcrafter.LogManagement.Status;

/**
 * Created by aiko on 4/29/17.
 */

public interface IStaticLogEntryWrapper extends ILogCreator{
    void SetSystem(String system);
    void SetApplication(String application);
    void SetComponent(String component);
    void SetEvent(String event);

    ILogEntry CreateLogEntry(Priority priority, String description);
    void EmitLog(Priority priority, String description);
    void EmitLog(Priority priority, Status status);
}
