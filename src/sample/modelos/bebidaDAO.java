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
    private int CveProveedor,cantidad;


    public int getCveBebida() { return CveBebida; }
    public void setCveBebida(int CveBebida) { this.CveBebida = CveBebida; }
    public String getNomBebida() { return nomBebida; }
    public void setNomBebida(String nomBebida) { this.nomBebida = nomBebida; }
    public String getTamaño() { return tamaño; }
    public void setTamaño(String tamaño) { this.tamaño = tamaño; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public int getCveProveedor() { return CveProveedor; }
    public void setCveProveedor(int CveProveedor) { this.CveProveedor = CveProveedor; }



    private Connection con;
    public bebidaDAO(){
        con = Conexion.con;
    }

    public void insBebida(){

        String query = "insert into bebida" +
                "(nombre,tamaño,precio,cantidad,CveProveedor,CveTipoBebida) " +
                "values('"+nomBebida+"','"+tamaño+"',"+precio+","+cantidad+","+CveProveedor+",1)";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updBebida(){
        String query = "update bebida set nombre='"+nomBebida+"'" +
                ",tamaño='"+tamaño+"',precio="+precio+",cantidad="+cantidad+",CveProveedor="+CveProveedor+" where CveBebida="+CveBebida;
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
        String query = "select * from bebida where cantidad>0 order by nombre";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objB = new bebidaDAO();
                objB.setCveBebida(res.getInt("CveBebida"));
                objB.setNomBebida(res.getString("nombre"));
                objB.setTamaño(res.getString("tamaño"));
                objB.setPrecio(res.getDouble("precio"));
                objB.setCantidad(res.getInt("cantidad"));
                objB.setCveProveedor(res.getInt("CveProveedor"));
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
                precio = res.getDouble("precio");
                cantidad = res.getInt("cantidad");
                CveProveedor = res.getInt("CveProveedor");
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
