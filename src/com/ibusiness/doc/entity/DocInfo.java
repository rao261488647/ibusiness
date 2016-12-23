package com.ibusiness.doc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * DocInfo 文档.
 * 
 * @author JiangBo
 */
@Entity
@Table(name = "IB_DOC_INFO")
public class DocInfo implements java.io.Serializable {
    private static final long serialVersionUID = 0L;
    // ID
    private String id;
    // 逻辑文件名
    private String docName;
    // 物理文件名
    private String name;
    // 路径
    private String path;
    // 文件类别
    private String docType;
    // 创建时间
    private Date createTime;
    // 上传用户ID
    private String userId;
    // 备注
    private String descn;
    // 范围
    private String scopeid;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    @Column(name = "NAME", length = 200)
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "PATH", length = 200)
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    @Column(name = "DOCTYPE")
    public String getDocType() {
        return this.docType;
    }
    public void setDocType(String docType) {
        this.docType = docType;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME", length = 26)
    public Date getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Column(name = "USER_ID")
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    @Column(name = "DESCN")
    public String getDescn() {
        return this.descn;
    }
    public void setDescn(String descn) {
        this.descn = descn;
    }
    @Column(name = "SCOPEID")
    public String getScopeid() {
        return scopeid;
    }
    public void setScopeid(String scopeid) {
        this.scopeid = scopeid;
    }
    @Column(name = "DOCNAME")
    public String getDocName() {
        return docName;
    }
    public void setDocName(String docName) {
        this.docName = docName;
    }
}
