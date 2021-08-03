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
import modal.Contact;

/**
 *
 * @author Dell Inc
 */
public class ContactDAO {

    public Contact getContact() throws Exception {
        DBContext db = new DBContext();
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT TOP 1000 [telephone]\n"
                    + "      ,[email]\n"
                    + "      ,[face_url]\n"
                    + "      ,[twitter_url]\n"
                    + "      ,[google_url]\n"
                    + "      ,[about]\n"
                    + "      ,[address]\n"
                    + "      ,[city]\n"
                    + "      ,[country]\n"
                    + "      ,[map_url]\n"
                    + "      ,[image_main]\n"
                    + "      ,[icon_face]\n"
                    + "      ,[icon_twitter]\n"
                    + "      ,[icon_google]\n"
                    + "      ,[sumary]\n"
                    + "  FROM [Photographer].[dbo].[contact]";
            con = db.getConnection();
            pre = con.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                Contact contact = new Contact();
                contact.setTelephone(rs.getString("telephone"));
                contact.setEmail(rs.getString("email"));
                contact.setFb_url(rs.getString("face_url"));
                contact.setTwitter_url(rs.getString("twitter_url"));
                contact.setGoogle_url(rs.getString("google_url"));
                contact.setAbout(rs.getString("about"));
                contact.setAddress(rs.getString("address"));
                contact.setCity(rs.getString("city"));
                contact.setCountry(rs.getString("country"));
                contact.setMap_url(rs.getString("map_url"));
                contact.setImage(rs.getString("image_main"));
                contact.setIcon_face(rs.getString("icon_face"));
                contact.setIcon_twitter(rs.getString("icon_twitter"));
                contact.setIcon_google(rs.getString("icon_google"));
                contact.setSumary(rs.getString("sumary"));
                return contact;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(con, pre, rs);
        }
        return null;
    }
}
