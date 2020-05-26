package sample.modelos;

import javafx.scene.control.*;

import java.util.Optional;

public class ButtonCellProveedor extends TableCell<proveedorDAO, String> {
    private Button btnCelda;
    private proveedorDAO objP;

    public ButtonCellProveedor (int opc) {
        if(opc == 1){
            btnCelda = new Button("Editar");
            btnCelda.setOnAction(event ->{
                TableView<proveedorDAO> tbvTemp = ButtonCellProveedor.this.getTableView();
                objP = ButtonCellProveedor.this.getTableView().getItems().get(ButtonCellProveedor.this.getIndex());
                new FrmProveedor(tbvTemp,objP);
            });
        }else {
            btnCelda = new Button("Borrar");
            btnCelda.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Mensaje del Sistema :)");
                alert.setHeaderText("Comfirmación");
                alert.setContentText("¿ Deseas eliminar el proveedor ?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    objP = ButtonCellProveedor.this.getTableView().getItems().get(ButtonCellProveedor.this.getIndex());
                    objP.delProveedor();

                    //refrescar la tabla
                    ButtonCellProveedor.this.getTableView().setItems(objP.selAllProveedor());
                    ButtonCellProveedor.this.getTableView().refresh();
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
