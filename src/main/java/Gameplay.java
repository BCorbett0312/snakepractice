import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Gameplay extends JPanel implements KeyListener, ActionListener {


    private ImageIcon titleImage;
    private ImageIcon snakeImage;

    private Integer moves = 0;

    private int[] snakeXLength = new int[750];
    private int[] snakeYLength = new int[750];

    private Integer lengthOfSnake = 1;

    private Integer score = 0;

    private Boolean left = false;
    private Boolean right = false;
    private Boolean up = false;
    private Boolean down = false;

    private ImageIcon rightMouth;
    private ImageIcon leftMouth;
    private ImageIcon upMouth;
    private ImageIcon downMouth;

    private Timer timer;
    private Integer delay = 70;



    private Integer[] appleXPos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275,
            300, 325, 350, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675,
            700, 725, 750, 775, 800, 825, 850};

    private Integer[] appleYPos = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325,
            350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650};

    private ImageIcon appleImage;

    private Random random = new Random();

    private Integer xPos = random.nextInt(33);

    private Integer yPos = random.nextInt(23);

    public Gameplay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g){

        if (moves == 0) {
            snakeXLength[0] = 50;

            snakeYLength[0] = 100;

        }

        //draw title border
        g.setColor(Color.WHITE);
        g.drawRect(24, 10, 851, 55);

        //draw image
        titleImage = new ImageIcon(getClass().getClassLoader().getResource("snaketitle.jpg"));
        titleImage.paintIcon(this, g, 25, 11);

        //draw gameplay border

        g.setColor(Color.WHITE);
        g.drawRect(24, 74, 851, 577);

        //draw background
        g.setColor(Color.BLACK);
        g.fillRect(25,75, 850, 575);

        //draw scores
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Score:  "+ score, 780, 30);

        //draw lenght of snake
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Length: " + lengthOfSnake, 780, 50);

        //draw snake
        rightMouth = new ImageIcon(getClass().getClassLoader().getResource("rightmouth.png"));
        rightMouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);

        for(int a = 0; a < lengthOfSnake; a++){
            if(a == 0 && right){
                rightMouth = new ImageIcon(getClass().getClassLoader().getResource("rightmouth.png"));
                rightMouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
            }
            if(a == 0 && left){
                leftMouth = new ImageIcon(getClass().getClassLoader().getResource("leftmouth.png"));
                leftMouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
            }
            if(a == 0 && down){
                downMouth = new ImageIcon(getClass().getClassLoader().getResource("downmouth.png"));
                downMouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
            }
            if(a == 0 && up){
                upMouth = new ImageIcon(getClass().getClassLoader().getResource("upmouth.png"));
                upMouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
            }
            if(a != 0){
                snakeImage = new ImageIcon(getClass().getClassLoader().getResource("snakeimage.png"));
                snakeImage.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
            }
        }

        appleImage = new ImageIcon(getClass().getClassLoader().getResource("enemy.png"));

        if(appleXPos[xPos].equals(snakeXLength[0]) && appleYPos[yPos].equals(snakeYLength[0])){
            score = score+3;
            lengthOfSnake++;
            xPos = random.nextInt(33);
            yPos = random.nextInt(23);
        }

        appleImage.paintIcon(this, g, appleXPos[xPos], appleYPos[yPos]);


        for(int b = 1; b < lengthOfSnake; b++){
            if (snakeXLength[b] == snakeXLength[0] && snakeYLength[b] == snakeYLength[0]){
                right = false;
                left = false;
                down = false;
                up = false;

                g.setColor(Color.WHITE);
                g.setFont(new Font("arial", Font.BOLD, 50));
                g.drawString("Game Over", 300, 300);

                g.setFont(new Font("arial", Font.BOLD, 20));
                g.drawString("Press Space to Restart", 350, 340);
            }
        }

        g.dispose();

    }

    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(right){
            for(int r  = lengthOfSnake-1; r>=0; r--){
                snakeYLength[r+1] = snakeYLength[r];
            }
            for(int r = lengthOfSnake; r>=0; r--){
                if(r == 0){
                    snakeXLength[r] = snakeXLength[r] +25;
                }
                else{
                    snakeXLength[r] = snakeXLength[r-1];
                }
                if(snakeXLength[r]>850){
                    snakeXLength[r] = 25;
                }
            }
            repaint();
        }
        if(left){
            for(int r  = lengthOfSnake-1; r>=0; r--){
                snakeYLength[r+1] = snakeYLength[r];
            }
            for(int r = lengthOfSnake; r>=0; r--){
                if(r == 0){
                    snakeXLength[r] = snakeXLength[r] - 25;
                }
                else{
                    snakeXLength[r] = snakeXLength[r-1];
                }
                if(snakeXLength[r] < 25){
                    snakeXLength[r] = 850;
                }
            }
            repaint();

        }
        if(down){
            for(int r  = lengthOfSnake-1; r>=0; r--){
                snakeXLength[r+1] = snakeXLength[r];
            }
            for(int r = lengthOfSnake; r>=0; r--){
                if(r == 0){
                    snakeYLength[r] = snakeYLength[r] +25;
                }
                else{
                    snakeYLength[r] = snakeYLength[r-1];
                }
                if(snakeYLength[r] > 625){
                    snakeYLength[r] = 75;
                }
            }
            repaint();

        }
        if(up){
            for(int r  = lengthOfSnake-1; r>=0; r--){
                snakeXLength[r+1] = snakeXLength[r];
            }
            for(int r = lengthOfSnake; r>=0; r--){
                if(r == 0){
                    snakeYLength[r] = snakeYLength[r] - 25;
                }
                else{
                    snakeYLength[r] = snakeYLength[r-1];
                }
                if(snakeYLength[r] < 75){
                    snakeYLength[r] = 625;
                }
            }
            repaint();
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            moves = 0;
            score = 0;
            lengthOfSnake = 1;
            repaint();
        } else{
            updateGameAfterPress(e);
        }


    }

    public void keyReleased(KeyEvent e) {

    }

    public void updateGameAfterPress(KeyEvent e){
        moves++;
        switch (e.getKeyCode()){
            case KeyEvent.VK_RIGHT:
                right = true;
                if(!left){
                    right = true;
                } else {
                    right = false;
                    left = true;
                }
                up = false;
                down = false;
                break;
            case KeyEvent.VK_LEFT:
                left = true;
                if(!right){
                    left = true;
                } else {
                    left = false;
                    right = true;
                }
                up = false;
                down = false;
                break;
            case KeyEvent.VK_UP:
                up = true;
                if (!down){
                    up = true;
                } else {
                    up = false;
                    down = true;
                }
                left = false;
                right = false;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                if(!up){
                    down = true;
                } else {
                    down = false;
                    up = true;
                }
                left = false;
                right = false;
                break;
        }
    }



}
