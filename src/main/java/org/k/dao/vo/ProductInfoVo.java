package org.k.dao.vo;

public class ProductInfoVo {
    private String pNameVo;
    private Integer typeIdVo;
    private Double lPriceVo;
    private Double hPriceVo;
    private  int pageNum;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public ProductInfoVo(String pNameVo, Integer typeIdVo, Double lPriceVo, Double hPriceVo) {
        this.pNameVo = pNameVo;
        this.typeIdVo = typeIdVo;
        this.lPriceVo = lPriceVo;
        this.hPriceVo = hPriceVo;
        this.pageNum = 1;
    }

    public String getpNameVo() {
        return pNameVo;
    }

    public void setpNameVo(String pNameVo) {
        this.pNameVo = pNameVo;
    }

    public Integer getTypeIdVo() {
        return typeIdVo;
    }

    public void setTypeIdVo(Integer typeIdVo) {
        this.typeIdVo = typeIdVo;
    }

    public Double getlPriceVo() {
        return lPriceVo;
    }

    public void setlPriceVo(Double lPriceVo) {
        this.lPriceVo = lPriceVo;
    }

    public Double gethPriceVo() {
        return hPriceVo;
    }

    public void sethPriceVo(Double hPriceVo) {
        this.hPriceVo = hPriceVo;
    }

    @Override
    public String toString() {
        return "ProductInfoVo{" +
                "pNameVo='" + pNameVo + '\'' +
                ", typeIdVo=" + typeIdVo +
                ", lPriceVo=" + lPriceVo +
                ", hPriceVo=" + hPriceVo +
                ", pageNum=" + pageNum +
                '}';
    }
}
