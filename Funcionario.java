import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() { return salario; }
    public String getFuncao() { return funcao; }

    public void aplicarAumentoPercentual(BigDecimal percentual) {
        // novo = atual * (1 + p/100)
        BigDecimal fator = BigDecimal.ONE.add(percentual.divide(new BigDecimal("100")));
        this.salario = this.salario.multiply(fator).setScale(2, java.math.RoundingMode.HALF_UP);
    }
}
