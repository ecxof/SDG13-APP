package com.example.sdg13ver5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;

public class AdminLoginController {

    @FXML private PasswordField password;
    @FXML private TextField passwordVisible;
    @FXML private Button togglePasswordBtn;
    @FXML private FontIcon togglePasswordIcon;
    @FXML private Label errorLabel;

    @FXML
    private void togglePassword() {
        boolean showing = passwordVisible.isVisible();
        if (showing) {
            password.setText(passwordVisible.getText());
            passwordVisible.setVisible(false);
            passwordVisible.setManaged(false);
            password.setVisible(true);
            password.setManaged(true);
            togglePasswordIcon.setIconLiteral("fth-eye");
        } else {
            passwordVisible.setText(password.getText());
            password.setVisible(false);
            password.setManaged(false);
            passwordVisible.setVisible(true);
            passwordVisible.setManaged(true);
            togglePasswordIcon.setIconLiteral("fth-eye-off");
        }
    }

    private String currentPassword() {
        return passwordVisible.isVisible() ? passwordVisible.getText() : password.getText();
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        if (Application.ADMIN_PASS.equals(currentPassword())) {
            StartController.openShell(event, "adminshell.fxml", 1280, 780);
        } else {
            errorLabel.setText("Invalid admin password");
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        StartController.swap(event, "start.fxml", 960, 600);
    }
}
