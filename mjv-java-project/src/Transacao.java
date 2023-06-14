import java.time.LocalDate;

public class Transacao {
    private LocalDate data;
    private String descricao;
    private Double valor;
    private Tipo tipo;

    public Transacao(LocalDate data, String descricao, Double valor, Tipo tipo) {
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo.name();
    }

}
