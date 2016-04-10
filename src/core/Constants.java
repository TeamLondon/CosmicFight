package core;

public class Constants {
    //Dimension variables
    public static final int WINDOW_WIDTH = 800, WINDOW_HEIGHT = (WINDOW_WIDTH / 12) * 9;
    public static final int BULLET_WIDTH = 15, BULLET_HEIGHT = 15;
    public static final int SLOW_ENEMY_WIDTH = 50, SLOW_ENEMY_HEIGHT = 50;
    public static final int PLAYER_WIDTH = 50, PLAYER_HEIGHT = 50;
    public static final int ROUND_ASTEROID_WIDTH = 64, ROUND_ASTEROID_HEIGHT = 64;
    public static final int CHAOTIC_ENEMY_WIDTH = 50, CHAOTIC_ENEMY_HEIGHT = 50;

    //Sprite paths
    public static final String PLAYER_PATH = "/player.png";
    public static final String ROUND_ASTEROID_PATH = "/rocks/Asteroids/Asteroids_256x256_008.png";
    public static final String CHAOTIC_ENEMY_PATH = "/ChaoticEnemy.png";
    public static final String BULLET_PATH = "/bullet.png";
    public static final String SLOW_ENEMY_PATH = "/enemy.png";
    public static final String BACKGROUND_PATH = "/background/GalaxyUno.bmp";

    //example
    public static final String StartGameSceneID = "StartGameScene";
    public static final String StartGameSceneResource = "/scenesFxmls/StartGameScene.fxml";

    public static final String InsertUsernameSceneID = "InsertUsernameScene";
    public static final String InsertUsernameSceneResource = "/scenesFxmls/InsertUsernameScene.fxml";

    public static final String HighScoreSceneID = "HighScoreScene";
    public static final String HighScoreSceneResource = "/scenesFxmls/HighScoreScene.fxml";

    public static final String EndGameSceneID = "EndGameScene";
    public static final String EndGameSceneResource = "/scenesFxmls/EndGameScene.fxml";

    public static final Integer SceneHeight = 600;
    public static final Integer SceneWidth = 800;

}
