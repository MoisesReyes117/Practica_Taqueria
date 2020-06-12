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
import sample.modelos.ButtonCellAlimentos;
import sample.modelos.ButtonCellBebidas;
import sample.modelos.FrmBebidas;
import sample.modelos.BebidasDAO;

public class CRUDBebidas extends Stage {
    private Scene escena;
    private VBox vbox;
    private TableView<BebidasDAO> tbvBebida;
    private Button btnAgregar;
    private BebidasDAO objP;

    public CRUDBebidas(){
        objP = new BebidasDAO();
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
        escena = new Scene(vbox,555,400);
    }

    private void AgregarBebida() {
        new FrmBebidas(tbvBebida,null);
    }

    private void CrearTabla() {
        TableColumn<BebidasDAO,Integer> tbcIdBebida = new TableColumn<>("CLAVE");
        tbcIdBebida.setCellValueFactory(new PropertyValueFactory<>("CveBebida"));

        TableColumn<BebidasDAO,String> tbcNomBebida = new TableColumn<>("NOMBRE");
        tbcNomBebida.setCellValueFactory(new PropertyValueFactory<>("nomBebida"));

        TableColumn<BebidasDAO,String> tbcTamaño = new TableColumn<>("TAMAÑO");
        tbcTamaño.setCellValueFactory(new PropertyValueFactory<>("tamaño"));

        TableColumn<BebidasDAO,Double> tbcPrecio = new TableColumn<>("PRECIO");
        tbcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));


        TableColumn<BebidasDAO,String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<BebidasDAO, String>, TableCell<BebidasDAO, String>>() {
                    @Override
                    public TableCell<BebidasDAO, String> call(TableColumn<BebidasDAO, String> param) {
                        return new ButtonCellBebidas(2);
                    }
                }
        );

        TableColumn<BebidasDAO,String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<BebidasDAO, String>, TableCell<BebidasDAO, String>>() {
                    @Override
                    public TableCell<BebidasDAO, String> call(TableColumn<BebidasDAO, String> param) {
                        return new ButtonCellBebidas(1);
                    }
                }
        );

        tbvBebida.getColumns().addAll(tbcIdBebida,tbcNomBebida,tbcTamaño,tbcPrecio,tbcEditar,tbcBorrar);
        tbvBebida.setItems(objP.seeAllBebidas());
    }

}
