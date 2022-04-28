import java.io.IOException;
import java.util.Scanner;

public class App {
    protected static ListaLigada<Disciplina> lista = new ListaLigada<Disciplina>();

    public static void main(String[] args) throws Exception {
        // generate option interact user and call the method to process the option
        carregaDisciplinas();
        menu();
    }

    public static void adicionaDisciplina() throws IOException {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Digite o nome da disciplina: ");
            String nome = sc.nextLine();
            System.out.println("Digite o nome do professor: ");
            String professor = sc.nextLine();
            System.out.println("Digite a carga horária: ");
            int cargaHoraria = sc.nextInt();
            Disciplina disciplina = new Disciplina(cargaHoraria, nome, professor);
            System.out.println("Disciplina adicionada com sucesso!");

            lista.adicionaFim(disciplina);
            String texto = cargaHoraria + "," + nome + "," + professor + ";\n";

            salvarDisciplinasArquivo(texto);
            menu();
        } catch (Exception e) {
            System.out.println("Erro ao adicionar disciplina!" + e.getMessage());
            menu();
        }
    }

    public static void salvarDisciplinasArquivo(String texto) throws IOException {
        FileReaderWrite.escreverArquivo("Disciplinas.txt", texto);
    }

    public static void carregaDisciplinas() throws IOException {

        var dadosArquivo = FileReaderWrite.carregarArquivo("Disciplinas.txt");

        var texto = FileReaderWrite.textoParaVetor(dadosArquivo, ";");

        for (int i = 0; i < texto.length; i++) {
            var vetorString = FileReaderWrite.textoParaVetor(texto[i], ",");

            if (vetorString.length == 3 && !contains(vetorString, " ") || !contains(vetorString, "")) {
                var disciplina = new Disciplina(Integer.parseInt(vetorString[0]),
                        vetorString[1], vetorString[2]);
                lista.adicionaFim(disciplina);
            }
        }
    }

    // create contains in array string
    public static boolean contains(String[] array, String value) {
        for (String element : array) {
            if (element.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public static void menu() throws IOException {
        try (Scanner optionsService = new Scanner(System.in)) {
            System.out.println("====== atividade  ========");
            System.out.println("[1] - adicionar disciplina");
            System.out.println("[2] - listar disciplinas");
            System.out.println("[3] - excluir disciplinas");
            System.out.println("[0] - sair do sistema");
            System.out.println("==========================");
            int option = optionsService.nextInt();

            switch (option) {
                case 1: {
                    adicionaDisciplina();
                    break;
                }
                case 2: {
                    System.out.println("Lista de disciplinas: ");
                    System.out.println(lista.toString());
                    menu();
                    break;
                }
                case 3: {
                    System.out.println("Digite a posicao da disciplina: ");
                    Scanner scanner = new Scanner(System.in);
                    String nome = scanner.nextLine();
                    // int posicao = scanner.nextInt();
                    // lista.exclui(posicao);
                    lista.removeItem(nome);
                    System.out.println("Disciplina excluida com sucesso!");
                    menu();
                    break;
                }
                case 0: {
                    System.out.println("Saindo do sistema...");
                    break;
                }
                default: {
                    System.out.println("Opção inválida!");
                    menu();
                    break;
                }
            }
        }
    }
}
