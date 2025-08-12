import java.time.LocalDate;

public class Pessoa {
    private String nome;
    private LocalDate dataNascimento;

    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() { return nome; }
    public LocalDate getDataNascimento() { return dataNascimento; }

    public int getIdade() {
        return java.time.Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Pessoa{nome='" + nome + "', nascimento=" + dataNascimento + "}";
    }
}
