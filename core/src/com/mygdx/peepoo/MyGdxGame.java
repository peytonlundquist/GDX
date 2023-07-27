package com.mygdx.peepoo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.peepoo.items.Item;
import com.mygdx.peepoo.items.Weapon;
import com.mygdx.peepoo.npc.BinaryTree;
import com.mygdx.peepoo.npc.Npc;
import com.mygdx.peepoo.player.Inventory;
import com.mygdx.peepoo.player.Player;
import com.mygdx.peepoo.tiles.Blur;
import com.mygdx.peepoo.tiles.Chest;
import com.mygdx.peepoo.tiles.Door;
import com.mygdx.peepoo.tiles.DoorMat;

public class MyGdxGame extends ApplicationAdapter {

	Music music;

	@Override
	public void create () {
		dialogueResponseX = 70;
		Audio audio = Gdx.audio;

		music = audio.newMusic(new FileHandle("raw-assets/song.mp3"));
		music.setVolume(0.0F);
		music.play();

		step = 32;

		/* Default Spawn */
		mapX = -2;
		mapY = -2;
		playerX = 12;
		playerY = 12;

		inventory = new Inventory();
//		inventory.getWeapons().add(new Sword());
		//inventory.getWeapons().add(new GreatSword());
//		inventory.getWeapons().add(new Dagger());

		batch = new SpriteBatch();
		waterAtlas = new TextureAtlas("water.txt");
		textureAtlas = new TextureAtlas("sprites.txt");
		textAtlas = new TextureAtlas("text.txt");
		charAtlas = new TextureAtlas("characters.txt");
		stateTime = 0f;

		messageBox = textAtlas.createSprite("messageBox");
		responseBox = textAtlas.createSprite("responseBox");

		messageBox.setPosition(60, 40);

		/* Create UI */


		/* Make player */
		player = new Player(new Texture("player/pumkinRight.png"),
				new Texture("player/pumkinLeft.png"),
				new Texture("player/pumkinFront.png"),
				new Texture("player/pumkinBack.png"));
		player.setX(310);
		player.setY(327);
		player.setFace(Player.Face.Back);

//		680, 714
		//20, 21,

		/* Make map */
		basicMap = Maps.getBasicMap(textureAtlas, charAtlas);
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


	String messageString = null;
	BinaryTree.Node currentDialogueNode = null;


	public void renderGame(){
		long endTime = System.currentTimeMillis();

		if(tickGoal + tickTime < endTime){
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

		if(messageString == null && currentDialogueNode == null && !loadInventory){
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
				int[] playerFacingTile = new int[]{playerX, playerY};
				if(player.getCurrentFace().name().equals("Right")) {
					playerFacingTile[0] = playerX + 1;
					playerFacingTile[1] = playerY;
				} else if (player.getCurrentFace().name().equals("Left")) {
					playerFacingTile[0] = playerX - 1;
					playerFacingTile[1] = playerY;
				} else if (player.getCurrentFace().name().equals("Front")){
					playerFacingTile[0] = playerX;
					playerFacingTile[1] = playerY - 1;
				} else if (player.getCurrentFace().name().equals("Back")){
					playerFacingTile[0] = playerX;
					playerFacingTile[1] = playerY + 1;
				}

				if(map.tiles[playerFacingTile[0]][playerFacingTile[1]].toString().equals("chest")){
					System.out.println("Is chest");

					Chest chest  = (Chest) map.tiles[playerFacingTile[0]][playerFacingTile[1]];

					if(!chest.isOpened()){
						System.out.println("Unopened");
						Item item = chest.interact();

						System.out.println(item.getClass().getSuperclass().getSimpleName());
						if(item.getClass().getSuperclass().getSimpleName().equals("Weapon")){
							inventory.getWeapons().add((Weapon) item);
							System.out.println("Item added");
						}
						messageString = "You opened the chest and received: " + item.toString();
					}else{
						messageString = "The chest is empty";
					}
				}
				if(map.tiles[playerFacingTile[0]][playerFacingTile[1] + 1].getNpc() != null) {
					currentDialogueNode = map.tiles[playerFacingTile[0]][playerFacingTile[1] + 1].getNpc().getCurrentNode();
					//npc.interact();
				}
			}
		}

		if(keyI && loadInventory == false){
			loadInventory = true;
		}else if (keyI && loadInventory){
			loadInventory = false;
		}

		batch.draw(player.getCurrentTexture(), player.getX(), player.getY());
		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

		renderMessage(messageString);
		renderDialogue(currentDialogueNode);

		boolean keySpace = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);

		if(keySpace) messageString = null;


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
				if((map.tiles[i][j].getNpc() != null)){
					map.tiles[i][j].getNpc().getSprite().setPosition((i * step) + (mapX*step), ((j-1) * step) + (mapY*step));
					map.tiles[i][j].getNpc().getSprite().draw(batch);
				}
			}
		}
	}


	public void renderUI(){
		Sprite icon = new Sprite(new Texture("player/pumkinFront.png"));
		icon.setPosition(280, 400);
		icon.draw(batch);


		font48.draw(batch, "Start Game", 160, 200);
		font24.draw(batch, "Press Enter or Space Key to continue", 60, 150);

		boolean keyEnter = Gdx.input.isKeyJustPressed(Input.Keys.ENTER);
		boolean keySpace = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
		boolean keyA = Gdx.input.isKeyJustPressed(Input.Keys.A);
		boolean keyD = Gdx.input.isKeyJustPressed(Input.Keys.D);

		if(keyEnter || keySpace) beginGame = true;


		font24.draw(batch, String.valueOf(music.getVolume()).substring(0, 3), 260, 30);


		if(keyA){
			if(music.getVolume() - 0.1f >= 0.0f){
				music.setVolume(music.getVolume() - 0.1f);
			}
		}

		if(keyD){
			if(music.getVolume() + 0.1f <= 1.0f){
				music.setVolume(music.getVolume() + 0.1f);
			}
		}

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

	public void renderMessage(String string){
		if(string == null) return;

		int wrapSize = 24;

		messageBox.draw(batch);

		if(string.length() > 24){
			font32.draw(batch, string.substring(0, wrapSize), 70, 164);
			font32.draw(batch, string.substring(wrapSize, string.length()), 70, 130);
		}else{
			font32.draw(batch, string, 70, 164);
		}
	}

	public void renderDialogue(BinaryTree.Node node){
		if(node == null) return;

		boolean keyEnter = Gdx.input.isKeyJustPressed(Input.Keys.ENTER);
		boolean keySpace = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
		boolean keyA = Gdx.input.isKeyJustPressed(Input.Keys.A);
		boolean keyD = Gdx.input.isKeyJustPressed(Input.Keys.D);

		int wrapSize = 24;

		messageBox.draw(batch);

		if(node.message.length() > 24){
			font32.draw(batch, node.message.substring(0, wrapSize), 70, 164);
			font32.draw(batch, node.message.substring(wrapSize), 70, 130);
		}else{
			font32.draw(batch, node.message, 70, 164);
		}

		font32.draw(batch, node.leftResponse, 80, 110);
		font32.draw(batch, node.rightResponse, 270, 110);

		if(keyD) dialogueResponseX = 270;
		if(keyA) dialogueResponseX = 70;

		if((keySpace && dialogueResponseX == 70) || (keyEnter && dialogueResponseX == 70)) currentDialogueNode = currentDialogueNode.leftNode;
		if((keySpace && dialogueResponseX == 270) || (keyEnter && dialogueResponseX == 270)) currentDialogueNode = currentDialogueNode.rightNode;


		responseBox.setPosition(dialogueResponseX, 50);
		responseBox.draw(batch);
	}

	Sprite messageBox, responseBox;
	int mapX, mapY, step, playerX, playerY, dialogueResponseX;
	SpriteBatch batch;
	Player player;
	Map map;
	Map basicMap;
	TextureAtlas textureAtlas, waterAtlas, textAtlas, charAtlas;
	float stateTime;
	Stage menu;
	Inventory inventory;
	long millisDrag = 150;
	long startTime = System.currentTimeMillis();
	long tickTime = System.currentTimeMillis();
	long tickGoal = 500;
}
