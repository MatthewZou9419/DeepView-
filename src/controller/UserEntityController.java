package controller;

import DAO.Impl.UserEntityDAOImpl;
import model.UserEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import util.BasicResponse;

@Controller
@RequestMapping("/user")
@CrossOrigin("http://localhost:8081")
public class UserEntityController extends BaseController<UserEntity> {
    ApplicationContext context =
            new ClassPathXmlApplicationContext("../applicationContext.xml");
    //此路径是在生成的class文件（out/artifacts/DeepView_war_exploded/WEB-INF/classes/controller/BaseController.class）中的相对路径
    UserEntityDAOImpl userEntityDAO = (UserEntityDAOImpl) context.getBean("UserEntityDAOImpl");

    @RequestMapping(value="/login",method = {RequestMethod.POST})
    public @ResponseBody BasicResponse login(
            @RequestParam(value="name") String name,
            @RequestParam(value="password") String password) {
        BasicResponse response = new BasicResponse();
        response.setResCode("-1");
        response.setResMsg("Error");
        try{
            boolean login = userEntityDAO.login(name, password);
            if(login){
                response.setResCode("1");
                response.setResMsg("success");
            }
            return response;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return response;
    }
}
