import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

import lzws.*;

public class LZWController {

    private final String dir;

    public LZWController(String dir) {
        File backup = new File(dir);
        if(!backup.exists()) {
            backup.mkdir();
        }
        this.dir = dir;
    }

    private void readAndWrite(String nome, int bytesByExecution, String dirOut) throws Exception {
        RandomAccessFile fileInput = null, fileOutput = null;
        try {
            fileInput = new RandomAccessFile("dados/" + nome, "rw");
            fileOutput = new RandomAccessFile(dir + "/" + dirOut + "/" + nome + ".lzw", "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] temp = new byte[bytesByExecution];
        fileInput.seek(0);
        fileOutput.seek(0);
        while (fileInput.getFilePointer() < fileInput.length()) {
            fileInput.read(temp);
            try {
                temp = LZW.codifica(temp);
            } catch (Exception e) {
                throw new Exception("Erro ao comprimir o conteudo de " + nome);
            }
            fileOutput.write(temp);
        }
        fileInput.close();
        fileOutput.close();
    }

    /**
     * 
     * @param bytesByCicle numero da quantidade de bytes que será por loop 
     * @throws Exception 
     */
    public void compressData(int bytesByExecution) throws Exception {
        String[] nomes = (new File("dados")).list();
        SimpleDateFormat simple = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        for (String nome : nomes) {
            String dirOut = simple.format(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            File file = new File(dir + "/" + dirOut);
            file.mkdir();
            readAndWrite(nome, bytesByExecution, dirOut);
        }
    }

    public void decompressData(String filePath, int bytesByExecution) throws Exception {
        try {
            RandomAccessFile file = new RandomAccessFile(filePath, "rw");
            byte[] conteudo = new byte[10];
            System.out.println("\n" + filePath);
            while (file.getFilePointer() < file.length()) {
                file.read(conteudo);
                for (byte b : conteudo) {
                    System.out.print(b);
                }
            }
            System.out.println();
            System.out.println();
            file.close();
        } catch (Exception e) {
            throw new Exception("Erro ao abrir o arquivo para decodificação!");
        }
    }

    public void decompressData(String dirBackUp, String dirOut, int bytesByExecution) throws Exception {
        String[] files = (new File(dirBackUp)).list();
        for (String file : files) {
            decompressData(dirBackUp + "/" + file, 10);
            RandomAccessFile fileOut = new RandomAccessFile(dirOut + "/" + file.substring(0, file.length()-4), "rw");
            
            byte[] conteudo = new byte[(int) fileOut.length()];
            fileOut.read(conteudo);
            for (byte b : conteudo) {
                System.out.print(b);
            }
            System.out.println();
            fileOut.close();
        }
    }

    public void makeMenu() throws Exception {
        System.out.println("Choose a backup:");
        String[] dirList = (new File(dir)).list();
        for(int i = 0; i < dirList.length; i++) {
            System.out.println("    " + i + ": " + dirList[i]);
        }
        System.out.print("Resposta: ");
        Scanner sc = new Scanner(System.in);
        int res = sc.nextInt();
        decompressData(dir + "/" + dirList[res], "dados", 10);
        sc.close();
    }

    public static void main(String[] args) throws Exception {
        LZWController controller = new LZWController("backup");
        controller.makeMenu();
        //controller.compressData(10);
    }

}
