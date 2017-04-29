package com.codeflowcrafter.peaa.Interfaces.Registries;

import com.codeflowcrafter.peaa.DataManipulation.BaseMapperInterfaces.IBaseMapperConcrete;
import com.codeflowcrafter.peaa.Domain.Interfaces.IDomainObject;

/**
 * Created by aiko on 3/11/17.
 */

public interface IMapperRegistry {
    <TEntity extends IDomainObject> IBaseMapperConcrete<TEntity> GetMapper(Class<TEntity> thisClass);
}
