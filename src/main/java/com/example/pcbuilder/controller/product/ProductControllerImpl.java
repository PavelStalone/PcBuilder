package com.example.pcbuilder.controller.product;

import edu.rutmiit.example.pcbuildercontracts.controllers.product.ProductController;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;
//
//@Controller
//@RequestMapping("/product")
//public class ProductControllerImpl implements ProductController {
//
//    @Override
//    @GetMapping("/case")
//    public String caseList(@ModelAttribute("filter") CaseFilter caseFilter, Model model) {
//        return null;
//    }
//
//    @Override
//    @GetMapping("/cpu")
//    public String cpuList(@ModelAttribute("filter") CpuFilter cpuFilter, Model model) {
//
//        return null;
//    }
//
//    @Override
//    public String gpuList(GpuFilter gpuFilter, Model model) {
//        return null;
//    }
//
//    @Override
//    public String hddList(HddFilter hddFilter, Model model) {
//        return null;
//    }
//
//    @Override
//    public String motherboardList(MotherboardFilter motherboardFilter, Model model) {
//        return null;
//    }
//
//    @Override
//    public String powerList(PowerFilter powerFilter, Model model) {
//        return null;
//    }
//
//    @Override
//    public String ramList(RamFilter ramFilter, Model model) {
//        return null;
//    }
//
//    @Override
//    public String ssdList(SsdFilter ssdFilter, Model model) {
//        return null;
//    }
//
//    @Override
//    public String detailCase(UUID id, Model model) {
//        return null;
//    }
//
//    @Override
//    @GetMapping("/cpu/{id}")
//    public String detailCpu(UUID id, Model model) {
//
//
//        return null;
//    }
//
//    @Override
//    public String detailGpu(UUID id, Model model) {
//        return null;
//    }
//
//    @Override
//    public String detailHdd(UUID id, Model model) {
//        return null;
//    }
//
//    @Override
//    public String detailMotherboard(UUID id, Model model) {
//        return null;
//    }
//
//    @Override
//    public String detailPower(UUID id, Model model) {
//        return null;
//    }
//
//    @Override
//    public String detailRam(UUID id, Model model) {
//        return null;
//    }
//
//    @Override
//    public String detailSsd(UUID id, Model model) {
//        return null;
//    }
//}
