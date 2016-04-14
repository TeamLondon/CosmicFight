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
    public static final String BACKGROUND_PATH = "/background/space3.jpg";
    public static final String BOSS_PATH = "/firstLevelBoss.png";
    public static final String HUD_HEALTH_BAR_PATH = "/HUD/HUD_healthBar.png";

    //example
    public static final String StartGameSceneResource = "/scenesFxmls/StartGameScene.fxml";

    public static final String InsertUsernameSceneResource = "/scenesFxmls/InsertUsernameScene.fxml";

    public static final String HighScoreSceneResource = "/scenesFxmls/HighScoreScene.fxml";

    public static final String EndGameSceneResource = "/scenesFxmls/GameOverScene.fxml";

    public static final Integer SceneHeight = 600;
    public static final Integer SceneWidth = 800;

    public static final String BUTTON_STYLE = "-fx-background-color: \n" +
            "        linear-gradient(#ffd65b, #e68400),\n" +
            "        linear-gradient(#ffef84, #f2ba44),\n" +
            "        linear-gradient(#ffea6a, #efaa22),\n" +
            "        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n" +
            "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n" +
            "    -fx-background-radius: 30;\n" +
            "    -fx-background-insets: 0,1,2,3,0;\n" +
            "    -fx-text-fill: #654b00;\n" +
            "    -fx-font-weight: bold;\n" +
            "    -fx-font-size: 14px;\n" +
            "    -fx-padding: 10 20 10 20;";

}
