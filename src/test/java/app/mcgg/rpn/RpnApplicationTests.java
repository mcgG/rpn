package app.mcgg.rpn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RpnApplicationTests {

    @MockBean
    private Calculator calculator;

    @Test
    public void contextLoads() {
    }





}
