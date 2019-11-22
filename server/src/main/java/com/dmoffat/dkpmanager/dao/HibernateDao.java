package com.dmoffat.dkpmanager.dao;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * DAO using Hibernate
 */
@SuppressWarnings("unchecked")
public class HibernateDao<E, K extends Serializable> {

    @PersistenceContext
    private EntityManager entityManager;
    private Class<E> daoType;
    private CriteriaBuilder criteriaBuilder;

    HibernateDao() {
        // getActualTypeArguments() returns the actual class objects, e.g. <Donation, Integer> for donation DAO.
        // However, this doesn't work if you don't extend this class (getGenericSuperclass returns Object), and I couldn't find a way to actually get that type information.
        daoType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    protected EntityManager entityManager() {
        return entityManager;
    }

    public void add(E entity) {
        entityManager.persist(entity);
    }

    public E update(E entity) {
        return entityManager.merge(entity);
    }

    public void remove(E entity) {
        if(!entityManager().contains(entity)) {
            entity = entityManager().merge(entity);
        }
        entityManager.remove(entity);
    }

    public E find(K primaryKey) {
        return entityManager.find(daoType, primaryKey);
    }

    public List<E> list() {
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(daoType);
        Root<E> root = criteriaQuery.from(daoType);
        TypedQuery<E> query = entityManager.createQuery(criteriaQuery.select(root))
        return query.getResultList();
    }

    public E getProxy(K key) {
        return entityManager.getReference(daoType, key);
    }

    /**
     * @return The total amount of rows in this entity's table without any filters applied.
     */
    public int count() {
        return count(null);
    }

    /**
     * @return The total amount of rows in this entity's table with the given predicate applied. Useful for stuff like
     * 'tell me the count of people with age > 10', stuff like that.
     */
    public int count(Predicate predicate) {
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(daoType)));
        if(predicate != null) {
            criteriaQuery.where(predicate);
        }
        return entityManager.createQuery(criteriaQuery).getSingleResult().intValue();
    }

    public E getSingleResult(Query query) {
        try {
            Object result = query.getSingleResult();
            return (E)result;
        } catch (NoResultException ex) {
            return null;
        }
    }

    public List<E> getResultList(Query query) {
        return (List<E>)query.getResultList();
    }
}