package rna.otimizadores;

import rna.estrutura.Camada;
import rna.estrutura.Neuronio;

/**
 * Implementa o treino da rede neural usando o algoritmo RMSProp (Root Mean Square Propagation).
 *
 * Ele é uma adaptação do Gradiente Descendente Estocástico (SGD) que ajuda a lidar com a
 * oscilação do gradiente, permitindo que a taxa de aprendizado seja adaptada para cada parâmetro 
 * individualmente.
 * <p>
 * 	Os hiperparâmetros do RMSProp podem ser ajustados para controlar 
 *    o comportamento do otimizador durante o treinamento.
 * </p
 */
public class RMSProp extends Otimizador{

   /**
    * Usado para evitar divisão por zero.
    */
   private double epsilon = 1e-8;
  
   /**
    * fator de decaimento do RMSprop.
    */
   double beta = 0.9;

   /**
    * Inicializa uma nova instância de otimizador RMSProp usando os valores 
    * de hiperparâmetros fornecidos.
    * @param epsilon usado para evitar a divisão por zero.
    * @param beta fator de decaimento do RMSProp.
    */
   public RMSProp(double epsilon, double beta){
      this.epsilon = epsilon;
      this.beta = beta;
   }

   /**
    * Inicializa uma nova instância de otimizador RMSProp.
    * <p>
    *    Os hiperparâmetros do RMSProp serão inicializados com os valores padrão, que são:
    * </p>
    * {@code epsilon = 1e-8}
    * <p>
    *    {@code beta = 0.9}
    * </p>
    */
   public RMSProp(){
      this(1e-8, 0.9);
   }

   @Override
    public void atualizar(Camada[] redec, double taxaAprendizagem, double momentum){
      Neuronio neuronio;

      //percorrer rede, com exceção da camada de entrada
      for(int i = 1; i < redec.length; i++){
         
         Camada camada = redec[i];
         int nNeuronios = camada.quantidadeNeuroniosSemBias();
         for(int j = 0; j < nNeuronios; j++){//percorrer neurônios da camada atual

            neuronio = camada.neuronio(j);
            for(int k = 0; k < neuronio.pesos.length; k++){//percorrer pesos do neurônio atual
               neuronio.acumuladorGradiente[k] = (beta * neuronio.acumuladorGradiente[k]) + ((1 - beta) * neuronio.gradiente[k] * neuronio.gradiente[k]);
               neuronio.pesos[k] -= (taxaAprendizagem / Math.sqrt(neuronio.acumuladorGradiente[k] + epsilon)) * neuronio.gradiente[k];
            }
         }
      }
   }

}
