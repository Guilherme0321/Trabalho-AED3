import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        new File("dados/livros.db").delete();
        new File("dados/livros.hash_d.db").delete();
        new File("dados/livros.hash_c.db").delete();

        /**
         * Arquivo onde terá os arquivos que já foram deletados;
         * Caso o arquivo espaço do arquivo deletado já tenha sido reutilizado, será
         * adicionado uma lápide *;
         */
        new File("deletados.db").delete();

        new File("dados").mkdir();

        Arquivo<Livro> arqLivros;

        try {
            arqLivros = new Arquivo<>("livros", Livro.class.getConstructor());
            int opcao;
            do {
                opcao = menu(console, arqLivros);
            } while (opcao != 0);

        } catch (Exception e) {
            e.printStackTrace();
        }

        console.close();
    }

    private static int menu(Scanner console, Arquivo<Livro> arqLivros) throws Exception {
        int opcao;
        System.out.println("\n\n-------------------------------");
        System.out.println("              MENU");
        System.out.println("-------------------------------");
        System.out.println("1 - Inserir Livro");
        System.out.println("2 - Buscar por ID");
        System.out.println("3 - Excluir por ID");
        System.out.println("0 - Sair");

        try {
            opcao = Integer.valueOf(console.nextLine());
        } catch (NumberFormatException e) {
            opcao = -1;
        }

        switch (opcao) {
            case 1:
                System.out.println("Digite o ISBN do livro:");
                String isbn = console.nextLine();
                System.out.println("Digite o título do livro:");
                String titulo = console.nextLine();
                System.out.println("Digite o preço do livro:");
                float preco = Float.parseFloat(console.nextLine());
                Livro livro = new Livro(-1, isbn, titulo, preco);
                int id = arqLivros.create(livro);
                System.out.println("Livro criado com o ID: " + id);
                break;
            case 2:
                System.out.println("Digite o ID do livro:");
                int idBusca = Integer.parseInt(console.nextLine());
                Livro livroBusca = arqLivros.read(idBusca);
                if (livroBusca != null) {
                    System.out.println("Livro encontrado:");
                    System.out.println(livroBusca);
                } else {
                    System.out.println("Livro não encontrado");
                }
                break;
            case 3:
                System.out.println("Digite o ID do livro:");
                int idExclusao = Integer.parseInt(console.nextLine());
                if (arqLivros.delete(idExclusao)) {
                    System.out.println("Livro excluído!");
                } else {
                    System.out.println("Livro não encontrado");
                }
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida");
        }
        return opcao;
    }
}
