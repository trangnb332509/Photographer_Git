/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modal.Image;

/**
 *
 * @author Dell Inc
 */
public class ImageDAO {

    public String getImageById(int id, int gid) throws Exception {
        DBContext db = new DBContext();
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT [image_url]\n"
                    + "  FROM [Photographer].[dbo].[image]\n"
                    + "  where id = ? and gid = ?";
            con = db.getConnection();
            pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            pre.setInt(2, gid);
            rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getString("image_url");
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(con, pre, rs);
        }
        return null;
    }

    public int count(int gid) throws Exception {
        DBContext db = new DBContext();
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT COUNT(*) as total FROM [Photographer].[dbo].[image]\n"
                    + "where gid = ?";
            con = db.getConnection();
            pre = con.prepareStatement(sql);
            pre.setInt(1, gid);
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

    public ArrayList<Image> getPagingImage(int pageIndex, int pageSize, int gid) throws Exception {
        DBContext db = new DBContext();
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        ArrayList<Image> images = new ArrayList<>();
        try {
            String sql = "SELECT [id], [gid], [image_url] From\n"
                    + "(SELECT ROW_NUMBER() over (order by id asc) as rid, [id], [gid], [image_url]\n"
                    + "FROM [Photographer].[dbo].[image]\n"
                    + "where gid = ?) tbl\n"
                    + "where rid between ? and ?";
            con = db.getConnection();
            pre = con.prepareStatement(sql);
            pre.setInt(1, gid);
            int first = (pageIndex - 1) * pageSize + 1;
            int last = pageIndex * pageSize;
            pre.setInt(2, first);
            pre.setInt(3, last);
            rs = pre.executeQuery();
            while (rs.next()) {
                Image image = new Image();
                image.setId(rs.getInt("id"));
                image.setGid(rs.getInt("gid"));
                image.setUrl(rs.getString("image_url"));
                images.add(image);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(con, pre, rs);
        }
        return images;
    }

    public Image getImageByGid(int gid) throws Exception {
        DBContext db = new DBContext();
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        ArrayList<Image> images = new ArrayList<>();
        try {
            String sql = "select top 1 id, image_url from image\n"
                    + "where gid = ?";
            con = db.getConnection();
            pre = con.prepareStatement(sql);
            pre.setInt(1, gid);
            rs = pre.executeQuery();
            if (rs.next()) {
                Image image = new Image();
                image.setId(rs.getInt("id"));
                image.setUrl(rs.getString("image_url"));
                return image;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(con, pre, rs);
        }
        return null;
    }
}
