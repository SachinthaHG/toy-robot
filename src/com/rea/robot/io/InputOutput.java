package com.rea.robot.io;

/**
 * This interface can be implemented for any input output method (ex: read from and write to file/console)
 */
public interface InputOutput {
    public String read();

    public void write(String line);
}
