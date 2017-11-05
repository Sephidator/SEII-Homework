package businesslogicservice.systemfunctionicservice;

import vo.client.ClientVO;

public interface LoginBlService {
    public ClientVO findClient(String ID, String password);
}
