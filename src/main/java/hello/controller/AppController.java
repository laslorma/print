package hello.controller;

import hello.domain.App;
import hello.domain.AppDao;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Victor on 16-Jan-17.
 */
@RestController
@RequestMapping("app")
public class AppController {

    @Autowired
    private AppDao appDao;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    //@ResponseBody
    public String create(@RequestParam(value="domain", defaultValue="smile.philosoph.net") String domain) {
        App app = null;
        try {

            app= new App(domain);

            appDao.save(app);
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created! (id = " + app.getId() + ")";
    }

    @RequestMapping(value ="/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(Integer id) {
        try {
            App app = appDao.findOne(id);
            appDao.delete(app);
        }
        catch (Exception ex) {
            return "Error deleting the user: " + ex.toString();
        }
        return "User succesfully deleted!";
    }

    @RequestMapping("/get-by-domain")
    @ResponseBody
    public String getByDomain(String domain) {
        String userId;
        try {
            App user = appDao.findByDomain(domain);
            userId = String.valueOf(user.getId());
        }
        catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userId;
    }

    @RequestMapping(value = {"/{appId}"},
                method = RequestMethod.GET,
                produces="application/json")
    public App get(@PathVariable(value = "appId")  int appId)
    {
        App app = null;
        try {
            app = appDao.findOne(appId);
        }
        catch (Exception ex) {
            return app;
        }

        return app;
    }


    @RequestMapping("/update")
    @ResponseBody
    public String updateUser(Integer id, String domain) {
        try {
            App app = appDao.findOne(id);
            app.getDomain();

            appDao.save(app);
        }
        catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "User succesfully updated!";
    }



}
