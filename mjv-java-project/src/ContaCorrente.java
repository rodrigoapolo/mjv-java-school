import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ContaCorrente {
    private Cliente cliente;
    private Double saldo;
    private Integer numeroConta;
    private Integer numeroAgencia;
    private boolean ativa;
    private String justificativa;
    private List<Transacao> transacaos = new ArrayList<>();


    public ContaCorrente(Cliente cliente, Double saldo, Integer numeroConta, Integer numeroAgencia) {
        this.cliente = cliente;
        this.saldo = saldo;
        this.numeroConta = numeroConta;
        this.numeroAgencia = numeroAgencia;
        this.ativa = true;
    }

    public List<Transacao> consultarExtrato(LocalDate di, LocalDate df){
        return transacaos;
    }

    public void cancelarConta(String justificativa) throws ContaException {
        if(isAtiva()){
            ativa = false;
            this.justificativa = justificativa;
        }else {
            throw new ContaException("Conta ja cancelada: "+ this.justificativa);
        }
    }
    public void sacar(Double valor) throws ContaException{
        if(saldo >= valor && isAtiva()){
            saldo -= valor;
            incluirTransaca(LocalDate.now(), "Saque em especie", valor, Tipo.SAQUE);
        }else{
            isConta("Conta com saldo insuficiente!");
        }
    }

    private void isConta(String msg) throws ContaException {
        if (isAtiva()) {
            throw new ContaException("Conta com saldo insuficiente!");
        }else {
            throw new ContaException("Conta ja cancelada: "+ this.justificativa);
        }
    }

    private void incluirTransaca(LocalDate date, String descricao, Double valor, Tipo tipo){
            transacaos.add(new Transacao(date, descricao, valor, tipo));
    }

    public void transferir(ContaCorrente contaDestino, Double valor) throws Exception{
        if(saldo >= valor && isAtiva()){
            contaDestino.setSaldo(contaDestino.consultarSaldo() + valor);
            saldo -= valor;
            incluirTransaca(LocalDate.now(), "Transferência para conta "+contaDestino.cliente.getNome(), valor, Tipo.TRANSFERENCIA);
        }else{
            isConta("Conta com saldo insuficiente!");
        }

    }
    public Double consultarSaldo(){
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Integer getNumeroConta() {
        return numeroConta;
    }

    public Integer getNumeroAgencia() {
        return numeroAgencia;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public String getJustificativa() {
        return justificativa;
    }
}
