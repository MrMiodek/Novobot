package gameplay;

import boardgame.elements.Action;
import boardgame.elements.GameActor;
import boardgame.elements.GameConfig;
import boardgame.elements.GameState;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Class for playing different games.
 * GameRunner should be able to handle all MCTS compatible games.
 */
public class GameRunner
        <GS extends GameState<GS, GA, AC>,
        AC extends Action<GS,GA>,
        GA extends GameActor> {

    Hashtable<GA, Agent<GS, AC>> actorAgentDict = new Hashtable<GA, Agent<GS, AC>>();

    GameConfig<GS, GA> gameConfig;

    GameplayLogger<GS, AC, GA> logger = new GameplayLogger<GS, AC, GA>();

    public GameRunner(GameConfig<GS, GA> gameConfig, Hashtable<GA, Agent<GS, AC>> agentActorDict) {
        this.gameConfig = gameConfig;
        this.actorAgentDict = agentActorDict;
    }

    public GameRunner(GameConfig<GS, GA> gameConfig, Hashtable<GA, Agent<GS, AC>> agentActorDict, GameplayLogger logger) {
        this(gameConfig, agentActorDict);
        this.logger = logger;
    }

    public GameRunner(GameConfig<GS, GA> gameConfig, List<Agent<GS, AC>> agents) throws RunnerException {
        if( gameConfig.getListOfActors().size() != agents.size()){
            throw new RunnerException("Number of agents needs to match number of actors");
        }
        this.gameConfig = gameConfig;
        ArrayList<Agent<GS, AC>> unassigned = new ArrayList<>(agents);
        for(GA actor : gameConfig.getListOfActors()){
            int r = (int) ((Math.random() * unassigned.size()));
            Agent<GS, AC> agent = unassigned.get(r);
            actorAgentDict.put(actor, agent);
            unassigned.remove(agent);
        }
    }

    public GameRunner(GameConfig<GS, GA> gameConfig, List<Agent<GS, AC>> agents, GameplayLogger logger) throws RunnerException {
        this(gameConfig, agents);
        this.logger = logger;
    }

    public void playout() {
        GS gameState = gameConfig.getInitialGameState();
        logger.logBeforeGame(gameState);
        while (gameState.getEndScore() == null) {
            GA actor = gameState.getCurrentActor();
            Agent<GS, AC> agent = actorAgentDict.get(actor);
            AC action = agent.chooseActionToPlay(gameState);
            logger.logBeforeMove(gameState, action);
            action.apply(gameState);
            logger.logAfterMove(gameState, action);
        }
        logger.logAfterGame(gameState);
    }

}
