package lk.ijse.d24_hostel_managment_system.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.d24_hostel_managment_system.bo.custom.impl.RoomBoImpl;
import lk.ijse.d24_hostel_managment_system.dao.custom.impl.RoomDAOImpl;
import lk.ijse.d24_hostel_managment_system.dto.RoomDTO;
import lk.ijse.d24_hostel_managment_system.entity.Room;
import org.controlsfx.control.Notifications;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RoomFormController implements Initializable {
    public javafx.scene.control.Label roomNameLbl;
    @FXML
    private JFXTextField roomTypeIdTxt;

    @FXML
    private JFXTextField keyMoneyTxt;

    @FXML
    private JFXTextField roomTypeTxt;

    @FXML
    private JFXTextField roomQtyTxt;

    @FXML
    private TableView<RoomDTO> roomTable;

    @FXML
    private TableColumn<?, ?> roomTypeIdColumn;

    @FXML
    private TableColumn<?, ?> roomTypeColumn;

    @FXML
    private TableColumn<?, ?> keyMoneyColumn;

    @FXML
    private TableColumn<?, ?> roomQtyColumn;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private JFXButton updateBtn;


    @FXML
    public Label roomLbl;


    RoomBoImpl roomBo = new RoomBoImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roomTableAddData();
        setCellValues();
        tableListener();
    }

    private void setCellValues() {
        roomTypeIdColumn.setCellValueFactory(new PropertyValueFactory<>("room_Type_Id"));
        roomTypeColumn.setCellValueFactory(new PropertyValueFactory<>("room_Type"));
        keyMoneyColumn.setCellValueFactory(new PropertyValueFactory<>("key_Money"));
        roomQtyColumn.setCellValueFactory(new PropertyValueFactory<>("rooms_Qty"));

    }

    private void roomTableAddData(){
        ArrayList<RoomDTO> all = roomBo.getAllRooms();
        for (RoomDTO room : all){
            roomTable.getItems().add(new RoomDTO(room.getRoom_Type_Id(),room.getRoom_Type(),room.getKey_Money(),room.getRooms_Qty()));
        }
    }

    private void  tableListener(){
        roomTable.getSelectionModel().selectedItemProperty().addListener((ob, oldValue, newValue) ->{
            if (newValue !=null){
                saveBtn.setVisible(false);
                roomNameLbl.setText("Room Update");
                roomTypeIdTxt.setText(newValue.getRoom_Type_Id());
                roomTypeTxt.setText(newValue.getRoom_Type());
                keyMoneyTxt.setText(newValue.getKey_Money());
                roomQtyTxt.setText(newValue.getRooms_Qty());
            }
        });
    }


    @FXML
    void saveBtnOnAction(ActionEvent event) {
        String roomId = roomTypeIdTxt.getText();
        String roomType = roomTypeTxt.getText();
        String keyMoney = keyMoneyTxt.getText();
        String qty = roomQtyTxt.getText();

        if (roomId.isEmpty() || roomType.isEmpty() || keyMoney.isEmpty() || qty.isEmpty()){
            Notifications.create()
                    .title("Notification!")
                    .text("Input Data!!")
                    .position(Pos.TOP_CENTER)
                    .showInformation();
        }else{
            boolean roomSaved = roomBo.saveRoom(new RoomDTO(roomTypeIdTxt.getText(), roomTypeTxt.getText(), keyMoneyTxt.getText(), roomQtyTxt.getText()));
            if (roomSaved) {
                Notifications.create()
                        .title("Notification!")
                        .text("Room Saved!!")
                        .position(Pos.TOP_CENTER)
                        .showInformation();
            }
        }




    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        boolean isDeleted = roomBo.deleteRoom(new RoomDTO(roomTypeIdTxt.getText(),roomTypeTxt.getText(),keyMoneyTxt.getText(),roomQtyTxt.getText()));
        if (isDeleted){
            Notifications.create()
                    .title("Notification !")
                    .text("Room Deleted !!")
                    .position(Pos.TOP_CENTER)
                    .showInformation();
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) {

        String roomId = roomTypeIdTxt.getText();
        String roomType = roomTypeTxt.getText();
        String keyMoney = keyMoneyTxt.getText();
        String qty = roomQtyTxt.getText();

        if (roomId.isEmpty() || roomType.isEmpty() || keyMoney.isEmpty() || qty.isEmpty()){
            Notifications.create()
                    .title("Notification!")
                    .text("Input Data!!")
                    .position(Pos.TOP_CENTER)
                    .showInformation();
        }else{
            boolean isUpdate = roomBo.updateRoom(new RoomDTO(roomTypeIdTxt.getText(),roomTypeTxt.getText(),keyMoneyTxt.getText(),roomQtyTxt.getText()));
            if (isUpdate){
                Notifications.create()
                        .title("Notification !")
                        .text("Room Updated!!")
                        .position(Pos.TOP_CENTER)
                        .showInformation();

              }
          }

        }

    }






