package org.my.web.scriptUtils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ScriptUtils {
	public static void init(HttpServletResponse response) {
		response.setContentType("text/html; charset=euc-kr");
		response.setCharacterEncoding("euc-kr");
	}
	
	public static void alert(HttpServletResponse response, String alertText) throws Exception{
		init(response);
		PrintWriter out = response.getWriter();
		out.println("<script>alert('"+alertText+"');</script>");
		out.flush();
	}
	
	public static void closeWindow(HttpServletResponse response) throws IOException {
		init(response);
		PrintWriter out = response.getWriter();
		out.println("<script>self.close();</script>");
		out.flush();
	}
	
	public static void alertAndMovePage(HttpServletResponse response, String alertText, String nextPage) throws IOException {
		init(response);
		PrintWriter out = response.getWriter();
		out.println("<script>alert('"+alertText+"'); location.href='"+nextPage+"';</script>");
		out.flush();
	}
	
	public static void alertAndClosingPage(HttpServletResponse response, String alertText, String nextPage) throws IOException {
		init(response);
		PrintWriter out = response.getWriter();
		out.println("<script>alert('"+alertText+"'); self.close(); location.href='"+nextPage+"'; opener.location.reload();</script>");
		out.flush();
	}
}
