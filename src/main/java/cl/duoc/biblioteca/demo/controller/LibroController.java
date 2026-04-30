package cl.duoc.biblioteca.demo.controller;

import cl.duoc.biblioteca.demo.dto.PokemonDTO;
import cl.duoc.biblioteca.demo.model.Libro;
import cl.duoc.biblioteca.demo.services.LibroService;
import jakarta.validation.Valid; // <--- IMPORTANTE
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public List<Libro> getLibros(@RequestParam(name = "autor", required = false) String autor) {
        if (autor != null && !autor.isEmpty()) {
            return libroService.buscarPorAutor(autor);
        }
        return libroService.getLibros();
    }

    @PostMapping
    public ResponseEntity<Libro> save(@Valid @RequestBody Libro libro){
        return ResponseEntity.ok(libroService.save(libro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroId(@PathVariable(name = "id") int id){
        Libro libro = libroService.getLibroId(id);
        if (libro != null) {
            return ResponseEntity.ok(libro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable(name = "id") int id, @Valid @RequestBody Libro libro){
        if (libroService.getLibroId(id) != null) {
            libro.setId(id);
            return ResponseEntity.ok(libroService.updateLibro(libro));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLibro(@PathVariable(name = "id") int id){
        boolean eliminado = libroService.deleteLibro(id);
        if (eliminado) {
            return ResponseEntity.ok("Libro eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/pokemon/{nombre}")
    public ResponseEntity<?> obtenerPokemon(@PathVariable(name = "nombre") String nombre) { 
        try {
            PokemonDTO pokemon = libroService.obtenerPokemon(nombre);
            return ResponseEntity.ok(pokemon);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}