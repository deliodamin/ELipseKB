/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appKB;

import conn.ConectarBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author delio
 */
public class MainThread {

    ComentariosKB coment = new ComentariosKB();
    static int lastID = 0;

    public void chamadas() {
        System.out.println("last antes = " + 2461);
        coment.verificaInsercaoDeComentario(2461);
        System.out.println("last depois = " + lastID);
        coment.verificaInsercaoDeComentario(lastID);
        System.out.println("last bem depois = " + lastID);
    }

    public void lastIDVerify() {
        try {
            ConectarBD conDB = new ConectarBD("Juba");
            Connection conn = conDB.getConn();
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //Filtro
            String name = "";
            String sql_caregory = "SELECT *"
                    + "FROM [ElipseKB].[dbo].[ikm_comments]";
            //System.out.println(sql_caregory);
            ResultSet rs = stmt.executeQuery(sql_caregory);
            //  int commentid = 0;
            int cont = 0;
            int id = 0;
            int controle = 0, size = 0;
            rs.last();
            lastID = rs.getInt("commentid");
        } catch (SQLException ex) {
            Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {

        MainThread mt = new MainThread();
        mt.lastIDVerify();
        mt.chamadas();
        // coments.verificaInsercaoDeComentario();

    }

}
