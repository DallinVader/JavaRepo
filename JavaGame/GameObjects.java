package JavaGame;

public class GameObjects {
    int Xpos = 0;
    int Ypos = 0;
    Boolean RenderObj = false;

    public GameObjects(boolean Render, int Xpos, int Ypos) {
        this.Xpos = Xpos;
        this.Ypos = Ypos;
        this.RenderObj = Render;

        if (Render) {

        }
    }

    public static class Horse extends GameObjects {
        int size = 1;

        public Horse(int Size) {
            super(true, Size, Size);
            this.size = Size;
        }
    }
}
