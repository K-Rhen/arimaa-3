package util.observer;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ObserveTest {
    private boolean ping = false;
    private ObserverTest testObserver;
    private Observable testObservable;

    @Before
    public void setUp() throws Exception {
        testObserver = new ObserverTest();
        testObservable = new Observable();
        testObservable.addObserver(testObserver);
    }

    @Test
    public void testNotify() {
        assertFalse(ping);
        testObservable.notifyObservers();
        assertTrue(ping);
    }

    @Test
    public void testRemove() {
        assertFalse(ping);
        testObservable.removeObserver(testObserver);
        testObservable.notifyObservers();
        assertFalse(ping);
    }

    @Test
    public void testRemoveAll() {
        assertFalse(ping);
        testObservable.removeAllObservers();
        testObservable.notifyObservers();
        assertFalse(ping);
    }

    class ObserverTest implements IObserver {
        // @Override
        public void update(Event e) {
            ping = true;
        }

    }

}