package com.dmoffat.dkpmanager.dao;

import com.dmoffat.dkpmanager.model.pagination.Results;

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

    HibernateDao() {
        // getActualTypeArguments() returns the actual class objects, e.g. <Donation, Integer> for donation DAO.
        // However, this doesn't work if you don't extend this class (getGenericSuperclass returns Object), and I couldn't find a way to actually get that type information.
        daoType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected EntityManager entityManager() {
        return entityManager;
    }

    public Predicate propertyEquals(String propertyName, Object value) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        Root<E> root =  criteriaBuilder.createQuery(daoType).from(daoType);
        return criteriaBuilder.equal(root.get(propertyName), value);
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
        TypedQuery<E> query = listQuery();
        return query.getResultList();
    }

    public List<E> list(Results.Parameters params) {
        TypedQuery<E> query = listQuery();
        query.setMaxResults(params.getItemsPerPage());
        query.setFirstResult(params.getOffset());
        return query.getResultList();
    }

    private TypedQuery<E> listQuery() {
        CriteriaQuery<E> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(daoType);
        Root<E> root = criteriaQuery.from(daoType);
        return entityManager.createQuery(criteriaQuery.select(root));
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
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
        criteriaQuery.select(entityManager.getCriteriaBuilder().count(criteriaQuery.from(daoType)));
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