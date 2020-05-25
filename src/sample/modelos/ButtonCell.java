package sample.modelos;

import javafx.scene.control.*;

import java.util.Optional;

public class ButtonCell extends TableCell<productoDAO, String> {
    private Button btnCelda;
    private productoDAO objP;

    public ButtonCell (int opc) {
        if(opc == 1){
            btnCelda = new Button("Editar");
            btnCelda.setOnAction(event ->{
                TableView<productoDAO> tbvTemp = ButtonCell.this.getTableView();
                objP = ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                new FrmProducto(tbvTemp,objP);
            });
        }else {
            btnCelda = new Button("Borrar");
            btnCelda.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Mensaje del Sistema :)");
                alert.setHeaderText("Comfirmación");
                alert.setContentText("¿ Deseas eliminar el producto ?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    objP = ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                    objP.delProducto();

                    //refrescar la tabla
                    ButtonCell.this.getTableView().setItems(objP.selAllProducto());
                    ButtonCell.this.getTableView().refresh();
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
