package com.homeproject.homeprojectfortrainingspringframework.repository;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericJpaRepository<T> extends AbstractJpaRepository<T> implements IGenericJpaRepository<T> {

}
