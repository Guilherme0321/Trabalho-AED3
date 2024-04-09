import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class Deleted {
    RandomAccessFile file;

    public Deleted(String name) {
        try {
            this.file = new RandomAccessFile(name, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

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
