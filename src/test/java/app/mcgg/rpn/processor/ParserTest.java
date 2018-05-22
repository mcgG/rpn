package app.mcgg.rpn.processor;

import app.mcgg.rpn.util.Parser;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class ParserTest {

    private Parser parser;

    @Before
    public void setup() {
        parser = new Parser();
    }

    @Test
    public void testParseDoubleValid() {
        assert(parser.parseDouble("1.3").equals(new BigDecimal("1.3")));
    }

    @Test
    public void testParseDoubleInvalid() {
        assert (parser.parseDouble("+") == null);
    }
}
