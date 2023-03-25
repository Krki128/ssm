package org.k.dao.vo;

public class ProductInfoVo {
    private String pName;
    private Integer typeId;
    private Double lPrice;
    private Double hPrice;
    private  int pageNum;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public ProductInfoVo(String pName, Integer typeId, Double lPrice, Double hPrice) {
        this.pName = pName;
        this.typeId = typeId;
        this.lPrice = lPrice;
        this.hPrice = hPrice;
        this.pageNum = 1;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Double getlPrice() {
        return lPrice;
    }

    public void setlPrice(Double lPrice) {
        this.lPrice = lPrice;
    }

    public Double gethPrice() {
        return hPrice;
    }

    public void sethPrice(Double hPrice) {
        this.hPrice = hPrice;
    }
}
