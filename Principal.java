import java.io.File;
import java.util.Scanner;

import arquivos.ArquivoAutores;
import arquivos.ArquivoCategorias;
import arquivos.ArquivoLivros;
import entidades.Autor;
import entidades.Categoria;
import entidades.Livro;
import lista_invertida.ListaInvertida;

public class Principal {

  private static Scanner console = new Scanner(System.in);

  public static void main(String[] args) throws Exception {

    ListaInvertida lista = new ListaInvertida(0, null, null);
    //System.out.println(lista.loadStopwords());


      try {
          System.out.println("Teste 1 // AUTOR");
          Autor autorteste = new Autor(1, "Árvore de natal parábola");
  
          // Cifrar
          byte[] dadosCifrados = autorteste.toByteArray();
          System.out.println("Dados cifrados: ");
          for (byte b : dadosCifrados) {
              System.out.print(b + " ");
          }
          System.out.println();
  
          // Reconstruir
          Autor autorReconstruido = new Autor();
          autorReconstruido.fromByteArray(dadosCifrados);
  
          // Ver se os dados são os mesmos
          System.out.println("Dados do objeto original:");
          System.out.println(autorteste);
  
          System.out.println("Dados do objeto decifrado:");
          System.out.println(autorReconstruido);
  
          boolean testeID = autorteste.getID() == autorReconstruido.getID();
          boolean testeNome = autorteste.getNome().equals(autorReconstruido.getNome());
  
          if (testeID && testeNome) {
              System.out.println("deu certo!");
          } else {
              System.out.println("falhou");
          }

          System.out.println();
  
          System.out.println("Teste 2 // CATEGORIA");
          Categoria categoriateste = new Categoria(10, "çççaaa áéê ana");
  
          // Cifrar
          byte[] dadosCifrados2 = categoriateste.toByteArray();
          System.out.println("Dados cifrados: ");
          for (byte b : dadosCifrados2) {
              System.out.print(b + " ");
          }
          System.out.println();
  
          // Reconstruir
          Categoria categoriareconstruida = new Categoria();
          categoriareconstruida.fromByteArray(dadosCifrados2);
  
          // Ver se os dados são os mesmos
          System.out.println("Dados do objeto original:");
          System.out.println(categoriateste);
  
          System.out.println("Dados do objeto decifrado:");
          System.out.println(categoriareconstruida);
  
          boolean testeIDCategoria = categoriateste.getID() == categoriareconstruida.getID();
          boolean testeNomeCategoria = categoriateste.getNome().equals(categoriareconstruida.getNome());
  
          if (testeIDCategoria && testeNomeCategoria) {
              System.out.println("deu certo!");
          } else {
              System.out.println("falhou");
          }

          System.out.println();
  
          System.out.println("Teste 3 // LIVRO");
          Livro livroteste = new Livro(1, "1234567890123", "O Senhor dos Anéis", 59.99f, 2);
  
          // Cifrar
          byte[] dadosCifrados3 = livroteste.toByteArray();
          System.out.println("Dados cifrados: ");
          for (byte b : dadosCifrados3) {
              System.out.print(b + " ");
          }
          System.out.println();
  
          // Reconstruir
          Livro livroreconstruido = new Livro();
          livroreconstruido.fromByteArray(dadosCifrados3);
  
          // Ver se os dados são os mesmos
          System.out.println("Dados do objeto original:");
          System.out.println(livroteste);
  
          System.out.println("Dados do objeto decifrado:");
          System.out.println(livroreconstruido);
  
          boolean testeIDLivro = livroteste.getID() == livroreconstruido.getID();
          boolean testeIsbnLivro = livroteste.getIsbn().equals(livroreconstruido.getIsbn());
          boolean testeTituloLivro = livroteste.getTitulo().equals(livroreconstruido.getTitulo());
          boolean testePrecoLivro = livroteste.getPreco() == livroreconstruido.getPreco();
          boolean testeIdCategoriaLivro = livroteste.getIdCategoria() == livroreconstruido.getIdCategoria();
  
          if (testeIDLivro && testeIsbnLivro && testeTituloLivro && testePrecoLivro && testeIdCategoriaLivro) {
              System.out.println("deu certo!");
          } else {
              System.out.println("falhou");
          }
  
      } catch (Exception e) {
          e.printStackTrace();
      }
  
  



    

    /*try {

      int opcao;
      do {
        System.out.println("\n\n\nBOOKAEDS 1.0");
        System.out.println("------------");
        System.out.println("\n> Início");
        System.out.println("\n1) Categorias");
        System.out.println("2) Autores");
        System.out.println("3) Livros");
        System.out.println("4) Fazer BACKUP");
        System.out.println("5) Restaurar BACKUP");
        System.out.println("\n9) Reiniciar BD");
        System.out.println("\n0) Sair");

        System.out.print("\nOpção: ");
        try {
          opcao = Integer.valueOf(console.nextLine());
        } catch (NumberFormatException e) {
          opcao = -1;
        }

        LZWController lzw = new LZWController("backup");

        switch (opcao) {
          case 1:
            (new MenuCategorias()).menu();
            break;
          case 2:
            (new MenuAutores()).menu();
            break;
          case 3:
            (new MenuLivros()).menu();
            break;
          case 4:
            lzw.compressData();
            break;
          case 5:
            lzw.makeBackupMenu(console);
            break;
          case 9:
            preencherDados();
            break;
          case -1: // Tratamento de erro
            break;
          case 0:
            break;
          default:
            System.out.println("Opção inválida");
        }
      } while (opcao != 0);

    } catch (Exception e) {
      e.printStackTrace();
    }*/
  }

  public static void preencherDados() {
    try {
      new File("dados/categorias.db").delete();
      new File("dados/categorias.hash_d.db").delete();
      new File("dados/categorias.hash_c.db").delete();
      new File("dados/autores.db").delete();
      new File("dados/autores.hash_d.db").delete();
      new File("dados/autores.hash_c.db").delete();
      new File("dados/livros.db").delete();
      new File("dados/livros.hash_d.db").delete();
      new File("dados/livros.hash_c.db").delete();
      new File("dados/livros_isbn.hash_d.db").delete();
      new File("dados/livros_isbn.hash_c.db").delete();
      new File("dados/livros_categorias.btree.db").delete();

      ArquivoLivros arqLivros = new ArquivoLivros();
      ArquivoCategorias arqCategorias = new ArquivoCategorias();
      ArquivoAutores arqAutores = new ArquivoAutores();

      arqCategorias.create(new Categoria("Romance"));
      arqCategorias.create(new Categoria("Educação"));
      arqCategorias.create(new Categoria("Sociologia"));
      arqCategorias.create(new Categoria("Policial"));
      arqCategorias.create(new Categoria("Aventura"));
      arqCategorias.create(new Categoria("Suspense"));

      arqAutores.create(new Autor("Homero"));
      arqAutores.create(new Autor("Lilian Bacich"));
      arqAutores.create(new Autor("Adolfo Tanzi Neto"));
      arqAutores.create(new Autor("Zygmunt Bauman"));
      arqAutores.create(new Autor("Plínio Dentzien"));
      arqAutores.create(new Autor("Ivan Izquierdo"));
      arqAutores.create(new Autor("Mariana Zapata"));

      arqLivros.create(new Livro("9788563560278", "Odisseia", 15.99F, 1));
      arqLivros.create(new Livro("9788584290483", "Ensino Híbrido", 39.90F, 2));
      arqLivros.create(new Livro("9786559790005", "Modernidade Líquida", 48.1F, 3));
      arqLivros.create(new Livro("9788582714911", "Memória", 55.58F, 1));
      arqLivros.create(new Livro("9786587150062", "Com Amor", 48.9F, 1));

      // Criar um objeto Autor
            Autor autorteste = new Autor(1, "José de Alencar");

            // Converter o objeto para um array de bytes e cifrar
            byte[] dadosCifrados = autorteste.toByteArray();
            System.out.println("Dados cifrados: ");
            for (byte b : dadosCifrados) {
                System.out.print(b + " ");
            }
            System.out.println();

            // Reconstruir o objeto a partir do array de bytes decifrados
            Autor autorReconstruido = new Autor();
            autorReconstruido.fromByteArray(dadosCifrados);

            // Verificar se os dados são os mesmos
            System.out.println("Dados do objetorteste:");
            System.out.println(autorteste);

            System.out.println("Dados do objeto reconstruído:");
            System.out.println(autorReconstruido);

            // Comparar os dados para verificar se a cifragem e decifragem estão funcionando corretamente
            boolean testeID = autorteste.getID() == autorReconstruido.getID();
            boolean testeNome = autorteste.getNome().equals(autorReconstruido.getNome());

            if (testeID && testeNome) {
                System.out.println("Teste bem-sucedido: os dados cifrados e decifrados são iguais aos originais.");
            } else {
                System.out.println("Teste falhou: os dados cifrados e decifrados são diferentes dos originais.");
            }



      arqLivros.close();
      arqCategorias.close();
      arqAutores.close();

      System.out.println("Banco de dados reinicializado com dados de exemplo.");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
