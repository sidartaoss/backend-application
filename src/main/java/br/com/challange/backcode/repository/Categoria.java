package br.com.challange.backcode.repository;

import br.com.challange.backcode.dto.CategoriaDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    private String descricao;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Produto> produtos = new ArrayList<>();

    public Categoria() {
    }

    private Categoria(
            final Long id,
            final String nome,
            final String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public static Categoria with(
            final Long id,
            final String nome,
            final String descricao) {
        return new Categoria(id, nome, descricao);
    }

    public CategoriaDTO convertToDTO() {
        return new CategoriaDTO(getId(), getNome(), getDescricao());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
