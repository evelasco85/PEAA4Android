package com.codeflowcrafter.peaa.Interfaces;

import com.codeflowcrafter.peaa.Domain.Interfaces.IDomainObject;
import com.codeflowcrafter.peaa.Domain.Interfaces.IForeignKeyMapping;

import java.util.List;

/**
 * Created by aiko on 4/1/17.
 */

public interface IForeignKeyMappingManager {
    <TParentEntity extends IDomainObject, TChildEntity extends IDomainObject>
    void RegisterForeignKeyMapping(
            Class<TParentEntity> parentClass,
            Class<TChildEntity> childClass,
            IForeignKeyMapping<TParentEntity, TChildEntity> mapping);

    <TParentEntity extends IDomainObject, TChildEntity extends IDomainObject>
    List<TChildEntity> GetForeignKeyValues(
            Class<TParentEntity> parentClass,
            Class<TChildEntity> childClass,
            TParentEntity entity)throws NullPointerException;
}
