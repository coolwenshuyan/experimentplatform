package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.model.Resource;
import com.coolwen.experimentplatform.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/res")
public class ResourceController {
	@Autowired
	private ResourceService resourceService;

	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("reses", resourceService.listResource());
		return "res/list";
	}
	
	@RequestMapping(value="/add",method= RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("res", new Resource());
		return "res/add";
	}
	
	@RequestMapping(value="/add",method= RequestMethod.POST)
	public String add(Resource res, Model model) {
		resourceService.add(res);
		return "redirect:/admin/res/list";
	}
	
	@RequestMapping(value="/update/{id}",method= RequestMethod.GET)
	public String update(@PathVariable int id, Model model) {
		model.addAttribute("res", resourceService.load(id));
		return "res/update";
	}
	
	@RequestMapping(value="/update/{id}",method= RequestMethod.POST)
	public String update(@PathVariable int id, Resource res, Model model) {
		Resource tr = resourceService.load(id);
		tr.setName(res.getName());
		tr.setPermission(res.getPermission());
		tr.setUrl(res.getUrl());
		resourceService.update(tr);
		return "redirect:/admin/res/list";
	}
}
