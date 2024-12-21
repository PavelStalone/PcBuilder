package com.example.pcbuilder.controller.product;

import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.service.product.contract.HddService;
import edu.rutmiit.example.pcbuildercontracts.controllers.product.HddController;
import edu.rutmiit.example.pcbuildercontracts.dto.base.BaseViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.HddDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.HddFilter;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.hdd.HddDetailsViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.hdd.HddInputViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.hdd.HddListViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.hdd.HddViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import static com.example.pcbuilder.common.fake.FakeData.hddInterfaces;

@Controller
@RequestMapping("/product/hdd")
public class HddControllerImpl implements HddController {

    private final HddService service;

    @Autowired
    public HddControllerImpl(HddService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public String list(
            @ModelAttribute("filter") HddFilter filter,
            BindingResult bindingResult,
            Model model
    ) {
        Log.d("list called - filter: " + filter);

        var filterIn = new HddFilter(
                filter.page() != null ? filter.page() : 1,
                filter.size() != null ? filter.size() : 10,
                filter.model(),
                filter.isDescCost(),
                filter.costLower(),
                filter.costUpper(),
                filter.powerLower(),
                filter.powerUpper(),
                filter.memoryLower(),
                filter.memoryUpper(),
                filter.rotationLower(),
                filter.rotationUpper()
        );
        var result = service.getAllByFilter(filterIn);
        var pages = result.list()
                .stream()
                .map((it) -> Mapper.createTypeMap(HddDto.class, HddViewModel.class).map(it));

        var viewModel = new HddListViewModel(
                createBaseViewModel("Жесткие диски"),
                pages.toList(),
                result.pages()
        );

        model.addAttribute("model", viewModel);
        model.addAttribute("filter", bindingResult.hasErrors() ? filter : filterIn);

        Log.i("Open hdd list on %d page", filterIn.page());
        return "product/hdd/list";
    }

    @Override
    @GetMapping("/{id}")
    public String detail(
            UUID id,
            Model model
    ) {
        Log.d("detail called - id: " + id);

        var details = new HddDetailsViewModel(
                createBaseViewModel("Жесткий диск"),
                service.getById(id).orElse(null)
        );

        model.addAttribute("model", details);

        Log.i("Open detail for hdd with id: " + id);
        return "product/hdd/details";
    }

    @Override
    @GetMapping("/create")
    public String createForm(Model model) {
        Log.d("createForm called");

        var details = new HddInputViewModel(
                createBaseViewModel("Добавление Жесткого диска"),
                hddInterfaces
        );

        model.addAttribute("model", details);
        model.addAttribute("form", new HddDto());

        Log.i("Open creating page for hdd");
        return "product/hdd/create";
    }

    @Override
    @PostMapping("/create")
    public String create(
            @ModelAttribute("form") HddDto form,
            BindingResult bindingResult,
            Model model
    ) {
        Log.d("Create called - form: " + form);

        if (bindingResult.hasErrors()) {
            var details = new HddInputViewModel(
                    createBaseViewModel("Добавление видеокарты"),
                    hddInterfaces
            );

            model.addAttribute("model", details);
            model.addAttribute("form", form);

            return "product/hdd/create";
        }

        var id = service.create(form);

        Log.i("Create new hdd with id: " + id);
        return "redirect:/product/hdd/" + id;
    }

    @Override
    @GetMapping("/{id}/edit")
    public String editForm(
            UUID id,
            Model model
    ) {
        Log.d("editForm called - id: " + id);

        var details = new HddInputViewModel(
                createBaseViewModel("Редактирование жесткого диска"),
                hddInterfaces
        );
        var form = service.getById(id).orElse(null);

        model.addAttribute("model", details);
        model.addAttribute("form", form);

        Log.i("Open editing page for hdd with id: " + id);
        return "product/hdd/edit";
    }

    @Override
    @PostMapping("/{id}/edit")
    public String edit(
            UUID id,
            @ModelAttribute("form") HddDto form,
            BindingResult bindingResult,
            Model model
    ) {
        Log.d("edit called - id: %s, form: %s", id, form);

        if (bindingResult.hasErrors()) {
            var details = new HddInputViewModel(
                    createBaseViewModel("Редактирование жесткого диска"),
                    hddInterfaces
            );

            model.addAttribute("model", details);
            model.addAttribute("form", form);

            return "product/hdd/edit";
        }

        service.create(form);

        Log.i("Edit hdd with id: " + id);
        return "redirect:/product/hdd/" + id;
    }

    @Override
    @GetMapping("/{id}/delete")
    public String delete(
            UUID id,
            Model model
    ) {
        Log.d("delete called - id: " + id);

        service.remove(id);

        Log.i("Remove case with id: " + id);
        return "redirect:/product/hdd";
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }
}
