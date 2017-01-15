package com.ibusiness.common.export;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi2.hssf.usermodel.HSSFCell;
import org.apache.poi2.hssf.usermodel.HSSFCellStyle;
import org.apache.poi2.hssf.usermodel.HSSFRichTextString;
import org.apache.poi2.hssf.usermodel.HSSFRow;
import org.apache.poi2.hssf.usermodel.HSSFSheet;
import org.apache.poi2.hssf.usermodel.HSSFWorkbook;
import org.apache.poi2.hssf.util.HSSFColor;
import org.apache.poi2.poifs.filesystem.POIFSFileSystem;

import com.ibusiness.common.model.CustomerCount;
import com.ibusiness.common.service.CommonBaseService;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.core.spring.ApplicationContextHelper;
import com.ibusiness.core.util.ReflectUtils;
import com.ibusiness.core.util.StringUtils;
import com.report.utils.DateUtil;

/**
 * excel 导入/导出 共用类
 * 
 * @author JiangBo
 * 
 */
public class CountExcelCommon {
    /**
     * Excel导出
     * 
     * @param response
     * @param tableModel
     * @throws IOException
     */
//    public void exportExcel(HttpServletResponse response, TableModel tableModel) throws IOException {
//        // 设置让浏览器弹出下载对话框的Header
//        setFileDownloadHeader(response, tableModel.getExcelName() + ".xls");
//        // 利用输出输入流导出文件
//        ServletOutputStream sos = response.getOutputStream();
//        // 生成Excel文件到sos流中
//        createExcelFile(sos, tableModel);
//    }
    
    /**
     * Excel导出
     * 
     * @param response
     * @param tableModel
     * @throws IOException
     */
    public void exportExcel1(HttpServletResponse response, CustomerCount customerCount1) throws IOException {
        // 设置让浏览器弹出下载对话框的Header
        setFileDownloadHeader(response, "来店客户统计分析_"+System.currentTimeMillis() + ".xls");
        // 利用输出输入流导出文件
        ServletOutputStream sos = response.getOutputStream();
        // 生成Excel文件到sos流中
        createExcelFile1(sos, customerCount1);
    }
    
    /**
     * Excel导出
     * 
     * @param response
     * @param tableModel
     * @throws IOException
     */
    public void exportExcel2(HttpServletResponse response, CustomerCount customerCount1) throws IOException {
        // 设置让浏览器弹出下载对话框的Header
        setFileDownloadHeader(response, "在职司机统计分析_"+System.currentTimeMillis() + ".xls");
        // 利用输出输入流导出文件
        ServletOutputStream sos = response.getOutputStream();
        // 生成Excel文件到sos流中
        createExcelFile2(sos, customerCount1);
    }
    
    /**
     * Excel导出
     * 
     * @param response
     * @param tableModel
     * @throws IOException
     */
    public void exportExcel3(HttpServletResponse response, List<CustomerCount> customerCountList, CustomerCount customerCount) throws IOException {
        // 设置让浏览器弹出下载对话框的Header
        setFileDownloadHeader(response,"车型库存盘点分析_"+ System.currentTimeMillis() + ".xls");
        // 利用输出输入流导出文件
        ServletOutputStream sos = response.getOutputStream();
        // 生成Excel文件到sos流中
        createExcelFile3(sos, customerCountList,customerCount);
    }
    
    /**
     * Excel导出
     * 
     * @param response
     * @param tableModel
     * @throws IOException
     */
    public void exportExcel4(HttpServletResponse response, List<CustomerCount> customerCountList, CustomerCount customerCount) throws IOException {
        // 设置让浏览器弹出下载对话框的Header
        setFileDownloadHeader(response, "车辆出入库情况统计表_"+System.currentTimeMillis() + ".xls");
        // 利用输出输入流导出文件
        ServletOutputStream sos = response.getOutputStream();
        // 生成Excel文件到sos流中
        createExcelFile4(sos, customerCountList,customerCount);
    }
    
    /**
     * Excel导出
     * 
     * @param response
     * @param tableModel
     * @throws IOException
     */
    public void exportExcel5(HttpServletResponse response, List<CustomerCount> customerCountList, CustomerCount customerCount) throws IOException {
        // 设置让浏览器弹出下载对话框的Header
        setFileDownloadHeader(response, "维修保养统计表_"+System.currentTimeMillis() + ".xls");
        // 利用输出输入流导出文件
        ServletOutputStream sos = response.getOutputStream();
        // 生成Excel文件到sos流中
        createExcelFile5(sos, customerCountList,customerCount);
    }
    
    /**
     * Excel导出
     * 
     * @param response
     * @param tableModel
     * @throws IOException
     */
    public void exportExcel6(HttpServletResponse response, Map<String,Object> map1,List<Map<String,Object>> list1,Map<String,Object> map2) throws IOException {
        // 设置让浏览器弹出下载对话框的Header
        setFileDownloadHeader(response, "违章统计表_"+System.currentTimeMillis() + ".xls");
        // 利用输出输入流导出文件
        ServletOutputStream sos = response.getOutputStream();
        // 生成Excel文件到sos流中
        createExcelFile6(sos, map1,list1,map2);
    }
    
    /**
     * 生成Excel文件到sos流中
     * @param sos
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public void createExcelFile1(ServletOutputStream sos, CustomerCount customerCount) {
        
        // 创建Excel文件  
        try {  
            // 创建新的Excel 工作簿  
            HSSFWorkbook workbook = new HSSFWorkbook();  
            // 在Excel工作簿中建一工作表，其名为缺省值  
            HSSFSheet sheet = workbook.createSheet();  

            //设置标题背景色
            HSSFCellStyle style = workbook.createCellStyle(); 
            style.setFillForegroundColor( HSSFColor.LIME.index ); 
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
            style.setAlignment(CellStyle.ALIGN_CENTER);//水平居中  
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
            
            //设置单元格居中 
            HSSFCellStyle style2 = workbook.createCellStyle(); 
            style2.setAlignment(CellStyle.ALIGN_CENTER);//水平居中  
            style2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
            
            // 创建第一行  
            HSSFRow row1 = sheet.createRow(0);  
            row1.setHeight( (short) 400 );
            HSSFCell cell1 = row1.createCell(1);
            cell1.setCellStyle(style);
            cell1.setCellValue( new HSSFRichTextString("今日来店司机数"));
            HSSFCell cell2 = row1.createCell(2);
            cell2.setCellStyle(style);
            cell2.setCellValue( new HSSFRichTextString("本周来店司机数"));
            
            HSSFCell cell3 = row1.createCell(3);
            cell3.setCellStyle(style);
            cell3.setCellValue( new HSSFRichTextString("当月来店司机数"));
            
            HSSFCell cell4 = row1.createCell(4);
            cell4.setCellStyle(style);
            cell4.setCellValue( new HSSFRichTextString("累计来店司机数"));
            
            // 创建第2行  
            HSSFRow row2 = sheet.createRow(1);  
            row2.setHeight( (short) 400 );
            HSSFCell cell_1 = row2.createCell(1);
            cell_1.setCellValue( new HSSFRichTextString(customerCount.getCount1()));
            HSSFCell cell_2 = row2.createCell(2);
            cell_2.setCellValue( new HSSFRichTextString(customerCount.getCount4()));
            
            HSSFCell cell_3 = row2.createCell(3);
            cell_3.setCellValue( new HSSFRichTextString(customerCount.getCount7()));
            
            HSSFCell cell_4 = row2.createCell(4);
            cell_4.setCellValue( new HSSFRichTextString(customerCount.getCount10()));
            
            
            // 创建第3行  
            HSSFRow row3 = sheet.createRow(2);  
            row3.setHeight( (short) 400 );
            HSSFCell cell__1 = row3.createCell(1);
            cell__1.setCellValue( new HSSFRichTextString("今日签约司机数"));
            HSSFCell cell__2 = row3.createCell(2);
            cell__2.setCellValue( new HSSFRichTextString("本周签约司机数"));
            
            HSSFCell cell__3 = row3.createCell(3);
            cell__3.setCellValue( new HSSFRichTextString("当月签约司机数"));
            
            HSSFCell cell__4 = row3.createCell(4);
            cell__4.setCellValue( new HSSFRichTextString("累计签约司机数"));
            
            
         // 创建第4行  
            HSSFRow row4 = sheet.createRow(3);  
            row4.setHeight( (short) 400 );
            HSSFCell cell___1 = row4.createCell(1);
            cell___1.setCellValue( new HSSFRichTextString(customerCount.getCount2()));
            HSSFCell cell___2 = row4.createCell(2);
            cell___2.setCellValue( new HSSFRichTextString(customerCount.getCount5()));
            
            HSSFCell cell___3 = row4.createCell(3);
            cell___3.setCellValue( new HSSFRichTextString(customerCount.getCount8()));
            
            HSSFCell cell___4 = row4.createCell(4);
            cell___4.setCellValue( new HSSFRichTextString(customerCount.getCount11()));
            
            
            // 创建第5行  
            HSSFRow row5 = sheet.createRow(4);  
            row5.setHeight( (short) 400 );
            HSSFCell cell____1 = row5.createCell(1);
            cell____1.setCellStyle(style);
            cell____1.setCellValue( new HSSFRichTextString("签约率"));
            HSSFCell cell____2 = row5.createCell(2);
            cell____2.setCellStyle(style);
            cell____2.setCellValue( new HSSFRichTextString("签约率"));
            
            HSSFCell cell____3 = row5.createCell(3);
            cell____3.setCellStyle(style);
            cell____3.setCellValue( new HSSFRichTextString("签约率"));
            
            HSSFCell cell____4 = row5.createCell(4);
            cell____4.setCellStyle(style);
            cell____4.setCellValue( new HSSFRichTextString("签约率"));
            
            
            // 创建第6行  
            HSSFRow row6 = sheet.createRow(5);  
            row6.setHeight( (short) 400 );
            HSSFCell cell_____1 = row6.createCell(1);
            cell_____1.setCellValue( new HSSFRichTextString(customerCount.getCount3()+"%"));
            HSSFCell cell_____2 = row6.createCell(2);
            cell_____2.setCellValue( new HSSFRichTextString(customerCount.getCount6()+"%"));
            
            HSSFCell cell_____3 = row6.createCell(3);
            cell_____3.setCellValue( new HSSFRichTextString(customerCount.getCount9()+"%"));
            
            HSSFCell cell_____4 = row6.createCell(4);
            cell_____4.setCellValue( new HSSFRichTextString(customerCount.getCount12()+"%"));
            
            // 把相应的Excel 工作簿  
            workbook.write(sos);  
  
            sos.flush();  
            // 操作结束，关闭文件  
            sos.close();  
        } catch (Exception e) {  
            System.out.println("Exception：" + e);  
        }  
    }
    
    /**
     * 生成Excel文件到sos流中
     * @param sos
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public void createExcelFile2(ServletOutputStream sos, CustomerCount customerCount) {
        
        // 创建Excel文件  
        try {  
            // 创建新的Excel 工作簿  
            HSSFWorkbook workbook = new HSSFWorkbook();  
            // 在Excel工作簿中建一工作表，其名为缺省值  
            HSSFSheet sheet = workbook.createSheet();  

            //设置标题背景色
            HSSFCellStyle style = workbook.createCellStyle(); 
            style.setFillForegroundColor( HSSFColor.LIME.index ); 
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
            style.setAlignment(CellStyle.ALIGN_CENTER);//水平居中  
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
            
            //设置单元格居中 
            HSSFCellStyle style2 = workbook.createCellStyle(); 
            style2.setAlignment(CellStyle.ALIGN_CENTER);//水平居中  
            style2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
            
            // 创建第一行  
            HSSFRow row1 = sheet.createRow(0);  
            row1.setHeight( (short) 400 );
            HSSFCell cell1 = row1.createCell(1);
            cell1.setCellStyle(style);
            cell1.setCellValue( new HSSFRichTextString("当日在职司机数"));
            HSSFCell cell2 = row1.createCell(2);
            cell2.setCellStyle(style);
            cell2.setCellValue( new HSSFRichTextString("当日在职自营司机总数"));
            
            HSSFCell cell3 = row1.createCell(3);
            cell3.setCellStyle(style);
            cell3.setCellValue( new HSSFRichTextString("当日以租代购司机数"));
            
            
            // 创建第2行  
            HSSFRow row2 = sheet.createRow(1);  
            row2.setHeight( (short) 400 );
            HSSFCell cell_1 = row2.createCell(1);
            cell_1.setCellValue( new HSSFRichTextString(customerCount.getCount1()));
            HSSFCell cell_2 = row2.createCell(2);
            cell_2.setCellValue( new HSSFRichTextString(customerCount.getCount2()));
            
            HSSFCell cell_3 = row2.createCell(3);
            cell_3.setCellValue( new HSSFRichTextString(customerCount.getCount3()));
            
            // 创建第3行  
            HSSFRow row3 = sheet.createRow(2);  
            row3.setHeight( (short) 400 );
            HSSFCell cell__1 = row3.createCell(1);
            cell__1.setCellStyle(style);
            cell__1.setCellValue( new HSSFRichTextString("当日司机提车数"));
            HSSFCell cell__2 = row3.createCell(2);
            cell__2.setCellStyle(style);
            cell__2.setCellValue( new HSSFRichTextString("当周司机提车数"));
            
            HSSFCell cell__3 = row3.createCell(3);
            cell__3.setCellStyle(style);
            cell__3.setCellValue( new HSSFRichTextString("当月司机提车数"));
            
            HSSFCell cell__4 = row3.createCell(4);
            cell__4.setCellStyle(style);
            cell__4.setCellValue( new HSSFRichTextString("累计司机提车数"));
            
            
         // 创建第4行  
            HSSFRow row4 = sheet.createRow(3);  
            row4.setHeight( (short) 400 );
            HSSFCell cell___1 = row4.createCell(1);
            cell___1.setCellValue( new HSSFRichTextString(customerCount.getCount4()));
            HSSFCell cell___2 = row4.createCell(2);
            cell___2.setCellValue( new HSSFRichTextString(customerCount.getCount5()));
            
            HSSFCell cell___3 = row4.createCell(3);
            cell___3.setCellValue( new HSSFRichTextString(customerCount.getCount6()));
            
            HSSFCell cell___4 = row4.createCell(4);
            cell___4.setCellValue( new HSSFRichTextString(customerCount.getCount7()));
            
            
            // 创建第5行  
            HSSFRow row5 = sheet.createRow(4);  
            row5.setHeight( (short) 400 );
            HSSFCell cell____1 = row5.createCell(1);
            cell____1.setCellStyle(style);
            cell____1.setCellValue( new HSSFRichTextString("当日离职司机数"));
            HSSFCell cell____2 = row5.createCell(2);
            cell____2.setCellStyle(style);
            cell____2.setCellValue( new HSSFRichTextString("当周离职司机数"));
            
            HSSFCell cell____3 = row5.createCell(3);
            cell____3.setCellStyle(style);
            cell____3.setCellValue( new HSSFRichTextString("当月离职司机数"));
            
            HSSFCell cell____4 = row5.createCell(4);
            cell____4.setCellStyle(style);
            cell____4.setCellValue( new HSSFRichTextString("累计离职司机数"));
            
            
            // 创建第6行  
            HSSFRow row6 = sheet.createRow(5);  
            row6.setHeight( (short) 400 );
            HSSFCell cell_____1 = row6.createCell(1);
            cell_____1.setCellValue( new HSSFRichTextString(customerCount.getCount8()));
            HSSFCell cell_____2 = row6.createCell(2);
            cell_____2.setCellValue( new HSSFRichTextString(customerCount.getCount9()));
            
            HSSFCell cell_____3 = row6.createCell(3);
            cell_____3.setCellValue( new HSSFRichTextString(customerCount.getCount10()));
            
            HSSFCell cell_____4 = row6.createCell(4);
            cell_____4.setCellValue( new HSSFRichTextString(customerCount.getCount11()));
            
            // 把相应的Excel 工作簿  
            workbook.write(sos);  
  
            sos.flush();  
            // 操作结束，关闭文件  
            sos.close();  
        } catch (Exception e) {  
            System.out.println("Exception：" + e);  
        }  
    }
    
    
    /**
     * 生成Excel文件到sos流中
     * @param sos
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public void createExcelFile3(ServletOutputStream sos,List<CustomerCount> customerCountList, CustomerCount customerCount) {
        
        // 创建Excel文件  
        try {  
            // 创建新的Excel 工作簿  
            HSSFWorkbook workbook = new HSSFWorkbook();  
            // 在Excel工作簿中建一工作表，其名为缺省值  
            HSSFSheet sheet = workbook.createSheet();  

            //设置标题背景色
            HSSFCellStyle style = workbook.createCellStyle(); 
            style.setFillForegroundColor( HSSFColor.LIME.index ); 
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
            style.setAlignment(CellStyle.ALIGN_CENTER);//水平居中  
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
            
            //设置单元格居中 
            HSSFCellStyle style2 = workbook.createCellStyle(); 
            style2.setAlignment(CellStyle.ALIGN_CENTER);//水平居中  
            style2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
            
            // 创建第一行  
            HSSFRow row1 = sheet.createRow(0);  
            row1.setHeight( (short) 400 );
            HSSFCell cell1 = row1.createCell(1);
            cell1.setCellStyle(style);
            cell1.setCellValue( new HSSFRichTextString("车型"));
            HSSFCell cell2 = row1.createCell(2);
            cell2.setCellStyle(style);
            cell2.setCellValue( new HSSFRichTextString("数量"));
            
            HSSFCell cell3 = row1.createCell(3);
            cell3.setCellStyle(style);
            cell3.setCellValue( new HSSFRichTextString("已出库"));
            
            HSSFCell cell4 = row1.createCell(4);
            cell4.setCellStyle(style);
            cell4.setCellValue( new HSSFRichTextString("待出库"));
            HSSFCell cell5 = row1.createCell(5);
            cell5.setCellStyle(style);
            cell5.setCellValue( new HSSFRichTextString("维修厂"));
            HSSFCell cell6 = row1.createCell(6);
            cell6.setCellStyle(style);
            cell6.setCellValue( new HSSFRichTextString("外借"));
            HSSFCell cell7 = row1.createCell(7);
            cell7.setCellStyle(style);
            cell7.setCellValue( new HSSFRichTextString("出车率"));
            
            for (int i=0;i<customerCountList.size();i++) {
                // 建立一行
                HSSFRow row2 = sheet.createRow(i+1);
                CustomerCount customerCountTmp = customerCountList.get(i);
                HSSFCell cellValue1 = row2.createCell(1);
                cellValue1.setCellValue(new HSSFRichTextString(customerCountTmp.getName()));
                HSSFCell cellValue2 = row2.createCell(2);
                cellValue2.setCellValue(new HSSFRichTextString(customerCountTmp.getCount1()));
                HSSFCell cellValue3 = row2.createCell(3);
                cellValue3.setCellValue(new HSSFRichTextString(customerCountTmp.getCount2()));
                HSSFCell cellValue4 = row2.createCell(4);
                cellValue4.setCellValue(new HSSFRichTextString(customerCountTmp.getCount3()));
                HSSFCell cellValue5 = row2.createCell(5);
                cellValue5.setCellValue(new HSSFRichTextString(customerCountTmp.getCount4()));
                HSSFCell cellValue6 = row2.createCell(6);
                cellValue6.setCellValue(new HSSFRichTextString(customerCountTmp.getCount5()));
                
                HSSFCell cellValue7 = row2.createCell(7);
                cellValue7.setCellValue(new HSSFRichTextString(DateUtil.numFormat(customerCountTmp.getCount1(),Integer.valueOf(customerCountTmp.getCount2())*100) +"%"));
            }
            
            
            // 创建第2行  
            HSSFRow row2 = sheet.createRow(customerCountList.size()+1);  
            row2.setHeight( (short) 400 );
            HSSFCell cell_1 = row2.createCell(1);
            cell_1.setCellValue( new HSSFRichTextString("合计"));
            HSSFCell cell_2 = row2.createCell(2);
            cell_2.setCellValue( new HSSFRichTextString(customerCount.getCount1()));
            
            HSSFCell cell_3 = row2.createCell(3);
            cell_3.setCellValue( new HSSFRichTextString(customerCount.getCount2()));
            
            HSSFCell cell_4 = row2.createCell(4);
            cell_4.setCellValue( new HSSFRichTextString(customerCount.getCount3()));
            
            HSSFCell cell_5 = row2.createCell(5);
            cell_5.setCellValue( new HSSFRichTextString(customerCount.getCount4()));
            
            HSSFCell cell_6 = row2.createCell(6);
            cell_6.setCellValue( new HSSFRichTextString(customerCount.getCount5()));
            
            HSSFCell cell_7 = row2.createCell(7);
            cell_7.setCellValue( new HSSFRichTextString(customerCount.getCount6() + "%"));
            
            // 把相应的Excel 工作簿  
            workbook.write(sos);  
  
            sos.flush();  
            // 操作结束，关闭文件  
            sos.close();  
        } catch (Exception e) {  
            System.out.println("Exception：" + e);  
        }  
    }
    
    /**
     * 生成Excel文件到sos流中
     * @param sos
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public void createExcelFile4(ServletOutputStream sos,List<CustomerCount> customerCountList, CustomerCount customerCount) {
        
        // 创建Excel文件  
        try {  
            // 创建新的Excel 工作簿  
            HSSFWorkbook workbook = new HSSFWorkbook();  
            // 在Excel工作簿中建一工作表，其名为缺省值  
            HSSFSheet sheet = workbook.createSheet();  

            //设置标题背景色
            HSSFCellStyle style = workbook.createCellStyle(); 
            style.setFillForegroundColor( HSSFColor.LIME.index ); 
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
            style.setAlignment(CellStyle.ALIGN_CENTER);//水平居中  
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
            
            //设置单元格居中 
            HSSFCellStyle style2 = workbook.createCellStyle(); 
            style2.setAlignment(CellStyle.ALIGN_CENTER);//水平居中  
            style2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
            
            // 创建第一行  
            HSSFRow row1 = sheet.createRow(0);  
            row1.setHeight( (short) 400 );
            HSSFCell cell1 = row1.createCell(1);
            cell1.setCellStyle(style);
            cell1.setCellValue( new HSSFRichTextString("车型"));
            HSSFCell cell2 = row1.createCell(2);
            cell2.setCellStyle(style);
            cell2.setCellValue( new HSSFRichTextString("上周库存"));
            
            HSSFCell cell3 = row1.createCell(3);
            cell3.setCellStyle(style);
            cell3.setCellValue( new HSSFRichTextString("退车入库"));
            
            HSSFCell cell4 = row1.createCell(4);
            cell4.setCellStyle(style);
            cell4.setCellValue( new HSSFRichTextString("新车入库"));
            HSSFCell cell5 = row1.createCell(5);
            cell5.setCellStyle(style);
            cell5.setCellValue( new HSSFRichTextString("车辆出库"));
            HSSFCell cell6 = row1.createCell(6);
            cell6.setCellStyle(style);
            cell6.setCellValue( new HSSFRichTextString("现库存"));
            
            for (int i=0;i<customerCountList.size();i++) {
                // 建立一行
                HSSFRow row2 = sheet.createRow(i+1);
                CustomerCount customerCountTmp = customerCountList.get(i);
                HSSFCell cellValue1 = row2.createCell(1);
                cellValue1.setCellValue(new HSSFRichTextString(customerCountTmp.getName()));
                HSSFCell cellValue2 = row2.createCell(2);
                cellValue2.setCellValue(new HSSFRichTextString(customerCountTmp.getCount1()));
                HSSFCell cellValue3 = row2.createCell(3);
                cellValue3.setCellValue(new HSSFRichTextString(customerCountTmp.getCount2()));
                HSSFCell cellValue4 = row2.createCell(4);
                cellValue4.setCellValue(new HSSFRichTextString(customerCountTmp.getCount3()));
                HSSFCell cellValue5 = row2.createCell(5);
                cellValue5.setCellValue(new HSSFRichTextString(customerCountTmp.getCount4()));
                HSSFCell cellValue6 = row2.createCell(6);
                cellValue6.setCellValue(new HSSFRichTextString(customerCountTmp.getCount5()));
            }
            
            
            // 创建第2行  
            HSSFRow row2 = sheet.createRow(customerCountList.size()+1);  
            row2.setHeight( (short) 400 );
            HSSFCell cell_1 = row2.createCell(1);
            cell_1.setCellValue( new HSSFRichTextString("合计"));
            HSSFCell cell_2 = row2.createCell(2);
            cell_2.setCellValue( new HSSFRichTextString(customerCount.getCount1()));
            
            HSSFCell cell_3 = row2.createCell(3);
            cell_3.setCellValue( new HSSFRichTextString(customerCount.getCount2()));
            
            HSSFCell cell_4 = row2.createCell(4);
            cell_4.setCellValue( new HSSFRichTextString(customerCount.getCount3()));
            
            HSSFCell cell_5 = row2.createCell(5);
            cell_5.setCellValue( new HSSFRichTextString(customerCount.getCount4()));
            
            HSSFCell cell_6 = row2.createCell(6);
            cell_6.setCellValue( new HSSFRichTextString(customerCount.getCount5()));
            
            // 把相应的Excel 工作簿  
            workbook.write(sos);  
  
            sos.flush();  
            // 操作结束，关闭文件  
            sos.close();  
        } catch (Exception e) {  
            System.out.println("Exception：" + e);  
        }  
    }
    
    /**
     * 生成Excel文件到sos流中
     * @param sos
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public void createExcelFile5(ServletOutputStream sos,List<CustomerCount> customerCountList, CustomerCount customerCount) {
        
        // 创建Excel文件  
        try {  
            // 创建新的Excel 工作簿  
            HSSFWorkbook workbook = new HSSFWorkbook();  
            // 在Excel工作簿中建一工作表，其名为缺省值  
            HSSFSheet sheet = workbook.createSheet();  

            //设置标题背景色
            HSSFCellStyle style = workbook.createCellStyle(); 
            style.setFillForegroundColor( HSSFColor.LIME.index ); 
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
            style.setAlignment(CellStyle.ALIGN_CENTER);//水平居中  
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
            
            //设置单元格居中 
            HSSFCellStyle style2 = workbook.createCellStyle(); 
            style2.setAlignment(CellStyle.ALIGN_CENTER);//水平居中  
            style2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
            
            // 创建第一行  
            HSSFRow row1 = sheet.createRow(0);  
            row1.setHeight( (short) 400 );
            HSSFCell cell1 = row1.createCell(1);
            cell1.setCellStyle(style);
            cell1.setCellValue( new HSSFRichTextString("维修厂"));
            HSSFCell cell2 = row1.createCell(2);
            cell2.setCellStyle(style);
            cell2.setCellValue( new HSSFRichTextString("保养"));
            
            HSSFCell cell3 = row1.createCell(3);
            cell3.setCellStyle(style);
            cell3.setCellValue( new HSSFRichTextString("维修进厂"));
            
            HSSFCell cell4 = row1.createCell(4);
            cell4.setCellStyle(style);
            cell4.setCellValue( new HSSFRichTextString("维修出厂"));
            
            for (int i=0;i<customerCountList.size();i++) {
                // 建立一行
                HSSFRow row2 = sheet.createRow(i+1);
                CustomerCount customerCountTmp = customerCountList.get(i);
                HSSFCell cellValue1 = row2.createCell(1);
                cellValue1.setCellValue(new HSSFRichTextString(customerCountTmp.getName()));
                HSSFCell cellValue2 = row2.createCell(2);
                cellValue2.setCellValue(new HSSFRichTextString(customerCountTmp.getCount1()));
                HSSFCell cellValue3 = row2.createCell(3);
                cellValue3.setCellValue(new HSSFRichTextString(customerCountTmp.getCount2()));
                HSSFCell cellValue4 = row2.createCell(4);
                cellValue4.setCellValue(new HSSFRichTextString(customerCountTmp.getCount3()));
            }
            
            
            // 创建第2行  
            HSSFRow row2 = sheet.createRow(customerCountList.size()+1);  
            row2.setHeight( (short) 400 );
            HSSFCell cell_1 = row2.createCell(1);
            cell_1.setCellValue( new HSSFRichTextString("合计"));
            HSSFCell cell_2 = row2.createCell(2);
            cell_2.setCellValue( new HSSFRichTextString(customerCount.getCount1()));
            
            HSSFCell cell_3 = row2.createCell(3);
            cell_3.setCellValue( new HSSFRichTextString(customerCount.getCount2()));
            
            HSSFCell cell_4 = row2.createCell(4);
            cell_4.setCellValue( new HSSFRichTextString(customerCount.getCount3()));
            
            // 把相应的Excel 工作簿  
            workbook.write(sos);  
  
            sos.flush();  
            // 操作结束，关闭文件  
            sos.close();  
        } catch (Exception e) {  
            System.out.println("Exception：" + e);  
        }  
    }
    
    /**
     * 生成Excel文件到sos流中
     * @param sos
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public void createExcelFile6(ServletOutputStream sos,Map<String,Object> map1,List<Map<String,Object>> list1, Map<String,Object> map2) {
        
        // 创建Excel文件  
        try {  
            // 创建新的Excel 工作簿  
            HSSFWorkbook workbook = new HSSFWorkbook();  
            // 在Excel工作簿中建一工作表，其名为缺省值  
            HSSFSheet sheet = workbook.createSheet();  

            //设置标题背景色
            HSSFCellStyle style = workbook.createCellStyle(); 
            style.setFillForegroundColor( HSSFColor.LIME.index ); 
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
            style.setAlignment(CellStyle.ALIGN_CENTER);//水平居中  
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
            
            //设置单元格居中 
            HSSFCellStyle style2 = workbook.createCellStyle(); 
            style2.setAlignment(CellStyle.ALIGN_CENTER);//水平居中  
            style2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
            
            // 创建第一行  
            HSSFRow row1 = sheet.createRow(0);  
            row1.setHeight( (short) 400 );
            HSSFCell cell1 = row1.createCell(1);
            cell1.setCellStyle(style);
            cell1.setCellValue( new HSSFRichTextString("违章车辆数"));
            HSSFCell cell2 = row1.createCell(2);
            cell2.setCellStyle(style);
            cell2.setCellValue( new HSSFRichTextString(map1.get("count").toString()));
            
            if (list1 != null && list1.size() >0) {
            	for (int i=0;i<list1.size();i++) {
                    // 建立一行
                    HSSFRow row2 = sheet.createRow(i+1);
                    Map<String,Object> map = list1.get(i);
                    HSSFCell cellValue1 = row2.createCell(1);
                    cellValue1.setCellValue(new HSSFRichTextString(map.get("name").toString()));
                    HSSFCell cellValue2 = row2.createCell(2);
                    cellValue2.setCellValue(new HSSFRichTextString(map.get("count").toString()));
                }
            }
            
            // 创建第2行  
            HSSFRow row2 = sheet.createRow(list1.size()+1);  
            row2.setHeight( (short) 400 );
            HSSFCell cell_1 = row2.createCell(1);
            cell_1.setCellValue( new HSSFRichTextString("违章总数"));
            HSSFCell cell_2 = row2.createCell(2);
            cell_2.setCellValue( new HSSFRichTextString(map2.get("count").toString()));
            
            // 把相应的Excel 工作簿  
            workbook.write(sos);  
  
            sos.flush();  
            // 操作结束，关闭文件  
            sos.close();  
        } catch (Exception e) {  
            System.out.println("Exception：" + e);  
        }  
    }
    
    /**
     * 设置让浏览器弹出下载对话框的Header.
     * 
     * @param response
     *            HttpServletResponse
     * @param fileName
     *            下载后的文件名.
     */
    private static void setFileDownloadHeader(HttpServletResponse response, String fileName) {
        try {
            // 中文文件名支持
            String encodedfileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            // 设置导出文件格式
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName + "\"");
            response.resetBuffer();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    /**     
     *  获取单元格数据内容为字符串类型的数据     
     *  @param cell Excel单元格     
     *  @return String 单元格数据内容     
     */    
    private Object getStringCellValue(HSSFCell cell) { 
        if (cell == null) {            
            return "";        
        } 
        switch (cell.getCellType()) {        
        case HSSFCell.CELL_TYPE_STRING:            
            return cell.getRichStringCellValue();          
        case HSSFCell.CELL_TYPE_NUMERIC:           
            return cell.getNumericCellValue() ;     
        case HSSFCell.CELL_TYPE_BOOLEAN:            
            return cell.getBooleanCellValue();            
        case HSSFCell.CELL_TYPE_BLANK:            
            return "";            
        default:            
            return "";            
        }       
    }
    
    // ======================================================================
    public CommonBaseService getCommonBaseService() {
        return ApplicationContextHelper.getBean(CommonBaseService.class);
    }
}
