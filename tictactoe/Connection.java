import java.rmi.*;
import java.util.List;

public interface Connection extends Remote {
    /* Sends mark to this or client depending on role */
    public void mark( final Pair coord, final char mark ) throws RemoteException;
    public boolean new_game() throws RemoteException;
}
