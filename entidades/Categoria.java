package entidades;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import aeds3.Registro;

public class Categoria implements Registro {

  private int ID;
  private String nome;
  private static final byte SUBSTITUTION_KEY = 5; // chave substituicao
  private static final int TRANSPOSITION_KEY = 3; // chave transpo

  public Categoria() {
    this(-1, "");
  }

  public Categoria(String n) {
    this(-1, n);
  }

  public Categoria(int id, String n) {
    this.ID = id;
    this.nome = n;
  }

  public int getID() {
    return ID;
  }

  public void setID(int iD) {
    ID = iD;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public byte[] toByteArray() throws Exception {
    ByteArrayOutputStream ba_out = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(ba_out);
    dos.writeInt(this.ID);
    dos.writeUTF(this.nome);
    byte[] dados = ba_out.toByteArray();
    byte[] dadosCifrados = cifrar(dados, SUBSTITUTION_KEY, TRANSPOSITION_KEY);
    return dadosCifrados;
}

public void fromByteArray(byte[] ba) throws Exception {
    byte[] dadosDecifrados = decifrar(ba, SUBSTITUTION_KEY, TRANSPOSITION_KEY);
    ByteArrayInputStream ba_in = new ByteArrayInputStream(dadosDecifrados);
    DataInputStream dis = new DataInputStream(ba_in);
    this.ID = dis.readInt();
    this.nome = dis.readUTF();
}

private byte[] cifrar(byte[] dados, byte substitutionKey, int transpositionKey) {
    byte[] substituidos = substituir(dados, substitutionKey);
    byte[] transpostos = transpor(substituidos, transpositionKey);
    return transpostos;
}

private byte[] decifrar(byte[] dados, byte substitutionKey, int transpositionKey) {
    byte[] transpostos = destranspor(dados, transpositionKey);
    byte[] substituidos = desubstituir(transpostos, substitutionKey);
    return substituidos;
}

private byte[] substituir(byte[] dados, byte key) {
    byte[] resultado = new byte[dados.length];
    for (int i = 0; i < dados.length; i++) {
        resultado[i] = (byte)(dados[i] + key);
    }
    return resultado;
}

private byte[] desubstituir(byte[] dados, byte key) {
    byte[] resultado = new byte[dados.length];
    for (int i = 0; i < dados.length; i++) {
        resultado[i] = (byte)(dados[i] - key);
    }
    return resultado;
}

private byte[] transpor(byte[] dados, int key) {
    byte[] resultado = new byte[dados.length];
    for (int i = 0; i < dados.length; i++) {
        int novoIndice = (i + key) % dados.length;
        resultado[novoIndice] = dados[i];
    }
    return resultado;
}

private byte[] destranspor(byte[] dados, int key) {
    byte[] resultado = new byte[dados.length];
    for (int i = 0; i < dados.length; i++) {
        int novoIndice = (i - key + dados.length) % dados.length;
        resultado[novoIndice] = dados[i];
    }
    return resultado;
}

  public String toString() {
    return "ID: " + this.ID +
        "\nNome: " + this.nome;
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  @Override
  public int compareTo(Object b) {
    return this.getNome().compareTo(((Categoria) b).getNome());
  }
}
