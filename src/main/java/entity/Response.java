/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Erick
 */
import java.io.Serializable;

public class Response implements Serializable {
    private int status;
    private Object data;

    // Empty constructor
    public Response() {
    }

    // Constructor with all parameters
    public Response(int status, Object data) {
        this.status = status;
        this.data = data;
    }

    // Getter and Setter methods for 'status'
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    // Getter and Setter methods for 'data'
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
