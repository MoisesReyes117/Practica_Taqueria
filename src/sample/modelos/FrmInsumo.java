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

public class FrmInsumo extends Stage {
    private TableView<InsumosDAO> tbvInsumos;
    private InsumosDAO objP;
    private VBox vbox;
    private TextField txtNombre,txtcosto,txtCveProveedor;
    private Button btnGuardar;
    private Scene escena;
    private Connection con;
    private String nombre;


    public FrmInsumo(TableView<InsumosDAO> tbvInsumos, InsumosDAO obj){

        if (obj != null)
            this.objP = obj;
        else
            objP = new InsumosDAO();


        this.tbvInsumos = tbvInsumos;
        CrearGUI();
        con = Conexion.con;
        this.setTitle("Gestion de Insumos");
        this.setScene(escena);
        this.show();
    }


    private void CrearGUI() {
        vbox = new VBox();
        txtcosto = new TextField();
        txtCveProveedor = new TextField();
        txtNombre = new TextField();
        nombre = objP.getNomInsumo();
        txtNombre.setText(objP.getNomInsumo());
        txtNombre.setPromptText("Introduce el nombre");
        txtcosto.setText(objP.getCosto()+"");
        txtcosto.setPromptText("Introduce el costo");
        txtCveProveedor.setText(objP.getCveProveedor()+"");
        txtCveProveedor.setPromptText("Introduce la clave del Proveedor");

        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> { guardarDatos(); });
        vbox.getChildren().addAll(txtNombre,txtcosto,txtCveProveedor,btnGuardar);
        escena = new Scene(vbox,350,250);
    }

    private void guardarDatos() {
        objP.setNomInsumo(txtNombre.getText());
        objP.setCosto(Double.parseDouble(txtcosto.getText()));
        objP.setCveProveedor(Integer.parseInt(txtCveProveedor.getText()));

        String query = "select * from insumo where nombre='"+nombre+"' ;";

        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()){
                objP.updInsumo();
            }else{
                objP.insInsumo();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        tbvInsumos.setItems(objP.seeAllInsumo());
        tbvInsumos.refresh();

        this.close();

    }
}
