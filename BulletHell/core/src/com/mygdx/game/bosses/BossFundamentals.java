package com.mygdx.game.bosses;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.mygdx.game.bullets.BulletPool;
import com.mygdx.game.player.Player;

import java.util.Random;

public class BossFundamentals {
    //Rendering
    private Texture texture;
    private SpriteBatch batch;
    private ShapeRenderer collider;

    //Location
    private int sizeX = 256, sizeY = 256;
    private float positionX = 500, positionY = 500;
    private float speedX = 200, speedY = 200;
    private float randomX, randomY;
    private float moveRate = 1;

    private float lastMoved = 0;
    private float moveCooldown = 1;
    private float elapsedTime;

    private int lock = 1;
    private float timeMoving = 0;
    private float startingMovingTime = 0;
    private float playerCenterX, playerCenterY;

    //atributtes
    private float health = 10000;

    //bullets
    BulletPool bulletPool = new BulletPool(50);

    //booleans
    private boolean destroyed = false;






    private Random random = new Random();

    public void createBoss(String path){
        batch = new SpriteBatch();
        texture = new Texture(path);
        collider = new ShapeRenderer();
        bulletPool.createBulletPool("EnemyBullet.png", "EnemyBullet");
    }
    public void renderBoss(float playerCenterX, float playerCenterY){
        if(!destroyed){

        this.playerCenterX = playerCenterX;
        this.playerCenterY = playerCenterY;
        move();
        checkBounds();
        checkHealth();

        bulletPool.renderBulletPoolEnemy(positionX, positionY, 
		sizeX, sizeY, rotateToPlayer(this.playerCenterX, this.playerCenterY) - 90);

        batch.begin();
        batch.draw(texture, positionX, positionY, sizeX / 2, sizeY / 2, sizeX,
        sizeY, 1f, 1f, rotateToPlayer(this.playerCenterX, this.playerCenterY), 0, 0, texture.getWidth(),
        texture.getHeight(), false, false);
        batch.end();

        Polygon collision = getCollider(); // Update the collider's position and rotation
        drawCollider(collision);

        // collider.begin(ShapeRenderer.ShapeType.Line);
        // collider.identity(); // Reset the transformation matrix
        // collider.translate(positionX + sizeX / 2, positionY + sizeY / 2, 0); // Translate to the center
        // collider.rotate(0, 0, 1, rotateToPlayer(playerPositionX, playerPositionY)); // Rotate around the center
        // collider.rect(-sizeX / 2, -sizeY / 2, sizeX, sizeY);
        // collider.end();}
        }

    }
    public void disposeBoss(){
        texture.dispose();
        batch.dispose();
        collider.dispose();
        bulletPool.disposeBulletPool();
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

    public float rotateToPlayer(float playerCenterX, float playerCenterY){
        float angleRad = (float) Math.atan2(playerCenterY- (positionY+(sizeY/2)), playerCenterX- (positionX + (sizeX/2))); //gets the rad angles
        float angleDeg = (float) Math.toDegrees(angleRad); //converts it to degrees
        return angleDeg + 90;
    }

    public void setHealth(float damage){
        health -= damage;
    }

    public void checkHealth() {
        if (health <= 0) {
            destroyed = true;
        }
    }

    public boolean getDestroyed() {return destroyed;}



    private void drawCollider(Polygon polygon) {
        float[] vertices = polygon.getTransformedVertices();
      
        collider.begin(ShapeRenderer.ShapeType.Line);
        collider.polygon(vertices);
        collider.end();
    }

    public Polygon getCollider() { //I found the solution in a forum, I don`t really understand this one
        Polygon polygon = new Polygon();
        float[] vertices = new float[] { 
            0, 0,                           // bottom-left
            sizeX, 0,                       // bottom-right
            sizeX, sizeY,                   // top-right
            0, sizeY                        // top-left
        };
        polygon.setVertices(vertices);
        polygon.setOrigin(sizeX / 2, sizeY / 2); // Set the origin to the center of the polygon
        polygon.setPosition(positionX, positionY); // Set the position of the polygon
        polygon.setRotation(rotateToPlayer(playerCenterX, playerCenterY));
       
        return polygon;
    }

    public void checkPlayerCollision(Player player){
        bulletPool.checkPlayerCollision(player);
    }
}
