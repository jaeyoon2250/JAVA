package Tag_Game;

import java.util.Random;

public class Tag_Game_Workspace {
    Random random = new Random();
    Wizard wizard = new Wizard("마법사", 9, "마법", 7, 50);
    Warrior warrior = new Warrior("전사", 1, "검", 9, 50);
    Monster monster = new Monster("몬스터", random.nextInt(6) + 5, "반격", 0, 100);
}
