package cz.mazl.tul.controller;

import cz.mazl.tul.blogic.action.index.PrepareIndexDataAction;
import cz.mazl.tul.dto.IndexDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @Autowired
    private PrepareIndexDataAction prepareIndexData;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndex(ModelMap modelMap) {
        IndexDataDTO indexDataDTO = prepareIndexData.prepare();
        modelMap.addAttribute("data", indexDataDTO);
        return "index";
    }
}
