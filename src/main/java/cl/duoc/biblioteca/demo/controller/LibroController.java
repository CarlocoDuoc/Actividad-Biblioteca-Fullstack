package cl.duoc.biblioteca.demo.controller;

import cl.duoc.biblioteca.demo.dto.PokemonDTO;
import cl.duoc.biblioteca.demo.model.Libro;
import cl.duoc.biblioteca.demo.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// Ajustado para coincidir con tu ruta de Postman: /api/v1/libros
@RequestMapping("/api/v1/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    // Obtener todos los libros O filtrar por autor si viene el parámetro ?autor=...
    
    
    //@GetMapping
    //public List<Libro> getLibros(){
    //    return libroService.getLibros();
    //}

    @GetMapping
    public List<Libro> getLibros(@RequestParam(name = "autor", required = false) String autor) {
        if (autor != null && !autor.isEmpty()) {
            return libroService.buscarPorAutor(autor);
        }
        return libroService.getLibros();
    }

    @PostMapping
    public Libro save(@RequestBody Libro libro){
        return libroService.save(libro);
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
    public ResponseEntity<Libro> updateLibro(@PathVariable(name = "id") int id, @RequestBody Libro libro){
        // Verificamos si existe antes de actualizar
        if (libroService.getLibroId(id) != null) {
            libro.setId(id); // Aseguramos que se actualice el ID correcto
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
            return ResponseEntity.notFound().build( );
        }
    }

    //@GetMapping("/pokemon/{nombre}")
    //public PokemonDTO getPokemon(@PathVariable String nombre) {
    //    return libroService.obtenerPokemon(nombre);
    //}

    // Este método vive dentro de LibroController
    @GetMapping("/pokemon/{nombre}")
    public ResponseEntity<?> obtenerPokemon(@PathVariable(name = "nombre") String nombre) { 
        try {
            PokemonDTO pokemon = libroService.obtenerPokemon(nombre);
            return ResponseEntity.ok(pokemon);
        } catch (Exception e) {
            e.printStackTrace(); // <--- ESTO te dirá en la consola exactamente por qué falló el mapeo
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    
    }}