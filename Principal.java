// Principal.java
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private static final DateTimeFormatter FMT_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DecimalFormat FMT_NUM;

    static {
        // ponto como milhar e vírgula como decimal
        DecimalFormatSymbols br = new DecimalFormatSymbols(new Locale("pt", "BR"));
        br.setDecimalSeparator(',');
        br.setGroupingSeparator('.');
        FMT_NUM = new DecimalFormat("#,##0.00", br);
    }

    public static void main(String[] args) {
        // 3.1 – Inserir todos os funcionários
        List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
                new Funcionario("Maria",  LocalDate.of(2000, 10, 18), bd("2009.44"), "Operador"),
                new Funcionario("João",   LocalDate.of(1990,  5, 12), bd("2284.38"), "Operador"),
                new Funcionario("Caio",   LocalDate.of(1961,  5,  2), bd("9836.14"), "Coordenador"),
                new Funcionario("Miguel", LocalDate.of(1988, 10, 14), bd("19119.88"), "Diretor"),
                new Funcionario("Alice",  LocalDate.of(1995,  1,  5), bd("2234.68"), "Recepcionista"),
                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), bd("1582.72"), "Operador"),
                new Funcionario("Arthur", LocalDate.of(1993,  3, 31), bd("4071.84"), "Contador"),
                new Funcionario("Laura",  LocalDate.of(1994,  7,  8), bd("3017.45"), "Gerente"),
                new Funcionario("Heloísa",LocalDate.of(2003,  5, 24), bd("1606.85"), "Eletricista"),
                new Funcionario("Helena", LocalDate.of(1996,  9,  2), bd("2799.93"), "Gerente")
        ));
        

        // 3.2 – Remover “João”
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));

        // 3.3 – Imprimir todos com formatação
        System.out.println("== Funcionários ==");
        imprimir(funcionarios);

        // 3.4 – Aumento de 10%
        funcionarios.forEach(f -> f.aplicarAumentoPercentual(new BigDecimal("10")));

        System.out.println("\n== Após aumento de 10% ==");
        imprimir(funcionarios);

        // 3.5 – Agrupar por função
        Map<String, List<Funcionario>> porFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao, LinkedHashMap::new, Collectors.toList()));

        // 3.6 – Imprimir agrupados por função
        System.out.println("\n== Agrupados por função ==");
        porFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            imprimir(lista);
            System.out.println();
        });

        // 3.8 – Aniversariantes dos meses 10 e 12
        System.out.println("== Aniversariantes em outubro e dezembro ==");
        funcionarios.stream()
                .filter(f -> {
                    int m = f.getDataNascimento().getMonthValue();
                    return m == 10 || m == 12;
                })
                .forEach(f -> System.out.printf("%s - %s%n", f.getNome(), f.getDataNascimento().format(FMT_DATA)));

        // 3.9 – Funcionário com maior idade
        Funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento)) // data mais antiga => mais velho
                .orElse(null);
        if (maisVelho != null) {
            System.out.printf("%n== Mais velho ==%n%s - %d anos%n", maisVelho.getNome(), maisVelho.getIdade());
        }

        // 3.10 – Ordem alfabética
        System.out.println("\n== Ordem alfabética ==");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome, String.CASE_INSENSITIVE_ORDER))
                .forEach(f -> System.out.println(f.getNome()));

        // 3.11 – Total dos salários
        BigDecimal total = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\n== Total dos salários ==");
        System.out.println("R$ " + FMT_NUM.format(total));

        // 3.12 – Quantos salários mínimos (R$1212,00)
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\n== Salários mínimos por funcionário ==");
        funcionarios.forEach(f -> {
            BigDecimal qtd = f.getSalario().divide(salarioMinimo, 2, java.math.RoundingMode.HALF_UP);
            System.out.printf("%s: %s salários mínimos%n", f.getNome(), FMT_NUM.format(qtd));
        });
    }

    private static BigDecimal bd(String v) { return new BigDecimal(v); }

    private static void imprimir(List<Funcionario> lista) {
        lista.forEach(f -> System.out.printf(
                "%s | Nasc.: %s | Salário: R$ %s | Função: %s%n",
                f.getNome(),
                f.getDataNascimento().format(FMT_DATA),
                FMT_NUM.format(f.getSalario()),
                f.getFuncao()
        ));
    }
}
