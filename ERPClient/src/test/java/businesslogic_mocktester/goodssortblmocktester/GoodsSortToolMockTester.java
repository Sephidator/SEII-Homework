package test.java.businesslogic_mocktester.goodssortblmocktester;

import main.java.businesslogic.goodssortbl.GoodsSortTool;
import main.java.businesslogic_mock.goodssortblmock.GoodsSortToolMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoodsSortToolMockTester {

    GoodsSortTool goodsSortTool=new GoodsSortToolMock();

    @Test
    public void getGoodsSortList() throws Exception {
        assertEquals(1,goodsSortTool.getGoodsSortList(null).size());
    }

}