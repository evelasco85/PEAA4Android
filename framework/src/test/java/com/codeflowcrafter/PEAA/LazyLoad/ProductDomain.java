package com.codeflowcrafter.PEAA.LazyLoad;

import com.codeflowcrafter.PEAA.DataManipulation.BaseMapperInterfaces.IBaseMapper;

/**
 * Created by aiko on 4/8/17.
 */

public class ProductDomain extends LazyLoadDomainObject<ProductDomain.Criteria> {
    public static class Criteria
    {
        public int Id;

        public Criteria(int id)
        {
            Id = id;
        }
    }

    public int Id;
    public String Description;

    public ProductDomain(IBaseMapper mapper, Criteria criteria)
    {
        super(criteria, mapper);

        if (criteria != null)
            Id = criteria.Id;
    }
}
