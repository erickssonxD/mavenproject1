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
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.util.Optional;

/**
 *
 * @author Erick
 */
@WebService(serviceName = "ServicioInsumo")
public class ServicioInsumo {

    @WebMethod(action = "agregarInsumo")
    public String agregarInsumo(
            @WebParam(name = "codigo") int codigo,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "valor") int valor) {
        String salida;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Response r = new Response();

        try {
            JsonReader reader = new JsonReader(new FileReader("productos.json"));
            List<Insumo> insumos = gson.fromJson(reader, new TypeToken<List<Insumo>>() {
            }.getType());

            Insumo newInsumo = new Insumo(codigo, nombre, valor);

            insumos.add(newInsumo);

            try (Writer writer = new FileWriter("productos.json")) {
                gson.toJson(insumos, writer);
            }

            r.setStatus(200);
            r.setData("Agregado el insumo " + nombre);
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
            r.setStatus(500);
            r.setData("Internal server error");
        }

        salida = gson.toJson(r);

        return salida;
    }

    @WebMethod(action = "modificarInsumo")
    public String modificarInsumo(
            @WebParam(name = "codigo") int codigo,
            @WebParam(name = "nombre") String newNombre,
            @WebParam(name = "nuevoValor") int newValor) {
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
                Insumo insumo = foundInsumo.get();
                insumo.setNombre(newNombre);
                insumo.setValor(newValor);

                try (Writer writer = new FileWriter("productos.json")) {
                    gson.toJson(insumos, writer);
                }

                r.setStatus(200);
                r.setData("Insumo " + codigo + " has been successfully modified with nuevoValor: " + newValor);
            } else {
                r.setStatus(404);
                r.setData("Insumo with codigo " + codigo + " not found");
            }
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
            r.setStatus(500);
            r.setData("Internal server error");
        }

        salida = gson.toJson(r);

        return salida;
    }

    @WebMethod(action = "eliminarInsumo")
    public String eliminarInsumo(
            @WebParam(name = "codigo") int codigo) {
        String salida;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Response r = new Response();

        try {
            JsonReader reader = new JsonReader(new FileReader("productos.json"));
            List<Insumo> insumos = gson.fromJson(reader, new TypeToken<List<Insumo>>() {
            }.getType());

            boolean removed = insumos.removeIf(insumo -> insumo.getCodigo() == codigo);

            if (removed) {
                try (Writer writer = new FileWriter("productos.json")) {
                    gson.toJson(insumos, writer);
                }

                r.setStatus(200);
                r.setData("Insumo " + codigo + " has been successfully deleted");
            } else {
                r.setStatus(404);
                r.setData("Insumo with codigo " + codigo + " not found");
            }
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
            r.setStatus(500);
            r.setData("Internal server error");
        }
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
        } catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
            r.setStatus(500);
            r.setData("Error reading 'productos.json'");
        }

        salida = gson.toJson(r);
        return salida;
    }

    @WebMethod(action = "consultarInsumoByCodigo")
    public String consultarInsumo(
            @WebParam(name = "codigo") int codigo) {
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
        } catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
            r.setStatus(500);
            r.setData("Internal server error");
        }

        salida = gson.toJson(r);
        return salida;
    }
}
