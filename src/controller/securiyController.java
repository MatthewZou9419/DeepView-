package controller;

import DAO.Impl.SecurityDAOImpl;
import DAO.Impl.UserEntityDAOImpl;
import model.SecurityEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import util.BasicResponse;

@Controller
@RequestMapping("/security")
@CrossOrigin(origins="*")
public class securiyController extends BaseController<SecurityEntity> {
    ApplicationContext context =
            new ClassPathXmlApplicationContext("../applicationContext.xml");
    //此路径是在生成的class文件（out/artifacts/DeepView_war_exploded/WEB-INF/classes/controller/BaseController.class）中的相对路径
    SecurityDAOImpl securityDAO = (SecurityDAOImpl) context.getBean("SecurityDAOImpl");

    @RequestMapping(value="/dimSearchByCode",method = {RequestMethod.GET})
    public @ResponseBody
    BasicResponse dimSearchByCode(
            @RequestParam(value="code") String code) {
        BasicResponse response = new BasicResponse();
        response.setResCode("-1");
        response.setResMsg("Error");
        try{
            response.setData(securityDAO.dimSearchByCode(code));
            response.setResCode("1");
            response.setResMsg("success");
            return response;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value="/dimSearchByName",method = {RequestMethod.GET})
    public @ResponseBody
    BasicResponse dimSearchByName(
            @RequestParam(value="name") String name) {
        BasicResponse response = new BasicResponse();
        response.setResCode("-1");
        response.setResMsg("Error");
        try{
            response.setData(securityDAO.dimSearchByName(name));
            response.setResCode("1");
            response.setResMsg("success");
            return response;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return response;
    }
}
