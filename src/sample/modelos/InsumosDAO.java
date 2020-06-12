package sample.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class InsumosDAO {

    private int CveInsumo;
    private String nomInsumo;
    private double costo;
    private int existencia;
    private int CveProveedor;

    public int getCveInsumo() { return CveInsumo; }
    public void setCveInsumo(int idInsumo) { this.CveInsumo = idInsumo; }
    public String getNomInsumo() { return nomInsumo; }
    public void setNomInsumo(String nomInsumo) { this.nomInsumo = nomInsumo; }
    public double getCosto() { return costo; }
    public void setCosto(double costo) { this.costo = costo; }
    public int getExistencia() {
        return existencia;
    }
    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }
    public int getCveProveedor() {
        return CveProveedor;
    }
    public void setCveProveedor(int cveProveedor) {
        CveProveedor = cveProveedor;
    }

    private Connection con;
    public InsumosDAO(){
        con = Conexion.con;
    }

    public void insInsumo(){

        String query = "insert into insumo" +
                "(nombre,costo,CveProveedor) " +
                "values('"+nomInsumo+"',"+costo+", " + CveProveedor + ")";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updInsumo(){
        String query = "update insumo set nombre='"+nomInsumo+"'" +
                ",costo="+ costo + ", CveProveedor = " + CveProveedor + " where CveInsumo="+CveInsumo;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){ e.printStackTrace();}
    }

    public void delInsumo(){
        String query = "delete from insumo where CveInsumo="+CveInsumo;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){}
    }

    public ObservableList<InsumosDAO> seeAllInsumo(){

        ObservableList<InsumosDAO> listaP = FXCollections.observableArrayList();
        InsumosDAO objP = null;
        String query = "select * from insumo order by nombre";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objP = new InsumosDAO();
                objP.setCveInsumo(res.getInt("CveInsumo"));
                objP.setNomInsumo(res.getString("nombre"));
                objP.setCosto(res.getDouble("costo"));
                objP.setExistencia(res.getInt("existencia"));
                objP.setCveProveedor(res.getInt("CveProveedor"));
                listaP.add(objP);
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return listaP;
    }

    public void getInsByCve (){
        String query = "SELECT * FROM insumo where CveInsumo="+CveInsumo;
        try {
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()){
                nomInsumo = res.getString("nombre");
                costo = res.getDouble("costo");
                existencia = res.getInt("existencia");
                CveProveedor = res.getInt("CveInsumo");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return nomInsumo;
    }
}
