package com.codeflowcrafter.peaa.CustomerServices;

import com.codeflowcrafter.peaa.IdentityFieldAnnotation;
import com.codeflowcrafter.peaa.DataManipulation.BaseMapperInterfaces.IBaseMapper;
import com.codeflowcrafter.peaa.Domain.DomainObject;

/**
 * Created by aiko on 3/5/17.
 */

public class Customer extends DomainObject {
    public Customer(IBaseMapper mapper)
    {
        super(mapper);
    }

    public Customer(IBaseMapper mapper, String number)
    {
        this(mapper);

        Number = number;
    }

    @IdentityFieldAnnotation
    public String Number;

    public String Name;
}
