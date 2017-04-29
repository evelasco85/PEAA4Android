package com.codeflowcrafter.peaa.LazyLoad;

import com.codeflowcrafter.peaa.Interfaces.ILazyLoader;

/**
 * Created by aiko on 4/2/17.
 */

public abstract class LazyLoader <TEntity extends LazyLoadDomainObject<TSearchInput>, TSearchInput> implements ILazyLoader<TEntity, TSearchInput> {
    public abstract void LoadAllFields(TEntity entity, TSearchInput criteria);
}
