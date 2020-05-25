package sample.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class productoDAO {

    private int CveProducto;
    private String nomProducto;
    private String descripcion;
    private double precio;

    public int getCveProducto() { return CveProducto; }
    public void setCveProducto(int idProducto) { this.CveProducto = idProducto; }
    public String getNomProducto() { return nomProducto; }
    public void setNomProducto(String nomProducto) { this.nomProducto = nomProducto; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }



    private Connection con;
    public productoDAO(){
        con = Conexion.con;
    }

    public void insProducto(){

        String query = "insert into producto" +
                "(nombre,descripcion,precio) " +
                "values('"+nomProducto+"','"+descripcion+"',"+precio+")";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updProducto(){
        String query = "update producto set nombre='"+nomProducto+"'" +
                ",descripcion='"+descripcion+"',precio="+precio+" where CveProducto="+CveProducto;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){ e.printStackTrace();}
    }

    public void delProducto(){
        String query = "delete from producto where CveProducto="+CveProducto;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){}
    }

    public ObservableList<productoDAO> selAllProducto(){

        ObservableList<productoDAO> listaP = FXCollections.observableArrayList();
        productoDAO objP = null;
        String query = "select * from producto order by nombre";
        //String query2 = "select insumo.nombre from insumo,producto,compone where producto.CveProducto=compone.Cveproducto and compone.CveInsumo=insumo.CveInsumo  order by nombre";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objP = new productoDAO();
                objP.setCveProducto(res.getInt("CveProducto"));
                objP.setNomProducto(res.getString("nombre"));
                objP.setDescripcion(res.getString("descripcion"));
                objP.setPrecio(res.getDouble("precio"));
                listaP.add(objP);
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return listaP;
    }
}
