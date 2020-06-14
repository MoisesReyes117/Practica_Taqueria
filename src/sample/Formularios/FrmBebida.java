package sample.Formularios;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.modelos.Conexion;
import sample.modelos.bebidaDAO;
import sample.modelos.proveedorDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class FrmBebida extends Stage {
    private TableView<bebidaDAO> tbvBebida;
    private bebidaDAO ObjB;
    private VBox vbox;
    private TextField txtNombre,txtTam,txtprecio,txtCantidad;
    private ComboBox cbxProveedor;
    private Button btnGuardar;
    private Scene escena;
    private Connection con;
    private String nombre;


    public FrmBebida(TableView<bebidaDAO> tbvBebida, bebidaDAO obj){

        if (obj != null)
            this.ObjB = obj;
        else
            ObjB = new bebidaDAO();


        this.tbvBebida = tbvBebida;
        CrearGUI();
        con =  Conexion.con;
        this.setTitle("Gestion de Bebidas");
        this.setScene(escena);
        this.show();
    }


    private void CrearGUI() {
        vbox = new VBox();
        txtTam = new TextField();
        txtprecio = new TextField();
        txtNombre = new TextField();
        txtCantidad = new TextField();
        nombre = ObjB.getNomBebida();
        txtNombre.setText(ObjB.getNomBebida());
        txtNombre.setPromptText("Introduce el nombre");
        txtTam.setText(ObjB.getTamaño());
        txtTam.setPromptText("Introduce el tamaño");
        txtprecio.setText(ObjB.getPrecio()+"");
        txtprecio.setPromptText("Introduce el precio");
        txtCantidad.setText(ObjB.getCantidad()+"");
        txtCantidad.setPromptText("Introduce la cantidad");
        cbxProveedor = new ComboBox();
        cbxProveedor.setItems(new proveedorDAO().selAllProveedor());
        proveedorDAO objPro = new proveedorDAO();
        objPro.setCveProveedor(ObjB.getCveProveedor());
        objPro.getProvByCve();
        cbxProveedor.setValue(objPro);

        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> { guardarDatos(); });
        vbox.getChildren().addAll(txtNombre,txtTam,txtprecio,txtCantidad,cbxProveedor,btnGuardar);
        escena = new Scene(vbox,350,250);
    }

    private void guardarDatos() {
        ObjB.setNomBebida(txtNombre.getText());
        ObjB.setTamaño(txtTam.getText());
        ObjB.setPrecio(Double.parseDouble(txtprecio.getText()));
        ObjB.setCantidad(Integer.parseInt(txtCantidad.getText()));

        proveedorDAO obj1 = (proveedorDAO) cbxProveedor.getValue();
        ObjB.setCveProveedor(obj1.getCveProveedor());

        String query = "select * from bebida where nombre='"+nombre+"' ;";

        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()){
                ObjB.updBebida();
            }else{
                ObjB.insBebida();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        tbvBebida.setItems(ObjB.selAllBebidas());
        tbvBebida.refresh();

        this.close();

    }
}