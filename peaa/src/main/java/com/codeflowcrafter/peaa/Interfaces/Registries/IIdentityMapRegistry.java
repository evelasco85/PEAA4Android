package com.codeflowcrafter.peaa.Interfaces.Registries;

import com.codeflowcrafter.peaa.Interfaces.IIdentityMapConcrete;
import com.codeflowcrafter.peaa.Domain.Interfaces.IDomainObject;

/**
 * Created by aiko on 3/11/17.
 */

public interface IIdentityMapRegistry {
    <TEntity extends IDomainObject> IIdentityMapConcrete<TEntity> GetIdentityMap();
}
