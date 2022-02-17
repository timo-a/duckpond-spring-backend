package com.circleescape.server.model;

import com.circleescape.server.model.game.Collocation;
import com.circleescape.server.model.game.GameEntity;
import com.circleescape.server.model.game.GameEntityRepository;
import com.circleescape.server.model.game.GameParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameEntityRepository gameEntityRepository;

    @Autowired
    public GameService(GameEntityRepository gameRepository) {
        this.gameEntityRepository = gameRepository;
    }

    public Game createNewGame() {
        GameEntity game = new GameEntity();
        game.setAuthKey("secret");
        GameParameters params = new GameParameters("4","3");
        game.setGameParameters(params);
        Collocation c = new Collocation(8,8,8);
        game.getCollocations().add(c);
        game.getCollocations().add(c);
        //game.setAuthKey("secret");
        gameEntityRepository.save(game);

        Game g = new Game();
        return g;
    }
}
