package test.java.businesslogic_mocktester.goodssortblmocktester;

import main.java.businesslogic.goodssortbl.GoodsSortTool;
import main.java.businesslogic_mock.goodssortblmock.GoodsSortToolMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoodsSortToolMockTester {
    GoodsSortTool tool=new GoodsSortToolMock();

    @Test
    public void find() throws Exception {
        assertEquals("",tool.find(null).getID());
    }

}