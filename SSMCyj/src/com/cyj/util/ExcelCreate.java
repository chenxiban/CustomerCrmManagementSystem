package com.cyj.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.cyj.entity.Students;



/**
 * Workbook: EXCEL文件
 * Sheet:  EXCEL文件中的一个sheet表格
 * Row:  sheet表中的一行
 * Cell: sheet表中的一个单元格
 *
 */
public class ExcelCreate {
	
	//导出使用
	private String fileName;
	private InputStream inputStream;
	
	
	/**
	 * 创建EXCEL文件,并把集合数据写入
	 * @param list
	 * @throws IOException
	 */
	public void createExcel(List<Students> list) throws IOException {
		
		int total=list.size();//所有数据行数
		System.out.println("所有数据的行数=> "+total);
		//如果需要下载的数据条数大于 65535 行
		//分多个EXCEL文件,打包压缩.
		//分多个sheet
		
		
		Workbook workbook=null;
		
				
		
			workbook=this.createWorkbook(list);
			String path = "C:\\Users\\Administrator\\Desktop\\studentCreate.xls";//文件路径
			String excelFileName = "student.xls";//文件名称
			File excel = new File(path);//文件对象
			java.io.File file=new java.io.File(path);
			if(!file.exists()){
				file.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(file);
			
			workbook.write(out);
			out.flush();
			out.close();
		
	}
		
		
		
		public Workbook createWorkbook(List<Students> studentList) {
			Workbook workbook=new HSSFWorkbook();//构造一个xlx后缀的EXCEL文件对象
//			workbook=new XSSFWorkbook();//构造一个xlx后缀的EXCEL文件对象
			Sheet sheet=workbook.createSheet("studentData");//创建一个sheet表,并设置表名称
			//创建表头字段
			Row row=sheet.createRow(0);//创建索引为0的Row对象
			String[] header={"姓名","性别","年龄","出生日期","住址"};
			int head_length=header.length;
			Cell[] cells=new HSSFCell[head_length];
			//填入表头的值
			for (int i=0;i<cells.length;i++) {
				cells[i]=row.createCell(i);//创建一个单元格对象
				cells[i].setCellType(1);//设置单元格类型为文本
				cells[i].setCellValue(header[i]);
			}
			
			for (int i = 0; i < studentList.size(); i++) {
				row = this.getRtData(sheet, i+1,studentList,head_length);
			}
			return workbook;
		}
		
		
		private Row getRtData(Sheet sheet,int i,List<Students> studentList,int head_length) {
			Row  row = sheet.createRow(i);
			//设计cell的格式
			for (int j = 0; j < head_length; j++) {
				/*cells[i]=getCell(row, i);
				cells[i].setCellType(1);*/
				row.createCell(j);//创建一个单元格对象
				row.getCell(j).setCellType(1);//设置单元格类型为文本
			}
			Students student=studentList.get(i-1);//从下标为0开始
			
			row.getCell(0).setCellValue(student.getName());
			row.getCell(1).setCellValue(student.getSex());
			row.getCell(2).setCellValue(student.getAge());
			
			
			
			return row;
		}
		
		
		public static void main(String[] args) {
			System.out.println("-----");
			List<Students> list = new ArrayList<Students>();
			Students student = null;
			/*student = new Students("王一", "男", 17);
			list.add(student);
			student = new Students("王二", "女", 16);
			list.add(student);
			student = new Students("王三", "女", 16);
			list.add(student);*/
			
			ExcelCreate create = new ExcelCreate();
			try {
				create.createExcel(list);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	

}
