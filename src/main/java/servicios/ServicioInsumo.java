/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package servicios;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Insumo;
import entity.Response;
import java.util.ArrayList;
import java.util.List;
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

    @WebMethod(action = "modificarInsumo")
    public String modificarInsumo(int codigo, String nombre, int nuevoValor) {
        String salida;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Response r = new Response();

        r.setStatus(200);
        r.setData("Insumo " + codigo + " has been successfully modified with nuevoValor: " + nuevoValor);

        salida = gson.toJson(r);

        return salida;
    }

    @WebMethod(action = "eliminarInsumo")
    public String eliminarInsumo(int codigo) {
        String salida;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Response r = new Response();

        r.setStatus(200);
        r.setData("Insumo " + codigo + " has been successfully deleted");

        salida = gson.toJson(r);

        return salida;
    }

    @WebMethod(action = "consultarInsumo")
    public String consultarInsumos() {
        String salida;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Response r = new Response();

        List array = new ArrayList<>();

        array.add(new Insumo(1, "Clavo", 1));
        array.add(new Insumo(2, "Martillo", 1000));
        array.add(new Insumo(3, "Perno", 2));

        r.setStatus(200);
        r.setData(array);
        salida = gson.toJson(r);
        return salida;
    }

    @WebMethod(action = "consultarInsumoPorCodigo")
    public String consultarInsumo(int codigo) {
        String salida;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Response r = new Response();

        Insumo insumo = null;

        if (codigo == 1) {
            insumo = new Insumo(1, "Clavo", 1);
        } else if (codigo == 2) {
            insumo = new Insumo(2, "Martillo", 1000);
        } else {
            insumo = new Insumo(3, "Perno", 2);
        }

        r.setStatus(200);
        r.setData(insumo);
        salida = gson.toJson(r);

        return salida;
    }
}
