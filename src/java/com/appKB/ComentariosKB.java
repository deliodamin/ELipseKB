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

/**
 *
 * @author scherer
 */
public class ComentariosKB {

    public void verificaInsercaoDeComentario(int lastIDcoment) {

        try {

            ConectarBD conDB = new ConectarBD("Juba");

            Connection conn = conDB.getConn();

            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

//            //Filtro
//            String name = "";
//
//            String sql_caregory = "SELECT *"
//                    + "FROM [ElipseKB].[dbo].[ikm_comments]";
//
//            //System.out.println(sql_caregory);
//            ResultSet rs = stmt.executeQuery(sql_caregory);
//            int commentid = 0;
//            int cont = 0;
            int id = 0;
//            int controle = 0, size = 0;
//
//            rs.last();
//            commentid = rs.getInt("commentid");
//
//            cont = commentid;
//
//            System.out.println("Last Record: " + lastIDcoment);

            //Varre tabela Novos comentários
           // lastIDcoment = 2460;
            String sql_caregory2 = "SELECT *"
                    + "FROM [ElipseKB].[dbo].[ikm_comments]"
                    + "where commentid  > '" + lastIDcoment + "'";

            // System.out.println(sql_caregory2);
            ResultSet rs1 = stmt.executeQuery(sql_caregory2);
            int i = 0;
            while (rs1.next()) {

                id = rs1.getInt("commentid");

//             ConectarBD conDB2 = new ConectarBD("Juba");
//    Connection conn2 = conDB.getConn();
                Statement stmt2 = conn.createStatement();
                String sql_caregory3 = "SELECT *"
                        + "FROM [ElipseKB].[dbo].[ikm_comments]"
                        + "where commentid  = '" + id + "'";

//                System.out.println(sql_caregory3);
                ResultSet rs2 = stmt2.executeQuery(sql_caregory3);

                while (rs2.next()) {
//                    System.out.println(rs2.getString("content"));

               //Chamar função para enviar e-mail
                int     idcoment = rs2.getInt("questionid");
                String  namecoment = rs2.getString("name");
                String  emailcoment = rs2.getString("email");
                String  contentcoment = rs2.getString("content");

                String destinatario = "suporte@elipse.com.br";
                String assunto = "Um comentário foi adicionado no KnowledgeBase!";
                String Mensagem = "Um comentário foi adicionado no Elipse Knowledgebase - http://kb.elipse.com.br/pt-br" + "\r\n" 
                        +  "URL do Artigo: http://kb.elipse.com.br/pt-br/questions/" + idcoment + "\r\n" 
                        + "----------------------------------" + "\r\n"  + "Informações do Comentário" + "\r\n" 
                        + "----------------------------------" + "\r\n" + "De: " + namecoment + "\r\n" + "E-mail: " 
                        + emailcoment + "\r\n" + contentcoment;  
        
                    System.out.println(Mensagem);                          
                    
                   

                      //  System.out.println(size);
                        
                    }
                     if (i == 4 ) {
                         break;
                }
                i++;
                   MainThread.lastID = id;  
            }
         
            //Imprime o valor
            // System.out.println(commentid);
        } catch (SQLException ex) {
            System.out.println("Erro Pesquisa no KB. PesquisaBD.java");

        }

    }

    public static void main(String[] args) {

        ComentariosKB coments = new ComentariosKB();
       // coments.verificaInsercaoDeComentario();

    }

}
