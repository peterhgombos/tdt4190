import java.rmi.*;
import java.util.List;

public interface Connection extends Remote {
    /* Sends mark to this or client depending on role */
    public void mark( final Pair coord ) throws RemoteException;
    public boolean register( final String address ) throws RemoteException;
    public boolean new_game() throws RemoteException;
}
