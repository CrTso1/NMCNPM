package src.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import src.model.Employee;
import src.service.EmployeeService;

public class EmployeeController implements Initializable {
    // khai bao controls
    @FXML
    public TableView<Employee> tbEmployee;
    @FXML
    public TableColumn<Employee, String> tbcEmployeeRole;
    @FXML
    public TableColumn<Employee, String> tbcEmployeeShift;
    @FXML
    public TableColumn<Employee, String> tbcEmployeeName;
    @FXML
    public TableColumn<Employee, String> tbcEmployeeBonus;
    @FXML
    public TableColumn<Employee, Date> tbcEmployeeSalary;
    @FXML
    public TableColumn<Employee, String> tbcEmployeeUsername;
    @FXML
    public TableColumn<Employee, String> tbcEmployeePassword;
    @FXML
    public TextField txtEmployeeId;
    @FXML
    public TextField txtEmployeeName;
    @FXML
    public TextField txtEmployeeBonus;
    @FXML
    public TextField txtEmployeeSalary;
    @FXML
    public TextField txtEmployeeShift;
    @FXML
    public TextField txtEmployeeUsername;
    @FXML
    public TextField txtEmployeePassword;
    @FXML
    public PasswordField txtPassword;
    @FXML
    public Button btnAddEmployee;
    @FXML
    public Button btnEditEmployee;
    @FXML
    public Button btnDeleteEmployee;
    @FXML
    public TextField txtUsername;
    @FXML
    public TextField txtRole;
    @FXML
    public TextField txtSearch;

    //region controller
    private ObservableList<Employee> listEmployee;
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCell();
        loadData();

        txtSearch.textProperty().addListener((observableValue, s, t1) -> {
            listNV.clear();
            listNV.addAll(EmployeeService.getInstance().searchNV(t1));
        });
    }

    private void setCell() {
        //hien thi thong tin
    }

    private void loadData() {
        listEmployee = FXCollections.observableArrayList(EmployeeService.getInstance().getAllNV());
        tbEmployee.setItems(listEmployee);
    }

    public void bindingData() {
        // binding data
    }

    public void refreshTable() {
        listEmployee.clear();
        loadData();
    }

    private void clearInput() {
        txtEmployeeName.setText("");
        txtBonus.setText("");
        txtPassword.setText("");
        txtRole.setText("");
        txtUsername.setText("");
    }

    public void btnAdd_Click(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/AddEmployeeDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            //
            AddEmployeeController addEmployeeController = loader.getController();
            addEmployeeController.setEmployeeController(this);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnEdit_Click(ActionEvent event) {
        if (tbEmployee.getSelectionModel().getSelectedIndex() >= 0) {
            Employee temp = tbEmployee.getSelectionModel().getSelectedItem();
            //
            //refreshTable();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../view/EditEmployeeDialog.fxml"));
                AnchorPane page = (AnchorPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.initStyle(StageStyle.UNDECORATED);
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);
                //
                EditEmployeeController editEmployeeController = loader.getController();
                editEmployeeController.setEmployeeController(this);
                editEmployeeController.setEditNV(temp);
                dialogStage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Chưa chọn nhân viên cần chỉnh sửa!");
            alert.showAndWait();
        }
    }

    public void btnDelete_Click(ActionEvent event) {
        //TO-DO viết hàm xóa nhân viên theo id
        if (tbEmployee.getSelectionModel().getSelectedIndex() >= 0) {
            Employee temp = tbEmployee.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xóa nhân viên");
            alert.setHeaderText("Bạn muốn xóa nhân viên này ra khỏi danh sách?");
            alert.setContentText("[" + temp.getID() + "] " + temp.getName());

            // option != null.
            Optional<ButtonType> option = alert.showAndWait();

             if (option.get() == ButtonType.OK) {
                int rs = EmployeeService.getInstance().removeEmployee(temp.getID());
                Util.showSuccess(rs, "Quản lý nhân viên", "Xóa nhân viên thành công!");
                refreshTable();
                clearInput();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Chưa chọn nhân viên cần chỉnh sửa!");
            alert.showAndWait();
        }

    }
}
