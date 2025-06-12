package org.sysestoque;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.sysestoque.dao.ProdutoDAO;
import org.sysestoque.utils.PathFxml;
import java.io.FileInputStream;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // carrega todo arquivo xml
        FXMLLoader fxmlLoader = new FXMLLoader();
        // criação do palco que e a janela
        Parent root = fxmlLoader.load(new FileInputStream(PathFxml.pathBase() + "\\main-view.fxml"));
        // criação da cena, e detro dela passo a janela, que e root
        Scene scene = new Scene(root, 700, 500);
        stage.setTitle("SysEstoque");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        System.out.println("Proximo id disponvel:");
        System.out.println(produtoDAO.carregarProximoId());
        System.out.println("----------------------------------------");
        System.out.println("Lista de todos os produto:");
        System.out.println(produtoDAO.listarProdutos());
        launch();
    }

}
