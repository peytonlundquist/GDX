package com.mygdx.peepoo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.peepoo.items.Item;
import com.mygdx.peepoo.items.Weapon;
import com.mygdx.peepoo.player.Inventory;
import com.mygdx.peepoo.player.Player;
import com.mygdx.peepoo.tiles.Blur;
import com.mygdx.peepoo.tiles.Door;
import com.mygdx.peepoo.tiles.DoorMat;
import com.mygdx.peepoo.items.weapons.Dagger;
import com.mygdx.peepoo.items.weapons.Sword;

public class MyGdxGame extends ApplicationAdapter {

	@Override
	public void create () {

		step = 32;

		/* Default Spawn */
		mapX = -2;
		mapY = -2;
		playerX = 12;
		playerY = 12;

		inventory = new Inventory();
		inventory.getWeapons().add(new Sword());
		//inventory.getWeapons().add(new GreatSword());
		inventory.getWeapons().add(new Dagger());

		batch = new SpriteBatch();
		waterAtlas = new TextureAtlas("water.txt");
		textureAtlas = new TextureAtlas("sprites.txt");
		textAtlas = new TextureAtlas("text.txt");
		stateTime = 0f;

		/* Create UI */



		/* Make player */
		player = new Player(new Texture("player/pumkinRight.png"),
				new Texture("player/pumkinLeft.png"),
				new Texture("player/pumkinFront.png"),
				new Texture("player/pumkinBack.png"));
		player.setX(320);
		player.setY(337);
		player.setFace(Player.Face.Back);

//		680, 714
		//20, 21,

		/* Make map */
		basicMap = Maps.getBasicMap(textureAtlas);
		map = basicMap;
		player.setCurrentTile(map.tiles[playerX][playerY]);
		System.out.println(player.getCurrentTile().toString());

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("raw-assets/slkscr.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter smallParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter largeParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		largeParameter.size = 64;
		smallParameter.size = 32;
		font64 = generator.generateFont(largeParameter); // font size 12 pixels
		font32 = generator.generateFont(smallParameter); // font size 12 pixels
		largeParameter.size = 48;
		smallParameter.size = 24;
		font48 = generator.generateFont(largeParameter); // font size 12 pixels
		font24 = generator.generateFont(smallParameter); // font size 12 pixels
		generator.dispose(); // don't forget to dispose to avoid memory leaks!
	}

	BitmapFont font24, font32, font48, font64;
	boolean beginGame = false;
	boolean loadInventory = false;

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 0);

		batch.begin();

		if(beginGame){
			renderGame();
			if(loadInventory){
				renderInventory();
			}
		}else{
			renderUI();
		}

		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
//		walkSheet.dispose();
		textureAtlas.dispose();
		waterAtlas.dispose();
		textAtlas.dispose();
		menu.dispose();
	}

	public void renderGame(){
		long endTime = System.currentTimeMillis();

		if(tickGoal + tickTime < endTime){
			//System.out.println("tick");
			renderMap(map);
			tickTime = System.currentTimeMillis();
		}else{
			renderMap(map);
		}

		boolean keyI = Gdx.input.isKeyJustPressed(Input.Keys.I);
		boolean keyE = Gdx.input.isKeyJustPressed(Input.Keys.E);


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
			player.setFace(Player.Face.Left);
			if(map.tiles[playerX - 1][playerY].getWalkable()){
				mapX = mapX + 1;
				playerX--;
				//batch.draw(player.getRightFacing(), player.getX(), player.getY());
				System.out.println(map.tiles[playerX][playerY]);
			}
		} else if ((keyWPressed && millisDrag + startTime < endTime) || keyW){
			startTime = System.currentTimeMillis();
			player.setFace(Player.Face.Back);
			if(map.tiles[playerX][playerY + 1].getWalkable()) {
				if(map.tiles[playerX][playerY + 1].toString().equals("Door")){
					Door door = (Door) map.tiles[playerX][playerY + 1];
					map = door.getInside();
				}
				mapY = mapY - 1;
				playerY++;
				System.out.println(map.tiles[playerX][playerY]);
			}
		} else if ((keySPressed && millisDrag + startTime < endTime) || keyS){
			startTime = System.currentTimeMillis();
			player.setFace(Player.Face.Front);
			if(map.tiles[playerX][playerY - 1].getWalkable()) {
				if(map.tiles[playerX][playerY - 1].toString().equals("doorMat")){
					DoorMat doorMat = (DoorMat) map.tiles[playerX][playerY - 1];
					map = doorMat.getOutside();
				}
				mapY = mapY + 1;
				playerY--;
				System.out.println(map.tiles[playerX][playerY]);
			}
		} else if ((keyDPressed && millisDrag + startTime < endTime) || keyD){
			startTime = System.currentTimeMillis();
			player.setFace(Player.Face.Right);
			if(map.tiles[playerX + 1][playerY].getWalkable()) {
				mapX = mapX - 1;
				playerX++;
				System.out.println(map.tiles[playerX][playerY]);
			}
		}

		if(keyE){
			if(player.getCurrentFace().name().equals("Right")){
				Item item = map.tiles[playerX + 1][playerY].interact();
				if(item != null){
					System.out.println(item.getClass().getSuperclass().getSimpleName());
					if(item.getClass().getSuperclass().getSimpleName().equals("Weapon")){
						inventory.getWeapons().add((Weapon) item);
						System.out.println("Item added");
					}
				}
			}
		}

		if(keyI && loadInventory == false){
			loadInventory = true;
		}else if (keyI && loadInventory){
			loadInventory = false;
		}
		//rayHandler.updateAndRender();

		batch.draw(player.getCurrentTexture(), player.getX(), player.getY());
		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
	}

	private void renderMap(Map map){
		for(int i = 0; i < map.width; i++){
			for(int j = 0; j < map.height; j++){
				if(map.tiles[i][j].getAnimation() != null){
					TextureRegion currentFrame = map.tiles[i][j].getAnimation().getKeyFrame(stateTime, true);
					batch.draw(currentFrame, (i * step) + (mapX*step), (j * step) + (mapY*step)); // Draw current frame at (50, 50)
				}else{
					map.tiles[i][j].sprite.setPosition((i * step) + (mapX*step), (j * step) + (mapY*step));
					map.tiles[i][j].sprite.draw(batch);
				}
			}
		}
	}

	public void renderUI(){
		Sprite icon = new Sprite(new Texture("player/pumkinFront.png"));
		icon.setPosition(220, 400);
		icon.draw(batch);


		font48.draw(batch, "Start Game", 100, 200);
		font24.draw(batch, "Press Enter or Space Key to continue", 20, 150);

		boolean keyEnter = Gdx.input.isKeyJustPressed(Input.Keys.ENTER);
		boolean keySpace = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);

		if(keyEnter || keySpace) beginGame = true;
	}

	public void renderInventory(){

		Blur blur = new Blur(textureAtlas);
		for(int i = 0; i < map.width; i++) {
			for (int j = 0; j < map.height; j++) {
				blur.sprite.setPosition((i * step) + (mapX*step), (j * step) + (mapY*step));
				blur.sprite.draw(batch);
			}
		}

		font64.draw(batch, "Inventory", 100, 600);
		font32.draw(batch, "Weapons", 20, 500);

		for(int i = 0; i < inventory.getWeapons().size(); i++){
			font32.draw(batch, inventory.getWeapons().get(i).toString(), 100, 500 - (32 * (i+1)));
		}

		font32.draw(batch, "Items", 20, 340);
	}


	int mapX, mapY, step, playerX, playerY;
	SpriteBatch batch;
	Player player;
	Map map;
	Map basicMap;
	TextureAtlas textureAtlas, waterAtlas, textAtlas;
	float stateTime;
	Stage menu;
	Inventory inventory;
	long millisDrag = 150;
	long startTime = System.currentTimeMillis();
	long tickTime = System.currentTimeMillis();
	long tickGoal = 500;
}
