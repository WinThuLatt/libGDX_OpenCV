package com.libgdxopencv;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.libgdxopencv.Screens.LoadingScreen;
import com.libgdxopencv.Utilities.AndroidUICallBack;

public class libGDXOpenCV_Main extends Game
{

    private SpriteBatch batch;
    private TextureAtlas atlas;
    private AssetManager manager;

    public final static int WIDTH = 360;
    public final static int HEIGHT = 500;

    public AndroidUICallBack UI_CALLBACK;

    public libGDXOpenCV_Main(AndroidUICallBack iuiCallbacks)
        {
            this.UI_CALLBACK = iuiCallbacks;
        }

    @Override
    public void create()
    {
	batch = new SpriteBatch();
	manager = new AssetManager();
	this.setScreen(new LoadingScreen(this));
    }

    @Override
    public void render()
    {
	super.render();
    }

    @Override
    public void dispose()
    {
	batch.dispose();
    }

    public SpriteBatch getBatch()
    {
	return batch;
    }

    public TextureAtlas getAtlas()
    {
	return atlas;
    }

    public void setAtlas(TextureAtlas atlas)
    {
	this.atlas = atlas;
    }

    public AssetManager getManager()
    {
	return manager;
    }

}
