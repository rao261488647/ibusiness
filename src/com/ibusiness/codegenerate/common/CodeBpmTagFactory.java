package com.ibusiness.codegenerate.common;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibusiness.codegenerate.code.Columnt;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.common.util.Constants;
import com.ibusiness.component.form.entity.ConfFormTableColumn;
import com.ibusiness.component.table.entity.ConfTable;
import com.ibusiness.core.util.StringUtils;


/**
 * CodeBpmTagFactory 流程用 生成页面标签工厂类
 * 
 * @author JiangBo
 *
 */
public class CodeBpmTagFactory {
    private CodeBpmTagFactory () {}
    private static CodeBpmTagFactory instance = new CodeBpmTagFactory();
    public static CodeBpmTagFactory getInstance() {
        return instance;
    }
    private static Logger logger = LoggerFactory.getLogger(CodeBpmTagFactory.class);
    /**
     * 组件map
     */
    private static Map<String, String> tagComponentMap = new HashMap<String, String>();
    static {
        // 单行输入框
        tagComponentMap.put(Constants.CODE_SINGLE_INPUT, "singleInputParser");
        // 多行输入框
        tagComponentMap.put(Constants.CODE_MULTI_INPUT, "multiInputParser");
        // 数值
        tagComponentMap.put(Constants.CODE_NUMBER_INPUT, "numberInputParser");
        // 日期
        tagComponentMap.put(Constants.CODE_DATE, "dateParser");
        // 时间
        tagComponentMap.put(Constants.CODE_DATE_TIME, "dateTimeParser");
        // 下拉列表
        tagComponentMap.put(Constants.CODE_SELECT, "selectParser");
        // 数据字典
        tagComponentMap.put(Constants.CODE_DATA_SELECT, "dataSelectParser");
        // 单选按钮
        tagComponentMap.put(Constants.CODE_RADIOBOX, "radioBoxParser");
        // 复选按钮
        tagComponentMap.put(Constants.CODE_CHECKBOX, "checkBoxParser");
        // 选择带出
        tagComponentMap.put(Constants.CODE_SELECT_INPUT, "selectInputParser");
        // 附件上传组件
        tagComponentMap.put(Constants.CODE_UPLOAD, "uploadParser");
        // 附件下载组件
        tagComponentMap.put(Constants.CODE_DOWNLOAD, "downloadParser");
        // 图片上传组件
        tagComponentMap.put(Constants.CODE_IMG_LOAD, "imgUploadParser");
    }
    
    /**
     * 根据标签类型生成标签
     * 
     * @param tagType
     * @param formColumn
     */
    public Columnt CreateTagComponent(Columnt columnt, ConfFormTableColumn formColumn) {
        // 根据事件类型解析事件
        try {
            if (tagComponentMap.containsKey(formColumn.getFcType())) {
                // 控件可编辑
                if ("1".equals(columnt.getFcEdit())) {
                    String strMethod = tagComponentMap.get(formColumn.getFcType());
                    columnt = (Columnt) CodeBpmTagFactory.class.getDeclaredMethod(strMethod, Columnt.class, ConfFormTableColumn.class).invoke(instance, columnt, formColumn);
                    return columnt;
                } else {
                    // 不可编辑
                    String str = "";
                    str = str + "<div class=\"col-lg-3 col-md-3 col-sm-3\">";
                    // 日期时间
                    if (Constants.CODE_DATE.equals(formColumn.getFcType()) || Constants.CODE_DATE_TIME.equals(formColumn.getFcType())) {
                        str = str + "  <fmt:formatDate value=\"${model."+columnt.getFieldName()+"}\" pattern=\"yyyy-MM-dd HH:mm:ss\" />";
                    } else {
                        // 非日期时间
                        str = str + "  <label>${model."+columnt.getFieldName()+"}</label>";
                    }
                    str = str + "  <input id=\"code-"+columnt.getFieldName()+"\" type=\"hidden\" name=\""+columnt.getFieldName()+"\" value=\"${model."+columnt.getFieldName()+"}\" >";
                    str = str + "</div>";
                    columnt.setJspTagInfo(str);
                    return columnt;
                }
            } else {
                logger.error("================要求生成的页面控件不存在:" + formColumn.getFcType());
            }
        } catch (IllegalArgumentException e) {
            logger.error("===============生成页面控件 IllegalArgumentException:" + e.toString());
        } catch (SecurityException e) {
            logger.error("===============生成页面控件 SecurityException:" + e.toString());
        } catch (IllegalAccessException e) {
            logger.error("===============生成页面控件 IllegalAccessException:" + e.toString());
        } catch (InvocationTargetException e) {
            logger.error("===============生成页面控件 InvocationTargetException:" + e.toString());
        } catch (NoSuchMethodException e) {
            logger.error("===============生成页面控件 NoSuchMethodException:" + e.toString());
        }
        return null;
    }
    
    /**
     * 单行输入框
     */
    public Columnt singleInputParser(Columnt columnt, ConfFormTableColumn formColumn) {
        // 取得参数里面最大 / 最小 输入长度
        String maxlength = getMaxlength(formColumn.getConfSelectInfo());
        String minlength = getMinlength(formColumn.getConfSelectInfo());
        String str = "";
        str = str + "<div class=\"col-lg-3 col-md-3 col-sm-3\">";
        str = str + "  <c:if test=\"${nodeColumsMap."+columnt.getFieldName()+".fcEdit=='1'}\">";
        str = str + "    <input id=\"code-"+columnt.getFieldName()+"\" type=\"text\" name=\""+columnt.getFieldName()+"\" value=\"${model."+columnt.getFieldName()+"}\" class=\"text form-control input-sm "+("1".equals(formColumn.getFcMust())? "required" : "")+"\" "+(null==maxlength? "":" maxlength=\""+maxlength+"\"")+(null==minlength? "":" minlength=\""+minlength+"\"")+" >";
        str = str + "  </c:if>";
        str = str + "  <c:if test=\"${nodeColumsMap."+columnt.getFieldName()+".fcEdit!='1'}\">";
        str = str + "    <label>${model."+columnt.getFieldName()+"}</label>";
        str = str + "    <input type=\"hidden\" name=\""+columnt.getFieldName()+"\" value=\"${model."+columnt.getFieldName()+"}\">";
        str = str + "  </c:if>";
        str = str + "</div>";
        columnt.setJspTagInfo(str);
        return columnt;
    }
    /**
     * 多行输入框
     */
    public Columnt multiInputParser(Columnt columnt, ConfFormTableColumn formColumn) {
        String str = "<!-- 编辑类型     多行 -->";
        str = str + "<div class=\"col-lg-6 col-md-6 col-sm-6\">";
        str = str + "  <c:if test=\"${nodeColumsMap."+columnt.getFieldName()+".fcEdit=='1'}\">";
        str = str + "    <textarea class=\"form-control\" id=\"code-"+columnt.getFieldName()+"\" name=\""+columnt.getFieldName()+"\" rows=\"1\" >${model."+columnt.getFieldName()+"}</textarea>";
        str = str + "  </c:if>";
        str = str + "  <c:if test=\"${nodeColumsMap."+columnt.getFieldName()+".fcEdit!='1'}\">";
        str = str + "    <label>${model."+columnt.getFieldName()+"}</label>";
        str = str + "    <input type=\"hidden\" name=\""+columnt.getFieldName()+"\" value=\"${model."+columnt.getFieldName()+"}\">";
        str = str + "  </c:if>";
        str = str + "</div>";
        columnt.setJspTagInfo(str);
        return columnt;
    }
    /**
     * 数值
     */
    public Columnt numberInputParser(Columnt columnt, ConfFormTableColumn formColumn) {
        String str = "";
        str = str + "<div class=\"col-lg-3 col-md-3 col-sm-3\">";
        str = str + "  <c:if test=\"${nodeColumsMap."+columnt.getFieldName()+".fcEdit=='1'}\">";
        str = str + "    <input id=\"code-"+columnt.getFieldName()+"\" type=\"text\" name=\""+columnt.getFieldName()+"\" value=\"${model."+columnt.getFieldName()+"}\" class=\"text number required\" >";
        str = str + "  </c:if>";
        str = str + "  <c:if test=\"${nodeColumsMap."+columnt.getFieldName()+".fcEdit!='1'}\">";
        str = str + "    <label>${model."+columnt.getFieldName()+"}</label>";
        str = str + "    <input type=\"hidden\" name=\""+columnt.getFieldName()+"\" value=\"${model."+columnt.getFieldName()+"}\">";
        str = str + "  </c:if>";
        str = str + "</div>";
        columnt.setJspTagInfo(str);
        return columnt;
    }
    /**
     * 日期
     */
    public Columnt dateParser(Columnt columnt, ConfFormTableColumn formColumn) {
        String str = "";
        str = str + "<div class=\"col-lg-3 col-md-3 col-sm-3\">";
        str = str + "<c:if test=\"${nodeColumsMap."+columnt.getFieldName()+".fcEdit=='1'}\">";
        str = str + "  <div class=\"input-append datepicker date\">";
        str = str + "  <span class=\"add-on\">";
        str = str + "    <input id=\"code-"+columnt.getFieldName()+"\" type=\"text\" name=\""+columnt.getFieldName()+"\" value=\"${model."+columnt.getFieldName()+"}\" class=\"text required\" readonly >";
        str = str + "  </span>";
        str = str + "  </div>";
        str = str + "</c:if>";
        str = str + "<c:if test=\"${nodeColumsMap."+columnt.getFieldName()+".fcEdit!='1'}\">";
        str = str + "  <label>${model."+columnt.getFieldName()+"}</label>";
        str = str + "  <input type=\"hidden\" name=\""+columnt.getFieldName()+"\" value=\"${model."+columnt.getFieldName()+"}\">";
        str = str + "</c:if>";
        str = str + "</div>";
        columnt.setJspTagInfo(str);
        return columnt;
    }
    /**
     * 时间
     */
    public Columnt dateTimeParser(Columnt columnt, ConfFormTableColumn formColumn) {
        // TODO 未实现
        String str = "";
        str = str + "<div class=\"col-lg-3 col-md-3 col-sm-3\">";
        str = str + "<c:if test=\"${nodeColumsMap."+columnt.getFieldName()+".fcEdit=='1'}\">";
        str = str + "  <div class=\"input-append datepicker date\">";
        str = str + "  <span class=\"add-on\">";
        str = str + "    <input id=\"code-"+columnt.getFieldName()+"\" type=\"text\" name=\""+columnt.getFieldName()+"\" value=\"${model."+columnt.getFieldName()+"}\" class=\"text required\" maxlength=\"10\" readonly >";
        str = str + "  </span>";
        str = str + "  </div>";
        str = str + "</c:if>";
        str = str + "<c:if test=\"${nodeColumsMap."+columnt.getFieldName()+".fcEdit!='1'}\">";
        str = str + "  <label>${model."+columnt.getFieldName()+"}</label>";
        str = str + "  <input type=\"hidden\" name=\""+columnt.getFieldName()+"\" value=\"${model."+columnt.getFieldName()+"}\">";
        str = str + "</c:if>";
        str = str + "</div>";
        columnt.setJspTagInfo(str);
        return columnt;
    }
    /**
     * 下拉列表
     */
    public Columnt selectParser(Columnt columnt, ConfFormTableColumn formColumn) {
        // 生成controller类中的Attribute
        String controllerInfo = "";
        // 取得表单对应表管理表Map
        controllerInfo = controllerInfo + "Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> "+columnt.getFieldName()+"FTCMap= CommonBusiness.getInstance().getFormTableColumnMap(\""+formColumn.getTableName()+"\", \""+formColumn.getFormName()+"\");";
        // 下拉列表
        controllerInfo = controllerInfo + "List<com.ibusiness.common.model.ConfSelectItem> "+columnt.getFieldName()+"Items = (List<com.ibusiness.common.model.ConfSelectItem>) CommonUtils.getListFromJson("+columnt.getFieldName()+"FTCMap.get(\""+columnt.getFieldDbName()+"\").getConfSelectInfo(), com.ibusiness.common.model.ConfSelectItem.class);";
        controllerInfo = controllerInfo + "model.addAttribute(\""+columnt.getFieldName()+"Items\", "+columnt.getFieldName()+"Items);";
        List<String> maList = columnt.getModelAttributeList();
        maList.add(controllerInfo);
        columnt.setModelAttributeList(maList);
        
        // ============================================================================
        // 生成JSP显示组件
        String jspTagInfo = "";
        jspTagInfo = jspTagInfo + "<div class=\"col-lg-3 col-md-3 col-sm-3\">";
        jspTagInfo = jspTagInfo + "  <c:if test=\"${nodeColumsMap."+columnt.getFieldName()+".fcEdit=='1'}\">";
        jspTagInfo = jspTagInfo + "    <select id=\"code-"+columnt.getFieldName()+"\" name=\""+columnt.getFieldName()+"\" class=\"form-control\" >";
        jspTagInfo = jspTagInfo + "          <option value=\"\" >请选择</option>";
        jspTagInfo = jspTagInfo + "        <c:forEach items=\"${"+columnt.getFieldName()+"Items}\" var=\"item\">";
        jspTagInfo = jspTagInfo + "          <option value=\"${item.key}\" ${item.key==model."+columnt.getFieldName()+"? 'selected':''} >${item.value}</option>";
        jspTagInfo = jspTagInfo + "        </c:forEach>";
        jspTagInfo = jspTagInfo + "    </select>";
        jspTagInfo = jspTagInfo + "  </c:if>";
        jspTagInfo = jspTagInfo + "  <c:if test=\"${nodeColumsMap."+columnt.getFieldName()+".fcEdit!='1'}\">";
        jspTagInfo = jspTagInfo + "    <label>${model."+columnt.getFieldName()+"}</label>";
        jspTagInfo = jspTagInfo + "    <input type=\"hidden\" name=\""+columnt.getFieldName()+"\" value=\"${model."+columnt.getFieldName()+"}\">";
        jspTagInfo = jspTagInfo + "  </c:if>";
        jspTagInfo = jspTagInfo + "</div>";
        columnt.setJspTagInfo(jspTagInfo);
        return columnt;
    }
    /**
     * 数据字典
     */
    public Columnt dataSelectParser(Columnt columnt, ConfFormTableColumn formColumn) {
        // 生成controller类中的Attribute
        String controllerInfo = "";
        // 取得表单对应表管理表Map
        controllerInfo = controllerInfo + "Map<String, com.ibusiness.component.form.entity.ConfFormTableColumn> "+columnt.getFieldName()+"FTCMap= CommonBusiness.getInstance().getFormTableColumnMap(\""+formColumn.getTableName()+"\", \""+formColumn.getFormName()+"\");";
        // 下拉列表
        controllerInfo = controllerInfo + "JSONObject "+columnt.getFieldName()+"JsonObj = JSONObject.fromObject("+columnt.getFieldName()+"FTCMap.get(\""+columnt.getFieldDbName()+"\").getConfSelectInfo());";
        controllerInfo = controllerInfo + "String "+columnt.getFieldName()+"Sql = "+columnt.getFieldName()+"JsonObj.getString(\"sql\");";
        controllerInfo = controllerInfo + "List<Map<String,Object>> "+columnt.getFieldName()+"List = com.ibusiness.core.spring.ApplicationContextHelper.getBean(com.ibusiness.common.service.CommonBaseService.class).getJdbcTemplate().queryForList("+columnt.getFieldName()+"Sql);";
        controllerInfo = controllerInfo + "List<ConfSelectItem> "+columnt.getFieldName()+"Items = new java.util.ArrayList<ConfSelectItem>();";
        controllerInfo = controllerInfo + "for (Map<String,Object> mapBean : "+columnt.getFieldName()+"List) {";
        controllerInfo = controllerInfo + "    ConfSelectItem confSelectItem = new ConfSelectItem();";
        controllerInfo = controllerInfo + "    confSelectItem.setKey(mapBean.get(\"vKey\").toString());";
        controllerInfo = controllerInfo + "    confSelectItem.setValue(mapBean.get(\"vValue\").toString());";
        controllerInfo = controllerInfo + "    "+columnt.getFieldName()+"Items.add(confSelectItem);";
        controllerInfo = controllerInfo + "}";
        //
        controllerInfo = controllerInfo + "model.addAttribute(\""+columnt.getFieldName()+"Items\", "+columnt.getFieldName()+"Items);";
        List<String> maList = columnt.getModelAttributeList();
        maList.add(controllerInfo);
        columnt.setModelAttributeList(maList);
        
        // ============================================================================
        // 生成JSP显示组件
        String jspTagInfo = "";
        jspTagInfo = jspTagInfo + "<div class=\"col-lg-3 col-md-3 col-sm-3\">";
        jspTagInfo = jspTagInfo + "  <c:if test=\"${nodeColumsMap."+columnt.getFieldName()+".fcEdit=='1'}\">";
        jspTagInfo = jspTagInfo + "    <select id=\"code-"+columnt.getFieldName()+"\" name=\""+columnt.getFieldName()+"\" class=\"form-control\" >";
        jspTagInfo = jspTagInfo + "          <option value=\"\" >请选择</option>";
        jspTagInfo = jspTagInfo + "        <c:forEach items=\"${"+columnt.getFieldName()+"Items}\" var=\"item\">";
        jspTagInfo = jspTagInfo + "          <option value=\"${item.key}\" ${item.key==model."+columnt.getFieldName()+"? 'selected':''} >${item.value}</option>";
        jspTagInfo = jspTagInfo + "        </c:forEach>";
        jspTagInfo = jspTagInfo + "    </select>";
        jspTagInfo = jspTagInfo + "  </c:if>";
        jspTagInfo = jspTagInfo + "  <c:if test=\"${nodeColumsMap."+columnt.getFieldName()+".fcEdit!='1'}\">";
        jspTagInfo = jspTagInfo + "    <label>${model."+columnt.getFieldName()+"}</label>";
        jspTagInfo = jspTagInfo + "    <input type=\"hidden\" name=\""+columnt.getFieldName()+"\" value=\"${model."+columnt.getFieldName()+"}\">";
        jspTagInfo = jspTagInfo + "  </c:if>";
        jspTagInfo = jspTagInfo + "</div>";
        columnt.setJspTagInfo(jspTagInfo);
        
        return columnt;
    }
    /**
     * 单选按钮
     */
    public Columnt radioBoxParser(Columnt columnt, ConfFormTableColumn formColumn) {
        String str = "";
        str = str + "<div class=\"col-lg-3 col-md-3 col-sm-3\">";
        str = str + "  <c:if test=\"${nodeColumsMap."+columnt.getFieldName()+".fcEdit=='1'}\">";
        str = str + "    <label class=\"radio\"><input type=\"radio\" name=\""+columnt.getFieldName()+"\" value=\"1\" ${1==model."+columnt.getFieldName()+"? 'checked':''} >是</label>";
        str = str + "    <label class=\"radio\"><input type=\"radio\" name=\""+columnt.getFieldName()+"\" value=\"2\" ${2==model."+columnt.getFieldName()+"? 'checked':''} >否</label>";
        str = str + "  </c:if>";
        str = str + "  <c:if test=\"${nodeColumsMap."+columnt.getFieldName()+".fcEdit!='1'}\">";
        str = str + "    <label class=\"radio\"><input type=\"radio\" name=\""+columnt.getFieldName()+"\" value=\"1\" ${1==model."+columnt.getFieldName()+"? 'checked':'' disabled=\"disabled\"} >是</label>";
        str = str + "    <label class=\"radio\"><input type=\"radio\" name=\""+columnt.getFieldName()+"\" value=\"2\" ${2==model."+columnt.getFieldName()+"? 'checked':'' disabled=\"disabled\"} >否</label>";
        str = str + "    <input type=\"hidden\" name=\""+columnt.getFieldName()+"\" value=\"${model."+columnt.getFieldName()+"}\">";
        str = str + "  </c:if>";
        str = str + " </div>";
        columnt.setJspTagInfo(str);
        return columnt;
    }
    /**
     * 复选按钮
     */
    public Columnt checkBoxParser(Columnt columnt, ConfFormTableColumn formColumn) {
        // TODO 未实现
        String str = "";
        str = str + "<div class=\"col-lg-3 col-md-3 col-sm-3\">";
        str = str + "  <input id=\"code-"+columnt.getFieldName()+"\" type=\"text\" name=\""+columnt.getFieldName()+"\" value=\"${model."+columnt.getFieldName()+"}\"  class=\"text\" >";
        str = str + "</div>";
        columnt.setJspTagInfo(str);
        return columnt;
    }
    /**
     * 选择带出
     */
    @SuppressWarnings("unchecked")
    public Columnt selectInputParser(Columnt columnt, ConfFormTableColumn formColumn) {
        String confSelectInfo = formColumn.getConfSelectInfo();
        JSONObject jsonObject= JSONObject.fromObject(confSelectInfo);
        // 根据表名取得className
        List<ConfTable> confTableList = CommonBusiness.getInstance().getTableDao().find("from ConfTable where tableName=?",jsonObject.getString("className"));
        String tableService = "";
        if (null != confTableList && confTableList.size() > 0) {
         // 实体类名(首字母大写)
            tableService = "com.codegenerate."+confTableList.get(0).getPackageName()+".service."
            + StringUtils.capitalize(confTableList.get(0).getTableName().replace("IB_", "").toLowerCase()) + "Service";
            
        }
        // ===============================================================================
        // java相关信息
        List<SelectInputBean> list = ((List<SelectInputBean>) CommonUtils.getListFromJson(jsonObject.get("jsplist").toString(), SelectInputBean.class));
        // 向controller中添加一个method
        String controllerInfo = "";
        // 取得表单对应表管理表Map
        controllerInfo = controllerInfo + "@ResponseBody @RequestMapping(\""+jsonObject.getString("queryName")+"-list\")";
        controllerInfo = controllerInfo + "public String ajaxroomlist(@ModelAttribute Page page, @RequestParam Map<String, Object> parameterMap) {";
        // 查询条件Filter过滤器
        controllerInfo = controllerInfo + "List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);";
        // 根据条件查询数据
        controllerInfo = controllerInfo + "page = com.ibusiness.core.spring.ApplicationContextHelper.getBean("+tableService+".class).pagedQuery(page, propertyFilters);";
        controllerInfo = controllerInfo + "List list = (java.util.ArrayList)page.getResult();";
        controllerInfo = controllerInfo + "return CommonUtils.getJsonFromList(list, null).toString();}";
        
        List<String> methodList = columnt.getMethodList();
        methodList.add(controllerInfo);
        columnt.setMethodList(methodList);
     
        // ============================================================================
        // JSP相关信息
        // 生成JSP显示组件
        String str = "";
        str = str + "<div class=\"col-lg-3 col-md-3 col-sm-3\">";
        str = str + "   <input id=\"code-"+columnt.getFieldName()+"\" type=\"text\" name=\""+columnt.getFieldName()+"\" value=\"${model."+columnt.getFieldName()+"}\" class=\"text "+("1".equals(formColumn.getFcMust())? "required" : "")+"\" >";
        str = str + "   <a href=\"#\" class=\"btn btn-primary btn-sm\" onclick=\"$('#"+columnt.getFieldName()+"SInputDiv').modal('show');\" >选择</a>";
        // ===================================
        str = str + "   <script type=\"text/javascript\">";
        str = str + "       function changeValue(";
        for (int i=0; i<list.size(); i++) {
            if (0 != i) {
                str = str + ",";
            }
            str = str + list.get(i).getInputValue();
        }
        str = str + "){";
        for (SelectInputBean inputBean : list) {
            str = str + "       $(\"#code-"+inputBean.getInputKey()+"\").val("+inputBean.getInputValue()+");";
        }
        str = str + "$('#"+columnt.getFieldName()+"SInputDiv').modal('hide'); ";
        str = str + "       }";
        // ajax call 调用后台进行查询
        str = str + "function searchOwnername(urlStr) { ";
        str = str + " $.ajax({ ";
        str = str + "    type: \"POST\", ";
        str = str + "    url: \"/\"+window.location.pathname.split(\"/\")[1]+\"/\"+urlStr +$(\"#code_table_"+jsonObject.getString("queryName")+"\").val(), ";
        str = str + "    dataType:\"json\", ";
        str = str + "    success: function(jsonData){ ";
        str = str + "      $(\"#"+columnt.getFieldName()+"Rowadd tr\").remove();  ";
        str = str + "      $.each(jsonData, function(i, item) { ";
        str = str + "          var newRow=' ";
        str = str + "                    <tr>";
        str = str + "                       <td><a href=\"#\" class=\"btn btn-primary btn-sm\" onClick=\"changeValue(\\''+";
        for (int i=0; i<list.size(); i++) {
            if (0 != i) {
                str = str + "+'\\',\\''+";
            }
            str = str + "item."+list.get(i).getInputValue();
        }
        str = str + "+'\\')\" >选择</a></td>";
        for (SelectInputBean inputBean : list) {
            str = str + "  <td>'+item."+inputBean.getInputValue()+"+'</td>";
        }
        str = str + "                    </tr>';";
        str = str + "          $('#"+columnt.getFieldName()+"Rowadd').append(newRow);  ";
        str = str + "       }); ";
        str = str + "    }, ";
        str = str + "    error: function (XMLHttpRequest, textStatus, errorThrown) { ";
        str = str + "        alert('请求数据出错了!'); ";
        str = str + "    }  ";
        str = str + " }); ";
        str = str + "} ";
        str = str + "   </script>";
        // ===================================
        str = str + "   <div id=\""+columnt.getFieldName()+"SInputDiv\" class=\"modal fade\" tabindex=\"-1\" style=\"top:20%;\" >";
        str = str + "     <div class=\"modal-dialog\">";
        str = str + "       <div class=\"modal-content\">";
        str = str + "         <div class=\"modal-header\">";
        str = str + "           <a href=\"#\" class=\"close btn btn-primary btn-sm\" onclick=\"$('#"+columnt.getFieldName()+"SInputDiv').modal('hide');\" ><span >&times;</span><span class=\"sr-only\">Close</span></a>";
        str = str + "           <h4 class=\"modal-title glyphicon glyphicon-paperclip\">选择带出</h4>";
        str = str + "           <div class=\"form-group\"> ";
        str = str + "             <label for=\"code_table_"+jsonObject.getString("queryName")+"\">"+jsonObject.getString("queryTitle")+":</label> ";
        str = str + "             <input type=\"text\" id=\"code_table_"+jsonObject.getString("queryName")+"\" name=\"filter_LIKES_"+jsonObject.getString("queryName")+"\" value=\"${param.filter_LIKES_"+jsonObject.getString("queryName")+"}\"> ";
        str = str + "             <a class=\"btn btn-primary btn-sm\" href=\"#\" onclick=\"searchOwnername('"+CommonUtils.toLowerCase(formColumn.getTableName()).replace("ib_", "")+"/"+jsonObject.getString("queryName")+"-list.do?filter_LIKES_"+jsonObject.getString("queryName")+"=')\"><span class=\"glyphicon glyphicon-search\"></span>查询</a> ";
        str = str + "           </div> ";
        str = str + "         </div>";
        str = str + "         <div class=\"modal-body\">";
        str = str + "           <div class=\"content\">";
        str = str + "               <table id=\"codeGrid\" class=\"table table-hover table-bordered\">";
        str = str + "                  <thead>";
        str = str + "                  <tr>";
        str = str + "                     <th width=\"80\">&nbsp;</th>";
        for (SelectInputBean inputBean : list) {
            str = str + "   <th class=\"sorting\">" + inputBean.getInputTitle() + "</th>";
        }
        str = str + "                  </tr>";
        str = str + "               </thead>";
        str = str + "               <tbody id='"+columnt.getFieldName()+"Rowadd'>";
        str = str + "                  <c:forEach items=\"${"+columnt.getFieldName()+"Page.result}\" var=\"item\">";
        str = str + "                    <tr>";
        str = str + "                       <td><a href=\"#\" class=\"btn btn-primary btn-sm\" onClick=\"changeValue(";
        for (int i=0; i<list.size(); i++) {
            if (0 != i) {
                str = str + ",";
            }
            str = str + "'${item."+list.get(i).getInputValue()+"}'";
        }
        str = str + ")\" >选择</a></td>";
        for (SelectInputBean inputBean : list) {
            str = str + "  <td>${item."+inputBean.getInputValue()+"}</td>";
        }
        str = str + "                    </tr>";
        str = str + "                 </c:forEach>";
        str = str + "               </tbody>";
        str = str + "           </table>";
        str = str + "         </div>";
        str = str + "       </div>";
        str = str + "       <div class=\"modal-footer\">";
        str = str + "         <a href=\"#\" class=\"btn btn-primary btn-sm\" onclick=\"$('#"+columnt.getFieldName()+"SInputDiv').modal('hide');\" >关闭</a>";
        str = str + "       </div>";
        str = str + "     </div>";
        str = str + "    </div>";
        str = str + "  </div>";
        
        str = str + "</div>";
        columnt.setJspTagInfo(str);
        return columnt;
    }
    /**
     * 附件上传组件
     */
    public Columnt uploadParser(Columnt columnt, ConfFormTableColumn formColumn) {
        String confSelectInfo = formColumn.getConfSelectInfo();
        JSONObject jsonObject= JSONObject.fromObject(confSelectInfo);
        String pathName = null == jsonObject.getString("pathName")? "ibusiness" : jsonObject.getString("pathName");
        // java相关信息 ======================================================================
        String methodStr = "";
        //上传下载功能
        methodStr = methodStr + " @ResponseBody @RequestMapping(\""+columnt.getFieldName()+"-upload\") ";
        methodStr = methodStr + " public String "+columnt.getFieldName()+"Upload(@org.springframework.beans.factory.annotation.Qualifier(\"attachment\") MultipartFile attachment, HttpServletResponse response) {";
        methodStr = methodStr + "   com.ibusiness.doc.store.StoreDTO storeDTO = null; ";
        methodStr = methodStr + "   if (null != attachment && attachment.getSize() > 0) {";
        methodStr = methodStr + "       try {";
        methodStr = methodStr + "           storeDTO = storeConnector.save(\""+pathName+"\", attachment.getInputStream(), attachment.getOriginalFilename());";
        methodStr = methodStr + "       } catch (Exception e) {e.printStackTrace();}}";
        methodStr = methodStr + "    return null == storeDTO ? \"\" : storeDTO.getKey();}";
        // 下载
        methodStr = methodStr + " @RequestMapping(\""+columnt.getFieldName()+"-download\")";
        methodStr = methodStr + " public void "+columnt.getFieldName()+"Download(@RequestParam(\"path\") String path, @RequestParam(\"filename\") String filename, HttpServletResponse response) throws Exception {";
        methodStr = methodStr + "    java.io.InputStream is = null;";
        methodStr = methodStr + "    try {";
        methodStr = methodStr + "        com.ibusiness.core.util.ServletUtils.setFileDownloadHeader(response, filename);";
        methodStr = methodStr + "        is = storeConnector.get(\""+pathName+"\", path).getInputStream();";
        methodStr = methodStr + "        com.ibusiness.core.util.IoUtils.copyStream(is, response.getOutputStream());";
        methodStr = methodStr + "    } finally { if (is != null) {is.close();}}";
        methodStr = methodStr + " }";
        List<String> methodList = columnt.getMethodList();
        methodList.add(methodStr);
        columnt.setMethodList(methodList);
        
        // JSP ======================================================================
        String str = "";
        str = str + "<div class=\"col-lg-4 col-md-4 col-sm-4\">";
        str = str + "  <input id=\"code_table_"+columnt.getFieldName()+"_text\" type=\"text\" value=\"\">";
        str = str + "  <a href=\"#\" class=\"btn btn-primary btn-sm\" onclick=\"$('#file_"+columnt.getFieldName()+"upload').click()\"><span class=\"glyphicon glyphicon-upload\"></span>上传</a>";
        str = str + "  <input id=\"code_table_"+columnt.getFieldName()+"\" type=\"hidden\" name=\""+columnt.getFieldName()+"\" value=\"${model."+columnt.getFieldName()+"}\">";
        str = str + "</div>";
        str = str + "<div class=\"col-lg-4 col-md-4 col-sm-4\">";
        str = str + "  <c:if test=\"${model."+columnt.getFieldName()+" != null && model."+columnt.getFieldName()+" !=''}\">";
        str = str + "    <a href=\""+columnt.getFieldName()+"-download.do?filename=${model."+columnt.getFieldName()+"}&path=${model."+columnt.getFieldName()+"}\">${model."+columnt.getFieldName()+"}</a>";
        str = str + "  </c:if>";
        str = str + "</div>";
        columnt.setJspTagInfo(str);
        
        // JSP FORM ======================================================================
        String jspFormStr = "<!-- 上传组件form提交 -->";
        jspFormStr = jspFormStr + "<form id=\""+columnt.getFieldName()+"UploadForm\" method=\"post\" action=\""+columnt.getFieldName()+"-upload.do\" class=\"form-horizontal\" enctype=\"multipart/form-data\">";
        jspFormStr = jspFormStr + "    <script type=\"text/javascript\">";
        jspFormStr = jspFormStr + "     function "+columnt.getFieldName()+"Upload() {";
        jspFormStr = jspFormStr + "       if( window.confirm(\"您确认要上传吗?\") == true ){";
        jspFormStr = jspFormStr + "         var $form=$('#"+columnt.getFieldName()+"UploadForm');";
        jspFormStr = jspFormStr + "         $form.ajaxSubmit({";
        jspFormStr = jspFormStr + "             url: $form.attr('action'),";
        jspFormStr = jspFormStr + "             type: 'POST',";
        jspFormStr = jspFormStr + "             dataType: 'text',";
        jspFormStr = jspFormStr + "             beforeSubmit: function () {},";
        jspFormStr = jspFormStr + "             success: function (data) {";
        jspFormStr = jspFormStr + "                 $('#code_table_"+columnt.getFieldName()+"').attr('value',data);";
        jspFormStr = jspFormStr + "                 $('#code_table_"+columnt.getFieldName()+"_text').attr('value',data);";
        jspFormStr = jspFormStr + "             },";
        jspFormStr = jspFormStr + "             error: function () {";
        jspFormStr = jspFormStr + "                 alert('请求数据出错了!');";
        jspFormStr = jspFormStr + "             }";
        jspFormStr = jspFormStr + "         });";
        jspFormStr = jspFormStr + "     }}";
        jspFormStr = jspFormStr + "  </script>";
        jspFormStr = jspFormStr + "    <input id=\"file_"+columnt.getFieldName()+"upload\" type=\"file\" name=\"attachment\" style=\"display:none;\" onChange=\""+columnt.getFieldName()+"Upload();\">";
        jspFormStr = jspFormStr + " </form>";
        List<String> jspFormList = columnt.getJspFormList();
        jspFormList.add(jspFormStr);
        columnt.setJspFormList(jspFormList);
        // 
        return columnt;
    }
    /**
     *  附件下载组件
     */
    public Columnt downloadParser(Columnt columnt, ConfFormTableColumn formColumn) {
        // java相关信息
        return columnt;
    }
    /**
     * 图片上传组件
     */
    public Columnt imgUploadParser(Columnt columnt, ConfFormTableColumn formColumn) {
        String confSelectInfo = formColumn.getConfSelectInfo();
        JSONObject jsonObject= JSONObject.fromObject(confSelectInfo);
        String pathName = (null == jsonObject.getString("pathName")? "ibusiness" : jsonObject.getString("pathName"));
        // java相关信息 ======================================================================
        String methodStr = "";
        // 图片上传功能
        methodStr = methodStr + " @ResponseBody @RequestMapping(\""+columnt.getFieldName()+"-upload\") ";
        methodStr = methodStr + " public String "+columnt.getFieldName()+"Upload(@org.springframework.beans.factory.annotation.Qualifier(\"attachment\") MultipartFile attachment, HttpServletResponse response) {";
        methodStr = methodStr + "   com.ibusiness.doc.store.StoreDTO storeDTO = null; ";
        methodStr = methodStr + "   if (null != attachment && attachment.getSize() > 0) {";
        methodStr = methodStr + "       try {";
        methodStr = methodStr + "           storeDTO = storeConnector.save(\""+pathName+"\", attachment.getInputStream(), attachment.getOriginalFilename());";
        methodStr = methodStr + "       } catch (Exception e) {e.printStackTrace();}}";
        methodStr = methodStr + "    return null == storeDTO ? \"\" : storeDTO.getKey();}";
        // 
        List<String> methodList = columnt.getMethodList();
        methodList.add(methodStr);
        columnt.setMethodList(methodList);
        
        // JSP ======================================================================
        String str = "";
        str = str + "<img id=\"code_img_"+columnt.getFieldName()+"\" height=\"200\" width=\"150\" src=\"${ctx}/ibresources/"+pathName+"/${model."+columnt.getFieldName()+"}\" />";
        str = str + "<div class=\"col-lg-4 col-md-4 col-sm-4\">";
        str = str + "  <a href=\"#\" class=\"btn btn-primary btn-sm\" onclick=\"$('#file_"+columnt.getFieldName()+"upload').click()\"><span class=\"glyphicon glyphicon-upload\"></span>上传</a>";
        str = str + "  <input id=\"code_table_"+columnt.getFieldName()+"\" type=\"hidden\" name=\""+columnt.getFieldName()+"\" value=\"${model."+columnt.getFieldName()+"}\">";
        str = str + "</div>";
        columnt.setJspTagInfo(str);
        
        // JSP FORM ======================================================================
        String jspFormStr = "<!-- 上传组件form提交 -->";
        jspFormStr = jspFormStr + "<form id=\""+columnt.getFieldName()+"UploadForm\" method=\"post\" action=\""+columnt.getFieldName()+"-upload.do\" class=\"form-horizontal\" enctype=\"multipart/form-data\">";
        jspFormStr = jspFormStr + "    <script type=\"text/javascript\">";
        jspFormStr = jspFormStr + "     function "+columnt.getFieldName()+"Upload() {";
        jspFormStr = jspFormStr + "       if( window.confirm(\"您确认要上传吗?\") == true ){";
        jspFormStr = jspFormStr + "         var $form=$('#"+columnt.getFieldName()+"UploadForm');";
        jspFormStr = jspFormStr + "         $form.ajaxSubmit({";
        jspFormStr = jspFormStr + "             url: $form.attr('action'),";
        jspFormStr = jspFormStr + "             type: 'POST',";
        jspFormStr = jspFormStr + "             dataType: 'text',";
        jspFormStr = jspFormStr + "             beforeSubmit: function () {},";
        jspFormStr = jspFormStr + "             success: function (data) {";
        jspFormStr = jspFormStr + "                 $('#code_table_"+columnt.getFieldName()+"').attr('value',data);";
        jspFormStr = jspFormStr + "                 $('#code_table_"+columnt.getFieldName()+"_text').attr('value',data);";
        jspFormStr = jspFormStr + "                 $('#code_img_"+columnt.getFieldName()+"').attr('src','${ctx}/ibresources/"+pathName+"/'+data);";
        jspFormStr = jspFormStr + "             },";
        jspFormStr = jspFormStr + "             error: function () {";
        jspFormStr = jspFormStr + "                 alert('请求数据出错了!');";
        jspFormStr = jspFormStr + "             }";
        jspFormStr = jspFormStr + "         });";
        jspFormStr = jspFormStr + "     }}";
        jspFormStr = jspFormStr + "  </script>";
        jspFormStr = jspFormStr + "    <input id=\"file_"+columnt.getFieldName()+"upload\" type=\"file\" name=\"attachment\" style=\"display:none;\" onChange=\""+columnt.getFieldName()+"Upload();\">";
        jspFormStr = jspFormStr + " </form>";
        List<String> jspFormList = columnt.getJspFormList();
        jspFormList.add(jspFormStr);
        columnt.setJspFormList(jspFormList);
        // 
        return columnt;
    }
    // =========================================================================
    // =========================================================================
    /**
     * 取得参数里面最小输入长度check
     */
    private String getMinlength(String confSelectInfo) {
        if (CommonUtils.isNull(confSelectInfo)) {
            return null;
        }
        JSONObject jsonObject= JSONObject.fromObject(confSelectInfo);
        String minlength = null;
        if (null != jsonObject && jsonObject.containsKey("minlength")) {
            minlength = jsonObject.getString("minlength");
        }
        return minlength;
    }

    /**
     * 取得参数里面最大输入长度check
     */
    private String getMaxlength(String confSelectInfo) {
        if (CommonUtils.isNull(confSelectInfo)) {
            return null;
        }
        JSONObject jsonObject= JSONObject.fromObject(confSelectInfo);
        String maxlength = null;
        if (null != jsonObject && jsonObject.containsKey("maxlength")) {
            maxlength = jsonObject.getString("maxlength");
        }
        return maxlength;
    }
}
