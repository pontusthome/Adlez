package com.mygdx.game.model;

import java.util.Random;

/**
 * Created by Pontus on 2016-05-05.
 */
public final class EnemyFactory {

    public static Enemy createEnemy(int type, float xPos, float yPos){

        Random random = new Random();

        float speed;
        int width;
        int height;
        int health;
        int damage;
        int gold;
        int mana;

        switch (type) {
            case Enemy.REGULAR_LEVEL_ONE:
                speed = 1.2f;
                width = 17;
                height = 17;
                health = 100;
                damage = 5;
                gold = random.nextInt(10);
                mana = 100;
                break;

            case Enemy.REGULAR_LEVEL_TWO:
                speed = 1.2f;
                width = 17;
                height = 17;
                health = 110;
                damage = 6;
                gold = random.nextInt(10) + 5;
                mana = 100;
                break;

            case Enemy.DARK_ONE_LEVEL_ONE:
                speed = 1.7f;
                width = 17;
                height = 17;
                health = 130;
                damage = 8;
                gold = random.nextInt(20) + 10;
                mana = 100;
                break;

            case Enemy.DOG_LEVEL_ONE:
                speed = 0.7f;
                width = 20;
                height = 20;
                health = 530;
                damage = 18;
                gold = random.nextInt(50) + 20;
                mana = 100;
                break;

            default:
                return null;
        }

        return new Enemy(Direction.NORTH, speed,
                width, height,
                xPos, yPos,
                health, damage, gold, mana, type);
    }
}
