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
import sample.modelos.InsumosDAO;
import sample.modelos.promoDAO;
import sample.modelos.proveedorDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class FrmInsumo extends Stage {

    private TableView<InsumosDAO> tbvInsumos;
    private InsumosDAO objP;
    private VBox vbox;
    private TextField txtNombre,txtcosto;
    private ComboBox cbxProveedor;
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
        txtNombre = new TextField();
        cbxProveedor = new ComboBox();
        nombre = objP.getNomInsumo();
        txtNombre.setText(objP.getNomInsumo());
        txtNombre.setPromptText("Introduce el nombre");
        txtcosto.setText(objP.getCosto()+"");
        txtcosto.setPromptText("Introduce el costo");

        cbxProveedor.setItems(new proveedorDAO().selAllProveedor());
        proveedorDAO objPro = new proveedorDAO();
        objPro.setCveProveedor(objP.getCveProveedor());
        objPro.getProvByCve();
        cbxProveedor.setValue(objPro);


        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> { guardarDatos(); });
        vbox.getChildren().addAll(txtNombre,txtcosto,cbxProveedor,btnGuardar);
        escena = new Scene(vbox,350,250);
    }

    private void guardarDatos() {

        objP.setNomInsumo(txtNombre.getText());
        objP.setCosto(Double.parseDouble(txtcosto.getText()));

        proveedorDAO obj1 = (proveedorDAO) cbxProveedor.getValue();
        objP.setCveProveedor(obj1.getCveProveedor());

        String query = "select * from insumo where nombre='"+nombre+"';";

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