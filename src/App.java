import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {

        // tamanho da janela
        int larguraFrame = 600;
        int alturaFrame = larguraFrame;
        

        // config da janela (jframe)
        // jframe -> classe da lib swing q representa uma janela gráfica. basicamente
        // uma janela de um aplicativo de desktop em java
        JFrame frame = new JFrame("Neymar Junior 2011");

        frame.setSize(alturaFrame, larguraFrame);
        frame.setLocationRelativeTo(null); // parametro para centralizar na tela qnd a janela for criada
        frame.setResizable(false); // desativa redimensionamento da janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        // instancia e adiciona ao jframe
        SnakeGame snakeGame = new SnakeGame(alturaFrame, larguraFrame);
        frame.add(snakeGame);
        frame.pack(); // ajusta o tamanho do frame para caber nos componentes 
        frame.setVisible(true); // deve ser chamada depois de frame.pack
        snakeGame.requestFocus();

    }
}