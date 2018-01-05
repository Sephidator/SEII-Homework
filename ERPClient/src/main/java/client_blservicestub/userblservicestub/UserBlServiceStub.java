package main.java.client_blservicestub.userblservicestub;

import main.java.businesslogicservice.userblservice.UserBlService;
import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class UserBlServiceStub implements UserBlService {

    @Override
    public ArrayList<UserVO> getUserList(UserQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public String addUser(UserVO vo) throws Exception {
        return "";
    }

    @Override
    public void editUser(UserVO vo) throws Exception {

    }

    @Override
    public void deleteUser(String UserID) throws Exception {

    }
}
