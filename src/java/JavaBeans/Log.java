/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBeans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import Clases.*;
import java.sql.*;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author brayan7
 */
@ManagedBean
@RequestScoped
public class Log {
//VARIABLES INICIAR SESION
public String Usuario;
public String Pass;
//VARIABLES REGISTRARSE
public String name;
public String last;
public String email;
public String dateN;
public int cel;
public String usu1;
public String pass1;
//VARIABLE CONECTAR
public Conectar cc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateN() {
        return dateN;
    }

    public void setDateN(String dateN) {
        this.dateN = dateN;
    }

    public int getCel() {
        return cel;
    }

    public void setCel(int cel) {
        this.cel = cel;
    }

    public String getUsu1() {
        return usu1;
    }

    public void setUsu1(String usu1) {
        this.usu1 = usu1;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    public String Inicio(){
        cc = new Conectar();
        String msj="";
        String []datos = new String[8];
        Connection cn = cc.conexion();
        PreparedStatement st =null;
        if (cn==null) {
            //JOptionPane.showMessageDialog(null, "EEROR AL CONECTAR AL BD");
            System.out.println("ERROR AL CONECTAR A LA BD");
        } else {
            try {
                Statement st1 = cn.createStatement();
                ResultSet rs = st1.executeQuery("SELECT * FROM Usuario");
                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2);
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);
                    datos[4] = rs.getString(5);
                    datos[5] = rs.getString(6);
                    datos[6] = rs.getString(7);
                    datos[7] = rs.getString(8);
                }
                System.out.println("!!!!!!!!!!!!!!!" + datos[7] + datos[6]);
                if (Usuario.equalsIgnoreCase(datos[7]) && this.Pass.equalsIgnoreCase(datos[6])) {
                    msj = "Bienvenido.xhtml";
                }else{
                    msj = "ErrorL.xhtml";
                }
                System.out.println("////////CONECTADO///////");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return msj;
    }
    
    public String IngresarD(){
        cc = new Conectar();
        PreparedStatement st =null;
        String msj="";
        Connection cn = cc.conexion();
        if (cn==null) {
            JOptionPane.showMessageDialog(null, "EEROR AL CONECTAR AL BD");
            System.out.println("¿¿¿¿¿ERROR");
        } else {
            System.out.println("SI ENTRO");
            try {
                st = cn.prepareStatement("INSERT INTO Usuario (Nombre, Apellido, Correo, FechaN, Telefono, Contrasena, Usuario) values(?,?,?,?,?,?,?);");
                st.setString(1, name);
                st.setString(2, last);
                st.setString(3, email);
                st.setString(4, dateN);
                st.setInt(5, cel);
                st.setString(6, pass1);
                st.setString(7, usu1);
                st.executeUpdate();
                //JOptionPane.showMessageDialog(null, "Ingreso Correcto!");
                msj = "index.xhtml";
            } catch (Exception e) {
                System.out.println("*********" + e.getMessage());
                e.printStackTrace();
            } finally{
                try {
                    st.close();
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("////////TODO BIEN///////");
        }
        return msj;
    }

    /**
     * Creates a new instance of Log
     */
    public Log() {
    }
    
}
