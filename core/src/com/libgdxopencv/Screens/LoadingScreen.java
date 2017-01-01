package com.libgdxopencv.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.libgdxopencv.libGDXOpenCV_Main;

public class LoadingScreen implements Screen
{
    libGDXOpenCV_Main app;

    private float time = 0f;
    private BitmapFont font;

    private Animation sharkAnimation;
    private TextureRegion[][] sharkTmp;
    private TextureRegion[] sharkAnimationFrames;

    private OrthographicCamera camera;
    private Viewport viewport;
    private Sound soundRaindrop;
    private Texture rainDrop1;
    private Texture rainDrop2;
    private Vector2 r1, r2;
    private boolean drawR1 = true, drawR2 = true;

    public LoadingScreen(libGDXOpenCV_Main app)
    {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, libGDXOpenCV_Main.WIDTH, libGDXOpenCV_Main.HEIGHT);
        viewport = new FitViewport(libGDXOpenCV_Main.WIDTH, libGDXOpenCV_Main.HEIGHT, camera);

        this.app = app;
        app.getManager().load("graphics/pack.atlas", TextureAtlas.class);
        font = new BitmapFont(Gdx.files.internal("fonts/cool.fnt"));

        soundRaindrop = Gdx.audio.newSound(Gdx.files.internal("music/raindropsound1.mp3"));

        rainDrop1 = new Texture(Gdx.files.internal("graphics/RainDrop.png"));
        rainDrop2 = new Texture(Gdx.files.internal("graphics/RainDrop2.png"));


        r1 = new Vector2(MathUtils.random(libGDXOpenCV_Main.WIDTH - rainDrop1.getWidth()), MathUtils.random(libGDXOpenCV_Main.HEIGHT));
        r2 = new Vector2(MathUtils.random(libGDXOpenCV_Main.WIDTH - rainDrop2.getWidth()), MathUtils.random(libGDXOpenCV_Main.HEIGHT));
    }

    @Override
    public void show()
    {
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (app.getManager().update())
        {
            app.setAtlas((TextureAtlas) app.getManager().get("graphics/pack.atlas"));
            app.setScreen(new GameScreen(app));
        }
        updateRainDropPositions();
        time += delta;

        app.getBatch().setProjectionMatrix(this.camera.combined);
        app.getBatch().begin();

        if (drawR1)
        {
            app.getBatch().draw(rainDrop1, r1.x, r1.y);
        }
        if (drawR2)
        {
            app.getBatch().draw(rainDrop2, r2.x, r2.y);
        }
        font.draw(app.getBatch(), "loading", libGDXOpenCV_Main.WIDTH / 2 - 48, 100);
        app.getBatch().end();
    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height);
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
    }

    private void updateRainDropPositions()
    {
        r1.add(0, -3f);
        r2.add(0, -3f);
        if (r1.y == 0)
        {
            soundRaindrop.play();
            drawR1 = false;
        }
        if (r2.y == 0)
        {
            soundRaindrop.play();
            drawR2 = false;
        }
    }

}
