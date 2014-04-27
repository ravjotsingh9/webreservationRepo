package org.reservation.module.controller;
import org.apache.tomcat.jni.Time;
import org.eclipse.jdt.internal.compiler.apt.dispatch.RoundDispatcher;
import org.joda.time.DateTime;
import org.reservation.module.model.*;
import org.reservation.module.service.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		    /*   			+ "<a href=ReservationView.jsp?reg=" + Integer.toString(vehlist.get(index).getRegNo())  +">"    */   
		        /*			+ "<input type=\"button\" value=\"ReserveNow\" " */
		        	/*		+ "id=\"reserve\" );\"></input>" */
		        			+ "<input type=\"button\" "
		        			+ "onclick=\"reserve('" + Integer.toString(vehlist.get(index).getRegNo())  +"')\" value=\"Reserve Now\"></input>"
		     /*   			+ "</a></font>"            */   
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
		else if(request.getParameter("id").equals("2")) // id==2
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
		else if(request.getParameter("id").equals("3"))
		{
			ReservationDAO obj = new ReservationDAO();
			double est= 0.00;
			String ptime = new String(request.getParameter("ptime"));
			String dtime = new String(request.getParameter("dtime"));
			ptime = ptime.replace('/', '-');
			ptime = ptime.concat(":00.00");
			dtime = dtime.replace('/', '-');
			dtime = dtime.concat(":00.00");
			/*
			try {
				
					SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
				    Date Date;
					Date = date.parse(ptime);
					Timestamp ptimestamp = new java.sql.Timestamp(Date.getTime());
					
				    Date = date.parse(dtime);
				    Timestamp dtimestamp = new java.sql.Timestamp(Date.getTime());

				    est = obj.calculateCharges(Integer.parseInt(request.getParameter("regNo")), Timestamp.valueOf(ptime) ,Timestamp.valueOf(dtime));
					
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
*/			
			est = obj.calculateCharges(Integer.parseInt(request.getParameter("regNo")), Timestamp.valueOf(ptime) ,Timestamp.valueOf(dtime));
			String result = String.format("%.2f", est);
			
			String text ="<table border=\"0\" bordercolor=\"#7f8bb7\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#f3f5f8\">"
						+"<tr>"
						+ "<td colspan=\"4\" bgcolor=\"#7f8bb7\" width=\"100%\" height=\"25px\"><b><font color=\"#FFFFFF\" face=\"Arial\" size=\"2\">&nbsp;Reserve Vehicle</font></b>"
						+ "</tr>"
						+ "<tr>"
						+ "<td width=5%></td>"
						+"<td rowspan=\"7\" colspan=\"1\" ><div align=\"center\" style=\"border:1px solid #d3dae5;width:120px;height:100px;\">"
						+ "<font  size=\"2\" face=\"Verdana\">"
						+ "<label for=\"lblcost\" style=\"bgcolor:#7f8bb7\">Estimated Cost</label></font>"
						+"<br><br><label for=\"cost\">CAD "+result+"</label></div></td>"
								+ "<td width=5%></td>"
						+"<td>"
					+"<table border=\"0\" width=\"300\" border=\"0\" align=\"left\">"
			   // 	+"<tr>"
				
				//	+"</tr>"
					+ "<tr>"
					+ "<td><font size=\"2\" face=\"Verdana\"><label for=\"username\">Name:</label></font></td>"
					+"<td><input type=\"text\" id=\"username\" name=\"username\" size=\"20\" /></td>"
					+"</tr><tr>"
					+"<td><font size=\"2\" face=\"Verdana\"><label for=\"ph\">Phone Number:</label></font></td>"
					+"<td><input type=\"text\" name=\"phn\" id=\"phn\" /></td>"
					+"</tr><tr>"
					+"<td><font size=\"2\" face=\"Verdana\"><label for=\"email\">Email Address:</label></font></td>"
					+"<td><input type=\"text\" name=\"email\" id==\"email\" size=\"20\" /></td>"
					+"</tr><tr>"
					+"<td><font size=\"2\" face=\"Verdana\"><label for=\"address\">Residential Address:</label></font></td>"
					+"<td><textarea rows=\"2\" cols=\"3\" name=\"desc\" id=\"desc\" style=\"height: 40px;width:150px\"></textarea></td>"
					+"</tr><tr>"
					+"<td><font size=\"2\" face=\"Verdana\"><label id=\"additional equipment\">Additional Equipment:</label></font></td>"
					+"<td valign=\"bottom\"><select id=\"slct1\" name=\"slct1\" onchange=\"populate(this.id, 'slct2')\">"
				   	+"<option value=\"\"></option>"
			   		+"<option value=\"Yes\">Yes</option>"
			   		+"<option value=\"No\">No</option>"
					+"</select></td></tr><tr><td></td>"
					+"<td id=\"slct2\"></td>"
					+"</tr>"
					+"<tr>"
					+"<td></td>"
					+"<td align=\"left\" valign=\"top\"><input type=\"button\" name=\"reserveit\" id=\"reserveit\" value=\"Submit\" />"
					+"</td>"
					+"</tr>"					+"</table>"
					+"</td><td width=10%></td>"
					+"</tr>"
					+"</table>";
			
			
			
			response.getWriter().write(text);
		}
		//request.getRequestDispatcher("UpdatedSearchCarView.jsp").forward(request, response);
	}

}
