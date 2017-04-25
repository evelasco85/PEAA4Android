package com.codeflowcrafter.peaa.Domain.Interfaces;

import com.codeflowcrafter.peaa.DataManipulation.BaseMapperInterfaces.IBaseMapper;
import com.codeflowcrafter.peaa.Domain.Enums.InstantiationType;

import java.util.UUID;

/**
 * Created by aiko on 2/11/17.
 */

public interface IDomainObject {
    UUID GetSystemId();
    IBaseMapper GetMapper();
    InstantiationType GetInstantiation();
}


