package com.example.studiodentistico;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.FileNotFoundException;
import java.util.Vector;

import static com.example.studiodentistico.StudioController.visualizzaElenco;

public class Appuntamenti {
    @FXML
    private ListView<String> elenco;
    private Vector<Inserimento> elencoAppuntamenti;

    public void crea() throws FileNotFoundException {
        creaElenco();
    }

    public void creaElenco() throws FileNotFoundException {
        ObservableList<String> items = FXCollections.observableArrayList();
        setElencoAppuntamenti();
        for (Inserimento inserimento : elencoAppuntamenti) {
            items.add(inserimento.getCognome() + " " + inserimento.getNome() + "                                                                                 " + inserimento.getData());
        }
        elenco.setItems(items);
    }

    public void setElencoAppuntamenti() throws FileNotFoundException {
        this.elencoAppuntamenti = visualizzaElenco();
    }

    public void rimuoviAppuntamento() {
        ObservableList<String> items = elenco.getItems();
        int selectedIndex = elenco.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < items.size()) {
            items.remove(selectedIndex);
        }
    }
}
