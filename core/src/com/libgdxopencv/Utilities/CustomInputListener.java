package com.libgdxopencv.Utilities;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by my on 10/22/2016.
 */

public class CustomInputListener extends InputListener
{
    public boolean isPressed()
    {
        return pressed;
    }

    public void setPressed(boolean pressed)
    {
        this.pressed = pressed;
    }

    private boolean pressed = false;

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
    {
        return super.touchDown(event, x, y, pointer, button);
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button)
    {
        super.touchUp(event, x, y, pointer, button);
    }
}
