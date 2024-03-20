package com.springframework.ticketservice.repository;

import java.util.List;

public interface IGenericRepository <T>{


        void setClas(Class<T> clasToSet);

        T findOne(final Integer id);

        T findOne(String query);

        T create(final T entity);

        T update(final T entity);

        List<T> findAll();

        List<T> findAll(String query);

        List<T> getNativeQuery(String query);

        boolean checkIfExist(String query);

        void delete(final T entity);

        void deleteById(final Integer entityId);

        void executeNativeQuery(String query);



}
