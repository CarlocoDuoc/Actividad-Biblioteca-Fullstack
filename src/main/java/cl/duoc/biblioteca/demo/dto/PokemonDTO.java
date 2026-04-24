package cl.duoc.biblioteca.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  // <--- Agrega esto
@AllArgsConstructor // <--- Agrega esto
public class PokemonDTO {
    private int id;
    private String name;
    private int height;
    private int weight;
}