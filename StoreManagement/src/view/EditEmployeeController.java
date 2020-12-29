package src.view;

// import model va service
import src.service.EmployeeService;
import src.utils.Util;
import com.jfoenix.controls.JFXButton;
import src.model.Employee;

//import Controller.CustomerController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class EditEmployeeController implements Initializable {
    private EmployeeController employeeController;
    private Employee employee;
    // Pane
    @FXML
    public Pane panelEditEmployee;
    // Text field
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
    // Controls
    @FXML
    public Button btnLuu;
    @FXML
    public Button btnHuy;
    @FXML
    public ImageView imgPreview;
    @FXML
    public JFXButton btnClose;
    @FXML
    public FontAwesomeIcon iconClose;
    private double mousepX = 0;
    private double mousepY = 0;

    File anhBia;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        panelEditEmployee.setOnMousePressed(mouseEvent -> {
            mousepX = mouseEvent.getSceneX();
            mousepY = mouseEvent.getSceneY();
        });

        panelEditEmployee.setOnMouseDragged(mouseEvent -> {
            panelEditEmployee.getScene().getWindow().setX(mouseEvent.getScreenX() - mousepX);
            panelEditEmployee.getScene().getWindow().setY(mouseEvent.getScreenY() - mousepY);
        });
        txtEmployeeId.setDisable(true);
    }

    public void setEmployeeController(EmployeeController employee) {
        this.employeeController = employee;
    }

    public void setEditEmployee(Employee employee) {
        this.employee = employee;
        bindingData();
    }
    private void bindingData() {
       // txtCustomerId.setText();
        txtEmployeeId.setText(employee.getID());
        txtEmployeeName.setText(employee.getName());
        txtEmployeeBonus.setText(""+employee.getBonus());
        txtEmployeePassword.setText(employee.getPassWord());
        txtEmployeeSalary.setText(""+employee.getSalary());
        txtEmployeeShift.setText(employee.getShift());
        txtEmployeeUsername.setText(employee.getEmployeeName());

    }

    public void btnHuy_Click(ActionEvent event) {
        Stage stage = (Stage) btnHuy.getScene().getWindow();
        stage.close();
    }

    public void btnCloseAction(ActionEvent actionEvent) {
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
    }

    public void btnCloseMouseEnter(MouseEvent mouseEvent) {
        btnClose.setStyle("-fx-background-color: red; -fx-background-radius: 15");
        iconClose.setVisible(true);
    }

    public void btnCloseMouseExit(MouseEvent mouseEvent) {
        btnClose.setStyle("-fx-background-color: #a6a6a6; -fx-background-radius: 15");
        iconClose.setVisible(false);
    }

    public void btnLuu_Click(ActionEvent event) throws FileNotFoundException {
        //VALIDATE
        if(txtEmployeeName.getText().trim().equals("") ||
                txtEmployeeBonus.getText().trim().equals("") ||
                txtEmployeeSalary.getText().toString().trim().equals("") ||
                txtEmployeeShift.getText().trim().equals("") ||
                txtEmployeeUsername.getText().trim().equals("")
        ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Vui lòng nhập đầy đủ dữ liệu!");
            alert.showAndWait();
            return;
        }
        //
        String employeeId = txtEmployeeId.getText();
        String employeeName = txtEmployeeName.getText();
        String employeeSalary = txtEmployeeSalary.getText();
        String employeeShift = txtEmployeeShift.getText();
        String employeeUsername = txtEmployeeUsername.getText();
        String employeePassword = txtEmployeePassword.getText();

        Employee employee = new Employee(employeeId, employeeName,"", "", "phone", "employee",employeeShift, Long.parseLong(employeeSalary), employeeUsername, "pass");

        EmployeeService.getInstance().updateEmployee(employee);
        Util.showSuccess("Quản lý nhân viên", "Sửa nhân viên thành công!");
        employeeController.refreshTable();
        txtEmployeeId.setText("");
        txtEmployeeName.setText("");
        txtEmployeeBonus.setText("");
        txtEmployeeSalary.setText("");
        txtEmployeeShift.setText("");
        txtEmployeeUsername.setText("");
        txtEmployeePassword.setText("");

        Stage stage = (Stage) btnHuy.getScene().getWindow();
        stage.close();
    }

}

