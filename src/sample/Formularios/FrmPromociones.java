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
import sample.modelos.PromocionesDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class FrmPromociones extends Stage {
    private TableView<PromocionesDAO> tbvPromocioness;
    private PromocionesDAO objP;
    private VBox vbox;
    private TextField txtCvePromo,txtNombre,txtDesc,txtporcentaje;
    private Button btnGuardar;
    private Scene escena;
    private Connection con;
    private String nombre;


    public FrmPromociones(TableView<PromocionesDAO> tbvPromocioness, PromocionesDAO obj){

        if (obj != null)
            this.objP = obj;
        else
            objP = new PromocionesDAO();


        this.tbvPromocioness = tbvPromocioness;
        CrearGUI();
        con = Conexion.con;
        this.setTitle("Gestion de Promocioness");
        this.setScene(escena);
        this.show();
    }


    private void CrearGUI() {
        vbox = new VBox();
        txtDesc = new TextField();
        txtporcentaje = new TextField();
        txtNombre = new TextField();
        txtCvePromo = new TextField();
        nombre = objP.getNomPromociones();
        txtCvePromo.setText(objP.getCvePromociones()+"");
        txtCvePromo.setPromptText("Introduce la clave");
        txtNombre.setText(objP.getNomPromociones());
        txtNombre.setPromptText("Introduce el nombre");
        txtDesc.setText(objP.getDescripcion());
        txtDesc.setPromptText("Introduce la descripción");
        txtporcentaje.setText(objP.getPorcentaje()+"");
        txtporcentaje.setPromptText("Introduce el porcentaje");

        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> { guardarDatos(); });
        vbox.getChildren().addAll(txtCvePromo,txtNombre,txtDesc,txtporcentaje,btnGuardar);
        escena = new Scene(vbox,350,250);
    }

    private void guardarDatos() {
        objP.setCvePromociones(Integer.parseInt(txtCvePromo.getText()));
        objP.setNomPromociones(txtNombre.getText());
        objP.setDescripcion(txtDesc.getText());
        objP.setPorcentaje(Integer.parseInt(txtporcentaje.getText()));

        String query = "select * from promociones where nombre='"+nombre+"' ;";

        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()){
                objP.updpromociones();
            }else{
                objP.inspromociones();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        tbvPromocioness.setItems(objP.seeAllPromociones());
        tbvPromocioness.refresh();

        this.close();

    }
}