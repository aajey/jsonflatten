package com.jsonflatten;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlattenJsonIgnoreArrayImplTest {

    private FlattenJsonInterface fltJson = new FlattenJsonIgnoreArrayImpl();

    private FlattenJsonInterface fltCustomJson = new FlattenJsonIgnoreArrayImpl("#");


    private static Stream<Arguments> dotDelimiter() {
        return Stream.of(

                Arguments.of(new JSONObject(), new JSONObject()),
                /*
                    {
                        "name":"john",
                        "phone": null,
                        "age": 22
                    }
                 */
                Arguments.of(new JSONObject("{\"name\":\"john\",\"phone\":null,\"age\":22}"), new JSONObject("{\"name\":\"john\",\"phone\":null,\"age\":22}")),

                /*
                    {
                        "name":"john",
                        "phone": {},
                        "age": 22
                    }
                 */
                Arguments.of(new JSONObject("{\"name\":\"john\",\"phone\":{},\"age\":22}"), new JSONObject("{\"name\":\"john\",\"age\":22}")),
                /*
                  {
                     "a": 1,
                     "b": true,
                     "c": {
                             "d": 3,
                             "e": "test"
                          }
                   }
                 */
                Arguments.of(new JSONObject("{\"a\":1,\"b\":true,\"c\":{\"d\":3,\"e\":\"test\"}}"), new JSONObject("{\"a\":1,\"b\":true,\"c.d\":3,\"c.e\":\"test\"}")),

                /*
                  {
                     "a": {
                         "b": true,
                         "c": {
                                 "d": 3,
                                 "e": "test"
                              }
                          }
                   }
                 */
                Arguments.of(new JSONObject("{\"a\":{\"b\":true,\"c\":{\"d\":3,\"e\":\"test\"}}}"), new JSONObject("{\"a.b\":true,\"a.c.d\":3,\"a.c.e\":\"test\"}"))
        );
    }

    private static Stream<Arguments> poundDelimiter() {
        return Stream.of(

                Arguments.of(new JSONObject(), new JSONObject()),
                /*
                    {
                        "name":"john",
                        "phone": null,
                        "age": 22
                    }
                 */
                Arguments.of(new JSONObject("{\"name\":\"john\",\"phone\":null,\"age\":22}"), new JSONObject("{\"name\":\"john\",\"phone\":null,\"age\":22}")),

                /*
                    {
                        "name":"john",
                        "phone": {},
                        "age": 22
                    }
                 */
                Arguments.of(new JSONObject("{\"name\":\"john\",\"phone\":{},\"age\":22}"), new JSONObject("{\"name\":\"john\",\"age\":22}")),
                /*
                  {
                     "a": 1,
                     "b": true,
                     "c": {
                             "d": 3,
                             "e": "test"
                          }
                   }
                 */
                Arguments.of(new JSONObject("{\"a\":1,\"b\":true,\"c\":{\"d\":3,\"e\":\"test\"}}"), new JSONObject("{\"a\":1,\"b\":true,\"c#d\":3,\"c#e\":\"test\"}")),

                /*
                  {
                     "a": {
                         "b": true,
                         "c": {
                                 "d": 3,
                                 "e": "test"
                              }
                          }
                   }
                 */
                Arguments.of(new JSONObject("{\"a\":{\"b\":true,\"c\":{\"d\":3,\"e\":\"test\"}}}"), new JSONObject("{\"a#b\":true,\"a#c#d\":3,\"a#c#e\":\"test\"}"))
        );
    }

    @ParameterizedTest
    @MethodSource("dotDelimiter")
    public void testDefaultDelimiter(JSONObject input, JSONObject output) {
        assertEquals(fltJson.flatten(input).toString(), output.toString());
    }

    @ParameterizedTest
    @MethodSource("poundDelimiter")
    public void testCustomDelimiter(JSONObject input, JSONObject output) {
        assertEquals(fltCustomJson.flatten(input).toString(), output.toString());
    }

    @Test
    public void testNullJSON()
    {
        assertEquals(fltJson.flatten(null), null);
        assertEquals(fltCustomJson.flatten(null), null);

    }


}
