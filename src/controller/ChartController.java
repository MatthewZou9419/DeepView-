package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import util.BasicResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Controller
@RequestMapping("/chart")
@CrossOrigin("http://localhost:8081")
public class ChartController {
    @RequestMapping(value="/realTimePrice",method={RequestMethod.GET})
    public @ResponseBody
    BasicResponse getPrice(@RequestParam String secuCode, HttpServletRequest request){
        BasicResponse response = new BasicResponse();
        response.setResCode("1");
        response.setResMsg("success");
        try {
            String path=getClass().getResource("").getPath();
            path=path.substring(1,path.length()-11)+ "python/" + "realTimePrice.py";
            String[] runPython=new String[]{"python",path,secuCode};
            Process pr=Runtime.getRuntime().exec(runPython);
            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            boolean flag=true;//去除返回值第一行的auth success
            while ((line = in.readLine()) != null) {
                if(!flag){
                    stringBuffer.append(line);
                }
                flag=false;
                System.out.println("*");
            }
            in.close();
            pr.waitFor();
            String result = stringBuffer.toString();
//            System.out.println(result);
//            System.out.println(path);
            response.setData(result);
        }catch (Exception e){
            response.setResCode("-1");
            response.setResMsg("error");
            e.printStackTrace();
        }
        return response;
    }
}
