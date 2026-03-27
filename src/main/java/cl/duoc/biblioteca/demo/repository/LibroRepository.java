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

    public Libro buscarPorIsbn(String isbn) {
        for (Libro libro : listalibros) {
            if (libro.getIsbn() != null && libro.getIsbn().equals(isbn)) {
                return libro;
            }
        }
        return null; // Si no lo encuentra, devuelve null de forma segura
    }

    public Libro guardar(Libro lib){
        listalibros.add(lib);
        return lib;
    }

    public Libro actualizar(Libro lib) {
        for (int i = 0; i < listalibros.size(); i++) {
            if (listalibros.get(i).getId() == lib.getId()) {
                listalibros.set(i, lib); // Reemplaza en la posición 'i' donde lo encontró
                return lib;
            }
        }
        return null; 
    }

    public void eliminar(int id){
        Libro libro = buscarPorId(id);
        if (libro != null){
            listalibros.remove(libro);
        }
    }

    // actividad 1.2.3
    public LibroRepository() {
        listalibros.add(new Libro(1, "9789569646638", "Fuego y Sangre", "Penguin Random House Grupo Editorial", 2018, "George R. R. Martin"));
        listalibros.add(new Libro(2, "9789563494150", "Quique Hache: El Mall Embrujadoy Otras Historias", "Sm Ediciones", 2014, "Sergio Gomez"));
        listalibros.add(new Libro(3, "9781484256251", "Spring Boot Persistence BestPractices", "Apress", 2020, "Anghel Leonard"));
        listalibros.add(new Libro(4, "9789566075752", "Harry Potter y la piedrafilosofal", "Salamandra", 2024, "J. K. Rowling"));
        listalibros.add(new Libro(5, "9780439139601", "Harry Potter y el prisionero deAzkaban", "Scholastic", 1999, "J. K. Rowling"));
        listalibros.add(new Libro(6, "9780439136365", "Harry Potter y el cáliz defuego", "Scholastic", 2000, "J. K. Rowling"));
        listalibros.add(new Libro(7, "9780321127426", "Effective Java", "Addison-Wesley", 2008, "Joshua Bloch"));
        listalibros.add(new Libro(8, "9780134685991", "Clean Architecture", "Prentice Hall", 2017, "Robert C. Martin"));
        listalibros.add(new Libro(9, "9780201633610", "Design Patterns", "Addison-Wesley", 1994, "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides"));
        listalibros.add(new Libro(10, "9780132350884", "Clean Code", "Prentice Hall",2008, "Robert C. Martin"));
    }

    public int totalLibros(){
        return listalibros.size();
    }
}