package com.codeflowcrafter.peaa;

import com.codeflowcrafter.peaa.DataManipulation.BaseQueryObjectInterfaces.IBaseQueryObjectConcrete;
import com.codeflowcrafter.peaa.Interfaces.IDataSynchronizationManager;

import java.util.ArrayList;
import java.util.List;

import com.codeflowcrafter.peaa.CustomerServices.Customer;
import com.codeflowcrafter.peaa.CustomerServices.CustomerMapper;
import com.codeflowcrafter.peaa.CustomerServices.GetCustomerByCivilStatusQuery;
import com.codeflowcrafter.peaa.CustomerServices.GetCustomerByIdQuery;

import static org.junit.Assert.assertEquals;

/**
 * Created by aiko on 4/8/17.
 */

public class QueryObjectTests {
    private IDataSynchronizationManager _manager;

    @org.junit.Before
    public void Initialize()
    {
        _manager = DataSynchronizationManager.GetInstance();

        List<IBaseQueryObjectConcrete<Customer>> customerQueryObjects = new ArrayList<IBaseQueryObjectConcrete<Customer>>();

        customerQueryObjects.add(new GetCustomerByIdQuery());
        customerQueryObjects.add(new GetCustomerByCivilStatusQuery());
        _manager.RegisterEntity(Customer.class, new CustomerMapper(), customerQueryObjects);
    }

    @org.junit.Test
    public void TestGetCustomerByIdQuery()
    {
        GetCustomerByIdQuery query = new GetCustomerByIdQuery();

        query.SetSearchInputObject(new GetCustomerByIdQuery.Criteria(2));

        List<Customer> resultsById = query.Execute();
        Customer matchById = resultsById.get(0);

        assertEquals("2", matchById.Number);
        assertEquals("Jane Doe", matchById.Name);
    }
}
