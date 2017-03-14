package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.communication.SerDes;

/**
 * Contains data returned from a command's execute() method.
 * The data can be primitive data types, commands, and other classes.
 * This data is stored as a json formatted String that can be accessed by getRawSerializedResult().
 * This class also has an error message, which if set indicates an error.
 * When there is an error the Result object contains no valid data,
 * so any attempt to extract data from the Result object would be invalid.
 * No error message indicates that the data is valid.
 * @invariant getRawSerializedResult(), getErrorMessage(), or both are null
 */
public abstract class Result
{
    private String serializedResult = null;
    private String errorMessage = null;

    /**
     * Creates a Result object with null data and no error message.
     * @pre none
     * @post getRawSerializedResult() is null
     * @post getErrorMessage() is null
     */
    public Result()
    {
        serializedResult = null;
        errorMessage = null;
    }

    /**
     * Returns a String containing the json serialization of this Result's data,
     * or null if this Result has no data.
     * @pre none
     * @post String containing json serialized object (or null) is returned
     * @return serialized object represented by this Result or null if no object
     */
    public String getRawSerializedResult()
    {
        return serializedResult;
    }

    /**
     * Sets getRawSerializedResult() to a serialized version of obj.
     * @pre getErrorMessage() is null
     * @pre obj != null
     * @post getRawSerializedResult() returns a json representation of obj
     * @param obj object to serialize and set getRawSerializedResult() to
     */
    public void setResultObject(Object obj)
    {
        serializedResult = SerDes.serialize(obj);
    }

//    /**
//     * @param serialized serialized object represented by this Result
//     */
//    public void setRawSerializedResult(String serialized)
//    {
//        serializedResult = serialized;
//    }

    /**
     * Returns the error message if there was an error,
     * otherwise returns null.
     * @pre none
     * @post error message is returned (or null if no error)
     * @return error message if there was an error (otherwise null)
     */
    public String getErrorMessage()
    {
        return errorMessage;
    }

    /**
     * Used to set the error message in the case of an error.
     * @pre getRawSerializedResult() is null
     * @post getErrorMessage() returns error
     * @param error error message that will be returned by getErrorMessage()
     */
    public void setErrorMessage(String error)
    {
        errorMessage = error;
    }

    /**
     * Deserialize getRawSerializedResult() into the given type.
     * @pre getRawSerializedResult() represents an object of the class given
     * @post deserialization of getRawSerializedResult() as the given type is returned
     * @param type class to serialize to
     * @return deserialize version of this Result
     */
    public Object toClass(Class<?> type)
    {
        return SerDes.deserializeToClass(serializedResult, type);
    }

    /**
     * Deserialize getRawSerializeResult() into a boolean.
     * @pre getRawSerializedResult() represents a boolean
     * @post deserialization of getRawSerializedResult() as a boolean is returned
     * @return deserialization of this Result's data as a boolean
     */
    public boolean toBoolean()
    {
        return (Boolean) toClass(Boolean.class);
    }

    /**
     * Deserialize getRawSerializeResult() into an int.
     * @pre getRawSerializedResult() represents an int
     * @post deserialization of getRawSerializedResult() as an int is returned
     * @return deserialization of this Result's data as an int
     */
    public int toInt()
    {
        return (Integer) toClass(Integer.class);
    }

    /**
     * Deserialize getRawSerializeResult() into a long.
     * @pre getRawSerializedResult() represents a long
     * @post deserialization of getRawSerializedResult() as a long is returned
     * @return deserialization of this Result's data as a long
     */
    public long toLong()
    {
        Long l = (Long) toClass(Long.class);
        return l.intValue();
    }

    /**
     * Deserialize getRawSerializeResult() into a short.
     * @pre getRawSerializedResult() represents a short
     * @post deserialization of getRawSerializedResult() as a short is returned
     * @return deserialization of this Result's data as a short
     */
    public short toShort()
    {
        return (Short) toClass(Short.class);
    }

    /**
     * Deserialize getRawSerializeResult() into a byte.
     * @pre getRawSerializedResult() represents a byte
     * @post deserialization of getRawSerializedResult() as a byte is returned
     * @return deserialization of this Result's data as a byte
     */
    public byte toByte()
    {
        return (Byte) toClass(Byte.class);
    }

    /**
     * Deserialize getRawSerializeResult() into a double.
     * @pre getRawSerializedResult() represents a double
     * @post deserialization of getRawSerializedResult() as a double is returned
     * @return deserialization of this Result's data as a double
     */
    public double toDouble()
    {
        return (Double) toClass(Double.class);
    }

    /**
     * Deserialize getRawSerializeResult() into a float.
     * @pre getRawSerializedResult() represents a float
     * @post deserialization of getRawSerializedResult() as a float is returned
     * @return deserialization of this Result's data as a float
     */
    public float toFloat()
    {
        return (Float) toClass(Float.class);
    }

    /**
     * Deserialize getRawSerializeResult() into a String.
     * @pre getRawSerializedResult() represents a String
     * @post deserialization of getRawSerializedResult() as a String is returned
     * @return deserialization of this Result's data as a String
     */
    public String toStringClass()
    {
        return (String) toClass(String.class);
    }

    /**
     * Deserialize getRawSerializeResult() into an ICommand.
     * The serialized instance of the ICommand must contain a name field that is the name of the command.
     * @pre getRawSerializedResult() represents an ICommand with a name field
     * @pre there is a command class with a name that matches the name field of the serialized command
     * @post deserialization of getRawSerializedResult() as an ICommand is returned
     * @return deserialization of this Result's data as an ICommand
     */
    public abstract ICommand toCommand();
}
