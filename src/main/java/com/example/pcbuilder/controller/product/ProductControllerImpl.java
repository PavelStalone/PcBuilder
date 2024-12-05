package com.example.pcbuilder.controller.product;

import edu.rutmiit.example.pcbuildercontracts.controllers.product.ProductController;
import edu.rutmiit.example.pcbuildercontracts.dto.base.BaseViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.*;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.ProductListViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.ProductViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/product")
public class ProductControllerImpl implements ProductController {

    @Override
    public String productList(Model model) {
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

        return "product/product-list";
    }

    @Override
    @GetMapping("/case")
    public String caseList(@ModelAttribute("filter") CaseFilter caseFilter, Model model) {
        return null;
    }

    @Override
    public String gpuList(GpuFilter gpuFilter, Model model) {
        return null;
    }

    @Override
    public String hddList(HddFilter hddFilter, Model model) {
        return null;
    }

    @Override
    public String motherboardList(MotherboardFilter motherboardFilter, Model model) {
        return null;
    }

    @Override
    public String powerList(PowerFilter powerFilter, Model model) {
        return null;
    }

    @Override
    public String ramList(RamFilter ramFilter, Model model) {
        return null;
    }

    @Override
    public String ssdList(SsdFilter ssdFilter, Model model) {
        return null;
    }

    @Override
    public String detailCase(UUID id, Model model) {
        return null;
    }

    @Override
    public String detailGpu(UUID id, Model model) {
        return null;
    }

    @Override
    public String detailHdd(UUID id, Model model) {
        return null;
    }

    @Override
    public String detailMotherboard(UUID id, Model model) {
        return null;
    }

    @Override
    public String detailPower(UUID id, Model model) {
        return null;
    }

    @Override
    public String detailRam(UUID id, Model model) {
        return null;
    }

    @Override
    public String detailSsd(UUID id, Model model) {
        return null;
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }
}
