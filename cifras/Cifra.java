package cifras;
public class Cifra {

    private static int key = 5;
    
    public static byte[] cifra(byte[] dados) {
        return cifra(dados, (byte)key, key);
    }

    private static byte[] cifra(byte[] dados, byte substitutionKey, int transpositionKey) {
        byte[] substituidos = substituir(dados, substitutionKey);
        byte[] transpostos = transpor(substituidos, transpositionKey);
        return transpostos;
    }

    public static byte[] decifra(byte[] dados) {
        return decifra(dados, (byte)key, key);
    }
    
    private static byte[] decifra(byte[] dados, byte substitutionKey, int transpositionKey) {
        byte[] transpostos = destranspor(dados, transpositionKey);
        byte[] substituidos = desubstituir(transpostos, substitutionKey);
        return substituidos;
    }
    
    private static byte[] substituir(byte[] dados, byte key) {
        byte[] resultado = new byte[dados.length];
        for (int i = 0; i < dados.length; i++) {
            resultado[i] = (byte)(dados[i] + key);
        }
        return resultado;
    }
    
    private static byte[] desubstituir(byte[] dados, byte key) {
        byte[] resultado = new byte[dados.length];
        for (int i = 0; i < dados.length; i++) {
            resultado[i] = (byte)(dados[i] - key);
        }
        return resultado;
    }
    
    private static byte[] transpor(byte[] dados, int key) {
        byte[] resultado = new byte[dados.length];
        for (int i = 0; i < dados.length; i++) {
            int novoIndice = (i + key) % dados.length;
            resultado[novoIndice] = dados[i];
        }
        return resultado;
    }
    
    private static byte[] destranspor(byte[] dados, int key) {
        byte[] resultado = new byte[dados.length];
        for (int i = 0; i < dados.length; i++) {
            int novoIndice = (i - key + dados.length) % dados.length;
            resultado[novoIndice] = dados[i];
        }
        return resultado;
    }
}
