package com.codeflowcrafter.peaa.TransactionScripts;

import com.codeflowcrafter.peaa.DataSynchronizationManager;
import com.codeflowcrafter.peaa.Interfaces.IRepository;
import com.codeflowcrafter.peaa.Interfaces.IUnitOfWork;
import com.codeflowcrafter.peaa.TransactionScript;

import java.util.ArrayList;
import java.util.List;

import com.codeflowcrafter.peaa.CustomerServices.Customer;
import com.codeflowcrafter.peaa.CustomerServices.CustomerInvocationDelegates;
import com.codeflowcrafter.peaa.CustomerServices.GetCustomerByCivilStatusQuery;

/**
 * Created by aiko on 4/8/17.
 */

public class AlterMarriedStatusIntoSingleTS  extends TransactionScript<GetCustomerByCivilStatusQuery.Criteria, AlterMarriedStatusIntoSingleTS.TransactionResult> {
    public class TransactionResult
    {
        public List<Customer> SuccessfullyAlteredCustomers = new ArrayList<Customer>();
    }

    public AlterMarriedStatusIntoSingleTS()
    {
        super(DataSynchronizationManager.GetInstance(), DataSynchronizationManager.GetInstance());
    }

    @Override
    public TransactionResult ExecutionBody() {
        IRepository<Customer> repository = GetRepositoryRegistry().GetRepository(Customer.class);
        GetCustomerByCivilStatusQuery.Criteria criteriaByStatus = GetInput();
        IUnitOfWork uow = CreateUnitOfWork();

        List<Customer> customers = repository.Matching(criteriaByStatus);
        CustomerInvocationDelegates delegates = new CustomerInvocationDelegates();

        for (Customer customer: customers) {
            customer.Name = "Test is now single";

            try
            {
                uow.RegisterDirty(customer, delegates);
            }
            catch (NullPointerException ex)
            {
            }
        }

        TransactionResult result = new TransactionResult();

        uow.Commit(new AlterMarriedInvocationDelegates(result));

        return result;
    }
}
