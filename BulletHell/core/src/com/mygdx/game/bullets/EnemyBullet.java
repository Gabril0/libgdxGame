package com.mygdx.game.bullets;

import com.badlogic.gdx.math.Polygon;

public class EnemyBullet extends SimpleBullet{
    EnemyBullet(float positionX, float positionY, Float sizeX, Float sizeY) {
        super(positionX, positionY, sizeX, sizeY);
        super.setBulletSpeed(300);
        
    }

    @Override
    public Polygon getCollider() {
        Polygon polygon = new Polygon();
        float centerX = positionX + sizeX / 2;
        float centerY = positionY + sizeY / 2;
        float halfSizeX = sizeX / 4; // Calculate half of the width (divide by 4 instead of 2)
        float halfSizeY = sizeY / 4; // Calculate half of the height (divide by 4 instead of 2)
        float[] vertices = new float[] {
                centerX - halfSizeX, centerY - halfSizeY, // bottom-left
                centerX + halfSizeX, centerY - halfSizeY, // bottom-right
                centerX + halfSizeX, centerY + halfSizeY, // top-right
                centerX - halfSizeX, centerY + halfSizeY // top-left
        };
        polygon.setVertices(vertices);
        polygon.setOrigin(centerX, centerY);
        polygon.setRotation(playerRotation);
        return polygon;
    }
}
