package com.codeflowcrafter.peaa.Domain;

import com.codeflowcrafter.peaa.DataManipulation.BaseMapperInterfaces.IBaseMapper;
import com.codeflowcrafter.peaa.DataManipulation.BaseQueryObjectInterfaces.IBaseQueryObject;
import com.codeflowcrafter.peaa.Domain.Interfaces.IDomainObject;
import com.codeflowcrafter.peaa.Domain.Interfaces.ISystemManipulation;
import com.codeflowcrafter.peaa.Domain.Enums.InstantiationType;

import java.util.UUID;

/**
 * Created by aiko on 3/4/17.
 */

public class DomainObject implements IDomainObject, ISystemManipulation {
    IBaseMapper _mapper;
    UUID _systemId;
    IBaseQueryObject _queryObject;

    public DomainObject(IBaseMapper mapper)
    {
        _mapper = mapper;
        _systemId = UUID.randomUUID();
    }

    @Override
    public UUID GetSystemId() {
        return _systemId;
    }

    @Override
    public IBaseMapper GetMapper() {
        return _mapper;
    }

    @Override
    public InstantiationType GetInstantiation() {
        if(_queryObject == null)
            return InstantiationType.New;
        else
            return InstantiationType.Loaded;
    }

    @Override
    public void SetQueryObject(IBaseQueryObject queryObject) {
        _queryObject = queryObject;
    }

    @Override
    public void SetMapper(IBaseMapper mapper) {
        _mapper = mapper;
    }
}
