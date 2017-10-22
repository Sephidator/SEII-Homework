package client_blservicestub.systemfunctionblservicestub;

import businesslogicservice.systemfunctionicservice.LoginBlService;
import vo.ClientVO;

public class LoginBlServiceStub implements LoginBlService{

    @Override
    public ClientVO findClient(String ID, String password) {
        return new ClientVO();
    }
}
