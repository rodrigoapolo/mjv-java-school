import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ContaCorrente {
    private Cliente cliente;
    private Double saldo = 0.0;
    private Integer numeroConta;
    private Integer numeroAgencia;
    private boolean ativa = true;
    private String justificativa;
    private List<Transacao> transacaos = new ArrayList<>();

    public List<Transacao> consultarExtrato(LocalDate dataInicio, LocalDate dataFinal){
        List<Transacao> extrato = transacaos.stream()
                .filter( t -> t.getData().isAfter(dataInicio) && t.getData().isBefore(dataFinal))
                .collect(Collectors.toList());
        return extrato;
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
            throw new ContaException(msg);
        }else {
            throw new ContaException("Conta ja cancelada: "+ this.justificativa);
        }
    }

    private void incluirTransaca(LocalDate date, String descricao, Double valor, Tipo tipo){
            transacaos.add(new Transacao(date, descricao, valor, tipo));
    }

    public void transferir(ContaCorrente contaDestino, Double valor) throws ContaException{
        if(saldo >= valor && isAtiva()){
            contaDestino.depositar(valor);
            saldo -= valor;
            incluirTransaca(LocalDate.now(), "Transferência para conta "+contaDestino.cliente.getNome(), valor, Tipo.TRANSFERENCIA);
        }else{
            isConta("Conta "+this.cliente.getNome()+" com saldo insuficiente!");
        }

    }
    public Double consultarSaldo(){
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void depositar(Double valor) throws ContaException{
        if (isAtiva()) {
            incluirTransaca(LocalDate.now(), "Deposito", valor, Tipo.DEPOSITO);
            this.saldo += valor;
        }else {
            isConta("");
        }
    }

    public Integer getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Integer numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Integer getNumeroAgencia() {
        return numeroAgencia;
    }

    public void setNumeroAgencia(Integer numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public String getJustificativa() {
        return justificativa;
    }
}
