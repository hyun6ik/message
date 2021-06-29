package hello.itemservice.domain.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    public void helloMessage() throws Exception {
        //given
        String result = ms.getMessage("hello", null, null);
        //then
        assertThat(result).isEqualTo("안녕");

     }

     @Test
     public void notFoundMessageCode() throws Exception {
        //then
         assertThatThrownBy(() -> ms.getMessage("no_code", null, null))
                 .isInstanceOf(NoSuchMessageException.class);
      }
      
      @Test
      public void NotFoundDefaultMessage() throws Exception {
          //given
          String result = ms.getMessage("no_code", null, "기본 메시지", null);
          //then
          assertThat(result).isEqualTo("기본 메시지");
       }

       @Test
       public void argumentMessage() throws Exception {
           //given
           String message = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
           //then
           assertThat(message).isEqualTo("안녕 Spring");
        }

        @Test
        public void defaultLang() throws Exception {
            //then
            assertThat(ms.getMessage("hello", null, null)).isEqualTo("안녕");
            assertThat(ms.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
         }

         @Test
         public void enLang() throws Exception {
             assertThat(ms.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
          }


}
