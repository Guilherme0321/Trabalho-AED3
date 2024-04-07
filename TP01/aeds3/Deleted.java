import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.lang.reflect.Constructor;

public class Deleted <T extends RegistroHashExtensivel> {
    RandomAccessFile file;
    Constructor<T> constructor;

    @SuppressWarnings("unchecked")
    public Deleted(Constructor constructor, String name) {
        this.constructor = constructor;
        try {
            this.file = new RandomAccessFile(name, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
