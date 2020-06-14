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
import sample.ButtonsCells.ButtonCellBebidas;
import sample.Formularios.FrmBebida;
import sample.modelos.bebidaDAO;

public class CRUDBebidas extends Stage {
    private Scene escena;
    private VBox vbox;
    private TableView<bebidaDAO> tbvBebida;
    private Button btnAgregar;
    private bebidaDAO objP;

    public CRUDBebidas(){
        objP = new bebidaDAO();
        CrearGUI();
        this.setTitle("Bebida");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        tbvBebida = new TableView<>();
        CrearTabla();
        btnAgregar = new Button("Agregar Bebida");
        btnAgregar.setOnAction(event -> AgregarBebida());
        vbox.getChildren().addAll(tbvBebida,btnAgregar);
        escena = new Scene(vbox,633,400);
    }

    private void AgregarBebida() {
        new FrmBebida(tbvBebida,null);
    }

    private void CrearTabla() {
        TableColumn<bebidaDAO,Integer> tbcIdBebida = new TableColumn<>("CLAVE");
        tbcIdBebida.setCellValueFactory(new PropertyValueFactory<>("CveBebida"));

        TableColumn<bebidaDAO,String> tbcNomBebida = new TableColumn<>("NOMBRE");
        tbcNomBebida.setCellValueFactory(new PropertyValueFactory<>("nomBebida"));

        TableColumn<bebidaDAO,String> tbcTamaño = new TableColumn<>("TAMAÑO");
        tbcTamaño.setCellValueFactory(new PropertyValueFactory<>("tamaño"));

        TableColumn<bebidaDAO,Double> tbcPrecio = new TableColumn<>("PRECIO");
        tbcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<bebidaDAO,Integer> tbcCantidad = new TableColumn<>("CANTIDAD");
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        TableColumn<bebidaDAO,Double> tbcProveedor = new TableColumn<>("CLAVE PROVEEDOR");
        tbcProveedor.setCellValueFactory(new PropertyValueFactory<>("CveProveedor"));


        TableColumn<bebidaDAO,String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<bebidaDAO, String>, TableCell<bebidaDAO, String>>() {
                    @Override
                    public TableCell<bebidaDAO, String> call(TableColumn<bebidaDAO, String> param) {
                        return new ButtonCellBebidas(2);
                    }
                }
        );

        TableColumn<bebidaDAO,String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<bebidaDAO, String>, TableCell<bebidaDAO, String>>() {
                    @Override
                    public TableCell<bebidaDAO, String> call(TableColumn<bebidaDAO, String> param) {
                        return new ButtonCellBebidas(1);
                    }
                }
        );

        tbvBebida.getColumns().addAll(tbcIdBebida,tbcNomBebida,tbcTamaño,tbcPrecio,tbcCantidad,tbcProveedor,tbcEditar,tbcBorrar);
        tbvBebida.setItems(objP.selAllBebidas());
    }

}