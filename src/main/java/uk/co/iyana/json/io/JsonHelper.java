/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.json.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uk.co.iyana.json.exception.JsonException;

/**
 *
 * @author fgyara
 */
public class JsonHelper {
    
    private final String json;
    
    public static JsonHelper read(String resourceName) throws JsonException {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            byte[] encodedBytes = Files.readAllBytes(Paths.get(loader.getResource(resourceName).toURI()));
        
            String json = new String(encodedBytes);
            JsonHelper toRet = new JsonHelper(json);
            return toRet;
        } catch (IOException | URISyntaxException ex) {
            throw new JsonException("Could not read in resource: " + resourceName, ex);
        }        
    }
    
    public static JsonHelper readFile(String fileName) throws JsonException {
        try {
            // read the file into a string
            byte[] encodedBytes = Files.readAllBytes(Paths.get(fileName));
            String json = new String(encodedBytes);

            JsonHelper toRet = new JsonHelper(json);
            return toRet;
        } catch(IOException ie) {
            throw new JsonException("Could not read file", ie);
        }
    }
    
    public static JsonHelper readString(String json) throws JsonException {
        JsonHelper toRet = new JsonHelper(json);
        return toRet;
    }
    
    public static JsonHelper write(Object src) throws JsonException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return new JsonHelper(gson.toJson(src));
    }
    
    private JsonHelper(String json) {
        this.json = json;
    }
    
    public <T> T to(Type t) {
        Gson gson = new Gson();
        return gson.fromJson(json, t);
    }
    
    public <T> T to(Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }
    
    public void to(PrintStream ps) {
        ps.println(this.json);
    }
    
    @Override
    public String toString() {
        return this.json;
    }
    

}
