package controller;

import DAO.Impl.BaseDAOImpl;
import model.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
@CrossOrigin("http://localhost:8081")
public class UserEntityController extends BaseController<UserEntity> {

}
