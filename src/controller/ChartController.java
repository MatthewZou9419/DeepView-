package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import util.BasicResponse;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

@Controller
@RequestMapping("/chart")
@CrossOrigin("http://localhost:8081")
public class ChartController{

    @RequestMapping(value="/realTimePrice",method={RequestMethod.GET})
    public @ResponseBody
    BasicResponse realTimePrice(@RequestParam String secuCode, HttpServletRequest request){
        BasicResponse response = new BasicResponse();
        response.setResCode("1");
        response.setResMsg("success");
        try {
            ArrayList<String> result=RunPython("realTimePrice.py",new String[]{secuCode});
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secuName",result.get(0));
            jsonObject.put("chartData", result.get(1));
            response.setData(jsonObject);
        }catch (Exception e){
            response.setResCode("-1");
            response.setResMsg("error");
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value="/correlation",method={RequestMethod.GET})
    public @ResponseBody
    BasicResponse correlation(@RequestParam ArrayList<String> secuCode, @RequestParam String startDate, @RequestParam String endDate, HttpServletRequest request){
        BasicResponse response = new BasicResponse();
        response.setResCode("1");
        response.setResMsg("success");
        try {
            String[] argv = new String[secuCode.size() + 2];
            argv[0]=startDate;
            argv[1]=endDate;
            System.arraycopy(secuCode.toArray(new String[secuCode.size()]),0,argv,2,secuCode.size());
            ArrayList<String> result=RunPython("correlation.py",argv);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secuNames",result.get(0));
            jsonObject.put("chartData", result.get(1));
            response.setData(jsonObject);
        }catch (Exception e){
            response.setResCode("-1");
            response.setResMsg("error");
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value="/regression",method={RequestMethod.GET})
    public @ResponseBody
    BasicResponse regression(@RequestParam String secuCode1, @RequestParam String secuCode2, @RequestParam String startDate, @RequestParam String endDate, HttpServletRequest request){
        BasicResponse response = new BasicResponse();
        response.setResCode("1");
        response.setResMsg("success");
        try {
            ArrayList<String> result = RunPython("regression.py", new String[]{secuCode1, secuCode2, startDate, endDate});
            System.out.println(result.size());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secuName1",result.get(0));
            jsonObject.put("secuName2",result.get(1));
            jsonObject.put("legendData", result.get(2));
            jsonObject.put("chartData", result.get(3));
            response.setData(jsonObject);
        }catch (Exception e){
            response.setResCode("-1");
            response.setResMsg("error");
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value="/priceCompare",method={RequestMethod.GET})
    public @ResponseBody
    BasicResponse priceCompare(@RequestParam String secuCode1, @RequestParam String secuCode2, @RequestParam String startDate, @RequestParam String endDate, HttpServletRequest request){
        BasicResponse response = new BasicResponse();
        response.setResCode("1");
        response.setResMsg("success");
        try {
            ArrayList<String> result = RunPython("priceCompare.py", new String[]{secuCode1, secuCode2, startDate, endDate});
            System.out.println(result.size());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secuName1",result.get(0));
            jsonObject.put("secuName2",result.get(1));
            jsonObject.put("chartData", result.get(2));
            response.setData(jsonObject);
        }catch (Exception e){
            response.setResCode("-1");
            response.setResMsg("error");
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value="/season",method={RequestMethod.GET})
    public @ResponseBody
    BasicResponse season(@RequestParam String secuCode, @RequestParam String yearNum, HttpServletRequest request){
        BasicResponse response = new BasicResponse();
        response.setResCode("1");
        response.setResMsg("success");
        try {
            ArrayList<String> result = RunPython("season.py", new String[]{secuCode, yearNum});
            System.out.println(result.size());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secuName",result.get(0));
            jsonObject.put("chartData", result.get(1));
            response.setData(jsonObject);
        }catch (Exception e){
            response.setResCode("-1");
            response.setResMsg("error");
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value="/history",method={RequestMethod.GET})
    public @ResponseBody
    BasicResponse history(@RequestParam String secuCode, @RequestParam String monthNum, HttpServletRequest request){
        BasicResponse response = new BasicResponse();
        response.setResCode("1");
        response.setResMsg("success");
        try {
            ArrayList<String> result = RunPython("history.py", new String[]{secuCode, monthNum});
            System.out.println(result.size());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secuName",result.get(0));
            jsonObject.put("window",result.get(1));
            jsonObject.put("chartData", result.get(2));
            response.setData(jsonObject);
        }catch (Exception e){
            response.setResCode("-1");
            response.setResMsg("error");
            e.printStackTrace();
        }
        return response;
    }

    public ArrayList<String> RunPython(String fileName, String [] argv ) throws Exception {
        String path=getClass().getResource("").getPath();
        path=path.substring(1,path.length()-11)+ "python/" + fileName;
        String[] runpy= new String[2+argv.length];
        runpy[0] = "python";
        runpy[1] = path;
        System.arraycopy(argv, 0, runpy, 2, argv.length);
        for (int i = 0; i < runpy.length; i++) {
            System.out.println(runpy[i]);
        }
        Process pr=Runtime.getRuntime().exec(runpy);
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        ArrayList<String> result = new ArrayList<>();
        String line;
        boolean flag=true;//去除返回值第一行的auth success
        while ((line = in.readLine()) != null) {
            if(!flag){
                System.out.println(line);
                result.add(line);
            }
            flag=false;
        }
        in.close();
        pr.waitFor();
        return result;
    }
}
