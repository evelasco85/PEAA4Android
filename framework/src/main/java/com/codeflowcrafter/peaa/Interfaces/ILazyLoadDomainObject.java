package com.codeflowcrafter.peaa.Interfaces;

import com.codeflowcrafter.peaa.LazyLoad.LazyLoadDomainObject;

/**
 * Created by aiko on 4/2/17.
 */

public interface ILazyLoadDomainObject<TSearchInput> {
    TSearchInput GetCriteria();
    <TEntity extends  LazyLoadDomainObject<TSearchInput>> void Load(ILazyLoader<TEntity, TSearchInput> loader);
}
