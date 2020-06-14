package sample.ButtonsCells;
import javafx.scene.control.*;
import sample.Formularios.FrmBebida;
import sample.modelos.bebidaDAO;


import java.util.Optional;

public class ButtonCellBebidas extends TableCell<bebidaDAO, String> {
    private Button btnCelda;
    private bebidaDAO objB;

    public ButtonCellBebidas(int opc) {
        if(opc == 1){
            btnCelda = new Button("Editar");
            btnCelda.setOnAction(event ->{
                TableView<bebidaDAO> tbvTemp = sample.ButtonsCells.ButtonCellBebidas.this.getTableView();
                objB = sample.ButtonsCells.ButtonCellBebidas.this.getTableView().getItems().get(sample.ButtonsCells.ButtonCellBebidas.this.getIndex());
                new FrmBebida(tbvTemp,objB);
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
                    objB = sample.ButtonsCells.ButtonCellBebidas.this.getTableView().getItems().get(sample.ButtonsCells.ButtonCellBebidas.this.getIndex());
                    objB.delBebida();

                    //refrescar la tabla
                    sample.ButtonsCells.ButtonCellBebidas.this.getTableView().setItems(objB.selAllBebidas());
                    sample.ButtonsCells.ButtonCellBebidas.this.getTableView().refresh();
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