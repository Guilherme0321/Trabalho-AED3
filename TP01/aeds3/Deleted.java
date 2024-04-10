import java.io.FileNotFoundException;
import java.io.RandomAccessFile;


public class Deleted {
    RandomAccessFile file;

    //Construtor
    public Deleted(String name) {
        try {
            this.file = new RandomAccessFile(name, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Função que cria o registro dentro do arquivo "deletados.db";
     * @param elem objeto da classe DeletedIndexRegister que contém o tamanho e a posição do registro;
     * @return retorna falso caso haja uma exception;
     */
    public boolean create(DeletedIndexRegister elem) {
        try {
            file.seek(file.length());
            file.write(' ');
            byte[] tam  = elem.toByteArray();
            file.writeShort(tam.length);
            file.write(elem.toByteArray());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Função que verifica se há algum espaço possivel de ser reutilizado entre os deletados;
     * @param len tamanho do array de bytes a ser adicionado;
     * @return posição do arquivo a ser reutilizado ou -1 caso não seja possível reutilizar algum espaço;
     */
    public long read(short len) {
        try {
            file.seek(0);
            while (file.getFilePointer() < file.length()) {
                long endereco = file.getFilePointer();
                char lapide = (char) file.read();
                short byteArrayLength = file.readShort();
                if(lapide != '*') {
                    byte[] bytes = new byte[byteArrayLength];
                    file.read(bytes);
                    DeletedIndexRegister indiceDeletado = new DeletedIndexRegister();
                    indiceDeletado.fromByteArray(bytes);
                    //System.out.println(indiceDeletado);
                    if(len <= indiceDeletado.getLength()) {
                        file.seek(endereco);
                        file.write('*');
                        return indiceDeletado.getPosition();
                    }
                } else {
                    file.skipBytes(byteArrayLength);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}
