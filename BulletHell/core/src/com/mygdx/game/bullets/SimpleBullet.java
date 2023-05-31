package com.mygdx.game.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;

public class SimpleBullet implements Bullet {
    protected boolean isActive = false; //checks if the bullet is active in the bullet pool, initialize as inactive
    protected Texture texture;
    protected SpriteBatch spriteBatch;
    protected float positionX, positionY; //for the position manipulation of the bullet
    protected float sizeX, sizeY; //for the size
    protected float bulletSpeed = 500;
    protected float velocityX = 200, velocityY = 200; //for the velocity manipulation of the bullet
    protected float playerRotation = 0f;

    protected ShapeRenderer collision;

    protected int guarantee = 1;

    SimpleBullet(float positionX, float positionY, float sizeX, float sizeY){ //Constructor to get players position
        this.positionX = positionX;
        this.positionY = positionY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        
    }

    public void createBullet(String imgPath){ //creating the bullet
        spriteBatch = new SpriteBatch();
        texture = new Texture(imgPath);
        collision = new ShapeRenderer();
    }

    public void renderBullet(float positionX, float positionY, float playerSizeX, float playersizeY, float playerRotation){ //rendering and moving
        if(guarantee == 1){ //guaranteed to run once to get the player position
            this.positionX = positionX + (playerSizeX/2 - sizeX/2); //making the bullet spawn from the center
            this.positionY = positionY + (playersizeY/2 - sizeY/2);
            this.playerRotation = playerRotation;
            
            // Calculate initial velocity based on player rotation
            float angleRad = playerRotation * MathUtils.degreesToRadians;
            velocityX = MathUtils.cos(angleRad) * bulletSpeed;
            velocityY = MathUtils.sin(angleRad) * bulletSpeed;
        }
        guarantee = 0;

        checkBounds();
        move();
        specialAction();

        
        spriteBatch.begin();
        spriteBatch.draw(texture, this.positionX, this.positionY, sizeX / 2, sizeY / 2, sizeX, sizeY, 1f, 1f, this.playerRotation, 0, 0, texture.getWidth(),
        texture.getHeight(), false, false);
        spriteBatch.end();

        //drawCollider();

        // collision.begin(ShapeRenderer.ShapeType.Line);
        // collision.identity(); // Reset the transformation matrix
        // collision.translate(this.positionX + sizeX / 2, this.positionY + sizeY / 2, 0); // Translate to the bullet's center
        // collision.rotate(0, 0, 1, this.playerRotation); // Rotate around the bullet's center
        // collision.rect(-sizeX / 2, -sizeY / 2, sizeX, sizeY); // Draw the rotated rectangle
        // collision.end();
    }

    public void disposeBullet(){ //freeing memory
        spriteBatch.dispose();
        texture.dispose();
        collision.dispose();
    }


    public void move() {
        positionX += velocityX * Gdx.graphics.getDeltaTime();
        positionY += velocityY * Gdx.graphics.getDeltaTime();
    }


    public void activate() { //activation method for the list
        isActive = true;
    }


    public void deactivate() { //deactivation method for the list
        isActive = false;
        guarantee = 1; //resets the guarantee to get the player position once is activated again
    }

    public boolean getIsActive(){ //to check whatever if a bullet is active or not
        return isActive;
    }

    private void checkBounds(){
        if (positionX < -50) deactivate();
        if (positionY < -50) deactivate();
        if (positionX > Gdx.graphics.getWidth() + 50) deactivate();
        if (positionY > Gdx.graphics.getHeight() + 50) deactivate();
    } 

    // private void drawCollider() {
    //     Polygon collider = getCollider();
    //     float[] vertices = collider.getTransformedVertices();
    
    //     collision.begin(ShapeRenderer.ShapeType.Line);
    //     collision.polygon(vertices);
    //     collision.end();
    // }

    public Polygon getCollider(){
        Polygon polygon = new Polygon();
        float centerX = positionX + sizeX / 2;
        float centerY = positionY + sizeY / 2;
        float[] vertices = new float[] {
            positionX, positionY,                           // bottom-left
            positionX + sizeX, positionY,                   // bottom-right
            positionX + sizeX, positionY + sizeY,           // top-right
            positionX, positionY + sizeY                    // top-left
        };
        polygon.setVertices(vertices);
        polygon.setOrigin(centerX, centerY);
        polygon.setRotation(playerRotation);
        return polygon;
    }

    protected void specialAction(){} // this is a class for bullet upgrade special actions

    @Override
    public Bullet getBullet() {
        return this;
    }
}
