package com.karthik.acc.service.impl;

import com.karthik.acc.model.SearchDto;
import com.karthik.acc.service.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class BaseServiceImpl<T,I> implements BaseService<T,I> {

    protected void validate(T t) {

    }

    protected void preInsert(T t) {

    }
    @Override
    public void create(T t) {
        validate(t);
        preInsert(t);
        getRepository().save(t);
    }

    @Override
    public void createAll(List<T> list) {
        list.forEach( e -> {
            validate(e);
            preInsert(e);
        });
        getRepository().saveAll(list);
    }

    protected void preUpdate(T t) {

    }
    @Override
    public T update(T t) {
        validate(t);
        preUpdate(t);
        return getRepository().save(t);
    }


    @Override
    public boolean deleteByUUID(I id) {
        Optional<T> optionalObj = getRepository().findById(id);
        if (optionalObj.isPresent()) {
            getRepository().delete(optionalObj.get());
            return true;
        }
        return false;
    }

    @Override
    public List<T> listAll() {
        return (List<T>) getRepository().findAll();
    }

    @Override
    public SearchDto<T> search(SearchDto searchDto) {
        return searchDto;
    }
}
