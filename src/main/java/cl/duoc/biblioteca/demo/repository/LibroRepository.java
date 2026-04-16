package cl.duoc.biblioteca.demo.repository;

import cl.duoc.biblioteca.demo.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {

    // Métodos automáticos heredados de JpaRepository:
    // - findAll()
    // - findById(int id)
    // - save(Libro libro)
    // - deleteById(int id)
    // - count()

    // Métodos custom (Spring Data JPA los implementa automáticamente)
    Optional<Libro> findByIsbn(String isbn);

    List<Libro> findByAutorContainingIgnoreCase(String autor);
}