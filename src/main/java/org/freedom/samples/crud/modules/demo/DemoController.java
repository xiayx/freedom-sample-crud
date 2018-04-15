package org.freedom.samples.crud.modules.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 示例控制器
 *
 * @author xiayx
 */
@Controller
@RequestMapping({"/demo"/*, "/demos"*/})
public class DemoController {

    @Autowired
    private DemoService demoService;
    @Autowired
    private DemoRepository demoRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
    }

    /* 跳页方法 */

    /** 跳转至列表页 */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String query(Model model, DemoParam demoParam, Pageable pageable) {
        model.addAttribute("entities", demoService.query(demoParam, pageable));
        model.addAttribute("params", demoParam);
        model.addAttribute("pageable", pageable);
        return "crud/demo/list";
    }

    /** 跳转至详情页 */
    @RequestMapping(value = {"/add", "/view", "/modify"}, method = RequestMethod.GET)
    public String detail(Model model, Demo demo) {
        if (demo.getId() != null) demo = demoRepository.getOne(demo.getId());
        //设置默认值，便于测试
        if (demo.getId() == null) {
            demo.setName("name");
            demo.setCreatorId(1L);
            demo.setCreatedTime(new Date());
            demo.setModifierId(1L);
            demo.setModifiedTime(new Date());
        }
        model.addAttribute("entity", demo);
        return "crud/demo/detail";
    }

    /** 保存 */
    @RequestMapping(value = {"/add", "/modify", "/save"}, method = RequestMethod.POST)
    public String save(RedirectAttributes attributes, Demo demo) {
        demoRepository.save(demo);
        attributes.addAttribute("id", demo.getId());
        attributes.addAttribute("success", true);
        return "redirect:/demo/view";
    }

    /** 删除 */
    @RequestMapping(value = "/delete")
    public String delete(HttpServletRequest request, RedirectAttributes attributes, Long id) {
        demoRepository.delete(demoRepository.getOne(id));
        attributes.addAttribute("success", true);
        return "redirect:/demo/list?" + request.getQueryString();
    }



    /* rest api 方法 */

    /** 分页查询数据 */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Page<Demo> query(DemoParam demoParam, Pageable pageable) {
        return demoService.query(demoParam, pageable);
    }


    /** 获取数据 */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Demo get(@PathVariable Long id) {
        return demoRepository.getOne(id);
    }

    /** 新增数据 */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Object add(Demo demo) {
        demoRepository.save(demo);
        return demo.getId();
    }

    /** 全量更新数据 */
    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public Object update(Demo demo) {
        demoRepository.save(demo);
        return demo.getId();
    }

    /** 局部更新数据 */
    @ResponseBody
    @RequestMapping(method = RequestMethod.PATCH)
    public Object patch(Demo demo) {
        demoRepository.save(demo);
        return demo.getId();
    }

    /** 删除数据 */
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable Long id) {
        demoRepository.delete(demoRepository.getOne(id));
    }

}
