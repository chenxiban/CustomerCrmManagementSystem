package com.cyj.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cyj.entity.Askers;
import com.cyj.entity.Component;
import com.cyj.entity.DyNamicMessages;
import com.cyj.entity.Result;
import com.cyj.entity.Students;
import com.cyj.entity.Users;
import com.cyj.service.AskersService;
import com.cyj.service.ComponentService;
import com.cyj.service.DyNamicMessagesService;
import com.cyj.service.RolesService;
import com.cyj.service.StudentsService;
import com.cyj.util.IsEmptyUtils;
import com.cyj.util.PoiUtils;

/**
 * 
 * @Description: 学生模块控制器
 * @author Cyj
 * @Date 2018-5-16
 * @Email 867647213@qq.com
 */
@RestController
@RequestMapping(value = "/students", name = "学生模块")
public class StudentsController {

	@Autowired
	private StudentsService sService = null;
	@Autowired
	private RolesService service;
	@Autowired
	private DyNamicMessagesService dService = null;
	@Autowired
	private ComponentService cService = null;
	@Autowired
	private AskersService aService = null;

	private PoiUtils poiUtils = new PoiUtils();// 初始化工具类

	// private boolean State;

	/**
	 * http://localhost:8080/SSMCyj/students/getStudents.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getStudents.php", produces = "text/plain;charset=UTF-8", name = "查询所有学生")
	public Object getStudents(Students s) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", sService.getStudentsCount(s));
		map.put("rows", sService.getStudents(s));
		return map;
	}

	/**
	 * http://localhost:8080/SSMCyj/students/delStudents.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delStudents.php", produces = "text/plain;charset=UTF-8", name = "删除学生")
	public Object delStudents(String id) {
		List<String> list = new ArrayList<String>();
		String[] ids = id.split(",");
		for (String dids : ids) {
			list.add(dids);
		}
		System.out.println(list);
		if (sService.delStudents(list)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * http://localhost:8080/SSMCyj/students/addStudents.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addStudents.php", produces = "text/plain;charset=UTF-8", name = "添加学生")
	public Object addStudents(Students s) {
		Component c = cService.selComponent();
		c.getComState();

		s.setCreateTime(new Date());
		if (c.getComState().equals("0")) {
			if (sService.addStudents(s)) {
				return new Result(true, "学生信息添加成功!");
			} else {
				return new Result(false, "学生信息添加失败!");
			}
		} else {
			List<Users> list = service.selUsersByRoleName("咨询师");

			Askers checkState;

			List<Askers> lists = new ArrayList<Askers>();
			for (int i = 0; i < list.size(); i++) {
				checkState = aService.getAskersByName(list.get(i)
						.getLoginName());
				if (checkState.getCheckState().equals("已签到")) {
					lists.add(checkState);
				}
			}
			if (IsEmptyUtils.isEmpty(lists) || lists.size() < 0) {
				sService.addStudents(s);
				return new Result(false, "学生信息添加成功!    但当前尚无咨询师签到,暂时无法分配!");
			}
			Random rand = new Random();
			int a = rand.nextInt(lists.size());
			s.setZiXunName(lists.get(a).getAskerName());
			if (sService.addStudents(s)) {
				return new Result(true, "学生信息添加成功!");
			} else {
				return new Result(false, "学生信息添加失败!");
			}
		}

	}

	/**
	 * http://localhost:8080/SSMCyj/students/updStudents.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updStudents.php", produces = "text/plain;charset=UTF-8", name = "修改学生")
	public Object updStudents(Students s) {
		if (sService.updStudents(s)) {
			return new Result(true, "学生信息修改成功!");
		} else {
			return new Result(false, "学生信息修改失败!");
		}
	}

	/**
	 * http://localhost:8080/SSMCyj/students/updStudentsByIds.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updStudentsByIds.php", produces = "text/plain;charset=UTF-8", name = "手动分配咨询师")
	public Object updStudentsByIds(String ziXunName, String id) {
		List<String> list = new ArrayList<String>();
		String[] ids = id.split(",");
		for (String dids : ids) {
			list.add(dids);
		}
		System.out.println(list);
		if (sService.updStudentsByIds(ziXunName, list)) {
			return new Result(true, "学生信息修改成功!");
		} else {
			return new Result(false, "学生信息修改失败!");
		}
	}

	/**
	 * http://localhost:8080/SSMCyj/students/addDyNamicMessages.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addDyNamicMessages.php", produces = "text/plain;charset=UTF-8", name = "添加动态")
	public Object addDyNamicMessages(DyNamicMessages d) {
		if (dService.addDyNamicMessages(d)) {
			return new Result(true, "动态添加成功!");
		} else {
			return new Result(false, "动态添加失败!");
		}
	}

	/**
	 * Excel导出数据 http://localhost:8080/SSMCyj/students/download.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/download.php", produces = "text/html;charset=UTF-8", name = "导出Excel")
	public String download(String id, HttpServletResponse response) {
		String downloadName = "导出的学生信息.xls";

		List<String> lists = new ArrayList<String>();
		String[] ids = id.split(",");
		for (String dids : ids) {
			lists.add(dids);
		}
		List<Students> list = sService.getStudentByIds(lists);// 要导出的数据集合
		try {
			response.setCharacterEncoding("UTF-8");// 设置响应的字符编码格式
			response.setContentType("application/vnd.ms-excel");// 指明响应文件为excel类型
			response.setHeader("Content-disposition", "attachment;filename="
					+ new String(downloadName.getBytes("GB2312"), "ISO8859-1"));// 文件名编码处理，防止浏览器下载文件名乱码
			ServletOutputStream outputStream = response.getOutputStream();// 获取响应的字节输出流

			poiUtils.createExcel(list, Students.class, outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Excel导入数据 http://localhost:8080/SSMCyj/students/upload.php
	 * SpringMVC处理文件上传 单文件上传
	 * 
	 * @param myfile
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/upload.php", produces = "text/html;charset=UTF-8", name = "上传文件")
	public Object upload(
			@RequestParam(value = "myfile", required = false) CommonsMultipartFile myfile,
			@RequestParam(value = "desc", required = false) String desc) {
		String name = myfile.getName();// form表单中参数名称
		String originalFilename = myfile.getOriginalFilename();// 得到上传文件的名称
		System.out.println("表单中文件参数名称 name=>" + name);
		System.out.println("上传的文件原始名称 originalfileName=>" + originalFilename);
		System.out.println("文件描述信息 desc=>" + desc);

		PoiUtils poiUtils = new PoiUtils();// Excel工具类

		List<Students> list = null;
		try {
			list = (List<Students>) poiUtils.parseExcel(Students.class,
					myfile.getInputStream(), originalFilename);
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < list.size(); i++) {
			sService.addStudentByList(list.get(i));
		}

		return list;
	}

	/**
	 * http://localhost:8080/SSMCyj/students/selComponent.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selComponent.php", produces = "text/plain;charset=UTF-8", name = "查询分量设置状态")
	public Object selComponent() {
		return cService.selComponent();
	}

	/**
	 * http://localhost:8080/SSMCyj/students/updComponent.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updComponent.php", produces = "text/plain;charset=UTF-8", name = "分量设置")
	public Object updComponent(Component c) {
		if (cService.updCom(c)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("comState", cService.selComponent());
			return new Result(true, map);
		} else {
			return new Result(false, "分量设置失败!");
		}
	}

	/**
	 * http://localhost:8080/SSMCyj/students/getDyNamicMessages.php 查看动态的详细信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getDyNamicMessages.php", produces = "text/plain;charset=UTF-8", name = "查询动态数")
	public Object getDyNamicMessages(DyNamicMessages d, String ziXunName) {
		if (IsEmptyUtils.isEmpty(ziXunName)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", dService.getDyNamicMessagesCount(d));
			map.put("rows", dService.getDyNamicMessages(d));
			return map;
		} else {
			List<String> ids = dService.selDyNamicMessages(ziXunName);
			d.setIds(ids);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", dService.getDyNamicMessagesCount(d));
			map.put("rows", dService.getDyNamicMessages(d));
			return map;
		}

	}

	/**
	 * http://localhost:8080/SSMCyj/students/getDyNamicMessagesCount.php
	 * 查看当前咨询时动态条数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getDyNamicMessagesCount.php", produces = "text/plain;charset=UTF-8", name = "查询动态")
	public Object getDyNamicMessagesCount(DyNamicMessages d, String ziXunName) {
		if (IsEmptyUtils.isEmpty(ziXunName)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", dService.getDyNamicMessagesCount(d));
			return new Result(true, map);
		} else {
			List<String> id = dService.selDyNamicMessages(ziXunName);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", id.size());
			return new Result(true, map);
		}

	}

	/**
	 * http://localhost:8080/SSMCyj/students/updDyNamicMessages.php 修改动态状态
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updDyNamicMessages.php", produces = "text/plain;charset=UTF-8", name = "动态修改")
	public Object updDyNamicMessages(DyNamicMessages c) {
		c.setIsOpen("否");
		if (dService.updDyNamicMessages(c)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * http://localhost:8080/SSMCyj/students/getStudentByAdd.php
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getStudentByAdd.php", produces = "text/plain;charset=UTF-8", name = "学生来源分布")
	public Object getStudentByAdd() {
		String zzString = "郑州";
		String kaifeng = "开封";
		String luoyang = "洛阳";
		String pds = "平顶山";
		String ay = "安阳";
		String hb = "鹤壁";
		String xinx = "新乡";
		String jiaozuo = "焦作";
		String puyang = "濮阳";
		String xuchang = "许昌";
		String luohe = "漯河";
		String sanmenxia = "三门峡";
		String nanyang = "南阳";
		String shangqiu = "商丘";
		String xinyang = "信阳";
		String zhouk = "周口";
		String zhumadian = "驻马店";
		String jiyuan = "济源";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("zz", sService.getStudentsCounts(zzString));
		map.put("kf", sService.getStudentsCounts(kaifeng));
		map.put("ly", sService.getStudentsCounts(luoyang));
		map.put("pds", sService.getStudentsCounts(pds));
		map.put("ay", sService.getStudentsCounts(ay));
		map.put("hb", sService.getStudentsCounts(hb));
		map.put("xx", sService.getStudentsCounts(xinx));
		map.put("jz", sService.getStudentsCounts(jiaozuo));
		map.put("py", sService.getStudentsCounts(puyang));
		map.put("xc", sService.getStudentsCounts(xuchang));
		map.put("lh", sService.getStudentsCounts(luohe));
		map.put("smx", sService.getStudentsCounts(sanmenxia));
		map.put("ny", sService.getStudentsCounts(nanyang));
		map.put("sq", sService.getStudentsCounts(shangqiu));
		map.put("xy", sService.getStudentsCounts(xinyang));
		map.put("zk", sService.getStudentsCounts(zhouk));
		map.put("zmd", sService.getStudentsCounts(zhumadian));
		map.put("jy", sService.getStudentsCounts(jiyuan));
		
		return new Result(true, map);
	}

}
