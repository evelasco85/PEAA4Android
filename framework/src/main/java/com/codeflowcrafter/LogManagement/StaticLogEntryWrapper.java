package com.codeflowcrafter.LogManagement;

import com.codeflowcrafter.LogManagement.Interfaces.ILogCreator;
import com.codeflowcrafter.LogManagement.Interfaces.ILogEntry;

/**
 * Created by aiko on 4/29/17.
 */

interface IStaticLogEntryWrapper extends ILogCreator{
    void SetSystem(String system);
    void SetApplication(String application);
    void SetComponent(String component);
    void SetEvent(String event);

    ILogEntry CreateLogEntry(Priority priority, String description);
    void EmitLog(Priority priority, String description);
}

public class StaticLogEntryWrapper implements IStaticLogEntryWrapper {
    ILogManager _manager;

    String _system;
    String _application;
    String _component;
    String _event;

    public void SetSystem(String system) {
        _system = system;
    }

    public void SetApplication(String application) {
        _application = application;
    }

    public void SetComponent(String component) {
        _component = component;
    }

    public void SetEvent(String event) {
        _event = event;
    }

    public StaticLogEntryWrapper(ILogManager manager) {
        this(manager, "", "", "", "");
    }

    public StaticLogEntryWrapper(ILogManager manager, String system) {
        this(manager, system, "", "", "");
    }

    public StaticLogEntryWrapper(ILogManager manager,
                                 String system,
                                 String application
    ) {
        this(manager, system, application, "", "");
    }

    public StaticLogEntryWrapper(ILogManager manager,
                                 String system,
                                 String application,
                                 String component
    ) {
        this(manager, system, application, component, "");
    }

    public StaticLogEntryWrapper(ILogManager manager,
                                 String system,
                                 String application,
                                 String component,
                                 String event
    ) {
        _manager = manager;

        _system = system;
        _application = application;
        _component = component;
        _event = event;
    }

    public ILogEntry CreateLogEntry(Priority priority) {
        ILogEntry entry = _manager.CreateLogEntry(priority);

        entry.SetSystem(_system);
        entry.SetApplication(_application);
        entry.SetComponent(_component);
        entry.SetEvent(_event);

        return entry;
    }

    public ILogEntry CreateLogEntry(Priority priority, String description)
    {
        ILogEntry entry = CreateLogEntry(priority);

        entry.SetDescription(description);

        return entry;
    }

    public void EmitLog(Priority priority, String description)
    {
        ILogEntry entry = CreateLogEntry(priority, description);

        _manager.EmitLog(entry);
    }
}