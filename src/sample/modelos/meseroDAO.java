package sample.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class meseroDAO {

    private int CveEmpleado;
    private String nombre;
    private String usuario;
    private String contraseña;
    private int CveTipo;

    public int getCveEmpleado() { return CveEmpleado; }
    public void setCveEmpleado(int CveEmpledo) { this.CveEmpleado = CveEmpledo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }
    public int getCveTipo() { return CveTipo; }
    public void setCveTipo(int CveTipo) { this.CveTipo = CveTipo; }
    private meseroDAO objMe = null;

    public ObservableList<meseroDAO> selAllMeseros() {

        ObservableList<meseroDAO> listMe = FXCollections.observableArrayList();
        String query = "SELECT * FROM empleado where CveTipoEmpleado=2 ORDER BY nombre";
        try {

            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {

                objMe = new meseroDAO();
                objMe.setCveEmpleado(res.getInt("CveEmpleado"));
                objMe.setNombre(res.getString("nombre"));
                objMe.setUsuario(res.getString("usuario"));
                objMe.setContraseña(res.getString("contraseña"));
                objMe.setCveTipo(res.getInt("CveTipoEmpleado"));
                listMe.add(objMe);


            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return listMe;
    }


    public void getProvByCve (){
        String query = "SELECT * FROM empleado where CveEmpleado="+CveEmpleado;
        try {
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()){
                nombre = res.getString("nombre");
                usuario = res.getString("usuario");
                contraseña = res.getString("contraseña");
                CveTipo = res.getInt("CveTipoEmpleado");
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
