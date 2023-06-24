package com.mygdx.game.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.mygdx.game.animation.Animation;
import com.mygdx.game.animation.Explosion;
import com.mygdx.game.animation.IntroAnimation;
import com.mygdx.game.bullets.BulletPool;
import com.mygdx.game.bullets.Shootable;
import com.mygdx.game.uirelated.EnemyHealthBar;

import java.util.Random;

public class Enemy implements Shootable {

    //For conversion between different screen sizes
    private float width;
    //private float height;

    protected SpriteBatch batch;
    protected Texture texture;
    protected ShapeRenderer collider;
    protected EnemyHealthBar healthBar = new EnemyHealthBar();
    protected String tag;

    // attributes
    protected float sizeX, sizeY;
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

    protected float hitAnimationDuration = 0.05f;
    protected float hitCurrentTime = 0f;

    protected boolean isHit = false;
    protected boolean explosionLock = true;

    protected Animation explosion;
    protected Animation intro;

    protected boolean startupAnimation = true;


    public Enemy(float positionX, float positionY, float speedX, float speedY, float health, String bulletImg, 
    String bulletType, String sprite) {
        width = Gdx.graphics.getWidth();
		//float height = Gdx.graphics.getHeight();

        //atributes
        sizeX = width/25;
        sizeY = width/25;
        damage = 100;
        this.positionX = positionX;
        this.positionY = positionY;
        this.speedX = speedX;
        this.speedY = speedY;
        this.health = health;
        isAlive = true;
        tag = "enemy";

        texture = new Texture(sprite);
        batch = new SpriteBatch();
        healthBar.createHealthBar();

        collider = new ShapeRenderer();
        bulletPool.createBulletPool(bulletImg, bulletType, damage);

        bulletPool.setCoolDown(0.5f);

        explosion = new Explosion();
        explosion.create();

        intro = new IntroAnimation();
        intro.create();
    }

    // template method was applied here so all subclasses can change its algorithm without messing things up
    public void render(float playerCenterX, float playerCenterY){
        float deltaTime = Gdx.graphics.getDeltaTime();
        batch.begin();
        if(startupAnimation){
            batch.setColor(Color.RED);
                intro.render(positionX - sizeX, positionY - sizeY, sizeX * 3, sizeY * 3,
                        0, batch);

                if (intro.getWasFinished()) {
                    batch.setColor(Color.WHITE);
                    startupAnimation = false;
                }
            }
        else {
            if (!isAlive) {
                batch.setColor(Color.WHITE);

                if (explosionLock) {
                    explosion.render(positionX - sizeX, positionY - sizeY, sizeX * 3, sizeY * 3,
                            0, batch);

                    if (explosion.getWasFinished()) {
                        explosionLock = false;
                    }
                }
            }

            if (isAlive) {

                this.playerCenterX = playerCenterX;
                this.playerCenterY = playerCenterY;
                applyMovement();
                checkBounds();
                checkHealth();

                shot();

                renderVariations();
                if (isHit)
                    gotHitAnimation(deltaTime);
                healthBar.renderHealthBar(this);

            }
        }
        batch.end();

    }


    protected void checkBounds(){ //checks if the enemy still on bounds
        if (positionX < 0) positionX = 0;
        if (positionY < 0) positionY = 0;
        if (positionX > Gdx.graphics.getWidth() - sizeX) positionX = Gdx.graphics.getWidth() - sizeX;
        if (positionY > Gdx.graphics.getHeight() - sizeY) positionY = Gdx.graphics.getHeight() - sizeY;

    }


    protected void move(float moveDuration, float stationaryTime) {
        float deltaTime = Gdx.graphics.getDeltaTime();
        elapsedTime += deltaTime;
        if (elapsedTime >= lastMoved + moveDuration) {
            if(lock == 1){
                randomX = moveRandomPosition();
                randomY = moveRandomPosition();
                lock = 0;
                startingMovingTime = elapsedTime;
            }
            else {
                // decides the signal of the random number, so it knows where to go
                positionX += speedX * deltaTime * Math.signum(randomX);
                positionY += speedY * deltaTime * Math.signum(randomY);
                timeMoving = elapsedTime - startingMovingTime;
            }
            if(timeMoving > stationaryTime){ //condition to stop enemy stuck
                timeMoving = 0;
                lock = 1;
                lastMoved = elapsedTime ;
            }
        }
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
        isHit = true;
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

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public void checkCollision(Shootable shootable){
        if(shootable.isAlive() && isAlive) { //don't move this please, I feel that with a very bad luck the player could die if I don't check the health
            bulletPool.checkCollision(shootable, this.damage);
            if (Intersector.overlapConvexPolygons(getCollider(), shootable.getCollider())) {
                if (shootable.getTag().compareTo("enemy") != 0) {
                    shootable.setHealth(shootable.getDamage());
                } 
            }
        }
    }

    public void gotHitAnimation(float deltaTime) {        
        hitCurrentTime += deltaTime;
    
        // Flash the sprite red if the enemy is hit
        if (hitCurrentTime <= hitAnimationDuration) {
            batch.setColor(Color.RED);
        } else {
            batch.setColor(Color.WHITE);
            hitCurrentTime = 0;
            isHit = false;
        }
    }

    public void dispose() {
        texture.dispose();
        batch.dispose();
        collider.dispose();
        bulletPool.disposeBulletPool();
        healthBar.disposeHealthBar();
    }

    public float getPositionX() {
        return positionX;
    }
    public float getPositionY() {
        return positionY;
    }
    public float getHealth() {
        return health;
    }
    public float getSizeY() {
        return sizeY;
    }
    public float getSizeX() {
        return sizeX;
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public float getDamage() {
        return damage;
    }

    private float moveRandomPosition(){
        return (-1 + random.nextInt(3));
    }

    // simply a capsule for the move call, so other enemies can change the parameters and not
    // affect the subclasses
    protected void applyMovement() {
        move(3, 1);
    }
    // a capsule for the different types of bullet
    protected void shot(){
        bulletPool.renderBulletPoolEnemy(positionX, positionY, 
            sizeX, sizeY, rotateToPlayer(this.playerCenterX, this.playerCenterY) - 90, damage);
    }
    // a capsule for the different types os rendering
    protected void renderVariations(){
        batch.draw(texture, positionX, positionY, sizeX / 2, sizeY / 2, sizeX,
            sizeY, 1f, 1f, rotateToPlayer(this.playerCenterX, this.playerCenterY), 0, 0, texture.getWidth(),
            texture.getHeight(), false, false);
    }

}
