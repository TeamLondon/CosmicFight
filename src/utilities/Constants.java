package utilities;

public class Constants {
    //Dimension variables
    public static final int WINDOW_WIDTH = 800, WINDOW_HEIGHT = (WINDOW_WIDTH / 12) * 9;
    public static final int BULLET_WIDTH = 15, BULLET_HEIGHT = 15;
    public static final int BOSS_BULLET_WIDTH = 1, BOSS_BULLET_HEIGHT = 24;
    public static final int GIANT_BOMB_WIDTH = 50, GIANT_BOMB_HEIGHT = 50;
    public static final int SLOW_ENEMY_WIDTH = 50, SLOW_ENEMY_HEIGHT = 50;
    public static final int PLAYER_WIDTH = 50, PLAYER_HEIGHT = 50;
    public static final int ROUND_ASTEROID_WIDTH = 64, ROUND_ASTEROID_HEIGHT = 64;
    public static final int CHAOTIC_ENEMY_WIDTH = 50, CHAOTIC_ENEMY_HEIGHT = 50;
    public static final int BOSS_WIDTH = 300, BOSS_HEIGHT = 100;
    public static final int BONUS_WIDTH = 30, BONUS_HEIGHT = 30;

    //Sprite paths
    public static final String PLAYER_PATH = "/player.png";
    public static final String ROUND_ASTEROID_PATH = "/rocks/Asteroids/Asteroids_256x256_008.png";
    public static final String CHAOTIC_ENEMY_PATH = "/ChaoticEnemy.png";
    public static final String BULLET_PATH = "/bullet.png";
    public static final String BOSS_BULLET_PATH = "/bossBullet.png";
    public static final String GIANT_BOMB_PATH = "/bomb/player/1.png";
    public static final String SLOW_ENEMY_PATH = "/enemy.png";
    public static final String BACKGROUND_PATH = "/background/space3.jpg";
    public static final String BOSS_PATH = "/firstLevelBoss.png";
    public static final String BONUS_PATH = "/GreenPlanet.png";
    public static final String HUD_HEALTH_BAR_PATH = "/HUD/HUD_healthBar.png";
    public static final String HUD_COOLDOWN_BAR_PATH = "/HUD/HUD_cooldownBar.png";
    public static final String BOSS_HEALTH_BAR_PATH = "/HealthBars/BossHealthBar.png";
    public static final String SLOW_ENEMY_HEALTH_BAR_PATH = "/HealthBars/slowEnemyHealthBar.png";

    //FXML Paths
    public static final String START_GAME_SCENE_RESOURCE = "/scenesFxmls/StartGameScene.fxml";
    public static final String INSERT_USERNAME_SCENE_RESOURCE = "/scenesFxmls/InsertUsernameScene.fxml";
    public static final String HIGH_SCORE_SCENE_RESOURCE = "/scenesFxmls/HighScoreScene.fxml";
    public static final String END_GAME_SCENE_RESOURCE = "/scenesFxmls/GameOverScene.fxml";

    // Starting coordinates
    public static final int PLAYER_X = 100, PLAYER_Y = 100;
    //Styles
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
