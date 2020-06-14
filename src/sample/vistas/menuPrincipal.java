package sample.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.modelos.Conexion;

public class menuPrincipal extends Stage {

    MenuItem mitProductos,mitBebidas,mitInsumos,mitProveedores,mitPromo,mitGrafica,mitNuevaOrden,mitOrdenesP,mitExit;
    MenuBar mnbTaqueria;
    Menu menConsultas,menOrdenes,menSalir;
    Scene escena;
    BorderPane brpPrincipal;

    public menuPrincipal (){
        CrearGUI();
        this.setTitle("Taquería 'El Taco Guapo' ");
        escena.getStylesheets().add("sample/estilos/estilo_principal.css");
        this.setScene(escena);
        this.setMaximized(true);
        this.show();
    }

    private void CrearGUI() {

        brpPrincipal = new BorderPane();
        mnbTaqueria = new MenuBar();
        brpPrincipal.setTop(mnbTaqueria);
        menConsultas = new Menu("Consultas");
        menOrdenes = new Menu("Ordenes");
        menSalir = new Menu("Salir");

        mitProductos = new MenuItem("Alimentos");
        mitProductos.setOnAction(event -> OpcionMenu(1));

        mitBebidas = new MenuItem("Bebidas");
        mitBebidas.setOnAction(event -> OpcionMenu(2));

        mitInsumos = new MenuItem("Insumos");
        mitInsumos.setOnAction(event -> OpcionMenu(3));

        mitProveedores = new MenuItem("Proveedores");
        mitProveedores.setOnAction(event -> OpcionMenu(4));

        mitPromo = new MenuItem("Promociones");
        mitPromo.setOnAction(event -> OpcionMenu(5));

        mitGrafica = new MenuItem("Grafica");
        mitGrafica.setOnAction(event -> OpcionMenu(6));

        mitNuevaOrden = new MenuItem("Ordenes activas");
        mitNuevaOrden.setOnAction(event -> OpcionMenu(7));

        mitOrdenesP = new MenuItem("Historial ordenes");
        mitOrdenesP.setOnAction(event -> OpcionMenu(8));

        mitExit = new MenuItem("¡Vuelva pronto!");
        mitExit.setOnAction(event -> OpcionMenu(20));

        menConsultas.getItems().addAll(mitProductos,mitBebidas,mitInsumos, mitProveedores,mitPromo,mitGrafica);
        menOrdenes.getItems().addAll(mitNuevaOrden,mitOrdenesP);
        menSalir.getItems().add(mitExit);

        //menu bar
        mnbTaqueria.getMenus().addAll(menConsultas, menOrdenes, menSalir);
        escena = new Scene(brpPrincipal, 400, 300);

        //Creamos la conexion a la base de datos
        Conexion.CrearConexion();

    }

    private void OpcionMenu(int i) {
        switch(i){
            case 1:
                new CRUDProductos();
                break;
            case 2:
                new CRUDBebidas();
                break;
            case 3:
                new CRUDInsumos();
                break;
            case 4:
                new CRUDProveedores();
                break;
            case 5:
                new CRUDPromociones();
                break;
            case 6:
                 new CRUDGrafica();
                break;
            case 7:
                new CRUDOrdenes();
                break;
            case 8:
                new CRUDHistorialOrdenes();
                break;
            case 20:
                System.exit(0);
        }
    }


}
