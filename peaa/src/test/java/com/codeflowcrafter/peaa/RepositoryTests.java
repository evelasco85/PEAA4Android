package com.codeflowcrafter.peaa;

import com.codeflowcrafter.peaa.DataManipulation.BaseQueryObjectInterfaces.IBaseQueryObjectConcrete;
import com.codeflowcrafter.peaa.Interfaces.IDataSynchronizationManager;
import com.codeflowcrafter.peaa.Interfaces.IRepository;

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

public class RepositoryTests {
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
    public void TestRepository()
    {
        IRepository<Customer> repository = _manager.GetRepository(Customer.class);

            /*Match by civil status*/
        GetCustomerByCivilStatusQuery.Criteria criteriaByStatus = new GetCustomerByCivilStatusQuery.Criteria(GetCustomerByCivilStatusQuery.CivilStatus.Married);
        List<Customer> resultsByStatus = repository.Matching(criteriaByStatus);
        Customer matchByStatus = resultsByStatus.get(0);

        assertEquals("5", matchByStatus.Number);
        assertEquals("Test Married", matchByStatus.Name);
        /***********************/

            /*Match by Id*/
        GetCustomerByIdQuery.Criteria criteriaById = new GetCustomerByIdQuery.Criteria(2);
        List<Customer> resultsById = repository.Matching(criteriaById);
        Customer matchById = resultsById.get(0);

        assertEquals("2", matchById.Number);
        assertEquals("Jane Doe", matchById.Name);
        /************/
    }
}
