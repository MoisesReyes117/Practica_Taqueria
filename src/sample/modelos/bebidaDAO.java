package sample.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class bebidaDAO {

    private int CveBebida;
    private String nomBebida;
    private String tamaño;
    private double precio;
    private int CveTipoBebida;
    private boolean existencia;

    public int getCveBebida() { return CveBebida; }
    public void setCveBebida(int CveBebida) { this.CveBebida = CveBebida; }
    public String getNomBebida() { return nomBebida; }
    public void setNomBebida(String nomBebida) { this.nomBebida = nomBebida; }
    public String getTamaño() { return tamaño; }
    public void setTamaño(String tamaño) { this.tamaño = tamaño; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public int getCveTipoBebida() { return CveTipoBebida; }
    public void setCveTipoBebida(int CveTipoBebida) { this.CveTipoBebida = CveTipoBebida; }
    public boolean getExistencia(){return existencia;}
    public void setExistencia(boolean existencia){this.existencia = existencia;}


    private Connection con;
    public bebidaDAO(){
        con = Conexion.con;
    }

    public void insBebida(){

        String query = "insert into bebida" +
                "(nombre,tamaño,precio,CveTipoBebida,existencia) " +
                "values('"+nomBebida+"','"+tamaño+"',"+precio+","+CveTipoBebida+","+existencia+")";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updBebida(){
        String query = "update bebida set nombre='"+nomBebida+"'" +
                ",tamaño='"+tamaño+"',precio="+precio+",CveTipoBebida="+CveTipoBebida+",existencia="+existencia+" where CveBebida="+CveBebida;
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

    public ObservableList<bebidaDAO> selAllBebidas(){

        ObservableList<bebidaDAO> listaB = FXCollections.observableArrayList();
        bebidaDAO objB = null;
        String query = "select * from bebida order by nombre";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objB = new bebidaDAO();
                objB.setCveBebida(res.getInt("CveBebida"));
                objB.setNomBebida(res.getString("nombre"));
                objB.setTamaño(res.getString("tamaño"));
                objB.setPrecio(res.getDouble("precio"));
                objB.setCveTipoBebida(res.getInt("CveTipoBebida"));
                objB.setExistencia(res.getBoolean("existencia"));
                listaB.add(objB);
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return listaB;
    }

    public void getProvByCve (){
        String query = "SELECT * FROM bebida where CveBebida="+CveBebida;
        try {
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()){
                nomBebida = res.getString("nombre");
                tamaño = res.getString("tamaño");
                precio = res.getInt("precio");
                CveTipoBebida = res.getInt("CveTipoBebida");
                existencia = res.getBoolean("existencia");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return nomBebida;
    }
}
