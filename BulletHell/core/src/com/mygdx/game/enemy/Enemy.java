package com.mygdx.game.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.mygdx.game.bullets.BulletPool;
import com.mygdx.game.bullets.Shootable;
import com.mygdx.game.player.Player;

import java.util.Random;

public class Enemy implements Shootable {
    protected SpriteBatch batch;
    protected Texture texture;
    protected ShapeRenderer collider;

    // attributes
    protected int sizeX, sizeY;
    protected float positionX, positionY;
    protected float speedX, speedY;
    protected float health;
    protected boolean isAlive;

    protected float damage;

    protected float randomX, randomY;
    protected float stationaryTime = 1;

    protected float lastMoved = 0;
    protected float moveDuration = 1;
    protected float elapsedTime;

    protected int lock = 1;
    protected float timeMoving = 0;
    protected float startingMovingTime = 0;
    protected float playerCenterX, playerCenterY;

    protected BulletPool bulletPool = new BulletPool(50);

    protected Random random = new Random();


    public Enemy(float positionX, float positionY, float speedX, float speedY, float health, String bulletImg, 
    String bulletType, String sprite) {
        sizeX = 64;
        sizeY = 64;
        damage = 100;
        this.positionX = positionX;
        this.positionY = positionY;
        this.speedX = speedX;
        this.speedY = speedY;
        this.health = health;
        isAlive = true;

        texture = new Texture(sprite);
        batch = new SpriteBatch();

        collider = new ShapeRenderer();
        bulletPool.createBulletPool(bulletImg, bulletType);
    }

    public void render(float playerCenterX, float playerCenterY){
        if(isAlive){

        this.playerCenterX = playerCenterX;
        this.playerCenterY = playerCenterY;
        randomMove();
        checkBounds();
        checkHealth();

        bulletPool.renderBulletPoolEnemy(positionX, positionY, 
		sizeX, sizeY, rotateToPlayer(this.playerCenterX, this.playerCenterY) - 90);

        batch.begin();
        batch.draw(texture, positionX, positionY, sizeX / 2, sizeY / 2, sizeX,
        sizeY, 1f, 1f, rotateToPlayer(this.playerCenterX, this.playerCenterY), 0, 0, texture.getWidth(),
        texture.getHeight(), false, false);
        batch.end();

        // Update the collider's position and rotation
        drawCollider(getCollider());

        }

    }


    protected void checkBounds(){ //checks if the player still on bounds
        if (positionX < 0) positionX = 0;
        if (positionY < 0) positionY = 0;
        if (positionX > Gdx.graphics.getWidth() - sizeX) positionX = Gdx.graphics.getWidth() - sizeX;
        if (positionY > Gdx.graphics.getHeight() - sizeY) positionY = Gdx.graphics.getHeight() - sizeY;

    }


    protected void randomMove() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        positionX += deltaTime * speedX * 1;
        positionY += deltaTime * speedY * 1;
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

    protected void drawCollider(Polygon polygon) {
        float[] vertices = polygon.getTransformedVertices();
      
        collider.begin(ShapeRenderer.ShapeType.Line);
        collider.polygon(vertices);
        collider.end();
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
            isAlive = false;
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void checkCollision(Shootable shootable){
        if(shootable.isAlive() && isAlive) { //don't move this please, I feel that with a very bad luck the player could die if I don't check the health
            bulletPool.checkCollision(shootable, this.damage);
            if (Intersector.overlapConvexPolygons(getCollider(), shootable.getCollider())) {
                shootable.setHealth(100);
            }
        }
    }


    public void dispose() {
        texture.dispose();
        batch.dispose();
        collider.dispose();
        bulletPool.disposeBulletPool();
    }

    public float getPositionX() {
        return positionX;
    }
    public float getPositionY() {
        return positionY;
    }



}
