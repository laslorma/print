package hello.controller;

import hello.domain.App;
import hello.domain.AppDao;

import org.hibernate.Transaction;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    private final Logger log = LoggerFactory.getLogger(AppController.class);


    /**
     * POST
     * Create a new app
     * @param app
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<App> create(@RequestBody App app) {



        try {

            // Validando
            if (appDao.findByDomain(app.getDomain())!=null) {

                log.error("App domain taken: " + app.getDomain());

                return new ResponseEntity(HttpStatus.BAD_REQUEST);

            }else{

                // Salvando
                appDao.save(app);

            }
        }
        catch (Exception ex) {

            log.error("Error creating app "+ ex.getMessage());

            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<App>(app,HttpStatus.OK);
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
