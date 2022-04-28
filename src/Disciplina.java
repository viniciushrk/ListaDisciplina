import java.nio.charset.StandardCharsets;

public class Disciplina {
    private String nome;
    private String professor;
    private int cargaHoraria;

    public Disciplina(int cargaHoraria, String nome, String professor) {
        this.setCargaHoraria(cargaHoraria);
        this.setNome(nome);
        this.setProfessor(professor);
    }

    // getProfessor
    public String getProfessor() {
        return professor;
    }

    // setProfessor
    public void setProfessor(String professor) {
        this.professor = professor;
    }

    // create get e set cargaHoraria
    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String toString() {
        return cargaHoraria + " Hrs. "
                + new String(nome.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8) + " ( "
                + professor + " )";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}