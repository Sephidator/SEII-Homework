package businesslogicservice.systemfunctionicservice;

import vo.ClientVO;

public interface LoginBlService {
    public ClientVO findClient(String ID, String password);
}
