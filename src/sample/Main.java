package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.modelos.Conexion;
import sample.vistas.login;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Conexion.CrearConexion();
        new login();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

