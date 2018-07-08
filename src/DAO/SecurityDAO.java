package DAO;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface SecurityDAO {
    List<JSONObject> dimSearchByCode(String code);
    List<JSONObject> dimSearchByName(String name);
}
