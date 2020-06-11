package sample.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class costoEntregaDAO {

    private int CveCosto;
    private String nombre;
    private double costo;


    public int getCveCosto() { return CveCosto; }
    public void setCveCosto(int CveCosto) { this.CveCosto = CveCosto; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getCosto() { return costo; }
    public void setCosto(double costo) { this.costo = costo; }


    private Connection con;
    public costoEntregaDAO(){
        con = Conexion.con;
    }

    /*
    public void insCosto(){

        String query = "insert into bebida" +
                "(nombre,tamaño,precio,CveTipoBebida) " +
                "values('"+nomBebida+"','"+tamaño+"',"+precio+","+CveTipoBebida+")";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updBebida(){
        String query = "update bebida set nombre='"+nomBebida+"'" +
                ",tamaño='"+tamaño+"',precio="+precio+",CveTipoBebida="+CveTipoBebida+" where CveBebida="+CveBebida;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){ e.printStackTrace();}
    }

    public void delBebida(){
        String query = "delete from bebida where CveBebida="+CveBebida;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){}
    }

     */

    public ObservableList<costoEntregaDAO> selAllCostos(){

        ObservableList<costoEntregaDAO> listaC = FXCollections.observableArrayList();
        costoEntregaDAO objC = null;
        String query = "select * from costoentrega order by nombre";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objC = new costoEntregaDAO();
                objC.setCveCosto(res.getInt("CveCosto"));
                objC.setNombre(res.getString("nombre"));
                objC.setCosto(res.getDouble("costo"));
                listaC.add(objC);
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return listaC;
    }

    public void getProvByCve (){
        String query = "SELECT * FROM costoentrega where CveCosto="+CveCosto;
        try {
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()){
                nombre = res.getString("nombre");
                costo = res.getDouble("precio");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return nombre;
    }
}
