import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {
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

    // snake
    Tile snakeHead; // snakeHead dentro do tile
    ArrayList<Tile> snakeBody;

    // comida
    Tile comida;
    Random random;

    // logica do jogo
    Timer gameLoop;
    // movimento para vertical
    int velocityX;
    // movimento para horizontal
    int velocityY;

    // Constructor da classe snakegame
    SnakeGame(int larguraJanela, int alturaJanela) {
        this.alturaJanela = alturaJanela;
        this.larguraJanela = larguraJanela;
        setPreferredSize(new Dimension(this.larguraJanela, this.alturaJanela));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);
        // inicializa a cabeça da cobra la ele no tile x5,y5
        snakeHead = new Tile(5, 5);
        // cria um array para armazenar o corpo da cobra
        snakeBody = new ArrayList<Tile>();

        gameLoop = new Timer(100, this);
        gameLoop.start();

        // inicializa a comida no x10y10
        comida = new Tile(10, 10);
        
        random = new Random();
        spawnComida();

        velocityX = 0;
        velocityY = 0;

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

        // Snake Head
        g.setColor(Color.white);
        g.fillRect(snakeHead.x * tamTile, // pos x em formato de pixels
                snakeHead.y * tamTile, // pos y em formato de pixels
                tamTile, // largura do bloco
                tamTile); // altura do bloco
                
        // Snake Body
        for (int i = 0; i < snakeBody.size(); i++) { // corpo da cobra
            Tile snakeFrag = snakeBody.get(i);
            g.fillRect(snakeFrag.x * tamTile, snakeFrag.y * tamTile, tamTile, tamTile);
        }
    }

       

    public void spawnComida() { // spawna comida aleatoriamente
        comida.x = random.nextInt(larguraJanela / tamTile); // 600/25 = 24
        comida.y = random.nextInt(alturaJanela / tamTile);
    }

    public boolean colisao(Tile tile1, Tile tile2) { // checa colisao
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    public void move() {
        // comer
        if (colisao(snakeHead, comida)) {
            snakeBody.add(new Tile(comida.x, comida.y));
            spawnComida();
        }

        for (int i = snakeBody.size()-1; i >= 0; i--) {
            Tile snakeFrag = snakeBody.get(i);
            if (i == 0) {
                snakeFrag.x = snakeHead.x; //  <------------------- !!!!! COMENTAR ISSO !!!!! --------------------------->
                snakeFrag.y = snakeHead.y;
            } else {
                Tile backSnakeFrag = snakeBody.get(i - 1);
                snakeFrag.x = backSnakeFrag.x;
                snakeFrag.y = backSnakeFrag.y;

            }
        }

        // Snake head
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;
        }

        


    // Logica de movimento e update
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                if (velocityY != 1) {
                    velocityX = 0;
                    velocityY = -1;
                }
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                if (velocityY != -1) {
                    velocityX = 0;
                    velocityY = 1;
                }
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                if (velocityX != 1) {
                    velocityX = -1;
                    velocityY = 0;
                }
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                if (velocityX != -1) {
                    velocityX = 1;
                    velocityY = 0;
                }
                break;
        }
    }

    // Não utilizado mas precisa existir para nao dar log flood
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

// TODO: 
// colisao de snakehead com snakebody;
// food spawnando dentro da cobra
// passar de uma parede para outra
