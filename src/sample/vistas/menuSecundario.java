package sample.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.modelos.Conexion;

public class menuSecundario extends Stage {

    MenuItem mitNuevaOrden,mitOrdenesP,mitExit;
    MenuBar mnbTaqueria;
    Menu menConsultas,menOrdenes,menSalir;
    Scene escena;
    BorderPane brpPrincipal;

    public menuSecundario (){
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

        /*
        mitProductos = new MenuItem("Alimentos");
        mitProductos.setOnAction(event -> OpcionMenu(1));

        mitProveedores = new MenuItem("Proveedores");
        mitProveedores.setOnAction(event -> OpcionMenu(2));

         */

        mitNuevaOrden = new MenuItem("Ordenes activas");
        mitNuevaOrden.setOnAction(event -> OpcionMenu(3));

        mitOrdenesP = new MenuItem("Historial ordenes");
        mitOrdenesP.setOnAction(event -> OpcionMenu(4));

        mitExit = new MenuItem("¡Vuelva pronto!");
        mitExit.setOnAction(event -> OpcionMenu(20));

        menConsultas.getItems().addAll();
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
                //new verProductos();
                break;
            case 2:
                //new verProveedores();
                break;
            case 3:
                new verOrdenes();
                break;
            case 4:
                new verHistorialOrdenes();
                break;
            case 20:
                System.exit(0);
        }
    }


}

