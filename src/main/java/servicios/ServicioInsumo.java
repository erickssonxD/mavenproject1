/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package servicios;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Response;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Erick
 */
@WebService(serviceName = "ServicioInsumo")
public class ServicioInsumo {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(action = "agregarInsumo")
    public String agregarInsumo(int codigo, String nombre, int valor) {
        String salida;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Response r = new Response();
        
        r.setStatus(200);
        r.setData("Agregado el insumo " + nombre);
        
        salida = gson.toJson(r);
        
        return salida;
    }
}
