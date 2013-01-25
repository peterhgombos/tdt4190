public interface Connection extends Remote {
    /* Sends mark to this or client depending on role */
    public void mark( final List state );

    private boolean send_state( final List state ) throws RemoteException;
    private void receive_state( final List state ) throws RemoteException; 
}
