package com.cyj.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;



/**
 * Workbook: EXCEL文件
 * Sheet:  EXCEL文件中的一个sheet表格
 * Row:  sheet表中的一行
 * Cell: sheet表中的一个单元格
 *
 */
public class ExcelCreateImage {
	
	 public static void main(String[] args) {
         FileOutputStream fileOut = null;   
         BufferedImage bufferImg = null;   
        //先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray  
        try {
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();   
            bufferImg = ImageIO.read(new File("C:/Users/Administrator/Desktop/Cammon.png"));   
            ImageIO.write(bufferImg, "jpg", byteArrayOut);
            
            HSSFWorkbook wb = new HSSFWorkbook();   
            HSSFSheet sheet1 = wb.createSheet("test picture");  
            //画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
            HSSFPatriarch patriarch = sheet1.createDrawingPatriarch();   
            //anchor主要用于设置图片的属性
            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 255, 255,(short) 1, 1, (short) 5, 8);   
            anchor.setAnchorType(3);   
            //插入图片  
            patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG)); 
            fileOut = new FileOutputStream("D:/测试Excel.xls");   
            // 写入excel文件   
             wb.write(fileOut);   
             System.out.println("----Excle文件已生成------");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(fileOut != null){
                 try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
	 
	 
	 
	 
	/* 至于为什么会是这样的结果，主要是因为HSSFClientAnchor(0, 0, 255, 255,(short) 1, 1, (short) 5, 8)这个构造函数造成的，下面我就来解释这个构造函数：HSSFClientAnchor(int dx1,int dy1,int dx2,int dy2,short col1,int row1,short col2, int row2);各个参数的含义如下：

     dx1：the x coordinate within the first cell。

     dy1：the y coordinate within the first cell。

     dx2：the x coordinate within the second cell。

     dy2：the y coordinate within the second cell。

     col1：the column (0 based) of the first cell。

     row1：the row (0 based) of the first cell。

     col2：the column (0 based) of the second cell。

     row2：the row (0 based) of the second cell。

     这里dx1、dy1定义了该图片在开始cell的起始位置，dx2、dy2定义了在终cell的结束位置。col1、row1定义了开始cell、col2、row2定义了结束cell。
	 */
	 
	 

}
