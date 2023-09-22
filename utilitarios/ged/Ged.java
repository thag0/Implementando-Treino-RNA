package utilitarios.ged;

/**
 * <p>
 *    Gerenciador de Dados.
 * </p>
 * Conjunto de ferramentas criadas para lidar com dados. As funcionalidades
 * incluem:
 * <ul>
 *    <li>Leitura de arquivos;</li>
 *    <li>Conversão de dados;</li>
 *    <li>Manipulação de estrutura de dados;</li>
 *    <li>Gerenciamento de treino e teste para rede neural;</li>
 *    <li>Operações matriciais;</li>
 * </ul>
 *    Algumas operação necessitam de um objeto do tipo {@code Dados} para serem realizadas que 
 *    deve ser importado através de <pre>import ged.Dados;</pre>
 * <p>
 *    A classe Ged é apeans uma casca/embrulho (ou wrapper) que contém as funções que são expostas 
 *    publicamente para quem quiser utilizá-las, o conteúdo com implementações está divididos de acordo 
 *    com suas especialidades.
 * </p>
 * <p>
 *    Aos poucos são adicionadas novas funcionalidades de acordo com as necessidades que surgem então o
 *    Ged sempre pode sofrer alterações e melhorias com o passar do tempo.
 * </p>
 * @see https://github.com/thag0/Treinando-Rede-Neural-Artificial/tree/main/utilitarios/ged
 * @author Thiago Barroso, acadêmico de Engenharia da Computação pela Universidade Federal do Pará, Campus Tucuruí. Maio/2023.
*/
public class Ged{

   ImpressaoMatriz im;//exibição
   ImpressaoArray ia;//exibição
   ImpressaoDados id;//exibição
   ManipuladorDados md;//manipulador de dados
   GerenciadorArquivos ga;//leitor de arquivos
   ConversorDados cd;//conversor de dados 
   TreinoTeste gtt;//gerenciador de treino e teste da rede
   OperadorMatriz om;//operador de matrizes
   OperadorMatrizMultithread omt;

   /**
    * Objeto responsável pelo manuseio de um conjunto de dados contendo 
    * diversas implementações de métodos para gerenciamento e manipulação.
    * <p>
    *    Algumas operação necessitam de um objeto do tipo {@code Dados} para serem realizadas que 
    *    deve ser importado através de <pre>import ged.Dados;</pre>
    * </p>
    */
   public Ged(){
      im = new ImpressaoMatriz();
      ia = new ImpressaoArray();
      id = new ImpressaoDados();
      md = new ManipuladorDados();
      ga = new GerenciadorArquivos();
      gtt = new TreinoTeste();
      cd = new ConversorDados();
      om = new OperadorMatriz();

      omt = new OperadorMatrizMultithread(Runtime.getRuntime().availableProcessors()/2);
   }


   /**
    * Exibe pelo console as informações contidas no conteúdo dos dados.
    * @param dados conjunto de dados.
    */
   public void imprimirDados(Dados dados){
      dados.imprimir();
   }


   /**
    * Imprime o início do conteúdo do conjunto de dados para facilitar a visualização 
    * em dados muito grandes.
    * @param dados conjunto de dados.
    */
   public void imprimirInicio(Dados dados){
      id.imprimirInicio(dados);
   }

   /**
    * Exibe as informações contidas no array fornecido.
    * <p>
    *    Tipos suportados:
    * </p>
    * <ul>
    *    <li>
    *       int[];
    *    </li>
    *    <li>
    *       float[];
    *    </li>
    *    <li>
    *       double[];
    *    </li>
    *    <li>
    *       String[];
    *    </li>
    * </ul>
    * @param array array com os dados.
    */
   public void imprimirArray(Object array){
      ia.imprimirArray(array);
   }

   /**
    * Exibe as informações contidas no array fornecido.
    * <p>
    *    Tipos suportados:
    * </p>
    * <ul>
    *    <li>
    *       int[];
    *    </li>
    *    <li>
    *       float[];
    *    </li>
    *    <li>
    *       double[];
    *    </li>
    *    <li>
    *       String[];
    *    </li>
    * </ul>
    * @param array array com os dados.
    * @param nome nome personalizado para o array impresso.
    */
   public void imprimirArray(Object array, String nome){
      ia.imprimirArray(array, nome);
   }

   /**
    * Exibe as informações contidas na matriz fornecida.
    * <p>
    *    Tipos suportados:
    * </p>
    * <ul>
    *    <li>
    *       int[][];
    *    </li>
    *    <li>
    *       float[][];
    *    </li>
    *    <li>
    *       double[][];
    *    </li>
    *    <li>
    *       String[][];
    *    </li>
    * </ul>
    * @param matriz matriz com os dados.
    */
   public void imprimirMatriz(Object matriz){
      im.imprimirMatriz(matriz);
   }

   /**
    * Exibe as informações contidas na matriz fornecida.
    * <p>
    *    Tipos suportados:
    * </p>
    * <ul>
    *    <li>
    *       int[][];
    *    </li>
    *    <li>
    *       float[][];
    *    </li>
    *    <li>
    *       double[][];
    *    </li>
    *    <li>
    *       String[][];
    *    </li>
    * </ul>
    * @param matriz matriz com os dados.
    * @param nome nome personalizado para a matriz impressa.
    */
   public void imprimirMatriz(Object matriz, String nome){
      im.imprimirMatriz(matriz);
   }

   
   //MANIPULADOR DE DADOS ---------------------


   /**
    * Verifica se o conteúdo dos dados é simetrico. A simetria leva em conta 
    * se todas as colunas têm o mesmo tamanho.
    * <p>
    *    A simetria também leva em conta se o conteúdo dos dados possui elementos, 
    *    caso o tamanho seja zero será considerada como não simétrica.
    * <p>
    * <p>
    *    Dados nulos não classificados.
    * <p>
    * Exemplo:
    * <pre>
    * dados =  [
    *    1, 2, 3
    *    4, 5, 6, 7
    *    8, 9, 
    * ]
    * 
    * dadosSimetricos(dados) == false
    * </pre>
    * @param dados conjunto de dados.
    * @return true caso os dados sejam simétricos, false caso contrário.
    * @throws IllegalArgumentException se o conteúdo dos dados for nulo.
    */
   public boolean dadosSimetricos(Dados dados){
      return md.dadosSimetricos(dados);
   }


   /**
    * Adiciona uma coluna com conteúdo {@code vazio} ao final de todas as 
    * linhas do conteúdo dos dados.
    * <p>
    *    Exemplo:
    * </p>
    * <pre>
    * dados = [
    *    1, 2, 3 
    *    4, 5, 6 
    *    7, 8, 9 
    * ]
    *
    * novaColuna = [
    *    1, 2, 3, 
    *    4, 5, 6,  
    *    7, 8, 9, 
    * ]
    * </pre>
    * @param dados conjunto de dados.
    */
   public void adicionarColuna(Dados dados){
      md.adicionarColuna(dados);
   }


   /**
    * Adiciona uma coluna com conteúdo {@code vazio} no índice fornecido. 
    * Todos os itens depois do índice serão deslocados para a direita.
    * <p>
    *    Exemplo:
    * </p>
    * <pre>
    * dados = [
    *    1, 2, 3 
    *    4, 5, 6 
    *    7, 8, 9 
    * ]
    *
    * int indice = 1;
    *
    * novaColuna = [
    *    1,  , 2, 3
    *    4,  , 5, 6 
    *    7,  , 8, 9
    * ]
    * </pre>
    * @param dados conjunto de dados.
    * @param indice índice onde a nova coluna será adicionada.
    * @throws IllegalArgumentException se o conteúdo dos dados não for simétrico.
    * @throws IllegalArgumentException se o índice fornecido for inválido.
    */
   public void adicionarColuna(Dados dados, int indice){
      md.adicionarColuna(dados, indice);
   }


   /**
    * Adiciona uma linha com conteúdo {@code vazio} ao final do conteúdo dos dados.
    * <p>
    *    Exemplo:
    * </p>
    * <pre>
    * dados = [
    *    1, 2, 3 
    *    4, 5, 6 
    *    7, 8, 9 
    * ]
    *
    * novaLinha = [
    *    1, 2, 3
    *    4, 5, 6 
    *    7, 8, 9 
    *     ,  , 
    * ]
    * </pre>
    * @param dados conjunto de dados.
    * @throws IllegalArgumentException se o conteúdo dos dados não for simétrico.
    */
   public void adicionarLinha(Dados dados){
      md.adicionarLinha(dados);
   }


   /**
    * Adiciona uma linha com conteúdo {@code vazio} de acordo com o índice especificado. Todos 
    * os elementos depois da linha fornecida serão deslocados para baixo.
    * <p>
    *    Exemplo:
    * </p>
    * <pre>
    * dados = [
    *    1, 2, 3 
    *    4, 5, 6 
    *    7, 8, 9 
    * ]
    *
    * int indice = 1;
    *
    * novaLinha = [
    *    1, 2, 3
    *     ,  , 
    *    4, 5, 6 
    *    7, 8, 9 
    * ]
    * </pre>
    * @param dados conjunto de dados.
    * @param indice índice onde a nova linha será adicionada.
    * @throws IllegalArgumentException se o conteúdo dos dados não for simétrico.
    * @throws IllegalArgumentException se o índice fornecido for inválido.
    */
   public void adicionarLinha(Dados dados, int indice){
      md.adicionarLinha(dados, indice);
   }


   /**
    * Remove uma linha inteira do conjunto de dados
    * @param dados conjunto de dados.
    * @param indice índice da linha que será removida.
    * @throws IllegalArgumentException o conteúdo dos dados for nulo.
    * @throws IllegalArgumentException se o indice for inválido.
    */
   public void removerLinha(Dados dados, int indice){
      md.removerLinha(dados, indice);
   }


   /**
    * Remove todas as colunas dos dados de acordo com o índice fornecido.
    * @param dados conjunto de dados.
    * @param indice índice da coluna que será removida.
    * @throws IllegalArgumentException o conteúdo dos dados for nulo.
    * @throws IllegalArgumentException se o indice for inválido.
    */
   public void removerColuna(Dados dados, int indice){
      md.removerColuna(dados, indice);
   }


   /**
    * Substitui o valor de busca pelo novo valor fornecido, de acordo com a linha e coluna especificadas.
    * @param dados conjunto de dados.
    * @param idLinha índice da linha alvo para a alteração dos dados.
    * @param idColuna índice da coluna alvo para a alteração dos dados.
    * @param valor novo valor que será colocado.
    * @throws IllegalArgumentException se o conteúdo dos dados for nulo.
    * @throws IllegalArgumentException se o conteúdo dos dados não for simétrica.
    * @throws IllegalArgumentException se o valor do índice da linha fornecido estiver fora de alcance.
    * @throws IllegalArgumentException se o valor do índice da coluna fornecido estiver fora de alcance.
    * @throws IllegalArgumentException se o valor de busca for nulo.
    * @throws IllegalArgumentException se o novo valor de substituição for nulo;
    */
   public void editarValor(Dados dados, int idLinha, int idColuna, String valor){
      md.editarValor(dados, idLinha, idColuna, valor);
   }


   /**
    * Substitui todas as linhas dos dados pelo valor fornecido, caso na coluna fornecida tenha o valor buscado.
    * @param dados conjunto de dados.
    * @param idColuna índice da coluna alvo para a alteração dos dados.
    * @param busca valor que será procurado para ser substituído.
    * @param valor novo valor que será colocado.
    * @throws IllegalArgumentException se o conteúdo dos dados estiver nulo.
    * @throws IllegalArgumentException se o conteúdo dos dados não for simétrico.
    * @throws IllegalArgumentException se o valor do índice fornecido estiver fora de alcance.
    * @throws IllegalArgumentException se o valor de busca for nulo.
    * @throws IllegalArgumentException se o novo valor de substituição for nulo;
    */
   public void editarValor(Dados dados, int idColuna, String busca, String valor){
      md.editarValor(dados, idColuna, busca, valor);
   }


   /**
    * Troca os valores das colunas no conteúdo dos dados de acordo com os índices fornecidos.
    * <p>
    *    Exemplo:
    * </p>
    * <pre>
    * dados = [
    *    1, 2, 3
    *    4, 5, 6
    *    7, 8, 9    
    * ]
    *
    * int idColuna1 = 0;
    * int idColuna2 = 2;
    *
    * dados = [
    *    3, 2, 1
    *    6, 5, 2
    *    9, 8, 3    
    * ]
    * </pre>
    * @param dados conjunto de dados.
    * @param idColuna1 índice da primeira coluna que será trocada.
    * @param idColuna2 índice da segunda coluna que será trocada.
    * @throws IllegalArgumentException se o conteúdo dos dados estiver nulo.
    * @throws IllegalArgumentException se o conteúdo dos dados não for simétrico.
    * @throws IllegalArgumentException se a o conteúdo dos dados não tiver pelo menos duas colunas.
    * @throws IllegalArgumentException se os índices fornecidos estiverem fora de alcance do tamanho das colunas.
    * @throws IllegalArgumentException se as colunas fornecidas forem iguais.
    */
   public void trocarColunas(Dados dados, int idColuna1, int idColuna2){
      md.trocarColunas(dados, idColuna1, idColuna2);
   }


   /**
    * Remove a linha inteira dos dados caso exista algum valor nas colunas que não consiga ser convertido para
    * um valor numérico.
    * <p>
    *    É importante verificar e ter certeza se os dados não possuem nenhuma coluna com caracteres, caso isso seja verdade
    *    o método irá remover todas as colunas como consequência e o conteúdo dos dados estará vazio.
    * </p>
    * Exemplo:
    * <pre>
    * dados = [
    *    1, "a", 3
    *    4,  5 , 6
    *    7,    , 9    
    * ]
    * resultado = [
    *    4, 5, 6
    * ]
    * </pre>
    * @param dados conjunto de dados.
    */
   public void removerNaoNumericos(Dados dados){
      md.removerNaoNumericos(dados);
   }


   /**
    * Categoriza o conteúdo de dados na coluna relativa ao índice fornecido,
    * usando a técnica de One-Hot Encoding. 
    * <p>
    *    As novas colunas adicionadas representarão as categorias únicas encontradas 
    *    na coluna especificada, e os valores das novas colunas serão definidos como "1" 
    *    quando a categoria correspondente estiver presente na linha e "0" caso contrário.
    * <p>
    * Exemplo:
    * <pre>
    * dados = [
    *    a, b, c
    *    d, e, f
    *    g, h, i
    * ]
    *
    * categorizar(dados, 2);
    *
    * dados = [
    *    a, b, 1, 0, 0
    *    d, e, 0, 1, 0
    *    g, h, 0, 0, 1
    * ]
    * </pre>
    * @param dados conjunto de dados.
    * @param indice O índice da coluna desejada para categorização.
    * @throws IllegalArgumentException se o conteúdo dos dados não for simétrico.
    * @throws IllegalArgumentException se o índice fornecido for inválido.
    */
   public void categorizar(Dados dados, int indice){
      md.categorizar(dados, indice);
   }


   /**
    * Une os dois conjuntos de dados fornecidos
    * <p>
    *    A união adiciona as linhas do conteúdo de B ao final das linhas
    *    do conteúdo de A. 
    * </p>
    * Exemplo:
    * <pre>
    * a = [
    *    "a", "b", "c" 
    *    "d", "e", "f" 
    *    "g", "h", "i" 
    * ]
    *
    * b = [
    *    "j", "k", "l" 
    *    "m", "n", "o" 
    *    "p", "q", "r" 
    * ]
    *
    * união = [
    *    "a", "b", "c" 
    *    "d", "e", "f" 
    *    "g", "h", "i" 
    *    "j", "k", "l" 
    *    "m", "n", "o" 
    *    "p", "q", "r" 
    * ]
    * </pre>
    * @param a primeiro conjunto de dados.
    * @param b segnudo conjunto de dados.
    * @return novo conjunto de dados contendo a união entre A e B.
    * @throws IllegalArgumentException se o conteúdo de A não for simétrico.
    * @throws IllegalArgumentException se o conteúdo de B não for simétrico.
    * @throws IllegalArgumentException se a quantidade de colunas de A e B forem diferentes.
    */
   public Dados unir(Dados a, Dados b){
      return md.unir(a, b);
   }


   /**
    * Une o conteúdo de cada coluna dentro de A e B num
    * novo conjunto de dados.
    * <p>
    *    A lógica de união envolve primeiro adicionar o conteúdo da linha de A e
    *    depois adicionar o conteúdo da linha de B.
    * </p>
    *    Exemplo:
    * <pre>
    * a = [
    *    1, 2
    *    4, 5
    * ]
    *
    * b = [
    *    3
    *    6
    * ]
    *
    * união = [
    *    1, 2, 3
    *    4, 5, 6
    *    7, 8, 9
    * ]
    * </pre>
    * @param a primeiro conjunto de dados.
    * @param b segundo conjunto de dados.
    * @return novo objeto do tipo {@code Dados} conténdo a união por colunas de A com B.
    * @throws IllegalArgumentException caso a quantiade de linhas de A e B sejam diferentes.
    */
   public Dados unirColunas(Dados a, Dados b){
      return md.unirColuna(a, b);
   }


   /**
    * Remove linhas repetidas dentro do conjunto de dados.
    * <p>
    *    Exemplo:
    * </p>
    * <pre>
    * a = [
    *    1, 2, 3
    *    4, 5, 6
    *    4, 5, 6
    *    7, 8, 9
    * ]
    *
    * sem duplicadas = [
    *    1, 2, 3
    *    4, 5, 6
    *    7, 8, 9
    * ]
    * </pre>
    * @param dados conjunto de dados.
    */
   public void removerDuplicadas(Dados dados){
      md.removerDuplicadas(dados);
   }


   /**
    * Normaliza todos os valores numéricos contidos no conjunto de dados.
    * <p>
    *    Caso alguma coluna possua algum valor que não possa ser convertido o 
    *    processo é cancelado e a coluna não sofrerá alterações.
    * </p>
    * Exemplo:
    * <pre>
    * dados = [
    *    1, 5 
    *    2, a
    *    3, 7
    *    4, 8
    *    5, 9
    * ]
    *
    * normalizado = [
    *    0.00, 5 
    *    0.25, a
    *    0.50, 7
    *    0.75, 8
    *    1.00, 9
    * ]
    * </pre>
    * @param dados conjunto de dados.
    * @throws IllegalArgumentException se o conteúdo dos dados estiver vazio.
    * @throws IllegalArgumentException se o conteúdo dos dados não for simétrico.
    */
   public void normalizar(Dados dados){
      md.normalizar(dados);
   }


   /**
    * Normaliza os valores numéricos contido na coluna fornecida.
    * <p>
    *    Caso a coluna possua algum valor que não possa ser convertido o 
    *    processo é cancelado.
    * </p>
    * Exemplo:
    * <pre>
    * dados = [
    *    1, 5 
    *    2, a
    *    3, 7
    *    4, 8
    *    5, 9
    * ]
    *
    * normalizar(dados, 0);
    *
    * dados = [
    *    0.00, 5 
    *    0.25, a
    *    0.50, 7
    *    0.75, 8
    *    1.00, 9
    * ]
    * </pre>
    * @param dados conjunto de dados.
    * @throws IllegalArgumentException se o conteúdo dos dados não for simétrico.
    */
   public void normalizar(Dados dados, int idCol){
      dados.normalizar(idCol);
   }


   /**
    * Captaliza todo valor alfabético contido no conteúdo dos dados.
    * <p>
    *    Exemplo
    * </p>
    * <pre>
    * a = [
    *    UM, doIS
    *    trÊs, QuAtRo 
    * ]
    *
    * capitalizado = [
    *    Um, Dois
    *    Três, Quatro 
    * ]
    * </pre>
    * @param dados conjunto de dados.
    * @throws IllegalArgumentException se o conteúdo dos dados for vazio.
    * @throws IllegalArgumentException se o conteúdo dos dados não for simétrico.
    */
   public void capitalizar(Dados dados){
      md.capitalizar(dados);
   }


   /**
    * Captaliza todo valor alfabético contido no conteúdo dos dados.
    * <p>
    *    Exemplo
    * </p>
    * <pre>
    * a = [
    *    UM, doIS
    *    trÊs, QuAtRo 
    * ]
    *
    * capitalizar(dados, 0);
    *
    * capitalizado = [
    *    Um, doIS
    *    Três, QuAtRo 
    * ]
    * </pre>
    * @param dados conjunto de dados.
    * @param idCol índice da coluna desejada.
    * @throws IllegalArgumentException se o conteúdo dos dados for vazio.
    * @throws IllegalArgumentException se o conteúdo dos dados não for simétrico.
    * @throws IllegalArgumentException se o índice da coluna fornecida for inválido.
    */
   public void capitalizar(Dados dados, int idCol){
      dados.capitalizar(idCol);
   }


   /**
    * Substitui pelo novo valor todo o conteúdo encontrado na coluna de acordo com a busca.
    * <p>
    *    Exemplo:
    * </p>
    * <pre>
    * d = [
    *    a.b, c.d
    *    e.f, g.h  
    * ]
    * 
    * substituir(dados, 0, ".", "");
    * 
    * d = [
    *    ab, c.d
    *    ef, g.h  
    * ]
    * </pre>
    * @param idCol índice da coluna desejada.
    * @param busca valor que será substituído.
    * @param valor novo valor que será colocado no lugar.
    * @throws IllegalArgumentException se o conteúdo dos dados estiver vazio.
    * @throws IllegalArgumentException se os dados não forem simétricos.
    * @throws IllegalArgumentException e o índice da coluna for inválido.
    */
   public void substituir(Dados dados, int idCol, String busca, String valor){
      dados.substituir(idCol, null, null);
   }


   /**
    * Substitui pelo novo valor todo o conteúdo encontrado dentro do conteúdo dos dados.
    * <p>
    *    Exemplo:
    * </p>
    * <pre>
    * d = [
    *    a.b, c.d
    *    e.f, g.h  
    * ]
    * 
    * substituir(dados, ".", "");
    * 
    * d = [
    *    ab, cd
    *    ef, gh  
    * ]
    * </pre>
    * @param idCol índice da coluna desejada.
    * @param busca valor que será substituído.
    * @param valor novo valor que será colocado no lugar.
    * @throws IllegalArgumentException se o conteúdo dos dados estiver vazio.
    * @throws IllegalArgumentException se os dados não forem simétricos.
    */
   public void substituir(Dados dados, String busca, String valor){
      md.substituir(dados, busca, valor);
   }


   /**
    * Ordena o conteúdo contido nos dados de acordo com a coluna desejada.
    * <p>
    *    A ordenação consequentemente irá mudar a ordem de organização
    *    dos outros elementos.
    * </p>
    * Exemplo:
    * <pre>
    * d = [
    *    30
    *    40
    *    10
    *    50
    *    20
    * ]
    * 
    * ordenar(d, 0, true).
    * 
    * d = [
    *    10
    *    20
    *    30
    *    40
    *    50
    * ]
    * </pre>
    * @param dados conjunto de dados.
    * @param idCol índice da coluna desejada.
    * @param crescente true caso a ordenação deva ser crescente, false caso contrário.
    * @throws IllegalArgumentException se o conteúdo dos dados estiver vazio.
    * @throws IllegalArgumentException se o conteúdo dos dados não forem simétricos.
    * @throws IllegalArgumentException se o índice da coluna for inválido.
    * @throws IllegalArgumentException se a coluna conter valores que não possam ser convertidos para números.
    */
   public void ordenar(Dados dados, int idCol, boolean crescente){
      dados.ordenar(idCol, crescente);
   }


   /**
    * Filtra o conteúdo contido nos dados fornecidos de 
    * acordo com o valor de busca.
    * <p>
    *    A busca retorna toda a linha caso o valor desejado seja encontrado. É importante 
    *    que o valor seja exatamente igual ao que está no conteúdo dos dados.
    * </p>
    * Exemplo:
    * <pre>
    * dados = [
    *    a, 1, 2
    *    a, 3, 4
    *    b, 5, 6
    *    c, 7, 8  
    * ]
    *
    * Dados filtro = filtrar(dados, 0, "a");
    *
    * filtro = [
    *    a, 1, 2
    *    a, 3, 4
    * ]
    * </pre>
    * @param dados conjunto de dados.
    * @param idCol índice da coluna para busca.
    * @param busca valor de busca desejado.
    * @return novo conjunto de dados contendo apenas as informações filtradas, caso 
    *    não seja encontrado nenhum valor desejado, o conteúdo dos novos dados
    *    estará {@code vazio}.
    */
   public Dados filtrar(Dados dados, int idCol, String busca){
      return md.filtrar(dados, idCol, busca);
   }


   /**
    * <p>
    *    Filtra o conteúdo numérico contido nos dados fornecidos de 
    *    acordo com o operador fornecido.
    * </p>
    * A ordem da filtragem segue o seguinte critério:
    * <p>
    *    {@code valorContidoNosDados (operador) valorDesejado}
    * </p>
    * Dados que não possam ser convertidos serão desconsiderados e consequentemente 
    * não incluídos no resultado filtrado.
    * <p>
    *    Operadores suportados:
    * </p>
    * <ul>
    *    <li> {@code >} </li>
    *    <li> {@code >=} </li>
    *    <li> {@code <} </li>
    *    <li> {@code <=} </li>
    *    <li> {@code ==} </li>
    *    <li> {@code !=} </li>
    * </ul>
    * @param dados conjunto de dados.
    * @param idCol índice da coluna para busca.
    * @param operador operador desejado.
    * @param valor valor de busca desejado.
    * @return novo conjunto de dados contendo apenas as informações filtradas, caso 
    *    não seja encontrado nenhum valor desejado, o conteúdo dos novos dados
    *    estará {@code vazio}.
    * @throws IllegalArgumentException se o conteúdo dos dados estiver vazio.
    * @throws IllegalArgumentException se o índice da coluna for inválido.
    * @throws IllegalArgumentException se o operador fornecido não for suportado.
    */
   public Dados filtrar(Dados dados, int idCol, String operador, String valor){
      return md.filtrar(dados, idCol, operador, valor);
   }


   /**
    * Substitui todos os valores ausentes no conteúdos dos dados fornecidos.
    * <p>
    *    São considerados dados ausentes quaisquer valores {@code vazio},
    *    {@code em branco} e {@code com "?"}
    * </p>
    * Valores de preenchimento como {@code média}, {@code mediana} entre outros, podem 
    * ser obtidos diretamente pelo objeto {@code Dados}.
    * @param dados conjunto de dados.
    * @param valor valor de preenchimento em elementos ausentes.
    */
   public void preencherAusentes(Dados dados, double valor){
      md.preencherAusentes(dados, valor);
   }


   /**
    * Clona o conteúdo dos dados fornecidos em uma nova estrutura e devolve um novo objeto
    * de dados conteúdo o mesmo conteúdo do original.
    * @param dados conjunto de dados originais.
    * @return novo objeto do tipo {@code Dados} com o mesmo conteúdo do original.
    */
   public Dados clonarDados(Dados dados){
      return md.clonarDados(dados);
   }


   /**
    * Substitui todos os valores ausentes no conteúdos dos dados fornecidos de 
    * acordo com a coluna informada.
    * <p>
    *    São considerados dados ausentes quaisquer valores {@code vazio},
    *    {@code em branco} e {@code com "?"}
    * </p>
    * Valores de preenchimento como {@code média}, {@code mediana} entre outros, podem 
    * ser obtidos diretamente pelo objeto {@code Dados}.
    * @param dados conjunto de dados.
    * @param idCol índice da coluna desejada.
    * @param valor valor de preenchimento em elementos ausentes.
    */
   public void preencherAusentes(Dados dados, int idCol, double valor){
      md.preencherAusentes(dados, idCol, valor);
   }


   /**
    * Descreve as dimensões do conteúdo dos dados, tanto em questão de quantidade de linhas 
    * quanto quantidade de colunas.
    * @param dados conjunto de dados.
    * @return array contendo as informações das dimensões do conteúdo do conjunto de dados, o primeiro elemento 
    * corresponde a quantidade de linhas e o segundo elemento corresponde a quantidade de colunas seguindo o 
    * formato {@code [linhas, colunas]}.
    * @throws IllegalArgumentException se o conteúdo estiver vazio.
    * @throws IllegalArgumentException se os dados não forem simétricos, tendo colunas com tamanhos diferentes.
    */
   public int[] shapeDados(Dados dados){
      return dados.shape();
   }


   //GERENCIADOR DE ARQUIVOS ---------------------


   /**
    * Lê o arquivo .csv de acordo com o caminho especificado.
    * Espaços contidos serão removidos.
    * <p>
    *    O formato da estrutura de dados será um objeto do tipo 
    *    {@code Dados}, contendo as linhas e colunas das informações lidas.
    * </p>
    * @param caminho caminho do arquivo, com extensão.
    * @return objeto do tipo {@code Dados} contendo as informações do arquivo lido.
    * @throws IllegalArgumentException caso não encontre o diretório fornecido.
    * @throws IllegalArgumentException caso arquivo não possua a extensão .csv.
    */
   public Dados lerCsv(String caminho){
      return ga.lerCsv(caminho);
   }


   /**
    * Lê o arquivo .txt de acordo com o caminho especificado.
    * Espaços contidos serão removidos.
    * <p>
    *    A separação do arquivo txt é considerada usando espaços
    * </p>
    * <p>
    *    O formato da estrutura de dados será um objeto do tipo 
    *    {@code Dados}, contendo as linhas e colunas das informações lidas.
    * </p>
    * @param caminho caminho do arquivo, com extensão.
    * @return objeto do tipo {@code Dados} contendo as informações do arquivo lido.
    * @throws IllegalArgumentException caso não encontre o diretório fornecido.
    * @throws IllegalArgumentException caso arquivo não possua a extensão .txt.
    */
   public Dados lerTxt(String caminho){
      return ga.lerTxt(caminho);
   }


   /**
    * Grava o conteúdo do conjunto de dados em um arquivo .csv.
    * @param dados conjunto de dados.
    * @param filePath caminho do arquivo onde os dados serão gravados, excluindo a extensão .csv.
    */
   public void exportarCsv(Dados dados, String caminho){
      ga.exportarCsv(dados, caminho);;
   }


   // GERENCIADOR TREINO TESTE ---------------------

   
   /**
    * Embaralha o conjunto de dados aleatoriamente.
    * <p>
    *    A alteração irá afetar o conteúdo dos dados recebidos.
    *    Caso queira manter os dados originais, é recomendado fazer uma cópia previamente.
    * </p>
    * @param dados conjunto de dados completo.
    */
   public void embaralharDados(int[][] dados){
      gtt.embaralharDados(dados);
   }


   /**
    * Embaralha o conjunto de dados aleatoriamente.
    * <p>
    *    A alteração irá afetar o conteúdo dos dados recebidos.
    *    Caso queira manter os dados originais, é recomendado fazer uma cópia previamente.
    * </p>
    * @param dados conjunto de dados completo.
    */
   public void embaralharDados(float[][] dados){
      gtt.embaralharDados(dados);
   }


   /**
    * Embaralha o conjunto de dados aleatoriamente.
    * <p>
    *    A alteração irá afetar o conteúdo dos dados recebidos.
    *    Caso queira manter os dados originais, é recomendado fazer uma cópia previamente.
    * </p>
    * @param dados conjunto de dados completo.
    */
   public void embaralharDados(double[][] dados){
      gtt.embaralharDados(dados);
   }


   /**
    * <p>
    *    Método para treino da rede neural.
    * </p>
    * Separa os dados que serão usados como entrada de acordo com os valores fornecidos.
    * <p>
    *    A lógica de separação dos dados de entrada envolve iniciar a coleta das colunas em ordem crescente,
    *    exemplo: 
    * </p>
    * <pre>
    * dados = [
    *    1, 2, 3
    *    4, 5, 6
    *    7, 8, 9    
    * ]
    *
    * int colunas = 2;
    *
    * entrada = [
    *    1, 2
    *    4, 5
    *    7, 8 
    * ]
    * </pre>
    * @param dados conjunto de dados completo.
    * @param colunas quantidade de colunas que serão preservadas, começando pela primeira até o valor fornecido.
    * @return nova matriz de dados apenas com as colunas desejadas.
    * @throws IllegalArgumentException Se o número de colunas for maior que o número de colunas disponíveis nos dados.
    * @throws IllegalArgumentException Se o número de colunas for menor que um.
    */
   public int[][] separarDadosEntrada(int[][] dados, int colunas){
      return gtt.separarDadosEntrada(dados, colunas);
   }


   /**
    * <p>
    *    Método para treino da rede neural.
    * </p>
    * Separa os dados que serão usados como entrada de acordo com os valores fornecidos.
    * <p>
    *    A lógica de separação dos dados de entrada envolve iniciar a coleta das colunas em ordem crescente,
    *    exemplo: 
    * </p>
    * <pre>
    * dados = [
    *    1, 2, 3
    *    4, 5, 6
    *    7, 8, 9    
    * ]
    *
    * int colunas = 2;
    *
    * entrada = [
    *    1, 2
    *    4, 5
    *    7, 8 
    * ]
    * </pre>
    * @param dados conjunto de dados completo.
    * @param colunas quantidade de colunas que serão preservadas, começando pela primeira até o valor fornecido.
    * @return nova matriz de dados apenas com as colunas desejadas.
    * @throws IllegalArgumentException Se o número de colunas for maior que o número de colunas disponíveis nos dados.
    * @throws IllegalArgumentException Se o número de colunas for menor que um.
    */
   public float[][] separarDadosEntrada(float[][] dados, int colunas){
      return gtt.separarDadosEntrada(dados, colunas);
   }


   /**
    * <p>
    *    Método para treino da rede neural.
    * </p>
    * Separa os dados que serão usados como entrada de acordo com os valores fornecidos.
    * <p>
    *    A lógica de separação dos dados de entrada envolve iniciar a coleta das colunas em ordem crescente,
    *    exemplo: 
    * </p>
    * <pre>
    * dados = [
    *    1, 2, 3
    *    4, 5, 6
    *    7, 8, 9    
    * ]
    *
    * int colunas = 2;
    *
    * entrada = [
    *    1, 2
    *    4, 5
    *    7, 8 
    * ]
    * </pre>
    * @param dados conjunto de dados completo.
    * @param colunas quantidade de colunas que serão preservadas, começando pela primeira até o valor fornecido.
    * @return nova matriz de dados apenas com as colunas desejadas.
    * @throws IllegalArgumentException Se o número de colunas for maior que o número de colunas disponíveis nos dados.
    * @throws IllegalArgumentException Se o número de colunas for menor que um.
    */
   public double[][] separarDadosEntrada(double[][] dados, int colunas){
      return gtt.separarDadosEntrada(dados, colunas);
   }


   /**
    * <p>
    *    Método para treino da rede neural.
    * </p>
    * Extrai os dados de saída do conjunto de dados e devolve um novo conjunto de dados contendo apenas as 
    * colunas de dados de saída especificadas.
    * <p>
    *    A lógica de separação dos dados de saída envolve iniciar a coleta das colunas em ordem decrescente,
    *    exemplo: 
    * </p>
    * <pre>
    * dados = [
    *    1, 2, 3
    *    4, 5, 6
    *    7, 8, 9
    * ]
    *
    * int colunas = 2;
    *
    * saida = [
    *    2, 3
    *    5, 6
    *    8, 9
    * ]
    * </pre>
    * @param dados O conjunto de dados com as informações completas.
    * @param colunas O número de colunas de dados de saída que serão extraídas.
    * @return novo conjunto de dados com apenas as colunas de dados de saída.
    * @throws IllegalArgumentException Se o número de colunas for maior que o número de colunas disponíveis nos dados.
    * @throws IllegalArgumentException Se o número de colunas for menor que um.
    */
   public int[][] separarDadosSaida(int[][] dados, int colunas){
      return gtt.separarDadosSaida(dados, colunas);
   }


   /**
    * <p>
    *    Método para treino da rede neural.
    * </p>
    * Extrai os dados de saída do conjunto de dados e devolve um novo conjunto de dados contendo apenas as 
    * colunas de dados de saída especificadas.
    * <p>
    *    A lógica de separação dos dados de saída envolve iniciar a coleta das colunas em ordem decrescente,
    *    exemplo: 
    * </p>
    * <pre>
    * dados = [
    *    1, 2, 3
    *    4, 5, 6
    *    7, 8, 9
    * ]
    *
    * int colunas = 2;
    *
    * saida = [
    *    2, 3
    *    5, 6
    *    8, 9
    * ]
    * </pre>
    * @param dados O conjunto de dados com as informações completas.
    * @param colunas O número de colunas de dados de saída que serão extraídas.
    * @return novo conjunto de dados com apenas as colunas de dados de saída.
    * @throws IllegalArgumentException Se o número de colunas for maior que o número de colunas disponíveis nos dados.
    * @throws IllegalArgumentException Se o número de colunas for menor que um.
    */
   public float[][] separarDadosSaida(float[][] dados, int colunas){
      return gtt.separarDadosSaida(dados, colunas);
   }


   /**
    * <p>
    *    Método para treino da rede neural.
    * </p>
    * Extrai os dados de saída do conjunto de dados e devolve um novo conjunto de dados contendo apenas as 
    * colunas de dados de saída especificadas.
    * <p>
    *    A lógica de separação dos dados de saída envolve iniciar a coleta das colunas em ordem decrescente,
    *    exemplo: 
    * </p>
    * <pre>
    * dados = [
    *    1, 2, 3
    *    4, 5, 6
    *    7, 8, 9
    * ]
    *
    * int colunas = 2;
    *
    * saida = [
    *    2, 3
    *    5, 6
    *    8, 9
    * ]
    * </pre>
    * @param dados O conjunto de dados com as informações completas.
    * @param colunas O número de colunas de dados de saída que serão extraídas.
    * @return novo conjunto de dados com apenas as colunas de dados de saída.
    * @throws IllegalArgumentException Se o número de colunas for maior que o número de colunas disponíveis nos dados.
    * @throws IllegalArgumentException Se o número de colunas for menor que um.
    */
   public double[][] separarDadosSaida(double[][] dados, int colunas){
      return gtt.separarDadosSaida(dados, colunas);
   }


   /**
    * Separa o conjunto de dados em dados de treino e dados de teste, de acordo com o tamanho do teste fornecido.
    * 
    * <p>
    *    A função recebe um conjunto de dados completo e separa ele em duas matrizes, uma para treino e outra para teste.
    *    A quantidade de dados para o conjunto de teste é determinada pelo parâmetro tamanhoTeste.
    * </p>
    * 
    * <p>
    *    Exemplo de uso:
    * </p>
    * <pre>{@code 
    * int[][][] treinoTeste = separarTreinoTeste(dados, 0.25f);
    * int[][] treino = treinoTeste[0];
    * int[][] teste = treinoTeste[1];}
    * </pre>
    * @param dados O conjunto de dados completo.
    * @param tamanhoTeste O tamanho relativo do conjunto de teste (entre 0 e 1).
    * @return Um array de duas matrizes contendo os dados de treino e teste, respectivamente.
    * @throws IllegalArgumentException caso o conjunto de dados for nulo.
    * @throws IllegalArgumentException caso o tamanho de teste estiver fora do intervalo (0, 1).
    */
   public int[][][] separarTreinoTeste(int[][] dados, float tamanhoTeste){
      return gtt.separarTreinoTeste(dados, tamanhoTeste);
   }


   /**
    * Separa o conjunto de dados em dados de treino e dados de teste, de acordo com o tamanho do teste fornecido.
    * 
    * <p>
    *    A função recebe um conjunto de dados completo e separa ele em duas matrizes, uma para treino e outra para teste.
    *    A quantidade de dados para o conjunto de teste é determinada pelo parâmetro tamanhoTeste.
    * </p>
    * 
    * <p>
    *    Exemplo de uso:
    * </p>
    * <pre>{@code 
    * float[][][] treinoTeste = separarTreinoTeste(dados, 0.25f);
    * float[][] treino = treinoTeste[0];
    * float[][] teste = treinoTeste[1];}
    * </pre>
    * @param dados O conjunto de dados completo.
    * @param tamanhoTeste O tamanho relativo do conjunto de teste (entre 0 e 1).
    * @return Um array de duas matrizes contendo os dados de treino e teste, respectivamente.
    * @throws IllegalArgumentException caso o conjunto de dados for nulo.
    * @throws IllegalArgumentException caso o tamanho de teste estiver fora do intervalo (0, 1).
    */
   public float[][][] separarTreinoTeste(float[][] dados, float tamanhoTeste){
      return gtt.separarTreinoTeste(dados, tamanhoTeste);
   }


   /**
    * Separa o conjunto de dados em dados de treino e dados de teste, de acordo com o tamanho do teste fornecido.
    * 
    * <p>
    *    A função recebe um conjunto de dados completo e separa ele em duas matrizes, uma para treino e outra para teste.
    *    A quantidade de dados para o conjunto de teste é determinada pelo parâmetro tamanhoTeste.
    * </p>
    * 
    * <p>
    *    Exemplo de uso:
    * </p>
    * <pre>{@code 
    * double[][][] treinoTeste = separarTreinoTeste(dados, 0.25f);
    * double[][] treino = treinoTeste[0];
    * double[][] teste = treinoTeste[1];}
    * </pre>
    * @param dados O conjunto de dados completo.
    * @param tamanhoTeste O tamanho relativo do conjunto de teste (entre 0 e 1).
    * @return Um array de duas matrizes contendo os dados de treino e teste, respectivamente.
    * @throws IllegalArgumentException caso o conjunto de dados for nulo.
    * @throws IllegalArgumentException caso o tamanho de teste estiver fora do intervalo (0, 1).
    */
   public double[][][] separarTreinoTeste(double[][] dados, float tamanhoTeste){
      return gtt.separarTreinoTeste(dados, tamanhoTeste);
   }


   //CONVERSOR DE DADOS ----------------------

   /**
    * Converte o conteúdo do conjunto de dados para uma matriz bidimensional 
    * com os valores numéricos.
    * @param dados conjunto de dados.
    * @return matriz convertida para valores tipo {@code int}.
    */
   public int[][] dadosParaInt(Dados dados){
      return cd.dadosParaInt(dados);
   }


   /**
    * Converte o conteúdo do conjunto de dados para uma matriz bidimensional 
    * com os valores numéricos.
    * @param dados conjunto de dados.
    * @return matriz convertida para valores tipo {@code float}.
    */
   public float[][] dadosParaFloat(Dados dados){
      return cd.dadosParaFloat(dados);
   }


   /**
    * Converte o conteúdo do conjunto de dados para uma matriz bidimensional 
    * com os valores numéricos.
    * @param dados conjunto de dados.
    * @return matriz convertida para valores tipo {@code double}.
    */
   public double[][] dadosParaDouble(Dados dados){
      return cd.dadosParaDouble(dados);
   }


   /**
    * Converte o conjunto de dados para uma matriz bidimensional 
    * com os conteúdo contido nos dados.
    * @param dados conjunto de dados.
    * @return matriz convertida para valores tipo {@code String}.
    */
   public String[][] dadosParaString(Dados dados){
      return cd.dadosParaString(dados);
   }


   //OPERADOR MATRIZ --------------


   /**
    * Retorna uma nova matriz que possui o conteúdo das linhas 
    * de acordo com os índices fornecidos.
    * <p>
    *    Exemplo:
    * </p>
    * <pre>
    * dados = [
    *    1, 2, 3
    *    4, 5, 6
    *    7, 8, 9
    * ]
    *
    * subLinhas = obterSubLinhas(dados, 0, 2);
    *
    * subLinhas = [
    *    1, 2, 3
    *    4, 5, 6
    * ]
    * </pre>
    * @param dados matriz contendo os dados completos.
    * @param inicio índice inicial do corte (inclusivo).
    * @param fim índice final do corte (exclusivo).
    * @return submatriz contendo o conteúdo da matriz original, com os dados selecionados.
    * @throws IllegalArgumentException se os índices fornecidos forem inválidos.
    */
   public int[][] obterSubLinhas(int[][] dados, int inicio, int fim){
      return om.obterSubLinhas(dados, inicio, fim);
   }


   /**
    * Retorna uma nova matriz que possui o conteúdo das linhas 
    * de acordo com os índices fornecidos.
    * <p>
    *    Exemplo:
    * </p>
    * <pre>
    * dados = [
    *    1, 2, 3
    *    4, 5, 6
    *    7, 8, 9
    * ]
    *
    * subLinhas = obterSubLinhas(dados, 0, 2);
    *
    * subLinhas = [
    *    1, 2, 3
    *    4, 5, 6
    * ]
    * </pre>
    * @param dados matriz contendo os dados completos.
    * @param inicio índice inicial do corte (inclusivo).
    * @param fim índice final do corte (exclusivo).
    * @return submatriz contendo o conteúdo da matriz original, com os dados selecionados.
    * @throws IllegalArgumentException se os índices fornecidos forem inválidos.
    */
   public float[][] obterSubLinhas(float[][] dados, int inicio, int fim){
      return om.obterSubLinhas(dados, inicio, fim);
   }


   /**
    * Retorna uma nova matriz que possui o conteúdo das linhas 
    * de acordo com os índices fornecidos.
    * <p>
    *    Exemplo:
    * </p>
    * <pre>
    * dados = [
    *    1, 2, 3
    *    4, 5, 6
    *    7, 8, 9
    * ]
    *
    * subLinhas = obterSubLinhas(dados, 0, 2);
    *
    * subLinhas = [
    *    1, 2, 3
    *    4, 5, 6
    * ]
    * </pre>
    * @param dados matriz contendo os dados completos.
    * @param inicio índice inicial do corte (inclusivo).
    * @param fim índice final do corte (exclusivo).
    * @return submatriz contendo o conteúdo da matriz original, com os dados selecionados.
    * @throws IllegalArgumentException se os índices fornecidos forem inválidos.
    */
   public double[][] obterSubLinhas(double[][] dados, int inicio, int fim){
      return om.obterSubLinhas(dados, inicio, fim);
   }


   /**
    * Retorna uma nova matriz que possui o conteúdo das colunas 
    * de acordo com os índices fornecidos.
    * <p>
    *    Exemplo:
    * </p>
    * <pre>
    * dados = [
    *    1, 2, 3
    *    4, 5, 6
    *    7, 8, 9
    * ]
    *
    * subColunas = obterColunas(dados, 0, 2);
    *
    * subColunas = [
    *    1, 2
    *    4, 5
    *    7, 8
    * ]
    * </pre>
    * @param dados matriz contendo os dados completos.
    * @param inicio índice inicial do corte (inclusivo).
    * @param fim índice final do corte (exclusivo).
    * @return submatriz contendo o conteúdo da matriz original, com os dados filtrados de acordo
    *    com as colunas indicadas.
    */
   public int[][] obterSubColunas(int[][] dados, int inicio, int fim){
      return om.obterSubColunas(dados, inicio, fim);
   }


   /**
    * Retorna uma nova matriz que possui o conteúdo das colunas 
    * de acordo com os índices fornecidos.
    * <p>
    *    Exemplo:
    * </p>
    * <pre>
    * dados = [
    *    1, 2, 3
    *    4, 5, 6
    *    7, 8, 9
    * ]
    *
    * subColunas = obterColunas(dados, 0, 2);
    *
    * subColunas = [
    *    1, 2
    *    4, 5
    *    7, 8
    * ]
    * </pre>
    * @param dados matriz contendo os dados completos.
    * @param inicio índice inicial do corte (inclusivo).
    * @param fim índice final do corte (exclusivo).
    * @return submatriz contendo o conteúdo da matriz original, com os dados filtrados de acordo
    *    com as colunas indicadas.
    */
   public float[][] obterSubColunas(float[][] dados, int inicio, int fim){
      return om.obterSubColunas(dados, inicio, fim);
   }


   /**
    * Retorna uma nova matriz que possui o conteúdo das colunas 
    * de acordo com os índices fornecidos.
    * <p>
    *    Exemplo:
    * </p>
    * <pre>
    * dados = [
    *    1, 2, 3
    *    4, 5, 6
    *    7, 8, 9
    * ]
    *
    * subColunas = obterColunas(dados, 0, 2);
    *
    * subColunas = [
    *    1, 2
    *    4, 5
    *    7, 8
    * ]
    * </pre>
    * @param dados matriz contendo os dados completos.
    * @param inicio índice inicial do corte (inclusivo).
    * @param fim índice final do corte (exclusivo).
    * @return submatriz contendo o conteúdo da matriz original, com os dados filtrados de acordo
    *    com as colunas indicadas.
    */
   public double[][] obterSubColunas(double[][] dados, int inicio, int fim){
      return om.obterSubColunas(dados, inicio, fim);
   }


   /**
    * Preenche o conteúdo da matriz de acordo com o valor fornecido.
    * @param matriz matriz com os dados.
    * @param valor valor de preenchimento.
    */
   public void preencherMatriz(int[][] matriz, int valor){
      om.preencherMatriz(matriz, valor);
   }


   /**
    * Preenche o conteúdo da matriz de acordo com o valor fornecido.
    * @param matriz matriz com os dados.
    * @param valor valor de preenchimento.
    */
   public void preencherMatriz(float[][] matriz, float valor){
      om.preencherMatriz(matriz, valor);
   }


   /**
    * Preenche o conteúdo da matriz de acordo com o valor fornecido.
    * @param matriz matriz com os dados.
    * @param valor valor de preenchimento.
    */
   public void preencherMatriz(double[][] matriz, double valor){
      om.preencherMatriz(matriz, valor);
   }


   /**
    * Preenche o conteúdo da matriz para que fique no formato identidade, onde
    * apenas os elementos da diagonal esquerda para direita tem valores iguais a 1.
    * <p>Exemplo:<pre>
    * m = [
    *  1, 0, 0
    *  0, 1, 0
    *  0, 0, 1
    * ]
    * </pre></p>
    * @param matriz
    */
   public void matrizIdentidade(int[][] matriz){
      om.matrizIdentidade(matriz);
   }


   /**
    * Preenche o conteúdo da matriz para que fique no formato identidade, onde
    * apenas os elementos da diagonal esquerda para direita tem valores iguais a 1.
    * <p>Exemplo:<pre>
    * m = [
    *  1, 0, 0
    *  0, 1, 0
    *  0, 0, 1
    * ]
    * </pre></p>
    * @param matriz
    */
   public void matrizIdentidade(float[][] matriz){
      om.matrizIdentidade(matriz);
   }


   /**
    * Preenche o conteúdo da matriz para que fique no formato identidade, onde
    * apenas os elementos da diagonal esquerda para direita tem valores iguais a 1.
    * <p>Exemplo:<pre>
    * m = [
    *  1, 0, 0
    *  0, 1, 0
    *  0, 0, 1
    * ]
    * </pre></p>
    * @param matriz
    */
   public void matrizIdentidade(double[][] matriz){
      om.matrizIdentidade(matriz);
   }


   /**
    * Realiza a transposição da matriz fornecida. A transposição consiste em 
    * inverter as linhas e colunas da matriz.
    * <p>Exemplo:<pre>
    * m = [
    *  1, 2, 3
    *  4, 5, 6
    *  7, 8, 9
    * ]
    * t = [
    *  1, 4, 7
    *  2, 5, 8
    *  3, 6, 9
    * ]
    * </pre></p>
    * @param matriz matriz original para transposição
    * @return matriz transposta.
    */
   public int[][] transporMatriz(int[][] matriz){
      return om.transporMatriz(matriz);
   }


   /**
    * Realiza a transposição da matriz fornecida. A transposição consiste em 
    * inverter as linhas e colunas da matriz.
    * <p>Exemplo:<pre>
    * m = [
    *  1, 2, 3
    *  4, 5, 6
    *  7, 8, 9
    * ]
    * t = [
    *  1, 4, 7
    *  2, 5, 8
    *  3, 6, 9
    * ]
    * </pre></p>
    * @param matriz matriz original para transposição
    * @return matriz transposta.
    */
   public float[][] transporMatriz(float[][] matriz){
      return om.transporMatriz(matriz);
   }


   /**
    * Realiza a transposição da matriz fornecida. A transposição consiste em 
    * inverter as linhas e colunas da matriz.
    * <p>Exemplo:<pre>
    * m = [
    *  1, 2, 3
    *  4, 5, 6
    *  7, 8, 9
    * ]
    * t = [
    *  1, 4, 7
    *  2, 5, 8
    *  3, 6, 9
    * ]
    * </pre></p>
    * @param matriz matriz original para transposição
    * @return matriz transposta.
    */
   public double[][] transporMatriz(double[][] matriz){
      return om.transporMatriz(matriz);
   }


   /**
    * Realiza a soma entre as duas matrizes fornecidas.
    * <p>Exemplo:<pre>
    * a = [
    *   1, 1
    *   1, 1
    * ]
    * b = [
    *   2, 2
    *   2, 2
    * ]
    * soma = [
    *   3, 3
    *   3, 3
    * ]
    * </pre></p>
    * @param a primeira matriz.
    * @param b segunda matriz.
    * @return matriz contendo o resultado da soma.
    * @throws IllegalArgumentException se as dimensões de A e B forem incompatíveis.
    */
   public int[][] somarMatrizes(int[][] a, int[][] b){
      return om.somarMatrizes(a, b);
   }


   /**
    * Realiza a soma entre as duas matrizes fornecidas.
    * <p>Exemplo:<pre>
    * a = [
    *   1, 1
    *   1, 1
    * ]
    * b = [
    *   2, 2
    *   2, 2
    * ]
    * soma = [
    *   3, 3
    *   3, 3
    * ]
    * </pre></p>
    * @param a primeira matriz.
    * @param b segunda matriz.
    * @return matriz contendo o resultado da soma.
    * @throws IllegalArgumentException se as dimensões de A e B forem incompatíveis.
    */
   public float[][] somarMatrizes(float[][] a, float[][] b){
      return om.somarMatrizes(a, b);
   }


   /**
    * Realiza a soma entre as duas matrizes fornecidas.
    * <p>Exemplo:<pre>
    * a = [
    *   1, 1
    *   1, 1
    * ]
    * b = [
    *   2, 2
    *   2, 2
    * ]
    * soma = [
    *   3, 3
    *   3, 3
    * ]
    * </pre></p>
    * @param a primeira matriz.
    * @param b segunda matriz.
    * @return matriz contendo o resultado da soma.
    * @throws IllegalArgumentException se as dimensões de A e B forem incompatíveis.
    */
   public double[][] somarMatrizes(double[][] a, double[][] b){
      return om.somarMatrizes(a, b);
   }


   /**
    * Realiza a subtração entre as duas matrizes fornecidas.
    * <p>Exemplo:<pre>
    * a = [
    *   1, 1
    *   1, 1
    * ]
    * b = [
    *   2, 2
    *   2, 2
    * ]
    * sub = [
    *   -1, -1
    *   -1, -1
    * ]
    * </pre></p>
    * @param a primeira matriz.
    * @param b segunda matriz.
    * @return matriz contendo o resultado da soma.
    * @throws IllegalArgumentException se as dimensões de A e B forem incompatíveis.
    */
   public int[][] subtrairMatrizes(int[][] a, int[][] b){
      return om.subtrairMatrizes(a, b);
   }


   /**
    * Realiza a subtração entre as duas matrizes fornecidas.
    * <p>Exemplo:<pre>
    * a = [
    *   1, 1
    *   1, 1
    * ]
    * b = [
    *   2, 2
    *   2, 2
    * ]
    * sub = [
    *   -1, -1
    *   -1, -1
    * ]
    * </pre></p>
    * @param a primeira matriz.
    * @param b segunda matriz.
    * @return matriz contendo o resultado da soma.
    * @throws IllegalArgumentException se as dimensões de A e B forem incompatíveis.
    */
   public float[][] subtrairMatrizes(float[][] a, float[][] b){
      return om.subtrairMatrizes(a, b);
   }


   /**
    * Realiza a subtração entre as duas matrizes fornecidas.
    * <p>Exemplo:<pre>
    * a = [
    *   1, 1
    *   1, 1
    * ]
    * b = [
    *   2, 2
    *   2, 2
    * ]
    * sub = [
    *   -1, -1
    *   -1, -1
    * ]
    * </pre></p>
    * @param a primeira matriz.
    * @param b segunda matriz.
    * @return matriz contendo o resultado da soma.
    * @throws IllegalArgumentException se as dimensões de A e B forem incompatíveis.
    */
   public double[][] subtrairMatrizes(double[][] a, double[][] b){
      return om.subtrairMatrizes(a, b);
   }


   /**
    * Realiza a multiplicação da matriz A pela matriz B.
    * <p>Exemplo:<pre>
    * a = [
    *   1, 2
    *   3, 4
    * ]
    * b = [
    *   5, 6
    *   7, 8
    * ]
    * mult = [
    *   19.0, 22.0
    *   43.0, 50.0
    * ]
    * </pre></p>
    * @param a primeira matriz.
    * @param b segunda matriz.
    * @return resultado da multiplicação A x B.
    * @throws IllegalArgumentException se as dimensões de A e B forem incompatíveis.
    */
   public int[][] multiplicarMatrizes(int[][] a, int[][] b){
      if(a.length >= 100) return om.multiplicarMatrizes(a, b);
      else return omt.multiplicarMatrizes(a, b);
   }


   /**
    * Realiza a multiplicação da matriz A pela matriz B.
    * <p>Exemplo:<pre>
    * a = [
    *   1, 2
    *   3, 4
    * ]
    * b = [
    *   5, 6
    *   7, 8
    * ]
    * mult = [
    *   19.0, 22.0
    *   43.0, 50.0
    * ]
    * </pre></p>
    * @param a primeira matriz.
    * @param b segunda matriz.
    * @return resultado da multiplicação A x B.
    * @throws IllegalArgumentException se as dimensões de A e B forem incompatíveis.
    */
   public float[][] multiplicarMatrizes(float[][] a, float[][] b){
      if(a.length >= 100) return om.multiplicarMatrizes(a, b);
      else return omt.multiplicarMatrizes(a, b);
   }


   /**
    * Realiza a multiplicação da matriz A pela matriz B.
    * <p>Exemplo:<pre>
    * a = [
    *   1, 2
    *   3, 4
    * ]
    * b = [
    *   5, 6
    *   7, 8
    * ]
    * mult = [
    *   19.0, 22.0
    *   43.0, 50.0
    * ]
    * </pre></p>
    * @param a primeira matriz.
    * @param b segunda matriz.
    * @return resultado da multiplicação A x B.
    * @throws IllegalArgumentException se as dimensões de A e B forem incompatíveis.
    */
   public double[][] multiplicarMatrizes(double[][] a, double[][] b){
      if(a.length >= 100) return om.multiplicarMatrizes(a, b);
      else return omt.multiplicarMatrizes(a, b);
   }


   /**
    * Multiplica cada elemento da matriz por um valor escalar fornecido.
    * @param matriz matriz contendo os dados.
    * @param escalar escalar para a ultiplicação.
    */
   public void multilpicarEscalar(int[][] matriz, int escalar){
      om.multilpicarEscalar(matriz, escalar);
   }


   /**
    * Multiplica cada elemento da matriz por um valor escalar fornecido.
    * @param matriz matriz contendo os dados.
    * @param escalar escalar para a ultiplicação.
    */
   public void multilpicarEscalar(float[][] matriz, float escalar){
      om.multilpicarEscalar(matriz, escalar);
   }


   /**
    * Multiplica cada elemento da matriz por um valor escalar fornecido.
    * @param matriz matriz contendo os dados.
    * @param escalar escalar para a ultiplicação.
    */
   public void multilpicarEscalar(double[][] matriz, double escalar){
      om.multilpicarEscalar(matriz, escalar);
   }


   /**
    * Multiplica cada elemento da matriz A pelo mesmo elementos correspondente
    * na matriz B.
    * <p>Exemplo:<pre>
    * a = [
    *   1, 2
    *   3, 4
    * ]
    * b = [
    *   1, 2
    *   3, 4
    * ]
    * had = [
    *   1, 4
    *   16, 25
    * ]
    * </pre></p>
    * @param a primeira matriz.
    * @param b segunda matriz.
    * @return matriz resultante do produto Hadamard.
    * @throws IllegalArgumentException se as dimensões de A e B forem incompatíveis.
    */
   public int[][] produtoHadamard(int[][] a, int[][] b){
      return om.hadamard(a, b);
   }


   /**
    * Multiplica cada elemento da matriz A pelo mesmo elementos correspondente
    * na matriz B.
    * <p>Exemplo:<pre>
    * a = [
    *   1, 2
    *   3, 4
    * ]
    * b = [
    *   1, 2
    *   3, 4
    * ]
    * had = [
    *   1, 4
    *   16, 25
    * ]
    * </pre></p>
    * @param a primeira matriz.
    * @param b segunda matriz.
    * @return matriz resultante do produto Hadamard.
    * @throws IllegalArgumentException se as dimensões de A e B forem incompatíveis.
    */
   public float[][] produtoHadamard(float[][] a, float[][] b){
      return om.hadamard(a, b);
   }


   /**
    * Multiplica cada elemento da matriz A pelo mesmo elementos correspondente
    * na matriz B.
    * <p>Exemplo:<pre>
    * a = [
    *   1, 2
    *   3, 4
    * ]
    * b = [
    *   1, 2
    *   3, 4
    * ]
    * had = [
    *   1, 4
    *   16, 25
    * ]
    * </pre></p>
    * @param a primeira matriz.
    * @param b segunda matriz.
    * @return matriz resultante do produto Hadamard.
    * @throws IllegalArgumentException se as dimensões de A e B forem incompatíveis.
    */
   public double[][] produtoHadamard(double[][] a, double[][] b){
      return om.hadamard(a, b);
   }
}
