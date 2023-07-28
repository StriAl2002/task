package com.teraplantask.server.control;

import com.teraplantask.server.sqlrequests.GettingAttributes;
import com.teraplantask.server.sqlrequests.GettingFlights;
import com.teraplantask.server.sqlrequests.GettingPairs;
import com.teraplantask.server.sqlrequests.LoadInDB;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;


@RestController
public class Controller {

    //catching requests

    @RequestMapping("/")
    public ModelAndView getWelcomePageAsModel(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainpage.html");
        return modelAndView;
    }

    @RequestMapping(value="/data")
    public ModelAndView welcome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("researcher.html");
        return modelAndView;
    }

    @RequestMapping(value="/pairings")
    public @ResponseBody String pairinginfo(@RequestParam("name") String name) throws SQLException {
        GettingPairs gettingPairs = new GettingPairs();
        return gettingPairs.GettingPairs(name);
    }

    @RequestMapping(value="/flights")
    public @ResponseBody String flightinfo(@RequestParam("name") String name) throws SQLException {
        GettingFlights gettingFlights = new GettingFlights();
        return gettingFlights.GettingFlights(name);
    }

    @RequestMapping(value="/about")
    public @ResponseBody String attributeinfo(@RequestParam("name") String name) throws SQLException {
        GettingAttributes gettingAttributes = new GettingAttributes();
        return gettingAttributes.GettingAttributes(name);
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody ModelAndView handleFileUpload(@RequestParam("name") String name,
                                                 @RequestParam("file") MultipartFile file) throws SQLException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name + ".json")));
                stream.write(bytes);
                stream.close();
                modelAndView.setViewName("download.html");
                new LoadInDB();
                return modelAndView;
            } catch (Exception e) {
                modelAndView.setViewName("mainpage.html");
                return modelAndView;
            }
        } else {
            modelAndView.setViewName("empty.html");
            return modelAndView;
        }
    }
}