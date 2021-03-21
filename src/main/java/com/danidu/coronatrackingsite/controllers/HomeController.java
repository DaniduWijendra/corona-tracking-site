package com.danidu.coronatrackingsite.controllers;

import com.danidu.coronatrackingsite.models.LocationStats;
import com.danidu.coronatrackingsite.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller // to render html ui no restcontroller used
public class HomeController {
    @Autowired//use service
    CoronaVirusDataService coronaVirusDataService;
    @GetMapping("/")
    public String home()
    {
        return "home";
    }
    @GetMapping("/user")
    public String user()
    {
        return "user";
    }
    @GetMapping("/admins")
    public String admin(Model model)
    {
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalCaseReported = allStats.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat->stat.getDifferFromPrev()).sum();
        model.addAttribute("totalCaseReported",totalCaseReported);
        model.addAttribute("totalNewCases",totalNewCases);
        model.addAttribute("locationStats",allStats);//set an attribute and get it in page
        return "admin";
    }
}
