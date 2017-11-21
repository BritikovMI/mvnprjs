package ru.rbt.mvnprjs.app.ejb;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

import static ru.rbt.mvnprjs.app.ejb.PuProvider.DBHELPER_PU;

/**
 * Базовая абстракция для фабрик иплементирует некоторые основные CRUD операции
 * <p>
 * Created by misha on 03.03.17.
 */
public abstract class AbstractEntityDao<I extends Serializable, E> {
    @PersistenceContext(unitName = DBHELPER_PU)
    private EntityManager em;

    private Class<E> eClass;

    public AbstractEntityDao(Class<E> eClass) {
        this.eClass = eClass;
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    public E find(I id) {
        return em.find(eClass, id);
    }

    public E update(E entity) {
        return em.merge(entity);
    }

    public E save(E entity) {
        em.persist(entity);
        return entity;
    }

    public void remove(E entity) {
        em.remove(entity);
    }

    public List<E> list() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<E> criteria = builder.createQuery(eClass);
        Root<E> root = criteria.from(eClass);
        criteria.select(root);
        return em.createQuery(criteria).getResultList();
    }

    public Long count() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<E> root = criteria.from(eClass);
        criteria.select(builder.count(root));
        return em.createQuery(criteria).getSingleResult();
    }
}
