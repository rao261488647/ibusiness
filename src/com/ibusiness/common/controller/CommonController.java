package com.ibusiness.common.controller;

import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ibusiness.doc.store.StoreConnector;

/**
 * 共用管理controller
 * 
 * @author JiangBo
 * 
 */
@Controller
@RequestMapping("common")
public class CommonController {

	private SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private StoreConnector storeConnector;

    /**
     * 内存使用情况查询
     * 
     * @return
     */
    @RequestMapping("quary-memory")
    public String quaryMemory(Model model) {
    	// 可使用内存 
        long totalMemory = Runtime.getRuntime().totalMemory()/1000/1000; // kb; 
        // 剩余内存 
        long freeMemory = Runtime.getRuntime().freeMemory()/1000/1000; // kb; 
        // 最大可使用内存 
        long maxMemory = Runtime.getRuntime().maxMemory()/1000/1000; // kb;
        
        model.addAttribute("totalMemory", totalMemory + "kb");
        model.addAttribute("freeMemory", freeMemory + "kb");
        model.addAttribute("maxMemory", maxMemory + "kb");

        return "common/memory/memory-info.jsp";
    }
    /**
     * 内存使用情况查询
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping("show-memory-chart")
    public String showMemoryChart(Model model) {
    	// 可使用内存
        long totalMemory = Runtime.getRuntime().totalMemory()/1000/1000; // kb; 
        // 剩余内存
        long freeMemory = Runtime.getRuntime().freeMemory()/1000/1000; // kb; 
        // 最大可使用内存
        long maxMemory = Runtime.getRuntime().maxMemory()/1000/1000; // kb;
        
        JSONArray array = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("eventTime", ymdhms.format(new Date()));
        obj.put("maxMemory", maxMemory);
        obj.put("totalMemory", totalMemory);
        obj.put("freeMemory", freeMemory);
        array.add(obj);
        
        return array.toString();
    }
    /**
     * 富文本编辑器中 提交图片
     */
	@ResponseBody
    @RequestMapping("upload")
    public void upload(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
        
        String webPathURL = request.getContextPath().equals("/") ? "" : request.getContextPath();
        // 绝对路径
        String webPath = this.getClass().getResource("/").getPath();
        webPath = webPath.replace("WEB-INF/classes/", "");
        String saveFilePathName = webPath + File.separator + "commonFile";
        
        JSONObject obj = new JSONObject();
        try {
            Map<String, Object> map = saveFileToServer(request, "imgFile", saveFilePathName, null, null);
            String url = webPathURL + "/commonFile/" + map.get("fileName");
            obj.put("error", Integer.valueOf(0));
            obj.put("url", url);
        } catch (IOException e) {
            obj.put("error", Integer.valueOf(1));
            obj.put("message", e.getMessage());
            e.printStackTrace();
        }
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.print(obj.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Map<String, Object> saveFileToServer(HttpServletRequest request, String filePath, String saveFilePathName,
            String saveFileName, String[] extendes) throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile(filePath);
        Map<String, Object> map = new HashMap<String, Object>();
        if ((file != null) && (!file.isEmpty())) {
            String extend = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1)
                    .toLowerCase();
            if ((saveFileName == null) || (saveFileName.trim().equals(""))) {
                saveFileName = UUID.randomUUID().toString() + "." + extend;
            }
            if (saveFileName.lastIndexOf(".") < 0) {
                saveFileName = saveFileName + "." + extend;
            }
            float fileSize = Float.valueOf((float) file.getSize()).floatValue();
            List<String> errors = new ArrayList<String>();
            boolean flag = true;
            if (extendes != null) {
                for (String s : extendes) {
                    if (extend.toLowerCase().equals(s))
                        flag = true;
                }
            }
            if (flag) {
                File path = new File(saveFilePathName);
                if (!path.exists()) {
                    path.mkdir();
                }
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                DataOutputStream out = new DataOutputStream(new FileOutputStream(saveFilePathName + File.separator
                        + saveFileName));
                InputStream is = null;
                try {
                    is = file.getInputStream();
                    int size = (int) fileSize;
                    byte[] buffer = new byte[size];
                    while (is.read(buffer) > 0)
                        out.write(buffer);
                } catch (IOException exception) {
                    exception.printStackTrace();
                } finally {
                    if (is != null) {
                        is.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                }
                if (isImg(extend)) {
                    File img = new File(saveFilePathName + File.separator + saveFileName);
                    try {
                        BufferedImage bis = ImageIO.read(img);
                        int w = bis.getWidth();
                        int h = bis.getHeight();
                        map.put("width", Integer.valueOf(w));
                        map.put("height", Integer.valueOf(h));
                    } catch (Exception localException) {
                    }
                }
                map.put("mime", extend);
                map.put("fileName", saveFileName);
                map.put("fileSize", Float.valueOf(fileSize));
                map.put("error", errors);
                map.put("oldName", file.getOriginalFilename());
            } else {
                errors.add("不允许的扩展名");
            }
        } else {
            map.put("width", Integer.valueOf(0));
            map.put("height", Integer.valueOf(0));
            map.put("mime", "");
            map.put("fileName", "");
            map.put("fileSize", Float.valueOf(0.0F));
            map.put("oldName", "");
        }
        return map;
    }
    public static boolean isImg(String extend) {
        boolean ret = false;
        List<String> list = new ArrayList<String>();
        list.add("jpg");
        list.add("jpeg");
        list.add("bmp");
        list.add("gif");
        list.add("png");
        list.add("tif");
        for (String s : list) {
            if (s.equals(extend))
                ret = true;
        }
        return ret;
    }
    // ~ ======================================================================
    @Resource
	public void setStoreConnector(StoreConnector storeConnector) {
		this.storeConnector = storeConnector;
	}
}
