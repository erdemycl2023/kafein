package com.kafein.envanter.urun;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.kafein.envanter.core.controller.AbstractController;
import com.kafein.envanter.kategori.KategoriEntity;
import com.kafein.envanter.kategori.KategoriService;

@Controller
@RestController
@RequestMapping("/urun")
public class UrunController extends AbstractController<UrunEntity, UrunService> {
	
	@Autowired
	private UrunService urunService;

	@Autowired
	private KategoriService kategoriService;

	@Override
	public UrunService getService() {
		return urunService;
	}

	@RequestMapping(value = "/search-get", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public ModelAndView searchUrun(@RequestParam String text) {

		ModelAndView modelAndView = new ModelAndView();
		List<UrunEntity> result = getService().getRepository().findByAdContainingOrderByAd(text);
		modelAndView.addObject("text", text);
		modelAndView.addObject("resultList", result);
		modelAndView.setViewName("urun/urunList");
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView listUrun() {
		ModelAndView modelAndView = new ModelAndView();
		List<UrunEntity> result = getService().getAll();

		modelAndView.addObject("resultList", result);
		modelAndView.setViewName("urun/urunList");
		return modelAndView;
	}

	@RequestMapping(value = "/getAdd", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView GetAdd() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("urun/urunAdd");
		
		List<KategoriEntity> kategoriList = kategoriService.getAll();
		modelAndView.addObject("kategoriList", kategoriList);
		return modelAndView;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html")	
	public ModelAndView addUrun(@ModelAttribute  UrunEntity entity) {		
		getService().save(entity);		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("urun", entity);
		modelAndView.addObject("message", "ok");
		
		List<KategoriEntity> kategoriList = kategoriService.getAll();
		modelAndView.addObject("kategoriList", kategoriList);
		getService().history(entity, "ekleme");
		
		modelAndView.setViewName("urun/urunAdd");
		return modelAndView;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = "text/html")	
	public ModelAndView deleteUrun(@PathVariable Long id) {		
		UrunEntity entity = getService().get(id);
		getService().remove(id);
		getService().history(entity, "silme");		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("urun/urunDelete");
		modelAndView.addObject("deletedId", id);
		return modelAndView;
	}


	@RequestMapping(value = "/getUpdate/{id}", method = RequestMethod.GET, produces = "text/html")	
	public ModelAndView updateUrun(@PathVariable Long id) {		
		UrunEntity entity = getService().get(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("urun", entity);
		List<KategoriEntity> kategoriList = kategoriService.getAll();
		modelAndView.addObject("kategoriList", kategoriList);
		
		modelAndView.setViewName("urun/urunUpdate");
		return modelAndView;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/html")	
	public ModelAndView updateUrun(@ModelAttribute  UrunEntity entity) {		
		getService().save(entity);
		getService().history(entity, "guncelleme");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("urun", entity);
		List<KategoriEntity> kategoriList = kategoriService.getAll();
		modelAndView.addObject("kategoriList", kategoriList);
		modelAndView.addObject("message", "ok");
		modelAndView.setViewName("urun/urunUpdate");
		return modelAndView;
	}

	
	
}