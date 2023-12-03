package model.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageTest {

    @Test
    public void testEmptyMessage(){
        Message m = new Message();
        assertAll(
                ()-> assertNull(m.getID()),
                ()-> assertNull(m.getReceiver()),
                ()-> assertNull(m.getSender()),
                ()-> assertNull(m.getMessage())
        );
    }

    @Test public void testConstructor(){
        Message m = new Message(1,2, "Message");
        assertAll(
                ()-> assertEquals(m.getReceiver(),2),
                ()->assertEquals(m.getSender(), 1),
                ()->assertEquals(m.getMessage(), "Message")
        );
    }
    @Test public void testSetReceiver(){
        Message m = new Message();
        m.setReceiver(3);
        assertEquals(m.getReceiver(), 3);
    }
    @Test public void testSetSender(){
        Message m = new Message();
        m.setSender(3);
        assertEquals(m.getSender(), 3);
    }
    @Test public void testSetMessage(){
        Message m = new Message();
        m.setMessage("Hi");
        assertEquals(m.getMessage(), "Hi");
    }

}


