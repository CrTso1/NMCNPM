package src.view;

import src.model.Employee;
import src.service.EmployeeService;
import src.service.ProductService;

import src.utils.*;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {
    private EmployeeController employeeController;
    @FXML
    public Pane panelAddEmployee;
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
    public Button btnThem;
    @FXML
    public Button btnHuy;
    @FXML
    public Button btnChonAnh;
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
        panelAddEmployee.setOnMousePressed(mouseEvent -> {
            mousepX = mouseEvent.getSceneX();
            mousepY = mouseEvent.getSceneY();
        });

        panelAddEmployee.setOnMouseDragged(mouseEvent -> {
            panelAddEmployee.getScene().getWindow().setX(mouseEvent.getScreenX() - mousepX);
            panelAddEmployee.getScene().getWindow().setY(mouseEvent.getScreenY() - mousepY);
        });

        txtEmployeeId.setText(Util.generateID(Util.PREFIX_CODE.NV));
        txtEmployeeId.setDisable(true);
    }

    public void setEmployeeController(EmployeeController employee) {
        this.employeeController= employee;
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

    public void btnThem_Click(ActionEvent event) throws FileNotFoundException {
        //VALIDATE
        if(txtEmployeeBonus.getText().trim().equals("") ||
                txtEmployeeName.getText().trim().equals("") ||
                txtEmployeeSalary.getText().toString().trim().equals("") ||
                txtEmployeeShift.getText().trim().equals("") ||
                txtEmployeePassword.getText().trim().equals("") ||
                txtEmployeeUsername.getText().trim().equals("")
        ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Vui lòng nhập đầy đủ dữ liệu!");
            alert.showAndWait();
            return;
        }
        if (true) {
            String employeeId = txtEmployeeId.getText();
            String employeeName = txtEmployeeName.getText();
            String employeeSalary = txtEmployeeSalary.getText();
            String employeeShift = txtEmployeeShift.getText();
            String employeeUsername = txtEmployeeUsername.getText();
            String employeePassword = txtEmployeePassword.getText();

            Employee employee = new Employee(employeeId, employeeName,"", "", "phone", "employee",employeeShift, Long.parseLong(employeeSalary), employeeUsername, "pass");

            EmployeeService.getInstance().addEmployee(employee);
            employeeController.refreshTable();
            txtEmployeeId.setText(Util.generateID(Util.PREFIX_CODE.NV));
            txtEmployeeBonus.setText("");
            txtEmployeePassword.setText("");
            txtEmployeeShift.setText("");
            txtEmployeeSalary.setText("");
            txtEmployeeUsername.setText("");
            
            Util.showSuccess("Quản lý nhân viên", "Thêm nhân viên thành công!");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.showAndWait();
        }
    }
}

