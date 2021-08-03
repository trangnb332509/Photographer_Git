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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dell Inc
 */
public class Contact extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            GaleryDAO galerydao = new GaleryDAO();
            ContactDAO contactdao = new ContactDAO();
            request.setAttribute("active", "activeContact");
            request.setAttribute("top3Galery", galerydao.getTop3Galery());
            request.setAttribute("contact", contactdao.getContact());
            request.getRequestDispatcher("contact.jsp").forward(request, response);
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
