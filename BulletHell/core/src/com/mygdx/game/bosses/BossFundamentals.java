package com.mygdx.game.bosses;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class BossFundamentals {
    private Texture texture;
    private SpriteBatch batch;
    private ShapeRenderer collider;

    private int sizeX = 256, sizeY = 256;
    private float positionX = 500, positionY = 500;
    private float speedX = 200, speedY = 200;
    private float randomX, randomY;
    private float moveRate = 1;

    private float lastMoved = 0;
    private float moveCooldown = 1;
    private float elapsedTime;
    public float signalDesiredX;
    public float signalDesiredY;


    private float health = 1000;

    private int lock = 1;
    private float timeMoving = 0;
    private float startingMovingTime = 0;

    private boolean destroyed = false;




    private Random random = new Random();

    public void createBoss(String path){
        batch = new SpriteBatch();
        texture = new Texture(path);
        collider = new ShapeRenderer();
    }
    public void renderBoss(float playerPositionX, float playerPositionY){
        if(!destroyed){
        move();
        checkBounds();
        checkHealth();

        batch.begin();
        batch.draw(texture, positionX, positionY, sizeX / 2, sizeY / 2, sizeX,
        sizeY, 1f, 1f, rotateToPlayer(playerPositionX, playerPositionY), 0, 0, texture.getWidth(),
        texture.getHeight(), false, false);
        batch.end();

        collider.begin(ShapeRenderer.ShapeType.Line);
        collider.identity(); // Reset the transformation matrix
        collider.translate(positionX + sizeX / 2, positionY + sizeY / 2, 0); // Translate to the center
        collider.rotate(0, 0, 1, rotateToPlayer(playerPositionX, playerPositionY)); // Rotate around the center
        collider.rect(-sizeX / 2, -sizeY / 2, sizeX, sizeY);
        collider.end();}

    }
    public void disposeBoss(){
        texture.dispose();
        batch.dispose();
        collider.dispose();
    }

    public void move() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        elapsedTime += deltaTime;
        if (elapsedTime >= lastMoved + moveCooldown) {
            if(lock == 1){
                randomX = moveRandomPosition();
                randomY = moveRandomPosition();
                lock = 0;
                startingMovingTime = elapsedTime;
            }
            if (positionX == 0 || positionY == 0){ 
                positionX += (deltaTime * speedX) * 1;
                positionY += (deltaTime * speedY) * 1;
            }

            if (positionX == Gdx.graphics.getWidth() - sizeX || positionY == Gdx.graphics.getHeight() - sizeY) {
                positionX += (deltaTime * speedX) * -1;
                positionY += (deltaTime * speedY) * -1;}
            else{
                positionX += (deltaTime * speedX) * randomX;
                positionY += (deltaTime * speedY) * randomY;
                timeMoving = elapsedTime - startingMovingTime ;}
            
            if(timeMoving > moveRate){ //condition to stop enemy stuck
                timeMoving = 0;
                lock = 1;
                lastMoved = elapsedTime ;
            }
        }
            
    }

    private float moveRandomPosition(){
        return (-1 + random.nextInt(3));
    }
    
    
    private void checkBounds(){ 
        if (positionX < 0) positionX = 0;
        if (positionY < 0) positionY = 0;
        if (positionX > Gdx.graphics.getWidth() - sizeX) positionX = Gdx.graphics.getWidth() - sizeX;
        if (positionY > Gdx.graphics.getHeight() - sizeY) positionY = Gdx.graphics.getHeight() - sizeY;

    }

    public float rotateToPlayer(float playerPositionX, float playerPositionY){
        float angleRad = (float) Math.atan2(playerPositionY - (positionY+(sizeY/2)), playerPositionX - (positionX + (sizeX/2))); //gets the rad angles
        float angleDeg = (float) Math.toDegrees(angleRad); //converts it to degrees
        return angleDeg + 90;
    }

    public void setHealth(float damage){
        health -= damage;
        System.out.println(health);
    }

    public void checkHealth() {
        if (health <= 0) {
            destroyed = true;
        }
    }

    public boolean getDestroyed() {return destroyed;}


    public Polygon getCollider() {
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
        polygon.setRotation(rotateToPlayer(signalDesiredX, signalDesiredY));
        return polygon;
    }

}
