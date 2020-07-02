package com.weatherapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;


public class FetchWeather extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public FetchWeather() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder stringBuilder=new StringBuilder();
		BufferedReader reader=null;
		PrintWriter outPrintWriter=response.getWriter();
		String location=request.getParameter("location");
		
		String lat=request.getParameter("Latitude");
		String lng=request.getParameter("Longitude");
		String urlString="http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lng+"&appid=0ad4e8592a3b42ae841d009768615bcc";
		System.out.println(urlString);
		URL url=new URL("http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lng+"&appid=0ad4e8592a3b42ae841d009768615bcc");
		HttpURLConnection connection=null;
		try {
			connection=(HttpURLConnection)url.openConnection();
			connection.setRequestProperty("Accept", "application/json");
			if(connection.getResponseCode()!=200)
			{
				throw new RuntimeException("HTTP GET failed..."+connection.getResponseCode());
			}
			else {
				reader=new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
				String outputString=null;
				while ((outputString=reader.readLine())!=null) {
					stringBuilder.append(outputString);
				}
				
				JSONObject weatherJsonObject=new JSONObject(stringBuilder.toString());
				JSONObject mainJsonObject=weatherJsonObject.getJSONObject("main");
				String tempString=mainJsonObject.get("temp").toString();
				String humidityString=mainJsonObject.get("humidity").toString();
				String pressureString=mainJsonObject.get("pressure").toString();
				String min=mainJsonObject.get("temp_min").toString();
				String max=mainJsonObject.get("temp_max").toString();
				request.setAttribute("loc", location);
				request.setAttribute("temp", tempString);
				request.setAttribute("hum", humidityString);
				request.setAttribute("pre", pressureString);
				request.setAttribute("min", min);
				request.setAttribute("max", max);
				//outPrintWriter.println("Temperature : "+tempString+"K");
				//outPrintWriter.println(stringBuilder.toString());
				//outPrintWriter.println("Longitude: "+coordinates.get(0).toString());
				//outPrintWriter.println("Latitude: "+coordinates.get(we).toString());
		 	   //request.setAttribute("geoResponse", geoResponse);
				RequestDispatcher dispatcher=request.getRequestDispatcher("weather.jsp");
		 	   dispatcher.forward(request, response);
			 
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			connection.disconnect();
		}
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
