package sample.modelos;

import java.sql.Connection;
import java.sql.Statement;

public class contieneBebidaDAO {

    private int CveOrden;
    private int CveBebida;
    private int cantidad;
    private double subtotal;


    public int getCveOrden() { return CveOrden; }
    public void setCveOrden(int CveOrden) { this.CveOrden = CveOrden; }
    public int getCveBebida() { return CveBebida; }
    public void setCveBebida(int CveBebida) { this.CveBebida = CveBebida; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    private Connection con;

    public contieneBebidaDAO(){ con = Conexion.con; }

    public void insContenido(double subtotal,int clave,int cveBebida){
        this.subtotal = subtotal;

        String query = "insert into contieneBebida(CveOrden,CveBebida,cantidad,subtotal) values("+clave+","+cveBebida+","+cantidad+","+subtotal+"); ";
        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
