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

import com.ibusiness.common.service.CommonBaseService;
import com.ibusiness.common.service.CommonBusiness;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.component.table.entity.ConfTableColumns;
import com.ibusiness.core.spring.ApplicationContextHelper;
import com.ibusiness.core.util.ReflectUtils;
import com.ibusiness.core.util.StringUtils;

/**
 * excel 导入/导出 共用类
 * 
 * @author JiangBo
 * 
 */
public class ExcelCommon {
    /**
     * Excel导出
     * 
     * @param response
     * @param tableModel
     * @throws IOException
     */
    public void exportExcel(HttpServletResponse response, TableModel tableModel) throws IOException {
        // 设置让浏览器弹出下载对话框的Header
        setFileDownloadHeader(response, tableModel.getExcelName() + ".xls");
        // 利用输出输入流导出文件
        ServletOutputStream sos = response.getOutputStream();
        // 生成Excel文件到sos流中
        createExcelFile(sos, tableModel);
    }
    
    /**
     * 文件上传
     */
    public void uploadExcel(File file, TableModel tableModel, String className){
        try {
            //1.将上传的Excel文件导入到数据库中
            importData(file, tableModel, className);
            //2.返回成功
            System.out.println("{'success':true,'msg':'导入数据成功!'}");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("{'success':true,'msg':'导入数据失败!'}");
        }
    }
    
    /**
     * 生成Excel文件到sos流中
     * @param sos
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public void createExcelFile(ServletOutputStream sos, TableModel tableModel) {
        
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
            HSSFRow row = sheet.createRow(0);  
            row.setHeight( (short) 400 );
            
            // 取得表字段信息
            Map<String, ConfTableColumns> tableColumnsMap = CommonBusiness.getInstance().getTableColumnsMap(CommonUtils.toUpperCase(tableModel.getTableName()));
            
            // 循环创建单元格信息
            for (int i = 0; i < tableModel.getHeaderCount(); i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(style);
                // excel显示列标题
                String key = CommonUtils.toUpperCase(tableModel.getHeader(i));
                if (tableColumnsMap.containsKey(key)) {
                    // Excel导出文件显示字段信息
                    cell.setCellValue( new HSSFRichTextString(tableColumnsMap.get(key).getColumnName()));
                } else {
                    cell.setCellValue( new HSSFRichTextString(tableModel.getHeader(i)));
                }
            }
            
            // 循环给每行及每个单元格赋值
            for (int i = 0; i < tableModel.getDataCount(); i++) {
                // 建立一行
                HSSFRow row2 = sheet.createRow(i+1);
                for (int j = 0; j < tableModel.getHeaderCount(); j++) {
                    String value = tableModel.getValue(i, j);
                    HSSFCell cellValue = row2.createCell(j);
                    if (CommonUtils.isNull(value)) {
                        cellValue.setCellValue(0D); 
                    } else {
                        cellValue.setCellValue(new HSSFRichTextString(value)); 
                    }
                }
            }
  
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
     * 将上传的Excel文件导入到数据库中
     * @param file
     * @throws IOException 
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    @SuppressWarnings("rawtypes")
    private void importData(File file, TableModel tableModel, String className) {
        try {
            Class clazz = Class.forName(className);
            
            //读取Excel中数据到这个集合中
            FileInputStream is = new FileInputStream(file);
            HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(is));
            //获得第一个选项卡
            HSSFSheet sheet = wb.getSheetAt(0);
            // 得到总行数        
            int rowNum = sheet.getLastRowNum(); 
            //获得第一行
            HSSFRow row = sheet.getRow(0); 
            //获得列数
//            int colNum = row.getPhysicalNumberOfCells();  
            // 正文内容应该从第二行开始,第一行为表头的标题        
            for (int i = 1; i <= rowNum; i++) {     
                Object entity = clazz.newInstance();
                row = sheet.getRow(i);
                 // 循环单元格信息
                String colName="(";
                String colValue="(";
                for (int j = 0; j < tableModel.getHeaderCount(); j++) {
                    if (j != 0) {
                        colName = colName + ",";
                        colValue = colValue + ",";
                    }
                    colName = colName + tableModel.getHeader(j);
                    colValue = colValue + row.getCell(j);
                    // 反射机制设置entity中的值
                    setMethodValue(entity, tableModel.getHeader(j), getStringCellValue(row.getCell(j)));
                }
                colName = colName + ")";
                colValue = colValue + ")";
                getCommonBaseService().saveInsert(entity);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("{'success':true,'msg':'导入数据失败!'}");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("{'success':true,'msg':'导入数据失败!'}");
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
    
    /**
     * 反射设置set方法值
     */
    public static void setMethodValue(Object target, String methodName,
            Object methodValue) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        
        String setMethodName = ReflectUtils.getSetterMethodName(methodName);
        String getMethodName = "get" + StringUtils.capitalize(methodName);
        // 
        Method getMethod = target.getClass().getDeclaredMethod(getMethodName);
        Type returnType = getMethod.getGenericReturnType();// 返回类型
        if (returnType == String.class) {
            Method setMethod = target.getClass().getDeclaredMethod(setMethodName, String.class);
            setMethod.invoke(target, methodValue.toString());
        } else if (returnType == Integer.class) {
            Method setMethod = target.getClass().getDeclaredMethod(setMethodName, Integer.class);
            Integer value = 0;
            if (methodValue.toString().indexOf(".") >0) {
                value = Integer.parseInt(methodValue.toString().substring(0,methodValue.toString().indexOf(".")));
            } else {
                value = Integer.parseInt(methodValue.toString());
            }
            setMethod.invoke(target, value);
        } else if (returnType == Long.class) {
            Method setMethod = target.getClass().getDeclaredMethod(setMethodName, Long.class);
            setMethod.invoke(target, methodValue);
        } else if (returnType == Double.class) {
            Method setMethod = target.getClass().getDeclaredMethod(setMethodName, Double.class);
            setMethod.invoke(target, Double.parseDouble(methodValue.toString()));
        } else if (returnType == Boolean.class) {
            Method setMethod = target.getClass().getDeclaredMethod(setMethodName, Boolean.class);
            setMethod.invoke(target, methodValue);
        } else if (returnType == java.util.Date.class) {
            Method setMethod = target.getClass().getDeclaredMethod(setMethodName, java.util.Date.class);
            if (null != methodValue && !CommonUtils.isNull(methodValue.toString())) {
                Date date = new Date();
                try {
                    date = CommonUtils.getInstance().getYmdhms().parse(methodValue.toString().replaceAll(".0", "").replaceAll("/", "-").replaceAll("  ", " "));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                setMethod.invoke(target, date);
            }
        } else {
            Method setMethod = target.getClass().getDeclaredMethod(setMethodName, String.class);
            setMethod.invoke(target, methodValue.toString());
        }
    }
    // ======================================================================
    public CommonBaseService getCommonBaseService() {
        return ApplicationContextHelper.getBean(CommonBaseService.class);
    }
}
