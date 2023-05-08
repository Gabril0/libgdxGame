package com.mygdxt.trabalho;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class Ground {
    private float spriteSizeX = 32, spriteSizeY = 32;
    private float spritePositionX, spritePositionY;
    private Rectangle colliderBottom, colliderTop, colliderSide;
    private ShapeRenderer shapeRenderer;
	private Texture sprite;
	private SpriteBatch batch;
    private String imgPath;

    Ground(float spritePositionX, float spritePositionY, String imgPath){
        this.spritePositionX = spritePositionX;
        this.spritePositionY = spritePositionY;
        this.imgPath = imgPath;
    }
    
    public void createGround(){
        shapeRenderer = new ShapeRenderer(); //Creates a shape renderer object
		batch = new SpriteBatch(); //Creates a batch
		sprite = new Texture(Gdx.files.internal(imgPath)); //Creates and gets ground sprite png
        
    }

    public void renderGround(){
        batch.begin();
        batch.draw(sprite,spritePositionX,spritePositionY,spriteSizeX,spriteSizeY);
        batch.end();
        colliderBottom = new Rectangle(spritePositionX,spritePositionY,spriteSizeX,1);
        colliderSide = new Rectangle(spritePositionX,spritePositionY + 1,spriteSizeX,spriteSizeY - 2);
        colliderTop = new Rectangle(spritePositionX,spritePositionY + (spriteSizeY-1) ,spriteSizeX,1);
        // shapeRenderer.begin(ShapeType.Filled); //HERE TO DEBUG HITBOXES
        // shapeRenderer.setColor(Color.RED);
        // shapeRenderer.rect(spritePositionX,spritePositionY + 31 ,spriteSizeX,1);
        // shapeRenderer.end();
    }

    public void disposeGround(){
        batch.dispose();
		sprite.dispose();
		shapeRenderer.dispose();
    }

    
	public Rectangle getColliderBottom(){
		return this.colliderBottom;
	}

    public Rectangle getColliderSide() {
        return colliderSide;
    }

    public Rectangle getColliderTop() {
        return colliderTop;
    }

    public float getSpritePositionX(){
        return spritePositionX;
    }
    public float getSpritePositionY(){
        return spritePositionX;
    }

}
