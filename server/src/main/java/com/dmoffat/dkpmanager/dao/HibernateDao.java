package com.dmoffat.dkpmanager.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@SuppressWarnings("unchecked")
@Transactional
public class HibernateDao<E, K extends Serializable> {

    @Autowired
    private EntityManager entityManager;
    private Class<? extends E> daoType;

    HibernateDao() {
        // getActualTypeArguments() returns the actual class objects, e.g. <Donation, Integer> for donation DAO.
        // However, this doesn't work if you don't extend this class (getGenericSuperclass returns Object), and I couldn't find a way to actually get that type information.
        daoType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
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
        entityManager.remove(entity);
    }

    public E find(K primaryKey) {
        return entityManager.find(daoType, primaryKey);
    }

    public List<E> list() {
        // todo: Use the proper method of listing entities
        return entityManager.unwrap(Session.class).createCriteria(daoType).list();
    }

    public E getProxy(K key) {
        // todo: Use the proper method of listing entities
        return entityManager.unwrap(Session.class).load(daoType, key);
    }

    /**
     * @return The total amount of rows in this entity's table without any filters applied.
     */
    public int count() {
        // todo: Use the proper method of listing entities
        return ((Long)entityManager.unwrap(Session.class).createCriteria(daoType)
                .setProjection(Projections.rowCount())
                .uniqueResult()).intValue();
    }

    /**
     * @return The total amount of rows in this entity's table with the given criteria applied.
     * Note: No version for Query, because it doesn't have a setProjection method, you can just use count(*) in your HQL query.
     */
    public int count(Criteria c) {
        if(c == null) {
            return count();
        }
        return ((Long)c.setProjection(Projections.rowCount()).uniqueResult()).intValue();
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