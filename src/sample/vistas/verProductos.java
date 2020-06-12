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
import sample.modelos.FrmProducto;
import sample.modelos.productoDAO;

public class verProductos extends Stage {
    private Scene escena;
    private VBox vbox;
    private TableView<productoDAO> tbvProductos;
    private Button btnAgregar;
    private productoDAO objP;

    public verProductos(){
        objP = new productoDAO();
        CrearGUI();
        this.setTitle("Productos");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        tbvProductos = new TableView<>();
        CrearTabla();
        btnAgregar = new Button("Agregar Producto");
        btnAgregar.setOnAction(event -> AgregarProducto());
        vbox.getChildren().addAll(tbvProductos,btnAgregar);
        escena = new Scene(vbox,555,400);
    }

    private void AgregarProducto() {
        new FrmProducto(tbvProductos,null);
    }

    private void CrearTabla() {
        TableColumn<productoDAO,Integer> tbcIdProducto = new TableColumn<>("CLAVE");
        tbcIdProducto.setCellValueFactory(new PropertyValueFactory<>("CveProducto"));

        TableColumn<productoDAO,String> tbcNomProducto = new TableColumn<>("NOMBRE");
        tbcNomProducto.setCellValueFactory(new PropertyValueFactory<>("nomProducto"));

        TableColumn<productoDAO,String> tbcDescripcion = new TableColumn<>("DESCRIPCIÓN");
        tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        TableColumn<productoDAO,Double> tbcPrecio = new TableColumn<>("PRECIO");
        tbcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));


        TableColumn<productoDAO,String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<productoDAO, String>, TableCell<productoDAO, String>>() {
                    @Override
                    public TableCell<productoDAO, String> call(TableColumn<productoDAO, String> param) {
                        return new ButtonCellAlimentos(2);
                    }
                }
        );

        TableColumn<productoDAO,String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<productoDAO, String>, TableCell<productoDAO, String>>() {
                    @Override
                    public TableCell<productoDAO, String> call(TableColumn<productoDAO, String> param) {
                        return new ButtonCellAlimentos(1);
                    }
                }
        );


        tbvProductos.getColumns().addAll(tbcIdProducto,tbcNomProducto,tbcDescripcion,tbcPrecio,tbcEditar,tbcBorrar);
        tbvProductos.setItems(objP.selAllProducto());
    }


}
