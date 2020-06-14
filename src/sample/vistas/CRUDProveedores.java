package sample.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.ButtonsCells.ButtonCellProveedor;
import sample.Formularios.FrmProveedor;
import sample.modelos.proveedorDAO;

public class CRUDProveedores extends Stage {
    private Scene escena;
    private VBox vbox;
    private TableView<proveedorDAO> tbvproveedores;
    private Button btnAgregar;
    private proveedorDAO objP;

    public CRUDProveedores(){
        objP = new proveedorDAO();
        CrearGUI();
        this.setTitle("Proveedores de la Taquería");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        tbvproveedores = new TableView<>();
        CrearTabla();
        btnAgregar = new Button("Agregar proveedor");
        btnAgregar.setOnAction(event -> Agregarproveedor());
        vbox.getChildren().addAll(tbvproveedores,btnAgregar);
        escena = new Scene(vbox,496,400);
    }

    private void Agregarproveedor() {
        new FrmProveedor(tbvproveedores,null);
    }

    private void CrearTabla() {
        TableColumn<proveedorDAO,Integer> tbcIdproveedor = new TableColumn<>("CLAVE");
        tbcIdproveedor.setCellValueFactory(new PropertyValueFactory<>("CveProveedor"));

        TableColumn<proveedorDAO,String> tbcNomproveedor = new TableColumn<>("NOMBRE");
        tbcNomproveedor.setCellValueFactory(new PropertyValueFactory<>("nomProveedor"));

        TableColumn<proveedorDAO,String> tbcCelular = new TableColumn<>("CELULAR");
        tbcCelular.setCellValueFactory(new PropertyValueFactory<>("celular"));

        TableColumn<proveedorDAO,String> tbcDireccion = new TableColumn<>("DIRECCION");
        tbcDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));


        TableColumn<proveedorDAO,String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<proveedorDAO, String>, TableCell<proveedorDAO, String>>() {
                    @Override
                    public TableCell<proveedorDAO, String> call(TableColumn<proveedorDAO, String> param) {
                        return new ButtonCellProveedor(2);
                    }
                }
        );

        TableColumn<proveedorDAO,String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<proveedorDAO, String>, TableCell<proveedorDAO, String>>() {
                    @Override
                    public TableCell<proveedorDAO, String> call(TableColumn<proveedorDAO, String> param) {
                        return new ButtonCellProveedor(1);
                    }
                }
        );


        tbvproveedores.getColumns().addAll(tbcIdproveedor,tbcNomproveedor,tbcCelular,tbcDireccion,tbcEditar,tbcBorrar);
        tbvproveedores.setItems(objP.selAllProveedor());
    }


}
