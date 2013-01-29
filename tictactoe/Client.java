import java.rmi.*;
import java.rmi.server.*;
import java.util.List;
import java.io.*;

public class Client extends UnicastRemoteObject implements Connection {

    private Server server;

    public Client( final String address )
        throws ConnectException, NotBoundException, java.net.MalformedURLException, RemoteException {

        System.setSecurityManager( new LiberalSecurityManager() );
        this.server = (Server)Naming.lookup( "rmi://" + address + "/ttt" );
        System.out.println( "Connection set up with " + address );
    }

    public void mark( final Pair coord ) throws RemoteException, IntegrityException {
        this.server.mark( coord );
    }
}
