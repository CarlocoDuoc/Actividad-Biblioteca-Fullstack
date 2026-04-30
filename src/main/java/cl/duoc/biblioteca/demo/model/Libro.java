package cl.duoc.biblioteca.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity 
@Table(name = "libros")
public class Libro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "El ISBN no puede estar vacío")
    @Column(name = "isbn", nullable = false, length = 20, unique = true)
    private String isbn;

    @NotBlank(message = "El título es obligatorio")
    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @NotBlank(message = "La editorial es obligatoria")
    @Column(name = "editorial", nullable = false, length = 200)
    private String editorial;

    @NotNull(message = "La fecha de publicación es obligatoria")
    @Positive(message = "El año debe ser un número positivo")
    @Column(name = "fecha_publicacion", nullable = false)
    private Integer fechaPublicacion; 

    @NotBlank(message = "El autor no puede estar vacío")
    @Column(name = "autor", nullable = false, length = 150)
    private String autor;

}