package browserFactory;

public class BrowserNotSupportedExeption extends IllegalStateException{
    public BrowserNotSupportedExeption(String browserName){
        super(String.format("Browser not support: %s",browserName));
    }
}
