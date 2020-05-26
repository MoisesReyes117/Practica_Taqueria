package sample.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class proveedorDAO {

    private int CveProveedor;
    private String nomProveedor;
    private String celular;
    private String direccion;

    public int getCveProveedor() { return CveProveedor; }
    public void setCveProveedor(int idproveedor) { this.CveProveedor = idproveedor; }
    public String getNomProveedor() { return nomProveedor; }
    public void setNomProveedor(String nomProveedor) { this.nomProveedor = nomProveedor; }
    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    private Connection con;
    public proveedorDAO(){
        con = Conexion.con;
    }

    public void insproveedor(){

        String query = "insert into proveedor" +
                       "(nombre,celular,direccion) " +
                       "values('"+nomProveedor+"',"+celular+",'"+direccion+"')";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updproveedor(){
        String query = "update proveedor set nombre='"+nomProveedor+"'" +
                ",celular="+celular+",direccion='"+direccion+"' where CveProveedor="+CveProveedor;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){ e.printStackTrace();}
    }

    public void delProveedor(){
        String query = "delete from proveedor where CveProveedor="+CveProveedor;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){}
    }

    public ObservableList<proveedorDAO> selAllProveedor(){

        ObservableList<proveedorDAO> listaP = FXCollections.observableArrayList();
        proveedorDAO objP = null;
        String query = "select * from proveedor order by CveProveedor";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objP = new proveedorDAO();
                objP.setCveProveedor(res.getInt("CveProveedor"));
                objP.setNomProveedor(res.getString("nombre"));
                objP.setCelular(res.getString("celular"));
                objP.setDireccion(res.getString("direccion"));
                listaP.add(objP);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return listaP;
    }
}
