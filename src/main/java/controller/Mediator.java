package controller;

import model.combat.Battle;

public interface Mediator {
    void registerMainController(MainPaneController controller);
    void registerBattleController(BattlePaneController controller);
    void registerBuildingController(BuildingsPaneController controller);
    void battleControllerAction(Battle battle);
    void battleControllerUpdateLabel(int turnsToBattle);
    void buildingsControllerInitialize();
    void mainControllerInitialize();




}
