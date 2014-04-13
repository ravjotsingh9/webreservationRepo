package org.reservation.module.controller;
import org.reservation.module.model.*;
import org.reservation.module.service.*;

import java.io.IOException;
import java.util.List;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CarDAO result = new CarDAO();
	        List<VehicleBeanModel> vehicles = result.getFilteredList();
	        int i=0;
	        while(i< vehicles.size())
	        {
	        	
		       System.out.print("RegNo: " +vehicles.get(i).getRegNo());
		        System.out.print(", Category: " + vehicles.get(i).getCategory());
		        System.out.print(", Type: " + vehicles.get(i).getType());
		        System.out.println(", Brand: " + vehicles.get(i).getBrand());
		        
	        	i++;
	        }
	        request.setAttribute("vehicles", vehicles); // Will be available as ${vehicles} in JSP
	    } catch (Exception e) {
	        System.out.print("Servlet Exception"+e.getMessage());
	    }
		request.getRequestDispatcher("UpdatedSearchCarView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CarDAO result = new CarDAO();
	        List<VehicleBeanModel> vehicles = result.getFilteredList();
	        request.setAttribute("vehicles", vehicles); // Will be available as ${vehicles} in JSP
	    } catch (Exception e) {
	        System.out.print("Servlet Exception"+e.getMessage());
	    }
		request.getRequestDispatcher("UpdatedSearchCarView.jsp").forward(request, response);
	}

}
