package com.libgdxopencv.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.libgdxopencv.libGDXOpenCV_Main;
import com.libgdxopencv.Utilities.Controller;
import com.libgdxopencv.Utilities.HUD;

/**
 * Created by my on 10/21/2016.
 */

public class GameScreen implements Screen
{
    private libGDXOpenCV_Main app;
    private HUD hud;
    private Controller controller;
    private Viewport viewport;
    private OrthographicCamera camera;

    public GameScreen(libGDXOpenCV_Main app)
    {
	this.app = app;
	hud = new HUD(app);
	controller = new Controller(app);

	camera = new OrthographicCamera();
	camera.setToOrtho(false, libGDXOpenCV_Main.WIDTH, libGDXOpenCV_Main.HEIGHT);
	viewport = new FitViewport(libGDXOpenCV_Main.WIDTH, libGDXOpenCV_Main.HEIGHT);

    }

    @Override
    public void show()
    {

    }

    public void update()
    {
	if (controller.isRightPressed())
	{
	    app.UI_CALLBACK.ToastMessage("HI");
	}
    }

    @Override
    public void render(float delta)
    {
	Gdx.gl.glClearColor(0, 0, 0, 0);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	update();
	hud.draw();
	controller.draw();
    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose()
    {
	hud.dispose();
    }
}
