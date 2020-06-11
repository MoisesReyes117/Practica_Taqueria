package sample.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class ordenDAO  {

    private int CveOrden;
    private Double total ;
    private Date fecha;
    private int CveMesero;
    private int CvePromo;

    public int getCveOrden() { return CveOrden; }
    public void setCveOrden(int CveOrden) { this.CveOrden = CveOrden; }
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public int getCveMesero() { return CveMesero; }
    public void setCveMesero(int CveMesero) { this.CveMesero = CveMesero; }
    public int getCvePromo() { return CvePromo; }
    public void setCvePromo(int CvePromo) { this.CvePromo = CvePromo; }

    private Connection con;
    public ordenDAO(){
        con = Conexion.con;
    }

    public void insOrden(){

        String query = "insert into orden " +
                "(fecha,CveEmpleado,CvePromo,pagada) values('"+fecha+"',"+CveMesero+","+CvePromo+",0)";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updOrden(){
        String query = "update orden set" +
                " fecha='"+fecha+"',CvePromo="+CvePromo+",CveEmpleado="+CveMesero+" where CveOrden="+CveOrden+"";
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){ e.printStackTrace();}
    }
    public void upTotal(double total){
        String query = "update orden set total="+total+" where total IS NULL";
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){ e.printStackTrace();}
    }

    public void upPagada(int clave){

        String query = "update orden set pagada=1 where CveOrden="+clave+";";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ObservableList<ordenDAO> selAllordenesActivas(){

        ObservableList<ordenDAO> listaO = FXCollections.observableArrayList();
        ordenDAO objO = null;
        String query = "select * from orden where pagada=0";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objO = new ordenDAO();
                objO.setCveOrden(res.getInt("CveOrden"));
                objO.setTotal(res.getDouble("total"));
                objO.setFecha(res.getDate("fecha"));
                objO.setCveMesero(res.getInt("CveEmpleado"));
                objO.setCvePromo(res.getInt("CvePromo"));
                listaO.add(objO);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return listaO;
    }

    public ObservableList<ordenDAO> selAllordenesPagadas(){

        ObservableList<ordenDAO> listaO = FXCollections.observableArrayList();
        ordenDAO objO = null;
        String query = "select * from orden where pagada=1";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objO = new ordenDAO();
                objO.setCveOrden(res.getInt("CveOrden"));
                objO.setTotal(res.getDouble("total"));
                objO.setFecha(res.getDate("fecha"));
                objO.setCveMesero(res.getInt("CveEmpleado"));
                objO.setCvePromo(res.getInt("CvePromo"));
                listaO.add(objO);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return listaO;
    }

}
