/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ContactDAO;
import dao.GaleryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Galery;

/**
 *
 * @author Dell Inc
 */
public class Home extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            GaleryDAO galerydao = new GaleryDAO();
            ContactDAO contactdao = new ContactDAO();
            request.setAttribute("active", "activeHome");
            request.setAttribute("top3Galery", galerydao.getTop3Galery());
            request.setAttribute("contact", contactdao.getContact());
            int pagesize = 3;
            //get total galery in database
            int total_galery = galerydao.count();
            //get total page after paging
            int total_page = (total_galery % pagesize == 0) ? (total_galery / pagesize)
                    : (total_galery / pagesize) + 1;
            ArrayList<Galery> galeries = new ArrayList<>();
            String p_index = request.getParameter("pageindex");
            int pageindex = 0;
            //set p_index for the first access
            if (p_index == null) {
                p_index = "1";
            }
            //handle when pageindex not number
            try {
                pageindex = Integer.parseInt(p_index);
            } catch (Exception ex) {
                request.setAttribute("result", "Invalid");
                request.getRequestDispatcher("home.jsp").forward(request, response);
            }

            //handle when pageindex out of size
            if (pageindex > total_page || pageindex < 1) {
                request.setAttribute("result", "Not Found");
            } else {
                galeries = galerydao.getPagingGalery(pageindex, pagesize);
            }
            request.setAttribute("pageindex", pageindex);
            request.setAttribute("totalpage", total_page);
            request.setAttribute("galeries", galeries);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("error", "Error");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
