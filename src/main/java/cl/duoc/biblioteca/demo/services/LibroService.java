package cl.duoc.biblioteca.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient; // <--- IMPORTANTE

import cl.duoc.biblioteca.demo.dto.PokemonDTO; // <--- IMPORTANTE
import cl.duoc.biblioteca.demo.model.Libro;
import cl.duoc.biblioteca.demo.repository.LibroRepository;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<Libro> getLibros(){
        return libroRepository.findAll();
    }

    public Libro save(Libro libro){
        return libroRepository.save(libro);
    }

    public Libro getLibroId(int id){
        return libroRepository.findById(id).orElse(null);
    }

    public Libro updateLibro(Libro libro){
        // save() en JPA hace un "upsert": si el ID existe, actualiza; si no, crea.
        return libroRepository.save(libro);
    }

    public boolean deleteLibro(int id){
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Libro> buscarPorAutor(String autor) {
        return libroRepository.findByAutorContainingIgnoreCase(autor);
    }

    public PokemonDTO obtenerPokemon(String nombre) {
        System.out.println("Buscando a: " + nombre); // Log de control
    
        PokemonDTO resultado = webClientBuilder.build()
                .get()
                .uri("https://pokeapi.co/api/v2/pokemon/" + nombre.toLowerCase())
                .retrieve()
                .bodyToMono(PokemonDTO.class)
                .block();

        System.out.println("Resultado obtenido: " + resultado); // Ver en consola si trae datos
        return resultado;
    }
}