package com.sankai.inside.crm.web.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sankai.inside.crm.web.ueditor.ActionEnter;

@Controller
public class UeditorController {

	@RequestMapping(value = "/ueditor.do")
	public void config(HttpServletRequest request, HttpServletResponse response, String action) {

		String agent = request.getHeader("User-Agent").toLowerCase();

		if (agent.indexOf("msie 7") > 0 || agent.indexOf("msie 8") > 0 || agent.indexOf("msie 9") > 0
				|| agent.indexOf("msie 10.0") > 0 || agent.indexOf("msie 11") > 0) {
			response.setContentType("text/html");
		} else {
			response.setContentType("application/json");
		}
		if (agent.indexOf("rv:11.0") > 0) {
			response.setContentType("text/html");
		}

		response.setCharacterEncoding("utf-8");
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		try {

			String exec = new ActionEnter(request, rootPath).exec();
			PrintWriter writer = response.getWriter();
			writer.write(exec);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/crmupload.do")
	public void crmupload(HttpServletRequest request, HttpServletResponse response, String action) {

		String agent = request.getHeader("User-Agent").toLowerCase();

		if (agent.indexOf("msie 7") > 0 || agent.indexOf("msie 8") > 0 || agent.indexOf("msie 9") > 0
				|| agent.indexOf("msie 10.0") > 0 || agent.indexOf("msie 11") > 0) {
			response.setContentType("text/html");
		} else {
			response.setContentType("application/json");
		}
		if (agent.indexOf("rv:11.0") > 0) {
			response.setContentType("text/html");
		}

		response.setCharacterEncoding("utf-8");
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		try {

			String exec = new ActionEnter(request, rootPath).exec();
			PrintWriter writer = response.getWriter();
			writer.write(exec);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
