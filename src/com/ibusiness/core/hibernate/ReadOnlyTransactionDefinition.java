package com.ibusiness.core.hibernate;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DelegatingTransactionDefinition;
/**
 * 事务管理定义
 * 
 * @author JiangBo
 *
 */
public class ReadOnlyTransactionDefinition extends DelegatingTransactionDefinition {
    private static final long serialVersionUID = 0L;

    public ReadOnlyTransactionDefinition(TransactionDefinition transactionDefinition) {
        super(transactionDefinition);
    }

    public boolean isReadOnly() {
        return true;
    }
}
