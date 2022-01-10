package controller;

import model.combat.Battle;

public class ControllerMediator implements Mediator{

    private MainPaneController mainPaneController;
    private BattlePaneController battlePaneController;
    private BuildingsPaneController buildingsPaneController;



    private static final ControllerMediator INSTANCE = new ControllerMediator();

    private ControllerMediator(){}

    public static ControllerMediator getInstance(){
        return INSTANCE;
    }

    @Override
    public void registerMainController(MainPaneController controller) {
        mainPaneController = controller;
    }

    @Override
    public void registerBattleController(BattlePaneController controller) {
        battlePaneController = controller;
    }

    @Override
    public void registerBuildingController(BuildingsPaneController controller) {
        buildingsPaneController = controller;
    }

    @Override
    public void battleControllerAction(Battle battle) {
        battlePaneController.startBattle(battle);
    }

    @Override
    public void battleControllerUpdateLabel(int turnsToBattle) {
        battlePaneController.updateLabel(turnsToBattle);
    }

    @Override
    public void buildingsControllerInitialize() {
        buildingsPaneController.initialize();
    }

    @Override
    public void mainControllerInitialize() {
        mainPaneController.initialize();
    }
}
