package com.codeflowcrafter.peaa;

import com.codeflowcrafter.peaa.Interfaces.IIdentityMapConcrete;
import com.codeflowcrafter.peaa.Interfaces.IRepository;
import com.codeflowcrafter.peaa.DataManipulation.BaseMapperInterfaces.IBaseMapperConcrete;
import com.codeflowcrafter.peaa.DataManipulation.BaseQueryObjectInterfaces.IBaseQueryObjectConcrete;
import com.codeflowcrafter.peaa.Domain.Interfaces.IDomainObject;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by aiko on 2/11/17.
 */

public class EntityServiceContainer<TEntity extends IDomainObject> {
    public IBaseMapperConcrete<TEntity> Mapper;
    public IIdentityMapConcrete<TEntity> IdentityMap;
    public IRepository<TEntity> Repository;
    public HashMap<String, IBaseQueryObjectConcrete<TEntity>> QueryDictionary;
    public HashMap<String, Field> PrimitiveFields;
}

