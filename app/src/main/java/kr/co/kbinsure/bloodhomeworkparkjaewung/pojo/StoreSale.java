package kr.co.kbinsure.bloodhomeworkparkjaewung.pojo;

import com.google.gson.annotations.SerializedName;

public class StoreSale{
    @SerializedName("addr")
    public String address="";
    public String code="";
    public String name="";
    public String type="01"; //판매처 유형[약국: '01', 우체국: '02', 농협: '03']

    @SerializedName("lat")
    public Float latitude;

    @SerializedName("lng")
    public Float longitude;

    @SerializedName("stock_at")
    public String insertedData = "";

    /**
     * 재고 상태[100개 이상(녹색): 'plenty'
     * 30개 이상 100개미만(노랑색): 'some'
     * 2개 이상 30개 미만(빨강색): 'few'
     * 1개 이하(회색): 'empty'
     * 판매중지: 'break']
     */
    @SerializedName("remain_stat")
    public String remainStatus = "";

    @SerializedName("created_at")
    public String createdDate = "";
}
