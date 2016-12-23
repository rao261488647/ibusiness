package com.report.controller;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.report.entity.ShowReportBean;

@Controller
public class ReportController {
	
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    //
    private SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 开始时间--静态
    private static String BEGINTIME = "beginTime";
    // 结束时间--静态
    private static String ENDTIME = "endTime";
    
    // 开始日期
    private String beginTime = null;
    // 开始时间
    private String beginTimeHour = null;
    // 结束日期
    private String endTime = null;
    // 结束时间
    private String endTimeHour = null;
    // 报表文件名
    private String reportName = null;
    // 报表查询头文件名
    private String reportTitleName = null;
    // 热源ID
    private String heatSourceId = null;
    // 热力站ID
    private String heatStationId = null;
    // 机组ID
    private String heatUnitId = null;
    // 报警信息说明
    private String warningContent = null;
    /**
     * 报表bean
     */
    private ShowReportBean reportBean = null;
    /**
     * 显示查询后的报表页面
     * @return
     */
    @RequestMapping("searchReport")
    public String searchReport(@ModelAttribute ShowReportBean reportBean, Model model) {
        // 如果为指定报表名字 跳转到错误提示页面。
        if (isNull(reportName)) {
            if (null == reportBean || isNull(reportBean.getReportName())) {
                return "failure";
            } else {
                reportName = reportBean.getReportName();
                reportTitleName = reportBean.getReportTitleName();
            }
        } else if (null == reportBean) {
            reportBean = new ShowReportBean();
        }
        // 创建ShowReportBean
        reportBean = createReportBean();

        model.addAttribute("reportBean", reportBean);
        return "success";
    }
    /**
     * 创建ShowReportBean
     * 
     * @return
     */
    private ShowReportBean createReportBean() {
        // 报表名
        reportBean.setReportName(reportName);
        // 报表查询头文件名(目前没用)
        reportBean.setReportTitleName(reportTitleName);
        // 开始日期时间
        if (!isNull(beginTime)) {
            reportBean.setBeginTime(beginTime);
        } else {
            reportBean.setBeginTime(ymdhms.format(Calendar.getInstance().getTime()).substring(0, 10));
        }
        if (!isNull(beginTimeHour)) {
            reportBean.setBeginTimeHour(beginTimeHour);
        } else {
            reportBean.setBeginTimeHour("00:00:00");
        }
        // 结束日期时间
        if (!isNull(endTime)) {
            reportBean.setEndTime(endTime);
        } else {
            reportBean.setEndTime(ymdhms.format(Calendar.getInstance().getTime()).substring(0, 10));
        }
        if (!isNull(endTimeHour)) {
            reportBean.setEndTimeHour(endTimeHour);
        } else {
            reportBean.setEndTimeHour("23:59:59");
        }
        reportBean.setHeatSourceId(heatSourceId);
        reportBean.setHeatStationId(heatStationId);
        reportBean.setHeatUnitId(heatUnitId);
        reportBean.setWarningContent(warningContent);
        
        // 设定参数
        reportBean.setParams(modefyRarams());
        return reportBean;
    }
    /**
     * 设定报表参数（以报表要求的式设定，把参数拼成name=value;name2=value2;.....的形式）
     * 
     * @param request
     * @return
     */
    private String modefyRarams() {
        String param = "";
        /* 如果初始没有时间条件，那就默认指定当天时间范围 */
        param = param + BEGINTIME + "=" + reportBean.getBeginTime() + " " + reportBean.getBeginTimeHour() +";";
        param = param + ENDTIME + "=" + reportBean.getEndTime() + " " + reportBean.getEndTimeHour() +";";
        // 热源编号
        if (!isNull(heatSourceId)) {
            param = param + "heatSourceId=" + heatSourceId + ";";
        }
        // 热力站编号
        if (!isNull(heatStationId)) {
            param = param + "heatStationId=" + heatStationId + ";";
        }
        // 机组编号
        if (!isNull(heatUnitId)) {
            param = param + "heatUnitId=" + heatUnitId + ";";
        }
        // 报警信息说明
        if (!isNull(warningContent)) {
            param = param + "warningContent=" + warningContent + ";";
        }
        return param;
    }
    // ===============================================================
    /**
     * 判断字符串是否为bull
     * @param str
     * @return true 为空 false 不为空
     */
    public static boolean isNull(String str) {
        return (null == str || "".equals(str) || "null".equals(str)) ? true : false;
    }
    // ===============================================================
    /**
     * 取得beginTime
     * @return the beginTime
     */
    public String getBeginTime() {
        return beginTime;
    }
    /**
     * 设置beginTime
     * @param beginTime the beginTime to set
     */
    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }
    /**
     * 取得beginTimeHour
     * @return the beginTimeHour
     */
    public String getBeginTimeHour() {
        return beginTimeHour;
    }
    /**
     * 设置beginTimeHour
     * @param beginTimeHour the beginTimeHour to set
     */
    public void setBeginTimeHour(String beginTimeHour) {
        this.beginTimeHour = beginTimeHour;
    }
    /**
     * 取得endTime
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }
    /**
     * 设置endTime
     * @param endTime the endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    /**
     * 取得endTimeHour
     * @return the endTimeHour
     */
    public String getEndTimeHour() {
        return endTimeHour;
    }
    /**
     * 设置endTimeHour
     * @param endTimeHour the endTimeHour to set
     */
    public void setEndTimeHour(String endTimeHour) {
        this.endTimeHour = endTimeHour;
    }
    /**
     * 取得reportName
     * @return the reportName
     */
    public String getReportName() {
        return reportName;
    }
    /**
     * 设置reportName
     * @param reportName the reportName to set
     */
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }
    /**
     * 取得reportTitleName
     * @return the reportTitleName
     */
    public String getReportTitleName() {
        return reportTitleName;
    }
    /**
     * 设置reportTitleName
     * @param reportTitleName the reportTitleName to set
     */
    public void setReportTitleName(String reportTitleName) {
        this.reportTitleName = reportTitleName;
    }
    /**
     * 取得heatStationId
     * @return the heatStationId
     */
    public String getHeatStationId() {
        return heatStationId;
    }
    /**
     * 设置heatStationId
     * @param heatStationId the heatStationId to set
     */
    public void setHeatStationId(String heatStationId) {
        this.heatStationId = heatStationId;
    }
    /**
     * 取得heatUnitId
     * @return the heatUnitId
     */
    public String getHeatUnitId() {
        return heatUnitId;
    }
    /**
     * 设置heatUnitId
     * @param heatUnitId the heatUnitId to set
     */
    public void setHeatUnitId(String heatUnitId) {
        this.heatUnitId = heatUnitId;
    }
    /**
     * 取得reportBean
     * @return the reportBean
     */
    public ShowReportBean getReportBean() {
        return reportBean;
    }
    /**
     * 设置reportBean
     * @param reportBean the reportBean to set
     */
    public void setReportBean(ShowReportBean reportBean) {
        this.reportBean = reportBean;
    }
    /**
     * 取得warningContent
     * @return the warningContent
     */
    public String getWarningContent() {
        return warningContent;
    }
    /**
     * 设置warningContent
     * @param warningContent the warningContent to set
     */
    public void setWarningContent(String warningContent) {
        this.warningContent = warningContent;
    }
    /**
     * 取得heatSourceId
     * @return the heatSourceId
     */
    public String getHeatSourceId() {
        return heatSourceId;
    }
    /**
     * 设置heatSourceId
     * @param heatSourceId the heatSourceId to set
     */
    public void setHeatSourceId(String heatSourceId) {
        this.heatSourceId = heatSourceId;
    }
}
