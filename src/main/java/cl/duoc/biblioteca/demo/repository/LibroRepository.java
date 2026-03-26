package cl.duoc.biblioteca.demo.repository;

import cl.duoc.biblioteca.demo.model.Libro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LibroRepository {
    private List<Libro> listalibros = new ArrayList<>();

    public List<Libro> obtenerLibros(){
        return listalibros;
    }

    public Libro buscarPorId(int id){
        for (Libro libro: listalibros){
            if(libro.getId()== id){
                return libro;
            }
        }
        return null;
    }

    public Libro buscarPorIsbn(String isbn){
        for (Libro libro: listalibros){
            if(libro.getIsbn().equals(isbn)){
                return libro;
            }
        }
        return null;
    }

    public Libro guardar(Libro lib){
        listalibros.add(lib);
        return lib;
    }

    public Libro actualizar(Libro lib){
        int id= 0;
        int idPosicion=0;

        for (int i=0; i < listalibros.size(); i++){
            if (listalibros.get(i).getId()== lib.getId()){
                id = lib.getId();
                    idPosicion= 1;
            }
        }
        
        Libro libro1 = new Libro();
        libro1.setId(id);
        libro1.setTitulo(lib.getTitulo());
        libro1.setAutor(lib.getAutor());
        libro1.setFechaPublicacion(lib.getFechaPublicacion());
        libro1.setEditorial(lib.getEditorial());
        libro1.setIsbn(lib.getIsbn());
        
        listalibros.set(idPosicion, libro1);
        return libro1;
    }

    public void eliminar(int id){
        Libro libro = buscarPorId(id);
        if (libro != null){
            listalibros.remove(libro);
        }
    }
}