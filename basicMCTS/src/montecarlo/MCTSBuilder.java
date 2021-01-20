package montecarlo;

import boardgame.customfunctions.*;

/**
 * Basic builder of Monte-Carlo Tree Search agents.
 */
public class MCTSBuilder {

    protected MCTSConfig config;

    public MCTSBuilder(){
        this.config = new MCTSConfig();
    }

    public MCTSBuilder(MCTSConfig config){
        this.config = config;
    }



    public MCTSBuilder withTimeCalculator(TimeCalculator timeCalculator){
        config.timeCalculator = timeCalculator;
        return this;
    }

    public MCTSBuilder withUCT(ChildFinder childFinder){
        config.bestChildFinder = childFinder;
        return this;
    }

    public MCTSBuilder withScoreUpdater(ScoreUpdater scoreUpdater){
        config.scoreUpdater = scoreUpdater;
        return this;
    }

    public MCTSBuilder withActionChooser(ActionChooser simulatedActionChooser){
        config.simulatedActionChooser = simulatedActionChooser;
        return this;
    }

    public MonteCarloTreeSearch build(){
        return new MonteCarloTreeSearch(config);
    }

}
