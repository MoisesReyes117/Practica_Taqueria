package sample.vistas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.modelos.validar;

public class login extends Stage {

    private Scene escena;
    private HBox hbox1,hbox2,hbox3,hboxs;
    private VBox vbox;
    private Button btnValidar;
    private Label lblUsuario, lblContraseña;
    private TextField txtUsuario;
    private PasswordField txtContraseña;

    public login(){
        CrearGUI();
        this.setTitle("Identificate");
        this.setScene(escena);
        this.setMaximized(true);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        hbox1 = new HBox();
        hbox2 = new HBox();
        hbox3 = new HBox();
        hboxs = new HBox();
        lblUsuario= new Label("    Usuario    ");
        lblUsuario.setId("texto");
        lblUsuario.setTextFill(Color.WHITE);
        lblContraseña = new Label(" Contraseña ");
        lblContraseña.setId("texto");
        lblContraseña.setTextFill(Color.WHITE);
        btnValidar = new Button(" Accesar ");
        btnValidar.setId("boton");
        btnValidar.setTextFill(Color.BLACK);
        txtUsuario = new TextField();
        txtContraseña = new PasswordField();
        btnValidar.addEventHandler(MouseEvent.MOUSE_CLICKED, new validar(txtUsuario,txtContraseña));

        hbox1.getChildren().addAll(lblUsuario,txtUsuario);
        hbox2.getChildren().addAll(lblContraseña,txtContraseña);
        hbox3.getChildren().add(btnValidar);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.setSpacing(5);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.setSpacing(5);
        hbox3.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(hbox1,hbox2,hbox3);
        vbox.setSpacing(20.0);
        vbox.setAlignment(Pos.CENTER);
        escena = new Scene(vbox);
        escena.getStylesheets().add("sample/estilos/estilo_login.css");


    }




}
