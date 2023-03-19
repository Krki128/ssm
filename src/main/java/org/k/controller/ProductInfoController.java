package org.k.controller;

import com.github.pagehelper.PageInfo;
import org.json.JSONObject;
import org.k.dao.ProductInfo;
import org.k.service.ProductInfoService;
import org.k.utils.FileNameUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductInfoController {
    public static int PAGE_SIZE=5;
    ProductInfoService productInfoService;
    String fileName="";

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
    public String split(Model model,@RequestParam(defaultValue = "1") int page){
        PageInfo<ProductInfo> info= productInfoService.splitPage(page,PAGE_SIZE);
        model.addAttribute("info",info);
        return "product";
    }

    @ResponseBody
    @RequestMapping("/ajaxSplitPaging")
    public void ajaxSplitPaging(int page, HttpSession session){
        PageInfo<ProductInfo> pageInfo=productInfoService.splitPage(page,PAGE_SIZE);
        session.setAttribute("info",pageInfo);
    }

    @ResponseBody
    @RequestMapping("/ajaxImg")
    public Object ajaxImg (MultipartFile pimage, HttpServletRequest request){
        fileName= FileNameUtil.getUUIDFileName()+
                FileNameUtil.getRealFileName(pimage.getOriginalFilename());
        String path=request.getServletContext().getRealPath("/image_big");
        try{
            pimage.transferTo(new File(path+File.separator+fileName));
        }catch (IOException e){
            e.printStackTrace();
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("imgurl",fileName);
        return jsonObject.toString();
    }

    @GetMapping("/addProduct")
    public String addProduct(@RequestParam int page,Model model){
        model.addAttribute("page",page);
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(ProductInfo productInfo, HttpSession session){
        productInfo.setpImage(fileName);
        fileName="";
        productInfo.setpDate(new Date());
        int num=productInfoService.create(productInfo);
        if(num>0)
            session.setAttribute("msg","添加成功");
        else
            session.setAttribute("msg","添加失败");
        return "forward:/product/split";
    }

    @GetMapping("/updateProduct")
    public String updateProduct(@RequestParam int page,int pId,Model model){
        ProductInfo productInfo=productInfoService.selectByKey(pId);
        model.addAttribute("page",page);
        model.addAttribute("prod",productInfo);
        return "updateProduct";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(ProductInfo productInfo,HttpSession session){
        productInfo.setpImage(fileName);
        fileName="";
        int num=productInfoService.update(productInfo);
        if(num>0)
            session.setAttribute("msg","更新成功");
        else
            session.setAttribute("msg","更新失败");
        return "forward:/product/split";
    }
}
