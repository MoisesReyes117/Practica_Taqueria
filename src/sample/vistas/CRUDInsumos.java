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
import sample.modelos.ButtonCellInsumos;
import sample.modelos.FrmInsumo;
import sample.modelos.InsumosDAO;

public class CRUDInsumos extends Stage {
    private Scene escena;
    private VBox vbox;
    private TableView<InsumosDAO> tbvInsumos;
    private Button btnAgregar;
    private InsumosDAO objP;

    public CRUDInsumos(){
        objP = new InsumosDAO();
        CrearGUI();
        this.setTitle("Insumos");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        tbvInsumos = new TableView<>();
        CrearTabla();
        btnAgregar = new Button("Agregar Insumo");
        btnAgregar.setOnAction(event -> AgregarInsumo());
        vbox.getChildren().addAll(tbvInsumos,btnAgregar);
        escena = new Scene(vbox,555,400);
    }

    private void AgregarInsumo() {
        new FrmInsumo(tbvInsumos,null);
    }

    private void CrearTabla() {
        TableColumn<InsumosDAO,Integer> tbcIdInsumo = new TableColumn<>("CLAVE");
        tbcIdInsumo.setCellValueFactory(new PropertyValueFactory<>("CveInsumo"));

        TableColumn<InsumosDAO,String> tbcNomInsumo = new TableColumn<>("NOMBRE");
        tbcNomInsumo.setCellValueFactory(new PropertyValueFactory<>("nomInsumo"));

        TableColumn<InsumosDAO,Double> tbcCosto = new TableColumn<>("COSTO");
        tbcCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));

        TableColumn<InsumosDAO,Integer> tbcExistencia = new TableColumn<>("EXISTENCIA");
        tbcExistencia.setCellValueFactory(new PropertyValueFactory<>("existencia"));

        TableColumn<InsumosDAO,Integer> tbcCveProvedor = new TableColumn<>("PROVEEDOR");
        tbcCveProvedor.setCellValueFactory(new PropertyValueFactory<>("CveProveedor"));

        TableColumn<InsumosDAO,String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<InsumosDAO, String>, TableCell<InsumosDAO, String>>() {
                    @Override
                    public TableCell<InsumosDAO, String> call(TableColumn<InsumosDAO, String> param) {
                        return new ButtonCellInsumos(1);
                    }
                }
        );

        tbvInsumos.getColumns().addAll(tbcIdInsumo,tbcNomInsumo,tbcCosto,tbcExistencia,tbcCveProvedor,tbcEditar);
        tbvInsumos.setItems(objP.seeAllInsumo());
    }


}
