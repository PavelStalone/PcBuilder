package com.example.pcbuilder.controller.product;

import com.example.pcbuilder.common.log.Log;
import edu.rutmiit.example.pcbuildercontracts.controllers.product.ProductController;
import edu.rutmiit.example.pcbuildercontracts.dto.base.BaseViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.ProductListViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.ProductViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductControllerImpl implements ProductController {

    @Override
    public String productList(Model model) {
        Log.d("productList called");

        var productList = new ProductListViewModel(
                createBaseViewModel("Комплектующие"),
                List.of(
                        new ProductViewModel(null, "Корпус", "case"),
                        new ProductViewModel(null, "Процессор", "cpu"),
                        new ProductViewModel(null, "Видеокарта", "gpu"),
                        new ProductViewModel(null, "Жесткий диск", "hdd"),
                        new ProductViewModel(null, "Материнская плата", "motherboard"),
                        new ProductViewModel(null, "Блок питания", "power"),
                        new ProductViewModel(null, "Оперативная память", "ram"),
                        new ProductViewModel(null, "SSD диск", "ssd")
                )
        );
        model.addAttribute("model", productList);

        Log.i("Open products list");
        return "product/product-list";
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }
}
