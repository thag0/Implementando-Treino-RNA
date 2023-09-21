package exemplos;

import rna.estrutura.RedeNeural;
import rna.serializacao.Serializador;

public class Teste{
   public static void main(String[] args){
      limparConsole();

      double[][] e = {
         {0, 0},
         {0, 1},
         {1, 0},
         {1, 1}
      };

      double[][] s = {
         {0},
         {1},
         {1},
         {0}
      };

      RedeNeural rede = Serializador.ler("./rede-xor.txt");
      System.out.println(rede.info());

      var perda = rede.avaliador.erroMedioQuadrado(e, s);
      System.out.println("Perda = " + perda);

      System.out.println(rede.obterCamadaOculta(0).neuronio(0).info());
      System.out.println(rede.obterCamadaOculta(0).neuronio(1).info());
      System.out.println(rede.obterCamadaSaida().neuronio(0).info());
   }

   static void limparConsole(){
      try{
         String nomeSistema = System.getProperty("os.name");

         if(nomeSistema.contains("Windows")){
         new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            return;
         }else{
            for (int i = 0; i < 100; i++){
               System.out.println();
            }
         }
      }catch(Exception e){
         return;
      }
   }
}
