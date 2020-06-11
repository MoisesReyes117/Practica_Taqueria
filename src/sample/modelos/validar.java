package sample.modelos;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sample.vistas.login;
import sample.vistas.menuPrincipal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class validar implements EventHandler {
    private TextField txtUsuario;
    private TextField txtContraseña;
    private String user,pass;
    private Connection con;

    public validar (TextField txtUsuario, TextField txtContraseña){
        this.txtUsuario = txtUsuario;
        this.txtContraseña = txtContraseña;
        con = Conexion.con;

    }


    @Override
    public void handle(Event event) {
        user = this.txtUsuario.getText();
        pass = this.txtContraseña.getText();

        consulta();

    }

    public void consulta(){

        String query = "select * from empleado where usuario='"+user+"' and contraseña='"+pass+"';";

        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()){
                Alert objAlert2 = new Alert (Alert.AlertType.INFORMATION);
                objAlert2.setTitle("Acesso Permitido  ");
                objAlert2.setHeaderText("          Datos validos");
                objAlert2.setContentText("                    Bienvenido "+user+" ");
                objAlert2.showAndWait();
                new menuPrincipal();
            }else {
                Alert objAlert = new Alert (Alert.AlertType.WARNING);
                objAlert.setTitle("Acceso");
                objAlert.setHeaderText("          Error de acceso");
                objAlert.setContentText("Los datos que ingreso son erroneos, vuelva a intertarlo");
                objAlert.showAndWait();
                new login();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
