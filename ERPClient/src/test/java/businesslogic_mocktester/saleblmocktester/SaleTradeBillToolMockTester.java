package test.java.businesslogic_mocktester.saleblmocktester;

import main.java.businesslogic.salebl.SaleTradeBillTool;
import main.java.businesslogic_mock.saleblmock.SaleTradeBillToolMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class SaleTradeBillToolMockTester {
    SaleTradeBillTool tool=new SaleTradeBillToolMock();

    @Test
    public void findsByReport() throws Exception {
        assertEquals(0,tool.findsByReport(null).size());
    }

    @Test
    public void pass() throws Exception {
    }

    @Test
    public void reject() throws Exception {
    }

    @Test
    public void getSaleTradeBillList() throws Exception {
        assertEquals(0,tool.getSaleTradeBillList(null).size());
    }

}