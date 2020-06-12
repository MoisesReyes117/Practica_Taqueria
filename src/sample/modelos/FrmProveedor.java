package sample.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class FrmProveedor extends Stage {
    private TableView<proveedorDAO> tbvProveedores;
    private proveedorDAO objP;
    private VBox vbox;
    private TextField txtNombre,txtCel,txtDir;
    private Button btnGuardar;
    private Scene escena;
    private Connection con;
    private String nombre;


    public FrmProveedor(TableView<proveedorDAO> tbvProveedores, proveedorDAO obj){

        if (obj != null)
            this.objP = obj;
        else
            objP = new proveedorDAO();


        this.tbvProveedores = tbvProveedores;
        CrearGUI();
        con = Conexion.con;
        this.setTitle("Gestion de Proveedores");
        this.setScene(escena);
        this.show();
    }


    private void CrearGUI() {
        vbox = new VBox();
        txtCel = new TextField();
        txtDir = new TextField();
        txtNombre = new TextField();
        nombre = objP.getNomProveedor();
        txtNombre.setText(objP.getNomProveedor());
        txtNombre.setPromptText("Introduce el nombre");
        txtCel.setText(objP.getCelular());
        txtCel.setPromptText("Introduce el número celular");
        txtDir.setText(objP.getDireccion());
        txtDir.setPromptText("Introduce la dirección");

        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> { guardarDatos(); });
        vbox.getChildren().addAll(txtNombre,txtCel,txtDir,btnGuardar);
        escena = new Scene(vbox,350,250);
    }

    private void guardarDatos() {

        objP.setNomProveedor(txtNombre.getText());
        objP.setCelular(txtCel.getText());
        objP.setDireccion(txtDir.getText());

        String query = "select * from Proveedor where nombre='"+nombre+"'; ";

        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()){
                objP.updproveedor();
            }else{
                objP.insproveedor();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        tbvProveedores.setItems(objP.selAllProveedor());
        tbvProveedores.refresh();

        this.close();

    }
}
