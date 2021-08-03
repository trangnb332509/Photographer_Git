/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ContactDAO;
import dao.GaleryDAO;
import dao.ImageDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Image;

/**
 *
 * @author Dell Inc
 */
public class Galery extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            GaleryDAO galerydao = new GaleryDAO();
            ImageDAO imagedao = new ImageDAO();
            ContactDAO contactdao = new ContactDAO();
            String galeryID = request.getParameter("galeryID");
            String imageID = request.getParameter("imageID");
            boolean galery_valid = true;
            int galery = 1;
            // check galeryID
            //galery must be integer and not null
            try {
                galery = Integer.parseInt(galeryID);
            } catch (Exception ex) {
                request.setAttribute("result1", "Galery Invalid");
                galery_valid = false;
            }
            //galery must be in size of galeries
            if (galery < 1 || galery > galerydao.count()) {
                request.setAttribute("result1", "Out of size");
                galery_valid = false;
            }
            request.setAttribute("top3Galery", galerydao.getTop3Galery());
            request.setAttribute("contact", contactdao.getContact());
            if (galery_valid) {
                request.setAttribute("active", galerydao.getGaleryById(galery).getName());
                request.setAttribute("galery", galerydao.getGaleryById(galery));
                int pagesize = 8;
                int imageTotal = imagedao.count(galery);
                int pageTotal = (imageTotal % pagesize == 0) ? (imageTotal / pagesize)
                        : (imageTotal / pagesize) + 1;
                ArrayList<Image> images = new ArrayList<>();
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
                    request.setAttribute("result3", "Invalid");
                    request.getRequestDispatcher("galery.jsp").forward(request, response);
                }

                //handle when pageindex out of size
                if (pageindex > pageTotal || pageindex < 1) {
                    request.setAttribute("result3", "Not Found");
                } else {
                    images = imagedao.getPagingImage(pageindex, pagesize, galery);
                }
                request.setAttribute("images", images);
                request.setAttribute("pageindex", pageindex);
                request.setAttribute("totalpage", pageTotal);
                int image = -1;
                //check imageID 
                try {
                    //set image for the first access
                    if (imageID == null) {
                        image = images.get(0).getId();
                    } else {
                        image = Integer.parseInt(imageID);
                    }
                } catch (Exception ex) {
                    request.setAttribute("result2", "Invalid");
                    request.getRequestDispatcher("galery.jsp").forward(request, response);
                }
                //get image in galery
                if (imagedao.getImageById(image, galery) != null) {
                    request.setAttribute("imageURL", imagedao.getImageById(image, galery));
                } else {
                    request.setAttribute("result2", "Not Found");
                }
                request.getRequestDispatcher("galery.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("galery.jsp").forward(request, response);
            }
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
