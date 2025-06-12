package org.sysestoque.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.sysestoque.dao.ProdutoDAO;
import org.sysestoque.model.Produto;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EstoqueController implements Initializable {
    @FXML
    public  TextField txtId;
    @FXML
    public TextField txtNome;
    @FXML
    public TextField txtPreco;
    @FXML
    public TextField txtQuantidade;
    @FXML
    public Label labMensagem;

    @FXML
    public TableView<Produto> tbProdutos;
    @FXML
    public TableColumn<Produto, Integer> columnId;
    @FXML
    public TableColumn<Produto, String> columnNome;
    @FXML
    public TableColumn<Produto, Double> columnPreco;
    @FXML
    public TableColumn<Produto, Integer> columnQuantidade;

    Produto produto;
    List<Produto> listaProdutos;
    ProdutoDAO produtoDAO = new ProdutoDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.produto = new Produto();
        this.listaProdutos = new ArrayList<>();

        inicializarTabela();
        carregarProdutosNaTabela();

        tbProdutos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                preencherCamposComProduto(newSelection);
            }
        });
    }

    public void inicializarTabela() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
    }

    public void carregarProdutosNaTabela() {
        List<Produto> produtos = produtoDAO.listarProdutos();
        ObservableList<Produto> observarProdutos = FXCollections.observableArrayList(produtos);
        tbProdutos.setItems(observarProdutos);
    }

    public void preencherCamposComProduto(Produto produto) {
        txtId.setText(String.valueOf(produto.getId()));
        txtNome.setText(produto.getNome());
        txtPreco.setText(String.valueOf(produto.getPreco()));
        txtQuantidade.setText(String.valueOf(produto.getQuantidade()));
    }

    @FXML
    protected void onClickAdicionar() {
        try {
            String nome = txtNome.getText();
            double preco = Double.parseDouble(txtPreco.getText());
            int quantidade = Integer.parseInt(txtQuantidade.getText());

            int id = produtoDAO.carregarProximoId();

            Produto novoProduto = new Produto(id, nome, preco, quantidade);

            produtoDAO.salvarProduto(novoProduto);

            labMensagem.setText("Produto cadastrado com sucesso!");

            carregarProdutosNaTabela();

            txtNome.clear();
            txtPreco.clear();
            txtQuantidade.clear();

        } catch (NumberFormatException e) {
            labMensagem.setText("Erro: preço ou quantidade inválidas!");
        } catch (Exception e) {
            labMensagem.setText("Erro ao cadastrar produto.");
            e.printStackTrace();
        }
    }

    @FXML
    protected void onClickAtualizar() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nome = txtNome.getText();
            double preco = Double.parseDouble(txtPreco.getText());
            int quantidade = Integer.parseInt(txtQuantidade.getText());

            Produto produtoAtualizado = new Produto(id, nome, preco, quantidade);

            produtoDAO.atualizarProduto(produtoAtualizado);

            labMensagem.setText("Produto atualizado com sucesso!");

            carregarProdutosNaTabela();


        } catch (NumberFormatException e) {
            labMensagem.setText("Erro: ID, preço ou quantidade inválidos.");
        }
    }

    @FXML
    protected void onClickExcluir() {
        try {
            int id = Integer.parseInt(txtId.getText());

            produtoDAO.excluirProduto(id);

            this.labMensagem.setText("Produto excluído com sucesso!");

            carregarProdutosNaTabela();

            txtId.clear();
            txtNome.clear();
            txtPreco.clear();
            txtQuantidade.clear();

        } catch (NumberFormatException e) {
            labMensagem.setText("Erro: ID inválido.");
        }
    }

}
