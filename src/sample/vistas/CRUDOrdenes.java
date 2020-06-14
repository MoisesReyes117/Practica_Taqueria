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
import sample.ButtonsCells.ButtonCellOrdenes;
import sample.Formularios.FrmOrden;
import sample.modelos.ordenDAO;

import java.util.Date;

public class CRUDOrdenes extends Stage {
    private Scene escena;
    private VBox vbox;
    private TableView<ordenDAO> tbvOrdenes;
    private Button btnAgregar;
    private ordenDAO objO;

    public CRUDOrdenes(){
        objO = new ordenDAO();
        CrearGUI();
        this.setTitle("Ordenes activas");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        tbvOrdenes = new TableView<>();
        CrearTabla();
        btnAgregar = new Button("Agregar Orden");
        btnAgregar.setOnAction(event -> AgregarProducto());
        vbox.getChildren().addAll(tbvOrdenes,btnAgregar);
        escena = new Scene(vbox,410,310);
    }

    private void AgregarProducto() {
        new FrmOrden(tbvOrdenes,null);
    }

    private void CrearTabla() {
        TableColumn<ordenDAO,Integer> tbcCveOrden = new TableColumn<>("CLAVE");
        tbcCveOrden.setCellValueFactory(new PropertyValueFactory<>("CveOrden"));

        TableColumn<ordenDAO,Double> tbcTotal = new TableColumn<>("TOTAL");
        tbcTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        TableColumn<ordenDAO, Date> tbcFecha = new TableColumn<>("FECHA");
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        TableColumn<ordenDAO,Integer> tbcMesero = new TableColumn<>("MESERO");
        tbcMesero.setCellValueFactory(new PropertyValueFactory<>("CveMesero"));

        TableColumn<ordenDAO,Integer> tbcPromo = new TableColumn<>("PROMOCIÓN");
        tbcPromo.setCellValueFactory(new PropertyValueFactory<>("CvePromo"));



        TableColumn<ordenDAO,String> tbcPagar = new TableColumn<>("Pagar");
        tbcPagar.setCellFactory(
                new Callback<TableColumn<ordenDAO, String>, TableCell<ordenDAO, String>>() {
                    @Override
                    public TableCell<ordenDAO, String> call(TableColumn<ordenDAO, String> param) {
                        return new ButtonCellOrdenes(1);
                    }
                }
        );


        tbvOrdenes.getColumns().addAll(tbcCveOrden,tbcTotal,tbcFecha,tbcMesero,tbcPromo,tbcPagar);
        tbvOrdenes.setItems(objO.selAllordenesActivas());
    }
}
