package Main;

/**
  Главный класс, откуда идет запуск приложения
 */
public class Main {
    public static void main(String[] args) {
        MenuFrame menuFrame = new MenuFrame();
        MyFrameDirector myFrameDirector = new MyFrameDirector();
        myFrameDirector.makeFrame(menuFrame);
    }

}
