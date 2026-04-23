package cl.duoc.biblioteca.demo.repository;

import cl.duoc.biblioteca.demo.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {

    // --- MÉTODOS QUE YA TENÍAS (No los borres) ---
    Optional<Libro> findByIsbn(String isbn);
    List<Libro> findByAutorContainingIgnoreCase(String autor);

    // --- NUEVOS MÉTODOS CON @QUERY (Para la actividad) ---

    // 1. Consulta nativa para buscar por ISBN
    @Query(value = "SELECT * FROM libros WHERE isbn = :isbn", nativeQuery = true)
    Libro buscarPorIsbnNativo(@Param("isbn") String isbn);

    // 2. Consulta nativa para buscar por rango de años
    @Query(value = "SELECT * FROM libros WHERE fecha_publicacion BETWEEN :inicio AND :fin", nativeQuery = true)
    List<Libro> buscarPorRangoDeAnios(@Param("inicio") int inicio, @Param("fin") int fin);
}