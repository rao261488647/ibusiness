package com.webservice.weixin.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.jdbc.core.JdbcTemplate;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.ibusiness.common.util.CommonUtils;
import com.ibusiness.core.spring.ApplicationContextHelper;

/**
 * 微信支付相关 web service接口
 * 
 * @author JiangBo
 * 
 */
@Path("/wxpayrs")
public class WxPayResource {
	/**
	 * 生成二维码图片并直接以流的形式输出到页面.
	 */
	@Path("/qr_code_img")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public void getQRCode(@Context HttpServletRequest request, @Context HttpServletResponse response,
	        @QueryParam("code_url") String code_url) {
		String content = code_url;
		if (CommonUtils.isNull(content)) {
			return;
		}
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); // 设置字符集编码类型
		BitMatrix bitMatrix = null;
		try {
			bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 300, 300, hints);
			BufferedImage image = toBufferedImage(bitMatrix);
			// 输出二维码图片流
			try {
				ImageIO.write(image, "png", response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (WriterException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * BitMatrix 转换 BufferedImage
	 */
	public BufferedImage toBufferedImage(BitMatrix matrix) {
		int BLACK = 0xFF000000;
		int WHITE = 0xFFFFFFFF;
		//
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	// =========================================================================================

	/**
	 * 取得jdbcTemplate对象
	 */
	public JdbcTemplate getJdbcTemplate() {
		return ApplicationContextHelper.getBean(JdbcTemplate.class);
	}
}
