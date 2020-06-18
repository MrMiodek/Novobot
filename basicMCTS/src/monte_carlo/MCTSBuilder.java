package monte_carlo;

import boardgame.custom_functions.*;

public class MCTSBuilder {

    MCTSConfig config = new MCTSConfig();

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

    public MCTSBuilder withActionChooser(ActionChooser actionChooser){
        config.actionChooser = actionChooser;
        return this;
    }

    public MonteCarloTreeSearch build(){
        return new MonteCarloTreeSearch(config);
    }

}
