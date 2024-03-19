package com.homeproject.homeprojectfortrainingspringframework.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.REQUIRES_NEW)
public abstract class AbstractJpaRepository<T> {
    private Class<T> clas;

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    public void setClas(Class<T> clasToSet) {
        this.clas = clasToSet;
    }

    public T findOne(final Integer id) {
        return entityManager.find(clas, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll(){
        return entityManager.createQuery("from "+clas.getName()+" order by id asc").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll(String query){
        return entityManager.createQuery(query).getResultList();
    }

    @SuppressWarnings("unchecked")
    public T findOne(String query) {
        List<T> list = entityManager.createQuery(query).getResultList();
        if(list != null && !list.isEmpty()) {
            return list.get(0);
        }else {
            return null;
        }
    }

    public boolean checkIfExist(String query) {
        Long isExist = (Long) entityManager.createQuery(query).getSingleResult();
        return (!isExist.equals(0L));
    }

    @SuppressWarnings("unchecked")
    public List<T> getNativeQuery(String query){
        return entityManager.createNativeQuery(query).getResultList();
    }

    public T create(final T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public T update(final T entity) {
        entityManager.merge(entity);
        return entity;
    }

    public void delete(final T entity) {
        entityManager.remove(entity);
    }

    public void deleteById(final Integer entityId) {
        final T entity = findOne(entityId);

        try {
            entity.getClass().getField("deleted").setChar(entity, '1');
            entityManager.merge(entity);
        } catch (Exception e) {
            entityManager.remove(entity);
        }
    }

    public void executeNativeQuery(String query) {
        entityManager.createNativeQuery(query).executeUpdate();
    }

}