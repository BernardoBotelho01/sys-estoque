package org.sysestoque.dao;

import org.sysestoque.model.Produto;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private static final String CAMINHO_CSV = "produtos.csv";

    public void salvarProduto(Produto produto) {
        try {
            FileWriter salvaArquivo = new FileWriter(CAMINHO_CSV, true);
            salvaArquivo.write(
                    produto.getId() + "," +
                            produto.getNome() + "," +
                            produto.getPreco() + "," +
                            produto.getQuantidade() + "\n"
            );
            salvaArquivo.close(); // IMPORTANTE: fechar o arquivo
        } catch (IOException e) {
            System.out.println("Erro na gravação de arquivo");
            e.printStackTrace();
        }
    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();

        try {
            BufferedReader lerCsv = new BufferedReader(new FileReader(CAMINHO_CSV));
            String linha;
            while ((linha = lerCsv.readLine()) != null) {
                String[] valores = linha.split(",");

                if (valores.length >= 4) {
                    int id = Integer.parseInt(valores[0]);
                    String nome = valores[1];
                    double preco = Double.parseDouble(valores[2]);
                    int quantidade = Integer.parseInt(valores[3]);

                    Produto produto = new Produto(id, nome, preco, quantidade);
                    produtos.add(produto);
                }
            }
            lerCsv.close(); // fechar BufferedReader
        } catch (IOException e) {
            System.out.println("Erro na leitura de arquivo");
            e.printStackTrace();
        }
        return produtos;
    }

    public void atualizarProduto(Produto produtoAtualizado) {
        List<Produto> produtos = listarProdutos();
        try {
            FileWriter salvaArquivoAtualizado = new FileWriter(CAMINHO_CSV);
            for (Produto p : produtos) {
                if (p.getId() == produtoAtualizado.getId()) {
                    salvaArquivoAtualizado.write(
                            produtoAtualizado.getId() + "," +
                                    produtoAtualizado.getNome() + "," +
                                    produtoAtualizado.getPreco() + "," +
                                    produtoAtualizado.getQuantidade() + "\n"
                    );
                } else {
                    salvaArquivoAtualizado.write(
                            p.getId() + "," +
                                    p.getNome() + "," +
                                    p.getPreco() + "," +
                                    p.getQuantidade() + "\n"
                    );
                }
            }
            salvaArquivoAtualizado.close(); // fechar FileWriter
        } catch (IOException e) {
            System.out.println("Erro na gravação de arquivo atualizado");
            e.printStackTrace();
        }
    }

    public void excluirProduto(int id) {
        List<Produto> produtos = listarProdutos();
        try {
            FileWriter excluir = new FileWriter(CAMINHO_CSV);
            for (Produto p : produtos) {
                if (p.getId() != id) {
                    excluir.write(
                            p.getId() + "," +
                                    p.getNome() + "," +
                                    p.getPreco() + "," +
                                    p.getQuantidade() + "\n"
                    );
                }
            }
            excluir.close(); // fechar FileWriter
        } catch (IOException e) {
            System.out.println("Erro na exclusão de produto");
            e.printStackTrace();
        }
    }

    public int carregarProximoId() {
        int maiorId = 0;
        try {
            BufferedReader carregar = new BufferedReader(new FileReader(CAMINHO_CSV));
            String linha;
            while ((linha = carregar.readLine()) != null) {
                String[] valores = linha.split(",");

                if (valores.length >= 1) {
                    int id = Integer.parseInt(valores[0]);

                    if (id > maiorId) {
                        maiorId = id;
                    }
                }
            }
            carregar.close(); // fechar BufferedReader
        } catch (IOException e) {
            System.out.println("Erro em carregar próximo ID");
            maiorId = 0;
        }
        return maiorId + 1;
    }
}
