# TP04

## Relatório:
Utilizamos dois métodos para a nossa cifragem:
O primeiro é utilizando o método de substituição, que consiste na utilização de uma chave numérica. Essa chave será utilizada para definir o quanto cada caracter (ou no caso byte) "andará" para frente, sendo semelhante à uma cifra de Cesar. Logo, para cifrar, somaremos o valor da chave em cada posição do array de bytes, e para decifrar, iremos subtrair o valor da chave em cada posição.

O segundo é utilizando o método de transposição, que também consiste na utilização de uma chave numérica e, nesse caso, essa chave será utilizada na construção de um novo índice para cada byte. A lógica é:
colocar o byte texto[i] na posição textoCriptografado[(i + chave) % texto.length()]. Essa lógica é feita para cada posição do array de bytes, e para decifrar, é utilizada a lógica reversa: texto[i] irá para posição textoCriptografado[(i - key + dados.length) % dados.length].


## Checklist:

- [x] Há uma função de cifragem em todas as classes de entidades, envolvendo pelo menos duas operações diferentes e usando uma chave criptográfica?
- [x] Uma das operações de cifragem é baseada na substituição e a outra na transposição?
- [x] O trabalho está funcionando corretamente?
- [x] O trabalho está completo?
- [x] O trabalho é original e não a cópia de um trabalho de um colega?

## Nomes

- Guilherme Otávio de Oliveira
- Gabriel Praes Bernardes Nunes
- Ana Fernanda Souza Cancado
- Júlia Pinheiro Roque
