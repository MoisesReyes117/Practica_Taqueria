package sample.modelos;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PromocionesDAO {

    private int Cvepromociones;
    private String nompromociones;
    private String descripcion;
    private int porcentaje;

    public int getCvePromociones() { return Cvepromociones; }
    public void setCvePromociones(int idpromociones) { this.Cvepromociones = idpromociones; }
    public String getNomPromociones() { return nompromociones; }
    public void setNomPromociones(String nompromociones) { this.nompromociones = nompromociones; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setPorcentaje(int porcentaje) { this.porcentaje = porcentaje; }
    public int getPorcentaje() {
        return porcentaje;
    }

    private Connection con;
    public PromocionesDAO(){
        con = Conexion.con;
    }

    public void inspromociones(){

        String query = "insert into promociones" +
                "(CvePromo,nombre,descripción,porcentaje) " +
                "values("+Cvepromociones+",'"+nompromociones+"','"+descripcion+"',"+porcentaje+")";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updpromociones(){
        String query = "update promociones set CvePromo="+Cvepromociones+",nombre='"+nompromociones+"'" +
                ",descripción='"+descripcion+"',porcentaje="+porcentaje+" where CvePromo="+Cvepromociones;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){ e.printStackTrace();}
    }

    public void delpromociones(){
        String query = "delete from promociones where CvePromo="+Cvepromociones;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){}
    }

    public ObservableList<PromocionesDAO> seeAllPromociones(){

        ObservableList<PromocionesDAO> listaP = FXCollections.observableArrayList();
        PromocionesDAO objP = null;
        String query = "select * from promociones order by nombre";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objP = new PromocionesDAO();
                objP.setCvePromociones(res.getInt("CvePromo"));
                objP.setNomPromociones(res.getString("nombre"));
                objP.setDescripcion(res.getString("descripción"));
                objP.setPorcentaje(res.getInt("porcentaje"));
                listaP.add(objP);
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return listaP;
    }

    public void getProvByCve (){
        String query = "SELECT * FROM promociones where CvePromo="+Cvepromociones;
        try {
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()){
                nompromociones = res.getString("nombre");
                descripcion = res.getString("descripción");
                porcentaje = res.getInt("porcentaje");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return nompromociones;
    }
}