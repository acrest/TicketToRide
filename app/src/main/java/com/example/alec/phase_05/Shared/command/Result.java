package com.example.alec.phase_05.Shared.command;

/**
 * Contains data returned for a commands execute() method.
 * Can contain primitive data types, commands, and other classes.
 * Created by samuel on 2/3/17.
 */
public abstract class Result
{
    private String serializedResult;
    private String errorMessage;

    /**
     * Creates of Result object with all instance variables set to null.
     */
    public Result()
    {
        serializedResult = null;
        errorMessage = null;
    }

    /**
     * @return serialized object represented by this Result
     */
    public String getRawSerializedResult()
    {
        return serializedResult;
    }

    /**
     * Sets the raw serialized result to a serialized version of obj.
     * @param obj object to serialize and set result string to
     */
    public void setResultObject(Object obj)
    {
        serializedResult = SerDes.serialize(obj);
    }

    /**
     * @param serialized serialized object represented by this Result
     */
    public void setRawSerializedResult(String serialized)
    {
        serializedResult = serialized;
    }

    /**
     * @return error message if there was an error (otherwise null)
     */
    public String getErrorMessage()
    {
        return errorMessage;
    }

    /**
     * @param error message if there was an error (otherwise null)
     */
    public void setErrorMessage(String error)
    {
        errorMessage = error;
    }

    /**
     * Deserialize the raw serialized result string into the given type.
     * @param type class to serialize to
     * @return deserialize version of this Result
     */
    public Object toClass(Class<?> type)
    {
        return SerDes.deserializeToClass(serializedResult, type);
    }

    /**
     * @return deserialization of this Result as a boolean
     */
    public boolean toBoolean()
    {
        return (Boolean) toClass(Boolean.class);
    }

    /**
     * @return deserialization of this Result as an int
     */
    public int toInt()
    {
        return (Integer) toClass(Integer.class);
    }

    /**
     * @return deserialization of this Result as a long
     */
    public long toLong()
    {
        Long l = (Long) toClass(Long.class);
        return l.intValue();
    }

    /**
     * @return deserialization of this Result as a short
     */
    public short toShort()
    {
        return (Short) toClass(Short.class);
    }

    /**
     * @return deserialization of this Result as a byte
     */
    public byte toByte()
    {
        return (Byte) toClass(Byte.class);
    }

    /**
     * @return deserialization of this Result as a double
     */
    public double toDouble()
    {
        return (Double) toClass(Double.class);
    }

    /**
     * @return deserialization of this Result as a float
     */
    public float toFloat()
    {
        return (Float) toClass(Float.class);
    }

    /**
     * @return deserialization of this Result as a String
     */
    public String toString()
    {
        return (String) toClass(String.class);
    }

    /**
     * Will return deserialization of this Result as a commmand.
     * @return deserialized command
     */
    public abstract ICommand toCommand();
}
