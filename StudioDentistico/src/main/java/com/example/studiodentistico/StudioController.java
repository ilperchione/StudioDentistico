package com.example.studiodentistico;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class StudioController {

    private static Vector<Inserimento> elencoAppuntamenti = new Vector<Inserimento>();
    @FXML
    private AnchorPane inserimentoPanel;
    @FXML
    private TextField txtCognome;
    @FXML
    private TextField txtNome;
    @FXML
    private DatePicker dateData;
    @FXML
    private TextArea txtPatologia;

    public void creaFile() {
        try {
            File file = new File("elencoAppuntamenti.txt");
            if (file.createNewFile()) {
                System.out.println("Elenco creato: " + file.getName());
            } else {
                System.out.println("Aggiunto all'elenco.");
            }
        } catch (IOException e) {
            System.out.println("Errore");
            e.printStackTrace();
        }
    }

    public static Vector<Inserimento> visualizzaElenco() throws FileNotFoundException {
        File file = new File("elencoAppuntamenti.txt");
        Scanner reader = new Scanner(file);
        Vector<Inserimento> vettore = new Vector<>();
        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            String[] dataVero = data.split(",");
            if (dataVero.length >= 4) {
                vettore.add(new Inserimento(dataVero[0], dataVero[1], dataVero[2], dataVero[3]));
            } else {
                System.out.println("elementi riga");
            }
        }
        reader.close();
        return vettore;
    }

    public void scriviFile(String stringa) {
        try {
            FileWriter fileWriter = new FileWriter("elencoAppuntamenti.txt", true);
            fileWriter.write(stringa);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Errore");
            e.printStackTrace();
        }
    }

    public String concatenaStringa(Inserimento inserimento) {
        String stringa = "";
        stringa += inserimento.getCognome() + "," + inserimento.getNome() + "," + inserimento.getPatologia() + "," + inserimento.getData() + "\n";
        return stringa;
    }


    public void crea(Vector<Inserimento> elencoAppuntamenti) {
        this.elencoAppuntamenti = elencoAppuntamenti;
    }

    @FXML
    protected void rimuovi() {
        txtNome.setText("");
        txtCognome.setText("");
        dateData.setValue(null);
        txtPatologia.setText("");
    }

    @FXML
    protected void salva() {
        if (txtCognome.getText().isEmpty() || txtNome.getText().isEmpty() || txtPatologia.getText().isEmpty()
                || dateData.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Problema durante l'inserimento");
            alert.setContentText("Si prega di compilare tutti i campi");
            alert.showAndWait();
        } else {
            String data = dateData.getValue().toString();
            scriviFile(concatenaStringa(new Inserimento(txtCognome.getText(), txtNome.getText(), txtPatologia.getText(), data)));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento avvenuto con successo");
            alert.setContentText("Inserimento avvenuto con successo");
            alert.show();
            txtCognome.setText("");
            txtNome.setText("");
            txtPatologia.setText("");
            dateData.setValue(null);
        }
    }

    @FXML
    protected void esci() {
        Stage stage;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("-Esci-");
        alert.setHeaderText("Finestra di inserimento dati");
        alert.setContentText("Desideri uscire?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) inserimentoPanel.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    protected void onInserimentoPazienteAction() throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("inserimento.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root.load(), 600, 400));
        stage.setTitle("Inserimento Paziente");
        stage.setResizable(false);
        StudioController controller = root.getController();
        controller.crea(elencoAppuntamenti);
        stage.show();
        creaFile();
    }

    @FXML
    protected void onAppuntamentiPazientiAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("appuntamenti.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(loader.load(), 600, 400));
        stage.setTitle("Appuntamenti Pazienti");
        stage.setResizable(false);
        Appuntamenti controller = loader.getController();
        controller.crea();
        stage.show();
    }

}
