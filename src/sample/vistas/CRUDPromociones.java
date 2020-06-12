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
import sample.modelos.ButtonCellPromociones;
import sample.modelos.FrmPromociones;
import sample.modelos.PromocionesDAO;

public class CRUDPromociones extends Stage {
    private Scene escena;
    private VBox vbox;
    private TableView<PromocionesDAO> tbvPromociones;
    private Button btnAgregar;
    private PromocionesDAO objP;

    public CRUDPromociones(){
        objP = new PromocionesDAO();
        CrearGUI();
        this.setTitle("Promociones");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        tbvPromociones = new TableView<>();
        CrearTabla();
        btnAgregar = new Button("Agregar Promoción");
        btnAgregar.setOnAction(event -> AgregarProducto());
        vbox.getChildren().addAll(tbvPromociones,btnAgregar);
        escena = new Scene(vbox,700,400);
    }

    private void AgregarProducto() {
        new FrmPromociones(tbvPromociones,null);
    }

    private void CrearTabla() {
        TableColumn<PromocionesDAO,Integer> tbcIdPromo = new TableColumn<>("CLAVE");
        tbcIdPromo.setCellValueFactory(new PropertyValueFactory<>("CvePromociones"));

        TableColumn<PromocionesDAO,String> tbcNomProducto = new TableColumn<>("NOMBRE");
        tbcNomProducto.setCellValueFactory(new PropertyValueFactory<>("NomPromociones"));

        TableColumn<PromocionesDAO,String> tbcDescripcion = new TableColumn<>("DESCRIPCIÓN");
        tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        TableColumn<PromocionesDAO,Integer> tbcPorcentaje = new TableColumn<>("PORCENTAJE");
        tbcPorcentaje.setCellValueFactory(new PropertyValueFactory<>("porcentaje"));

        TableColumn<PromocionesDAO,String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<PromocionesDAO, String>, TableCell<PromocionesDAO, String>>() {
                    @Override
                    public TableCell<PromocionesDAO, String> call(TableColumn<PromocionesDAO, String> param) {
                        return new ButtonCellPromociones(2);
                    }
                }
        );

        TableColumn<PromocionesDAO,String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<PromocionesDAO, String>, TableCell<PromocionesDAO, String>>() {
                    @Override
                    public TableCell<PromocionesDAO, String> call(TableColumn<PromocionesDAO, String> param) {
                        return new ButtonCellPromociones(1);
                    }
                }
        );


        tbvPromociones.getColumns().addAll(tbcIdPromo,tbcNomProducto,tbcDescripcion,tbcPorcentaje,tbcEditar,tbcBorrar);
        tbvPromociones.setItems(objP.seeAllPromociones());
    }


}
