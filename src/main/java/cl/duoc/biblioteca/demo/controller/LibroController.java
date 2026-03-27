package cl.duoc.biblioteca.demo.controller;

import cl.duoc.biblioteca.demo.model.Libro;
import cl.duoc.biblioteca.demo.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")

public class LibroController {
    @Autowired
    private LibroService libroService;

    @GetMapping
    public List<Libro> getLibros(){
        return libroService.getLibros();
    }

    @PostMapping
    public Libro save(@RequestBody Libro libro){
        return libroService.save(libro);
    }

    @GetMapping("/{id}")
    public Libro getlibroId(@PathVariable int id){
        return libroService.getlibroId(id);
    }

    @PutMapping
    public Libro updateLibro(@RequestBody Libro libro){
        return libroService.updateLibro(libro);
    }

    @DeleteMapping("/{id}")
    public String deleteLibro(@PathVariable int id){
        return libroService.deleteLibro(id);
    }
    //actividad 1.2.3
    @GetMapping("/total")
    public int totalLibros(){
        return libroService.totalLibrosV2();
    }

    // 1. Buscar por ISBN (Ejemplo: http://localhost:8080/libros/buscar/9780132350884)
    @GetMapping("/buscar/{isbn}")
    public Libro buscarPorIsbn(@PathVariable String isbn) {
        return libroService.buscarPorIsbn(isbn);
    }
}
