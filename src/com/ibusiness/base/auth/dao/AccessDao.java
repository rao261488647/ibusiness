package com.ibusiness.base.auth.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ibusiness.base.auth.entity.Access;
import com.ibusiness.base.auth.entity.Perm;
import com.ibusiness.common.page.HibernateEntityDao;
/**
 * 资源访问权限表DAO
 * 
 * @author JiangBo
 *
 */
@Service
public class AccessDao extends HibernateEntityDao<Access> {
    private static final int PRIORITY_STEP = 10;

    public void batchSave(String scopeId, String type, List<String> ids,
            List<String> values, List<String> perms) {
        int priority = 0;

        for (int i = 0; i < values.size(); i++) {
            String idStr = ids.get(i);
            String value = values.get(i);
            String permStr = perms.get(i);

            if (value.trim().length() == 0) {
                continue;
            }

            Access access = createOrGetAccess(idStr, value, type, permStr,
                    scopeId);

            priority += PRIORITY_STEP;
            access.setPriority(priority);

            Perm perm = this.createOrGetPerm(permStr, scopeId);
            access.setPerm(perm);

            this.save(access);
        }
    }

    public Access createOrGetAccess(String id, String value, String type,
            String perm, String scopeId) {
        Access access;

        if (id.length() != 0) {
            access = this.get(id);
        } else {
            access = this.findUnique(
                    "from Access where value=? and type=? and scope_id=?",
                    value, type, scopeId);
        }

        if (access == null) {
            access = new Access();
            access.setType(type);
            access.setValue(value);
            access.setScopeid(scopeId);
            this.save(access);
        }

        return access;
    }

    public Perm createOrGetPerm(String code, String scopeId) {
        Perm perm = this.findUnique("from Perm where code=? and scope_id=?",
                code, scopeId);

        if (perm == null) {
            perm = new Perm();
            perm.setCode(code);
            perm.setName(code);
            perm.setScopeid(scopeId);
            this.save(perm);
        }

        return perm;
    }
}
