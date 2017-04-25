package com.codeflowcrafter.peaa;

import com.codeflowcrafter.peaa.Interfaces.IDataSynchronizationManager;
import com.codeflowcrafter.peaa.Interfaces.IRepository;
import com.codeflowcrafter.peaa.DataManipulation.BaseMapperInterfaces.IBaseMapperConcrete;
import com.codeflowcrafter.peaa.DataManipulation.BaseQueryObjectInterfaces.IBaseQueryObject;
import com.codeflowcrafter.peaa.DataManipulation.BaseQueryObjectInterfaces.IBaseQueryObjectConcrete;
import com.codeflowcrafter.peaa.Domain.Interfaces.IDomainObject;
import com.codeflowcrafter.peaa.Domain.Interfaces.ISystemManipulation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aiko on 4/1/17.
 */

public class Repository <TEntity extends IDomainObject> implements IRepository<TEntity>
{
    Class<TEntity> _class;
    IDataSynchronizationManager _manager;

    public Repository(Class<TEntity> thisClass, IDataSynchronizationManager manager)
    {
        _class = thisClass;
        _manager = manager;
    }

    void ApplyDomainObjectSettings(List<TEntity> newResult, IBaseQueryObject query)
    {
        if ((newResult == null) || (newResult.size() < 1))
            return;

        IBaseMapperConcrete<TEntity> mapper = DataSynchronizationManager.GetInstance().GetMapper(_class);

        for(int index = 0; index < newResult.size(); ++index)
        {
            TEntity entity = newResult.get(index);

            ((ISystemManipulation)entity).SetQueryObject(query);
            ((ISystemManipulation)entity).SetMapper(mapper);
        }
    }

    List<TEntity> Matching(IBaseQueryObjectConcrete<TEntity> query) {
        List<TEntity> results = new ArrayList<TEntity>();

        if (query != null)
            results.addAll(query.Execute());

        ApplyDomainObjectSettings(results, query);

        return results;
    }

    public <TSearchInput> List<TEntity> Matching(TSearchInput criteria)
    {
        IBaseQueryObjectConcrete<TEntity> query = _manager.GetQueryBySearchCriteria(_class, criteria.getClass());

        query.SetSearchInputObject(criteria);

        return Matching(query);
    }
}
