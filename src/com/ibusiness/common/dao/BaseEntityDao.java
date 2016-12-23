package com.ibusiness.common.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.common.page.EntityCreatedEvent;
import com.ibusiness.common.page.EntityRemovedEvent;
import com.ibusiness.common.page.EntityUpdatedEvent;
import com.ibusiness.common.page.HibernateUtils;
import com.ibusiness.common.page.MatchType;
import com.ibusiness.common.page.PropertyFilter;
import com.ibusiness.core.hibernate.HibernateGenericDao;
import com.ibusiness.core.util.GenericsUtils;

/**
 * 使用泛型的hibernate基类.
 * 
 * @author JiangBo
 * @param <T>
 *            实体类型
 */
public class BaseEntityDao<T> extends HibernateGenericDao {
    /** logger. */
    private static Logger logger = LoggerFactory.getLogger(BaseEntityDao.class);

    /** 持久类的类型. */
    private Class<T> entityClass;

    /** 构造方法. */
    @SuppressWarnings("unchecked")
    public BaseEntityDao() {
        this.entityClass = GenericsUtils.getSuperClassGenericType(this.getClass());
    }

    /**
     * constructor.
     * 
     * @param sessionFactory
     *            SessionFactory
     */
    public BaseEntityDao(SessionFactory sessionFactory) {
        this();
        super.setSessionFactory(sessionFactory);
    }

    /**
     * constructor.
     * 
     * @param sessionFactory
     *            SessionFactory
     * @param entityClass
     *            Class
     */
    public BaseEntityDao(SessionFactory sessionFactory, Class<T> entityClass) {
        super(sessionFactory);
        this.entityClass = entityClass;
    }

    /**
     * 子类可以获得泛型对应的实体类型.
     * 
     * @return entityClass
     */
    public Class<T> getEntityClass() {
        return entityClass;
    }

    /**
     * @param entityClass
     *            Class.
     */
    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    // ============================================================================================
    // get, load, getAll, save, remove, removeById, removeAll
    // ============================================================================================
    /**
     * 获得一个实体类型的一条记录.
     * 
     * @param id
     *            主键
     * @return 实例
     */
    @Transactional(readOnly = true)
    public T get(Serializable id) {
        return this.get(this.entityClass, id);
    }

    /**
     * load一个实例，如果id不存在，会返回一个proxy，在调用proxy的时候出现问题. 使用这个方法，可以利用缓存，但是如果实例不存在，会出现不容易预计的错误
     * 
     * @param id
     *            主键
     * @return 实例
     */
    @Transactional(readOnly = true)
    public T load(Serializable id) {
        return this.load(this.entityClass, id);
    }

    /**
     * 获得一个实体类型的所有记录.
     * 
     * @return 所有实例列表
     */
    @Transactional(readOnly = true)
    public List<T> getAll() {
        return this.getAll(this.entityClass);
    }

    /**
     * 获得所有记录，带排序参数.
     * 
     * @param orderBy
     *            排序字段名
     * @param isAsc
     *            是否正序排列
     * @return 返回结果列表
     */
    @Transactional(readOnly = true)
    public List<T> getAll(String orderBy, boolean isAsc) {
        return this.getAll(this.entityClass, orderBy, isAsc);
    }

    /**
     * 根据主键删除记录.
     * 
     * @param id
     *            主键
     */
    @Transactional
    public void removeById(Serializable id) {
        this.remove(this.get(id));
    }

    /**
     * 删除所有记录.
     */
    @Transactional
    public void removeAll() {
        this.removeAll(this.getAll());
    }

    @Transactional
    @Override
    public void save(Object entity) {
        try {
            boolean isCreated = getId(entity.getClass(), entity) == null;
            super.save(entity);

            if (isCreated) {
                publishEvent(new EntityCreatedEvent(entity));
            } else {
                publishEvent(new EntityUpdatedEvent(entity));
            }
        } catch (NoSuchMethodException ex) {
            logger.warn(ex.getMessage(), ex);
            super.save(entity);
        } catch (IllegalAccessException ex) {
            logger.warn(ex.getMessage(), ex);
            super.save(entity);
        } catch (InvocationTargetException ex) {
            logger.warn(ex.getMessage(), ex);
            super.save(entity);
        }
    }

    @Transactional
    @Override
    public void remove(Object entity) {
        super.remove(entity);
        publishEvent(new EntityRemovedEvent(entity));
    }

    // ============================================================================================
    // createCriteria
    // ============================================================================================

    /**
     * 根据entityClass生成对应类型的Criteria.
     * 
     * @param criterions
     *            条件
     * @return Criteria
     */
    public Criteria createCriteria(Criterion... criterions) {
        return this.createCriteria(this.entityClass, criterions);
    }

    /**
     * 根据entityClass，生成带排序的Criteria.
     * 
     * @param orderBy
     *            排序字段名
     * @param isAsc
     *            是否正序
     * @param criterions
     *            条件
     * @return Criteria
     */
    public Criteria createCriteria(String orderBy, boolean isAsc, Criterion... criterions) {
        return this.createCriteria(this.entityClass, orderBy, isAsc, criterions);
    }

    // ============================================================================================
    // findBy
    // ============================================================================================

    /**
     * 根据name，value进行查询.
     * 
     * @param name
     *            字段名
     * @param value
     *            参数值
     * @return 查询结果
     */
    @Transactional(readOnly = true)
    public List<T> findBy(String name, Object value) {
        return this.findBy(this.entityClass, name, value);
    }

    /**
     * find by ids.
     * 
     * @param entityClass
     *            Class
     * @param ids
     *            List
     * @param <T>
     *            generic
     * @return List
     */
    @Transactional(readOnly = true)
    public List<T> findByIds(List ids) {
        return this.findByIds(entityClass, ids);
    }

    /**
     * 根据name,value进行模糊查询.
     * 
     * @param name
     *            字段名
     * @param value
     *            用来做模糊查询的字段值
     * @return 查询结果
     */
    @Transactional(readOnly = true)
    public List<T> findByLike(String name, Object value) {
        return this.findByLike(this.entityClass, name, value);
    }

    /**
     * 查询唯一记录.
     * 
     * @param name
     *            字段名
     * @param value
     *            字段值
     * @return 实例
     */
    @Transactional(readOnly = true)
    public T findUniqueBy(String name, Object value) {
        return this.findUniqueBy(this.entityClass, name, value);
    }

    // ============================================================================================
    // PropertyFilter
    // ============================================================================================
    /**
     * findBy.
     * 
     * @param propertyName
     *            String
     * @param propertyValue
     *            Object
     * @param matchType
     *            MatchType
     * @return List
     */
    public List<T> findBy(String propertyName, Object propertyValue, MatchType matchType) {
        return find(this.entityClass, HibernateUtils.buildCriterion(propertyName, propertyValue, matchType));
    }

    /**
     * find.
     * 
     * @param propertyFilters
     *            list
     * @return List
     */
    public List<T> find(List<PropertyFilter> propertyFilters) {
        return find(this.entityClass, HibernateUtils.buildCriterion(propertyFilters));
    }
}
