## TP 1 - Grupo 8

### Descrição do trabalho
Para reaproveitar o espaço vazio, nós criamos um novo arquivo, "deletados.db". Nesse arquivo, são adicionados alguns campos para cada registro do arquivo original.
Por exemplo, o Livro l1, foi adicionado assim no arquivo "livros.db":
| lapide        | tamanho       | id            | isbn          | titulo        | preco         |
| ------------- | ------------- | ------------- | ------------- | ------------- | ------------- |
| " "           | 33            | 1             | 9788563560278 | Odisseia      | 15.99F        |

Caso esse livro seja apagado ou atualizado e ocupe uma quantidade de bytes maior que originalmente, nós marcamos a sua lápide no arquivo "livros.db" e adicionamos as informações dele no arquivo "deletados.db" dessa maneira:

| lapide        | tamanho ocupado pelo l1 nesse arquivo(tam. fixo)       | tamanho do registro l1 no arquivo "livros.db"  | endereco do inicio do registro l1 no arquivo "livros.db"  |
| ------------- | ------------- | ------------- | ------------- | 
| " "           | 10            | 33             | 04          | 

Assim, ao criar ou atualizar um registro que possua o seu tam2>tam, nós percorremos esse arquivo de deletados, procurando um espaço que possa ser reutilizado e caso encontrado, marcarmos a lápide no arquivo de apagados com um "*" (o que indica que
o espaço desse apagado já foi utilizado).


## Relatos do grupo
Durante a execução do projeto,tivemos com alguns desafios. Um deles foi identificado método update do "Arquivo.Java", onde, ao invés de comparar (tam),
estávamos comparando com o (tam2). Além disso, tivemos dificuldades na atualização dos endereços na tabela hash. Foi resolvido quando ao encontrarmos um endereço disponível para sobrescrita,
atualizamos o endereço correspondente na tabela hash.

A operação que achamos mais dificil foi o Update. Isso se deve ao fato de que precisávamos tanto adicionar o registro atualizado, que possuía um tamanho menor do que o original, ao "deletados.db", quanto encontrar um endereço disponível para sobrescrever.

Os resultados foram alcançados, conseguimos realizar o que foi especificado na descrição do trabalho. Fizemos alguns testes e em todos as ações de sobrescrita ocorreram conforme esperado e sem problemas.
