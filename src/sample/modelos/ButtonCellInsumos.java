package sample.modelos;

import javafx.scene.control.*;

import java.util.Optional;

public class ButtonCellInsumos extends TableCell<InsumosDAO, String> {
    private Button btnCelda;
    private InsumosDAO objP;

    public ButtonCellInsumos(int opc) {
        if(opc == 1){
            btnCelda = new Button("Editar");
            btnCelda.setOnAction(event ->{
                TableView<InsumosDAO> tbvTemp = ButtonCellInsumos.this.getTableView();
                objP = ButtonCellInsumos.this.getTableView().getItems().get(ButtonCellInsumos.this.getIndex());
                new FrmInsumo(tbvTemp,objP);
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
