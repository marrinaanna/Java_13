package ru.net.game;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    Player player1 = new Player(101, "Alex", 10);
    Player player2 = new Player(102, "Sam", 12);
    Player player3 = new Player(201, "Philipp", 13);
    Player player4 = new Player(203, "Michael", 10);
    Game game = new Game();
    ArrayList<Player> expected = new ArrayList<>();
    ArrayList<Player> actual = new ArrayList<>();

    @Test
    public void shouldRegister() {
        game.register(player1);
        game.register(player3);

        actual = game.getAll();

        expected.add(player1);
        expected.add(player3);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindPlayerByName() {
        game.register(player1);
        game.register(player2);
        game.register(player3);
        game.register(player4);
        int actual = game.findPlayerByName("Michael");
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotCompetitionBecauseUnregistered2() {
        game.register(player1);
        assertThrows(NotRegisteredException.class, () -> {
            game.round("Alex", "Philipp");
        });
    }

    @Test
    public void shouldNotCompetitionBecauseUnregistered1() {
        game.register(player1);
        assertThrows(NotRegisteredException.class, () -> {
            game.round("Philipp", "Alex");
        });
    }

    @Test
    public void shouldCompeteWin1() {
        game.register(player2);
        game.register(player3);
        int actual = game.round("Philipp", "Sam");
        assertEquals(1, actual);
    }

    @Test
    public void shouldCompeteWin2() {
        game.register(player2);
        game.register(player3);
        int actual = game.round("Sam", "Philipp");
        assertEquals(2, actual);
    }

    @Test
    public void shouldCompeteDraw() {
        game.register(player1);
        game.register(player4);
        int actual = game.round("Alex", "Michael");
        assertEquals(0, actual);
    }
}
