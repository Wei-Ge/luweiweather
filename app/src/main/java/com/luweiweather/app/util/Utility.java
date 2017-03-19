package com.luweiweather.app.util;

import android.text.TextUtils;

import com.luweiweather.app.db.LuweiWeatherDB;
import com.luweiweather.app.model.City;
import com.luweiweather.app.model.County;
import com.luweiweather.app.model.Province;

/**
 * Created by user on 2017/3/18 0018.
 */
public class Utility {
    /*
    * 解析和处理服务器返回的省级数据
    * */
    public synchronized static boolean handleProvincesResponse(LuweiWeatherDB luweiWeatherDB,String response){
        if (!TextUtils.isEmpty(response)){
            String[] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length>0){
                for (String p : allProvinces){
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    luweiWeatherDB.saveProvince(province);
                }
                return true;
            }

        }
        return false;
    }
    /*
    * 解析和处理服务器返回的市级数据
    * */
    public  static boolean handleCitiesResponse(LuweiWeatherDB luweiWeatherDB,String response,int provinceId){
        if (!TextUtils.isEmpty(response)){
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length>0){
                for (String c : allCities){
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    luweiWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }
    /*
    * 解析和处理服务器返回的县级数据
    * */
    public static boolean handleCountiesResponse(LuweiWeatherDB luweiWeatherDB,String response,int cityId){
        if (!TextUtils.isEmpty(response)){
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length>0){
                for (String c : allCounties){
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    luweiWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}
