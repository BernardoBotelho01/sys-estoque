package org.sysestoque.model;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private int quantidade;

    public Produto() {
    }

    // Construtor usado na DAO (com ID passado)
    public Produto(int id, String nome, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "{" +
                "id= " + id +
                ", nome= " + nome +
                ", preco= " + preco +
                ", quantidade= " + quantidade +
                '}';
    }
}
