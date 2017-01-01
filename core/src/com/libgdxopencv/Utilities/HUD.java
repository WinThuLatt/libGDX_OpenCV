package com.libgdxopencv.Utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.libgdxopencv.libGDXOpenCV_Main;

/**
 * Created by my on 10/21/2016.
 */

public class HUD implements Disposable
{
    libGDXOpenCV_Main app;
    Viewport viewport;
    OrthographicCamera camera;
    BitmapFont font;

    public HUD(libGDXOpenCV_Main app)
    {
	camera = new OrthographicCamera();
	camera.setToOrtho(false, libGDXOpenCV_Main.WIDTH, libGDXOpenCV_Main.HEIGHT);
	viewport = new FitViewport(libGDXOpenCV_Main.WIDTH, libGDXOpenCV_Main.HEIGHT, camera);
	this.app = app;

	font = new BitmapFont(Gdx.files.internal("fonts/cool.fnt"));
    }

    public void draw()
    {
	app.getBatch().setProjectionMatrix(viewport.getCamera().combined);
	app.getBatch().begin();
	font.draw(app.getBatch(), "Some Information", 0, 500);
	app.getBatch().end();
    }

    @Override
    public void dispose()
    {
	font.dispose();
    }
}
