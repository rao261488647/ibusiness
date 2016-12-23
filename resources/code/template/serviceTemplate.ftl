package ${bussiPackage}.${entityPackage}.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibusiness.common.page.HibernateEntityDao;
import ${bussiPackage}.${entityPackage}.entity.${entityName}Entity;

/**   
 * @Title: Service
 * @Description: ${ftl_description}
 * @author JiangBo
 *
 */
@Service
@Transactional
public class ${entityName}Service extends HibernateEntityDao<${entityName}Entity> {
    /**
     * 插入
     * @param entity
     */
    public <T> void insert(T entity) {
        super.saveInsert(entity);
    }
    /**
     * 删除
     * @param entity
     */
    public void remove(${entityName}Entity entity) {
        super.remove(entity);
    }
}
