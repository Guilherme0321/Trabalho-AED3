# TP03

## Relatório:

No menu principal foram adicionadas mais duas opções: 4)Fazer BACKUP e 5)Restaurar BACKUP

### LZWController
Nós criamos a classe LZWController para gerenciar a compressão e descompressão de arquivos, além de criar e restaurar backups. 
* A função "makeBackupMenu" exibe um menu para o usuário escolher um backup para restaurar. Após a entrada do usuário, ele chama o método "decompressData" para descomprimir os arquivos do backup escolhido.
* O método "compressData" lista todos os arquivos da pasta dados, cria uma nova pasta de backup com a data e hora, e chama "readAndWrite" para comprimir e armazenar cada arquivo.
* A função "readAndWrite" lê um arquivo, comprime seu conteúdo usando LZW e escreve o arquivo comprimido em um diretório de backup especificado.
* A primeira função "decompressData" descomprime um arquivo específico eretorna seu conteúdo descomprimido. Já a segunda função "decompressData" descomprime todos os arquivos que estão no backup e escreve o conteúdo descomprimido.

## Checklist:

- [x] Há uma rotina de compactação usando o algoritmo LZW para fazer backup dos arquivos?
- [x] Há uma rotina de descompactação usando o algoritmo LZW para recuperação dos arquivos?
- [x] O usuário pode escolher a versão a recuperar?
- [ ] Qual foi a taxa de compressão alcançada por esse backup? (Compare o tamanho dos arquivos compactados com os arquivos originais)
- [x] O trabalho está funcionando corretamente?
- [x] O trabalho está completo?
- [x] O trabalho é original e não a cópia de um trabalho de um colega?

## Nomes

- Guilherme Otávio de Oliveira
- Gabriel Praes Bernardes Nunes
- Ana Fernanda Souza Cancado
- Júlia Pinheiro Roque
