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
    Menu menOrdenes,menSalir;
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
        menOrdenes = new Menu("Ordenes");
        menSalir = new Menu("Salir");


        mitNuevaOrden = new MenuItem("Ordenes activas");
        mitNuevaOrden.setOnAction(event -> OpcionMenu(2));

        mitOrdenesP = new MenuItem("Historial ordenes");
        mitOrdenesP.setOnAction(event -> OpcionMenu(3));

        mitExit = new MenuItem("¡Vuelva pronto!");
        mitExit.setOnAction(event -> OpcionMenu(20));


        menOrdenes.getItems().addAll(mitNuevaOrden,mitOrdenesP);
        menSalir.getItems().add(mitExit);

        //menu bar
        mnbTaqueria.getMenus().addAll(menOrdenes, menSalir);
        escena = new Scene(brpPrincipal, 400, 300);

        //Creamos la conexion a la base de datos
        Conexion.CrearConexion();

    }

    private void OpcionMenu(int i) {
        switch(i){
            case 1:
                break;
            case 2:
                new CRUDOrdenes();
                break;
            case 3:
                new CRUDHistorialOrdenes();
                break;
            case 20:
                System.exit(0);
        }
    }


}

