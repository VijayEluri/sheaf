package com.goodworkalan.sheaf;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * A general purpose exception that indicates that an error occurred in one 
 * of the classes in the sheaf package.
 *   
 * @author Alan Gutierrez
 */
public final class SheafException
extends RuntimeException
{
    /** The serial version id. */
    private static final long serialVersionUID = 20070821L;
    
    /** A list of arguments to the formatted error message. */
    private final List<Object> arguments = new ArrayList<Object>(); 
    
    /** The error code. */
    private final int code;
    
    /**
     * Create a Sheaf exception with the given error code.
     * @param code The error code.
     */
    public SheafException(int code)
    {
        this.code = code;
    }

    /**
     * Wrap the given cause exception in a Sheaf exception with the given error
     * code.
     * 
     * @param code
     *            The error code.
     * @param cause
     *            The cause exception.
     */
    public SheafException(int code, Throwable cause)
    {
        super(cause);
        this.code = code;
    }

    /**
     * Get the error code.
     * 
     * @return The error code.
     */
    public int getCode()
    {
        return code;
    }

    /**
     * Add an argument to the list of arguments to provide the formatted error
     * message associated with the error code.
     * 
     * @param argument
     *            The format argument.
     * @return This sheaf exception for chained invocation of add.
     */
    public SheafException add(Object argument)
    {
        arguments.add(argument);
        return this;
    }

    /**
     * Create an detail message from the error message format associated with
     * the error code and the format arguments.
     * 
     * @return The exception message.
     */
    @Override
    public String getMessage()
    {
        String key = Integer.toString(code);
        ResourceBundle exceptions = ResourceBundle.getBundle("com.goodworkalan.sheaf.exceptions");
        String format;
        try
        {
            format = exceptions.getString(key);
        }
        catch (MissingResourceException e)
        {
            return key;
        }
        try
        {
            return String.format(format, arguments.toArray());
        }
        catch (Throwable e)
        {
            throw new Error(key, e);
        }
    }
}