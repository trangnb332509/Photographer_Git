/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modal.Galery;

/**
 *
 * @author Dell Inc
 */
public class GaleryDAO {

    public ArrayList<Galery> getTop3Galery() throws Exception {
        DBContext db = new DBContext();
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        ArrayList<Galery> galeries = new ArrayList<>();
        try {
            String sql = "SELECT top 3 [ID]\n"
                    + "      ,[title]\n"
                    + "      ,[description]\n"
                    + "      ,[name]\n"
                    + "  FROM [Photographer].[dbo].[gallery]";
            con = db.getConnection();
            pre = con.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                Galery galery = new Galery();
                galery.setId(rs.getInt("ID"));
                galery.setTitle(rs.getString("title"));
                galery.setDescription(rs.getString("description"));
                galery.setName(rs.getString("name"));
                galeries.add(galery);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(con, pre, rs);
        }
        return galeries;
    }

    public Galery getGaleryById(int gid) throws Exception {
        DBContext db = new DBContext();
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT [ID]\n"
                    + "      ,[title]\n"
                    + "      ,[description]\n"
                    + "      ,[name]\n"
                    + "  FROM [Photographer].[dbo].[gallery]\n"
                    + "  where ID = ?";
            con = db.getConnection();
            pre = con.prepareStatement(sql);
            pre.setInt(1, gid);
            rs = pre.executeQuery();
            if (rs.next()) {
                Galery galery = new Galery();
                galery.setId(rs.getInt("ID"));
                galery.setTitle(rs.getString("title"));
                galery.setDescription(rs.getString("description"));
                galery.setName(rs.getString("name"));
                return galery;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(con, pre, rs);
        }
        return null;
    }

    public int count() throws Exception {
        DBContext db = new DBContext();
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT COUNT(*) as total FROM [Photographer].[dbo].[gallery]";
            con = db.getConnection();
            pre = con.prepareStatement(sql);
            rs = pre.executeQuery();
            int count = 0;
            if (rs.next()) {
                count = rs.getInt("total");
            }
            return count;
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(con, pre, rs);
        }
    }

    public ArrayList<Galery> getPagingGalery(int pageIndex, int pageSize) throws Exception {
        DBContext db = new DBContext();
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        ArrayList<Galery> galeries = new ArrayList<>();
        try {
            String sql = "SELECT [ID], [title], [description], [name] From\n"
                    + "(SELECT ROW_NUMBER() over (order by ID asc) as rid, [ID], [title], [description], [name]\n"
                    + "FROM [Photographer].[dbo].[gallery]) tbl\n"
                    + "where rid between ? and ?";
            con = db.getConnection();
            pre = con.prepareStatement(sql);
            int first = (pageIndex-1)*pageSize + 1;
            int last = pageIndex*pageSize;
            pre.setInt(1, first);
            pre.setInt(2, last);
            rs = pre.executeQuery();
            while (rs.next()) {
                Galery galery = new Galery();
                galery.setId(rs.getInt("ID"));
                galery.setTitle(rs.getString("title"));
                galery.setDescription(rs.getString("description"));
                galery.setName(rs.getString("name"));
                ImageDAO imagedao = new ImageDAO();
                galery.setImage_url(imagedao.getImageByGid(rs.getInt("ID")).getUrl());
                galeries.add(galery);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(con, pre, rs);
        }
        return galeries;
    }
}
