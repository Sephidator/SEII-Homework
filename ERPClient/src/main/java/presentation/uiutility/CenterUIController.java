package main.java.presentation.uiutility;

import main.java.presentation.mainui.RootUIController;
import main.java.vo.user.UserVO;

public class CenterUIController {
    protected RootUIController root;

    public void setRoot(RootUIController root){
        this.root=root;
    }

    public RootUIController getRoot() {
        return root;
    }

    public void instanceInit(RootUIController root){

    }

}
