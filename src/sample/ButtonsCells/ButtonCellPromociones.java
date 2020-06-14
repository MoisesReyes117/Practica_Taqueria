package sample.ButtonsCells;

import javafx.scene.control.*;
import sample.Formularios.FrmPromociones;
import sample.modelos.PromocionesDAO;

import java.util.Optional;

public class ButtonCellPromociones extends TableCell<PromocionesDAO, String> {
    private Button btnCelda;
    private PromocionesDAO objP;

    public ButtonCellPromociones(int opc) {
        if(opc == 1){
            btnCelda = new Button("Editar");
            btnCelda.setOnAction(event ->{
                TableView<PromocionesDAO> tbvTemp = ButtonCellPromociones.this.getTableView();
                objP = ButtonCellPromociones.this.getTableView().getItems().get(ButtonCellPromociones.this.getIndex());
                new FrmPromociones(tbvTemp,objP);
            });
        }else {
            btnCelda = new Button("Borrar");
            btnCelda.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Mensaje del Sistema :)");
                alert.setHeaderText("Comfirmación");
                alert.setContentText("¿ Deseas eliminar la promocion ?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    objP = ButtonCellPromociones.this.getTableView().getItems().get(ButtonCellPromociones.this.getIndex());
                    objP.delpromociones();

                    //refrescar la tabla
                    ButtonCellPromociones.this.getTableView().setItems(objP.seeAllPromociones());
                    ButtonCellPromociones.this.getTableView().refresh();
                }


            });
        }
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty)
            setGraphic(btnCelda);
    }
}