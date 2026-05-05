package org.acme.reservation;

import org.junit.jupiter.api.Tag;

import io.quarkus.test.junit.QuarkusIntegrationTest;

@QuarkusIntegrationTest
/**
 * Usar @Tag("native") não é nenhuma necessidade técnica para
 * conseguir executar o teste de forma nativa. Isto aqui eu declarei
 * apenas para categorizar o teste de uma forma diferente, para que
 * valendo-se dessa configuração diferente aplicada nos testes de
 * integração, evitar que o eclipse tente executar o teste nativo,
 * uma vez que o eclipse não executa o processo completo como o gradle
 * faz, pois ele não gera previamente a imagem nativa necessária para
 * executar o teste de integração. Desta forma, os testes de integração
 * que executam através do eclipse falham, então esta categorização
 * serve para filtrar e excluir da execução através do eclipse, estes
 * testes para que não falhem pois o set-up não funciona quando a
 * execução ocorre internamente na IDE.
 */
@Tag("native")
class ReservationResourceIT extends ReservationResourceTest {

}