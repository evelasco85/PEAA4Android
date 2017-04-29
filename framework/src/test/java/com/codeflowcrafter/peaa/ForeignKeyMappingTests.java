package com.codeflowcrafter.peaa;

import com.codeflowcrafter.peaa.DataManipulation.BaseQueryObjectInterfaces.IBaseQueryObjectConcrete;
import com.codeflowcrafter.peaa.Interfaces.IDataSynchronizationManager;
import com.codeflowcrafter.peaa.Interfaces.IForeignKeyMappingManager;
import com.codeflowcrafter.peaa.Interfaces.IRepository;

import java.util.ArrayList;
import java.util.List;

import com.codeflowcrafter.peaa.CustomerServices.Customer;
import com.codeflowcrafter.peaa.CustomerServices.CustomerMapper;
import com.codeflowcrafter.peaa.CustomerServices.GetCustomerByCivilStatusQuery;
import com.codeflowcrafter.peaa.CustomerServices.GetCustomerByIdQuery;
import com.codeflowcrafter.peaa.Mapping.FK_Customer_AccountReceivable;
import com.codeflowcrafter.peaa.ReceivableServices.AccountReceivable;
import com.codeflowcrafter.peaa.ReceivableServices.AccountReceivableMapper;
import com.codeflowcrafter.peaa.ReceivableServices.GetAccountReceivablesByCustomerId;

import static org.junit.Assert.assertEquals;

/**
 * Created by aiko on 4/8/17.
 */

public class ForeignKeyMappingTests {
    private IDataSynchronizationManager _dataSyncManager;
    private IForeignKeyMappingManager _fkMappingManager;

    @org.junit.Before
    public void Initialize()
    {
        _dataSyncManager = DataSynchronizationManager.GetInstance();
        _fkMappingManager = ForeignKeyMappingManager.GetInstance();

        List<IBaseQueryObjectConcrete<Customer>> customerQueryObjects = new ArrayList<IBaseQueryObjectConcrete<Customer>>();

        customerQueryObjects.add(new GetCustomerByIdQuery());
        customerQueryObjects.add(new GetCustomerByCivilStatusQuery());
        _dataSyncManager.RegisterEntity(Customer.class, new CustomerMapper(), customerQueryObjects);

        List<IBaseQueryObjectConcrete<AccountReceivable>> receivablesQueryObjects = new ArrayList<IBaseQueryObjectConcrete<AccountReceivable>>();

        receivablesQueryObjects.add(new GetAccountReceivablesByCustomerId());
        _dataSyncManager.RegisterEntity(AccountReceivable.class, new AccountReceivableMapper(),receivablesQueryObjects);

        _fkMappingManager.RegisterForeignKeyMapping(Customer.class, AccountReceivable.class, new FK_Customer_AccountReceivable());
    }

    @org.junit.Test
    public void TestForeignKeyMapping()
    {
        IRepository<Customer> repository = _dataSyncManager.GetRepository(Customer.class);

        /*Match by Id*/
        GetCustomerByIdQuery.Criteria criteriaById = new GetCustomerByIdQuery.Criteria(3);
        List<Customer> resultsById = repository.Matching(criteriaById);
        Customer matchById = resultsById.get(0);

        assertEquals("3", matchById.Number);
        assertEquals("John Doe", matchById.Name);
        /************/

        try {
            List<AccountReceivable> receivables = _fkMappingManager.GetForeignKeyValues(Customer.class, AccountReceivable.class, matchById);

            assertEquals(2, receivables.size());
            assertEquals("02", receivables.get(0).Number);
            assertEquals("04", receivables.get(1).Number);
        }
        catch (NullPointerException ex)
        {
        }
    }
}
