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
import sample.modelos.ButtonCellOrdenes;
import sample.modelos.FrmOrden;
import sample.modelos.ordenDAO;

import java.util.Date;

public class verHistorialOrdenes extends Stage {

    private Scene escena;
    private VBox vbox;
    private TableView<ordenDAO> tbvOrdenes;
    private ordenDAO objO;

    public verHistorialOrdenes(){
        objO = new ordenDAO();
        CrearGUI();
        this.setTitle("Historial de Ordenes");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        tbvOrdenes = new TableView<>();
        CrearTabla();
        vbox.getChildren().addAll(tbvOrdenes);
        escena = new Scene(vbox,350,310);
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




        tbvOrdenes.getColumns().addAll(tbcCveOrden,tbcTotal,tbcFecha,tbcMesero,tbcPromo);
        tbvOrdenes.setCenterShape(true);
        tbvOrdenes.setItems(objO.selAllordenesPagadas());
    }
}
