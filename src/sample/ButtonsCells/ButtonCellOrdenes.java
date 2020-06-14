package sample.ButtonsCells;



import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.stream.Stream;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import sample.modelos.Conexion;
import sample.modelos.ordenDAO;


public class ButtonCellOrdenes extends TableCell<ordenDAO, String> {
    private Connection con;
    private Button btnCelda;
    private ordenDAO objO;
    String fecha;
    int hora,minutos,segundos;
    public ButtonCellOrdenes (int opc) {
        con = Conexion.con;
        if(opc == 1){
            btnCelda = new Button("Pagar");
            btnCelda.setOnAction(event ->{


                TableView<ordenDAO> tbvTemp = ButtonCellOrdenes.this.getTableView();
                objO = ButtonCellOrdenes.this.getTableView().getItems().get(ButtonCellOrdenes.this.getIndex());
                int clave = objO.getCveOrden();
                objO.upPagada(clave);
                try {

                    Document doc = new Document();
                    PdfWriter. getInstance (doc, new FileOutputStream ("Cuenta Orden No."+clave+".pdf"));
                    doc.open ();

                    Font bold = new Font (Font.FontFamily. HELVETICA , 18, Font.ITALIC );
                    Paragraph titulo = new Paragraph ("Taquería 'El Taco Guapo'");
                    Paragraph subtitulo = new Paragraph ("Contenido Orden");
                    Paragraph parrafo1 = new Paragraph ("Alimentos");
                    Paragraph parrafo2 = new Paragraph ("Bebidas");
                    Paragraph parrafo3 = new Paragraph ("Entrega");
                    Paragraph parrafo4 = new Paragraph ("Descuento");
                    Paragraph parrafo5 = new Paragraph ("");

                    String query = "select fecha from orden where CveOrden="+clave+";";

                        Statement stmt = Conexion.con.createStatement();
                        ResultSet res = stmt.executeQuery(query);

                        while (res.next()) {
                            fecha = ""+res.getDate("fecha");
                        }

                    Calendar calendario = new GregorianCalendar();
                    hora =calendario.get(Calendar.HOUR_OF_DAY);
                    minutos = calendario.get(Calendar.MINUTE);
                    segundos = calendario.get(Calendar.SECOND);

                    Paragraph parrafo = new Paragraph ("Fecha y Hora: "+fecha+" a las "+hora+":"+minutos+":"+segundos);

                    PdfPTable table = new PdfPTable (3);
                    Stream.of ("Nombre", "Cantidad","Precio"). forEach ( table::addCell);

                    String query1 = "select comida.nombre,contieneComida.cantidad,comida.precio from comida,contieneComida where contieneComida.CveOrden="+clave+" and contieneComida.CveComida=comida.CveComida order by nombre;";

                        Statement stmt1 = con.createStatement();
                        ResultSet res1 = stmt1.executeQuery(query1);
                        while(res1.next()){

                            table.addCell (res1.getString("nombre"));
                            table.addCell (""+res1.getInt("cantidad"));
                            table.addCell (""+res1.getDouble("precio"));

                        }

                        PdfPTable table2 = new PdfPTable (4);
                    Stream.of ("Nombre","Tamaño","Cantidad","Precio"). forEach ( table2::addCell);

                    String query2 = "select bebida.nombre,bebida.tamaño,contieneBebida.cantidad,bebida.precio from bebida,contieneBebida where contieneBebida.CveOrden="+clave+" and contieneBebida.CveBebida=bebida.CveBebida order by nombre;";

                    Statement stmt2 = con.createStatement();
                    ResultSet res2 = stmt2.executeQuery(query2);
                    while(res2.next()){

                        table2.addCell (res2.getString("nombre"));
                        table2.addCell (res2.getString("tamaño"));
                        table2.addCell (""+res2.getInt("cantidad"));
                        table2.addCell (""+res2.getDouble("precio"));


                    }

                    PdfPTable table3 = new PdfPTable (3);
                    Stream.of ("Nombre","Descripción","Costo"). forEach ( table3::addCell);

                    String query3 = "select costoEntrega.nombre,entrega.descripción,costoEntrega.costo from entrega,costoEntrega where entrega.CveOrden="+clave+" and entrega.CveCosto=costoEntrega.CveCosto;";

                    Statement stmt3 = con.createStatement();
                    ResultSet res3 = stmt3.executeQuery(query3);
                    while(res3.next()){

                        table3.addCell (res3.getString("nombre"));
                        table3.addCell (res3.getString("descripción"));
                        table3.addCell (""+res3.getDouble("costo"));

                    }


                    int promo=0;
                    String queryP = "select CvePromo from orden where CveOrden="+clave+";";

                    Statement stmtP = con.createStatement();
                    ResultSet resP = stmtP.executeQuery(queryP);
                    while(resP.next()){

                        promo = resP.getInt("CvePromo");

                    }

                    PdfPTable table4 = new PdfPTable (2);
                    Stream.of ("Nombre","Porcenjate de descuento"). forEach ( table4::addCell);

                    String query4 = "select nombre,porcentaje from promociones where CvePromo="+promo+";";

                    Statement stmt4 = con.createStatement();
                    ResultSet res4 = stmt4.executeQuery(query4);
                    while(res4.next()){

                        table4.addCell (res4.getString("nombre"));
                        table4.addCell ("%"+res4.getDouble("porcentaje"));

                    }


                    String query5 = "select total from orden where CveOrden="+clave+";";

                    Statement stmt5 = con.createStatement();
                    ResultSet res5 = stmt5.executeQuery(query5);
                    while(res5.next()){

                         parrafo5 = new Paragraph("Total a pagar: $"+res5.getDouble("total"));

                    }


                    titulo.setAlignment(Element.ALIGN_CENTER);
                    subtitulo.setAlignment(Element.ALIGN_CENTER);
                    parrafo.setAlignment(Element.ALIGN_RIGHT);
                    parrafo5.setAlignment(Element.ALIGN_RIGHT);
                    parrafo1.setAlignment(Element.ALIGN_CENTER);
                    parrafo2.setAlignment(Element.ALIGN_CENTER);
                    parrafo3.setAlignment(Element.ALIGN_CENTER);
                    parrafo4.setAlignment(Element.ALIGN_CENTER);
                    parrafo.setSpacingAfter(3);
                    parrafo1.setSpacingAfter(3);
                    parrafo2.setSpacingAfter(3);
                    parrafo3.setSpacingAfter(3);
                    parrafo4.setSpacingAfter(3);
                    parrafo4.setSpacingAfter(3);
                    parrafo1.add(table);
                    parrafo1.setFont(bold);
                    parrafo2.add(table2);
                    parrafo2.setFont(bold);
                    parrafo3.add(table3);
                    parrafo3.setFont(bold);
                    parrafo4.add(table4);
                    parrafo4.setFont(bold);
                    parrafo5.setFont(bold);
                    doc.add(parrafo);
                    doc.add(titulo);
                    doc.add(subtitulo);
                    doc.add(parrafo1);
                    doc.add(parrafo2);
                    doc.add(parrafo3);
                    doc.add(parrafo4);
                    doc.add(parrafo5);
                    doc.close ();

                    File path = new File("Cuenta Orden No."+clave+".pdf");
                    Desktop.getDesktop().open(path);
                }catch (Exception e){
                    e.printStackTrace();
                }

                tbvTemp.setItems(objO.selAllordenesActivas());
                tbvTemp.refresh();
            });
        }else {

        }
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty)
            setGraphic(btnCelda);
    }
}
