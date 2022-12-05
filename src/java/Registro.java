/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//es la conexion con la base de datos
import java.sql.Connection;
import java.sql.DriverManager;
//realiza las acciones sql como
//insert, delete, update create, alter , drop
import java.sql.Statement;
//crea un objeto para poder realizar la consulta
import java.sql.ResultSet;
import javax.servlet.ServletConfig;



/**
 *
 * @author user
 */
public class Registro extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request atender las peticiones del cliente
     * @param response servlet response atender las respuestas por parte del servidor
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    //variables globales
    private Connection con;
    private Statement set;
    private ResultSet rs;
    
    //constructorr 
    
    public void init(ServletConfig cfg) throws ServletException{
        
        //como se va a conectar a la base de datos
        String url = "jdbc:mysql://localhost/registro4iv9";
                    //tipo de driver:gestorbd:puerto//ip//nombrebd
        
        String userName = "root";
        String password = "root";
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            
           con = DriverManager.getConnection(url, userName, password);
           set = con.createStatement();
           
           System.out.println("conexion exitosaa");
           
        }catch(Exception e){
            System.out.println("chispas");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        
    }
    
    
    
    
    
    
    protected void processRequest(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            String nom, appat, apmat,correo, ip, ipr;
            int edad, puerto, puertor;
//el nombre viene desde el formulario y el formulario es una solicitud - objeto request y llamamos a get parameter
            nom = request.getParameter("nombre");
            appat = request.getParameter("appat");
            apmat = request.getParameter("apmat");//la edad es entera por lo que debemos parciarla 
            edad = Integer.parseInt(request.getParameter("edad"));
            correo = request.getParameter("correo");
            
             ip= request.getLocalAddr();
            puerto = request.getLocalPort();
            
            ipr = request.getRemoteAddr();
            puertor = request.getRemotePort();
            
            
            //VAMOS A INTENTAR REGISTRAR EN LA BD
            
            try{
            
                /*
                para poder registrar un usuario es necesario la sentencia insert
                bajo el siguiente esquema
                
                insert into nombretabla (atributo1, atributo2,...)  values("valor1, 'valor 2" ....
                "" valores de tipo cadena
                ''numerico 
                si no tienen nada tambien puede ser numerico*/
                
                String q = "insert into mregistro"
                        + "(nom_usu, appat_usu, edad_usu, email_usu)"
                        + "values"
                        /*recordar que no se puede usar comillas dobles en comillas dobles  */
                
                        + "('"+nom+"', '"+appat+"', "+edad+", '"+correo+"')";  
                
                        set.executeUpdate(q);
                        
                      System.out.println("Registro Exitoso");
                        
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Registro</title>");            
            out.println("</head>");
            out.println("<body>"
                    + "<br>"
                    + "Tu nombre es:"+ nom
                    + "<br>"
                    + "Tu apellido Paterno es:"+appat
                    + "<br>"
                    + "Tu apellido materno es:"+apmat
                    + "<br>"
                    + "Tu edad es:"+edad
                    + "<br>"
                    + "Tu correo es:"+correo
                    + "<br>"
                    + "ip local: "+ip
                    + "<br>"
                    + "IP remota : "+ ipr
                    + "<br>"
                    + "Puerto Local :"+puerto
                    + "<br>"
                    + "Puerto Remoto"+ puertor);
            
           
            
            out.println("<h1>Registro Exitoso  </h1>"
                    //ya no podemos usar doble comilla asi que simple
            + "<a href='index.html'>Regresar Al menu</a>"
                    + "Hola te docisaron" + ipr);
            out.println("</body>");
            out.println("</html>");
            }catch(Exception e){
                
                System.out.println("Error al nacer");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
                
                out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Registro</title>");            
            out.println("</head>");
            out.println("<body>"
                    +"<br>");
                     out.println("<h1>Registro Malnacidoso ocurrio un aborto espontaneo </h1>"
                    //ya no podemos usar doble comilla asi que simple
            + "<a href='index.html'>Regresar Al menu principal</a>"
                    + "Hola te docisaron" + ipr);
            out.println("</body>");
            out.println("</html>");
            }
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}



