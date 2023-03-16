package org.k.controller;

import com.github.pagehelper.PageInfo;
import org.k.dao.ProductInfo;
import org.k.service.ProductInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductInfoController {
    public static int PAGE_SIZE=5;
    ProductInfoService productInfoService;

    public ProductInfoController(ProductInfoService productInfoService) {
        this.productInfoService = productInfoService;
    }

    @RequestMapping("/getAll")
    public String getAll(Model model){
        List<ProductInfo> productInfoList=productInfoService.getAll();
        model.addAttribute(productInfoList);
        return "product";
    }

    @RequestMapping("/split")
    public String split(Model model){
        PageInfo<ProductInfo> info= productInfoService.splitPage(1,PAGE_SIZE);
        model.addAttribute("info",info);
        return "product";
    }

    @ResponseBody
    @RequestMapping("/ajaxSplitPaging")
    public void ajaxSplitPaging(int page, HttpSession session){
        PageInfo<ProductInfo> pageInfo=productInfoService.splitPage(page,PAGE_SIZE);
        session.setAttribute("info",pageInfo);
    }

}
