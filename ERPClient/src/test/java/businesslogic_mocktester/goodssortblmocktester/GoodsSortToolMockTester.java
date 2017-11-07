package businesslogic_mocktester.goodssortblmocktester;

import businesslogic.goodssortbl.GoodsSortTool;
import businesslogic_mock.goodssortblmock.GoodsSortToolMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoodsSortToolMockTester {

    GoodsSortTool goodsSortTool=new GoodsSortToolMock();

    @Test
    public void getGoodsSortList() throws Exception {
        assertEquals(1,goodsSortTool.getGoodsSortList(null).size());
    }

}