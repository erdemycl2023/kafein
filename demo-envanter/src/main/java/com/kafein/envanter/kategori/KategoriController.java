package com.kafein.envanter.kategori;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.kafein.envanter.core.controller.AbstractController;

@Controller
@RestController
@RequestMapping("/kategori")
public class KategoriController extends AbstractController<KategoriEntity, KategoriService> {
	
	@Autowired
	private KategoriService kategoriService;

	@Override
	public KategoriService getService() {
		return kategoriService;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView listKategori() {
		ModelAndView modelAndView = new ModelAndView();
		List<KategoriEntity> result = getService().getAll();
		modelAndView.addObject("resultList", result);
		modelAndView.setViewName("kategori/kategoriList");
		return modelAndView;
	}

	@RequestMapping(value = "/getAdd", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView GetAdd() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("kategori/kategoriAdd");
		return modelAndView;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html")	
	public ModelAndView addKategori(@ModelAttribute  KategoriEntity entity) {		
		getService().save(entity);		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", "ok");
		modelAndView.setViewName("kategori/kategoriAdd");
		return modelAndView;
	}
	
}