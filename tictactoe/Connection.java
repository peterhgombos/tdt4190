import java.rmi.*;
import java.util.List;

public interface Connection extends Remote {
    /* Sends mark to this or client depending on role */
    public void mark( final List state ) throws RemoteException, IntegrityException;

    public boolean receive_state( final List state ) throws RemoteException, IntegrityException;
}
