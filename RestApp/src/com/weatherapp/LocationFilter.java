package com.weatherapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class LocationFilter
 */
public class LocationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LocationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String placeString=request.getParameter("loc");
		 boolean numeric = true;
		 System.out.println("INSIDE FILTER");
 
	        
	        if((placeString != null)  && (placeString.matches("^[a-zA-Z]*$")) && !placeString.isBlank() && !placeString.isEmpty())
	        {	request.setAttribute("loc", placeString);
        	chain.doFilter(request, response);
	        }
	 
	        else {
	        	PrintWriter printWriter=response.getWriter();
		         request.getRequestDispatcher("loc.jsp").include(request, response);
		        	printWriter.println("<div align='center'><SPAN style='color:red'>Invalid Location</SPAN></div>");
	        
	        }
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
