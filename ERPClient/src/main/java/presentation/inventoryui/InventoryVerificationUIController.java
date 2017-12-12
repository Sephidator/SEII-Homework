package main.java.presentation.inventoryui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.java.MainApp;
import main.java.businesslogicservice.goodsblservice.GoodsBlService;
import main.java.presentation.mainui.RootUIController;
import main.java.presentation.messageui.InventoryPanelUIController;
import main.java.presentation.uiutility.CenterUIController;
import main.java.vo.goods.GoodsVO;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InventoryVerificationUIController extends CenterUIController {
    private GoodsBlService goodsBlService;

    private ObservableList<GoodsVO> goodsObservableList= FXCollections.observableArrayList();
    @FXML
    private TableView<GoodsVO> goodsTableView;
    @FXML
    private TableColumn<GoodsVO,String> goodsIDColumn;
    @FXML
    private TableColumn<GoodsVO,String> goodsNameColumn;
    @FXML
    private TableColumn<GoodsVO,String> goodsModelColumn;
    @FXML
    private TableColumn<GoodsVO,String> goodsCostColumn;
    @FXML
    private TableColumn<GoodsVO,String> goodsRetailColumn;
    @FXML
    private TableColumn<GoodsVO,String> goodsNumberColumn;
    @FXML
    private TableColumn<GoodsVO,String> goodsCommentColumn;

    // 加载文件后调用的方法******************************************

    /**
     * 设置显示的客户信息以及显示方法
     * */
    public void initialize(){
        goodsIDColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getID()));
        goodsNameColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getName()));
        goodsModelColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getModel()));
        goodsCostColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getCost())));
        goodsRetailColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getRetail())));
        goodsNumberColumn.setCellValueFactory(cellData->new SimpleStringProperty(String.valueOf(cellData.getValue().getNumber())));
        goodsCommentColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getComment()));
    }

    // 设置controller数据的方法*****************************************

    public void setGoodsBlService(GoodsBlService goodsBlService) {
        this.goodsBlService = goodsBlService;
        //ArrayList<GoodsVO> goodsList=goodsBlService.getGoodsList(null);
        //showGoodsList(goodsList);
    }

    private void refresh(){
        //ArrayList<GoodsVO> goodsList=goodsBlService.getGoodsList(null);
        //showGoodsList(goodsList);
    }

    /**
     * 取得客户列表并修改ObservableList的信息
     * */
    private void showGoodsList(ArrayList<GoodsVO> goodsList){
        goodsObservableList.removeAll();

        for(int i=0;i<goodsList.size();i++){
            goodsObservableList.add(goodsList.get(i));
        }
        goodsTableView.setItems(goodsObservableList);
    }

    // 界面之中会用到的方法******************************************

    @FXML
    private void handleExport(){
        try {
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
            String day=sdf.format(new Date());

            String filename = "C:/导出报表/"+day+"库存盘点"+".xls" ;
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("First Sheet");

            HSSFRow rowHead = sheet.createRow((short)0);
            rowHead.createCell(0).setCellValue("商品编号");
            rowHead.createCell(1).setCellValue("名称");
            rowHead.createCell(2).setCellValue("型号");
            rowHead.createCell(3).setCellValue("进价");
            rowHead.createCell(4).setCellValue("零售价");
            rowHead.createCell(5).setCellValue("数量");
            rowHead.createCell(6).setCellValue("备注");

            ObservableList<GoodsVO> list=goodsTableView.getItems();
            for(int i=0;i<list.size();i++){
                HSSFRow row = sheet.createRow((short)(i+1));
                row.createCell(0).setCellValue(list.get(i).getID());
                row.createCell(1).setCellValue(list.get(i).getName());
                row.createCell(2).setCellValue(list.get(i).getModel());
                row.createCell(3).setCellValue(list.get(i).getCost());
                row.createCell(4).setCellValue(list.get(i).getRetail());
                row.createCell(5).setCellValue(list.get(i).getNumber());
                row.createCell(6).setCellValue(list.get(i).getComment());
            }

            File file=new File(filename);
            if(!file.getParentFile().exists()) {
                //如果目标文件所在的目录不存在，则创建父目录
                System.out.println("目标文件所在目录不存在，准备创建它！");
                file.getParentFile().mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(file);
            workbook.write(fileOut);
            fileOut.close();

            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("导出报表成功");
            alert.setContentText("文件路径："+filename);
            alert.showAndWait();

        } catch ( Exception ex ) {
            System.out.println(ex);
        }
    }


    // 加载文件和界面的方法******************************************

    /**
     * 初始化方法，调用init方法
     * 之所以有这个方法是为了多态而提供的
     * */
    public void instanceInit(RootUIController root){
        init(root);
    }

    /**
     * 静态初始化方法，加载相应的FXML文件，并添加一些信息
     * */
    public static void init(RootUIController root){
        try{
            // 加载登陆界面
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/main/java/presentation/inventoryui/InventoryVerificationUI.fxml"));
            root.setCenterPane(loader.load());

            InventoryVerificationUIController controller=loader.getController();
            controller.setRoot(root);
            controller.setGoodsBlService(null);

            GoodsVO c1=new GoodsVO("台灯1", null, "",50,20,30,20,30,10,"备注");
            c1.setID("123");
            GoodsVO c2=new GoodsVO("台灯1", null, "",50,20,30,20,30,10,"备注");
            c2.setID("123");

            ArrayList<GoodsVO> list=new ArrayList<>();
            list.add(c1);
            list.add(c2);

            controller.showGoodsList(list);

            root.setReturnPaneController(new InventoryPanelUIController());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
