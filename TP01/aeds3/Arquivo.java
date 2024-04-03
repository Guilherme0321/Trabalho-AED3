package aeds3;

import java.io.RandomAccessFile;
import java.lang.reflect.Constructor;

public class Arquivo<T extends Registro> {

    protected RandomAccessFile arquivo;
    protected String nomeEntidade = "";
    protected Constructor<T> construtor;
    final protected int TAM_CABECALHO = 4;
    protected HashExtensivel<ParIDEndereco> indiceDireto;

    public Arquivo(String nomeDoArquivo, Constructor<T> construtor) throws Exception {
        this.nomeEntidade = nomeDoArquivo;
        this.construtor = construtor;
        arquivo = new RandomAccessFile("dados/" + nomeDoArquivo + ".db", "rw");

        boolean arquivoVazio = arquivo.length() < TAM_CABECALHO;

        if (arquivoVazio) {
            arquivo.seek(0);
            arquivo.writeInt(0);
        }

        indiceDireto = new HashExtensivel<>(ParIDEndereco.class.getConstructor(),
                3,
                "dados/" + nomeDoArquivo + ".hash_d.db",
                "dados/" + nomeDoArquivo + ".hash_c.db");
    }

    public int create(T obj) throws Exception {

        // Lê o último ID
        arquivo.seek(0);
        int ultimoID = arquivo.readInt();

        // Incrementa o ID
        ultimoID++;

        arquivo.seek(0);

        // Escreve o novo ID, tanto no arquivo quanto no objeto
        arquivo.writeInt(ultimoID);
        obj.setID(ultimoID);

        // Escreve o objeto no final do arquivo
        arquivo.seek(arquivo.length());
        long endereco = arquivo.getFilePointer();
        byte[] ba = obj.toByteArray();
        short tam = (short) ba.length;
        arquivo.writeByte(' '); // lápide
        arquivo.writeShort(tam);
        arquivo.write(ba);

        // Insere o endereço no índice direto
        indiceDireto.create(new ParIDEndereco(obj.getID(), endereco));
        return obj.getID();
    }

    public T read(int id) throws Exception {

        // Cria um objeto do tipo T, vazio (vai ser preenchido depois com o objeto lido
        // do arquivo)
        T obj = construtor.newInstance();
        short tam;
        byte[] ba;

        // Lê o endereço do objeto no índice direto
        ParIDEndereco pie = indiceDireto.read(id);

        // Caso o objeto não exista, endereco = -1
        long endereco = pie != null ? pie.getEndereco() : -1;

        // Se o objeto existe, lê o objeto do arquivo
        if (endereco != -1) {
            arquivo.seek(endereco + 1); // pula o lápide também
            tam = arquivo.readShort();
            ba = new byte[tam];
            arquivo.read(ba);
            obj.fromByteArray(ba);
            return obj;
        }
        return null;
    }

    public boolean delete(int id) throws Exception {
        // Lê o endereço do objeto no índice direto
        ParIDEndereco pie = indiceDireto.read(id);
        long endereco = pie != null ? pie.getEndereco() : -1;

        // Se o objeto foi encontrado, marca o registro como deletado
        if (endereco != -1) {
            arquivo.seek(endereco);
            arquivo.writeByte('*');
            // Remove o objeto do índice direto (!! importante)
            indiceDireto.delete(id);
            return true;
        }
        return false;
    }

    public boolean update(T novoObj) throws Exception {

        // Cria um objeto do tipo T, vazio (vai ser preenchido depois com o objeto lido) 
        T obj = construtor.newInstance();


        short tamanhoOriginal, tamanhoNovo;
        byte[] byteArrayOriginal, byteArrayNovo;

        // Lê o endereço do objeto no índice direto -> OBS: o novoObj tem o ID do objeto que será atualizado! por isso é possível pesquisar ele
        ParIDEndereco pie = indiceDireto.read(novoObj.getID());
        long endereco = pie != null ? pie.getEndereco() : -1;

        if (endereco != -1) {
            arquivo.seek(endereco + 1); // pula o campo lápide
            tamanhoOriginal = arquivo.readShort();
            byteArrayOriginal = new byte[tamanhoOriginal];
            arquivo.read(byteArrayOriginal);

            //Objeto original:
            obj.fromByteArray(byteArrayOriginal);

            byteArrayNovo = novoObj.toByteArray();
            tamanhoNovo = (short) byteArrayNovo.length;

            boolean registroNovoCabeNoEspacoOriginal = tamanhoNovo <= tamanhoOriginal;
            if (registroNovoCabeNoEspacoOriginal) {
                //Só escreve o novo objeto no lugar do antigo
                arquivo.seek(endereco + 1 + 2);
                arquivo.write(byteArrayNovo); //aq nao precisa atualizar o indice direto pq a posição (endereço) no arquivo vai ser a msm
            } else {

                // Marca o registro antigo como deletado
                arquivo.seek(endereco);
                arquivo.writeByte('*');

                // Cria um novo registro no final do arquivo e escreve o novo obj
                arquivo.seek(arquivo.length());
                long endereco2 = arquivo.getFilePointer();
                arquivo.writeByte(' ');
                arquivo.writeShort(tamanhoNovo);
                arquivo.write(byteArrayNovo);

                // Atualiza o índice direto com o novo indireto
                indiceDireto.update(new ParIDEndereco(novoObj.getID(), endereco2));
            }
            return true;
        }
        return false;
    }

}
