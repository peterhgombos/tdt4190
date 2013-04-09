import java.rmi.*;
import java.util.*;

/**
 * A resource with an associated lock that can be
 * held by only one transaction at a time.
 */
public class Resource
{
	/** The server where this resource is located */
	private ServerImpl server;
	/** The transaction currently holding the lock to this resource */
	private Integer lockOwner;

	/**
	 * Creates a new resource.
	 * @param server	The server where this resource is located.
	 */
	public Resource(ServerImpl server) {
		this.server = server;
		lockOwner = null;
	}

	/**
	 * Gives the lock of this resource to the requesting transaction. Blocks
	 * the caller until the lock could be acquired.
	 *
	 * @param transactionId		The ID of the transaction that wants the lock.
	 * @return					Whether or not the lock could be acquired.
	 */
	public synchronized boolean lock(int transactionId) {
		if(lockOwner == null) {
			lockOwner = new Integer(transactionId);
			return true;
		}
		else {
			// Wait for the lock
			try	{
				wait();
			} catch (InterruptedException ie) {
				return false;
			}
			lockOwner = new Integer(transactionId);
			return true;
		}
	}

        // Wait for the lock
        try	{
            wait( 3000 );
            // java <3
            // the worst hack I have ever seen to overcome the retardness the
            // of the java standard library. as wait() is broken and doesn't
            // actually differentiate the cases of timeout and notifying this
            // has to be handled manually. java - write once, run away
            if( lockOwner != null ) throw new InterruptedException();
        } catch (InterruptedException ie) {
            return false;
        }

	/**
	 * Gets the current owner of this resource's lock.
	 * @return	An Integer containing the ID of the transaction currently
	 *			holding the lock, or null if the resource is unlocked.
	 */
	public Integer getLockOwner() {
		return lockOwner;
	}

	/**
	 * Unconditionally releases the lock of this resource.
	 */
	public void forceUnlock() {
		unlock(lockOwner.intValue());
	}

	/**
	 * Checks if this resource's lock is held by a transaction running on the specified server.
	 * @param serverId	The ID of the server.
	 * @return			Whether or not the current lock owner is running on that server.
	 */
	public boolean isLockedByServer(int serverId) {
		return (lockOwner != null && server.getTransactionOwner(lockOwner.intValue()) == serverId);
	}
}
