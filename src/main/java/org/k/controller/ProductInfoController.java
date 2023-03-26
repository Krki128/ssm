package org.k.controller;

import com.github.pagehelper.PageInfo;
import org.json.JSONObject;
import org.k.dao.ProductInfo;
import org.k.dao.vo.ProductInfoVo;
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
import java.util.Objects;

@Controller
@RequestMapping("/product")
public class ProductInfoController {
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
    public String split(ProductInfoVo productInfoVo, HttpSession session){
        PageInfo<ProductInfo> pageInfo= productInfoService.splitPage(productInfoVo);
        session.setAttribute("info",pageInfo);
        session.setAttribute("productInfoVo",productInfoVo);
        return "product";
    }

    @ResponseBody
    @RequestMapping("/ajaxSplitPaging")
    public void ajaxSplitPaging(ProductInfoVo productInfoVo,HttpSession session){
        PageInfo<ProductInfo> pageInfo=productInfoService.splitPage(productInfoVo);
        session.setAttribute("productInfoVo",productInfoVo);
        session.setAttribute("info",pageInfo);
    }

    @ResponseBody
    @RequestMapping("/ajaxImg")
    public Object ajaxImg (MultipartFile pImage, HttpServletRequest request){
        System.out.println("ajax Img");
        fileName= FileNameUtil.getUUIDFileName()+
                FileNameUtil.getRealFileName((Objects.requireNonNull(pImage.getOriginalFilename())));
        String path=request.getServletContext().getRealPath("/image_big");
        try{
            pImage.transferTo(new File(path+File.separator+fileName));
        }catch (IOException e){
            e.printStackTrace();
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("imgUrl",fileName);
        return jsonObject.toString();
    }

    @GetMapping("/createProduct")
    public String createProduct(ProductInfoVo productInfoVo, Model model){
        model.addAttribute("productInfoVo",productInfoVo);
        return "addProduct";
    }

    @PostMapping("/createProduct")
    public String createProduct(ProductInfo productInfo, ProductInfoVo productInfoVo, Model model){
        productInfo.setpImage(fileName);
        fileName="";
        productInfo.setpDate(new Date());
        int num=productInfoService.createProduct(productInfo);
        String string=num>0?"添加成功":"添加失败";
        model.addAttribute("msg",string);
        model.addAttribute("productInfoVo",productInfoVo);
        return "forward:/product/split";
    }

    @GetMapping("/updateProduct")
    public String updateProduct(int pId,ProductInfoVo productInfoVo,Model model){
        ProductInfo productInfo=productInfoService.selectByKey(pId);
        model.addAttribute("productInfoVo",productInfoVo);
        model.addAttribute("prod",productInfo);
        return "updateProduct";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(ProductInfo productInfo,ProductInfoVo productInfoVo,Model model){
        if(!fileName.equals("")){
            productInfo.setpImage(fileName);
            fileName = "";
        }
        int num=productInfoService.update(productInfo);
        String string=num>0?"添加成功":"添加失败";
        model.addAttribute("msg",string);
        model.addAttribute("productInfoVo",productInfoVo);
        return "forward:/product/split";
    }

    @ResponseBody
    @PostMapping("/ajaxDeleteProduct")
    public Object ajaxDeleteProduct(String pIds, ProductInfoVo productInfoVo, HttpSession session){
        String[] temp=pIds.split(",");
        int num=productInfoService.deleteBatch(temp);
        PageInfo<ProductInfo> pageInfo=productInfoService.splitPage(productInfoVo);
        session.setAttribute("info",pageInfo);
        JSONObject jsonObject=new JSONObject();

        if(num==temp.length)
            jsonObject.put("state", 1);
        else
            jsonObject.put("state", 0);
        return jsonObject.toString();
    }

    @ResponseBody
    @PostMapping("/ajaxCondition")
    public void ajaxCondition(ProductInfoVo productInfoVo, HttpSession session){
        PageInfo<ProductInfo> productInfoList=productInfoService.selectCondition(productInfoVo);
        session.setAttribute("info",productInfoList);
    }
}
