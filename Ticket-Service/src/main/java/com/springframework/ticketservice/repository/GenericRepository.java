package com.springframework.ticketservice.repository;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericRepository<T> extends AbstractRepository<T> implements IGenericRepository<T> {

}