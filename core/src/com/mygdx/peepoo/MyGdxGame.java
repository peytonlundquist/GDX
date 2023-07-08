package com.mygdx.peepoo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.peepoo.tiles.Door;
import com.mygdx.peepoo.tiles.DoorMat;

public class MyGdxGame extends ApplicationAdapter {

	@Override
	public void create () {
		batch = new SpriteBatch();
		waterAtlas = new TextureAtlas("water.txt");
		textureAtlas = new TextureAtlas("sprites.txt");
		stateTime = 0f;

		/* Create UI */
		createUI();

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

	boolean beginGame = false;

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 0);

		batch.begin();

		if(beginGame){
			renderGame();
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
		stage.dispose();
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
			if(map.tiles[playerX][playerY].getWalkable()){
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
					map = door.getInside();
				}
				mapY = mapY - 1;
				playerY++;
				System.out.println(map.tiles[playerX][playerY]);
			}
		} else if ((keySPressed && millisDrag + startTime < endTime) || keyS){
			startTime = System.currentTimeMillis();
			player.setCurrentTexture(player.getFrontFacing());
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
			player.setCurrentTexture(player.getRightFacing());
			if(map.tiles[playerX + 1][playerY].getWalkable()) {
				mapX = mapX - 1;
				playerX++;
				System.out.println(map.tiles[playerX][playerY]);
			}
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
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
		Sprite sprite = new Sprite(new Texture("player/pumkinFront.png"));
		sprite.setPosition(400, 400);
		sprite.draw(batch);
	}

	public void createUI(){
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		// A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
		// recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
		skin = new Skin();

		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		// Store the default libGDX font under the name "default".
		skin.add("default", new BitmapFont());

		// Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);

		// Create a table that fills the screen. Everything else will go inside this table.
		Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);

		// Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
		final TextButton button = new TextButton("Start Game", skin);
		table.add(button);

		// Add a listener to the button. ChangeListener is fired when the button's checked state changes, eg when clicked,
		// Button#setChecked() is called, via a key press, etc. If the event.cancel() is called, the checked state will be reverted.
		// ClickListener could have been used, but would only fire when clicked. Also, canceling a ClickListener event won't
		// revert the checked state.
		button.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				System.out.println("Clicked! Is checked: " + button.isChecked());
				beginGame = true;
				button.setText("Good job!");
			}
		});
	}

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
	TextureAtlas waterAtlas;
	float stateTime;
	Skin skin;
	Stage stage;
	long millisDrag = 150;
	long startTime = System.currentTimeMillis();
	long tickTime = System.currentTimeMillis();
	long tickGoal = 500;
}
