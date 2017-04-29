package com.codeflowcrafter.peaa.Domain.Interfaces;

import com.codeflowcrafter.peaa.DataManipulation.BaseMapperInterfaces.IBaseMapper;
import com.codeflowcrafter.peaa.DataManipulation.BaseQueryObjectInterfaces.IBaseQueryObject;

/**
 * Created by aiko on 3/4/17.
 */

public interface ISystemManipulation {
    void SetQueryObject(IBaseQueryObject queryObject);
    void SetMapper(IBaseMapper mapper);
}
