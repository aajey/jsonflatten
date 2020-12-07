package com.jsonflatten;

import java.util.Iterator;
import org.json.JSONObject;

public class FlattenJsonIgnoreArrayImpl implements FlattenJsonInterface {

    private String delimiter;

    public FlattenJsonIgnoreArrayImpl()
    {
        this.delimiter = ".";
    }

    public FlattenJsonIgnoreArrayImpl(String delimiter)
    {
        this.delimiter = delimiter;
    }

    public JSONObject flatten(JSONObject input)
    {
        if(input == null)
        {
            return null;
        }
        return flatten(input, "", new JSONObject());
    }

    private JSONObject flatten(JSONObject input, String prefix, JSONObject flat)
    {
        Iterator<String> jsonKeys = input.keys();
        String key;
        Object keyObject;
        while(jsonKeys.hasNext())
        {
            key = jsonKeys.next();
            keyObject = input.get(key);
            if(keyObject instanceof JSONObject)
            {
                flat = flatten((JSONObject)keyObject, prefix + key + delimiter, flat);
            }
            else
            {
                flat.put(prefix + key, keyObject);
            }
        }
        return flat;
    }
}
