import java.io.*;

class Principal {

  public static void main(String args[]) {

    new File("dados/livros.db").delete();
    new File("dados/livros.hash_d.db").delete();
    new File("dados/livros.hash_c.db").delete();

    /**
     * Arquivo onde terá os arquivos que já foram deletados;
     * Caso o arquivo espaço do arquivo deletado já tenha sido reutilizado, será adicionado uma lápide *;
     */
    new File("deletados.db").delete();

    new File("dados").mkdir();
    // new File("dados/deletedIndices").mkdir();

    Arquivo<Livro> arqLivros;
    Livro l1 = new Livro(-1, "9788563560278", "Odisseia", 15.99F);
    Livro l2 = new Livro(-1, "9788584290482", "Ensino Híbrido", 39.90F);
    Livro l3 = new Livro(-1, "9786559790005", "Modernidade Líquida", 48.1F);
    Livro l4 = new Livro(-1, "9788582714911", "Memória", 55.58F);
    Livro l5 = new Livro(-1, "9786587150062", "Com Amor", 48.9F);
    Livro l6 = new Livro(-1, "9786587150062", "Olá", 48.9F);

    int id1, id2, id3, id4, id5, id6;

    try {
      arqLivros = new Arquivo<>("livros", Livro.class.getConstructor());

      /**
       * Criando livros 1 a 4 no arquivo:
       */
      id1 = arqLivros.create(l1);
      System.out.println("Livro criado com o ID: " + id1);

      id2 = arqLivros.create(l2);
      System.out.println("Livro criado com o ID: " + id2);

      id3 = arqLivros.create(l3);
      System.out.println("Livro criado com o ID: " + id3);
      
      id4 = arqLivros.create(l4);
      System.out.println("Livro criado com o ID: " + id4);

      
      
      //Deletando livro 2:
      if (arqLivros.delete(id2))
      System.out.println("Livro de ID " + id2 + " excluído!");
      else
      System.out.println("Livro de ID " + id2 + " não encontrado!");

      //Criando livro 5
      id5 = arqLivros.create(l5); //O livro 5 é menor que o Livro 2 apagado, logo, ele irá sobrescrever;
      System.out.println("Livro criado com o ID: " + id5);

      /**
       * Atualizando o nome do livro 4;
       * Como o novo nome é maior que o antigo, o arquivo também será maior.
       * Logo, ele irá procurar algum arquivo deletado para sobreescrever, e como não tem nenhum deletado disponível, irá criar um novo arquivo no final.
       * O livro quatro antigo terá sua lápide marcada.
       */
      l4.setTitulo("A Memória");
      if (arqLivros.update(l4))
      System.out.println("Livro de ID " + l4.getID() + " alterado!");
      else
      System.out.println("Livro de ID " + l4.getID() + " não encontrado!");

      //Cria o livro 6
      id6 = arqLivros.create(l6); //Sobreescreve o antigo livro 4 que foi atualizado;
      System.out.println("Livro criado com o ID: " + id6);

      //Print de todos os livros (os apagados estarão como null)
      System.out.println("\nLivro 1:\n" + arqLivros.read(1));
      System.out.println("\nLivro 2:\n" + arqLivros.read(2));
      System.out.println("\nLivro 3:\n" + arqLivros.read(3));
      System.out.println("\nLivro 4:\n" + arqLivros.read(4));
      System.out.println("\nLivro 5:\n" + arqLivros.read(5));
      System.out.println("\nLivro 6:\n" + arqLivros.read(6));

      arqLivros.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}