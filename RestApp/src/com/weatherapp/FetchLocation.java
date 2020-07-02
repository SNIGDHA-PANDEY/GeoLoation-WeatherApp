package com.weatherapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;


public class FetchLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchLocation() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder stringBuilder=new StringBuilder();
		BufferedReader reader=null;
		PrintWriter outPrintWriter=response.getWriter();
		String location=request.getParameter("loc");
		URL url=new URL("https://api.mapbox.com/geocoding/v5/mapbox.places/"+location+".json?limit=1&access_token=pk.eyJ1Ijoic25pZ2RoYTk3IiwiYSI6ImNrYmJ2dnpncTA1ZDQyc3A5bXN0bTBjNTcifQ.kAQ-XsoW4xFm6atJFv_RiQ");
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
				JSONObject jsonObject=new JSONObject(stringBuilder.toString());
				JSONArray featuresArray=jsonObject.getJSONArray("features");
				JSONObject object=featuresArray.getJSONObject(0);
				JSONObject geobject=object.getJSONObject("geometry");
				JSONArray coordinates=geobject.getJSONArray("coordinates");
				System.out.println(coordinates);
				
				String Longitude=coordinates.get(0).toString();
				String Latitude= coordinates.get(1).toString();
						
				//outPrintWriter.println("Geocordinates for "+location);
				//outPrintWriter.println("Longitude: "+coordinates.get(0).toString());
				//outPrintWriter.println("Latitude: "+coordinates.get(1).toString());
		 	   request.setAttribute("location", location);
		 	   request.setAttribute("Latitude", Latitude);
		 	   request.setAttribute("Longitude", Longitude);
				RequestDispatcher dispatcher=request.getRequestDispatcher("locResponse.jsp");
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
