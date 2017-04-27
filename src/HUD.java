import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;

public class HUD {

    static ObjectHandler handler;

    private static float p1Health = 100;
    private static float p2Health = 100;

    protected static float p1Stamina = 200;
    protected static float p2Stamina = 100;

    private float grnValBar1 = 255;
    private float grnValBar2 = 255;

    private float yelValBar1 = 255;
    private float yelValBar2 = 255;

    private static int p1Score = 0;
    private static int p2Score = 0;

    private int stamRegen = 225;

    private Color jmuGold = new Color(194, 161, 77);
    private static MusicPlayer music = new MusicPlayer();

    private Font font = null;
    private boolean fontMade = false;
    private static boolean gameOver = false;

    
    public HUD(ObjectHandler handler) {
        HUD.handler = handler;
    }
    
    public void tick() {
        p1Health = Game.bound((int) p1Health, 0, 100);
        p2Health = Game.bound((int) p2Health, 0, 100);

        grnValBar1 = Game.bound((int) grnValBar1, 0, 255);
        grnValBar2 = Game.bound((int) grnValBar2, 0, 255);

        grnValBar1 = p1Health * 2;
        grnValBar2 = p2Health * 2;

        p1Stamina = Game.bound((int) p1Stamina, 0, 100);
        p2Stamina = Game.bound((int) p2Stamina, 0, 100);

        yelValBar1 = Game.bound((int) yelValBar1, 0, 255);
        yelValBar2 = Game.bound((int) yelValBar1, 0, 255);

        yelValBar1 = p1Stamina * 2;
        yelValBar2 = p2Stamina * 2;

        updateStamina();
    }

    public void render(Graphics g) {

        if (!fontMade) {
            makeFont();
        }

        fontMade = true;
        g.setColor(Color.DARK_GRAY);
        g.fillRect(10, 15, 150, 20);
        g.setColor(new Color(75, (int) grnValBar1, 0));
        g.fillRect(10, 15, (int) (p1Health * 1.5), 20);
        g.setColor(Color.white);
        g.drawRect(10, 15, 150, 20);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(10, 30, 150, 10);
        g.setColor(new Color(255, (int) yelValBar1, 40));
        g.fillRect(10, 30, (int) (p1Stamina * 1.5), 10);
        g.setColor(Color.white);
        g.drawRect(10, 30, 150, 10);

        if (Game.twoPlayerMode) {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(335, 15, 150, 20);
            g.setColor(new Color(75, (int) grnValBar2, 0));
            g.fillRect(335, 15, (int) (p2Health * 1.5), 20);
            g.setColor(Color.white);
            g.drawRect(335, 15, 150, 20);

            g.setColor(Color.DARK_GRAY);
            g.fillRect(335, 30, 150, 10);
            g.setColor(new Color(255, (int) yelValBar2, 40));
            g.fillRect(335, 30, (int) (p2Stamina * 1.5), 10);
            g.setColor(Color.white);
            g.drawRect(335, 30, 150, 10);
        }

        // player score(s)
        g.setColor(jmuGold);
        font = font.deriveFont(Font.PLAIN, 24.0f);
        g.setFont(font);
        g.drawString("" + p1Score, 190, 40);
        g.drawString("" + p2Score, 280, 40);

        if (!gameOver) {
            isWinner();   
        }

        font = font.deriveFont(Font.PLAIN, 17.0f);
        g.setFont(font);
        g.drawString("vs", 230, 35);
    }

    public void makeFont() {

        try {
            InputStream is = getClass().getResourceAsStream("Fipps.TTF");
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void isWinner() {
        if (p1Score == 5) {
            System.out.println(
                    "Player 1 has " + p1Score + " points. Player 1 Wins.");
            Game.p1Win = true;
            Game.state = State.Win;
            gameOver = true;
        }

        if (p2Score == 5) {
            System.out.println(
                    "Player 2 has " + p2Score + " points. Player 2 Wins.");
            Game.p2Win = true;
            Game.state = State.Win;
            gameOver = true;
        }
        
    }

    public static void updateHealth(ID id, int damage) {
        if (id == ID.Player && !Player.p1Blocking) {
            p1Health -= damage;
            // System.out.println("Player1 took damage.");
            if (p1Health == 0) {
                // System.out.println("Player 1 has died.");
                music.playWin();
                p2Score += 1;
                handler.remAll();
                handler.addObject(new Player(Game.width / 6,
                        Game.height / 6 + 110, ID.Player, handler));
                handler.addObject(new Player(Game.width / 2 + 100,
                        Game.height / 6 + 110, ID.Player2, handler));
                p1Health = 100;
                p1Stamina = 100;
                p2Health = 100;
                p2Stamina = 100;
            }
        } else if (id == ID.Player2 && !Player.p2Blocking) {
            p2Health -= damage;
            // System.out.println("Player2 took damage.");
            if (p2Health == 0) {
                // System.out.println("Player 2 has died.");
                music.playWin();
                p1Score += 1;
                handler.remAll();
                handler.addObject(new Player(Game.width / 6,
                        Game.height / 6 + 110, ID.Player, handler));
                handler.addObject(new Player(Game.width / 2 + 100,
                        Game.height / 6 + 110, ID.Player2, handler));
                p1Health = 100;
                p1Stamina = 100;
                p2Health = 100;
                p2Stamina = 100;
            }
        }
    }

    public void updateStamina() {
        long time = System.currentTimeMillis();

        if (Player.p1Blocking && p1Stamina != 0) {
            p1Stamina -= 1;
        } else if (!Player.p1Blocking && p1Stamina != 100 && !Player.p1IsMoving
                && time - Game.previousTime >= stamRegen) {
            p1Stamina += 1;
        } else if (Player.p2Blocking && p2Stamina != 0) {
            p2Stamina -= 1;
        } else if (!Player.p2Blocking && p2Stamina != 100 && !Player.p2IsMoving
                && time - Game.previousTime >= stamRegen) {
            p2Stamina += 1;
        } else if (Player.p1IsMoving) {
            p1Stamina -= 1;
        } else if (Player.p2IsMoving) {
            p2Stamina -= 1;
        }
    }
    
    public static void setGameOver(boolean gameOver) {
        HUD.gameOver = gameOver;
    }

    public static void setP1Score(int p1Score) {
        HUD.p1Score = p1Score;
    }

    public static void setP2Score(int p2Score) {
        HUD.p2Score = p2Score;
    }
}
