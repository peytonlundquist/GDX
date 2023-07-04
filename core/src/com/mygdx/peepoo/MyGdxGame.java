package com.mygdx.peepoo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.peepoo.tiles.Decor;
import com.mygdx.peepoo.tiles.Door;

import javax.xml.datatype.Duration;
import java.awt.image.BufferedImage;
import java.math.BigInteger;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class MyGdxGame extends ApplicationAdapter {
	int mapX = 0;
	int mapY = 0;
	int step = 32;
	int playerX = 10;
	int playerY = 10;
	SpriteBatch batch;
	Player player;
	Map map;
	Map basicMap;

	TextureAtlas textureAtlas;
	Sprite dirt;

	@Override
	public void create () {
		batch = new SpriteBatch();
		textureAtlas = new TextureAtlas("sprites.txt");
		dirt = textureAtlas.createSprite("dirt");


		/* Make player */
		player = new Player(new Texture("player/pumkinRight.png"),
				new Texture("player/pumkinLeft.png"),
				new Texture("player/pumkinFront.png"),
				new Texture("player/pumkinBack.png"));
		player.setX(320);
		player.setY(337);
		player.setCurrentTexture(player.getFrontFacing());

//		680, 714
		//20, 21,

		/* Make map */
		basicMap = Maps.getBasicMap(textureAtlas);
		map = basicMap;
		player.setCurrentTile(map.tiles[playerX][playerY]);
		System.out.println(player.getCurrentTile().toString());
	}

	private void renderMap(Map map){
		for(int i = 0; i < map.width; i++){
			for(int j = 0; j < map.height; j++){
				map.tiles[i][j].sprite.setPosition((i * step) + (mapX*step), (j * step) + (mapY*step));
				map.tiles[i][j].sprite.draw(batch);
			}
		}
	}

	long millisDrag = 150;
	long startTime = System.currentTimeMillis();
	long tickTime = System.currentTimeMillis();
	long tickGoal = 500;

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 0);
		batch.begin();
		long endTime = System.currentTimeMillis();

		if(tickGoal + tickTime < endTime){
			//System.out.println("tick");
			renderMap(map);
			tickTime = System.currentTimeMillis();
		}else{
			renderMap(map);
		}

		/* Get Input */
		boolean keyA = Gdx.input.isKeyJustPressed(Input.Keys.A);
		boolean keyW = Gdx.input.isKeyJustPressed(Input.Keys.W);
		boolean keyS = Gdx.input.isKeyJustPressed(Input.Keys.S);
		boolean keyD = Gdx.input.isKeyJustPressed(Input.Keys.D);

		boolean keyAPressed = Gdx.input.isKeyPressed(Input.Keys.A);
		boolean keyWPressed = Gdx.input.isKeyPressed(Input.Keys.W);
		boolean keySPressed = Gdx.input.isKeyPressed(Input.Keys.S);
		boolean keyDPressed = Gdx.input.isKeyPressed(Input.Keys.D);

		/* Player Movement*/
		if((keyAPressed && millisDrag + startTime < endTime) || keyA){
			startTime = System.currentTimeMillis();
			player.setCurrentTexture(player.getLeftFacing());
			if(map.tiles[playerX - 1][playerY].getWalkable()){
				mapX = mapX + 1;
				playerX--;
				//batch.draw(player.getRightFacing(), player.getX(), player.getY());
				System.out.println(map.tiles[playerX][playerY]);
			}
		} else if ((keyWPressed && millisDrag + startTime < endTime) || keyW){
			startTime = System.currentTimeMillis();
			player.setCurrentTexture(player.getBackFacing());
			if(map.tiles[playerX][playerY + 1].getWalkable()) {
				if(map.tiles[playerX][playerY + 1].toString().equals("Door")){
					Door door = (Door) map.tiles[playerX][playerY + 1];
					if(door.isOpen()){
						door.closeDoor();
						map = basicMap;
					}else{
						door.openDoor();
						map = door.getInside();
					}
				}
				mapY = mapY - 1;
				playerY++;
				System.out.println(map.tiles[playerX][playerY]);
			}
		} else if ((keySPressed && millisDrag + startTime < endTime) || keyS){
			startTime = System.currentTimeMillis();
			player.setCurrentTexture(player.getFrontFacing());
			if(map.tiles[playerX][playerY - 1].getWalkable()) {
				mapY = mapY + 1;
				playerY--;
				System.out.println(map.tiles[playerX][playerY]);
			}
		} else if ((keyDPressed && millisDrag + startTime < endTime) || keyD){
			startTime = System.currentTimeMillis();
			player.setCurrentTexture(player.getRightFacing());
			if(map.tiles[playerX + 1][playerY].getWalkable()) {
				mapX = mapX - 1;
				playerX++;
				System.out.println(map.tiles[playerX][playerY]);
			}
		}

		batch.draw(player.getCurrentTexture(), player.getX(), player.getY());
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
