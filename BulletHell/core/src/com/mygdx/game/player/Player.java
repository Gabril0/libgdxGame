package com.mygdx.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Input;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.mygdx.game.animation.*;
import com.mygdx.game.bullets.Bullet;
import com.mygdx.game.bullets.BulletPool;
import com.mygdx.game.bullets.Shootable;
import com.mygdx.game.evolution.Evolution;
import com.mygdx.game.evolution.StoredEnergy;
import com.mygdx.game.listeners.EventManager;
import com.mygdx.game.listeners.Listener;
import com.mygdx.game.uirelated.HealthBar;

public class Player implements Listener, Shootable {

    private Evolution evolution;

    //For conversion between different screen sizes
    private float width;
	private float height;
    
    // Player atributes
    private float health = 640;
    private float damage = 100;
    private String tag = "player";

    // Base sprite rendering variables
    private float deltaTime;
    private SpriteBatch batch;
    private Texture img, transformImg;
    //private ShapeRenderer collision;
    private HealthBar healthBar = new HealthBar();

    // Movement and sizing
    private float spriteSizeX, spriteSizeY; // float for the size
    private float spritePositionX , spritePositionY = height/2; // float for the position
    private float speedX = 450f, speedY = 450f; // float for the speed
    private float originalSpeed = 450f;

    private float centerX, centerY;
    private float transformTime = -10;

    private BulletPool bulletPool = new BulletPool(200);

    // Animations
    private EventManager em = new EventManager();


    private Animation shootingAnimation;
    private Animation transitionAnimation;
    private Animation transformShooting;
    private Animation transformationIdle;
    private Animation backToNormalAnimation;
    protected Animation explosion;


    // Booleans
    private boolean isShooting;
    private boolean isAlive = true;

    private boolean gotHit = false;
    private boolean isTransformed = false;
    private boolean transformationFlag = false; // checks if the player has already transformed
    protected boolean explosionLock = true;
    private boolean canShoot = true;
    private boolean canSlowdown = false;



    // Timing
    private float timeHit = 0; // initializing to not lock
    private float damageCooldown = 0.5f; // prevents the player to get hit after just getting hit
    private float currentTime;

    public void createPlayer() { // do these actions once the game starts
        width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();



        //atributes
        spriteSizeX = width/25;
        spriteSizeY = width/25;
        spritePositionX = (width/2) - (spriteSizeX) ;
        spritePositionY = (height/2) - (spriteSizeY) ;


        // player rendering
        batch = new SpriteBatch();

        img = new Texture("playerAnimation/PlayerBaseSprite.png");
        transformImg = new Texture("playerAnimation/PlayerTransformation.png");
        //collision = new ShapeRenderer();
        healthBar.createHealthBar();
        tag = "player";

        // shooting AND shooting animation
        bulletPool.createBulletPool("Bullets/PlayerBullet.png", "SimpleBullet", damage);
        em.addShotListener(this);

        shootingAnimation = new ShootingAnimation();
        transitionAnimation = new PlayerTransitionAnimation();
        transformShooting = new TransformShooting();
        transformationIdle = new TransformationIdle();
        explosion = new Explosion();
        backToNormalAnimation = new BackToNormal();

        backToNormalAnimation.create();
        shootingAnimation.create();
        transitionAnimation.create();
        transformShooting.create();
        transformationIdle.create();
        explosion.create();

    }

    public void renderPlayer() { // do these actions every frame
        if(!isAlive){
            batch.begin();
            batch.setColor(Color.WHITE);

            if(explosionLock){
                explosion.render(spritePositionX - spriteSizeX, spritePositionY - spriteSizeY, spriteSizeX*3,
                        spriteSizeY*3, 0, batch);

                if(explosion.getWasFinished()){
                    explosionLock = false;

                }
            }
            batch.end();
        }
        if (isAlive) {
            deltaTime = Gdx.graphics.getDeltaTime();
            currentTime += deltaTime;
            checkHealth();
            movePlayer();
            if(canShoot) shoot();
            if(canSlowdown) slowDown();
            checkBounds();
            drawPlayer();

            // drawCollider();
            healthBar.renderHealthBar(this);
        }
    }

    public void disposePlayer() { // dispose of the player resources onde the game is closed
        batch.dispose();
        img.dispose();
        shootingAnimation.dispose();
        bulletPool.disposeBulletPool();
        healthBar.disposeHealthBar();
        transitionAnimation.dispose();
        explosion.dispose();
    }

    private void shoot() {
        //if(currentTime > transformTime + 10) {
            if (Gdx.input.isKeyPressed(Input.Keys.F)) {
                //transformTime = currentTime + 2;
                isTransformed = true;
            }
        //}
        bulletPool.renderBulletPoolPlayer(getSpritePositionX(), getSpritePositionY(),
                getSpriteSizeX(), getSpriteSizeY(), rotateToCursor() - 90,damage, em); // -op because bullets are in a diferent
                                                                                // orientation
    }

    private void movePlayer() {

        spritePositionX += deltaTime * speedX * horizontalMovement();
        spritePositionY += deltaTime * speedY * verticalMovement();
    }

    private void drawPlayer() { // Draws the player sprite each frame
        batch.begin();

        if(isTransformed) {
            drawTransformedPlayer();
        }
        else {
            if (isShooting) { // shooting animation
                shootingAnimation.render(spritePositionX, spritePositionY, spriteSizeX,
                        spriteSizeY, rotateToCursor(), batch);

            } else { // idle
                batch.draw(img, spritePositionX, spritePositionY, spriteSizeX / 2, spriteSizeY / 2, spriteSizeX,
                        spriteSizeY, 1f, 1f, rotateToCursor(), 0, 0, img.getWidth(),
                        img.getHeight(), false, false); // Giant constructor because the framework doesn`t accept it any
                // shorter
            }

        }
        batch.end();

    }
    private void drawTransformedPlayer() { // Draws the player sprite each frame
//this part makes the player transform back to normal
//        if(currentTime > transformTime + 20){
//            backToNormalAnimation.render(spritePositionX, spritePositionY, spriteSizeX, spriteSizeY, rotateToCursor(), batch);
//            canShoot = false;
//            if (backToNormalAnimation.getWasFinished()) {
//                damage = damage / 0.8f;
//                bulletPool.setBulletType("PlayerBullet", "Bullets/PlayerBullet.png");
//                setShootingRate((1 / 0.75f));
//                transformationFlag = false;
//                transformTime = currentTime;
//                canShoot = true;
//                isTransformed = false;
//            }
//        }
        if (isShooting && transformationFlag ) {
            // Shooting animation
            transformShooting.render(spritePositionX, spritePositionY, spriteSizeX, spriteSizeY, rotateToCursor(), batch);
        } else if (!transformationFlag) {
            transitionAnimation.render(spritePositionX, spritePositionY, spriteSizeX, spriteSizeY, rotateToCursor(), batch);
            canShoot = false;
            if (transitionAnimation.getWasFinished()) {
                // Changes player by transforming them
                transformTime = currentTime;
                damage = damage * 1.2f;
                bulletPool.setBulletType("TransformationBullet", "Bullets/TransformationBullet.png");
                setShootingRate(1.25f);
                transformationFlag = true;
                canShoot = true;
            }
        } else {
            // Default transformation animation
            transformationIdle.render(spritePositionX, spritePositionY, spriteSizeX, spriteSizeY, rotateToCursor(), batch);
        }


    }

    private void checkBounds() { // checks if the player still on bounds
        if (spritePositionX < 0)
            spritePositionX = 0;
        if (spritePositionY < 0)
            spritePositionY = 0;
        if (spritePositionX > Gdx.graphics.getWidth() - spriteSizeX)
            spritePositionX = Gdx.graphics.getWidth() - spriteSizeX;
        if (spritePositionY > Gdx.graphics.getHeight() - spriteSizeY)
            spritePositionY = Gdx.graphics.getHeight() - spriteSizeY;

    }

    private int horizontalMovement() {
        boolean left = Gdx.input.isKeyPressed(Input.Keys.A); // gets the input for left movement
        boolean right = Gdx.input.isKeyPressed(Input.Keys.D); // gets the input for right movement
        // atributions
        if (left && !right)
            return -1;
        if (!left && right)
            return 1;
        return 0;

    }

    private int verticalMovement() {
        boolean up = Gdx.input.isKeyPressed(Input.Keys.W); // gets the input for up movement
        boolean down = Gdx.input.isKeyPressed(Input.Keys.S); // gets the input for down movement
        // atributions
        if (up && !down)
            return 1;
        if (!up && down)
            return -1;
        return 0;

    }

    public float getSpritePositionX() {
        return spritePositionX;
    }

    public float getSpritePositionY() {
        return spritePositionY;
    }

    public float getSpriteSizeX() {
        return spriteSizeX;
    }

    public float getSpriteSizeY() {
        return spriteSizeY;
    }

    public float rotateToCursor() { // Rotates player sprite to the cursor position
        float mouseX = Gdx.input.getX(); // gets mouse position X
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY(); // gets mouse position Y
        // The getHeight is because libgdx uses bottomLeft starting position, so this is
        // a conversion to their system

        float angleRad = (float) Math.atan2(mouseY - (spritePositionY + (spriteSizeY / 2)),
                mouseX - (spritePositionX + (spriteSizeX / 2))); // gets the rad angles
        float angleDeg = (float) Math.toDegrees(angleRad); // converts it to degrees
        return angleDeg + 90;
    }

    @Override
    public void onShoot(boolean isShooting) {
        this.isShooting = isShooting;
    }

    // private void drawCollider() {
    //     Polygon collider = getCollider();
    //     float[] vertices = collider.getTransformedVertices();

    //     collision.begin(ShapeRenderer.ShapeType.Line);
    //     collision.polygon(vertices);
    //     collision.end();
    // }

    public Polygon getCollider() {
        Polygon polygon = new Polygon();
        float centerX = spritePositionX + spriteSizeX / 2;
        float centerY = spritePositionY + spriteSizeY / 2;
        float halfSizeX = spriteSizeX / 4; // Calculate half of the width (divide by 4 instead of 2)
        float halfSizeY = spriteSizeY / 4; // Calculate half of the height (divide by 4 instead of 2)
        float[] vertices = new float[] {
                centerX - halfSizeX, centerY - halfSizeY, // bottom-left
                centerX + halfSizeX, centerY - halfSizeY, // bottom-right
                centerX + halfSizeX, centerY + halfSizeY, // top-right
                centerX - halfSizeX, centerY + halfSizeY // top-left
        };
        polygon.setVertices(vertices);
        polygon.setOrigin(centerX, centerY);
        polygon.setRotation(rotateToCursor());
        return polygon;
    }

    public void checkCollision(Shootable shootable) {
        if (shootable.isAlive() && isAlive) { // don't move this please, I feel that with a very bad luck the player
                                              // could die if I don't check the health
            bulletPool.checkCollision(shootable, this.damage);
            if (Intersector.overlapConvexPolygons(getCollider(), shootable.getCollider())) {
                if (shootable.getTag().compareTo("enemy") == 0) {
                    setHealth(shootable.getDamage() * 2);
                } 
                else shootable.setHealth(shootable.getDamage());
            }
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setHealth(float damage) {
        if (timeHit < currentTime - damageCooldown) {
            health = health - damage;
            gotHit = true;
            timeHit = currentTime;
        }
    }

    public void checkHealth() {
        if (health <= 0) {
            isAlive = false;
        }
    }

    public void slowDown(){
        if(isShooting){
            speedX = originalSpeed / 2;
            speedY = originalSpeed / 2;

        }
        else{
            speedX = originalSpeed;
            speedY = originalSpeed;
        }
    }

    public void goToPoint(float x, float y){
        if(spritePositionX > x) spritePositionX += -1 * 200 * deltaTime;
        if(spritePositionX < x) spritePositionX += 1 * 200   * deltaTime;
        if(spritePositionY > y) spritePositionY += -1 * 200   * deltaTime;
        if(spritePositionY < y) spritePositionY += 1 * 200   * deltaTime;
    }


    public float getCenterX() {
        centerX = spritePositionX + (spriteSizeX / 2);
        return centerX;
    }

    public float getCenterY() {
        centerY = spritePositionY + (spriteSizeY / 2);
        return centerY;
    }

    public boolean gotHit() {
        return gotHit;
    }

    public void setGotHit() {
        gotHit = false;
    }

    public float getHealth() {
        return health;
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = this.damage * damage;
    }

    public void setShootingRate(float fireRate){
        bulletPool.setCoolDown(bulletPool.getCoolDown() * fireRate);
    }
    public void setBulletType(String bulletType, String imgPath){
        bulletPool.setBulletType(bulletType, imgPath);
    }
    public Bullet getBullet(){
        return bulletPool.getBullet();
    }
    public void setSpeed(float speed){
        speedY = speedY * speed;
        speedX = speedX * speed;
        originalSpeed = originalSpeed * speed;
    }
    public void setLife(float percentage){ //alternative to setHealth to adjust the life instead of just damage
        health = health * percentage;

    }

    public void setCanSlowdown(boolean bool){
        canSlowdown = bool;
    }
}