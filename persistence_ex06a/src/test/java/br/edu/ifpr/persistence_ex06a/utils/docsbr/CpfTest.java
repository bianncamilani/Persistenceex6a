package br.edu.ifpr.persistence_ex06a.utils.docsbr;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import br.edu.ifpr.persistence_ex06a.enums.UF;
import br.edu.ifpr.persistence_ex06a.utils.docsbr.CpfUtils;
import br.edu.ifpr.persistence_ex06a.utils.docsbr.CpfStatus;

public class CpfTest {

   @ParameterizedTest
   @ValueSource(strings = {"52295231078"})
   final void testValidCpf(String cpf) {
   
       assertEquals(CpfStatus.OK, CpfUtils.validate(cpf));
   }
   
   @ParameterizedTest
   @ValueSource(strings = {"94749899080"}) 
   final void testInvalidCpfDv1(String cpf) {
   
      assertEquals(CpfStatus.CPF_DV1, CpfUtils.validate(cpf));
   }
   
   @ParameterizedTest
   @ValueSource(strings = {"94749899071"}) 
   final void testInvalidCpfDv2(String cpf) {
   
      assertEquals(CpfStatus.CPF_DV2, CpfUtils.validate(cpf));
   }
   
   @ParameterizedTest
   @ValueSource(strings = {"9474989907"})
   final void testInvalidCpfLen(String cpf) {
   
       assertEquals(CpfStatus.CPF_LEN, CpfUtils.validate(cpf));
   }
   
   @ParameterizedTest
   @NullSource
   final void testInvalidCpfInvNull(String cpf) {
   
       assertEquals(CpfStatus.CPF_INV, CpfUtils.validate(cpf));
   }
   
   @ParameterizedTest
   @EmptySource
   final void testInvalidCpfInvEmpty(String cpf) {
   
       assertEquals(CpfStatus.CPF_INV, CpfUtils.validate(cpf));
   }
   
   @ParameterizedTest
   @ValueSource(strings = {
           "11111111111",
           "22222222222",
           "99999999999"
   })
   final void testInvalidCpfInvAllDigitsEqual(String cpf) {
   
       assertEquals(CpfStatus.CPF_INV, CpfUtils.validate(cpf));
   }
   
   @Test
   final void testGeneratedCpf1() {
   
       String cpf = CpfUtils.generate();
   
       assertEquals(CpfStatus.OK, CpfUtils.validate(cpf));
   }
   
   @ParameterizedTest
   @EnumSource(
       value = UF.class,
       names = {"PARANA", "SAO_PAULO"}
   )
   final void testGeneratedCpf(UF uf) {
   
       String cpf = CpfUtils.generate(uf);
   
       assertEquals(CpfStatus.OK, CpfUtils.validate(cpf));
       assertThat(CpfUtils.ufs(cpf), hasItem(uf));
   }
   
   @ParameterizedTest
   @ValueSource(strings = {"12345678062"})
   final void testEstadoCpf_0(String cpf) {
   
       assertThat(CpfUtils.ufs(cpf), contains(UF.RIO_GRANDE_SUL));
   }
   
   @ParameterizedTest
   @ValueSource(strings = {"75882606101"})
   final void testEstadoCpf_1(String cpf) {
   
       assertThat(CpfUtils.ufs(cpf), contains(
               UF.DISTRITO_FEDERAL,
               UF.GOIAS,
               UF.MATO_GROSSO,
               UF.MATO_GROSSO_SUL,
               UF.TOCANTINS
       ));
   }
   
   @ParameterizedTest
   @ValueSource(strings = {"99100736252"})
   final void testEstadoCpf_2(String cpf) {
   
       assertThat(CpfUtils.ufs(cpf), contains(
               UF.ACRE,
               UF.AMAPA,
               UF.AMAZONAS,
               UF.PARA,
               UF.RONDONIA,
               UF.RORAIMA
       ));
   }
   
   @ParameterizedTest
   @ValueSource(strings = {"41297382323"})
   final void testEstadoCpf_3(String cpf) {
   
       assertThat(CpfUtils.ufs(cpf), contains(
               UF.CEARA,
               UF.MARANHAO,
               UF.PIAUI
       ));
   }
   
   @ParameterizedTest
   @ValueSource(strings = {"35712300435"})
   final void testEstadoCpf_4(String cpf) {
   
       assertThat(CpfUtils.ufs(cpf), contains(
               UF.ALAGOAS,
               UF.PARAIBA,
               UF.PERNAMBUCO,
               UF.RIO_GRANDE_NORTE
       ));
   }
   
   @ParameterizedTest
   @ValueSource(strings = {"26285938580"})
   final void testEstadoCpf_5(String cpf) {
   
       assertThat(CpfUtils.ufs(cpf), contains(
               UF.BAHIA,
               UF.SERGIPE
       ));
   }
   
   @ParameterizedTest
   @ValueSource(strings = {"44517967689"})
   final void testEstadoCpf_6(String cpf) {
   
       assertEquals(
               java.util.List.of(UF.MINAS_GERAIS),
               CpfUtils.ufs(cpf)
       );
   }
   
   @ParameterizedTest
   @ValueSource(strings = {"50400252708"})
   final void testEstadoCpf_7(String cpf) {
   
       assertThat(CpfUtils.ufs(cpf), contains(
               UF.ESPIRITO_SANTO,
               UF.RIO_JANEIRO
       ));
   }
   
   @ParameterizedTest
   @ValueSource(strings = {"88244122897"})
   final void testEstadoCpf_8(String cpf) {
   
       assertEquals(
               java.util.List.of(UF.SAO_PAULO),
               CpfUtils.ufs(cpf)
       );
   }
   
   @ParameterizedTest
   @ValueSource(strings = {"02482035929"})
   final void testEstadoCpf_9(String cpf) {
   
       assertThat(CpfUtils.ufs(cpf), contains(
               UF.PARANA,
               UF.SANTA_CATARINA
       ));
   }

}
