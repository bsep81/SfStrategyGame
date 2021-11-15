package controller;

import model.combat.Battle;

public interface Mediator {
    void registerMainController(MainPaneController controller);
    void registerBattleController(BattlePaneController controller);
    void battleControllerAction(Battle battle);




}
