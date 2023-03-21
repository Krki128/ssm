package org.k.dao.vo;

public class ProductInfoVo {

    private String pname;
    private Integer typeid;
    private Double lprice;

    private Double hprice;

    public ProductInfoVo(String pname, Integer typeid, Double lprice, Double hprice) {
        this.pname = pname;
        this.typeid = typeid;
        this.lprice = lprice;
        this.hprice = hprice;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public Double getLprice() {
        return lprice;
    }

    public void setLprice(Double lprice) {
        this.lprice = lprice;
    }

    public Double getHprice() {
        return hprice;
    }

    public void setHprice(Double hprice) {
        this.hprice = hprice;
    }

    @Override
    public String toString() {
        return "ProductInfoVo{" +
                "pname='" + pname + '\'' +
                ", typeid=" + typeid +
                ", lprice=" + lprice +
                ", hprice=" + hprice +
                '}';
    }
}
