package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class Film {
    private long id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    @Size(max = 200)
    private String description;
    @NotNull
    private LocalDate releaseDate;
    @NotNull
    @Positive
    @JsonProperty("duration")
    private int duration;
    private List<Genre> genres;
    private Mpa mpa;
    private int rate;
//    private final Set<Long> likes = new HashSet<>();

//    public void addLike(long userId) {
//        likes.add(userId);
//    }
//
//    public void removeLike(long userId) {
//        likes.remove(userId);
//    }
}
