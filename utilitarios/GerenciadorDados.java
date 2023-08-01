package utilitarios;

import java.util.ArrayList;

/**
 * Responsável pelo manuseio do conjunto de dados, 
 * funcionalidades disponíveis:
 * <ul>
 *    <li>Funções de leitura de arquivos.</li>
 *    <li>Funções de conversão de dados.</li>
 *    <li>Funções de gerenciamento de treino e teste para rede neural..</li>
 * </ul>
*/
public class GerenciadorDados{

   LeitorArquivos la;//leitor de arquivos
   ConversorDados cd;//conversor de dados
   TreinoTeste gtt;//gerenciador de treino e teste da rede
   
   public GerenciadorDados(){
      la = new LeitorArquivos();
      cd = new ConversorDados();
      gtt = new TreinoTeste();
   }


   /**
    * Exibe pelo console as informações contidas na lista.
    * @param lista lista com os dados.
    */
   public void imprimirCsv(ArrayList<String[]> lista){
      String separador = ",";
      for(String linha[] : lista){
         for(int i = 0; i < linha.length; i++){
            if(i == 0) System.out.print(linha[i]);
            else System.out.print(separador + " " + linha[i]);
         }
         System.out.println();
      }
   }


   /**
    * Remove uma linha inteira do conjunto de dados
    * @param lista lista com os dados.
    * @param indice índice da coluna que será removida.
    * @throws IllegalArgumentException se o indice lista for nula.
    * @throws IllegalArgumentException se o indice estiver fora de alcance da lista.
    */
   public void removerLinhaDados(ArrayList<String[]> lista, int indice){
      if(lista == null) throw new IllegalArgumentException("A lista de dados é nula.");
      if((indice < 0) || (indice > lista.get(0).length-1)){
         throw new IllegalArgumentException("Índice fornecido para remoção é inválido");
      }

      lista.remove(indice);
   }


   /**
    * Remove todas as colunas dos dados de acordo com o índice fornecido.
    * @param lista lista com os dados.
    * @param indice índice da coluna que será removida.
    * @return nova lista com a coluna removida.
    * @throws IllegalArgumentException se a lista for nula.
    * @throws IllegalArgumentException se o indice estiver fora de alcance da lista.
    */
   public ArrayList<String[]> removerColunaDados(ArrayList<String[]> lista, int indice){
      if(lista == null) throw new IllegalArgumentException("A lista de dados é nula.");
      if((indice < 0) || (indice > lista.get(0).length-1)){
         throw new IllegalArgumentException("Índice fornecido para remoção é inválido");
      }

      ArrayList<String[]> novaLista = new ArrayList<String[]>();

      for(int i = 0; i < lista.size(); i++){
         String[] linha = lista.get(i);
         String[] novaLinha = new String[linha.length - 1];

         int contador1 = 0;
         int contador2 = 0;

         while(contador2 < linha.length){
            if(contador2 == indice){
                  contador2++;
            }else{
               novaLinha[contador1] = linha[contador2];
               contador1++;
               contador2++;
            }
         }

         novaLista.add(novaLinha);
      }
      
      return novaLista;
   }


   /**
    * Substitui todas as linhas dos dados pelo valor fornecido, caso na coluna fornecida tenha o valor buscado.
    * @param lista lista com os dados lidos.
    * @param indice índice da coluna alvo para a alteração dos dados.
    * @param valorBusca valor que será procurado para ser substituído.
    * @param novoValor novo valor que será colocado.
    * @throws IllegalArgumentException se a lista estiver nula.
    * @throws IllegalArgumentException se o valor do índice fornecido estiver fora de alcance.
    * @throws IllegalArgumentException se o valor de busca for nulo.
    * @throws IllegalArgumentException se o novo valor de substituição for nulo;
    */
   public void editarValorDados(ArrayList<String[]> lista, int indice, String valorBusca, String novoValor){
      if(lista == null) throw new IllegalArgumentException("A lista de dados é nula.");
      if(indice < 0 || (indice > lista.get(0).length-1)){
         throw new IllegalArgumentException("Valor do índice está fora de alcance dos índices da lista.");
      }
      if(valorBusca == null) throw new IllegalArgumentException("O valor de busca é nulo");
      if(novoValor == null) throw new IllegalArgumentException("O novo valor para substituição é nulo");

      for(String[] linha : lista){
         if(linha[indice].contains(valorBusca)){
            linha[indice] = novoValor;
         }
      }
   }


   /**
    * Remove a linha inteira dos dados caso exista algum valor nas colunas que não consiga ser convertido para
    * um valor numérico.
    * <p>
    *    É importante verificar e ter certeza se os dados não possuem nenhuma coluna com caracteres, caso isso seja verdade
    *    o método irá remover todas as colunas como consequência e a lista ficará vazia.
    * </p>
    * @param lista lista com os dados;
    */
   public void removerNaoNumericos(ArrayList<String[]> lista){
      int indiceInicial = 0;
      boolean removerLinha = false;
   
      while(indiceInicial < lista.size()){
         removerLinha = false;

         for(int j = 0; j < lista.get(indiceInicial).length; j++){
            //verificar se existe algum valor que não possa ser convertido para número.
            if((valorInt(lista.get(indiceInicial)[j]) == false) || 
               (valorFloat(lista.get(indiceInicial)[j]) == false) || 
               (valorDouble(lista.get(indiceInicial)[j]) == false)
            ){
               removerLinha = true;
               break;
            }
         }

         if(removerLinha) lista.remove(indiceInicial);
         else indiceInicial++; 
      }
   }


   /**
    * Tenta converter o valor para um numérico do tipo int
    * @param valor valor que será testado.
    * @return resultado da verificação, verdadeiro se foi convertido ou false se não
    */
   private boolean valorInt(String valor){
      try{
         Integer.parseInt(valor);
         return true;
      
      }catch(Exception e){
         return false;
      }
   }


   /**
    * Tenta converter o valor para um numérico do tipo float.
    * @param valor valor que será testado.
    * @return resultado da verificação, verdadeiro se foi convertido ou false se não
    */
   private boolean valorFloat(String valor){
      try{
         Float.parseFloat(valor);
         return true;
      
      }catch(Exception e){
         return false;
      }
   }


   /**
    * Tenta converter o valor para um numérico do tipo double
    * @param valor valor que será testado.
    * @return resultado da verificação, verdadeiro se foi convertido ou false se não
    */
   private boolean valorDouble(String valor){
      try{
         Double.parseDouble(valor);
         return true;
      
      }catch(Exception e){
         return false;
      }
   }


   /**
    * Descreve as dimensões da lista, tanto em questão de quantidade de linhas qunanto quantidade de colunas.
    * @param lista lista com os dados.
    * @return array contendo as informações das dimensões da lista, o primeiro elemento corresponde a quantidade de 
    * linhas e o segundo elemento corresponde a quantidade de colunas.
    * @throws IllegalArgumentException se a lista estiver nula.
    * @throws IllegalArgumentException se a lista estiver vazia.
    */
   public int[] obterShapeLista(ArrayList<String[]> lista){
      if(lista == null) throw new IllegalArgumentException("A lista fornecida é nula.");
      if(lista.size() == 0) throw new IllegalArgumentException("A lista fornecida está vazia.");

      int[] shape = new int[2];
      shape[0] = lista.size();
      shape[1] = lista.get(0).length;
      return shape;
   }


   //LEITOR DE ARQUIVOS ---------------------

   /**
    * Lê o arquivo .csv de acordo com o caminho especificado.
    *
    * <p>
    *    O formato da estrutura de dados será um objeto do tipo 
    *    {@code ArrayList<String[]>}, contendo as linhas e colunas das informações lidas.
    * </p>
    * @param caminho caminho relativo do arquivo.
    * @return objeto contendo as informações do arquivo lido.
    * @throws IllegalArgumentException caso não encontre o diretório fornecido.
    */
   public ArrayList<String[]> lerCsv(String caminho){
      return la.lerCsv(caminho);
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
   public void embaralharDados(double[][] dados){
      gtt.embaralharDados(dados);
   }


   /**
    * <p>
    *    Método para treino da rede neural.
    * </p>
    *
    * Separa os dados que serão usados como entrada de acordo com os valores fornecidos.
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
    *
    * Extrai os dados de saída do conjunto de dados e devolve um novo conjunto de dados contendo apenas as 
    * colunas de dados de saída especificadas.
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
    * double[][][] treinoTeste = separarTreinoTeste(dados, 0.25f);
    * double[][] treino = treinoTeste[0];
    * double[][] teste = treinoTeste[1];}
    * </pre>
    * @param dados O conjunto de dados completo.
    * @param tamanhoTeste O tamanho relativo do conjunto de teste (entre 0 e 1).
    * @return Um array de duas matrizes contendo os dados de treino e teste, respectivamente.
    * @throws IllegalArgumentException caso o conjunto de dados for nulo ou o tamanho de teste estiver fora do intervalo (0, 1).
    */
   public double[][][] separarTreinoTeste(double[][] dados, float tamanhoTeste){
      return gtt.separarTreinoTeste(dados, tamanhoTeste);
   }


   //CONVERSOR DE DADOS ----------------------

   /**
    * Converte a lista no formato {@code ArrayList<String[]>} para uma matriz bidimensional 
    * com os valores numéricos.
    * @param lista lista com os dados.
    * @return matriz convertida para valores tipo integer.
    */
   public int[][] listaParaDadosInt(ArrayList<String[]> lista){
      return cd.listaParaDadosInt(lista);
   }


   /**
    * Converte a lista no formato {@code ArrayList<String[]>} para uma matriz bidimensional 
    * com os valores numéricos.
    * @param lista lista com os dados 
    * @return matriz convertida para valores tipo float.
    */
   public float[][] listaParaDadosFloat(ArrayList<String[]> lista){
      return cd.listaParaDadosFloat(lista);
   }


   /**
    * Converte a lista no formato {@code ArrayList<String[]>} para uma matriz bidimensional 
    * com os valores numéricos.
    * @param lista lista com os dados 
    * @return matriz convertida para valores tipo double.
    */
   public double[][] listaParaDadosDouble(ArrayList<String[]> lista){
      return cd.listaParaDadosDouble(lista);
   }
}
