public class LeakerModel {
    private String message;
    private LeakerProxy proxy;
    private String publicKeyFile;
    public LeakerModel(String message, LeakerProxy proxy, String publicKeyFile) {
        this.message = message;
        this.proxy = proxy;
        this.publicKeyFile = publicKeyFile;
    }

    // Hidden operations.

    /**
    * Class Reporter provides a runnable object that reports the fire sensor
    * state to the listener.
    */
    private class Reporter implements Runnable {
        public void run() {
            try {
                proxy.encode(message, publicKeyFile);
            } catch (IOException exc) {
                exc.printStackTrace (System.err);
                System.exit (1);
            }
        }
    }
}