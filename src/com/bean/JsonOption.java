package com.bean;

import com.entity.TbCartEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonOption {
    public static String goodSet2CartSet(String goodSet){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("goodSet",goodSet);
        return jsonObject.toString();
    }

    public static String carts2Order(TbCartEntity cartEntity, String orderInfo){
        JSONArray jsonArray;
        if(orderInfo=="default"){
            jsonArray = new JSONArray();
        }else {
            jsonArray = new JSONArray(orderInfo);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("goodCheck",cartEntity.getGoodCheck());
        jsonObject.put("goodCount",cartEntity.getGoodCount());
        jsonObject.put("goodId",cartEntity.getGoodId());
        jsonObject.put("goodName",cartEntity.getGoodName());
        jsonObject.put("goodPrice", cartEntity.getGoodPrice());
        jsonObject.put("goodSet", cartEntity.getGoodSet());
        jsonArray.put(jsonObject);
        return jsonArray.toString();
    }

    public static List<TbCartEntity> order2Cart(String orderInfo){
        List<TbCartEntity> list = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(orderInfo);
        TbCartEntity cartEntity;
        for(int i = 0; i < jsonArray.length();i++){
            cartEntity = new TbCartEntity();
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            cartEntity.setGoodCheck(jsonObject.getString("goodCheck"));
            cartEntity.setGoodCount(jsonObject.getInt("goodCount"));
            cartEntity.setGoodId(jsonObject.getString("goodId"));
            cartEntity.setGoodName(jsonObject.getString("goodName"));
            cartEntity.setGoodPrice(jsonObject.getInt("goodPrice"));
            cartEntity.setGoodSet(jsonObject.getString("goodSet"));
            list.add(cartEntity);
        }
        return list;
    }

    public static String makeUpSeedAddress(String owner, String phone, String address){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("address", address);
        jsonObject.put("phone", phone);
        jsonObject.put("owner", owner);
        return jsonObject.toString();
    }
    public static String getStringFromSendAddress(String sendAddress, String key){
        JSONObject jsonObject = new JSONObject(sendAddress);
        return (String) jsonObject.get(key);
    }

    public static String string2JsonArray(List<StatisticsBean> list){
        String arrayString = "[\"";
        int count = 0;
        int total = list.size();
        for (StatisticsBean currBean :
                list) {
            arrayString = arrayString + currBean.getGoodsName();
            count++;
            if(count<total){
                arrayString += "\",\"";
            }
        }
        arrayString = arrayString + "\"]";
        return arrayString;
    }

    public static String count2JsonArray(List<StatisticsBean> list) {
        String arrayString = "[";
        int count = 0;
        int total = list.size();
        for (StatisticsBean currBean :
                list) {
            arrayString = arrayString + currBean.getCount();
            count++;
            if(count<total){
                arrayString += ",";
            }
        }
        arrayString = arrayString + "]";
        return arrayString;
    }

    public static String price2JsonArray(List<StatisticsBean> list) {
        String arrayString = "[";
        int count = 0;
        int total = list.size();
        for (StatisticsBean currBean :
                list) {
            arrayString = arrayString + currBean.getGoodsPrice();
            count++;
            if(count<total){
                arrayString += ",";
            }
        }
        arrayString = arrayString + "]";
        return arrayString;
    }
}
