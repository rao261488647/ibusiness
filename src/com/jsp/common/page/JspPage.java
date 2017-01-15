package com.jsp.common.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * JSP用--分页参数与分页结果.
 * 
 * @author JiangBo
 */
public class JspPage {
    // ============== static fields ============================
    /** 正序. */
    public static final String ASC = "ASC";
    /** 倒序. */
    public static final String DESC = "DESC";
    /** 默认每页显示10条数据. */
    public static final int DEFAULT_PAGE_SIZE = 10;

    // ==============  fields  ============================
    /** 当前第几页，默认值为1，既第一页. */
    private int pageNo = 1;

    /** 每页最大记录数，默认值为10. */
    private int pageSize = DEFAULT_PAGE_SIZE;

    /** 查询结果. */
    private List<Map<String,Object>> rows;

    /** 总记录数，默认值为-1，表示totalCount不可用. */
    private int totalCount = 0;

    /** 总页数，默认值为-1，表示pageCount不可用. */
    private int pageCount = -1;

    // =================== constructor =======================
    /** 构造方法. */
    public JspPage() {
        totalCount = 0;
        rows = new ArrayList<Map<String,Object>>();
    }
    
    /**
     * 是否有前一页.
     * 
     * @return boolean
     */
    public boolean isPreviousEnabled() {
        return pageNo > 1;
    }

    /**
     * 是否有后一页.
     * 
     * @return boolean
     */
    public boolean isNextEnabled() {
        return pageNo < pageCount;
    }
    // ==========================================

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Map<String, Object>> getRows() {
        return rows;
    }

    public void setRows(List<Map<String, Object>> rows) {
        this.rows = rows;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    
}
