package sample.modelos;

import java.sql.Connection;

import java.sql.Statement;

public class contenidoComidaDAO {

    private int CveOrden;
    private int CveProducto;
    private int cantidad;
    private double subtotal;


    public int getCveOrden() { return CveOrden; }
    public void setCveOrden(int CveOrden) { this.CveOrden = CveOrden; }
    public int getCveProducto() { return CveProducto; }
    public void setCveProducto(int CveProducto) { this.CveProducto = CveProducto; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    private Connection con;

    public contenidoComidaDAO(){
        con = Conexion.con;
    }

    public void insContenido(double subtotal,int clave,int cveProducto){
        this.subtotal = subtotal;

        String query = "insert into contieneComida(CveOrden,CveComida,cantidad,subtotal) values("+clave+","+cveProducto+","+cantidad+","+subtotal+"); ";
        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
