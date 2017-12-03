package main.java.presentation.uiutility;

import main.java.presentation.mainui.RootUIController;
import main.java.vo.user.UserVO;

public class MyUIController {
    protected RootUIController root;
    protected UserVO operator;
    protected MyUIController returnPaneController;

    public void setRoot(RootUIController root){
        this.root=root;
    }

    public RootUIController getRoot() {
        return root;
    }

    public void setOperator(UserVO operator){
        this.operator=operator;
    }

    public UserVO getOperator() {
        return operator;
    }

    public void setReturnPaneController(MyUIController returnPaneController) {
        this.returnPaneController = returnPaneController;
    }

    public MyUIController getReturnPaneController() {
        return returnPaneController;
    }

    public void instanceInit(RootUIController root){

    }

}
