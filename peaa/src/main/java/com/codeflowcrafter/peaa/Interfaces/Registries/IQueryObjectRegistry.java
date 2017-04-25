package com.codeflowcrafter.peaa.Interfaces.Registries;

import com.codeflowcrafter.peaa.DataManipulation.BaseQueryObjectInterfaces.IBaseQueryObjectConcrete;
import com.codeflowcrafter.peaa.Domain.Interfaces.IDomainObject;

/**
 * Created by aiko on 3/11/17.
 */

public interface IQueryObjectRegistry {
    <TEntity extends IDomainObject, TSearchInput> IBaseQueryObjectConcrete<TEntity> GetQueryBySearchCriteria(Class<TEntity> thisClass, Class<TSearchInput> thisSearch);
}
