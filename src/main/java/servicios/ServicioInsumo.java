/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package servicios;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Insumo;
import entity.Response;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.io.FileReader;
import java.io.FileWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.util.Optional;

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

    @WebMethod(action = "consultarInsumos")
    public String consultarInsumos() {
        String salida;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Response r = new Response();

        try {
            JsonReader reader = new JsonReader(new FileReader("productos.json"));
            List<Insumo> insumos = gson.fromJson(reader, new TypeToken<List<Insumo>>() {
            }.getType());
            r.setStatus(200);
            r.setData(insumos);
        } catch (Exception e) {
            r.setStatus(500);
            r.setData("Error reading 'productos.json'");
        }

        salida = gson.toJson(r);
        return salida;
    }

    @WebMethod(action = "consultarInsumoByCodigo")
    public String consultarInsumo(int codigo) {
        String salida;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Response r = new Response();

        try {
            JsonReader reader = new JsonReader(new FileReader("productos.json"));
            List<Insumo> insumos = gson.fromJson(reader, new TypeToken<List<Insumo>>() {
            }.getType());

            Optional<Insumo> foundInsumo = insumos.stream()
                    .filter(insumo -> insumo.getCodigo() == codigo)
                    .findFirst();

            if (foundInsumo.isPresent()) {
                r.setStatus(200);
                r.setData(foundInsumo.get());
            } else {
                r.setStatus(404);
                r.setData("Not found");
            }
        } catch (Exception e) {
            r.setStatus(500);
            r.setData("Internal server error");
        }

        salida = gson.toJson(r);
        return salida;
    }
}
