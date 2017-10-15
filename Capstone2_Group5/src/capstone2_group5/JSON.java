/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone2_group5;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.*;

/**
 *
 * @author Cameron
 */
public abstract class JSON extends JSONObject {
    
    private static Object instantiateObject(Class<?> classType) throws Exception{
        Object object = null;
        System.out.println("instantiating object of class: " + classType);
        switch(classType.getTypeName()){
            case "boolean":
                return false;
            case "int":
            case "long":
            case "short":
            case "byte":
            case "double":
            case "float":
            case "char":
                return 0;
            case "java.lang.String":
                return new String();
        }
        Constructor<?>[] constructors = classType.getDeclaredConstructors();
        if(constructors.length > 0){
            Constructor<?> constructor = constructors[0];
            constructor.setAccessible(true);
            int numArgs = constructor.getParameterCount();
            Class<?>[] paramTypes = constructor.getParameterTypes();
            Object[] params = new Object[numArgs];
            for(int i = 0; i < numArgs; i++){
                params[i] = instantiateObject(paramTypes[i]);
            }
            try{
                object = constructor.newInstance(params);
            }catch(Exception e){
                System.out.println("Error constructing class: " + classType.getTypeName());
            }
        }
        return object;
    }
    
    public static Object toJavaObject(JSONObject json)throws Exception{
        Object toReturn = null;
//        System.out.println("converting object: " + json);
        if(json == null){
            return null;
        }
        Object fields = json.get("fields");
        Object value = json.get("value");
        String name = (String)json.get("name");
        String className = (String)json.get("class");
        
        if(fields != null && className != null){
//            System.out.println("Found a class with fields");
            Class<?> classType = Class.forName(className);
            toReturn = instantiateObject(classType);
            JSONArray fieldsArray = (JSONArray)fields;
            for(Object fieldArrayElement : fieldsArray){
                JSONObject fieldObj = (JSONObject)fieldArrayElement;
                String fieldName = (String)fieldObj.get("name");
                Field field = classType.getDeclaredField(fieldName);
                field.setAccessible(true);
                try{
                    field.set(toReturn, toJavaObject(fieldObj));
                }catch(Exception e){
                    System.out.println("Error setting field: " + fieldName + " in object: " + toReturn + " to value: " + fieldObj + "\n    " + e);
                }
            }
        }
        if(name != null && className != null && value != null){
            switch(className){
                case "int":
                case "byte":
                case "short":
                case "long":
                case "char":
                case "boolean":
                case "float":
                case "double":
                case "void":
                case "Boolean":
                case "java.lang.Boolean":
                    return value;
                case "int[]":
                    JSONArray intArray = (JSONArray)value;
                    return buildPrimitiveArray(Array.newInstance(int.class, intArray.size()), intArray);
                case "byte[]":
                    JSONArray byteArray = (JSONArray)value;
                    return buildPrimitiveArray(Array.newInstance(byte.class, byteArray.size()), byteArray);
                case "short[]":
                    JSONArray shortArray = (JSONArray)value;
                    return buildPrimitiveArray(Array.newInstance(short.class, shortArray.size()), shortArray);                    
                case "long[]":
                    JSONArray longArray = (JSONArray)value;
                    return buildPrimitiveArray(Array.newInstance(long.class, longArray.size()), longArray);
                case "char[]":
                    JSONArray charArray = (JSONArray)value;
                    return buildPrimitiveArray(Array.newInstance(char.class, charArray.size()), charArray);
                case "boolean[]":
                    JSONArray boolArray = (JSONArray)value;
                    return buildPrimitiveArray(Array.newInstance(boolean.class, boolArray.size()), boolArray);
                case "float[]":
                    JSONArray floatArray = (JSONArray)value;
                    return buildPrimitiveArray(Array.newInstance(float.class, floatArray.size()), floatArray);
                case "double[]":
                    JSONArray doubleArray = (JSONArray)value;
                    return buildPrimitiveArray(Array.newInstance(double.class, doubleArray.size()), doubleArray);
                case "ArrayList":
                case "java.util.ArrayList":
                    ArrayList<Object> list = new ArrayList<>();
                    JSONArray arrayListValues = (JSONArray)value;
                    for(Object object : arrayListValues){
                        if(JSONObject.class.isAssignableFrom(object.getClass())){
                            list.add(JSON.toJavaObject((JSONObject)object));
                        } else {
                            list.add(JSONValue.parse(object.toString()));
                        }
                    }
                    toReturn = list;
                    break;
                case "HashMap":
                case "java.util.HashMap":
                    HashMap<Object, Object> map = new HashMap<>();
                    JSONObject jsonMap = (JSONObject)value;
                    for(Object mapEntry : jsonMap.entrySet()){
                        Entry<Object, Object> entry = (Entry)mapEntry;
                        map.put(entry.getKey(), entry.getValue());
                    }
                    toReturn = map;
                    break;
                default:
                    toReturn = value;
            }
        }
        return toReturn;
    }
    
    private static Object buildPrimitiveArray(Object array, JSONArray jsonArray){
        int i = 0;
        for(Object object : jsonArray){
            Array.set(array, i, object);
            i++;
        }
        return array;
    }
    
    JSONObject jsonRepresentation = null;
    private enum Primitive{
        BYTE,
        CHAR,
        INT,
        LONG,
        FLOAT,
        DOUBLE,
        BOOLEAN,
        SHORT,
        VOID
    }
    public JSONObject toJsonObject(){
        JSONObject obj = new JSONObject();
        try{
            JSONArray fields = new JSONArray();
            for(Field field : this.getClass().getDeclaredFields()){
                if(Modifier.isStatic(field.getModifiers()) || Modifier.isTransient(field.getModifiers())){
                    continue;
                }
                field.setAccessible(true);
                JSONObject fieldDetails = new JSONObject();
                Class fieldClass = field.getType();
                fieldDetails.put("class", field.getGenericType().getTypeName());
                fieldDetails.put("name", field.getName());
                if(fieldClass.equals(ArrayList.class)){
                    ParameterizedType type = (ParameterizedType) field.getGenericType();
                    String listType = type.getActualTypeArguments()[0].getTypeName();
                    fieldDetails.put("listValuesType", listType);
                    fieldDetails.put("class", fieldClass.getName());
                } else if(fieldClass.equals(HashMap.class)){
                    ParameterizedType type = (ParameterizedType) field.getGenericType();
                    Type[] classes = type.getActualTypeArguments();
                    String keyClass = classes[0].getTypeName();
                    String valueClass = classes[1].getTypeName();
                    fieldDetails.put("keyType", keyClass);
                    fieldDetails.put("valueType", valueClass);
                    fieldDetails.put("class", fieldClass.getName());
                } else if(fieldClass.isArray()){
                    JSONArray array = new JSONArray();
                    Object object = field.get(this);
                    int length = Array.getLength(object);
                    for(int i = 0; i < length; i++){
                        array.add(Array.get(object, i));
                    }
                    fieldDetails.put("value", array);
                }
                if(field.getType().isPrimitive()){
                    fieldDetails.put("value", getPrimitiveValue(field));
                } else if(!fieldClass.isArray()){
                    fieldDetails.put("value", getValue(field.get(this)));
                }
                fields.add(fieldDetails);
            }
            obj.put("class", this.getClass().getName());
            obj.put("fields", fields);
        }catch(Exception e){
            Logger.getLogger(JSON.class.getName()).log(Level.SEVERE, null, e);
        }
        jsonRepresentation = obj;
        return obj;
    }
    
    private Primitive isPrimitive(Object object){
        if(object == null){
            return null;
        }
        Class type = object.getClass();
        if(type.equals(byte.class)){
            return Primitive.BYTE;
        } else if(type.equals(char.class)){
            return Primitive.CHAR;
        } else if(type.equals(short.class)){
            return Primitive.SHORT;
        } else if(type.equals(int.class)){
            return Primitive.INT;
        } else if(type.equals(long.class)){
            return Primitive.LONG;
        } else if(type.equals(float.class)){
            return Primitive.FLOAT;
        } else if(type.equals(double.class)){
            return Primitive.DOUBLE;
        } else if(type.equals(boolean.class)){
            return Primitive.BOOLEAN;
        } else if(type.equals(void.class)){
            return Primitive.VOID;
        }
        return null;
    }
    private byte getByteValue(Object object){
        return (byte)object;
    }
    private short getShortValue(Object object){
        return (short)object;
    }
    private int getIntValue(Object object){
        return (int)object;
    }
    private long getLongValue(Object object){
        return (long)object;
    }
    private char getCharValue(Object object){
        return (char)object;
    }
    private float getFloatValue(Object object){
        return (float)object;
    }
    private double getDoubleValue(Object object){
        return (double)object;
    }
    private boolean getBooleanValue(Object object){
        return (boolean)object;
    }
    private Object getVoidValue(Object object){
        return null;
    }
    
    private Object getPrimitiveValue(Primitive type, Object value) throws Exception{
        if(type == null){
            return null;
        }
        switch(type){
            case BYTE:
                return getByteValue(value);
            case CHAR:
                return getCharValue(value);
            case INT:
                return getIntValue(value);
            case LONG:
                return getLongValue(value);
            case FLOAT:
                return getFloatValue(value);
            case DOUBLE:
                return getDoubleValue(value);
            case BOOLEAN:
                return getBooleanValue(value);
            case SHORT:
                return getShortValue(value);
            case VOID:
                return getVoidValue(value);
        }
        return null;
    }
    
    private Object getPrimitiveValue(Field field) throws Exception{
        Class fieldType = field.getType();
        if(fieldType.equals(byte.class)){
            return field.getByte(this);
        } else if(fieldType.equals(char.class)){
            return field.getChar(this);
        } else if(fieldType.equals(short.class)){
            return field.getShort(this);
        } else if(fieldType.equals(int.class)){
            return field.getInt(this);
        } else if(fieldType.equals(long.class)){
            return field.getLong(this);
        } else if(fieldType.equals(float.class)){
            return field.getFloat(this);
        } else if(fieldType.equals(double.class)){
            return field.getDouble(this);
        } else if(fieldType.equals(boolean.class)){
            return field.getBoolean(this);
        } else if(fieldType.equals(void.class)){
            return null;
        }
        return null;
    }
    
    private Object getValue(Object value) throws Exception{
        Object toReturn = null;
        Primitive type = isPrimitive(value);
        if(type == null && value != null){
            Class fieldType = value.getClass();
            if(value instanceof JSON){
                toReturn = ((JSON)(value)).toJsonObject();
            } else if(value instanceof String || value instanceof Boolean){
                toReturn = value;
            } else if(value instanceof ArrayList){
                JSONArray temp = new JSONArray();
                for(Object subVal : (ArrayList)value){
                    temp.add(getValue(subVal));
                }
                toReturn = temp;
            } else if(value instanceof HashMap){
                JSONObject temp = new JSONObject();
                for(Object _entry : ((HashMap)(value)).entrySet()){
                    Entry entry = (Entry<Object, Object>)_entry;
                    temp.put(getValue(entry.getKey()), getValue(entry.getValue()));
                }
                toReturn = temp;
            } else if(fieldType.isArray()){
                System.out.println("found array" + value);
            } else {
                toReturn = value;
            }
        } else {
            toReturn = getPrimitiveValue(type, value);
        }
        return toReturn;
    }
    
    public String toPrettyString(){
        int numIndents = 0;
        int numQuotes = 0;
        Boolean wasOpeningBracketOrComma = true;
        if(jsonRepresentation == null){
            this.toJsonObject();
        }
        StringBuilder sb = new StringBuilder();
        for(char c : jsonRepresentation.toJSONString().toCharArray()){
            switch (c) {
                case '{':
                case '[':
                    if(!wasOpeningBracketOrComma){
                        appendLine(sb, numQuotes, numIndents);
                    }
                    sb.append(c);
                    if(numQuotes%2 == 0){
                        numIndents += 1;
                    }
                    appendLine(sb, numQuotes, numIndents); 
                    wasOpeningBracketOrComma = true;
                    break;
                case ',':
                    sb.append(c);
                    appendLine(sb, numQuotes, numIndents); 
                    wasOpeningBracketOrComma = true;
                    break;
                case '}':
                case ']':
                    if(numQuotes%2 == 0){
                        numIndents -= 1;
                    }
                    appendLine(sb, numQuotes, numIndents);
                    sb.append(c);
                    wasOpeningBracketOrComma = false;
                    break;
                case '"':
                    sb.append(c);
                    numQuotes ++;
                    break;
                default:
                    sb.append(c);
                    wasOpeningBracketOrComma = false;
                    break;
            }
        }
        return sb.toString();
    }
    
    private void appendLine(StringBuilder sb, int numQuotes, int numIndents){
        if(numQuotes%2 == 0){
            sb.append('\n');
            for(int i = 0; i < numIndents; i++){
                sb.append("    ");
            }
        }
    }
}


