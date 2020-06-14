package sample.vistas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.modelos.Conexion;

import java.beans.EventHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class CRUDGrafica extends Stage {
    private Connection con;
    private Scene escena;
    private HBox hBox;
    private String nombreComida="",nombreBebida="";
    private int cantidad=10, totalVendidoC=0,totalVendidoB=0;


    public CRUDGrafica(){
        CrearGUI();
        this.setTitle("Grafica Ventas");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        con = Conexion.con;
        hBox = new HBox();
        Pane graficaComida = new Pane();
        Pane graficaBebida = new Pane();
        ObservableList<PieChart.Data> valueList = FXCollections.observableArrayList();
        ObservableList<PieChart.Data> valueList2 = FXCollections.observableArrayList();

        String query = "select SUM(cantidad)total from contieneComida";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
               totalVendidoC = res.getInt("total");
            }


        String query1 = "select SUM(cantidad)total from contieneBebida";

            Statement stmt1 = con.createStatement();
            ResultSet res1 = stmt1.executeQuery(query1);
            while(res1.next()){
                totalVendidoB = res1.getInt("total");
            }

            String query2 = "select comida.nombre,SUM(contieneComida.cantidad)cantidad from comida,contieneComida where comida.CveComida=contieneComida.CveComida group by comida.nombre;";

            Statement stmt2 = con.createStatement();
            ResultSet res2 = stmt2.executeQuery(query2);
            double porcen;
            while(res2.next()){
                nombreComida = res2.getString("nombre");
                cantidad = res2.getInt("cantidad");
                porcen = Double.valueOf((cantidad*100)/totalVendidoC);
                valueList.add(new PieChart.Data(nombreComida, porcen));
            }

            String query3 = "select bebida.nombre,SUM(contieneBebida.cantidad)cantidad from bebida,contieneBebida where bebida.CveBebida=contieneBebida.CveBebida group by bebida.nombre;";

            Statement stmt3 = con.createStatement();
            ResultSet res3 = stmt3.executeQuery(query3);
            double porcen2;
            while(res3.next()){
                nombreBebida = res3.getString("nombre");
                cantidad = res3.getInt("cantidad");
                porcen2 = (double) ((cantidad * 100) / totalVendidoB);
                valueList2.add(new PieChart.Data(nombreBebida, porcen2));

            }


        }catch (Exception e){
            e.printStackTrace();
        }





        PieChart pastelComida = new PieChart(valueList);
        pastelComida.setTitle("Comidas");
        pastelComida.setMaxSize(650,500);

        PieChart pastelBebida = new PieChart(valueList2);
        pastelBebida.setTitle("Bebidas");
        pastelBebida.setMaxSize(650,500);

        graficaComida.getChildren().addAll(pastelComida);
        graficaBebida.getChildren().addAll(pastelBebida);
        hBox.getChildren().addAll(graficaComida,graficaBebida);
        escena = new Scene(hBox,1000,500);
    }
}
