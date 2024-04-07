import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DeletedIndexRegister implements RegistroHashExtensivel {

    //private int ID; 
    // id do elemento deletado
    private short length; // tamanho do arquivo deletado
    private long position; // posição do arquivo deletado no arquivo
    final private int TAMANHO = 14; // tamanho do int(4) short(2) long(8)

/*     public int getID() {
        return this.ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    } */
    public short getLength() {
        return this.length;
    }
    public void setLength(short length) {
        this.length = length;
    }
    public long getPosition() {
        return this.position;
    }
    public void setPosition(long position) {
        this.position = position;
    }

    public String toString() { 
        return "{\n     Tamanho: " + length + "\n       Posição: " + position + "\n}";
    }

    @Override
    public short size() {
        return TAMANHO;
    }
    @Override
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(bytes);
        //data.writeInt(ID);
        data.writeShort(length);
        data.writeLong(position);
        return bytes.toByteArray();
    }
    @Override
    public void fromByteArray(byte[] bytes) throws IOException {
        ByteArrayInputStream arrayInput = new ByteArrayInputStream(bytes);
        DataInputStream data = new DataInputStream(arrayInput);
        //this.ID = data.readInt();
        this.length = data.readShort();
        this.position = data.readLong();
    }


}
