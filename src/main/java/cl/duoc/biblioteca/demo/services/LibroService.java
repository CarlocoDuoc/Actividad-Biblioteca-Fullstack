package cl.duoc.biblioteca.demo.services;

import cl.duoc.biblioteca.demo.model.Libro;
import cl.duoc.biblioteca.demo.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> getLibros(){
        return libroRepository.obtenerLibros();
    }
    public Libro save(Libro libro){
        return libroRepository.guardar(libro);
    }
    public Libro getlibroId(int id){
        return libroRepository.buscarPorId(id);
    }
    public Libro updateLibro(Libro libro){
        return libroRepository.actualizar(libro);
    }
    public String deleteLibro(int id){
        libroRepository.eliminar(id);
        return "producto eliminado";
    }
}
