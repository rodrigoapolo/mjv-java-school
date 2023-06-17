import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Cliente cliente1 = new Cliente("Rodrigo", LocalDate.of(2000, 02, 27));
        Cliente cliente2 = new Cliente("Maria", LocalDate.of(1990, 05, 15));

        ContaCorrente conta1 = new ContaCorrente();
        conta1.setCliente(cliente1);
        conta1.depositar(1000.0);
        conta1.setNumeroConta(654);
        conta1.setNumeroAgencia(115);

        ContaCorrente conta2 = new ContaCorrente();
        conta2.setCliente(cliente2);
        conta2.depositar(500.0);
        conta2.setNumeroConta(843);
        conta2.setNumeroAgencia(115);

        System.out.println("Saldo do Cliente 1: " + conta1.consultarSaldo() + "\n");
        System.out.println("Saldo do Cliente 2: " + conta2.consultarSaldo() + "\n");

        try {
            System.out.println("Fazendo um saque: 50.0");
            conta1.sacar(50.0);

            System.out.println("Saldo do Cliente 1: " + conta1.consultarSaldo() + "\n");


            System.out.println("Fazendo uma Transferencia para Conta 2 de 300");
            conta1.transferir(conta2, 300.0);


            System.out.println("Saldo do Cliente 1: " + conta1.consultarSaldo() + "\n");
            System.out.println("Saldo do Cliente 2: " + conta2.consultarSaldo() + "\n");

            List<Transacao> transacaos = conta1.consultarExtrato(LocalDate.now(), LocalDate.now());
            System.out.println("transações: ");
            for (Transacao transacao : transacaos) {
                String extrato = String.format("Tipo: %s%n" +
                        "Data: %s %n" +
                        "Descrição: %s %n" +
                        "Valor: %.2f %n", transacao.getTipo(), transacao.getData().toString(), transacao.getDescricao(), transacao.getValor());
                System.out.println(extrato);
            }

            System.out.println("Conta ativa: " + (conta1.isAtiva() ? "Sim" : "Não") + "\n");


            System.out.println("Cancelado a Conta do " + conta1.getCliente().getNome() + " com número: " + conta1.getNumeroConta());
            conta1.cancelarConta("O cliente pediu para cancelar conta.");


            System.out.println("Conta ativa: " + (conta1.isAtiva() ? "Sim" : "Não") + "\n");


            System.out.println("Cancelado a Conta " + conta1.getCliente().getNome() + " de novo com número: " + conta1.getNumeroConta());
            conta1.cancelarConta("Conta cancelada pelo banco.");


            System.out.println("Conta ativa: " + (conta1.isAtiva() ? "Sim" : "Não") + "\n");
        } catch (ContaException c) {
            System.out.println(c.getMessage());
        }
    }
}