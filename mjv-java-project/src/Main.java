import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Rodrigo", LocalDate.of(2000,02,27));
        ContaCorrente conta1 = new ContaCorrente(cliente1, 1000.0, 645, 115);

        System.out.println("Saldo: "+conta1.consultarSaldo()+"\n");

        try {
            System.out.println("Fazendo um saque: 50.0");
            conta1.sacar(50.0);
        } catch (ContaException e) {
            System.out.println(e.getMessage());
        }finally {
            System.out.println();
        }

        System.out.println("Saldo: "+conta1.consultarSaldo()+"\n");

        List<Transacao> transacaos = conta1.consultarExtrato(LocalDate.now(), LocalDate.now());
        System.out.println("transações: ");
        for (Transacao transacao: transacaos) {
            String extrato = String.format("Tipo: %s%n" +
                    "Data: %s %n" +
                    "Descrição: %s %n" +
                    "Valor: %.2f %n", transacao.getTipo(), transacao.getData().toString(), transacao.getDescricao(), transacao.getValor());
            System.out.println(extrato);
        }

        System.out.println("Conta ativa: " + (conta1.isAtiva() ? "Sim" : "Não")+"\n");

        try {
            System.out.println("Cancelado a Conta do "+conta1.getCliente().getNome()+" com número: "+conta1.getNumeroConta());
            conta1.cancelarConta("O cliente pediu para cancelar conta.");
        } catch (ContaException e) {
            System.out.println(e.getMessage());
        }finally {
            System.out.println();
        }

        System.out.println("Conta ativa: " + (conta1.isAtiva() ? "Sim" : "Não")+"\n");

        try {
            System.out.println("Cancelado a Conta do "+conta1.getCliente().getNome()+" com número: "+conta1.getNumeroConta());
            conta1.cancelarConta("Conta cancelada pelo banco.");
        } catch (ContaException e) {
            System.out.println(e.getMessage());
        }finally {
            System.out.println();
        }

        System.out.println("Conta ativa: " + (conta1.isAtiva() ? "Sim" : "Não")+"\n");
    }
}