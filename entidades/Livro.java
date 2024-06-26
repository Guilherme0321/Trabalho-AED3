package entidades;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.text.NumberFormat;

import aeds3.Registro;

public class Livro implements Registro {

  private int ID;
  private String isbn;
  private String titulo;
  private float preco;
  private int idCategoria;
  private static final byte SUBSTITUTION_KEY = 5; // chave substituicao
  private static final int TRANSPOSITION_KEY = 3; // chave transpo

  public Livro() {
    this(-1, "", "", 0F, -1);
  }

  public Livro(String i, String t, float p, int ic) {
    this(-1, i, t, p, ic);
  }

  public Livro(int id, String i, String t, float p, int ic) {
    this.ID = id;
    this.isbn = i;
    this.titulo = t;
    this.preco = p;
    this.idCategoria = ic;
  }

  public int getID() {
    return ID;
  }

  public void setID(int iD) {
    ID = iD;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public float getPreco() {
    return preco;
  }

  public void setPreco(float preco) {
    this.preco = preco;
  }

  public int getIdCategoria() {
    return idCategoria;
  }

  public void setIdCategoria(int idCategoria) {
    this.idCategoria = idCategoria;
  }

  public byte[] toByteArray() throws Exception {
    ByteArrayOutputStream ba_out = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(ba_out);
    dos.writeInt(this.ID);
    dos.writeUTF(this.isbn);
    dos.writeUTF(this.titulo);
    dos.writeFloat(this.preco);
    dos.writeInt(this.idCategoria);
    byte[] dados = ba_out.toByteArray();
    byte[] dadosCifrados = cifrar(dados, SUBSTITUTION_KEY, TRANSPOSITION_KEY);
    return dadosCifrados;
  }

  public void fromByteArray(byte[] ba) throws Exception {
    byte[] dadosDecifrados = decifrar(ba, SUBSTITUTION_KEY, TRANSPOSITION_KEY);
    ByteArrayInputStream ba_in = new ByteArrayInputStream(dadosDecifrados);
    DataInputStream dis = new DataInputStream(ba_in);
    this.ID = dis.readInt();
    this.isbn = dis.readUTF();
    this.titulo = dis.readUTF();
    this.preco = dis.readFloat();
    this.idCategoria = dis.readInt();
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
      resultado[i] = (byte) (dados[i] + key);
    }
    return resultado;
  }

  private byte[] desubstituir(byte[] dados, byte key) {
    byte[] resultado = new byte[dados.length];
    for (int i = 0; i < dados.length; i++) {
      resultado[i] = (byte) (dados[i] - key);
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
        "\nISBN: " + this.isbn +
        "\nTítulo: " + this.titulo +
        "\nPreço: " + NumberFormat.getCurrencyInstance().format(this.preco) +
        "\nCategoria: " + this.idCategoria;
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  @Override
  public int compareTo(Object b) {
    return this.getID() - ((Livro) b).getID();
  }
}
