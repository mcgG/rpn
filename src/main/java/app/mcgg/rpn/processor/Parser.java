package app.mcgg.rpn.processor;

import app.mcgg.rpn.operator.*;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    Map<String, Calculator> map = new HashMap<>();

    Parser() {
        map.put("+", new Add());
        map.put("-", new Subtract());
        map.put("*", new Multiply());
        map.put("/", new Divide());
        map.put("sqrt", new Sqrt());
        map.put("undo", new Undo());
        map.put("clear", new Clear());
    }
}
