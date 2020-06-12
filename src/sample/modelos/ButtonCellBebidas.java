package sample.modelos;
import javafx.scene.control.*;
import sample.modelos.BebidasDAO;
import sample.modelos.FrmProducto;
import sample.modelos.productoDAO;

import java.util.Optional;

public class ButtonCellBebidas extends TableCell<BebidasDAO, String> {
    private Button btnCelda;
    private BebidasDAO objB;

    public ButtonCellBebidas(int opc) {
        if(opc == 1){
            btnCelda = new Button("Editar");
            btnCelda.setOnAction(event ->{
                TableView<BebidasDAO> tbvTemp = sample.modelos.ButtonCellBebidas.this.getTableView();
                objB = sample.modelos.ButtonCellBebidas.this.getTableView().getItems().get(sample.modelos.ButtonCellBebidas.this.getIndex());
                new FrmBebidas(tbvTemp,objB);
            });
        }else {
            btnCelda = new Button("Borrar");
            btnCelda.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Mensaje del Sistema :)");
                alert.setHeaderText("Comfirmación");
                alert.setContentText("¿ Deseas eliminar el Bebida ?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    objB = sample.modelos.ButtonCellBebidas.this.getTableView().getItems().get(sample.modelos.ButtonCellBebidas.this.getIndex());
                    objB.delBebida();

                    //refrescar la tabla
                    sample.modelos.ButtonCellBebidas.this.getTableView().setItems(objB.seeAllBebidas());
                    sample.modelos.ButtonCellBebidas.this.getTableView().refresh();
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
