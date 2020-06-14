package sample.Formularios;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.modelos.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class FrmLlenadoOrden extends Stage {

    private TableView<ordenDAO> tbvOrdenes;
    private contenidoComidaDAO objC;
    private contieneBebidaDAO objB;
    private costoEntregaDAO objE;
    private VBox vbox1,vbox2,vbox3,vbox4;
    private TextField txtCantidadC,txtCantidadB,txtEntrega;
    private Label lbComida,lbBebida,lbEntrega;
    private ComboBox<productoDAO> cbxProducto;
    private ComboBox<bebidaDAO> cbxBebida;
    private ComboBox<costoEntregaDAO> cbxCosto;
    private Button btnAgregarC,btnAgregarB;
    private Button btnTerminar;
    private Scene escena;
    private Connection con;
    private int clave;

    public FrmLlenadoOrden(int CveOrden,TableView<ordenDAO> tbvOrdenes){
        this.tbvOrdenes = tbvOrdenes;
        this.clave = CveOrden;
        objC = new contenidoComidaDAO();
        objB = new contieneBebidaDAO();
        objE = new costoEntregaDAO();

        CrearGUI();
        this.setTitle("Llenado de orden");
        this.setScene(escena);
        this.show();
    }


    private void CrearGUI() {
        con = Conexion.con;
        vbox1 = new VBox();
        vbox1.setSpacing(3);
        vbox1.setAlignment(Pos.CENTER);
        vbox2 = new VBox();
        vbox2.setSpacing(3);
        vbox2.setAlignment(Pos.CENTER);
        vbox3 = new VBox();
        vbox3.setSpacing(3);
        vbox3.setAlignment(Pos.CENTER);
        vbox4 = new VBox();
        vbox4.setSpacing(20);
        lbBebida = new Label();
        lbComida = new Label();
        lbEntrega = new Label();
        txtCantidadC = new TextField();


        lbComida.setText("Selecciona la comida");
        lbComida.setTextFill(Color.BLUE);
        cbxProducto = new ComboBox();
        cbxProducto.setItems(new productoDAO().selAllProducto());
        productoDAO objPro = new productoDAO();
        objPro.setCveProducto(objC.getCveProducto());
        objPro.getProvByCve();
        cbxProducto.setValue(objPro);

        txtCantidadC.setMaxWidth(170);
        txtCantidadC.setPromptText("Introduce la cantidad");


        btnAgregarC = new Button("Agregar Comida");
        btnAgregarC.setOnAction(event -> { guardarComida(); });

        lbBebida.setText("Seleciona la bebida");
        lbBebida.setTextFill(Color.BLUE);
        cbxBebida = new ComboBox();
        cbxBebida.setItems(new bebidaDAO().selAllBebidas());
        bebidaDAO objBe = new bebidaDAO();
        objBe.setCveBebida(objB.getCveBebida());
        objBe.getProvByCve();
        cbxBebida.setValue(objBe);

        txtCantidadB = new TextField();
        txtCantidadB.setMaxWidth(170);
        txtCantidadB.setPromptText("Introduce la cantidad");

        btnAgregarB = new Button("Agregar Bebida");
        btnAgregarB.setOnAction(event -> { guardarBebida(); });

        lbEntrega.setText("Selecciona la entrega");
        lbEntrega.setTextFill(Color.BLUE);
        cbxCosto = new ComboBox();
        cbxCosto.setItems(new costoEntregaDAO().selAllCostos());
        costoEntregaDAO objE = new costoEntregaDAO();
        objE.setCveCosto(objE.getCveCosto());
        objE.getProvByCve();
        cbxCosto.setValue(objE);

        txtEntrega = new TextField();
        txtEntrega.setMaxWidth(300);
        txtEntrega.setPromptText("Entregar a:");

        btnTerminar = new Button("Terminar");
        btnTerminar.setOnAction(event -> { terminar(); });

        vbox1.getChildren().addAll(lbComida,cbxProducto,txtCantidadC,btnAgregarC);
        vbox2.getChildren().addAll(lbBebida,cbxBebida,txtCantidadB,btnAgregarB);
        vbox3.getChildren().addAll(lbEntrega,cbxCosto,txtEntrega,btnTerminar);
        vbox4.getChildren().addAll(vbox1,vbox2,vbox3);
        vbox4.setAlignment(Pos.TOP_CENTER);
        escena = new Scene(vbox4,350,400);
    }

    private void guardarBebida() {
        int cantidad = 0;
        int cveBebida;
        double precio = 0,total = 0;

        bebidaDAO objTemp1 = cbxBebida.getValue();
        objB.setCveBebida(objTemp1.getCveBebida());
        cveBebida = objTemp1.getCveBebida();

        objB.setCantidad(Integer.parseInt(txtCantidadB.getText()));
        cantidad = Integer.parseInt(txtCantidadB.getText());

        String query = "select precio from bebida where CveBebida="+cveBebida+"";

        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()){
                precio = res.getDouble("precio");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        total = cantidad * precio;
        objB.upCantidad(cveBebida,cantidad);
        objB.insContenido(total,clave,cveBebida);

        this.close();
        new FrmLlenadoOrden(clave,tbvOrdenes);
    }

    private void terminar() {
        Double total = 0.0,descuento,costoentrega=0.0,porcentaje=0.0;
        int cvecosto,promo = 0;
        String entrega;

        costoEntregaDAO objTemp = cbxCosto.getValue();
        objE.setCveCosto(objTemp.getCveCosto());
         cvecosto = objTemp.getCveCosto();

          entrega = txtEntrega.getText();


        String query = "insert into entrega(CveOrden,CveCosto,descripción) values("+clave+","+cvecosto+",'"+entrega+"')";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String query1 = "select CvePromo from orden where CveOrden="+clave+";";

        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query1);
            if (res.next()){
                promo = res.getInt("CvePromo");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        String query2 = "select SUM(contieneComida.subtotal)suma from contieneComida,comida where contieneComida.CveOrden="+clave+" and contieneComida.CveComida=comida.CveComida";

        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query2);
            if (res.next()){
                total += res.getDouble("suma");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        String query3 = "select SUM(contieneBebida.subtotal)suma from contieneBebida,bebida where contieneBebida.CveOrden="+clave+" and contieneBebida.CveBebida=bebida.CveBebida";

        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query3);
            if (res.next()){
                total += res.getDouble("suma");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        String query5 = "select costo from costoentrega where CveCosto="+cvecosto+";";

        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query5);
            if (res.next()){
                costoentrega = res.getDouble("costo");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        total += costoentrega;


        String query4 = "select porcentaje from promociones where CvePromo="+promo+";";

        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query4);
            if (res.next()){
                porcentaje = res.getDouble("porcentaje");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        descuento = total*(porcentaje/100);


        total = total-descuento;

        ordenDAO objO = new ordenDAO();
        objO.upTotal(total);
        tbvOrdenes.setItems(objO.selAllordenesActivas());
        tbvOrdenes.refresh();
        this.close();
    }

    private void guardarComida() {
        int cantidad=0;
        int cveproducto;
        double precio = 0,total=0;

        productoDAO objTemp1 = cbxProducto.getValue();
        objC.setCveProducto(objTemp1.getCveProducto());
        cveproducto = objTemp1.getCveProducto();

        objC.setCantidad(Integer.parseInt(txtCantidadC.getText()));
        cantidad = Integer.parseInt(txtCantidadC.getText());

        String query = "select precio from comida where CveComida="+cveproducto+"";

        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()){
                precio = res.getDouble("precio");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        total = cantidad * precio;

        objC.insContenido(total,clave,cveproducto);

        this.close();
        new FrmLlenadoOrden(clave,tbvOrdenes);

    }

}
