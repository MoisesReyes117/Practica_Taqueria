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

public class FrmBebidas extends Stage {
    private TableView<BebidasDAO> tbvBebida;
    private BebidasDAO ObjB;
    private VBox vbox;
    private TextField txtNombre,txtTam,txtprecio;
    private Button btnGuardar;
    private Scene escena;
    private Connection con;
    private String nombre;


    public FrmBebidas(TableView<BebidasDAO> tbvBebida, BebidasDAO obj){

        if (obj != null)
            this.ObjB = obj;
        else
            ObjB = new BebidasDAO();


        this.tbvBebida = tbvBebida;
        CrearGUI();
        con = Conexion.con;
        this.setTitle("Gestion de Bebidas");
        this.setScene(escena);
        this.show();
    }


    private void CrearGUI() {
        vbox = new VBox();
        txtTam = new TextField();
        txtprecio = new TextField();
        txtNombre = new TextField();
        nombre = ObjB.getNomBebida();
        txtNombre.setText(ObjB.getNomBebida());
        txtNombre.setPromptText("Introduce el nombre");
        txtTam.setText(ObjB.getTamaño());
        txtTam.setPromptText("Introduce el tamaño");
        txtprecio.setText(ObjB.getPrecio()+"");
        txtprecio.setPromptText("Introduce el precio");

        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> { guardarDatos(); });
        vbox.getChildren().addAll(txtNombre,txtTam,txtprecio,btnGuardar);
        escena = new Scene(vbox,350,250);
    }

    private void guardarDatos() {
        ObjB.setNomBebida(txtNombre.getText());
        ObjB.setTamaño(txtTam.getText());
        ObjB.setPrecio(Double.parseDouble(txtprecio.getText()));

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

        tbvBebida.setItems(ObjB.seeAllBebidas());
        tbvBebida.refresh();

        this.close();

    }
}
