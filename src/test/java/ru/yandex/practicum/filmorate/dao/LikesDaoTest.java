package ru.yandex.practicum.filmorate.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:dao-test-data.sql")
public class LikesDaoTest {
    private final LikesDao likesDao;
    private final FilmsDao filmsDao;

    @Autowired
    public LikesDaoTest(LikesDao likesDao, FilmsDao filmsDao) {
        this.likesDao = likesDao;
        this.filmsDao = filmsDao;
    }

    @Test
    public void checkCreate() {
        // ставим два лайка Шреку - он выбивается на 1-е место по популярности
        likesDao.create(4, 2);
        likesDao.create(4, 5);

        List<Film> popularFilms = filmsDao.getPopular(1);

        assertEquals("Шрек", popularFilms.get(0).getName());
    }

    @Test
    public void checkRemove() {
        // убираем два лайка Шреку - он опускается на 3-е место по популярности
        likesDao.remove(4, 1);
        likesDao.remove(4, 3);

        List<Film> popularFilms = filmsDao.getPopular(2);

        assertEquals("Титаник", popularFilms.get(0).getName());
        assertEquals("Бойцовский клуб", popularFilms.get(1).getName());
    }
}
