public interface LeakerListener {
    /**
     * Interface LeakerListener specifies the interface to an object that receives
     * notifications from the Reporter and Leaker.
     *
     * @author  Jahongir Amirkulov
     * @version 04/25/18
     */
    public void send(byte[] cipher);
}
