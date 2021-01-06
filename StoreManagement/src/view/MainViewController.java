package src.view;

import src.model.Account;
import src.model.Employee;
import src.service.AccountService;
import com.jfoenix.controls.JFXButton;
//import com.lowagie.text.Anchor;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    public MenuButton lbUsername;
    public MenuItem menuItemInfo;
    public MenuItem menuItemDMK;
    public AnchorPane paneMain;
    public JFXButton btnCollapse;
    public JFXButton btnExpand;
    public AnchorPane paneContent;
    public AnchorPane paneMenu;
    public AnchorPane paneNameApp;
    public Label labelNameApp;
    public JFXButton btnBanHang;
    public JFXButton btnQLNV;
    public JFXButton btnQLKH;
    public JFXButton btnQLSP;
    public JFXButton btnHT;
    public JFXButton btnBCTK;
    public Separator separatorMenu;
    public FontAwesomeIcon imgTrangChu;
    public FontAwesomeIcon imgBCTK;
    public FontAwesomeIcon imgHT;
    public FontAwesomeIcon imgNV;
    public FontAwesomeIcon imgMT;
    public FontAwesomeIcon imgSach;
    public FontAwesomeIcon imgDG;
    private Stage stage;
    private Double mousepX;
    private Double mousepY;

    @FXML
    public Label lbNameAccount;
    @FXML
    public JFXButton btnTrangChu;
    @FXML
    public BorderPane borderPaneMain;
    @FXML
    public AnchorPane tileBar;
    @FXML
    public JFXButton btnMaximize;
    @FXML
    public JFXButton btnClose;
    @FXML
    public FontAwesomeIcon iconClose;
    @FXML
    public JFXButton btnMinimize;
    @FXML
    public FontAwesomeIcon iconMinimize;
    @FXML
    public FontAwesomeIcon iconMaximize;

    Employee User;

    String style = "-fx-background-color:  rgba(19,21,32, 0.8);\n" +
            "    -fx-background-radius:  25px 0px 0px 25px;\n" +
            "    -fx-border-width:  0px 3px 0px 0px;\n" +
            "    -fx-border-color: #4ba9e1;\n";

    String style1 = "-fx-background-color:  transparent;\n" +
            "    -fx-background-radius:  25px 0px 0px 25px;\n" +
            "    -fx-border-width:  0px;\n" +
            "    -fx-border-color: transparent;\n";

    IntegerProperty indexButton = new SimpleIntegerProperty(-1);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tileBar.setOnMousePressed(mouseEvent -> {
            mousepX = mouseEvent.getSceneX();
            mousepY = mouseEvent.getSceneY();
        });

        tileBar.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - mousepX);
            stage.setY(mouseEvent.getScreenY() - mousepY);
        });

        //QuyenDAO.QUYEN currentRole = QuyenDAO.getInstance().getQuyenOfUser(User.getUsername());

        indexButton.addListener((observableValue, number, t1) -> {
            switch ((int)t1){
                case 0:
                    btnTrangChu.setStyle(style);
                    imgTrangChu.setFill(Paint.valueOf("#4ba9e1"));
                    btnTrangChu.setTextFill(Paint.valueOf("#4ba9e1"));
                    break;
                case 1:
                    btnBanHang.setStyle(style);
                    imgDG.setFill(Paint.valueOf("#4ba9e1"));
                    btnBanHang.setTextFill(Paint.valueOf("#4ba9e1"));
                    break;
                case 2:
                    btnQLKH.setStyle(style);
                    imgMT.setFill(Paint.valueOf("#4ba9e1"));
                    btnQLKH.setTextFill(Paint.valueOf("#4ba9e1"));
                    break;
                case 3:
                    btnQLSP.setStyle(style);
                    imgNV.setFill(Paint.valueOf("#4ba9e1"));
                    btnQLSP.setTextFill(Paint.valueOf("#4ba9e1"));
                    break;
                case 4:
                    btnQLNV.setStyle(style);
                    imgBCTK.setFill(Paint.valueOf("#4ba9e1"));
                    btnQLNV.setTextFill(Paint.valueOf("#4ba9e1"));
                    break;
                case 5:
                    btnHT.setStyle(style);
                    imgHT.setFill(Paint.valueOf("#4ba9e1"));
                    btnHT.setTextFill(Paint.valueOf("#4ba9e1"));
                    break;
//                case 2:
//                    btnQLSach.setStyle(style);
//                    imgSach.setFill(Paint.valueOf("#4ba9e1"));
//                    btnQLSach.setTextFill(Paint.valueOf("#4ba9e1"));
//                    break;
            }

            switch ((int)number){
                case 0:
                    btnTrangChu.setStyle(style1);
                    imgTrangChu.setFill(Paint.valueOf("#fff"));
                    btnTrangChu.setTextFill(Paint.valueOf("#fff"));
                    break;
                case 1:
                    btnBanHang.setStyle(style1);
                    imgDG.setFill(Paint.valueOf("#fff"));
                    btnBanHang.setTextFill(Paint.valueOf("#fff"));
                    break;
                case 2:
                    btnQLKH.setStyle(style1);
                    imgMT.setFill(Paint.valueOf("#fff"));
                    btnQLKH.setTextFill(Paint.valueOf("#fff"));
                    break;
                case 3:
                    btnQLSP.setStyle(style1);
                    imgNV.setFill(Paint.valueOf("#fff"));
                    btnQLSP.setTextFill(Paint.valueOf("#fff"));
                    break;
                case 4:
                    btnQLNV.setStyle(style1);
                    imgBCTK.setFill(Paint.valueOf("#fff"));
                    btnQLNV.setTextFill(Paint.valueOf("#fff"));
                    break;
                case 5:
                    btnHT.setStyle(style1);
                    imgHT.setFill(Paint.valueOf("#fff"));
                    btnHT.setTextFill(Paint.valueOf("#fff"));
                    break;
//                case 2:
//                    btnQLSach.setStyle(style1);
//                    imgSach.setFill(Paint.valueOf("#fff"));
//                    btnQLSach.setTextFill(Paint.valueOf("#fff"));
//                    break;
            }
        });
    }

    private void setStyleButtonSelect(){

    }

    public void tiledBarButtonMouseEnter(MouseEvent mouseEvent) {
        btnClose.setStyle("-fx-background-color: red; -fx-background-radius: 15");
        iconClose.setVisible(true);
        btnMinimize.setStyle("-fx-background-color: #d7d744; -fx-background-radius: 15");
        iconMinimize.setVisible(true);
        btnMaximize.setStyle("-fx-background-color: #248c19; -fx-background-radius: 15");
        iconMaximize.setVisible(true);
    }

    public void tiledBarButtonMouseExit(MouseEvent mouseEvent) {
        btnClose.setStyle("-fx-background-color: #d6d6d6; -fx-background-radius: 15");
        iconClose.setVisible(false);
        btnMinimize.setStyle("-fx-background-color: #d6d6d6; -fx-background-radius: 15");
        iconMinimize.setVisible(false);
        btnMaximize.setStyle("-fx-background-color: #d6d6d6; -fx-background-radius: 15");
        iconMaximize.setVisible(false);
    }

    public void btnCloseAction(ActionEvent actionEvent) {
//        System.exit(0);
        stage.close();
    }

    public void btnMinimizeAction(ActionEvent actionEvent) {
        stage.setIconified(!stage.isIconified());
    }

    public void btnMaximizeAction(ActionEvent actionEvent) {
        stage.setFullScreen(!stage.isFullScreen());
    }

    public void setMainStage(Stage stage, Employee user) {
        this.stage = stage;
        this.User = new Employee(user.getID(), user.getName(), user.getDoB(), user.getAddress(), user.getPhone(), user.getRole(), user.getShift(), user.getSalary(),user.getEmployeeName(),user.getPassWord());
//        Account.currentUser = this.User;
        lbNameAccount.setText(this.User.getName());
        lbUsername.setText(this.User.getEmployeeName());
        System.out.println(user.getRole());
        if(user.getRole().equals("quản lý")){
//            menuItemInfo.setVisible(true);
//            menuItemDMK.setVisible(false);
            //nhan vien
//            btnQLNV.setDisable(true);


        }else{
            btnQLNV.setDisable(true);
            menuItemInfo.setVisible(false);
            menuItemDMK.setVisible(true);
        }
    }


    public void btnBanHangAction(ActionEvent actionEvent) {
        try {
            if(indexButton.get()==1)
                return;
            indexButton.set(1);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/BanHang.fxml"));
            AnchorPane banHangView = (AnchorPane) loader.load();
            borderPaneMain.setCenter(banHangView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnQLKHAction(ActionEvent actionEvent) {
        try {
            if(indexButton.get()==2)
                return;
            indexButton.set(2);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/CustomerView.fxml"));
            AnchorPane customerView = (AnchorPane) loader.load();
            borderPaneMain.setCenter(customerView);

            //TODO: Nếu xong phần nào thì setContent vào đúng Tab của nó
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnQLSPAction(ActionEvent actionEvent) {
        try {
            if(indexButton.get()==3)
                return;
            indexButton.set(3);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/ProductView.fxml"));
            AnchorPane productView = (AnchorPane) loader.load();
            borderPaneMain.setCenter(productView);
            //TODO: Nếu xong phần nào thì setContent vào đúng Tab của nó
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnQLNVAction(ActionEvent actionEvent) {
        try {
            /*if(indexButton.get()==4)
                return;*/
            indexButton.set(4);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/EmployeeView.fxml"));
            AnchorPane employeeView = (AnchorPane) loader.load();
            borderPaneMain.setCenter(employeeView);
            //TODO: Nếu xong phần nào thì setContent vào đúng Tab của nó
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void MenuDangXuatClicked(ActionEvent actionEvent) throws IOException {
        String tilte = "Sign Out";
        String message = "Đăng xuất thành công!";
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;

        tray.setAnimationType(type);
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(2000));

        Stage stageLogin = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("LoginView.fxml"));
        AnchorPane rootLayout = loader.load();

        LoginViewController controller = loader.getController();
        controller.setMainStage(stageLogin);

        Scene scene = new Scene(rootLayout);
        scene.setFill(Color.TRANSPARENT);
        stageLogin.setScene(scene);
        stageLogin.initStyle(StageStyle.TRANSPARENT);
        this.stage.hide();
        stageLogin.show();
    }

    /*public void menuItemDMKClicked(ActionEvent actionEvent){
                try {
                    String mk = User.getPassword();
                    // Load the fxml file and create a new stage for the popup dialog.
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../view/DoiMatKhauView.fxml"));
                    AnchorPane page = (AnchorPane) loader.load();

                    DoiMatKhauController controller = loader.getController();
                    controller.setMK(User);

                    // Create the dialog Stage.
                    Stage dialogStage = new Stage();
                    Scene scene = new Scene(page);
                    scene.setFill(Color.TRANSPARENT);
                    dialogStage.initModality(Modality.WINDOW_MODAL);
                    dialogStage.initOwner(paneMain.getScene().getWindow());
                    dialogStage.initStyle(StageStyle.TRANSPARENT);
                    dialogStage.setScene(scene);

                    // Show the dialog and wait until the user closes it
                    dialogStage.showAndWait();

                    if (!mk.equals(User.getPassword())) {
                        int rs = AccountService.getInstance().editUser(User);
                        System.out.println(rs);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

*/
    /*public void btnHeThongAction(ActionEvent actionEvent)
    {
        try {
            if(indexButton.get()==6)
                return;
            indexButton.set(6);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/MenuHeThong.fxml"));
            AnchorPane MTView = (AnchorPane) loader.load();
            borderPaneMain.setCenter(MTView);
            //TODO: Nếu xong phần nào thì setContent vào đúng Tab của nó
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public void btnCollapseClicked(ActionEvent actionEvent) {
        final Animation slide = new Transition(){
            {
                setCycleDuration(Duration.seconds(0.3));
            }
            @Override
            public void interpolate(double v) {
                final double curWidth = 93.0 + 168.0 * (1.0 - v);
                final double a = 168.0*v;

                btnCollapse.setLayoutX(224.0 - a);
                labelNameApp.setPrefWidth(171.0 - a);
                paneNameApp.setPrefWidth(208.0 - a);
                separatorMenu.setPrefWidth(241.5 -a);
                btnTrangChu.setPrefWidth(247.0 -a);
                btnBanHang.setPrefWidth(247.0 -a);
                btnQLKH.setPrefWidth(247.0 -a);
                btnQLSP.setPrefWidth(247.0 -a);
                btnQLNV.setPrefWidth(247.0 -a);
                btnBCTK.setPrefWidth(247.0 -a);
                btnHT.setPrefWidth(247.0 -a);

                AnchorPane.setLeftAnchor(paneContent, 261.0 - a);
                paneMenu.setPrefWidth(curWidth);
            }
        };

        slide.onFinishedProperty().set(actionEvent1 -> {
            labelNameApp.setVisible(false);
            btnExpand.setVisible(true);
            btnCollapse.setVisible(false);
            btnCollapse.setLayoutX(0);
            btnQLSP.contentDisplayProperty().set(ContentDisplay.GRAPHIC_ONLY);
            btnTrangChu.contentDisplayProperty().set(ContentDisplay.GRAPHIC_ONLY);
            btnBanHang.contentDisplayProperty().set(ContentDisplay.GRAPHIC_ONLY);
            btnQLNV.contentDisplayProperty().set(ContentDisplay.GRAPHIC_ONLY);
            btnQLKH.contentDisplayProperty().set(ContentDisplay.GRAPHIC_ONLY);
            btnBCTK.contentDisplayProperty().set(ContentDisplay.GRAPHIC_ONLY);
            btnHT.contentDisplayProperty().set(ContentDisplay.GRAPHIC_ONLY);
        });
        slide.play();
    }

    public void btnExpandClicked(ActionEvent actionEvent) {
        final Animation slide = new Transition(){
            {
                setCycleDuration(Duration.seconds(0.3));
            }
            @Override
            public void interpolate(double v) {
                final double a = 168.0 * (1.0 - v);
                final double curWidth = 261.0 - a;

                btnCollapse.setLayoutX(224.0 - a);
                labelNameApp.setPrefWidth(171.0 - a);
                paneNameApp.setPrefWidth(208.0 - a);
                separatorMenu.setPrefWidth(241.5 -a);
                btnTrangChu.setPrefWidth(247.0 -a);
                btnBanHang.setPrefWidth(247.0 -a);
                btnQLSP.setPrefWidth(247.0 -a);
                btnQLKH.setPrefWidth(247.0 -a);
                btnQLNV.setPrefWidth(247.0 -a);
                btnBCTK.setPrefWidth(247.0 -a);
                btnHT.setPrefWidth(247.0 -a);

                AnchorPane.setLeftAnchor(paneContent, 261.0 - a);
                paneMenu.setPrefWidth(curWidth);
            }
        };

        slide.onFinishedProperty().set(actionEvent1 -> {
            labelNameApp.setVisible(true);
            btnExpand.setVisible(false);
            btnCollapse.setVisible(true);
//            btnCollapse.setLayoutX(0);
            btnBanHang.contentDisplayProperty().set(ContentDisplay.LEFT);
            btnTrangChu.contentDisplayProperty().set(ContentDisplay.LEFT);
            btnQLKH.contentDisplayProperty().set(ContentDisplay.LEFT);
            btnQLNV.contentDisplayProperty().set(ContentDisplay.LEFT);
            btnQLSP.contentDisplayProperty().set(ContentDisplay.LEFT);
            btnBCTK.contentDisplayProperty().set(ContentDisplay.LEFT);
            btnHT.contentDisplayProperty().set(ContentDisplay.LEFT);
        });
        slide.play();
    }
}
