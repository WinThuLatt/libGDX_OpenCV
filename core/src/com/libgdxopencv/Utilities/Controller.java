package com.libgdxopencv.Utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.libgdxopencv.libGDXOpenCV_Main;

/**
 * Created by my on 10/21/2016.
 */

public class Controller implements Disposable
{
    private Viewport viewport;
    private Stage stage;
    private boolean leftPressed;
    private float buttonHeight = 100;

    public boolean isLeftPressed()
    {
	return leftPressed;
    }

    public boolean isRightPressed()
    {
	return rightPressed;
    }

    private boolean rightPressed;
    private OrthographicCamera camera;
    private libGDXOpenCV_Main app;

    public Controller(libGDXOpenCV_Main app)
    {
	camera = new OrthographicCamera();
	camera.setToOrtho(false, libGDXOpenCV_Main.WIDTH, libGDXOpenCV_Main.HEIGHT);
	viewport = new FitViewport(libGDXOpenCV_Main.WIDTH, libGDXOpenCV_Main.HEIGHT, camera);
	stage = new Stage(viewport, app.getBatch());
	this.app = app;
	CustomInputListener leftInputListener = new CustomInputListener()
	{

	    @Override
	    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
	    {
		System.out.println("leftPressed");
		setPressed(true);
		leftPressed = true;
		return true;
	    }

	    @Override
	    public void touchUp(InputEvent event, float x, float y, int pointer, int button)
	    {
		setPressed(false);
		leftPressed = false;
	    }

	};

	CustomInputListener rightInputListener = new CustomInputListener()
	{
	    @Override
	    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
	    {
		System.out.println("rightPressed");
		setPressed(true);
		rightPressed = true;
		return true;
	    }

	    @Override
	    public void touchUp(InputEvent event, float x, float y, int pointer, int button)
	    {

		setPressed(false);
		rightPressed = false;
	    }
	};

	TextureButton left = new TextureButton(app.getAtlas().findRegion("left"),
		app.getAtlas().findRegion("leftPressed"), 0, buttonHeight, leftInputListener);
	TextureButton right = new TextureButton(app.getAtlas().findRegion("right"),
		app.getAtlas().findRegion("rightPressed"),
		libGDXOpenCV_Main.WIDTH - app.getAtlas().findRegion("right").getRegionWidth(), buttonHeight,
		rightInputListener);

	left.addListener(leftInputListener);
	right.addListener(rightInputListener);
	stage.addActor(left);
	stage.addActor(right);
	final libGDXOpenCV_Main temp = app;
	stage.addListener(new InputListener()
	{
	    @Override
	    public boolean keyDown(InputEvent event, int keycode)
	    {
		if (keycode == Input.Keys.MENU)
		{
		    System.out.println("MENU");
		    temp.UI_CALLBACK.ToastMessage("MENU");
		}
		return true;
	    }

	    @Override
	    public boolean keyUp(InputEvent event, int keycode)
	    {
		return super.keyUp(event, keycode);
	    }
	});

	Gdx.input.setCatchBackKey(true);
	Gdx.input.setCatchMenuKey(true);

	Gdx.input.setInputProcessor(stage);
    }

    public void draw()
    {
	stage.draw();
    }

    @Override
    public void dispose()
    {

    }
}
