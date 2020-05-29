package com.karthik.acc.service;

import com.karthik.acc.entity.UserRequest;
import com.karthik.acc.model.SearchDto;
import com.karthik.acc.model.UserRequestDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface BaseService<T,I> {


    public void create(T t);
    public void createAll(List<T> list);
    public T update(T t);
    //public Integer delete(Integer id);
    public boolean deleteByUUID(I Id);
    public SearchDto<T> search(SearchDto searchDto);

    public CrudRepository<T, I> getRepository();

    List<T> listAll();
}
