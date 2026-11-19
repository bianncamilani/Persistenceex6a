package br.edu.ifpr.persistence_ex06a.utils.docsbr; 

import static org.junit.jupiter.api.Assertions.assertEquals; 
import org.junit.jupiter.api.Test; 
import org.junit.jupiter.params.ParameterizedTest; 
import org.junit.jupiter.params.provider.ValueSource; 

public class CnpjTest { 
   
   @ParameterizedTest 
   @ValueSource(strings= { "48389806000140", "86948549000128"}) 
   public void testValidCnpjParameterized(String cnpj) { 
      CnpjStatus status = CnpjUtils.validate(cnpj); 
      assertEquals(CnpjStatus.OK,status); } 
    
   //Valida a detecção de erro no 1º Dígito Verificador (13ª posição).
   @ParameterizedTest
   @ValueSource(strings = { "48389806000100", "86948549000108" })
   public void testInvalidFirstDigit(String cnpj) {
      CnpjStatus status = CnpjUtils.validate(cnpj);
      assertEquals(CnpjStatus.CNPJ_DV1, status); }
   
   //Valida o controle de comprimento do documento.
   @ParameterizedTest
   @ValueSource(strings = { "12345", "483898060001", "48389806000140999" })
   public void testInvalidCnpjLength(String cnpj) {
      CnpjStatus status = CnpjUtils.validate(cnpj);
      assertEquals(CnpjStatus.CNPJ_LEN, status);
   }
   
   //Valida a detecção de erro no 2º Dígito Verificador (14ª posição).
   @ParameterizedTest
   @ValueSource(strings = { "48389806000149", "86948549000129" })
   public void testInvalidSecondDigit(String cnpj) {
      CnpjStatus status = CnpjUtils.validate(cnpj);
      assertEquals(CnpjStatus.CNPJ_DV2, status); }
   
   @Test final void testGeneratedCnpj() { 
      String cnpj = CnpjUtils.gerarCNPJ(); 
      assertEquals(CnpjStatus.OK, CnpjUtils.validate(cnpj)); }
}