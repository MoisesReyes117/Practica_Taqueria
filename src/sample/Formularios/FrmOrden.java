package sample.Formularios;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.modelos.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FrmOrden extends Stage {

    private TableView<ordenDAO> tbvOrdenes;
    private ordenDAO objO;
    private VBox vbox;
    private ComboBox<promoDAO> cbxPromo;
    private ComboBox<meseroDAO> cbxMesero;
    private Label lbPromo,lbMesero;
    private Button btnGuardar;
    private Scene escena;
    private Connection con;
    private int clave;

    public FrmOrden(TableView<ordenDAO> tbvOrdenes, ordenDAO obj){

        if (obj != null)
            this.objO = obj;
        else
            objO = new ordenDAO();


        this.tbvOrdenes = tbvOrdenes;
        CrearGUI();
        this.setTitle("Gestion de Ordenes");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        con = Conexion.con;
        vbox = new VBox();
        lbPromo = new Label();
        lbMesero = new Label();
        clave = objO.getCveOrden();

        lbPromo.setText("Selecciona la promoción");
        lbPromo.setTextFill(Color.BLUE);
        cbxPromo = new ComboBox();
        cbxPromo.setItems(new promoDAO().selAllPromos());
        promoDAO objPromo = new promoDAO();
        objPromo.setCvePromo(objO.getCvePromo());
        objPromo.getProvByCve();
        cbxPromo.setValue(objPromo);

        lbMesero.setText("Selecciona el mesero");
        lbMesero.setTextFill(Color.BLUE);
        cbxMesero = new ComboBox();

        cbxMesero.setItems(new meseroDAO().selAllMeseros());
        meseroDAO objMe = new meseroDAO();
        objMe.setCveEmpleado(objO.getCveMesero());
        objMe.getProvByCve();
        cbxMesero.setValue(objMe);

        btnGuardar = new Button("Continuar");
        btnGuardar.setOnAction(event -> { guardarDatos(); });
        vbox.getChildren().addAll(lbPromo,cbxPromo,lbMesero,cbxMesero,btnGuardar);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setSpacing(5);
        escena = new Scene(vbox,360,260);
    }

    private void guardarDatos() {
        String date;
        int año,mes=1,dia;
        Calendar fecha = new GregorianCalendar();
        año = fecha.get(Calendar.YEAR);
        date = ""+año;
        mes += fecha.get(Calendar.MONTH);
        date +="-"+mes;
        dia = fecha.get(Calendar.DAY_OF_MONTH);
        date +="-"+dia;

        objO.setFecha(Date.valueOf(date));

        promoDAO objTemp1 = cbxPromo.getValue();
        objO.setCvePromo(objTemp1.getCvePromo());

        meseroDAO objTemp2 = cbxMesero.getValue();
        objO.setCveMesero(objTemp2.getCveEmpleado());

        String query = "select * from orden where CveOrden="+clave+" ;";

        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()){
                objO.updOrden();
            }else{
                objO.insOrden();

                String query2 = "select CveOrden from orden where total IS NULL;";

                try {
                    Statement stmt2 = con.createStatement();
                    ResultSet res2 = stmt2.executeQuery(query2);
                    if (res2.next()){
                        clave = res2.getInt("CveOrden");
                        new FrmLlenadoOrden(clave,tbvOrdenes);
                    }else{

                    }




                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        tbvOrdenes.setItems(objO.selAllordenesActivas());
        tbvOrdenes.refresh();

        this.close();

    }


}
