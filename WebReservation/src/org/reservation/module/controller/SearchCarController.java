package org.reservation.module.controller;
import org.apache.tomcat.jni.Time;
import org.eclipse.jdt.internal.compiler.apt.dispatch.RoundDispatcher;
import org.joda.time.DateTime;
import org.omg.CORBA.OBJ_ADAPTER;
import org.reservation.module.model.*;
import org.reservation.module.service.*;

import java.io.IOException;
import java.sql.SQLException;
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
		    	int point = result.viewPoints(memNo);
		    	if(point==-1)
		    	{
		    		text= "Membership No. does not exist";
		    	}
		    	else
		    	{
		    		text = Integer.toString(result.viewPoints(memNo));
		    	}
		    	System.out.print(text);
		    	response.getWriter().write(text);
	    	}
	    	catch(Exception e)
	    	{
	    		System.out.print(e.getMessage());
	    		response.getWriter().write(e.getMessage());
	    	}
    	}
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id").equals("1"))
		{
			try {
				SearchVehiclesDAO s = new SearchVehiclesDAO();
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
		        			+ "<input type=\"button\" id=\"res\" "
		        			+ "onclick=\"reserve('" + (vehlist.get(index).getRegNo()).toString()  +"')\" value=\"Reserve Now\"></input>"
		     /*   			+ "</a></font>"            */   
		        			+ "</td></tr>");
		        	System.out.print(vehlist.get(index).getRegNo() +"\t"+ vehlist.get(index).getCategory()+"\t"+ vehlist.get(index).getType() +"\t"+ vehlist.get(index).getBrand() +"\n");
		        	index++;
		        }
		        text= text.concat("</table>"
		        		+ "<div>"
		        		+ "<input type=\"hidden\" name=\"hcategory\" id=\"hcategory\" value=\""+request.getParameter("category")+"\"/>"
		        		+ "<input type=\"hidden\" name=\"htype\" id=\"htype\" value=\""+request.getParameter("type")+"\"/>"
		        		+ "<input type=\"hidden\" name=\"hptime\" id\"hptime\" value=\""+request.getParameter("ptime")+"\"/>"
		        		+ "<input type=\"hidden\" name=\"hdtime\" id=\"hdtime\" value=\""+request.getParameter("dtime")+"\"/>"
		        		+ "</div>"
		        		+ "</div>");
		        System.out.print(text);
		        
		        //response.setContentType("text/html");  // Set content type of the response so that jQuery knows what it can expect.
		        response.getWriter().write(text);
		        //vehicles = s.search(request.getParameter("category"),"SELECT", request.getParameter("picktime"), request.getParameter("droptime"));
		        //request.setAttribute("vehicles", vehicles); // Will be available as ${vehicles} in JSP
		    } catch (Exception e) {
		        System.out.print("Servlet Exception"+e.getMessage());
		        response.getWriter().write(e.getMessage());
		    }
		}
		else if(request.getParameter("id").equals("2")) // id==2
		{
			ReservationDAO cancelRes;
			try {
				cancelRes = new ReservationDAO();
			
			
					String text= new String();
					System.out.print(request.getParameter("id")+"\t"+request.getParameter("conf")+"\t"+request.getParameter("ph")+"\t"+ request.getParameter("ptime")+"\n");
					
					String ptime =	new String(request.getParameter("ptime"));
					System.out.print("ptime="+ptime+"Return="+ptime.isEmpty());
										
					if((!ptime.isEmpty())){
						ptime = ptime.replace('/', '-');
						ptime = ptime.concat(":00.00");
					}else{
						ptime = "1970-01-01 00:00:00";
					}
					System.out.println("ptime final="+ptime);
					int result= cancelRes.cancelReservation(request.getParameter("conf"), request.getParameter("ph"), Timestamp.valueOf(ptime));
					System.out.println(result);
					response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
			    	response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
					if(result==0)
					{
						
						text="No reservation exists";
						response.getWriter().write(text);
					}
					else
					{
						
						text= "Confirmation # "+request.getParameter("conf")+"/Reservation has been Cancelled.";
						System.out.print(text);
						response.getWriter().write(text);
					}

			} 
			catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.getWriter().write(e.getMessage());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.getWriter().write(e.getMessage());
			}
			 catch (Exception e1) {
			// TODO Auto-generated catch block
				e1.printStackTrace();
				response.getWriter().write(e1.getMessage());
			}
			
		}
		else if(request.getParameter("id").equals("3"))
		{
			
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
*/			ReservationDAO obj;
			try {
				
				obj = new ReservationDAO();
				est = obj.calculateCharges((request.getParameter("regNo")), Timestamp.valueOf(ptime) ,Timestamp.valueOf(dtime));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.getWriter().write(e.getMessage());
				return;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.getWriter().write(e.getMessage());
				return;
			}
			catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				response.getWriter().write(e1.getMessage());
				return;
			}
			String result = String.format("%.2f", est);
			String category = null;
			try {
				category = obj.getCategory(request.getParameter("regNo"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(category);
			String text ="<table border=\"0\" bordercolor=\"#7f8bb7\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#f3f5f8\">"
						+"<tr>"
						+ "<td colspan=\"4\" bgcolor=\"#7f8bb7\" width=\"100%\" height=\"25px\"><b><font color=\"#FFFFFF\" face=\"Arial\" size=\"2\">&nbsp;Reserve Vehicle</font></b>"
						+ "</tr>"
						+"<tr>"
						+"<td colspan=\"4\"><div style=\"height:4px;\"></div><div style=\"border:2px solid #d3dae5;\">"
						+"<table border=\"0\" width=\"100%\">"
						+"<tr>"
						+"<td><font  size=\"1\" face=\"Verdana\">"
						+"<label>Vehicle</label><div  id=\"hdcategory\" style=\"width:100px;\" >"+category + "</div>"
						+"</font></td>"
						+"<td><font  size=\"1\" face=\"Verdana\">"
						+"<label>Pickup at</label><div  id=\"hdptime\" >"+ptime +"</div>"
						+"</font></td>"
						+"<td><font  size=\"1\" face=\"Verdana\">"
						+"<label>Drop at</label><div  id=\"hddtime\" >"+dtime +"</div>"
						+"</font></td>"
						+"<td><font  size=\"1\" face=\"Verdana\" color=\"#f3f5f8\" >"
						+"<div  id=\"hdregnum\" style=\"visiblity:hidden;width:100px;\" >"+request.getParameter("regNo") + "</div>"
						+"</font></td>"
						+"</tr>"
						+"</table></div>"
						+"</td>"
						+"</tr>"
						+ "<tr>"
						+ "<td width=5%></td>"
						+"<td rowspan=\"7\" colspan=\"1\" ><div align=\"center\" style=\"border:1px solid #d3dae5;width:120px;height:100px;\">"
						+ "<font  size=\"2\" face=\"Verdana\">"
						+ "<label for=\"lblcost\" style=\"bgcolor:#7f8bb7\">Estimated Cost*</label></font>"
						+"<br><br><label for=\"cost\">CAD "+result+"</label></div><br>"
								+ "<div><font  size=\"1\" face=\"Verdana\">*excluding insurance cost.</font></div></td>"
								+ "<td width=5%></td>"	
						+"<td>"
					
					+ "<table border=\"0\" width=\"300\" border=\"0\" align=\"left\">"
			   // 	+"<tr>"
				
				//	+"</tr>"
					+ "<tr>"
					+ "<td><font size=\"2\" face=\"Verdana\"><label for=\"username\">Name:</label></font></td>"
					+"<td><input type=\"text\" id=\"username\" name=\"username\" size=\"20\" /></td>"
					+"</tr><tr>"
					+"<td><font size=\"2\" face=\"Verdana\"><label for=\"ph\">Phone Number:</label></font></td>"
					+"<td><input type=\"text\" name=\"phone\" id=\"phone\" /></td>"
					+"</tr><tr>"
					+"<td><font size=\"2\" face=\"Verdana\"><label for=\"email\">Email Address:</label></font></td>"
					+"<td><input type=\"text\" name=\"email1\" id=\"email1\" size=\"20\" /></td>"
					+"</tr><tr>"
					+"<td><font size=\"2\" face=\"Verdana\"><label for=\"address\">Residential Address:</label></font></td>"
					+"<td><textarea rows=\"2\" cols=\"3\" name=\"desc\" id=\"desc\" style=\"height: 40px;width:150px\"></textarea></td>"
					+"</tr><tr>"
					+"<td><font size=\"2\" face=\"Verdana\"><label id=\"additional equipment\">Additional Equipment:</label></font></td>"
					+"<td>";
			
			
			if(category.equalsIgnoreCase("car"))
			{
				text = text.concat("<input type=\"checkbox\" id=\"childseat\" name=\"childseat\" value=\"childseat\"><font size=\"2\" face=\"Verdana\">Child Seat</font>"
						+ "<br><input type=\"checkbox\" id=\"skirack\" name=\"skirack\" value=\"skirack\"><font size=\"2\" face=\"Verdana\">Ski Rack</font>"
						+ "</td></tr><tr><td></td>"
						+"<td id=\"slct2\"></td>"
						+"</tr>"
						+"<tr>"
						+"<td></td>"
						+"<td align=\"left\" valign=\"top\">"
						+ "<input type=\"button\" name=\"reserveit\" id=\"reserveit\" value=\"Reserve it\" "
						+ "onclick=\"reservecar()\"/>"
						+"</td>"
						+"</tr>"					+"</table>"
						+"</td><td width=10%></td>"
						+"</tr>"
						+"</table>");
			}
			else if(category.equalsIgnoreCase("truck") )
			{
				text = text.concat("<input type=\"checkbox\" id=\"cartow\" name=\"cartow\" value=\"cartow\"><font size=\"2\" face=\"Verdana\">Car Tow</font>"
						+ "<br><input type=\"checkbox\" id=\"liftgate\" name=\"liftgate\" value=\"liftgate\"><font size=\"2\" face=\"Verdana\">Lift Gate</font>"
						+ "</td></tr><tr><td></td>"
						+"<td id=\"slct2\"></td>"
						+"</tr>"
						+"<tr>"
						+"<td></td>"
						+"<td align=\"left\" valign=\"top\">"
						+ "<input type=\"button\" name=\"reserveit\" id=\"reserveit\" value=\"Reserve it\" "
						+ "onclick=\"reservetruck()\"/>"
						+"</td>"
						+"</tr>"					+"</table>"
						+"</td><td width=10%></td>"
						+"</tr>"
						+"</table>");
			}
			System.out.println(text);
			response.getWriter().write(text);
		}
		else if(request.getParameter("id").equals("4"))
		{
			System.out.println("id4");
			System.out.print(request.getParameter("id")+"\t"
							+request.getParameter("regNo")+"\t"
							+request.getParameter("name")+"\t"
							+request.getParameter("email")+"\t"
							+request.getParameter("add")+"\t"
							+request.getParameter("ph")+"\t"
							+request.getParameter("childseat")+"\t"
							+request.getParameter("skirack")+"\t"
							+ request.getParameter("ptime")+"\t"
							+request.getParameter("dtime")
							+"\n");
			int uid=0;
			boolean confirmed = false;
			String regNo= request.getParameter("regNo");
			String name= request.getParameter("name");
			String email= request.getParameter("email");
			String add= request.getParameter("add");
			String ph= request.getParameter("ph");
			String childseat= request.getParameter("childseat");
			String skirack= request.getParameter("skirack");
			String ptime= request.getParameter("ptime");
			String dtime= request.getParameter("dtime");
			UserDAO u;
			try {
				u = new UserDAO();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				response.getWriter().write(e2.getMessage());
				return;
			}
			UserBeanModel user= new UserBeanModel();
			user.setAddress(add);
			user.setEmail(email);
			user.setName(name);
			user.setPhoneNumber(Long.parseLong(ph));
			try {
				uid = u.addUser(user);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.getWriter().write(e.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.getWriter().write(e.getMessage());
			}
			
			ReservationDAO r;
			try {
				r = new ReservationDAO();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				response.getWriter().write(e1.getMessage());
				return;
			}
			String[] addEquip= {
					"nothing","nothing"
			};
			if(childseat.equalsIgnoreCase("childseat"))
			{
				addEquip[0]="CHILD SEAT";
			}
			if(skirack.equalsIgnoreCase("skirack"))
			{
				addEquip[1]="SKI RACK";
			}
			
			try {
				try {
					confirmed=  r.makeReservation(uid,Timestamp.valueOf(ptime),Timestamp.valueOf(dtime),regNo, addEquip);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(confirmed==true)
			{
				response.getWriter().write("Your Reservation is Confirmed.");
			}
			else
			{
				response.getWriter().write("Failed! Please try again.");
			}
		}
		else if(request.getParameter("id").equals("5"))
		{
			System.out.println("id5");
			System.out.print(request.getParameter("id")+"\t"+request.getParameter("cartow")+"\t"+request.getParameter("ph")+"\t"+ request.getParameter("ptime")+"\n");
			int uid=0;
			String regNo= request.getParameter("regNo");
			String name= request.getParameter("name");
			String email= request.getParameter("email");
			String add= request.getParameter("add");
			String ph= request.getParameter("ph");
			String cartow= request.getParameter("cartow");
			String liftgate= request.getParameter("liftgate");
			String ptime= request.getParameter("ptime");
			String dtime= request.getParameter("dtime");
			UserDAO u;
			try {
				u = new UserDAO();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				response.getWriter().write(e1.getMessage());
				return;
			}
			UserBeanModel user= new UserBeanModel();
			user.setAddress(add);
			user.setEmail(email);
			user.setName(name);
			user.setPhoneNumber(Integer.parseInt(ph));
			try {
				uid = u.addUser(user);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.getWriter().write(e.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.getWriter().write(e.getMessage());
			}
			
			ReservationDAO r;
			try {
				r = new ReservationDAO();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				response.getWriter().write(e1.getMessage());
				return;
			}
			String[] addEquip= new String[2];
			if(cartow.equalsIgnoreCase("true"))
			{
				addEquip[0]="CAR TOW";
			}
			if(liftgate.equalsIgnoreCase("true"))
			{
				addEquip[1]="LIFT GATE";
			}
			
			boolean confirmed= false;
			try {
				confirmed=  r.makeReservation(uid,Timestamp.valueOf(ptime),Timestamp.valueOf(dtime),regNo, addEquip);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(confirmed==true)
			{
				response.getWriter().write("Your Reservation is Confirmed.");
			}
			else
			{
				response.getWriter().write("Failed! Please try again.");
			}
		}
		//request.getRequestDispatcher("UpdatedSearchCarView.jsp").forward(request, response);
	}

}
