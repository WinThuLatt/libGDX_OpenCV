package com.libgdxopencv.Utilities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class TextureButton extends Actor
{
    TextureAtlas.AtlasRegion normal;
    TextureAtlas.AtlasRegion pressed;

    CustomInputListener inputListener;

    float x = 0, y = 0;

    public TextureButton(TextureAtlas.AtlasRegion normal, TextureAtlas.AtlasRegion pressed, float x, float y, CustomInputListener IL)
    {
        this.normal = normal;
        this.pressed = pressed;
        this.x = x;
        this.y = y;
        setBounds(x, y, normal.getRegionWidth(), normal.getRegionHeight());
        inputListener = IL;
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        batch.draw(inputListener.isPressed()? normal:pressed, x, y);
    }


}

