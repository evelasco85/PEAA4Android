package com.codeflowcrafter.peaa.Interfaces;

import com.codeflowcrafter.peaa.DataManipulation.BaseMapperInterfaces.InvocationDelegates;
import com.codeflowcrafter.peaa.Domain.Interfaces.IDomainObject;


/**
 * Created by aiko on 4/1/17.
 */

public interface IUnitOfWork {
    <TEntity extends IDomainObject> TEntity RegisterNew(TEntity entity, InvocationDelegates invocationDelegates)
            throws NullPointerException;

    <TEntity extends IDomainObject> TEntity RegisterDirty(TEntity entity, InvocationDelegates invocationDelegates)
            throws NullPointerException;

    <TEntity extends IDomainObject> TEntity RegisterRemoved(TEntity entity, InvocationDelegates invocationDelegates)
            throws NullPointerException;

    void Commit(UoWInvocationDelegates delegates);
    void ClearUnitOfWork();
    boolean PendingCommits();
}
