package com.mygdx.game;

//import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import io.socket.client.IO;
import io.socket.emitter.Emitter; 
import io.socket.client.Socket;





public class Mapa extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture block;
	OrthographicCamera camera;
	Viewport viewport;

	private Bomberman bomberman;
	private List<Float> blockDestructibleX;
	private List<Float> blockDestructibleY;
	private List<Texture> blockDestructibleTexture;

	private Powers powers;
	private io.socket.client.Socket socket;

	@Override
	public void create() {

		// Conexão socket
		connectSocket();
		// Detectar movimentos
		configSocketEventos();

		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();

		batch = new SpriteBatch();
		img = new Texture("fundo.png");
		block = new Texture("block.png");

		camera = new OrthographicCamera();
		viewport = new FitViewport(screenWidth, screenHeight, camera);
		viewport.apply();

		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
		camera.update();

		bomberman = new Bomberman(100, 100);
		powers = new Powers();

		Texture texture1 = new Texture("Poder_Bomba.jpeg");
		Texture texture2 = new Texture("luvas.jpeg");
		Texture texture3 = new Texture("Velocidade.jpeg");
		Texture texture4 = new Texture("caveira.jpeg");
		Texture texture5 = new Texture("amarelo.jpeg");

		powers.adicionarPowers(texture1, 155, 200);
		powers.adicionarPowers(texture2, 465, 254);
		powers.adicionarPowers(texture3, 465, 363);
		powers.adicionarPowers(texture4, 160, 363);
		powers.adicionarPowers(texture5, 160, 28);

		blockDestructibleX = new ArrayList<>();
		blockDestructibleY = new ArrayList<>();
		blockDestructibleTexture = new ArrayList<>();

		// Adicione as coordenadas dos blocos
		blockDestructibleX.add(230f);
		blockDestructibleY.add(308f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(256f);
		blockDestructibleY.add(308f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(282f);
		blockDestructibleY.add(308f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(308f);
		blockDestructibleY.add(308f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(256f);
		blockDestructibleY.add(335f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(282f);
		blockDestructibleY.add(335f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(514f);
		blockDestructibleY.add(145f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(487f);
		blockDestructibleY.add(145f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(153f);
		blockDestructibleY.add(145f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(127f);
		blockDestructibleY.add(145f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(101f);
		blockDestructibleY.add(145f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(75f);
		blockDestructibleY.add(145f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(49f);
		blockDestructibleY.add(145f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(25f);
		blockDestructibleY.add(145f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(257f);
		blockDestructibleY.add(199f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(283f);
		blockDestructibleY.add(199f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(257f);
		blockDestructibleY.add(226f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(257f);
		blockDestructibleY.add(282f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(283f);
		blockDestructibleY.add(226f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(283f);
		blockDestructibleY.add(282f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(77f);
		blockDestructibleY.add(308f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(77f);
		blockDestructibleY.add(363f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(77f);
		blockDestructibleY.add(417f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(334f);
		blockDestructibleY.add(335f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(386f);
		blockDestructibleY.add(308f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(180f);
		blockDestructibleY.add(336f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(51f);
		blockDestructibleY.add(336f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(26f);
		blockDestructibleY.add(336f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(180f);
		blockDestructibleY.add(119f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(257f);
		blockDestructibleY.add(119f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(334f);
		blockDestructibleY.add(119f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(154f);
		blockDestructibleY.add(92f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(180f);
		blockDestructibleY.add(65f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(52f);
		blockDestructibleY.add(65f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(410f);
		blockDestructibleY.add(65f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(268f);
		blockDestructibleY.add(27f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(537f);
		blockDestructibleY.add(27f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(588f);
		blockDestructibleY.add(227f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(512f);
		blockDestructibleY.add(227f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(408f);
		blockDestructibleY.add(227f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(128f);
		blockDestructibleY.add(227f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(179f);
		blockDestructibleY.add(227f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(51f);
		blockDestructibleY.add(227f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(25f);
		blockDestructibleY.add(281f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(51f);
		blockDestructibleY.add(390f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(257f);
		blockDestructibleY.add(390f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(334f);
		blockDestructibleY.add(390f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(462f);
		blockDestructibleY.add(417f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(564f);
		blockDestructibleY.add(390f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(460f);
		blockDestructibleY.add(200f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(384f);
		blockDestructibleY.add(200f);
		blockDestructibleTexture.add(new Texture("block2.png"));

		blockDestructibleX.add(436f);
		blockDestructibleY.add(173f);
		blockDestructibleTexture.add(new Texture("block2.png"));
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(img, 0, 0, camera.viewportWidth, camera.viewportHeight);

		int numberOfBlocksRightLeft = 19;
		int numberOfBlocksTopBottom = 24;
		float blockWidth = camera.viewportWidth * 0.04f;
		float blockSpacingRightLeft = (camera.viewportHeight - blockWidth * numberOfBlocksRightLeft)
				/ (numberOfBlocksRightLeft - 1);
		float blockSpacingTopBottom = (camera.viewportWidth - blockWidth * numberOfBlocksTopBottom)
				/ (numberOfBlocksTopBottom - 1);
		int numberOfColumns = 7;
		int numberOfBlocksPerColumn = 7;
		float blockSpacingCenterX = camera.viewportWidth * 0.08f; // Espaçamento entre os blocos no centro na direção X
		float blockSpacingCenterY = camera.viewportHeight * 0.06f; // Espaçamento entre os blocos no centro na direção Y

		// Desenha a fileira de blocos no lado direito
		for (int i = 0; i < numberOfBlocksRightLeft; i++) {
			float blockY = i * (blockWidth + blockSpacingRightLeft);
			float blockX = camera.viewportWidth - blockWidth;
			batch.draw(block, blockX, blockY, blockWidth, blockWidth);
		}

		// Desenha a fileira de blocos no lado esquerdo
		for (int i = 0; i < numberOfBlocksRightLeft; i++) {
			float blockY = i * (blockWidth + blockSpacingRightLeft);
			float blockX = 0;
			batch.draw(block, blockX, blockY, blockWidth, blockWidth);

		}

		// Desenha a fileira de blocos na parte superior
		for (int i = 0; i < numberOfBlocksTopBottom; i++) {
			float blockX = i * (blockWidth + blockSpacingTopBottom);
			float blockY = camera.viewportHeight - blockWidth;
			batch.draw(block, blockX, blockY, blockWidth, blockWidth);
		}

		// Desenha a fileira de blocos na parte inferior
		for (int i = 0; i < numberOfBlocksTopBottom; i++) {
			float blockX = i * (blockWidth + blockSpacingTopBottom);
			float blockY = 0;
			batch.draw(block, blockX, blockY, blockWidth, blockWidth);
		}

		// Desenha as colunas de blocos no centro da tela
		for (int col = 0; col < numberOfColumns; col++) {
			float offsetX = camera.viewportWidth * 0.0015f; // Desloca as colunas do meio 10% da largura da tela para a
															// esquerda
			float startX = offsetX + (camera.viewportWidth
					- (numberOfBlocksPerColumn * blockWidth + (numberOfBlocksPerColumn - 1) * blockSpacingCenterX)) / 2;
			float startY = (camera.viewportHeight
					- (numberOfBlocksPerColumn * blockWidth + (numberOfBlocksPerColumn - 1) * blockSpacingCenterY)) / 2;
			for (int row = 0; row < numberOfBlocksPerColumn; row++) {
				float blockX = startX + col * (blockWidth + blockSpacingCenterX);
				float blockY = startY + row * (blockWidth + blockSpacingCenterY);
				batch.draw(block, blockX, blockY, blockWidth, blockWidth);
			}
		}

		bomberman.atualizar(Gdx.graphics.getDeltaTime());
		bomberman.desenhar(batch);
		powers.desenhar(batch);

		// Desenhe os blocos especiais
		for (int i = 0; i < blockDestructibleX.size(); i++) {
			float x = blockDestructibleX.get(i);
			float y = blockDestructibleY.get(i);
			Texture texture = blockDestructibleTexture.get(i);
			batch.draw(texture, x, y, blockWidth, blockWidth);
		}

		batch.end();
	}

	// Função de connectar
	public void connectSocket() {

		try {
			socket = IO.socket("http://localhost:8080");
			socket.connect();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// Dectar movimentos

	public void configSocketEventos(){
		socket.on(Socket.EVENT_CONNECT, new Emitter.Listener(){
			public void call(Object...args){
				 Gdx.app.log("SocketIO", "ConnectED");
				
			}
		}).on("socketID", new Emitter.Listener(){
			@Override
			public void call(Object...args){
				JSONObject data = (JSONObject) args[0];
				
				 try {

					String id = data.getString("id"); 
				Gdx.app.log("SocketIO", "My ID: " + id);
					
				 } catch (Exception e) {
					Gdx.app.log("SocketIO", "Erro getting id");
				 }


			}
	}).on("newPlayer", new Emitter.Listener() {
		@Override
		public void call(Object...args){
				JSONObject data = (JSONObject) args[0];
				
				 try {

					String id = data.getString("id"); 
				Gdx.app.log("SocketIO", "My ID: " + id);
					
				 } catch (Exception e) {
					Gdx.app.log("SocketIO", "Erro getting id");
				 }

				}

				});
}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
		block.dispose();
		powers.dispose();
	}
}
