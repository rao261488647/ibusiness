package com.jsp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.ibusiness.common.util.Constants;
import com.jsp.common.page.JspPage;
import com.jsp.common.service.JspService;

/**
 * JSP页面共用方法类
 * 
 * @author JiangBo
 *
 */
public class JspCommonUtils extends ApplicationObjectSupport {

    /**
     * 单例模式
     */
    private static JspCommonUtils instance = new JspCommonUtils();
    public JspCommonUtils() {}
    public static JspCommonUtils getInstance() {
        return instance;
    }

    /**
     * 字符串,日期类型转换用变量
     */
    private SimpleDateFormat ymdhms = new SimpleDateFormat(Constants.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
    private SimpleDateFormat ymd = new SimpleDateFormat(Constants.DATE_FORMAT_YYYY_MM_DD);
    // Log4j
    private static Logger logger = LoggerFactory.getLogger(JspCommonUtils.class);
    
    private static JspService jspService;
    
    // ====================================================================================
    
	/**
     * 根据sql查询
     * @param sql
     * @return
     */
	public List<Map<String, Object>> queryListBySql(String sql) {
    	
		//注意一点，在服务器启动时，Spring容器初始化时，不能通过以下方法获取Spring 容器，如需细节可以观看源码org.springframework.web.context.ContextLoader
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();  
		jspService = (JspService) context.getBean("jspService");
    	
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	list = jspService.getJdbcTemplate().queryForList(sql);
    	System.out.println(list);
    	
    	return list;
    }
	
    /**
     * 根据表名, where等条件取得指定表多条信息
     * @return
     */
    public List<Map<String,Object>> getListByTableName(String tableName, String where, String orerby, String pageNo, String pageSize) {
        
    	WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();  //注意一点，在服务器启动时，Spring容器初始化时，不能通过以下方法获取Spring 容器，如需细节可以观看源码org.springframework.web.context.ContextLoader
		jspService = (JspService) context.getBean("jspService");
    	
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	String wheres = "1 = 1";
    	if(where != null) {
    		wheres = wheres + " " + where;
    	}
		String sql = "";
    	if(!isNull(pageNo)) {
    		if("1".equals(pageNo)) { // 判断起始位置
    			pageNo = "1";
    		} else {
    			pageNo = ((Integer.parseInt(pageNo) * Integer.parseInt(pageSize)) + 1) + "";
    		}
    		sql = "select * from " + tableName + " where " + wheres + " order by " + orerby + " desc " + "limit " + pageNo + "," + pageSize;
    	} else {
    		sql = "select * from " + tableName + " where " + wheres + " order by " + orerby + " desc";
    	}
    	list = jspService.getJdbcTemplate().queryForList(sql);
    	
        return list;
    }
    
    /**
     * 根据表名和id取得指定表中的一条信息
     * @return
     */
    public Map<String,Object> getMapById(String tableName, String id) {
        
    	WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();  //注意一点，在服务器启动时，Spring容器初始化时，不能通过以下方法获取Spring 容器，如需细节可以观看源码org.springframework.web.context.ContextLoader
		jspService = (JspService) context.getBean("jspService");
    	
		Map<String,Object> map = new HashMap<String,Object>();
        
		String sql = "select * from " + tableName + " where id = ?";
    	map = jspService.getJdbcTemplate().queryForMap(sql, id);
        
    	return map;
    }
    
    /**
     * 新增
     * @param tableName
     * @param map
     * @return
     */
    public void saveByName(String tableName, Map<String, Object> map) {
    	
    	WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();  //注意一点，在服务器启动时，Spring容器初始化时，不能通过以下方法获取Spring 容器，如需细节可以观看源码org.springframework.web.context.ContextLoader
		jspService = (JspService) context.getBean("jspService");
    	
		String id = UUID.randomUUID().toString();
		// 获取key
		String colum = "(id, " + getKey(map) + ")";
		// 获取value
		String values = "('" + id + "', " + getValue(map) + ")";
    	String sql = "insert into " + tableName + " " + colum + " values " + values;
    	
    	jspService.saveByName(sql);

    }
    
    /**
     * 根据id删除信息
     * @param tableName
     * @param id
     */
    public int deleteById(String tableName, String id) {
    	
    	WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();  //注意一点，在服务器启动时，Spring容器初始化时，不能通过以下方法获取Spring 容器，如需细节可以观看源码org.springframework.web.context.ContextLoader
		jspService = (JspService) context.getBean("jspService");
		String sql = "delete from " + tableName + " where id = ?";
		int x = jspService.deleteById(sql, id);
//		int x = jspService.getJdbcTemplate().update(sql, new Object[]{id});
    	return x;
    }
    
    /**
     * 更新信息
     */
    public int updateBySql(String sql) {
    	
    	WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();  //注意一点，在服务器启动时，Spring容器初始化时，不能通过以下方法获取Spring 容器，如需细节可以观看源码org.springframework.web.context.ContextLoader
		jspService = (JspService) context.getBean("jspService");
		
		int x = jspService.updateBySql(sql);  
		
		return x;
    }
    
    
    /**
     * 分页信息--根据表名, where等条件取得指定表多条信息
     * @return
     */
    public JspPage getPageByTableName(String tableName, String where, String orerby, String pageNo, String pageSize) {
        
    	WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();  //注意一点，在服务器启动时，Spring容器初始化时，不能通过以下方法获取Spring 容器，如需细节可以观看源码org.springframework.web.context.ContextLoader
		jspService = (JspService) context.getBean("jspService");
    	
		String wheres = "1 = 1";
    	if(where != null) {
    		wheres = wheres + " " + where;
    	}
		
		JspPage jsp = new JspPage();
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	
    	String sql = "select * from " + tableName + " where " + wheres + " order by " + orerby + " desc " + "limit " + pageNo + "," + pageSize;
    	list = jspService.getJdbcTemplate().queryForList(sql);
    	
    	// 封装JspPage
    	jsp.setPageCount(Integer.parseInt(pageSize));
    	jsp.setPageNo(Integer.parseInt(pageNo));
    	jsp.setTotalCount(list.size());
    	jsp.setRows(list);
    	
        return jsp;
    }
    
    /**
     * 分页信息--根据表ID, where等条件取得指定表多条信息
     * @return
     */
    public JspPage getPageByTableId(String tableId, String where, String orerby, String pageNo, String pageSize) {
        
    	WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();  //注意一点，在服务器启动时，Spring容器初始化时，不能通过以下方法获取Spring 容器，如需细节可以观看源码org.springframework.web.context.ContextLoader
		jspService = (JspService) context.getBean("jspService");
    	
		JspPage jsp = new JspPage();
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	
    	String wheres = "1 = 1";
    	if(where != null) {
    		wheres = wheres + " " + where;
    	}
    	
    	//根据表id获取表名
    	String sql1 = "select tableName from ib_conf_table where id = ?";
    	Map<String, Object> map = jspService.getJdbcTemplate().queryForMap(sql1, tableId);
        String tableName = map.get("tableName").toString();
        
        String sql = "select * from " + tableName + " where " + wheres + " order by " + orerby + " desc " + "limit " + pageNo + "," + pageSize;
        list = jspService.getJdbcTemplate().queryForList(sql);
        
        // 封装JspPage
    	jsp.setPageCount(Integer.parseInt(pageSize));
    	jsp.setPageNo(Integer.parseInt(pageNo));
    	jsp.setTotalCount(list.size());
    	jsp.setRows(list);
        
    	return jsp;
    }
    
    /**
     * 根据表ID, where等条件取得指定表多条信息
     * @return
     */
    public List<Map<String, Object>> getListByTableId(String tableId, String where, String orerby, String pageNo, String pageSize) {
        
    	WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();  //注意一点，在服务器启动时，Spring容器初始化时，不能通过以下方法获取Spring 容器，如需细节可以观看源码org.springframework.web.context.ContextLoader
		jspService = (JspService) context.getBean("jspService");
    	
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	
		String wheres = "1 = 1";
    	if(where != null) {
    		wheres = wheres + " " + where;
    	}
		
    	//根据表id获取表名
    	String sql1 = "select tableName from ib_conf_table where id = ?";
    	Map<String, Object> map = jspService.getJdbcTemplate().queryForMap(sql1, tableId);
        String tableName = map.get("tableName").toString();
        
        String sql = "select * from " + tableName + " where " + wheres + " order by " + orerby + " desc " + "limit " + pageNo + "," + pageSize;
        list = jspService.getJdbcTemplate().queryForList(sql);
    	
        return list;
    }
    
    /**
     * 获取记录数量
     * @param sql
     * @return
     */
    @SuppressWarnings("deprecation")
	public int getCountBySql(String sql){
    	
    	WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();  //注意一点，在服务器启动时，Spring容器初始化时，不能通过以下方法获取Spring 容器，如需细节可以观看源码org.springframework.web.context.ContextLoader
		jspService = (JspService) context.getBean("jspService");
		
		int count = jspService.getJdbcTemplate().queryForInt(sql);
		
		return count;
    }
    
    /**
     * 根据session判断是否登录
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
	public boolean isLogin(HttpServletRequest request){
    	
    	boolean flag = false;
    	HttpSession session = request.getSession();
		Map<String,Object> map = (Map<String, Object>) session.getAttribute("user");
		if(map != null) {
			flag = true;
		}
    	
    	return flag;
    }
    
    /**
     * 登录
     * @param request
     */
    public void login(HttpServletRequest request) {
    	
    }
   
    /**
     * 登出
     * @param request
     */
    public void loginOut(HttpServletRequest request) {
    	
    }
	
	// ===============================================================================================
    
    /**
     * 获取map中key
     * @param map
     * @return
     */
    public String getKey(Map<String, Object> map) {
    	
    	// 获取key
		String key = map.keySet().toString();
		String keys = key.substring(1, key.length() - 1);
		
		return keys;
    }
    
    /**
     * 获取map中values
     * @param map
     * @return
     */
	public String getValue(Map<String, Object> map) {

		// 获取value
		String val = map.values().toString();
		String values = val.substring(1, val.length() - 1);
		
		return values;
	}
    
    
    
    // ===============================================================================================
    /**
     * 判断字符串是否为bull
     * @return true 为空 false 不为空
     */
    public static boolean isNull(String str) {
        return (null == str || "".equals(str) || "null".equals(str)) ? true : false;
    }
    /**
     * @return the ymdhms
     */
    public SimpleDateFormat getYmdhms() {
        return ymdhms;
    }
    /**
     * @param ymdhms the ymdhms to set
     */
    public void setYmdhms(SimpleDateFormat ymdhms) {
        this.ymdhms = ymdhms;
    }
	public SimpleDateFormat getYmd() {
		return ymd;
	}
	public void setYmd(SimpleDateFormat ymd) {
		this.ymd = ymd;
	}
	
	//=================================================================================================
	public static void main(String[] args) {
		String driver = "com.mysql.jdbc.Driver";
		// 数据库url
		String url = "jdbc:mysql://localhost:3306/ibusiness";
		// 填入数据库的用户名跟密码
		String username = "root";
		String password = "123456";
		String sql = "select * from ib_conf_table";// 编写要执行的sql语句
		try {
			Class.forName(driver);// 加载驱动程序，此处运用隐式注册驱动程序的方法
		} catch (ClassNotFoundException e) {
			System.err.println("加载驱动失败");
			e.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection(url, username,
					password);// 创建连接对象
			Statement st = con.createStatement();// 创建sql执行对象
			ResultSet rs = st.executeQuery(sql);// 执行sql语句并返回结果集
			while (rs.next()){// 对结果集进行遍历输出
				System.out.println("1: " + rs.getString(1));// 通过列的标号来获得数据
				System.out.println("2: " + rs.getString(2));
				System.out.println("3: " + rs.getString(3));
				System.out.println("4: " + rs.getString(4));
				System.out.println("5: " + rs.getString(5));
				System.out.println("6: " + rs.getString(6));
				System.out.println("7: " + rs.getString(7));
			}
			// 关闭相关的对象
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
