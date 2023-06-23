package com.mygdx.game.backgroundAndCursor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;

public class CustomCursor {
    private Cursor customCursor;

    public void create() {
        Pixmap cursorPixmap = new Pixmap(Gdx.files.internal("UI/cursor.png")); //create a cursor
        
        int hotspotX = cursorPixmap.getWidth() / 2;
        int hotspotY = cursorPixmap.getHeight() / 2;
        
        customCursor = Gdx.graphics.newCursor(cursorPixmap, hotspotX, hotspotY);
        
        cursorPixmap.dispose(); //it`s no longer needed cause it`s assigned once
        
        Gdx.graphics.setCursor(customCursor); //set the cursor png
    }
    //render is not needed
    public void dispose() {
        customCursor.dispose();
    }
}
