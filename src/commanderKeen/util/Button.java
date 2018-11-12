package commanderKeen.util;

public class Button {
    private ButtonClickListener listener;

    public Button(ButtonClickListener listener){
        this.listener = listener;
    }

    public void clicked(){
        this.listener.buttonClicked();
    }
}
