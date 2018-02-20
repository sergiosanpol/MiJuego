package com.pmdm.mijuego;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MiJuego extends ApplicationAdapter implements InputProcessor{
	SpriteBatch batch;
	Texture img;
	//Objeto que recoge el mapa de baldosas
	private TiledMap map;
	//Objeto con el wue se pinta el mapa de baldosas
	private TiledMapRenderer mapRenderer;
	//Cámara que nos da la vista del juego
	private OrthographicCamera orthographicCamera;

	
	@Override
	public void create () {
		//Inicializamos la camara
		float anchura = Gdx.graphics.getWidth();
		float altura = Gdx.graphics.getHeight();

		//Creamos una camara y la vinculamos con el lienzo del juego
		//En este caso le damos unos valores de tamaño que haga que el juego
		//se muestre de forma idéntica en todas las plataformas
		orthographicCamera = new OrthographicCamera(800, 480);
		//Posicionamos la vista de la cámara para que su vétice inferior izquierdo sea (0,0)
		orthographicCamera.position.set(orthographicCamera.viewportWidth / 2f, orthographicCamera.viewportHeight / 2f, 0);
		orthographicCamera.update();

		// Indicamos que los eventos de entrada sean procesados por esta clase.
		Gdx.input.setInputProcessor(this);

		//Cargamos el mapa de baldosas desde la carpeta de assets
		map = new TmxMapLoader().load("escenario.tmx");
		mapRenderer = new OrthogonalTiledMapRenderer(map);
	}

	@Override
	public void render () {
		//Color de fondo a negro
		Gdx.gl.glClearColor(0, 0, 0, 1);
		//Borramos la pantalla
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Actualizamos la cámara del juego
		orthographicCamera.update();
		//Vinculamos el objeto de dibuja el TiledMap con la cámara del juego
		mapRenderer.setView(orthographicCamera);
		//Dibujamos el tiledMap
		mapRenderer.render();

		/*
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();*/
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		//Si se pulsa una tecla del cursor
		if(keycode == Input.Keys.LEFT){
			orthographicCamera.translate(-32f, 0f);
		}

		if(keycode == Input.Keys.RIGHT){
			orthographicCamera.translate(32f, 0f);
		}

		if(keycode == Input.Keys.UP){
			orthographicCamera.translate(0f, 32f);
		}

		if(keycode == Input.Keys.DOWN){
			orthographicCamera.translate(0f, -32f);
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		float incrementox = 0,  incrementoy = 0;
		//Si tocamos a la derecha de la pantalla
		if(orthographicCamera.position.x < screenX){
			incrementox = 32f;
		//Si tocamos a la izquierda de la pantalla
		}else if(orthographicCamera.position.x > screenX){
			incrementox = -32f;
		}

		//Si tocamos en la parte superior de la pantalla
		if(orthographicCamera.position.y < screenY){
			incrementoy = 32f;
		//Si tocamos en la parte inferior de la pantalla
		}else if(orthographicCamera.position.y > screenY){
			incrementoy = -32f;
		}

		orthographicCamera.translate(incrementox, incrementoy);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
