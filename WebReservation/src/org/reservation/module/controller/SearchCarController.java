package org.reservation.module.controller;
import org.joda.time.DateTime;
import org.reservation.module.model.*;
import org.reservation.module.service.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SearchCar")
public class SearchCarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SearchCarController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	if(request.getParameter("txt1").equals("car") || request.getParameter("txt1").equals("truck"))
    	{/*
    		try 
			{
				String btn = request.getParameter("txt1");			
				
				if(btn.equals("car"))
				{
					btn ="CAR";
				}
				else if(btn.equals("truck"))
				{
					btn = "TRUCK";
				}
				else
				{
					btn="";   
				}
				VehicleTypeDAO result = new VehicleTypeDAO();
								
				//DateTime date = new DateTime();
				//DateTime date1 = date.plus(1);
				
				VehicleTypeListBeanModel vehicles = result.getFilteredList(btn);
		        request.setAttribute("vehicles", vehicles); // Will be available as ${vehicles} in JSP
		        request.getRequestDispatcher("UpdatedSearchCarView.jsp").forward(request, response);
		    } catch (Exception e) {
		        System.out.print("Servlet Exception"+e.getMessage());
		    }
		   */ 
    	}
    	else
    	{
	    	try
	    	{
		    	int memNo= Integer.parseInt(request.getParameter("txt1"));
		    	String text;
		    	
		    	response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
		    	response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
		    	ClubMemberDAO result = new ClubMemberDAO();
		    	text = Integer.toString(result.viewPoints(memNo));
		    	System.out.print(text);
		    	response.getWriter().write(text);
	    	}
	    	catch(Exception e)
	    	{
	    		System.out.print(e.getMessage());
	    	}
    	}
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id").equals("1"))
		{
			try {
				SearchVehicleDAO s = new SearchVehicleDAO();
		        VehicleListBeanModel vehicles = new VehicleListBeanModel();
		        System.out.print(request.getParameter("id")+"\t"+request.getParameter("category")+"\t"+request.getParameter("type")+"\t"+ request.getParameter("ptime")+"\t"+request.getParameter("dtime")+"\n");
		        
		        vehicles = s.search(request.getParameter("category"),request.getParameter("type"), request.getParameter("ptime"), request.getParameter("dtime"));
		        
		        String text= "<div id=\"result\"><table  border=\"1\" width=\"535\" align=\"left\" bordercolor=\"#dae0ea\">"
		        		+ "<tr style=\"height:30px;width:50px;background-color:#d2d9e4\">"
		        		+ "<td><font size=\"2\" face=\"Verdana\">Category</font></td>"
		        		+ "<td><font size=\"2\" face=\"Verdana\">Brand</font></td>"
		        		+ "<td><font size=\"2\" face=\"Verdana\">Type</font></td>"
		        		+ "<td><font size=\"2\" face=\"Verdana\">Reserve Now</font></td>"
		        		+ "</tr>";
	
		        ArrayList<VehicleBeanModel> vehlist = vehicles.getVehlist();
		        //VehicleBeanModel veh= new VehicleBeanModel();
		        int len = vehlist.size();
		        int index=0;
		        while(len>index)
		        { 
		        	//veh = vehlist.get(index);
		        	text= text.concat("<tr style=\"height:25px;width:50px;\">");
		        	text= text.concat("<td><font size=\"2\" face=\"Verdana\" color=\"#62799e\">" + vehlist.get(index).getCategory() + "</font></td>");
		        	text= text.concat("<td><font size=\"2\" face=\"Verdana\" color=\"#62799e\">" + vehlist.get(index).getBrand() + "</font></td>");
		        	text= text.concat("<td><font size=\"2\" face=\"Verdana\" color=\"#62799e\">" + vehlist.get(index).getType() + "</font></td>");
		        	text= text.concat("<td><font size=\"2\" face=\"Verdana\" color=\"#62799e\">"
		       			+ "<a href=ReservationView.jsp?reg=" + Integer.toString(vehlist.get(index).getRegNo())  +">"   
		        			+ "<input type=\"button\" value=\"ReserveNow\" "
		        			+ "id=\"reserve\" );\"></input>"
		        			
		        			+ "</a></font>"   
		        			+ "</td></tr>");
		        	System.out.print(vehlist.get(index).getRegNo() +"\t"+ vehlist.get(index).getCategory()+"\t"+ vehlist.get(index).getType() +"\t"+ vehlist.get(index).getBrand() +"\n");
		        	index++;
		        }
		        text= text.concat("</table></div>");
		        System.out.print(text);
		        
		        //response.setContentType("text/html");  // Set content type of the response so that jQuery knows what it can expect.
		        response.getWriter().write(text);
		        //vehicles = s.search(request.getParameter("category"),"SELECT", request.getParameter("picktime"), request.getParameter("droptime"));
		        //request.setAttribute("vehicles", vehicles); // Will be available as ${vehicles} in JSP
		    } catch (Exception e) {
		        System.out.print("Servlet Exception"+e.getMessage());
		    }
		}
		else // id==2
		{
			ReservationDAO cancelRes = new ReservationDAO();
			try {
					String text= new String();
					System.out.print(request.getParameter("id")+"\t"+request.getParameter("conf")+"\t"+request.getParameter("ph")+"\t"+ request.getParameter("ptime")+"\n");
					int result= cancelRes.cancelReservation(request.getParameter("conf"), request.getParameter("ph"), request.getParameter("ptime"));
					System.out.println(result);
					response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
			    	response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
					if(result==0)
					{
						
						text="The provided confirmation no. doesnot exist";
						response.getWriter().write(text);
					}
					else
					{
						
						text= "Confirmation # "+request.getParameter("conf")+" has been Cancelled.";
						System.out.print(text);
						response.getWriter().write(text);
					}

			} 
			catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//request.getRequestDispatcher("UpdatedSearchCarView.jsp").forward(request, response);
	}

}
