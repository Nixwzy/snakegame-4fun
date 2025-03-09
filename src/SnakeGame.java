import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener {
    // classe interna que representa um bloco, para tambem representar a cobrinha e
    // a comida
    private class Tile {
        int x;
        int y;

        // Constructor do tile para inicializar a posição x e y do mesmo.
        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int larguraJanela;
    int alturaJanela;

    // Cada tile tem 25 px
    int tamTile = 25;

    // <!-- TILES -->
        // snake
    Tile snakeHead; // snakeHead dentro do tile

        // comida
    Tile comida;
    Random random;

    // logica do jogo
    Timer gameLoop;

    // Constructor da classe snakegame
    SnakeGame(int larguraJanela, int alturaJanela) {
        this.alturaJanela = alturaJanela;
        this.larguraJanela = larguraJanela;
        setPreferredSize(new Dimension(this.larguraJanela, this.alturaJanela));
        setBackground(Color.BLACK);
        // inicializa a cabeça da cobra la ele no tile x5,y5
        snakeHead = new Tile(5, 5);

        gameLoop = new Timer(100, this);
        gameLoop.start();
        
        comida = new Tile(10, 10);
        random = new Random();
        spawnComida();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g); // desenha os elementos do jogo
    }

    public void draw(Graphics g) {
        // Grid
        for (int i = 0; i < larguraJanela / tamTile; i++) {
            // x1, y1, x2, y2
            g.drawLine(i * tamTile, 0, i * tamTile, alturaJanela);
            g.drawLine(0, i * tamTile, larguraJanela, i * tamTile);
        }

        // comida
        g.setColor(Color.red);
        g.fillRect(comida.x * tamTile, // pos x em formato de pixels
                comida.y * tamTile, // pos y em formato de pixels
                tamTile, // largura do bloco
                tamTile); // altura do bloco

        // Snake
        g.setColor(Color.white);
        g.fillRect(snakeHead.x * tamTile, // pos x em formato de pixels
                snakeHead.y * tamTile, // pos y em formato de pixels
                tamTile, // largura do bloco
                tamTile); // altura do bloco
    }

    public void spawnComida() {
        comida.x = random.nextInt(larguraJanela/tamTile); // 600/25 = 24
        comida.y = random.nextInt(alturaJanela/tamTile);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       repaint();
    }
}
