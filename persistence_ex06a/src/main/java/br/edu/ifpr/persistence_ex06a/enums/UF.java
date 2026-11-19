package br.edu.ifpr.persistence_ex06a.enums;

public enum UF {
   
   ACRE("AC", "Acre") {
      @Override
      public String toString() {
         return String.format("%s (%s)",nome(),sigla());
      }
    },
   
   ALAGOAS("AL", "Alagoas") {
       @Override
       public String toString() {
          return String.format("s (%s)", nome(), sigla());
       }
    },
   
   AMAPA("AP", "Amapa") {
       @Override
       public String toString() {
          return String.format("s (%s)", nome(), sigla());
       }
    },
   
   AMAZONAS("AM", "Amazonas") {
       @Override
       public String toString() {
          return String.format("s (%s)", nome(), sigla());
       }
    },
   
   BAHIA("BA", "Bahia") {
       @Override
       public String toString() {
          return String.format("s (%s)", nome(), sigla());
       }
    },
   
   CEARA("CE", "Ceara") {
       @Override
       public String toString() {
          return String.format("s (%s)", nome(), sigla());
       }
    },
   
   DISTRITO_FEDERAL("DF", "Distrito Federal") {
       @Override
       public String toString() {
          return String.format("s (%s)", nome(), sigla());
       }
    },
   
   ESPIRITO_SANTO("ES", "Espirito Santo") {
       @Override
       public String toString() {
          return String.format("s (%s)", nome(), sigla());
       }
    },
   
   GOIAS("GO", "GOIAS"){
       @Override
       public String toString() {
          return String.format("s (%s)", nome(), sigla());
       }
    },
   
   MARANHAO("MA", "Maranhao") {
       @Override
       public String toString() {
          return String.format("s (%s)", nome(), sigla());
       }
    },
   
   MATO_GROSSO("MT", "Mato Grosso") {
       @Override
       public String toString() {
          return String.format("s (%s)", nome(), sigla());
       }
    },
   
   MATO_GROSSO_SUL("MS", "Mato Grosso do Sul") {
       @Override
       public String toString() {
          return String.format("s (%s)", nome(), sigla());
       }
    },
   
   MINAS_GERAIS("MG", "Minas Gerais") {
       @Override
       public String toString() {
          return String.format("s (%s)", nome(), sigla());
       }
    },
   
   PARA("PA", "Para") {
       @Override
       public String toString() {
          return String.format("s (%s)", nome(), sigla());
       }
    },
   
   PARAIBA("PB", "Paraiba") {
       @Override
       public String toString() {
          return String.format("s (%s)", nome(), sigla());
       }
    },
   
   PARANA("PR", "Parana") {
       @Override
       public String toString() {
          return String.format("s (%s)", nome(), sigla());
       }
    },
   
   PERNAMBUCO("PE", "Pernambuco") {
       @Override
       public String toString() {
          return String.format("s (%s)", nome(), sigla());
       }
    },
   
   PIAUI("PI", "Piaui") {
       @Override
       public String toString() {
          return String.format("s (%s)", nome(), sigla());
       }
    },
   
   RIO_JANEIRO("RJ", "Rio de Janeiro") {
         @Override
         public String toString() {
            return String.format("s (%s)", nome(), sigla());
         }
      },

    RIO_GRANDE_NORTE("RN", "Rio Grande do Norte") {
         @Override
         public String toString() {
            return String.format("s (%s)", nome(), sigla());
         }
      },
    
    RIO_GRANDE_SUL("RS", "Rio Grande do Sul") {
         @Override
         public String toString() {
            return String.format("s (%s)", nome(), sigla());
         }
      },
    
    RONDONIA("RO", "Rondonia") {
         @Override
         public String toString() {
            return String.format("s (%s)", nome(), sigla());
         }
      },
     
    RORAIMA("RR", "Roraima") {
         @Override
         public String toString() {
            return String.format("s (%s)", nome(), sigla());
         }
      },
    
    SANTA_CATARINA("SC", "Santa Catarina") {
         @Override
         public String toString() {
            return String.format("s (%s)", nome(), sigla());
         }
      },
    
    SAO_PAULO("SP", "São Paulo") {
         @Override
         public String toString() {
            return String.format("s (%s)", nome(), sigla());
         }
      },
    
    SERGIPE("SE", "Sergipe") {
         @Override
         public String toString() {
            return String.format("s (%s)", nome(), sigla());
         }
      },
    
    TOCANTINS("TO", "Tocantis") {
         @Override
         public String toString() {
            return String.format("s (%s)", nome(), sigla());
         }
      };
  
      
   private String sigla;
   private String nome;
   
   private UF(String sigla, String nome) {
   this.sigla = sigla;
   this.nome = nome;
   }
   
   public String sigla() { return sigla; }
   public String nome() { return nome; }
   
   public static UF fromIndex(int pos) {
   UF[] ufs = UF.values(); //Gera um array contendo todas as constantes declaradas no Enum na ordem em que foram definidas.
   
   if (pos < 0 || pos > ufs.length) // Valida se a posição informada está dentro dos limites válidos.
   throw new IllegalArgumentException("Posição " + "inválida: " + pos); //Lança uma exceção se a posição for inválida (fora do intervalo).
      
   return ufs[pos]; // Retorna a constante do Enum correspondente à posição informada.
   }
   
}
