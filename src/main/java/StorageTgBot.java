import java.util.HashMap;

public class StorageTgBot {
    private HashMap<Long, TicTacToeTg> games;

    public void addGame(Long id, TicTacToeTg game) {
        games.put(id, game);
    }
    public TicTacToeTg findGameById(Long id) {
        return games.get(id);
    }

    StorageTgBot() {
        games = new HashMap<>();
    }

}
