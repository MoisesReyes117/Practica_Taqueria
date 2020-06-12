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

public class FrmProducto extends Stage {
    private TableView<productoDAO> tbvProductos;
    private productoDAO objP;
    private VBox vbox;
    private TextField txtNombre,txtDesc,txtprecio;
    private Button btnGuardar;
    private Scene escena;
    private Connection con;
    private String nombre;


    public FrmProducto(TableView<productoDAO> tbvProductos, productoDAO obj){

        if (obj != null)
            this.objP = obj;
        else
            objP = new productoDAO();


        this.tbvProductos = tbvProductos;
        CrearGUI();
        con = Conexion.con;
        this.setTitle("Gestion de productos");
        this.setScene(escena);
        this.show();
    }


    private void CrearGUI() {
        vbox = new VBox();
        txtDesc = new TextField();
        txtprecio = new TextField();
        txtNombre = new TextField();
        nombre = objP.getNomProducto();
        txtNombre.setText(objP.getNomProducto());
        txtNombre.setPromptText("Introduce el nombre");
        txtDesc.setText(objP.getDescripcion());
        txtDesc.setPromptText("Introduce la descripción");
        txtprecio.setText(objP.getPrecio()+"");
        txtprecio.setPromptText("Introduce el precio");

        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> { guardarDatos(); });
        vbox.getChildren().addAll(txtNombre,txtDesc,txtprecio,btnGuardar);
        escena = new Scene(vbox,350,250);
    }

    private void guardarDatos() {
        objP.setNomProducto(txtNombre.getText());
        objP.setDescripcion(txtDesc.getText());
        objP.setPrecio(Double.parseDouble(txtprecio.getText()));

        String query = "select * from producto where nombre='"+nombre+"' ;";

        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()){
                objP.updProducto();
            }else{
                objP.insProducto();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        tbvProductos.setItems(objP.selAllProducto());
        tbvProductos.refresh();

        this.close();

    }
}
