package controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import javax.swing.text.Element;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class QrGeneratorHome {
    @FXML
    private Button btnClear;
    @FXML
    private ImageView generatedImage;

    @FXML
    private Button btnChoosePath;

    @FXML
    private TextField txtQrPath;

    @FXML
    private Button btnGenerate;

    @FXML
    private Button btnSaveAs;

    @FXML
    private TextField txtQrDetails;

    @FXML
    private TextField txtQrName;

    @FXML
    void btnChoosePathOnAction(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();

        //set title for dialog
        directoryChooser.setTitle("Select a path for save qr");

        //optional!!set initial directory
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        //set stage
        Stage stage = new Stage();
        File selectedDirectory = directoryChooser.showDialog(stage);

        //get file path and assign to a variable
        if (selectedDirectory != null) {
            String filePath = selectedDirectory.getAbsolutePath();
            txtQrPath.setText(filePath);
        }else {
            stage.close();
        }
    }

    @FXML
    void btnGenerateOnAction(ActionEvent event) {
        String qrName = txtQrName.getText()+".jpg";
        String qrDetails = txtQrDetails.getText();
        String qrPath = txtQrPath.getText()+ "\\" + qrName;


        try {
            // Generate the QR code
            BitMatrix matrix = new MultiFormatWriter().encode(qrDetails, BarcodeFormat.QR_CODE, 500, 500);

            // Write to file
            MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(qrPath));
            new Alert(Alert.AlertType.INFORMATION,"QR Code generated successfully at: " + qrPath).showAndWait();

            //set image
            Image image = new Image(qrPath);
            generatedImage.setImage(image);

        } catch (WriterException | IOException e) {
            new Alert(Alert.AlertType.ERROR,"Error generating QR Code: " + e.getMessage()).showAndWait();
        }
    }
    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtQrName.setText("");
        txtQrDetails.setText("");
        txtQrPath.setText("");
        generatedImage.setImage(null);
    }

    @FXML
    void qrDetails(ActionEvent event) {btnChoosePath.requestFocus();}
    @FXML
    void qrName(ActionEvent event) {
        txtQrDetails.requestFocus();
    }
    @FXML
    void qrPath(ActionEvent event) {
        btnGenerate.requestFocus();
    }

}
