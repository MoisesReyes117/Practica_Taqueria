package sample.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class promoDAO {
    private int CvePromo;
    private String nombre;

    public int getCvePromo() { return CvePromo; }
    public void setCvePromo(int CvePromo) { this.CvePromo = CvePromo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    private promoDAO objPromo = null;

    public ObservableList<promoDAO> selAllPromos() {

        ObservableList<promoDAO> listPromo = FXCollections.observableArrayList();
        String query = "select * from promociones";
        try {

            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {

                objPromo = new promoDAO();
                objPromo.setCvePromo(res.getInt("CvePromo"));
                objPromo.setNombre(res.getString("nombre"));
                listPromo.add(objPromo);


            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return listPromo;
    }


    public void getProvByCve (){
        String query = "SELECT * FROM promociones where CvePromo="+CvePromo;
        try {
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()){
                nombre = res.getString("nombre");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return nombre ;
    }
}
