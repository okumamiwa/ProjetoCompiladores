# **[COMPILADORES] Q3-2021**
# *Entrega de Projeto --ProjLang--*

Conteúdos
=================

<!--ts-->
   * [Descrição do projeto](#descrição-do-projeto)
   * [Integrantes do grupo](#integrantes-do-grupo)
   * [Itens obrigatórios](#itens-obrigatórios-)
   * [Itens opcionais](#itens-opcionais-)
   * [Comandos e funções](#comandos-e-funções-)
   * [Exemplos de código](#exemplos-de-código-%EF%B8%8F)

<!--te-->


Descrição do Projeto 📋
====================
A linguagem ProjLang foi desenvolvida como projeto final para a disciplina Compiladores Q2-2021

Criada com o auxílio da ferramenta ANTLR 4 (versão antlr-4.5-complete), e possúi destino final a linguagem Java

Usamos como base as aulas fornecidas pelo professor Isidro disponíveis no link:
https://www.youtube.com/watch?v=gxlxHYv-9oo&list=PLjcmNukBom6--0we1zrpoUE2GuRD-Me6W

E o repositório, também fornecido pelo professor: https://github.com/professorisidro/IsiLanguageEmbriao

Integrantes do Grupo
====================

|Nome                             |RA
|---------------------------------|-----------|
| Egidio Henrique Paixão          |11201721106
| Gabriel Mendonça Farias         |21060116
| Mariana Miwa Okuma Miyashiro    |11201811238
| William César Santos Ramalho    |11038216

Itens obrigatórios ✅
==================
- [x] 2 tipos de variáveis (Double)--fizemos string
- [x] possuir if-else
- [x] 1 estrutura de repetição (while)
- [x] verificar atribuição de variáveis (erro semântico) - compatibilidade de tipos
- [x] operações de entrada e saída (leia e escreva)
- [x] aceitar números decimais
- [x] verificar se a variável foi ou não declarada
- [x] verificar se a variável declarada foi ou não utilizada (produz warnings)
- [x] linguagem de destino: Java

Itens opcionais 💡
===============
- [x] String
- [x] compatibilidade de tipos em expressões comparativas
- [x] repetição do-while
- [x] indicação do tempo de compilação 
- [x] repetição for
- [x] identação do código gerado
- [x] ignorar comentários
- [x] rodar arquivo passado como argumento
- [x] organização de código por edentação

Comandos e funções 🔧
==================
**Declarações de início e fim**
|Função                                     |Comando
|-------------------------------------------|--------|
|início do código                           |programa
|fim do código                              |fimprog
|para a declaração de variáveis numéricas   |numero
|declaração de fim da linha de instrução    |;
|declaraçao de início da instrução da função|{
|declaração de fim de operação da função    |}

**Entrada e saída de dados**
|Função                                     |Comando
|-------------------------------------------|--------|
|para a declaração de palavras (strings)    |texto
|para declarar números (Double)             |numero
|para ler uma entrada a                     |leia (a)
|para escrever um valor a na saída          |escreva (a) 

**Operadores**
|Função                                     |Comando
|-------------------------------------------|--------|
|operação de adição de a por b              |a + b
|operação de subtração de a por b           |a - b
|operação de multiplicação de a por b       |a * b
|operação de divisão de a por b             |a / b
|comparativo a maior que b                  |a > b
|comparativo a menor que b                  |a < b
|comparativo a maior ou igual que b         |a >= b
|comparativo a menor ou igual que b         |a <= b
|comparativo a igual a b                    |a == b
|comparativo a diferente de b               |a != b     
 

**Funções**
|Função                                                    |Comando
|----------------------------------------------------------|--------|
|função de verificação de estado (if)                      |se
|função de verificação de estado (else)                    |senao   
|função de repetição (while)                               |enquanto
|função de executar uma função (for)                       |repetir
|realizar uma 'função a' (do while)                        |fazer {função a} até
|comparativo (booleano) entre a e b, se true c, se false d |a == b ? c ; d

Exemplos de código 🖥️
=================

Exemplo 1

    programa
        numero a, b, c, d;
        texto t1, t2;

        leia(a);
        leia(b);

        a = 1+2*3/b;

        se (a < b ) {
          escreva (a);
        }
        senao {
          escreva(b);
        }	

        c = 0;

        t1 = "testing";

        enquanto ( c < 5 ) {
          escreva (c);
          c = c + 1;
          }

          fazer {
            escreva (c);
            c = c - 1;
          } ate (c > 0)

          c == a ? escreva (a); : escreva (t1);

          escreva(t1);

      fimprog;

Exemplo 2
 
    programa
      numero a, b, c, d;
      texto t1;

      leia(a);
      leia(b);

      a = 1+2*3/b;

      se (a < b ) {
        escreva (a);
      }
      senao {
        escreva(b);
      }	

      t1 = "testing";

        escreva(t1);

    fimprog;

Exemplo 3
 
    programa
      numero a, b, c, d, i;
      texto t1;

      leia(a);
      leia(b);

      a = 1+2*3/b;

      se (a < b ) {
        escreva (a);
      }
      senao {
        escreva(b);
      }	

      t1 = "testing";

        repetir (i=0; i<3; i=i+1) {
          escreva(a);
        }

        escreva(t1);

    fimprog;

