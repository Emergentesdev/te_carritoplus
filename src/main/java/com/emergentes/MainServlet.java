package com.emergentes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = request.getParameter("op");

        if (op.equals("vaciar")) {
            // Vaciar carrito
            HttpSession ses = request.getSession();
            ses.invalidate();
        }
        if (op.equals("eliminar")){
            int id = Integer.parseInt(request.getParameter("id"));
            
            // Eliminar el producto
            HttpSession ses = request.getSession();
            ArrayList<Producto> lista = (ArrayList<Producto>)ses.getAttribute("almacen");
            
            Producto auxi = new Producto();
            boolean hay = false;
            for(Producto p : lista){
                if (p.getId() == id){
                    hay = true;
                    auxi = p;
                    break;
                }                
            }
            if (hay) lista.remove(auxi);
        }
        response.sendRedirect("index.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String producto = request.getParameter("producto");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        double precio = Double.parseDouble(request.getParameter("precio"));

        Producto prod = new Producto();

        prod.setId(id);
        prod.setProducto(producto);
        prod.setCantidad(cantidad);
        prod.setPrecio(precio);

        HttpSession ses = request.getSession();

        ArrayList<Producto> lista = (ArrayList<Producto>) ses.getAttribute("almacen");

        lista.add(prod);

        response.sendRedirect("index.jsp");

    }

}
